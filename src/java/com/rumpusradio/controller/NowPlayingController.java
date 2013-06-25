package com.rumpusradio.controller;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.rumpusradio.util.PlaylistManager;
import com.rumpusradio.model.Song;
import com.rumpusradio.model.Artist;

public class NowPlayingController implements Controller {
	
	PlaylistManager playlistManager;
	
    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
    	
    	Map<String, Object> model = new HashMap<String, Object>();
    	
    	Artist artist;
        Song song = playlistManager.getCurrentSong();
    	
        if( song == null ) { 
        
        	return new ModelAndView("nowplayingoffline", "model", model );
        	
        }
        
        
        artist = song.getArtist();
        	
		model.put("artist", artist);
		model.put("song", song);
	
		return new ModelAndView("nowplaying", "model", model);
    	
	}

	public PlaylistManager getPlaylistManager() {
		return playlistManager;
	}

	public void setPlaylistManager(PlaylistManager playlistManager) {
		this.playlistManager = playlistManager;
	}

}
