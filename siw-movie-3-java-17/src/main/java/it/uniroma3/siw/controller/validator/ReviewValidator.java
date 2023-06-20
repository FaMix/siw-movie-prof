package it.uniroma3.siw.controller.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Review;

@Component
public class ReviewValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Review.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		// TODO Auto-generated method stub
		
	}

}
