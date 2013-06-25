package com.rumpusradio.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import com.rumpusradio.controller.PasswordValidator;
import com.rumpusradio.model.UserRegistration;
import com.rumpusradio.model.UserManager;

public class BandRegisterValidator implements Validator {

	public boolean supports(Class clazz)
    {
        return clazz.equals(UserRegistration.class);
    }

    public void validate(Object command, Errors errors)
    {
    	UserManager userManager = new UserManager();
    	
    	ValidationUtils.rejectIfEmpty(errors, "firstName", "firstName.empty");
    	ValidationUtils.rejectIfEmpty(errors, "lastName", "lastName.empty");
    	ValidationUtils.rejectIfEmpty(errors, "email", "email.empty");
    	/*
    	ValidationUtils.rejectIfEmpty(errors, "password1", "password1.empty");
    	ValidationUtils.rejectIfEmpty(errors, "password2", "password2.empty");
    	*/
    	
    	UserRegistration newUser = (UserRegistration) command;
    	
        if (newUser == null) return;

        if( userManager.findExistingUser( newUser.getEmail() ) ) {
        	errors.rejectValue("email", "email.existing" );
        }
        //Make sure passwords are the same
        /*
        if( ! newUser.getPassword1().equals(newUser.getPassword2()) ) {
        	errors.rejectValue("password1", "password1.notmatch" );
        }
        */
        PasswordValidator passwordValidator = new PasswordValidator();
        passwordValidator.validate(command, errors);
    }
}
