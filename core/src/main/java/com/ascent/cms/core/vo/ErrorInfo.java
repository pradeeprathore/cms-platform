package com.ascent.cms.core.vo;

import org.apache.commons.lang3.builder.CompareToBuilder;

public class ErrorInfo implements Comparable<ErrorInfo>
{

	private String propertyName;

	private Object invalidValue;

	private String message;

	public ErrorInfo()
	{

	}

	public ErrorInfo(String propertyName, Object invalidValue, String message)
	{
		super();
		this.propertyName = propertyName;
		this.invalidValue = invalidValue;
		this.message = message;
	}

	public String getPropertyName()
	{
		return propertyName;
	}

	public void setPropertyName(String propertyName)
	{
		this.propertyName = propertyName;
	}

	public Object getInvalidValue()
	{
		return invalidValue;
	}

	public void setInvalidValue(Object invalidValue)
	{
		this.invalidValue = invalidValue;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	@Override
	public int compareTo(ErrorInfo other)
	{
		if (other == null)
		{
			return -1;
		}
		return new CompareToBuilder().append(this.getPropertyName(), other.getPropertyName()).toComparison();
	}

}
