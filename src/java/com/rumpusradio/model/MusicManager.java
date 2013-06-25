package com.rumpusradio.model;

import java.util.*;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.rumpusradio.util.HibernateUtil;

public class MusicManager {
	
	public ArrayList getArtistAlbums( long artistId ) {
		ArrayList albumList = new ArrayList();

        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        try
        {
        	albumList = (ArrayList)session.createQuery("from Album " + "where artistId = ? ORDER BY dateEntered")
            	.setLong( 0, artistId)
            	.list();
            
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw e;
        }
        
		return albumList;
	}
	
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
	public Album getAlbum( long albumId ) {
		
		Album album; 
        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        try
        {
        	album = (Album)session.createQuery("from Album " + "where albumId = ?")
            	.setLong( 0, albumId)
            	.uniqueResult();
            
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw e;
        }
        
		return album;
	}
	public boolean checkAlbumOwner( long artistId, long albumId ) {
		
		return true;
	}
	public ArrayList<Song> getAlbumSongs( long albumId ) {
		
		ArrayList<Song> songList = new ArrayList<Song>();
		
        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        try
        {
        	songList = (ArrayList<Song>)session.createQuery("from Song " + "where albumId = ? ORDER BY trackNumber ASC")
            	.setLong( 0, albumId)
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
}
