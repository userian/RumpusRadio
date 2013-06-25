package com.rumpusradio.test;

import com.rumpusradio.model.Artist;
import com.rumpusradio.util.HibernateUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestDatabaseArtist extends TestCase {

	Artist artist = new Artist();

    public static void main(String args[])
    {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite()
    {
        return new TestSuite(TestDatabaseArtist.class);
    }

	public void testNewArtists() {
		try {
			
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	        
			Artist artist = new Artist();
			
			artist.setUserId(12);
			artist.setArtistName("Rock Band");
			artist.setGenre1(1);
			artist.setGenre2(2);
			artist.setGenre3(3);
			
			HibernateUtil.getSessionFactory().getCurrentSession().save(artist);
			
			
			//Check Work
			long artistId = artist.getArtistId();
			
			
			Artist artist2 = (Artist) HibernateUtil.getSessionFactory().getCurrentSession().get(Artist.class, artistId);
			assertTrue(artist2.getUserId() == 12 );
			assertTrue(artist2.getArtistName() == "Rock Band");
			assertTrue(artist2.getGenre1() == 1 );
			assertTrue(artist2.getGenre2() == 2 );
			assertTrue(artist2.getGenre3() == 3 );
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			
		} catch (Exception ex) {
			System.out.println("Shit's Fucked:"+ex.toString());
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
		
	}
}


