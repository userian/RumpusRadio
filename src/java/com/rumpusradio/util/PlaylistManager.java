package com.rumpusradio.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.StatelessSession;
import com.rumpusradio.util.HibernateUtil;
import com.rumpusradio.model.Song;
import com.rumpusradio.model.Artist;
import com.rumpusradio.model.Playlist;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




public class PlaylistManager {

	protected final Log logger = LogFactory.getLog(getClass());
	


	public Song getCurrentSong() {



		/* this is really fucking ugly... */
		
		Playlist playlist;
		long songId;
		Song song;		

		StatelessSession statelessSession = HibernateUtil.getSessionFactory().openStatelessSession();
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	
				
		try {
			
			songId = (Integer) statelessSession.createSQLQuery("SELECT SongID FROM tblPlaylist ORDER BY PlaylistID DESC")
				.setFirstResult(0)
				.setMaxResults(1)
				.setCacheable( false )
				.uniqueResult();

		}
		catch (HibernateException e)
		{
		    return null;
		    //throw e;
		}
		finally {
			statelessSession.close();
		}
    
		try
		{
			//force refresh
			session.clear();
			//query
			song = ( Song ) session.createCriteria( Song.class )
				.add( Restrictions.eq("songId", songId) )
				.setFirstResult(0)
				.setMaxResults(1)
				.setCacheable( false )
				.uniqueResult();
				
				
		}
		catch (HibernateException e)
		{
			logger.info(" no results");
		    return null;
		}
		

		return song;
		
	}


	public Artist getCurrentArtist() {
		
		Artist artist = new Artist();
		
		return artist;
	}
	
	
	public String getCurrentArtistName() {
		
		String artist = null;

        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
                
        try
        {
        	artist = (String) session.createSQLQuery(
        			"SELECT ArtistName FROM tblArtist WHERE ArtistID = ( " +
					"	SELECT tblSong.ArtistID FROM tblPlaylist, tblSong " +
					"	WHERE tblPlaylist.SongID = tblSong.SongID" +
					"	ORDER BY tblPlaylist.PlaylistID DESC Limit 1 ) ")
        			.uniqueResult();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            return null;
            //throw e;
        }
        
        return artist;
		
	}
	/*
	public String getCurrentSongName() {
		
		Song song = null;
		Playlist playlist = null;
		
        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        
        try
        {
        
        	song = (Song) session.createSQLQuery(
        			" SELECT song.* FROM tblSong as song, tblPlaylist as playlist WHERE " +
        			" song.SongID = playlist.SongID " +
        			" ORDER BY playlist.DatePlayed DESC"	
        			).addEntity( Song.class )
        			.uniqueResult();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            return null;
            //throw e;
        }
        
        return song;
	}
	*/
}
 
