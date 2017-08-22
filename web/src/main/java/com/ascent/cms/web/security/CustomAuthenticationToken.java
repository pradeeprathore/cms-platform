package com.ascent.cms.web.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken
{

	private static final long serialVersionUID = 1L;

	private String path;

	private String method;

	public CustomAuthenticationToken(Object principal, Object credentials)
	{
		super(principal, credentials);
	}

	public String getPath()
	{
		return path;
	}

	public void setPath(String path)
	{
		this.path = path;
	}

	public String getMethod()
	{
		return method;
	}

	public void setMethod(String method)
	{
		this.method = method;
	}
}
