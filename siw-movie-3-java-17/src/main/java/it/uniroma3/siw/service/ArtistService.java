package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Image;
import it.uniroma3.siw.repository.ArtistRepository;
import jakarta.transaction.Transactional;

@Service
public class ArtistService {
	@Autowired
	private ArtistRepository artistRepo;

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private ImageService imageService;

	public Iterable<Artist> findAll() {
		return this.artistRepo.findAll();
	}

	public boolean existsByNameAndSurname(Artist artist) {
		return this.artistRepo.existsByNameAndSurname(artist.getName(), artist.getSurname());
	}

	@Transactional
	public Artist save(Artist artist) {
		return this.artistRepo.save(artist);

	}

	public Artist findById(Long id) {
		return this.artistRepo.findById(id).get();
	}

	@Transactional
	public void deleteArtist(Long id) {
		this.artistRepo.deleteById(id);
	}

	public List<Artist> actorsToAdd(Long movieId) {
		List<Artist> actorsToAdd = new ArrayList<>();

		for (Artist a : this.artistRepo.findActorsNotInMovie(movieId)) {
			actorsToAdd.add(a);
		}
		return actorsToAdd;
	}

	public boolean isModified(Artist oldArtist, Artist updated) {
		boolean res = oldArtist.getName().equals(updated.getName()) ||
				oldArtist.getSurname().equals(updated.getSurname()) ||
				oldArtist.getDateOfBirth().equals(updated.getDateOfBirth());
		if(oldArtist.getDateOfDeath() != null)
			return res || oldArtist.getDateOfDeath().equals(updated.getDateOfDeath());
		if(updated.getDateOfDeath() != null)
			return true;
		return res;
	}

	@Transactional
	public void updatePicture(Artist artist, MultipartFile file) {
		if(file.getSize() != 0) {
			Image oldPic = artist.getPicture();
			if(oldPic != null) {
				String filename = oldPic.getFileName();
				this.fileStorageService.delete(filename);
				this.imageService.delete(oldPic.getId());
			}
			Image newPic = this.fileStorageService.createImage(file);
			artist.setPicture(newPic);
			this.imageService.save(newPic);
		}
	}
}
