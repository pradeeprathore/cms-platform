package com.ascent.cms.core.constants;

public enum DatePattern
{
	INDIAN_SHORT("dd-MM-yyyy"), INDIAN_DATE_TIME("dd-MM-yyyy HH:mm");

	private DatePattern(String pattern)
	{
		this.pattern = pattern;
	}

	private String pattern;

	public String getPattern()
	{
		return pattern;
	}

}
