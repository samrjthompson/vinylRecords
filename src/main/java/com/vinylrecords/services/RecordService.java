package com.vinylrecords.services;

import com.vinylrecords.exceptions.NotFoundException;
import com.vinylrecords.models.Created;
import com.vinylrecords.models.RecordDocument;
import com.vinylrecords.models.Updated;
import com.vinylrecords.repositories.VinylRecordsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    Logger logger = LoggerFactory.getLogger(RecordService.class);

    private final VinylRecordsRepository repository;

    public RecordService(VinylRecordsRepository repository) {
        this.repository = repository;
    }

    public void upsertRecord(RecordDocument requestBody) throws ServiceUnavailableException {
        try {
            Optional<RecordDocument> existingRecord =
                    Optional.ofNullable(repository.
                        findRecordByArtistAndAlbum(
                                requestBody.getArtist().toLowerCase(),
                                requestBody.getAlbumName().toLowerCase(),
                                requestBody.getAlbumYear()));

            if (existingRecord.isPresent()) {
                requestBody.setCreated(existingRecord.get().getCreated());
                requestBody.setId(existingRecord.get().getId());
            } else {
                requestBody.setCreated(new Created().setAt(LocalDateTime.now()));
            }

            requestBody.setUpdated(new Updated().setAt(LocalDateTime.now()));
            requestBody.setArtist(requestBody.getArtist().toLowerCase());
            requestBody.setAlbumName(requestBody.getAlbumName().toLowerCase());

            repository.save(requestBody);
            logger.info("Successfully saved document!");
        } catch (DataAccessException ex) {
            throw new ServiceUnavailableException("Data access exception thrown when calling database");
        }
    }

    public RecordDocument getRecord(String recordNumber) throws ServiceUnavailableException {
        try {
            return repository.findById(recordNumber).orElseThrow(() -> new NotFoundException("No records could be found for that artist!"));
        } catch (DataAccessException ex) {
            throw new ServiceUnavailableException("Data access exception thrown when calling database");
        } catch (NotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public Optional<List<RecordDocument>> getRecordByArtist(String artist) {
        return Optional.ofNullable(repository.findAllByArtistName(artist.toLowerCase()));
    }

    public Optional<List<RecordDocument>> getAllRecords() {
        return Optional.of(repository.findAll());
    }

    public void deleteRecord(String recordNumber) throws ServiceUnavailableException {
        try {
            repository.deleteById(recordNumber);
        } catch (DataAccessException ex) {
            throw new ServiceUnavailableException("Data access exception thrown when calling database");
        }
    }
}
