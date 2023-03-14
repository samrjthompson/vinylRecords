package com.vinylrecords;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@SpringBootApplication
public class VinylRecordsApplication {

	public static void main(String[] args) {
//		if (!Desktop.isDesktopSupported()) {
//			System.out.println("This app needs a desktop manager to run, exiting.");
//			System.exit(1);
//		}
//		new SpringApplicationBuilder(VinylRecordsApplication.class).headless(false).run(args);
		SpringApplication.run(VinylRecordsApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void openBrowserAfterStartup() throws IOException, URISyntaxException {
//		// open default browser after start:
//		Desktop.getDesktop().browse(new URI("http://localhost:8181/records"));
//	}
}
