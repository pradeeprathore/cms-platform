package com.ascent.cms.core.util;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.vo.SearchCriteria;

public class Assert
{
	private static final String ID_SHOULD_BE_GREATER_THAN_0 = "'id' should be greater than 0";

	private static final String ID_IS_REQUIRED = "'id' is required";

	private static final String A_NAME_IS_REQUIRED = "'aName' is required";

	private static final String A_APP_USER_IS_REQUIRED = "'aAppUser' is required";

	private static final String APP_USER_ID_IS_REQUIRED = "'appUserId' is required";

	private static final String APP_USER_ID_SHOULD_BE_GREATER_THAN_0 = "'appUserId' should be greater than 0";

	private static final String SEARCH_CRITERIA_IS_REQUIRED = "'searchCriteria' is required";;



	public static void appUserNotNull(final AppUser patent)
	{
		notNull(patent, A_APP_USER_IS_REQUIRED);
	}

	public static void notNull(Object arg, String message)
	{
		if (arg == null)
		{
			throw new NullPointerException(message);
		}
	}

	public static void nameNotBlank(final String name)
	{
		notBlank(name, A_NAME_IS_REQUIRED);
	}

	public static void notBlank(String arg, String message)
	{
		if (StringUtils.isBlank(arg))
		{
			throw new IllegalArgumentException(message);
		}
	}


	public static void NonZero(int number, String message)
	{
		if (number <= 0)
		{
			throw new IllegalArgumentException(message);
		}

	}

	public static void NonZero(Long number, String message)
	{
		if (number <= 0)
		{
			throw new IllegalArgumentException(message);
		}

	}

	public static void validId(Integer id)
	{
		notNull(id, ID_IS_REQUIRED);
		NonZero(id, ID_SHOULD_BE_GREATER_THAN_0);
	}

	public static void validId(Long id)
	{
		notNull(id, ID_IS_REQUIRED);
		NonZero(id, ID_SHOULD_BE_GREATER_THAN_0);
	}

	public static void validAppUserId(Long appUserId)
	{
		notNull(appUserId, APP_USER_ID_IS_REQUIRED);
		NonZero(appUserId, APP_USER_ID_SHOULD_BE_GREATER_THAN_0);

	}

	public static void searchCriteriaNotNull(SearchCriteria searchCriteria)
	{
		notNull(searchCriteria, SEARCH_CRITERIA_IS_REQUIRED);

	}

	public static void notEmpty(@SuppressWarnings("rawtypes") Collection collection, String message)
	{
		if (CollectionUtils.isEmpty(collection))
		{
			throw new IllegalArgumentException(message);
		}
	}
}
