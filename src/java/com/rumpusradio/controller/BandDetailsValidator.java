package com.rumpusradio.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rumpusradio.model.Artist;

public class BandDetailsValidator implements Validator {
	public boolean supports(Class clazz)
    {
        return clazz.equals(Artist.class);
    }
	
	public void validate(Object command, Errors errors)
    {
    	ValidationUtils.rejectIfEmpty(errors, "artistName", "artistName.empty");
    	ValidationUtils.rejectIfEmpty(errors, "genre1", "genre1.empty");
    	
    	Artist updateArtist = (Artist) command;
        
        if (updateArtist == null) return;
        
        if( updateArtist.getGenre1() == 0
        		&& updateArtist.getGenre2() == 0 
        		&& updateArtist.getGenre3() == 0 ) {
        	errors.rejectValue("genre1", "genre1.allempty" );
        }
        
    }
}
