/*

package com.rumpusradio.test;

import com.rumpusradio.model.ArtistInfo;
import com.rumpusradio.util.HibernateUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestDatabaseArtistInfo extends TestCase {

	public static void main(String args[])
    {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite()
    {
        return new TestSuite(TestDatabaseArtistInfo.class);
    }

	public void testNewArtistInfo() {
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
	        
			ArtistInfo artistInfo = new ArtistInfo();
			
			artistInfo.setDescription("This band rocks the fucking house");
			artistInfo.setCity("New York");
			artistInfo.setState(1);
			
			HibernateUtil.getSessionFactory().getCurrentSession().save(artistInfo);
			
			
			//Check Work
			long id = artistInfo.getInfoId();
			
			
			ArtistInfo info2 = (ArtistInfo) HibernateUtil.getSessionFactory().getCurrentSession().get(ArtistInfo.class, id);
			assertTrue(info2.getDescription() == "This band rocks the fucking house" );
			assertTrue( info2.getCity() == "New York" );
			assertTrue( info2.getState() == 1 );
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			
		} catch (Exception ex) {
			System.out.println("Shit's Fucked:"+ex.toString());
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
		
	}
		
}
*/