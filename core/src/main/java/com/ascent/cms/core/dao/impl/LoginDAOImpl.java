package com.ascent.cms.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ascent.cms.core.dao.LoginDAO;
import com.ascent.cms.core.domain.Login;
import com.ascent.cms.core.util.Assert;

@Repository
public class LoginDAOImpl extends BaseDAOImpl implements LoginDAO
{

	@Override
	public Login getByUserId(Long userId)
	{
		Assert.notNull(userId, "'userId' is required");
		Assert.NonZero(userId, "'userId' should be greater than 0");

		Criteria criteria = createCriteria(Login.class);
		criteria.add(Restrictions.eq(Login.USER_ID, userId));

		return (Login) criteria.uniqueResult();
	}

	@Override
	public void deleteLogin(Long userId, Login login)
	{
		Assert.notNull(userId, "'userId' is required");
		Assert.NonZero(userId, "'userId' should be greater than 0");
		Assert.notNull(login, "'login' is required");

		super.delete(login);
	}

}
