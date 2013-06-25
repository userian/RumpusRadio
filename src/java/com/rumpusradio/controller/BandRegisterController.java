package com.rumpusradio.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.rumpusradio.model.UserRegistration;
import com.rumpusradio.model.UserManager;
import com.rumpusradio.model.ArtistManager;
import com.rumpusradio.model.User;
import com.rumpusradio.model.Artist;
import com.rumpusradio.util.ApplicationSecurityManager;

public class BandRegisterController extends SimpleFormController {
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    protected ApplicationSecurityManager applicationSecurityManager;
    
    public ModelAndView showForm(
            HttpServletRequest request,
            HttpServletResponse response,
            BindException errors,
            Map controlModel) throws Exception
    {

        return super.showForm(request, response, errors, controlModel);
    }
    
    /** Validates user/password against database */
    public void onBindAndValidate(
            HttpServletRequest request,
            Object command,
            BindException errors) throws Exception
    {
    	
    }
    
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception
    {
    	UserManager userManager = new UserManager();
    	ArtistManager artistManager = new ArtistManager();
    	UserRegistration newUser = (UserRegistration) command;
    	
    	if( newUser.addNewUser( ) ) {
    		
    		User user = userManager.findUser( newUser.getEmail(), newUser.getPassword1() );
    		artistManager.newArtist( user );
    		
    		applicationSecurityManager.setUser(request, user);
    		//applicationSecurityManager.setArtist(request, user.getArtist() );
    		Artist artist = userManager.findArtist( user.getUserId() );
    		applicationSecurityManager.setArtist(request, artist );
    		
    		return new ModelAndView(getSuccessView());
    	} else {
    		return new ModelAndView("error");
    	}
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


