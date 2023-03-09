package com.vinylrecords;

import com.vinylrecords.models.RecordDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.vinylrecords.repositories.VinylRecordsRepository;

@SpringBootApplication
public class VinylRecordsApplication {

	public static void main(String[] args) {
		SpringApplication.run(VinylRecordsApplication.class, args);
	}
}
