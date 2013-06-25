package com.rumpusradio.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.rumpusradio.model.Artist;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class HibernateTest extends TestCase {
	
	public static void main(String args[])
    {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite()
    {
        return new TestSuite(HibernateTest.class);
    }

	public void testHibernate() {
		
        SessionFactory sessionFactory = new Configuration().configure()
                .buildSessionFactory();
        Session session = sessionFactory.openSession(); // changed to
        // openSession
        Transaction tx = session.beginTransaction();
        Artist artist;

        // Demo 1: Get single record
        artist = (Artist) session.get(Artist.class, "Rock Band");
        System.out.println("Name for Band = " + artist.getArtistName());

        // Demo 2: Get all records
        /*
        List departmentList = session.createQuery("from tblArtist").list();
        for (int i = 0; i < departmentList.size(); i++)
        {
            artist = (Artist) departmentList.get(i);
            System.out.println("Row " + (i + 1) + "> " + artist.getArtistName()
                    + " (" + artist.getArtistId() + ")");
        }
	*/
        tx.commit();
        session.close();
        sessionFactory.close();
    }
}
