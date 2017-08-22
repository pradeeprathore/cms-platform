package com.ascent.cms.web;

import java.io.IOException;

import org.codehaus.jackson.map.ObjectMapper;

public class ControllerTestingUtil
{

	public static byte[] convertObjectToJsonBytes(Object object) throws IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsBytes(object);
	}

	public static String createStringWithLength(int length)
	{
		StringBuilder builder = new StringBuilder();

		for (int index = 0; index < length; index++)
		{
			builder.append("a");
		}

		return builder.toString();
	}
}
