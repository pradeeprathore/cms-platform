package com.ascent.cms.core.util;

import java.text.ParseException;

import org.springframework.security.authentication.BadCredentialsException;

import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public abstract class CoreUtil
{

	public static boolean isNonZero(Long id)
	{
		boolean nonZero = (id != null && id > 0);
		return nonZero;
	}

	public static boolean isZero(Long id)
	{
		boolean zero = !isNonZero(id);
		return zero;
	}

	public static Object getClaim(final String claimName, final String jsonWebToken)
	{
		SignedJWT signedJWT = null;
		try
		{
			signedJWT = SignedJWT.parse(jsonWebToken);
		} catch (ParseException e)
		{
			throw new BadCredentialsException("Bad credentials", e);
		}
		Object claim = null;
		try
		{
			ReadOnlyJWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
			claim = claimsSet.getCustomClaim(claimName);
		} catch (ParseException e)
		{
			throw new BadCredentialsException("Bad credentials");
		}
	
		return claim;
	}

}
