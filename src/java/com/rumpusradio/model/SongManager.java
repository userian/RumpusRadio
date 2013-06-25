package com.rumpusradio.model;

import java.io.File;
import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rumpusradio.model.Song;
import com.rumpusradio.util.GlobalVariables;
import com.rumpusradio.util.HibernateUtil;

public class SongManager {
public ArrayList getArtistSongsNoAlbums( long artistId ) {
		
		ArrayList songList = new ArrayList();
		
        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        try
        {
        	songList = (ArrayList)session.createQuery("from Song " + "where artistId = ? and AlbumID = '0' ORDER BY dateEntered")
            	.setLong( 0, artistId)
            	.list();
            
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw e;
        }
        
		return songList;
	}
	public boolean saveSongInfo( Song song) {
		
		Session session = HibernateUtil.getSessionFactory()
        .getCurrentSession();
		session.beginTransaction();
		
		try
		{
		    session.saveOrUpdate(song);
		    session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw e;
		}
		
		return true;
		
	}
	
	public Song getSong( long songId ) {

		Song song;
        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        try
        {
        	song = (Song)session.createQuery("from Song " + "where songId = ?")
            	.setLong( 0, songId)
            	.uniqueResult();
            
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw e;
        }
        
		return song;
	}
	
	public void deleteSong( Song song ) {
		String uploadDir;
		GlobalVariables globalVariables = new GlobalVariables();
		String sep = System.getProperty("file.separator");
		
		uploadDir = globalVariables.getMusicDirectory()+sep+song.getArtistId()+sep+song.getAlbumId();
		
		
		File songFile = new File(uploadDir + sep + song.getFileName());
		
		// delete song file 
		songFile.delete();
		
		//then delete database entry
		Session session = HibernateUtil.getSessionFactory()
        	.getCurrentSession();
		session.beginTransaction();
		try
		{
		    session.delete( song );
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw e;
		}
		
	}
}
