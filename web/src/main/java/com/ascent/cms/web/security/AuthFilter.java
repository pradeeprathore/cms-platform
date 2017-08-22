package com.ascent.cms.web.security;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Hashtable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpUtils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;

import com.ascent.cms.web.util.WebUtils;

public class AuthFilter extends GenericFilterBean implements InitializingBean
{
	private static final String FIND_PARAM_KEY = "find";

	private static final Logger logger = Logger.getLogger(AuthFilter.class);

	private AuthenticationManager authenticationManager;

	public void setAuthenticationManager(AuthenticationManager authenticationManager)
	{
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void afterPropertiesSet() throws ServletException
	{
		super.afterPropertiesSet();
		Assert.notNull(authenticationManager, "'authenticationManager' is required.");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException
	{
		long startTime = System.currentTimeMillis();

		Authentication authentication = getAuthenticationToken(request);
		Authentication authResult = null;
		try
		{
			authResult = this.authenticationManager.authenticate(authentication);
		} catch (AuthenticationException e)
		{
			authResult = null;
		}
		SecurityContextHolder.getContext().setAuthentication(authResult);
		chain.doFilter(request, response);

		logExecutionTime(request, startTime);
	}

	private void logExecutionTime(ServletRequest request, long startTime)
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		StringBuffer urlBuilder = HttpUtils.getRequestURL(httpRequest);
		if (StringUtils.isNotBlank(httpRequest.getQueryString()))
		{
			Hashtable<String, String[]> params = HttpUtils.parseQueryString(httpRequest.getQueryString());
			if (params != null)
			{
				String[] find = params.get(FIND_PARAM_KEY);
				if (find != null && find.length > 0)
				{
					urlBuilder.append("?find=").append(find[0]);
				}
			}
		}
		String msg = MessageFormat.format("Service : {0} : time taken : {1}", urlBuilder,
				(System.currentTimeMillis() - startTime));
		logger.debug(msg);
	}

	private Authentication getAuthenticationToken(ServletRequest request)
	{
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		String authToken = WebUtils.getAuthToken(httpServletRequest);
		String userId = WebUtils.getHeaderValue(httpServletRequest,WebUtils.X_ASCENT_USERID);
		Authentication authentication = new CustomAuthenticationToken(userId, authToken);
		
		return authentication;
	}

}
