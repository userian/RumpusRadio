package com.rumpusradio.controller;

import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RumpusRadioController implements Controller {
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
	    	throws ServletException, IOException {
	
		String now = (new java.util.Date()).toString(); 
        logger.info("returning hello view with " + now);

        return new ModelAndView("hello", "now", now);
	}
}
