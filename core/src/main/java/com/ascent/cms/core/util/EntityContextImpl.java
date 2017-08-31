package com.ascent.cms.core.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.mail.javamail.JavaMailSender;

import com.ascent.cms.core.EmailSender;

public class EntityContextImpl implements EntityContext {

	private BeanValidator beanValidator;

	private SessionFactory sessionFactory;

	private EmailSender email;
	
	private JavaMailSender mail;
	
	

	public EntityContextImpl(BeanValidator beanValidator, SessionFactory sessionFactory) {
		super();
		this.beanValidator = beanValidator;
		this.sessionFactory = sessionFactory;
		this.email = email;
		this.mail = mail;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public EmailSender getEmail() {
		return email;
	}

	public void setEmail(EmailSender email) {
		this.email = email;
	}

	public JavaMailSender getMail() {
		return mail;
	}

	public void setMail(JavaMailSender mail) {
		this.mail = mail;
	}

	public void setBeanValidator(BeanValidator beanValidator) {
		this.beanValidator = beanValidator;
	}

	@Override
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public BeanValidator getBeanValidator()
	{
		return beanValidator;
	}

	

	@Override
	public void setEmailSender(EmailSender emailSender) {
		this.email=emailSender;
		
	}

	@Override
	public EmailSender getEmailSender() {
		
		return this.email;
	}

	@Override
	public void setMailSender(JavaMailSender emailSender) {
		this.mail=emailSender;
		
	}

	@Override
	public JavaMailSender getMailSender() {
		
		return this.mail;
	}


}
