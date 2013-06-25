package com.rumpusradio.controller;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.rumpusradio.model.GenreManager;

public class BandInfoController extends SimpleFormController {
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private GenreManager genreManager;
    
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
    	
    	// UPDATE
    	if( true ) {
    		return new ModelAndView(getSuccessView());
    	} else {
    		return new ModelAndView("error");
    	}
    }
    
    protected Map referenceData(HttpServletRequest request) throws Exception
    {
        HashMap<String, Object> model = new HashMap<String, Object>();
        model.put("genres", genreManager.getAllGenres());
        return model;
    }
    public GenreManager getGenreManager() {
    	return genreManager;
    }
    public void setGenre( GenreManager genreManager ) {
    	this.genreManager = genreManager;
    }
}
