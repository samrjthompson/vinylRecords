package com.vinylrecords.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "records")
public class Record {
    @Id
    private String id;
    private String name;
    private int albumYear;
    private Updated updated;
    private Created created;
}
