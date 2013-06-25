package com.rumpusradio.model;

import com.rumpusradio.util.HibernateUtil;
import com.rumpusradio.util.Security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;


public class UserRegistration {
	private String firstName;
	private String lastName;
	private String email;
	private String password1;
	private String password2;
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword1() {
		return password1;
	}
	public void setPassword1(String password1) {
		this.password1 = password1;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	

	public boolean addNewUser( ) {
		
		Session session = HibernateUtil.getSessionFactory()
        	.getCurrentSession();
		session.beginTransaction();

		try {
			
			User user = new User();
			user.setFirstName(this.firstName);
			user.setLastName(this.lastName);
			user.setEmail(this.email);
			user.setPassword(Security.MD5( this.password1 ) );
			session.saveOrUpdate(user);
			session.getTransaction().commit();
			
		} catch (Exception ex) {
			logger.error("Regestration Error:"+ex.toString() );
	        session.getTransaction().rollback();
	        return false;
		}
		
		return true;
	}
}
