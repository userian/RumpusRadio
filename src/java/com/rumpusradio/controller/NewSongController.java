package com.rumpusradio.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

//import be.telio.mediastore.ui.upload.MonitoredDiskFileItemFactory;
//import be.telio.mediastore.ui.upload.UploadListener;

import com.rumpusradio.model.Artist;
import com.rumpusradio.model.MusicManager;
import com.rumpusradio.model.AlbumManager;
import com.rumpusradio.model.Song;
import com.rumpusradio.model.SongManager;
import com.rumpusradio.util.ApplicationSecurityManager;
import com.rumpusradio.util.GlobalVariables;
import com.hillert.upload.web.ajax.AjaxFileUploadMultipartResolver;
import com.hillert.upload.service.UploadService;


public class NewSongController extends SimpleFormController {


	protected final Log logger = LogFactory.getLog(getClass());
	private String message;
	private boolean success;
    private Song song;
    private SongManager songManager;
    private MusicManager musicManager;
    protected ApplicationSecurityManager applicationSecurityManager;
    protected AjaxFileUploadMultipartResolver multipartResolver;
    protected UploadService service;
    
    
    public NewSongController() {
    	message = "";
    	success = false;
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
    	model.put("success", success);
    	message = "";
    	success = false;
        return super.showForm(request, response, errors, model);
    }
    
    public ModelAndView onSubmit(
            HttpServletRequest request,
            HttpServletResponse response,
            Object command,
            BindException errors) throws Exception
    {
    	GlobalVariables globalVariables = new GlobalVariables();
    	
    	
    	Artist artist = (Artist)applicationSecurityManager.getArtist( request );
    	String uploadDir;
    	String filename = (UUID.randomUUID()).toString()+".mp3";
    	
    	song = (Song) command;
    	logger.info("Song Name:"+song.getSongName());
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
        
    	logger.info("uploading to: " + uploadDir + sep + filename);
        
        File uploadedFile = new File(uploadDir + sep + filename);
        
        //FileCopyUtils.copy(uploadfile, uploadedFile);
        multipartFile.transferTo( uploadedFile );
        
        songManager.saveSongInfo( song );
        
    	success = true;
    	
        if( success ) {
        	response.sendRedirect("music.html");
        }
        
        return showForm(request, response, errors);
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
    	
    	Song song = new Song();
    	Map getParameters = request.getParameterMap(); 
    	
    	if( getParameters.containsKey("a") )
    	{
	    	if( request.getParameter("a").length() != 0 ) {
	    		song.setAlbumId( Long.parseLong( request.getParameter("a")) );
	    	}
    	}
    	return song;
    	
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

	public AjaxFileUploadMultipartResolver getMultipartResolver() {
		return multipartResolver;
	}

	public void setMultipartResolver(
			AjaxFileUploadMultipartResolver multipartResolver) {
		this.multipartResolver = multipartResolver;
	}
	
	public final void setService(final UploadService service) {
        this.service = service;
    }

}
