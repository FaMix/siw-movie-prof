package it.uniroma3.siw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	private ReviewRepository reviewRepo;

	public boolean existsByOwnerAndReviewedMovie(User owner, Movie movie) {
		return this.reviewRepo.existsByOwnerAndReviewedMovie(owner, movie);
	}

	public Review save(Review review) {
		return this.reviewRepo.save(review);
	}

}
