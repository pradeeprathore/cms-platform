package com.ascent.cms.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ascent.cms.core.dao.PasswordResetRequestDAO;
import com.ascent.cms.core.domain.PasswordResetRequest;
import com.ascent.cms.core.util.Assert;

@Repository
public class PasswordResetRequestDAOImpl extends BaseDAOImpl implements PasswordResetRequestDAO
{

	@SuppressWarnings("unchecked")
	@Override
	public List<PasswordResetRequest> findByEmail(String email)
	{
		Assert.notBlank(email, "'email' is required");

		Criteria criteria = createCriteria(PasswordResetRequest.class);

		criteria.add(Restrictions.eq(PasswordResetRequest.EMAIL, email));
		criteria.add(Restrictions.ge(PasswordResetRequest.EXPIRATION_TIME, System.currentTimeMillis()));

		return criteria.list();
	}

	@Override
	public PasswordResetRequest findByToken(String token)
	{
		Assert.notBlank(token, "'token' is required");

		Criteria criteria = createCriteria(PasswordResetRequest.class);

		criteria.add(Restrictions.eq(PasswordResetRequest.TOKEN, token));
		criteria.add(Restrictions.le(PasswordResetRequest.EXPIRATION_TIME, System.currentTimeMillis()));

		return (PasswordResetRequest) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<PasswordResetRequest> findNonExpiredRequests()
	{
		Criteria criteria = createCriteria(PasswordResetRequest.class);
		criteria.add(Restrictions.ge(PasswordResetRequest.EXPIRATION_TIME, System.currentTimeMillis()));

		return criteria.list();
	}
}
