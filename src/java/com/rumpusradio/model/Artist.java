package com.rumpusradio.model;

import java.util.*;
import com.rumpusradio.model.ArtistInfo;
public class Artist {
	
	private ArtistInfo artistInfo;
	private long artistId;
	private long userId;
	private String artistName;
	private int genre1;
	private int genre2;
	private int genre3;
	private Date dateEntered;
	private Map albums;
	private Set songs;
	

	public Artist() {}
	
	/* ArtistID */
	public long getArtistId() {
		return this.artistId;
	}
	public void setArtistId( long artistId ) {
		this.artistId = artistId;
	}
	
	/* UserID */
	public long getUserId() {
		return this.userId;
	}
	public void setUserId( long userId ) {
		this.userId = userId;
	}
	
	public Date getDateEntered() {
		return dateEntered;
	}

	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	/* ArtistName */
	public String getArtistName() {
		return this.artistName;
	}
	public void setArtistName( String artistName ) {
		this.artistName = artistName;
	}
	
	/* Genre1 */
	public int getGenre1() {
		return this.genre1;
	}
	public void setGenre1( int genre ) {
		this.genre1 = genre;
	}
	
	/* Genre2 */
	public int getGenre2() {
		return this.genre2;
	}
	public void setGenre2( int genre ) {
		this.genre2 = genre;
	}
	
	/* Genre1 */
	public int getGenre3() {
		return this.genre3;
	}
	public void setGenre3( int genre ) {
		this.genre3 = genre;
	}

	public ArtistInfo getArtistInfo() {
		return this.artistInfo;
	}
	public void setArtistInfo( ArtistInfo artistInfo ) {
		this.artistInfo = artistInfo;
	}

	/* Albums */
	public Map getAlbums() {
		return albums;
	}

	public void setAlbums(Map albums) {
		this.albums = albums;
	}
	
	/* Songs */
	public Set getSongs() {
		return songs;
	}

	public void setSongs(Set songs) {
		this.songs = songs;
	}
}
