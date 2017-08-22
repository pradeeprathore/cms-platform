package com.ascent.cms.core.dao.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ascent.cms.core.dao.AppUserDAO;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.domain.user.UserRole;
import com.ascent.cms.core.util.Assert;

@Repository
public class AppUserDAOImpl extends BaseDAOImpl implements AppUserDAO
{

	private static final Logger logger = Logger.getLogger(AppUserDAOImpl.class);

	@Override
	public void save(AppUser appUser)
	{
		logger.debug("DAO invoked to save appuser.");
		Assert.notNull(appUser, "'appUser' is required");
		getCurrentSession().save(appUser);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AppUser> getAll()
	{
		logger.debug("DAO invoked to get all appusers.");
		return getCurrentSession().createQuery("from AppUser").list();

	}

	@Override
	public AppUser findByUserName(String mobile)
	{
		Assert.notBlank(mobile, "'mobile' is required");

		Session session = getCurrentSession();
		// session.setDefaultReadOnly(true);
		session.enableFetchProfile(UserRole.FP_USER_ROLE_ROLE);

		Criteria criteria = session.createCriteria(AppUser.class);
		criteria.add(Restrictions.eq(AppUser.MOBILE, mobile));
		criteria.setFetchMode(AppUser.ASSIGNED_ROLES, FetchMode.JOIN);

		AppUser result = (AppUser) criteria.uniqueResult();

		session.disableFetchProfile(UserRole.FP_USER_ROLE_ROLE);

		return result;
	}

	@Override
	public AppUser findByEmail(String email)
	{
		Assert.notBlank(email, "'email' is required");

		Criteria criteria = createCriteria(AppUser.class);
		criteria.add(Restrictions.eq(AppUser.EMAIL, email));

		return (AppUser) criteria.uniqueResult();
	}

}
