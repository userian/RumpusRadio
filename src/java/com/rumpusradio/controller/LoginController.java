package com.rumpusradio.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rumpusradio.model.User;
import com.rumpusradio.model.Artist;
import com.rumpusradio.model.UserManager;
import com.rumpusradio.util.ApplicationSecurityManager;
import com.rumpusradio.util.Security;

import java.util.HashMap;
import java.util.Map;

public class LoginController extends SimpleFormController {
	
	private UserManager userManager;
    private ApplicationSecurityManager applicationSecurityManager;
    private String message;
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    
    public LoginController() {
    	message = "";
    }
    
    
    /** 
     * Always returns a new User object
     */
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception
    {
        return new User();
    }

    
    /** Forwards to success view, if already logged in */
    public ModelAndView showForm(
            HttpServletRequest request,
            HttpServletResponse response,
            BindException errors,
            Map controlModel) throws Exception
    {
        if (applicationSecurityManager.getUser(request) != null) {
        	logger.info("User already authenticated ");
            return new ModelAndView(getSuccessView());
        }
        logger.info("User not already authenticated ");
        Map<String, Object> model = new HashMap<String, Object>();
    	if( controlModel != null ) {
    		model.putAll((HashMap<String, Object>)controlModel);
    	}
    	model.put("message", message);
    	message = "";
        return super.showForm(request, response, errors, model);
    }
    
    /** Validates user/password against database */
    public void onBindAndValidate(
            HttpServletRequest request,
            Object command,
            BindException errors) throws Exception
    {
        if (errors.hasErrors()) return;

        User formUser = (User) command;
        User dbUser = (User) command;
        
        logger.error("Email: "+formUser.getEmail()+" Password: "+Security.MD5(formUser.getPassword())+" UserID "+ formUser.getUserId() );
        
        if ((dbUser = userManager.findUser(formUser.getEmail(), formUser.getPassword())) == null) {
        	logger.info("User not found ");
            errors.reject("email.authenticate"); 
            message = "Failed Login: Email / Password combination incorrect";
    	} else {
    		logger.info("User has authenticated ");
            applicationSecurityManager.setUser(request, dbUser);
            Artist artist = userManager.findArtist( dbUser.getUserId() );
            if( artist == null ) {
            	artist = new Artist();
            }
            logger.info("ArtistID: " + artist.getArtistId() );
            applicationSecurityManager.setArtist(request, artist);
        }
    }

    /** returns ModelAndView(getSuccessView()) */
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception
    {
    	logger.info("User should be authenticated ");
        return new ModelAndView(getSuccessView());
    }
    
    public UserManager getUserManager()
    {
        return userManager;
    }

    public void setUserManager(UserManager userManager)
    {
        this.userManager = userManager;
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
