package com.rumpusradio.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.rumpusradio.model.MusicManager;
import com.rumpusradio.model.Artist;
import com.rumpusradio.model.User;
import com.rumpusradio.util.ApplicationSecurityManager;

public class BandMusicController implements Controller {
	
	protected final Log logger = LogFactory.getLog(getClass());
    protected ApplicationSecurityManager applicationSecurityManager;
    private MusicManager musicManager;
    private String message;
    
    public BandMusicController() {
    	message = ""; 
    }
    
    public ModelAndView handleRequest(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception
    {
    	
    	Map<String, Object> myModel  = new HashMap<String, Object>();
        	
		User user = (User) applicationSecurityManager.getUser(request);
		Artist artist = (Artist)applicationSecurityManager.getArtist( request );
		
		logger.info("UserID:" + user.getUserId());
		logger.info("ArtistID:" + artist.getArtistId());
		
		//myModel.put("message", message);
		myModel.put("songs", musicManager.getArtistSongsNoAlbums( artist.getArtistId() ));
		myModel.put("albums", musicManager.getArtistAlbums(artist.getArtistId()) );
		
		if( user.getUploadAgreement() == 0 ){
			response.sendRedirect("uploadagreement.html");
		} 	
		myModel.put("message", message);
		
		return new ModelAndView("band_music", "model", myModel);
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
    
    public void setMusicManager( MusicManager musicManager ) {
    	this.musicManager = musicManager;
    }
    public MusicManager getMusicManager() {
    	return musicManager;
    }
}
