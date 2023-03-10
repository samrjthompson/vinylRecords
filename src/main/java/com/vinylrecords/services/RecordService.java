package com.vinylrecords.services;

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
import java.util.List;
import java.util.Optional;

@Service
public class RecordService {

    Logger logger = LoggerFactory.getLogger(RecordService.class);

    private final VinylRecordsRepository repository;

    public RecordService(VinylRecordsRepository repository) {
        this.repository = repository;
    }

    public void upsertRecord(String recordNumber, RecordDocument requestBody) throws ServiceUnavailableException {
        try {
            Optional<RecordDocument> existingDocument = repository.findById(recordNumber);

            existingDocument.map(RecordDocument::getCreated)
                    .ifPresentOrElse(requestBody::setCreated,
                            () -> requestBody.setCreated(new Created().setAt(LocalDateTime.now())));

            requestBody.setUpdated(new Updated().setAt(LocalDateTime.now()));
            requestBody.setArtist(requestBody.getArtist().toLowerCase());

            repository.save(requestBody);
            logger.info("Successfully saved document!");
        } catch (DataAccessException ex) {
            throw new ServiceUnavailableException("Data access exception thrown when calling database");
        }
    }

    public RecordDocument getRecord(String recordNumber) throws ServiceUnavailableException {
        try {
            return repository.findById(recordNumber).orElseThrow();
        } catch (DataAccessException ex) {
            throw new ServiceUnavailableException("Data access exception thrown when calling database");
        }
    }

    public List<RecordDocument> getRecordByArtist(String artist) throws ServiceUnavailableException {
        try {
            List<RecordDocument> recordList = repository.findAllByArtistName(artist.toLowerCase());
            if (!recordList.isEmpty()) {
                logger.info("No records could be found for that artist!");
            } else {
                return recordList;
            }
        } catch (DataAccessException ex) {
            throw new ServiceUnavailableException("Data access exception thrown when calling database");
        }
    }

    public void deleteRecord(String recordNumber) throws ServiceUnavailableException {
        try {
            repository.deleteById(recordNumber);
        } catch (DataAccessException ex) {
            throw new ServiceUnavailableException("Data access exception thrown when calling database");
        }
    }
}
