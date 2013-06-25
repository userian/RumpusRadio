package com.rumpusradio.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.rumpusradio.model.User;
import com.rumpusradio.model.GenreManager;
import com.rumpusradio.model.Artist;
import com.rumpusradio.model.ArtistInfo;
import com.rumpusradio.model.ArtistManager;
import com.rumpusradio.model.UserManager;
import com.rumpusradio.util.ApplicationSecurityManager;
import org.springframework.validation.Errors;

public class BandDetailsController extends SimpleFormController {
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private Artist artist;
    private ArtistManager artistManager;
    private UserManager userManager;
    protected ApplicationSecurityManager applicationSecurityManager;
    private String message;
    
    public BandDetailsController() {
    	message = "";
    }
    public ModelAndView showForm(
            HttpServletRequest request,
            HttpServletResponse response,
            BindException errors,
            Map controlModel) throws Exception
    {
    	
    	Map<String, Object> model = new HashMap<String, Object>();
    	if( controlModel != null ) {
    		model.putAll((HashMap<String, Object>)controlModel);
    	}
    	
        return super.showForm(request, response, errors, model);
    }
    
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception
    {
    	
    	artist = (Artist) command;
    	
    	// UPDATE
    	if( artistManager.saveArtistDetails( artist ) ) {
    		return new ModelAndView(getSuccessView());
    		//message = "Profile Saved";
    		//return showForm(request, response, errors);    
    	} else {
    		message = "*Error Saving Profile*";
    		return showForm(request, response, errors);    
    	}
    }
    
    protected Map referenceData(HttpServletRequest request,
					            Object command, 
					            Errors errors ) throws Exception {
    	
    	Map<Long, String> genreMap = new HashMap<Long, String>();
    	GenreManager genreManager = new GenreManager();

    	artist = new Artist();
		User user = (User) applicationSecurityManager.getUser(request);
		
	    if (user != null)
	    {
	    	artist = user.getArtist();
	    }
	    
	    genreMap.put(Long.valueOf(0).longValue(),"Please Select");
    	genreMap.putAll( genreManager.getAllGenres() );
    	

    	
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("command", command);
        model.put("genres", genreMap);
        
        model.put("message", message);
    	message = "";
    	
        return model;
        
    }
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	
    	User user =  (User) applicationSecurityManager.getUser(request);
    	Artist artist;
    	//logger.debug("UserID:"+user.getUserId());
        //Artist artist = userManager.findArtist( user.getUserId() );
        
    	logger.info("UserID:"+user.getUserId());
        //Artist artist = userManager.findArtist( user.getUserId() );
    	artist = userManager.findArtist( user.getUserId() );
    	/*
    	Set artists = user.getArtists();
    	if( ! artists.isEmpty() ) {
    		artist = (Artist)artists.iterator().next();
    	//	Artist artist = user.getArtists();
    	
    	} else {        	
        	logger.info("Can't find Artist");
    		artist = new Artist();
    		artist.setUserId( user.getUserId() );
        }
        */
    	if( artist == null ) {
    		logger.info("Can't find Artist from findArtist()");
    		artist = new Artist();
    		artist.setUserId( user.getUserId() );
    	}
        if( artist.getArtistInfo() == null ) {
        	artist.setArtistInfo( new ArtistInfo() );
        }
        logger.info("ArtistID: "+artist.getArtistId());
        
        return artist;
        
    }
    public UserManager getUserManager()
    {
        return userManager;
    }
    public void setUserManager(UserManager userManager)
    {
        this.userManager = userManager;
    }
    public ArtistManager getArtistManager() {
    	return this.artistManager;
    }
    public void setArtistManager( ArtistManager artistManager ) {
    	this.artistManager = artistManager;
    }
    public ApplicationSecurityManager getApplicationSecurityManager()
    {
        return applicationSecurityManager;
    }
    public void setApplicationSecurityManager(
            ApplicationSecurityManager applicationSecurityManager)
    {
        this.applicationSecurityManager = applicationSecurityManager;
    }
}
