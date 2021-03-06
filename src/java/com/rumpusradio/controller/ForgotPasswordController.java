package com.rumpusradio.controller;


import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.rumpusradio.model.UserManager;
import com.rumpusradio.model.UserRegistration;
import com.rumpusradio.util.ApplicationSecurityManager;


public class ForgotPasswordController extends SimpleFormController {
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private UserManager userManager;
    protected ApplicationSecurityManager applicationSecurityManager;
    private String message;
    
    public ForgotPasswordController() {
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
        return super.showForm(request, response, errors, model);
    }
    
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception
    {
    	UserRegistration userRegistration = (UserRegistration)command;
    	userManager = new UserManager();
    	logger.debug("Found User:"+userManager.findUser(userRegistration.getEmail()).getFirstName());
    	if( userManager.resetUserPassword( userManager.findUser(userRegistration.getEmail())) ) {
    		return new ModelAndView(getSuccessView());
    	} else {
    		return showForm(request, response, errors);
    	}
    }
}
