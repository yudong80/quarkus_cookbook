package org.acme.kafka.jukebox;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.enterprise.context.ApplicationScoped;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.KafkaRecord;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

@ApplicationScoped
public class Jukebox {
    private static final Logger LOG = Logger.getLogger(Jukebox.class);

    private ThreadLocalRandom random = ThreadLocalRandom.current();

    private List<Song> songs = Collections.unmodifiableList(
            Arrays.asList(
                new Song(1, "Confessions", "Usher"),
                new Song(2, "How Do I Live", "LeAnn Rimes"),
                new Song(3, "Physical", "Olivia Newton-John"),
                new Song(4, "You Light Up My Life", "Debby Boone"),
                new Song(5, "The Twist", "Chubby Checker"),
                new Song(6, "Mack the Knife", "Bobby Darin"),
                new Song(7, "Night Fever", "Bee Gees"),
                new Song(8, "Bette Davis Eyes", "Kim Carnes"),
                new Song(9, "Macarena (Bayside Boys Mix)", "Los Del Rio"),
                new Song(10, "Yeah!", "Usher")
            )
    );

    @Outgoing("song-values")
    public Multi<KafkaRecord<Integer, String>> generate() {
        return Multi.createFrom().ticks().every(Duration.ofMillis(500))
                .onOverflow().drop()
                .map(tick -> {
                   Song s = songs.get(random.nextInt(songs.size()));
                   int timesPlayed = random.nextInt(1, 100);

                   LOG.infov("song {0}, times played: {1,number}",
                           s.title, timesPlayed);
                   return KafkaRecord.of(s.id, Instant.now()
                                               + ";" + timesPlayed);
                });
    }

    @Outgoing("songs")
    public Multi<KafkaRecord<Integer, String>> songs() {
        return Multi.createFrom().iterable(songs)
                .map(s -> KafkaRecord.of(s.id,
                        "{\n" +
                        "\t\"id\":\""+ s.id + "\",\n" +
                        "\t\"title\":\"" + s.title + "\",\n" +
                        "\t\"artist\":\"" + s.artist + "\"\n" +
                        "}"
                        ));
    }

    private static class Song {
        int id;
        String title;
        String artist;

        public Song(int id, String title, String artist) {
            this.id = id;
            this.title = title;
            this.artist = artist;
        }
    }
}
