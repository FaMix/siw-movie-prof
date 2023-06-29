package it.uniroma3.siw.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.ArtistRepository;
import it.uniroma3.siw.repository.MovieRepository;
import jakarta.transaction.Transactional;

@Service
public class MovieService {
	
	@Autowired
	MovieRepository movieRepo;
	
	@Autowired
	ArtistRepository artistRepo;

	public Movie findById(Long id) {
		return this.movieRepo.findById(id).get();
	}

	public Iterable<Movie> findAll() {
		return this.movieRepo.findAll();
	}

	public Movie setDirectorToMovie(Long directorId, Long movieId) {
		Artist director = this.artistRepo.findById(directorId).get();
		Movie movie = this.findById(movieId);
		movie.setDirector(director);
		return this.save(movie);
	}

	public Movie save(Movie movie) {
		return this.movieRepo.save(movie);
	}

	public List<Movie> findByYear(int year) {
		return this.movieRepo.findByYear(year);
	}

	public Movie addActorToMovie(Long actorId, Long movieId) {
		Movie movie = this.findById(movieId);
		Artist actor = this.artistRepo.findById(actorId).get();
		Set<Artist> actors = movie.getActors();
		actors.add(actor);
		return this.save(movie);
	}

	public Movie removeActorFromMovie(Long actorId, Long movieId) {
		Movie movie = this.findById(movieId);
		Artist actor = this.artistRepo.findById(actorId).get();
		Set<Artist> actors = movie.getActors();
		actors.remove(actor);
		return this.save(movie);
	}

	@Transactional
	public void addReviewToMovie(Movie movie, Review review) {
		movie.getReviews().add(review);
	}

	@Transactional
	public boolean removeReviewFromMovie(Movie reviewedMovie, Review review) {
		boolean res = reviewedMovie.getReviews().remove(review);
		this.save(reviewedMovie);
		return res;
	}

	@Transactional
	public void deleteMovie(Long id) {
		this.movieRepo.deleteById(id);
	}

}
