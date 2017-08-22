package com.ascent.cms.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

public class SessionListener implements HttpSessionListener
{

	private static final Logger logger = Logger.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent se)
	{
		logger.warn("HTTP Session is created.");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se)
	{
		logger.warn("HTTP Session is destroyed.");
	}

}
