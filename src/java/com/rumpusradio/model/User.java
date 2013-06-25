package com.rumpusradio.model;

import java.util.Date;
import java.util.*;
import com.rumpusradio.model.Artist;

public class User {
	private long userId;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private Date dateEntered;
	private int userLevel;
	private int active;
	private Set artists;
	private Artist artist;
	private int uploadAgreement;
	
	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public User() {}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	public int getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public Set getArtists() {
		return artists;
	}

	public void setArtists(Set artists) {
		this.artists = artists;
	}

	public int getUploadAgreement() {
		return uploadAgreement;
	}

	public void setUploadAgreement(int uploadAgreement) {
		this.uploadAgreement = uploadAgreement;
	}

}
