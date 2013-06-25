package com.rumpusradio.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.Writer;

import com.rumpusradio.util.PlaylistManager;
import com.rumpusradio.model.Song;

public class PlaylistXMLController implements Controller {
	
	PlaylistManager playlistManager;
	
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
    	
    	response.setContentType("text/xml");
    	
    	Song song;
    	song = playlistManager.getCurrentSong();
    	//Playlist playlist = playlistManager.getCurrentSong(); 
    	
    	
    	Writer writer = response.getWriter();
    	writer.write("<Song>");
    	writer.write("<SongID>" + song.getSongId() + "</SongID>");
    	writer.write("<Artist>" + song.getArtist().getArtistName() + "</Artist>");
    	writer.write("<Title>" +  song.getSongName() + "</Title>");
    	writer.write("</Song>");
    	writer.close();
    	
    	return null;
	}

	public PlaylistManager getPlaylistManager() {
		return playlistManager;
	}

	public void setPlaylistManager(PlaylistManager playlistManager) {
		this.playlistManager = playlistManager;
	}

}
