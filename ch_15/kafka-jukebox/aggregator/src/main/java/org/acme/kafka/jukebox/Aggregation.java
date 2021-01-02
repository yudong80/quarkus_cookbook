package org.acme.kafka.jukebox;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Aggregation {
    public int songId;
    public String songTitle;
    public String songArtist;
    public int count;
    public int sum;
    public int min;
    public int max;
    public double avg;

    public Aggregation updateFrom(PlayedCount playedCount) {
        songId = playedCount.id;
        songTitle = playedCount.title;
        songArtist = playedCount.artist;

        count++;
        sum += playedCount.count;
        avg = BigDecimal.valueOf(sum / count)
                .setScale(1, RoundingMode.HALF_UP).doubleValue();
        min = Math.min(min, playedCount.count);
        max = Math.max(max, playedCount.count);

        return this;
    }
}
