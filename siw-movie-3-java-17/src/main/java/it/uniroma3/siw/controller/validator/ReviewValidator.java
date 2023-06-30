package it.uniroma3.siw.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.ReviewRepository;

@Component
public class ReviewValidator implements Validator {
	
	@Autowired
	private ReviewRepository reviewRepo;

	@Override
	public boolean supports(Class<?> clazz) {
		return Review.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Review rev = (Review) o;
		if(rev.getOwner() != null && rev.getReviewedMovie() != null && 
				this.reviewRepo.existsByOwnerAndReviewedMovie(rev.getOwner(), rev.getReviewedMovie()))
			errors.reject("review.duplicate");
	}

}
