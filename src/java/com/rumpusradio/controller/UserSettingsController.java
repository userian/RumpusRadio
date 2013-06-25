package com.rumpusradio.controller;

import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.rumpusradio.model.User;
import com.rumpusradio.model.UserRegistration;
import com.rumpusradio.model.GenreManager;
import com.rumpusradio.model.Artist;
import com.rumpusradio.model.ArtistManager;
import com.rumpusradio.model.UserManager;
import com.rumpusradio.util.ApplicationSecurityManager;
import org.springframework.validation.Errors;

public class UserSettingsController extends SimpleFormController {
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private Artist artist;
    private ArtistManager artistManager;
    private UserManager userManager;
    protected ApplicationSecurityManager applicationSecurityManager;
    
    public ModelAndView showForm(
            HttpServletRequest request,
            HttpServletResponse response,
            BindException errors,
            Map controlModel) throws Exception
    {
    	
    	
        return super.showForm(request, response, errors, controlModel);
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
    		//return new ModelAndView(getSuccessView());
    		
    		return showForm(request, response, errors);    
    	} else {
    		return new ModelAndView("error");
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
        
        return model;
        
    }
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	
    	User user =  (User) applicationSecurityManager.getUser(request);
    	
    	logger.info("UserID:"+user.getUserId());
    	
    	UserRegistration userRegistration = new UserRegistration();
        userRegistration.setEmail(user.getEmail());
    	
        return userRegistration;
        
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
