package com.rumpusradio.controller;

import java.util.*;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;


import com.rumpusradio.model.AlbumManager;
import com.rumpusradio.model.Artist;
import com.rumpusradio.model.Song;
import com.rumpusradio.model.SongManager;
import com.rumpusradio.model.MusicManager;
import com.rumpusradio.util.ApplicationSecurityManager;
import com.rumpusradio.util.GlobalVariables;

import org.springframework.validation.Errors;


public class EditSongController extends SimpleFormController {


	protected final Log logger = LogFactory.getLog(getClass());
    private Song song;
    private SongManager songManager;
    private MusicManager musicManager;
    protected ApplicationSecurityManager applicationSecurityManager;
    private String message;
    
    public EditSongController() {
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
    	Song song;
    	
    	
    	if( request.getParameterMap().containsKey("s") ) {
    		
    		song = songManager.getSong( Long.parseLong(request.getParameter("s") ));
    		
    		if( song.getArtistId() == artist.getArtistId() ) {

    			
    			//Check if user wants to delete
    			if( request.getParameterMap().containsKey("d") ) {
            		
        			if( Integer.parseInt( request.getParameter("d") ) == 1 ) {
        				
        				songManager.deleteSong( song );
        				
        				response.sendRedirect("music.html");
        				 
        			}
            	}
    			
    			return super.showForm(request, response, errors, model);
        		
    		} 
    	}
    	
   		return new ModelAndView("error");

    }
    
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception
    {
    	boolean success = true;
    	GlobalVariables globalVariables = new GlobalVariables();
    	String fileName;
    	String uploadDir;
    	String filename = (UUID.randomUUID()).toString()+".mp3";

    	Artist artist = (Artist)applicationSecurityManager.getArtist( request );
    	
    	
    	song = (Song) command;
    	fileName = song.getFileName();
    	song.setArtistId(artist.getArtistId());
    	song.setFileName( filename );
    	
    	
    	
    	uploadDir = globalVariables.getMusicDirectory()+"/"+song.getArtistId()+"/"+song.getAlbumId();    	
    	
    	MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;        
        CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile("file");

        logger.info("Upload File Size:" + multipartFile.getSize());
               
        //Create the directory if it doesn't exist
        File dirPath = new File(uploadDir);
        
        if (!dirPath.exists()) {
            dirPath.mkdirs();
        }
        
        String sep = System.getProperty("file.separator");
        
        //overwrite old file
        
    	logger.info("uploading to: " + uploadDir + sep + fileName);
        
        File uploadedFile = new File(uploadDir + sep + fileName);
        
        //FileCopyUtils.copy(uploadfile, uploadedFile);
        multipartFile.transferTo( uploadedFile );
        
        //song needs to get rechecked
        song.setStatus(0);
        
        songManager.saveSongInfo( song );
        
    	success = true;
    	
        if( success ) {
        	message = "Song Saved";
        	return showForm(request, response, errors);
        } else { 
        	return showForm(request, response, errors);
        }
    }
    
    protected Map referenceData(HttpServletRequest request,
            Object command, 
            Errors errors ) throws Exception {
    	HashMap<String, Object> model = new HashMap<String, Object>();
    	
    	Artist artist = (Artist)applicationSecurityManager.getArtist( request );
    	
    	AlbumManager albumManager = new AlbumManager();
    	
    	model.put("albums", albumManager.getArtistAlbums( artist.getArtistId() ) );
    
		return model;
	
	}
	
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
    	
    	Artist artist = (Artist)applicationSecurityManager.getArtist( request );
    	Song song = new Song();
    	Long songId;
    	
    	if( request.getParameter("s") != null ) {
    		songId = Long.parseLong( request.getParameter("s") );
    		song = songManager.getSong( songId );
    	}

    	//make sure they own the song
    	if( song.getArtistId() == artist.getArtistId() ) {
            //return song;
    		return song;
    	}
    	
    	return new Song();
    }
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
	    throws ServletException {
	    // to actually be able to convert Multipart instance to byte[]
	    // we have to register a custom editor
	    binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	    // now Spring knows how to handle multipart object and convert them
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
    public SongManager getSongManager() {
    	return songManager;
    }
    public void setSongManager( SongManager albumManager ) {
    	this.songManager = albumManager;
    }
}
