package com.ascent.cms.core.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class SMSSenderImpl implements SMSSender, InitializingBean
{
	private static final String REQUEST_METHOD_GET = "GET";

	private static final String MOBILE_NUMBER_INDIA_CODE = "91";

	@Value("${sms.url}")
	private String url;

	@Override
	public void afterPropertiesSet() throws Exception
	{
		Assert.notBlank(url, "'url' is required");
	}

	@Async
	@Override
	public void send(String mobileNumberStr, String message)
	{
		Assert.notBlank(mobileNumberStr, "'mobileNumber' is required");
		Assert.notBlank(message, "'message' is required");
		String mobileNumber = mobileNumberStr.substring(Math.max(0, mobileNumberStr.length() - 10),
				mobileNumberStr.length());
		mobileNumber = MOBILE_NUMBER_INDIA_CODE + mobileNumber;
		URL obj;
		try
		{
			String encodedMessage = java.net.URLEncoder.encode(message, "UTF-8");
			String apiUrl = MessageFormat.format(url, mobileNumber, encodedMessage);
			obj = new URL(apiUrl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod(REQUEST_METHOD_GET);

			int responseCode = con.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null)
			{
				response.append(inputLine);
			}
			in.close();
			System.out.println(response.toString());

		} catch (MalformedURLException e)
		{
			throw new RuntimeException(e);
		} catch (ProtocolException e)
		{
			throw new RuntimeException(e);
		} catch (IOException e)
		{
			throw new RuntimeException(e);
		}

	}

}
