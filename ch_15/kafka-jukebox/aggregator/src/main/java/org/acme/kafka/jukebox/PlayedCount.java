package org.acme.kafka.jukebox;

import java.time.Instant;

public class PlayedCount {
    public int count;
    public String title;
    public String artist;
    public int id;
    public Instant timestamp;

    public PlayedCount(int id, String title, String artist,
                       int count, Instant timestamp) {
        this.count = count;
        this.title = title;
        this.artist = artist;
        this.id = id;
        this.timestamp = timestamp;
    }
}
