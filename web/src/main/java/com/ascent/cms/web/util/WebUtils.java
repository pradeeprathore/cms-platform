package com.ascent.cms.web.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.ascent.cms.core.constants.JWTConstants;
import com.ascent.cms.core.util.CoreUtil;
import com.ascent.cms.core.vo.SearchCriteria;

public class WebUtils
{
	private static final String X_ASCENT_PROXIED = "X-ASCENT-PROXIED";

	public static final String END_PATTERN = "/";

	public static final String X_ASCENT_USERID = "X-ASCENT-USERID";

	public static final String X_ASCENT_AUTHTOKEN = "X-ASCENT-AUTHTOKEN";

	public static final String SEARCH_CRITERIA = "searchCriteria";

	public static String getHeaderValue(HttpServletRequest request, String headerName)
	{
		String value = request.getHeader(headerName);
		return value;
	}

	public static String getUserId(HttpServletRequest httpServletRequest)
	{
		String jsonWebToken= getHeaderValue(httpServletRequest, X_ASCENT_AUTHTOKEN);
		Long userId=(Long)CoreUtil.getClaim(JWTConstants.CLAIM_USER_ID, jsonWebToken);
		return userId.toString();
	}

	public static Long getParsedUserId(HttpServletRequest request)
	{
		return Long.valueOf(getUserId(request));
	}

	public static Long getParsedProxiedDoctorId(HttpServletRequest request)
	{
		String proxiedId = getHeaderValue(request, X_ASCENT_PROXIED);
		if (StringUtils.isBlank(proxiedId))
		{
			return null;
		}
		return Long.valueOf(proxiedId);
	}

	public static String getAuthToken(HttpServletRequest httpServletRequest)
	{
		return getHeaderValue(httpServletRequest, X_ASCENT_AUTHTOKEN);
	}

	public static Integer getRequestParamAsInt(HttpServletRequest request, String paramaName)
	{
		String paramValueStr = request.getParameter(paramaName);
		Integer paramValue = StringUtils.isNotBlank(paramValueStr) ? Integer.valueOf(paramValueStr) : null;
		return paramValue;
	}

	public static boolean getRequestParamAsBoolean(HttpServletRequest request, String paramaName)
	{
		String paramValueStr = request.getParameter(paramaName);
		boolean paramValue = StringUtils.isNotBlank(paramValueStr) ? Boolean.parseBoolean(paramValueStr) : false;
		return paramValue;
	}

	public static String getRequestParam(HttpServletRequest request, String paramaName)
	{
		String paramValue = request.getParameter(paramaName);
		return paramValue;
	}

	public static SearchCriteria getSearchCriteria(HttpServletRequest request)
	{
		SearchCriteria searchRequest = (SearchCriteria) request.getAttribute(SEARCH_CRITERIA);
		return searchRequest;
	}

	@SuppressWarnings("deprecation")
	public static Date getEndOfTommorrow()
	{
		Date today = new Date();
		today.setDate(today.getDate() - 1);
		today.setHours(23);
		today.setMinutes(59);
		return today;
	}
}
