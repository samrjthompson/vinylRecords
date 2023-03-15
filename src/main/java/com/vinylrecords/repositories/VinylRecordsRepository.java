package com.vinylrecords.repositories;

import com.vinylrecords.models.RecordDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("repository")
public interface VinylRecordsRepository extends MongoRepository<RecordDocument, String> {

    @Query("{'artist': '?0'}")
    List<RecordDocument> findAllByArtistName(String artist);

    @Query("{$and: [{'artist': '?0'}, {'albumName': '?1'}, {'albumYear': ?2}]}")
    RecordDocument findRecordByArtistAndAlbum(String artist, String albumName, int albumYear);
}
