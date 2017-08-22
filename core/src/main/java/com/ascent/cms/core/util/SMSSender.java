package com.ascent.cms.core.util;

public interface SMSSender
{

	public abstract void send(String mobileNumber, String message);

}
