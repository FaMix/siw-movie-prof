package it.uniroma3.siw.service;

import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import it.uniroma3.siw.controller.ImageController;
import it.uniroma3.siw.model.Image;
import jakarta.transaction.Transactional;

@Service
public class FileStorageService {
	private final Path root = Paths.get("./src/main/resources/static/images");

	@Transactional
	public void save(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		} catch (Exception e) {
			if (e instanceof FileAlreadyExistsException) {
				throw new RuntimeException("A file of that name already exists.");
			}

			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Transactional
	public void delete(String filename) {
		try {
			Path file = root.resolve(filename);
			Files.delete(file);
		} catch(Exception e) {
			if (e instanceof NoSuchFileException) {
				throw new RuntimeException("File not found");
			}
			throw new RuntimeException(e.getMessage());
		}
	}

	@Transactional
	public Image createImage(MultipartFile file) {
		this.save(file);
		String url = MvcUriComponentsBuilder.fromMethodName(ImageController.class, "getImage", file.getOriginalFilename())
				.build().toString();
		return new Image(file.getOriginalFilename(), url);
	}

	@Transactional
	public Resource load(String filename) {
		try {
			Path file = root.resolve(filename);
			Resource resource = new UrlResource(file.toUri());

			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("Could not read the file!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("Error: " + e.getMessage());
		}
	}
}