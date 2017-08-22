package com.ascent.cms.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascent.cms.core.dao.LoginDAO;
import com.ascent.cms.core.domain.Login;
import com.ascent.cms.services.LogoutService;

@Service("logoutService")
public class LogoutServiceImpl extends BaseServiceImpl implements LogoutService
{

	@Autowired
	private LoginDAO loginDAO;

	@Override
	@Transactional(readOnly = false)
	public void logout(Long userId)
	{
		// logout should be fail safe
		if (userId == null || userId <= 0)
		{
			return;
		}

		Login login = loginDAO.getByUserId(userId);
		if (login != null)
		{
			loginDAO.deleteLogin(userId, login);
		}
	}

}
