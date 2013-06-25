package com.rumpusradio.util;

public class Status {

	private boolean authenticated;
	private String lastActionResult;
	
	public boolean isAuthenticated() {
		return authenticated;
	}
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}
	public String getLastActionResult() {
		return lastActionResult;
	}
	public void setLastActionResult(String lastActionResult) {
		this.lastActionResult = lastActionResult;
	}
	
	
}
