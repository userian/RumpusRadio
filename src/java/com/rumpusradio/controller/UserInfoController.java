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
import com.rumpusradio.model.ArtistManager;
import com.rumpusradio.model.UserManager;
import com.rumpusradio.util.ApplicationSecurityManager;

public class UserInfoController extends SimpleFormController {
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private User user;
    private ArtistManager artistManager;
    private UserManager userManager;
    private String message;
    protected ApplicationSecurityManager applicationSecurityManager;
    
    public UserInfoController() {
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
    	model.put("message", message);
    	message = "";
        return super.showForm(request, response, errors, model);
    }
    
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception
    {
    	
    	user = (User) applicationSecurityManager.getUser(request);
    	
    	User newInfo = (User) command;
    	
    	user.setFirstName( newInfo.getFirstName() );
    	user.setLastName( newInfo.getLastName() );
    	user.setEmail( newInfo.getEmail() );
    	
    	// UPDATE
    	if( userManager.saveUserInfo( user ) ) {
    		message = "Info Saved";
    		return showForm(request, response, errors);    
    	} else {    		
    		message = "*Error Saving Info*";
    		return showForm(request, response, errors);    
    	}
    }
    
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	
    	User user =  (User) applicationSecurityManager.getUser(request);
    	
    	logger.info("UserID:"+user.getUserId());
    	
        return user;
        
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
