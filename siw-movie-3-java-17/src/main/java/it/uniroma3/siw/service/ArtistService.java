package it.uniroma3.siw.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.repository.ArtistRepository;

@Service
public class ArtistService {
	@Autowired
	ArtistRepository artistRepo;

	public Iterable<Artist> findAll() {
		return this.artistRepo.findAll();
	}

	public List<Artist> actorsToAdd(Long movieId) {
		List<Artist> actorsToAdd = new ArrayList<>();
		
		for (Artist a : this.artistRepo.findActorsNotInMovie(movieId)) {
			actorsToAdd.add(a);
		}
		return actorsToAdd;
	}

	public boolean existsByNameAndSurname(Artist artist) {
		return this.artistRepo.existsByNameAndSurname(artist.getName(), artist.getSurname());
	}

	public Artist save(Artist artist) {
		return this.artistRepo.save(artist);
		
	}

	public Artist findById(Long id) {
		return this.artistRepo.findById(id).get();
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

}
