package com.ascent.cms.core.exception;

public class AuthenticationFailureException extends CoreRuntimeException
{

	private static final long serialVersionUID = 1L;

	public AuthenticationFailureException(String errorCode, String message)
	{
		super(errorCode, message);
	}

}
