package com.ascent.cms.core.exception;

public class CoreRuntimeException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	private String errorCode;

	public CoreRuntimeException(final String errorCode)
	{
		super();
		this.errorCode = errorCode;
	}

	public CoreRuntimeException(final String errorCode, final String message)
	{
		super(message);
		this.errorCode = errorCode;
	}

	public CoreRuntimeException(final String errorCode, final Throwable cause)
	{
		super(cause);
		this.errorCode = errorCode;
	}

	public CoreRuntimeException(final String errorCode, final String message, final Throwable cause)
	{
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode()
	{
		return errorCode;
	}

}
