package com.vinylrecords.controllers;

import com.vinylrecords.models.RecordDocument;
import com.vinylrecords.services.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;

@RestController
public class RecordController {

    private RecordService recordService;

    @PutMapping("/vinylrecords/{record_number}")
    public ResponseEntity<Void> recordUpsert (
            @PathVariable("record_number") String recordNumber,
            @RequestBody RecordDocument requestBody) throws ServiceUnavailableException {
        recordService.upsertRecord(recordNumber, requestBody);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/vinylrecords/{record_number}")
    public ResponseEntity<Void> recordGet (
            @PathVariable("record_number") String recordNumber) throws ServiceUnavailableException {
        recordService.getRecord(recordNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/vinylrecords/{record_number}")
    public ResponseEntity<Void> recordDelete (
            @PathVariable("record_number") String recordNumber) throws ServiceUnavailableException {
        recordService.deleteRecord(recordNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
