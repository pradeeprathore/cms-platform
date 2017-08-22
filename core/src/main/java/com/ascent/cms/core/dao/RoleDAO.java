package com.ascent.cms.core.dao;

import java.util.List;

import com.ascent.cms.core.constants.RoleName;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.domain.user.Role;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;

public interface RoleDAO extends BaseDAO
{

	public abstract Role findByName(RoleName roleName);

	public abstract ListResponse getAll(SearchCriteria searchCriteria);

	public List<Role> findRoles(AppUser appUser);

}
