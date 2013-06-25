package com.rumpusradio.model;

import java.util.Date;

public class ArtistDetailsManager {

	private long artistId;
	private long userId;
	private String artistName;
	private int genre1;
	private int genre2;
	private int genre3;
	private Date dateEntered;
	protected long infoId;
	protected String city;
	protected int state;
	protected String description;
	
	
	public long getArtistId() {
		return artistId;
	}
	public void setArtistId(long artistId) {
		this.artistId = artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getDateEntered() {
		return dateEntered;
	}
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getGenre1() {
		return genre1;
	}
	public void setGenre1(int genre1) {
		this.genre1 = genre1;
	}
	public int getGenre2() {
		return genre2;
	}
	public void setGenre2(int genre2) {
		this.genre2 = genre2;
	}
	public int getGenre3() {
		return genre3;
	}
	public void setGenre3(int genre3) {
		this.genre3 = genre3;
	}
	public long getInfoId() {
		return infoId;
	}
	public void setInfoId(long infoId) {
		this.infoId = infoId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
