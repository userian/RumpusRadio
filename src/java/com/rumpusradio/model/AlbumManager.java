package com.rumpusradio.model;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.rumpusradio.model.Album;
import com.rumpusradio.util.HibernateUtil;

public class AlbumManager {

	public boolean saveAlbumInfo( Album album ) {
		
		Session session = HibernateUtil.getSessionFactory()
        	.getCurrentSession();
		session.beginTransaction();
		
		try
		{
		    session.saveOrUpdate(album);
		    session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw e;
		}
		
		return true;
		
	}
	public Map<Long, String> getArtistAlbums( long artistId ) {
		Map<Long, String>  albumMap = new HashMap<Long, String> ();
		Iterator iter;
		
        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        try
        {
        	iter = session.createQuery("from Album " + "where artistId = ? ORDER BY dateEntered")
            	.setLong( 0, artistId)
            	.iterate();
            
        	while( iter.hasNext() ) {
            	Album album = (Album)iter.next();
            	albumMap.put( album.getAlbumId() , album.getAlbumName() );
            }
        	
            session.getTransaction().commit();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw e;
        }
        
		return albumMap;
	}
	
	public void deleteAlbum( Album album ) {
		
		Iterator iter;
		SongManager songManager = new SongManager();
		
		Session session = HibernateUtil.getSessionFactory()
        .getCurrentSession();
		session.beginTransaction();
		try
		{
			iter = session.createQuery("from Song " + "where albumId = ? ")
		    	.setLong( 0, album.getAlbumId() )
		    	.iterate();
		    
			while( iter.hasNext() ) {
				
		    	Song song = (Song)iter.next();
		    	songManager.deleteSong(song);
		    	
		    }
			
			session.delete( album );
			
		    session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw e;
		}

	}
	
}
