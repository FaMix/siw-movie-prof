package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.ReviewValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MovieService;
import it.uniroma3.siw.service.ReviewService;
import jakarta.validation.Valid;

@Controller
public class ReviewController {
	@Autowired
	private ReviewService reviewService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private ReviewValidator reviewValidator;

	@GetMapping(value="/formNewReview/{movieId}")
	public String formNewReview(@PathVariable("movieId") Long id, Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
		if(!(credentials.getRole().equals(Credentials.DEFAULT_ROLE))) {
			model.addAttribute("movie", this.movieService.findById(id));
			return "movie.html";
		}
		model.addAttribute("movieId", id);
		model.addAttribute("review", new Review());
		return "formNewReview.html";
	}

	@PostMapping("/review/{movieId}")
	public String newReview(@ModelAttribute("username") String username, @PathVariable("movieId") Long movieId, 
			@Valid @ModelAttribute("review") Review review, BindingResult bindingResult, Model model) {
		Credentials credentials = this.credentialsService.getCredentials(username);
		User user = credentials.getUser();
		Movie movieAdded = this.movieService.findById(movieId);
		review.setReviewedMovie(movieAdded);
		review.setOwner(user);
		this.reviewValidator.validate(review, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.movieService.addReviewToMovie(movieAdded, review);
			this.reviewService.save(review);
			this.movieService.save(movieAdded);
			model.addAttribute("movie", movieAdded);
			model.addAttribute("currentUser", user);
			return "movie.html";
		} else {
			return "formNewReview.html";
		}
	}

	@GetMapping("/admin/deleteReview/{id}")
	public String deleteReview(@PathVariable("id") Long id, Model model) {
		Review review = this.reviewService.findById(id);
		Movie reviewedMovie = review.getReviewedMovie();
		this.reviewService.deleteReview(id);
		model.addAttribute("movie", reviewedMovie);
		return "admin/movieAdmin.html";
	}

	@GetMapping("/updateReview/{id}")
	public String formUpdateReview(@PathVariable("id") Long id, Model model) {
		Review review = this.reviewService.findById(id);
		model.addAttribute("review", review);
		return "formUpdateReview.html";
	}

	@PostMapping("/updateReview/{id}")
	public String newReview(@PathVariable("id") Long id, 
			@ModelAttribute("review") Review review, Model model) {
		Review oldReview = this.reviewService.findById(id);
		review.setOwner(oldReview.getOwner());
		review.setReviewedMovie(oldReview.getReviewedMovie());
		this.reviewService.save(review);
		model.addAttribute("movie", review.getReviewedMovie());
		model.addAttribute("currentUser", review.getOwner());
		return "movie.html";
	}
}
