package com.ascent.cms.core.util;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.ascent.cms.core.vo.UserPrincipal;

@Component("authenticationCache")
public class AuthenticationCache
{

	private ConcurrentHashMap<String, com.ascent.cms.core.vo.UserPrincipal> authenticatedUsers;

	public AuthenticationCache()
	{
		authenticatedUsers = new ConcurrentHashMap<String, UserPrincipal>();
	}

	public void addAuthentication(String authToken, UserPrincipal principal)
	{
		this.authenticatedUsers.put(authToken, principal);
	}

	public void removeAuthentication(String authToken)
	{
		this.authenticatedUsers.remove(authToken);
	}

	public UserPrincipal get(String authToken)
	{
		return authenticatedUsers.get(authToken);
	}

}
