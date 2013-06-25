package com.rumpusradio.util;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import com.rumpusradio.model.User;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Mailer {
	
	private MailSender mailSender;
    private SimpleMailMessage templateMessage;
    
    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }
    public MailSender getmailSender() {
    	return mailSender;
    }
    
    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }
    public SimpleMailMessage getTemplateMessage() {
    	return templateMessage;
    }

	public boolean sendPasswordResetEmail( User user, String newPassword ) {
		
		this.mailSender = new JavaMailSenderImpl();
		
        // Do the business calculations...

        // Call the collaborators to persist the order...

        // Create a thread safe "copy" of the template message and customize it
        //SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        SimpleMailMessage msg = new SimpleMailMessage();
        
        
        msg.setFrom("info@rumpusradio.com");
        msg.setSubject("[Rumpus Radio] Pasword Reset");
        msg.setTo(user.getEmail());
        msg.setText(
            "Hello " + user.getFirstName() + " " + user.getLastName()
                + ", your password has been reset to "
                + newPassword);
        try{
            this.mailSender.send(msg);
        }
        catch(MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());            
        }

		return true;
	}
}
