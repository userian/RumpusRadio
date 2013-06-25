package com.rumpusradio.model;

import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import com.rumpusradio.util.HibernateUtil;
import com.rumpusradio.util.Security;
import com.rumpusradio.model.User;
import com.rumpusradio.model.Artist;
import com.rumpusradio.util.Mailer;

public class UserManager {
	
	
	public boolean findExistingUser( String email ) {
		User user = null;

        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        
        try
        {
        	user = (User) session.createQuery(
        			"from User" + " where Email = ?")
        			.setString( 0, email )
        			.uniqueResult();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            return false;
            //throw e;
        }
       
        if( user == null ) {
        	return false;
        }
        
        return true;
        
	}
	public User findUser(String email)
    {
        User user = null;

        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        
        try
        {
        	user = (User) session.createQuery(
        			"from User" + " where Email = ?")
        			.setString( 0, email )
        			.uniqueResult();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            return null;
            //throw e;
        }
        
        return user;
    }
	public User findUser(String email, String password)
    {
        User user = null;

        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        
        try
        {
        	user = (User) session.createQuery(
        			"from User" + " where Email = ? AND Password = ?")
        			.setString( 0, email )
        			.setString(1, Security.MD5( password ) )
        			.uniqueResult();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            return null;
            //throw e;
        }
        
        return user;
    }
	public Artist findArtist( long UserId ) {
		Artist artist = null;

        Session session = HibernateUtil.getSessionFactory()
                .getCurrentSession();
        session.beginTransaction();
        try
        {
        	artist = (Artist) session.createQuery(
        			"from Artist" + " where UserID = ?")
        			.setLong( 0, UserId )
        			.uniqueResult();
        }
        catch (HibernateException e)
        {
            session.getTransaction().rollback();
            throw e;
        }
        
        return artist;
	}
	public boolean changePassword( User user, UserRegistration userRegistration ) {
				
		Session session = HibernateUtil.getSessionFactory()
        .getCurrentSession();
		session.beginTransaction();
		
		try
		{
			user.setPassword( Security.MD5( userRegistration.getPassword1() ) );
		    session.saveOrUpdate(user);
		    session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw e;
		}
		
		return true;
	}
	
	public boolean saveUserInfo( User user ) {

		
		Session session = HibernateUtil.getSessionFactory()
        .getCurrentSession();
		session.beginTransaction();
		
		try
		{
			//user.setPassword( Security.MD5( user.getPassword() ) );
		    session.saveOrUpdate(user);
		    session.getTransaction().commit();
		}
		catch (HibernateException e)
		{
		    session.getTransaction().rollback();
		    throw e;
		}
		
		return true;
	}
	public boolean resetUserPassword( User user ) {
		
		//generate new random password
        StringBuffer newPassword= new StringBuffer("");
        Random rnd = new Random();
        final String alphabet =
                     "0123456789abcdefghijklmnopqrstuvwxyz";
        for (int i=0; i<10; i++) {
          newPassword.append(alphabet.charAt(
            rnd.nextInt(alphabet.length())));
        }
        // add two digits to be sure to pass password policy
        newPassword.append(alphabet.charAt(rnd.nextInt(10)));
        newPassword.append(alphabet.charAt(rnd.nextInt(10)));
        
        user.setPassword( Security.MD5( newPassword.toString() ));
        this.saveUserInfo(user);
        Mailer mailer = new Mailer();
        mailer.sendPasswordResetEmail( user, newPassword.toString() );
		
		return true;
	}
}
