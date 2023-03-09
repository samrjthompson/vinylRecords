package com.vinylrecords.repositories;

import com.vinylrecords.models.RecordDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("repository")
public interface VinylRecordsRepository extends MongoRepository<RecordDocument, String> {
}
