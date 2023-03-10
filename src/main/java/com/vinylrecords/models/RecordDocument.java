package com.vinylrecords.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "records")
public class RecordDocument {

    @Id
    private String id;
    private String artist;
    private int albumYear;
    private Updated updated;
    private Created created;

    public String getId() {
        return id;
    }

    public RecordDocument setId(String id) {
        this.id = id;
        return this;
    }

    public String getArtist() {

        return artist;
    }

    public RecordDocument setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public int getAlbumYear() {

        return albumYear;
    }

    public RecordDocument setAlbumYear(int albumYear) {
        this.albumYear = albumYear;
        return this;
    }

    public Updated getUpdated() {

        return updated;
    }

    public RecordDocument setUpdated(Updated updated) {
        this.updated = updated;
        return this;
    }

    public Created getCreated() {
        return created;
    }

    public RecordDocument setCreated(Created created) {
        this.created = created;
        return this;
    }
}
