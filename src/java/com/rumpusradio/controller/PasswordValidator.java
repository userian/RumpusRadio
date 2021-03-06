package com.rumpusradio.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rumpusradio.model.UserRegistration;

public class PasswordValidator implements Validator {
	public boolean supports(Class clazz)
    {
        return clazz.equals(UserRegistration.class);
    }
	
	public void validate(Object command, Errors errors)
    {
    	ValidationUtils.rejectIfEmpty(errors, "password1", "password1.empty");
    	ValidationUtils.rejectIfEmpty(errors, "password2", "password2.empty");
    	
    	//Password newPassword = (Password) command;
    	UserRegistration newPassword = (UserRegistration) command;
    	
    	if (newPassword == null) return;
    	
    	//Make sure passwords are the same
        if( ! newPassword.getPassword1().equals(newPassword.getPassword2()) ) {
        	errors.rejectValue("password1", "password1.notmatch" );
        }
        
    }
}
