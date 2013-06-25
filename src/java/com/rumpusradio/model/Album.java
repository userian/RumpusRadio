package com.rumpusradio.model;

import java.util.*;

public class Album {

		private long albumId;
		private long artistId;
		private Date dateEntered;
		private	int	active;
		private String albumName;

		public Album () {}
		
		/* AlbumID */
		public long getAlbumId() {
			return this.albumId;
		}
		public void setAlbumId( long albumId ) {
			this.albumId = albumId;
		}
		
		/* ArtistID */
		public long getArtistId() {
			return this.artistId;
		}
		public void setArtistId( long artistId ) {
			this.artistId = artistId;
		}
		
		/* Album Name */
		public String getAlbumName() {
			return this.albumName;
		}
		public void setAlbumName( String albumName ) {
			this.albumName = albumName;
		}
		
		/* DateEntered */
		public Date getDateEntered() {
			return this.dateEntered;
		}
		public void setDateEntered( Date dateEntered ) {
			this.dateEntered = dateEntered;
		}
		
		/* Active */
		public int getActive() {
			return this.active;
		}
		public void setActive( int active ) {
			this.active = active;
		}
		
		
}
