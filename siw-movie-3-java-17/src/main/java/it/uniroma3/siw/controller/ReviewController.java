package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MovieService;
import it.uniroma3.siw.service.ReviewService;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;
	
	@Autowired
	private MovieService movieService;
	
	@Autowired
	private CredentialsService credentialsService;
	
	@GetMapping(value="/formNewReview/{movieId}")
	public String formNewReview(@PathVariable("movieId") Long id, Model model) {
		model.addAttribute("movieId", id);
		model.addAttribute("review", new Review());
		return "formNewReview.html";
	}
	
	@PostMapping("/review/{movieId}")
	public String newReview(@ModelAttribute("username") String username, @PathVariable("movieId") Long movieId, 
					@ModelAttribute("review") Review review, BindingResult bindingResult, Model model) {
		//this.reviewValidator.validate(review, bindingResult);
		Movie movieAdded = this.movieService.findById(movieId);
		Credentials credentials = this.credentialsService.findByUsername(username);
		User user = credentials.getUser();
		if (!this.reviewService.existsByOwnerAndReviewedMovie(user, movieAdded)) {
			this.movieService.addReviewToMovie(movieAdded, review);
			review.setReviewedMovie(movieAdded);
			review.setOwner(user);
			this.reviewService.save(review);
			this.movieService.save(movieAdded);
			model.addAttribute("movie", movieAdded);
			return "movie.html";
		} else {
			return "formNewReview.html";
		}
	}
}
