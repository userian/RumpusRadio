package com.rumpusradio.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.rumpusradio.util.HibernateUtil;
import com.rumpusradio.model.Artist;
import com.rumpusradio.model.ArtistInfo;
import com.rumpusradio.model.User;

public class ArtistManager {

	public boolean newArtist( User user ) {
		
		Artist artist = new Artist();
		artist.setUserId( user.getUserId() );
		ArtistInfo artistInfo = new ArtistInfo();
		artist.setArtistInfo( artistInfo );
		artistInfo.setArtist( artist );
		user.setArtist( artist );
		this.saveArtistDetails( artist );
				
		return true;
	}
	public boolean saveArtistDetails( Artist artist ) {
		
		Session session = HibernateUtil.getSessionFactory()
        .getCurrentSession();
		session.beginTransaction();
		
		try
		{
			ArtistInfo artistInfo = artist.getArtistInfo();
		    session.saveOrUpdate(artist);
		    session.saveOrUpdate(artistInfo);
		    session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw e;
		}
		
		return true;
		
	}

}
