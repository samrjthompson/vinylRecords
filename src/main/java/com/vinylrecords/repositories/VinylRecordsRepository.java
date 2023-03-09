package com.vinylrecords.repositories;

import com.vinylrecords.models.Record;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VinylRecordsRepository extends MongoRepository<Record, String> {
}
