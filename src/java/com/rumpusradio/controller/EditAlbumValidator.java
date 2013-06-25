package com.rumpusradio.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rumpusradio.model.Album;

public class EditAlbumValidator implements Validator  {
	
	public boolean supports(Class clazz)
    {
        return clazz.equals(Album.class);
    }

    public void validate(Object command, Errors errors)
    {
    	
    	ValidationUtils.rejectIfEmpty(errors, "albumName", "albumname.empty");
    	
    }
}
