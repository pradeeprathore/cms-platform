package com.ascent.cms.core.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.stereotype.Component;

import com.ascent.cms.core.constants.DatePattern;

@Component
public class JsonDateTimeDeserializer extends JsonDeserializer<Date>
{
	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException,
			JsonProcessingException
	{
		SimpleDateFormat format = new SimpleDateFormat(DatePattern.INDIAN_DATE_TIME.getPattern());
		String date = jsonparser.getText();
		try
		{
			return format.parse(date);
		} catch (ParseException e)
		{
			throw new RuntimeException(e);
		}
	}

}
