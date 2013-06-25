package com.rumpusradio.test;

import com.rumpusradio.model.User;
import com.rumpusradio.util.HibernateUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestDatabaseUser extends TestCase {

	
	public static void main(String args[])
    {
        junit.textui.TestRunner.run(suite());
    }

    public static Test suite()
    {
        return new TestSuite(TestDatabaseUser.class);
    }

	public void testNewUser() {
		
		try {
			HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
			
			User user = new User();
			user.setFirstName("John");
			user.setLastName("Smith");
			user.setEmail("john@smith.com");
			user.setPassword("passwordhash");
			user.setUserName("john.smith");
			
			HibernateUtil.getSessionFactory().getCurrentSession().save(user);
			
//			Check Work
			long id = user.getUserId();
			
			
			User user2 = (User) HibernateUtil.getSessionFactory().getCurrentSession().get(User.class, id);
			assertTrue( user2.getFirstName() == "John" );
			assertTrue( user2.getLastName() == "Smith" );
			assertTrue( user2.getEmail() == "john@smith.com" );
			assertTrue( user2.getPassword() == "passwordhash" );
			assertTrue( user2.getUserName() == "john.smith" );
			
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().commit();
			
		} catch (Exception ex) {
			System.out.println("Shit's Fucked:"+ex.toString());
            HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
		}
		
	}
	
}
