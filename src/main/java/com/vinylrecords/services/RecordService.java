package com.vinylrecords.services;

import com.vinylrecords.models.Created;
import com.vinylrecords.models.RecordDocument;
import com.vinylrecords.models.Updated;
import com.vinylrecords.repositories.VinylRecordsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.naming.ServiceUnavailableException;
import java.time.LocalDateTime;
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
            requestBody.setId(recordNumber);
            Optional<RecordDocument> existingDocument = repository.findById(recordNumber);

            existingDocument.map(RecordDocument::getCreated)
                    .ifPresentOrElse(requestBody::setCreated,
                            () -> requestBody.setCreated(new Created().setAt(LocalDateTime.now())));

            requestBody.setUpdated(new Updated().setAt(LocalDateTime.now()));

            repository.save(requestBody);
            logger.info("Successfully saved document!");
        } catch (DataAccessException ex) {
            throw new ServiceUnavailableException("Data access exception thrown when calling database");
        }
    }

    public void getRecord(String recordNumber) throws ServiceUnavailableException {
        try {
            repository.findById(recordNumber);
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
