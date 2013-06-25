package com.rumpusradio.model;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.rumpusradio.util.HibernateUtil;
import java.util.*;

public class GenreManager {
	
	public Map<Long, String> getAllGenres() {
		
		Map<Long, String> genreMap = new HashMap<Long, String>();
		Iterator<Genre> iter;

        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        try
        {

            iter = session.createQuery("from Genre " + "ORDER BY genreName").iterate();
            
            while( iter.hasNext() ) {
            	Genre genre = (Genre)iter.next();
            	genreMap.put( genre.getGenreId() , genre.getGenreName() );
            }

            session.getTransaction().commit();
            
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw e;
        }
       
        return genreMap;
		
	}
}
