package com.vinylrecords.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "records")
public class Record {
    @Id
    private String id;
    private String name;

    public Record(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
