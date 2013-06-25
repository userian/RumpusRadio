package com.rumpusradio.model;

import java.util.*;
import com.rumpusradio.model.Song;

public class Playlist {

		private long playlistId;
		private long songId;
		private Date datePlayed;
		private Song song;

		public Song getSong() {
			return song;
		}


		public void setSong(Song song) {
			this.song = song;
		}


		public Date getDatePlayed() {
			return datePlayed;
		}


		public void setDatePlayed(Date datePlayed) {
			this.datePlayed = datePlayed;
		}


		public long getPlaylistId() {
			return playlistId;
		}


		public void setPlaylistId(long playlistId) {
			this.playlistId = playlistId;
		}


		public long getSongId() {
			return songId;
		}


		public void setSongId(long songId) {
			this.songId = songId;
		}


		public Playlist () {}
		
				
		
}
