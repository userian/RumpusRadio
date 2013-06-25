package com.rumpusradio.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.rumpusradio.util.Security;
import com.rumpusradio.model.User;
import com.rumpusradio.model.UserManager;
import com.rumpusradio.model.UserRegistration;
import com.rumpusradio.util.ApplicationSecurityManager;

public class PasswordController extends SimpleFormController {
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private String message;
    protected ApplicationSecurityManager applicationSecurityManager;
    
    
    public PasswordController() {
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
    	UserManager userManager = new UserManager();
    	UserRegistration userRegistration = (UserRegistration) command;
    	User user = (User) applicationSecurityManager.getUser(request);
    	    	
    	//userManager.changePassword(user, userRegistration);
    	
    	//return showForm(request, response, errors);    
    	
    	user.setPassword( Security.MD5( userRegistration.getPassword1() ));
    	
    	if( userManager.saveUserInfo(user) ) {
    		message = "Password Saved";
    		return showForm(request, response, errors);    
    	} else {
    		message = "*Error Saving Password*";
    		return showForm(request, response, errors);    
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
