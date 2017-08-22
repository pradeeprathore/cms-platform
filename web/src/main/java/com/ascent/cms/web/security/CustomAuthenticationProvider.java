package com.ascent.cms.web.security;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;

import com.ascent.cms.core.constants.JWTConstants;
import com.ascent.cms.services.LoginService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class CustomAuthenticationProvider implements AuthenticationProvider
{

	@Autowired
	private LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException
	{
		String userId = authentication.getPrincipal() != null ? authentication.getPrincipal().toString() : null;
		String authToken = authentication.getCredentials() != null ? authentication.getCredentials().toString() : null;

		if (StringUtils.isBlank(authToken) || StringUtils.isBlank(userId))
		{
			throw new AuthenticationCredentialsNotFoundException("Bad credentials");
		}

		return authenticateByJsonWebToken(userId, authToken);
	}

	private Authentication authenticateByJsonWebToken(final String userId, final String jsonWebToken)
	{
		SignedJWT signedJWT = null;
		try
		{
			signedJWT = SignedJWT.parse(jsonWebToken);
		} catch (ParseException e)
		{
			throw new BadCredentialsException("Bad credentials", e);
		}
		byte[] sharedSecret = JWTConstants.SECURITY_KEY.getBytes();
		JWSVerifier verifier = new MACVerifier(sharedSecret);
		boolean success = false;
		try
		{
			success = signedJWT.verify(verifier);
		} catch (JOSEException e)
		{
			throw new BadCredentialsException("Bad credentials", e);
		}
		if (!success)
		{
			throw new BadCredentialsException("Bad credentials");
		}
		long expirationTime = 0l;
		String[] roles = null;
		try
		{
			ReadOnlyJWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
			expirationTime = claimsSet.getExpirationTimeClaim();
			String role = (String) claimsSet.getCustomClaim(JWTConstants.CLAIM_ROLE);
			roles = role.split(",");
		} catch (ParseException e)
		{
			throw new BadCredentialsException("Bad credentials");
		}
		if (System.currentTimeMillis() > expirationTime)
		{
			throw new BadCredentialsException("Login expired.");
		}
		List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		for (String role : roles)
		{
			grantedAuthorities.add(new GrantedAuthorityImpl(role));
		}

		return new UsernamePasswordAuthenticationToken(userId, jsonWebToken, grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return true;
	}

}
