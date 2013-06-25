package com.rumpusradio.test;

import com.rumpusradio.model.Album;
import com.rumpusradio.util.HibernateUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;

public class TestDatabaseAlbum extends TestCase {

    public static void main(String args[])
    {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite()
    {
        return new TestSuite(TestDatabaseAlbum.class);
    }

	public void testNewAlbum() {
		try {
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	        
			Album album = new Album();

			album.setArtistId(12);
			album.setDateEntered( new Date() );
			album.setActive(1);
			album.setAlbumName("The Album");
			
			HibernateUtil.getSessionFactory().getCurrentSession().save(album);
			
			//Check Work
			long id = album.getAlbumId();
			
			Album check = (Album) HibernateUtil.getSessionFactory().getCurrentSession().get(Album.class, id);
			
			assertTrue(check.getArtistId()== 12 );
			assertTrue(check.getAlbumName()== "The Album" );
			assertTrue(check.getActive() == 1 );
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			
		} catch (Exception ex) {
			System.out.println("Shit's Fucked:"+ex.toString());
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
		
	}
}


