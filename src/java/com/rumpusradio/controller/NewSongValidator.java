package com.rumpusradio.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rumpusradio.model.Song;

public class NewSongValidator implements Validator {

	public boolean supports(Class clazz)
    {
        return clazz.equals(Song.class);
    }

    public void validate(Object command, Errors errors)
    {
    	ValidationUtils.rejectIfEmpty(errors, "songName", "songname.empty");
    }
    
}
