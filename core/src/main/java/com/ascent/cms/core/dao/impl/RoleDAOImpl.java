package com.ascent.cms.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ascent.cms.core.constants.RoleName;
import com.ascent.cms.core.dao.RoleDAO;
import com.ascent.cms.core.dao.util.DAOUtils;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.domain.user.Role;
import com.ascent.cms.core.domain.user.UserRole;
import com.ascent.cms.core.util.Assert;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;

@Repository
public class RoleDAOImpl extends BaseDAOImpl implements RoleDAO
{

	@Override
	public Role findByName(RoleName roleName)
	{
		Assert.notNull(roleName, "'roleName' is required");

		Criteria criteria = createCriteria(Role.class);
		criteria.add(Restrictions.eq(Role.NAME, roleName.name()));
		Role role = (Role) criteria.uniqueResult();

		return role;
	}

	@Override
	public ListResponse getAll(SearchCriteria searchCriteria)
	{
		Assert.searchCriteriaNotNull(searchCriteria);

		Criteria criteria = createCriteria(Role.class);
		Long resultCount = DAOUtils.getResultCount(criteria);

		DAOUtils.setPagination(searchCriteria.getStartPosition(), searchCriteria.getMaxResult(), criteria);
		@SuppressWarnings("unchecked")
		List<Role> result = criteria.list();
		return new ListResponse(resultCount, resultCount, result);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> findRoles(AppUser appUser)
	{
		Assert.notNull(appUser, "'appUser' is required");

		Criteria criteria = createCriteria(UserRole.class);
		criteria.add(Restrictions.eq(UserRole.APP_USER, appUser));
		criteria.setProjection(Projections.property(UserRole.ROLE));

		return criteria.list();
	}

}
