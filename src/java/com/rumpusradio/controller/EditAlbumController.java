package com.rumpusradio.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.rumpusradio.model.Album;
import com.rumpusradio.model.AlbumManager;
import com.rumpusradio.model.MusicManager;
import com.rumpusradio.model.Artist;
import com.rumpusradio.model.Song;
import com.rumpusradio.util.ApplicationSecurityManager;
import org.springframework.validation.Errors;


public class EditAlbumController extends SimpleFormController {
	
	protected final Log logger = LogFactory.getLog(getClass());
    private Album album;
    private AlbumManager albumManager;
    private MusicManager musicManager;
    protected ApplicationSecurityManager applicationSecurityManager;
    private String message;
    
    public EditAlbumController() {
    	message = "";
    }
    
    public ModelAndView showForm(
            HttpServletRequest request,
            HttpServletResponse response,
            BindException errors,
            Map controlModel) throws Exception
    {
    	Map<String, Object> model = new HashMap<String, Object>();
    	if( controlModel != null ) {
    		model.putAll((HashMap<String, Object>)controlModel);
    	}
    	
    	model.put("message", message);
    	
    	Artist artist = (Artist) applicationSecurityManager.getArtist(request);
    	AlbumManager albumManager = new AlbumManager();
    	Album album;
    	boolean validRequest = false;
    	    	   	
    	if( request.getParameterMap().containsKey("a") ) {

    		album = musicManager.getAlbum( Long.parseLong(request.getParameter("a")) );
    		
    		if( album.getArtistId() == artist.getArtistId() ) {
    			validRequest = true;
    			
    			//Check if user wants to delete
    			if( request.getParameterMap().containsKey("d") ) {
            		
        			if( Integer.parseInt( request.getParameter("d") ) == 1 ) {
        				
        				albumManager.deleteAlbum( album );
        				
        				response.sendRedirect("music.html");
        				 
        			}
            	}
    		}
    	}
    	
    	if( validRequest ) {
    		return super.showForm(request, response, errors, model);
    	} else {
    		return new ModelAndView("error");
    	}
    }
    
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception
    {
    	Artist artist = (Artist) applicationSecurityManager.getArtist(request);
    	album = (Album) command;
    	album.setArtistId( artist.getArtistId() );
    	
    	// UPDATE
    	if( albumManager.saveAlbumInfo( album ) ) {
    		message = "Album Saved";
    		return showForm(request, response, errors);    
    		
    	} else {
    		return new ModelAndView("error");
    	}
    }
    protected Map referenceData(HttpServletRequest request,
            Object command, 
            Errors errors ) throws Exception {
    	
    	HashMap<String, Object> model = new HashMap<String, Object>();
    	musicManager = new MusicManager();
    	Long albumId;
    	ArrayList<Song> songList = null;
    	
    	if( request.getParameterMap().containsKey("a") ) {
    		albumId = Long.parseLong( request.getParameter("a") );
    		logger.info("Request a:" + request.getParameter("a"));
        	album = musicManager.getAlbum( albumId );
        	songList = musicManager.getAlbumSongs(albumId);
        	model.put("command", album);
    		model.put("songs", songList);
    	}
    
		return model;
	
	}
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	
    	Album album = new Album();
    	Long albumId;
    	boolean validAlbum = false;
    	
    	Artist artist = (Artist) applicationSecurityManager.getArtist(request);
    	
    	if( request.getParameterMap().containsKey("a") ) {
    		
    		logger.info("Request a:" + request.getParameter("a"));
    	
    		albumId = Long.parseLong( request.getParameter("a") );
    	
    		album = musicManager.getAlbum( albumId );
    		
    		if( album.getArtistId() == artist.getArtistId() ) {
    			validAlbum = true;
    		}
    		
    	} 
    	if( ! validAlbum ) {
    		album = new Album();
    	}
    	        
        return album;
        
    }
    public ApplicationSecurityManager getApplicationSecurityManager()
    {
        return applicationSecurityManager;
    }
    public void setApplicationSecurityManager(
            ApplicationSecurityManager applicationSecurityManager)
    {
        this.applicationSecurityManager = applicationSecurityManager;
    }
    public MusicManager getMusicManager() {
    	return musicManager;
    }
    public void setMusicManager( MusicManager musicManager ) {
    	this.musicManager = musicManager;
    }
    public AlbumManager getAlbumManager() {
    	return albumManager;
    }
    public void setAlbumManager( AlbumManager albumManager ) {
    	this.albumManager = albumManager;
    }
}
