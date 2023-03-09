package com.vinylrecords;

import com.vinylrecords.models.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.vinylrecords.repositories.VinylRecordsRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.vinylrecords.repositories")
public class VinylRecordsApplication implements CommandLineRunner {

	@Autowired
	VinylRecordsRepository vinylRecordsRepository;

	public static void main(String[] args) {
		SpringApplication.run(VinylRecordsApplication.class, args);
	}

	// Create
	void createVinylRecord() {
		System.out.println("Data creation started...");
		vinylRecordsRepository.save(new Record());
		System.out.println("Data creation complete.");
	}

	public void run(String... args) {
		System.out.println("-----------------CREATE GROCERY ITEMS------------------------");
		createVinylRecord();
	}
}
