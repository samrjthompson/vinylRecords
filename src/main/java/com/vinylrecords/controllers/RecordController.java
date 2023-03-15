package com.vinylrecords.controllers;

import com.vinylrecords.models.RecordDocument;
import com.vinylrecords.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.ServiceUnavailableException;
import java.util.List;

@CrossOrigin
@RestController
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PutMapping("/records")
    public ResponseEntity<Void> recordUpsert (
            @RequestBody RecordDocument requestBody) throws ServiceUnavailableException {
        recordService.upsertRecord(requestBody);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/records/{record_number}")
    public ResponseEntity<RecordDocument> recordGet (
            @PathVariable("record_number") String recordNumber) throws ServiceUnavailableException {
        recordService.getRecord(recordNumber);
        return ResponseEntity.ok(recordService.getRecord(recordNumber));
    }

    @DeleteMapping("/records/{record_number}")
    public ResponseEntity<Void> recordDelete (
            @PathVariable("record_number") String recordNumber) throws ServiceUnavailableException {
        recordService.deleteRecord(recordNumber);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/records/{artist}")
    public ResponseEntity<List<RecordDocument>> recordGetArtist (
            @PathVariable("artist") String artist) throws ServiceUnavailableException {
        return recordService.getRecordByArtist(artist)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/records")
    public ResponseEntity<List<RecordDocument>> recordGetAll () throws ServiceUnavailableException {
//        ResponseEntity<List<RecordDocument>> response = new ResponseEntity<>(HttpStatus.OK);
//                response.getHeaders().add("Access-Control-Allow-Origin", "*");
        return recordService.getAllRecords()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/index")
    public String showForm() {
        return "index";
    }
}
