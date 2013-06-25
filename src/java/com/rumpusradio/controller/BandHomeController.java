package com.rumpusradio.controller;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rumpusradio.model.Artist;
import com.rumpusradio.model.User;
import com.rumpusradio.util.ApplicationSecurityManager;

public class BandHomeController implements Controller {
	
	private String successView;
	private ApplicationSecurityManager applicationSecurityManager;
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
	    	throws ServletException, IOException {
		boolean showNoMusic = false;
		boolean showNoProfile = false;
		
        Map<String, Object> myModel  = new HashMap<String, Object>();
    	
		User user = (User) applicationSecurityManager.getUser(request);
		Artist artist = (Artist)applicationSecurityManager.getArtist( request );
		
		//Check to see if artist has uploaded music
		if( artist.getSongs().size() == 0 ) {
			showNoMusic = true;
		}
		
		//Check to see if artist has filled out their profile
		if( artist.getArtistName() == null ) {
			showNoProfile = true;
		}
		
		logger.info("UserID:" + user.getUserId());
		logger.info("ArtistID:" + artist.getArtistId());
		
		myModel.put("showNoMusic", showNoMusic);
		myModel.put("showNoProfile", showNoProfile);
		
		
        return new ModelAndView("band_home", "model", myModel);

        //return new ModelAndView(getSuccessView());
	}
	
	public String getSuccessView()
    {
        return successView;
    }

    public void setSuccessView(String successView)
    {
        this.successView = successView;
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

