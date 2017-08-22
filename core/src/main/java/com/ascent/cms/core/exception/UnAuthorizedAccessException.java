package com.ascent.cms.core.exception;

public class UnAuthorizedAccessException extends CoreRuntimeException
{

	private static final long serialVersionUID = 8280814891145949611L;

	public UnAuthorizedAccessException(String errorCode, String message)
	{
		super(errorCode, message);
	}

}
