package com.rumpusradio.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rumpusradio.model.UserRegistration;
import com.rumpusradio.model.UserManager;

public class ForgotPasswordValidator implements Validator {

	public boolean supports(Class clazz)
    {
        return clazz.equals(UserRegistration.class);
    }
	
	public void validate(Object command, Errors errors)
    {
		ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
		
		UserManager userManager = new UserManager();
		String email = ((UserRegistration)command ).getEmail();
		
		if( !email.contentEquals("") && !userManager.findExistingUser( email ) ) {
			errors.rejectValue("email", "email.notexisting");
		}
    }
}
