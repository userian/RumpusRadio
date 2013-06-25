package com.rumpusradio.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rumpusradio.model.Album;

public class NewAlbumValidator implements Validator  {
	
	public boolean supports(Class clazz)
    {
        return clazz.equals(Album.class);
    }

    public void validate(Object command, Errors errors)
    {
    	
    	ValidationUtils.rejectIfEmpty(errors, "albumName", "albumname.empty");
    	/*
    	ValidationUtils.rejectIfEmpty(errors, "password", "password.empty");
    	
    	 user = (User) command;
        if (user == null) return;

        String email = user.getEmail();
        String password = user.getPassword();

        if (email == null || email.trim().length() < 1)
            errors.reject("email.empty");
        else
            if ( password == null )
                errors.reject("password.empty");
        */
    }
}
