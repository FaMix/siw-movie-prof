package it.uniroma3.siw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.uniroma3.siw.service.FileStorageService;
import jakarta.annotation.Resource;

@SpringBootApplication
public class SiwMovie03Application {


	public static void main(String[] args) {
		SpringApplication.run(SiwMovie03Application.class, args);
	}
}
