package com.rumpusradio.util;

import javax.servlet.http.HttpServletRequest;
import com.rumpusradio.model.User;
import com.rumpusradio.util.ApplicationSecurityManager;

public class Logger {
	
	private ApplicationSecurityManager applicationSecurityManager;
	
	public Logger(){
		applicationSecurityManager = new ApplicationSecurityManager();
	}
	public void addError( HttpServletRequest request ) {
		User user = (User) applicationSecurityManager.getUser(request);
		System.out.println( user.toString() );
	}
	
}
