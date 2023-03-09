package com.vinylrecords.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class Created {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime at;
    private String by;

    public LocalDateTime getAt() {
        return at;
    }

    public Created setAt(LocalDateTime at) {
        this.at = at;
        return this;
    }

    public String getBy() {
        return this.by;
    }

    public Created setBy(String by) {
        this.by = by;
        return this;
    }
}
