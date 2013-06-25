package com.rumpusradio.test;

import com.rumpusradio.model.Genre;
import com.rumpusradio.util.HibernateUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestDatabaseGenre extends TestCase {
	
    public static void main(String args[])
    {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite()
    {
        return new TestSuite(TestDatabaseGenre.class);
    }

	public void testNewGenre() {
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	        
			Genre genre = new Genre();
			
			genre.setGenreName("Rock");
			
			HibernateUtil.getSessionFactory().getCurrentSession().save(genre);
			
			
			//Check Work
			long id = genre.getGenreId();
			
			
			Genre genre2 = (Genre) HibernateUtil.getSessionFactory().getCurrentSession().get(Genre.class, id);
			assertTrue(genre2.getGenreName() == "Rock" );
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			
		} catch (Exception ex) {
			System.out.println("Shit's Fucked:"+ex.toString());
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
	}
}