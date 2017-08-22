package com.ascent.cms.core.exception;

public class BookingFailureException extends CoreRuntimeException
{

	private static final long serialVersionUID = 4149246545197786795L;

	public BookingFailureException(String errorCode)
	{
		super(errorCode);
	}

	public BookingFailureException(String errorCode, String message, Throwable cause)
	{
		super(errorCode, message, cause);
	}

	public BookingFailureException(String errorCode, String message)
	{
		super(errorCode, message);
	}

	public BookingFailureException(String errorCode, Throwable cause)
	{
		super(errorCode, cause);
	}

}
