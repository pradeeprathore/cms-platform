package com.ascent.cms.services.util;

import java.util.Date;

import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.ErrorCode.ResourceNotFoundConstants;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.exception.InvalidArgumentException;
import com.ascent.cms.core.exception.ResourceNotFoundException;
import com.ascent.cms.services.BaseService;
import com.ascent.cms.services.impl.BaseServiceImpl;

public abstract class ServiceUtils
{

	public static AppUser getDoctor(BaseServiceImpl baseServiceImpl, final Long appUserId)
	{
		AppUser appUser = baseServiceImpl.getById(AppUser.class, appUserId);
		if (appUser == null)
		{
			throw new ResourceNotFoundException(ResourceNotFoundConstants.APPUSER_NOT_FOUND);
		}
		return appUser;
	}


	public static AppUser getValidAppUser(BaseService baseService, Long appUserId)
	{
		if (appUserId == null)
		{
			throw new InvalidArgumentException(ErrorCode.CommonConstants.REQUIRED_PARAM_MISSING, "doctoreId");
		}
		AppUser appUser = baseService.getById(AppUser.class, appUserId);
		if (appUser == null)
		{
			throw new InvalidArgumentException(ErrorCode.CommonConstants.DOCTOR_NOT_FOUND);
		}
		return appUser;
	}

	public static boolean isEquals(Long one, Long other)
	{
		return one.equals(other);
	}



	private static boolean isEquals(Integer one, Integer other)
	{
		return one.equals(other);
	}
	
	
}
