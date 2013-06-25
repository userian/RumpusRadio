package com.rumpusradio.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.rumpusradio.model.User;
import com.rumpusradio.model.UserManager;
import com.rumpusradio.model.UploadAgreement;

import com.rumpusradio.util.ApplicationSecurityManager;

public class UploadAgreementController extends SimpleFormController {
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private String message;
    protected ApplicationSecurityManager applicationSecurityManager;
    
    
    public UploadAgreementController() {
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
    	
    	User user = (User) applicationSecurityManager.getUser(request);
    	UserManager userManager = new UserManager();
    	
    	UploadAgreement uploadAgreement = (UploadAgreement)command;
    	
    	if( uploadAgreement.getAgreementCheckbox() == 1 ) {
    		user.setUploadAgreement( 1 );
    		userManager.saveUserInfo( user );
    		response.sendRedirect("music.html");
    	}
    	
    	return showForm(request, response, errors);    
    }
    protected Map referenceData(HttpServletRequest request,
            Object command, 
            Errors errors ) throws Exception {
    	HashMap<String, Object> model = new HashMap<String, Object>();
    	
    	Map<Object, Object> checkboxes = new LinkedHashMap<Object, Object>();
    	checkboxes.put("1", "I agree to the Terms and Conditions");
    	model.put("checkboxes", checkboxes );
    
		return model;
	
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
