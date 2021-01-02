package org.acme.kafka.jukebox;

import java.time.Instant;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import io.quarkus.kafka.client.serialization.JsonbSerde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.GlobalKTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.Stores;

@ApplicationScoped
public class TopologyProducer {
    static final String SONG_STORE = "song-store";

    private static final String SONG_TOPIC = "songs";
    private static final String SONG_VALUES_TOPIC = "song-values";
    private static final String SONG_AGG_TOPIC = "song-aggregated";

    @Produces
    public Topology buildTopology() {
        StreamsBuilder builder = new StreamsBuilder();

        JsonbSerde<Song> songSerde = new JsonbSerde<>(Song.class);
        JsonbSerde<Aggregation> aggregationSerde =
                new JsonbSerde<>(Aggregation.class);

        KeyValueBytesStoreSupplier storeSupplier =
                Stores.persistentKeyValueStore(SONG_STORE);

        GlobalKTable<Integer, Song> songs = builder.globalTable(SONG_TOPIC,
                Consumed.with(Serdes.Integer(), songSerde));

        builder.stream(SONG_VALUES_TOPIC, Consumed.with(Serdes.Integer(),
                Serdes.String()))
                .join(
                        songs,
                        (songId, timestampAndValue) -> songId,
                        (timestampAndValue, song) -> {
                            String[] parts = timestampAndValue.split(";");
                            return new PlayedCount(song.id, song.title,
                                    song.artist,
                                    Integer.parseInt(parts[1]),
                                    Instant.parse(parts[0]));
                        }
                )
                .groupByKey()
                .aggregate(
                        Aggregation::new,
                        (songId, value, aggregation) ->
                                aggregation.updateFrom(value),
                        Materialized.<Integer, Aggregation> as(storeSupplier)
                            .withKeySerde(Serdes.Integer())
                            .withValueSerde(aggregationSerde)
                )
                .toStream()
                .to(
                        SONG_AGG_TOPIC,
                        Produced.with(Serdes.Integer(), aggregationSerde)
                );
        return builder.build();
    }
}
