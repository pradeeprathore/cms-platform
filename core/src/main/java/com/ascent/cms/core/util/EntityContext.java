package com.ascent.cms.core.util;


import org.hibernate.Session;
import org.springframework.mail.javamail.JavaMailSender;

import com.ascent.cms.core.EmailSender;

public interface EntityContext {

	public Session getSession();

	public BeanValidator getBeanValidator();

	public void setMailSender(JavaMailSender emailSender);
	
	public JavaMailSender getMailSender();
	
	
public void setEmailSender(EmailSender emailSender);
	
	public EmailSender getEmailSender();

	
}
