package com.rumpusradio.test;

import com.rumpusradio.model.Song;
import com.rumpusradio.util.HibernateUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestDatabaseSong extends TestCase {

	
	public static void main(String args[])
    {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite()
    {
        return new TestSuite(TestDatabaseSong.class);
    }

	public void testNewSong() {
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			Song song = new Song();
			
			song.setAlbumId(1);
			song.setArtistId(12);
			song.setFileName("apple.mp3");
			song.setSongName("Apple Song");
			song.setTrackNumber(1);
			
			
			HibernateUtil.getSessionFactory().getCurrentSession().save(song);
			
//			Check Work
			long id = song.getSongId();
			
			
			Song song2 = (Song) HibernateUtil.getSessionFactory().getCurrentSession().get(Song.class, id);
			assertTrue( song2.getAlbumId() == 1 );
			assertTrue( song2.getArtistId() == 12 );
			assertTrue( song2.getFileName() == "apple.mp3");
			assertTrue( song2.getSongName() == "Apple Song" );
			assertTrue( song2.getTrackNumber() == 1 );
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			
		} catch (Exception ex) {
			System.out.println("Shit's Fucked:"+ex.toString());
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
		
	}
}
