package com.ascent.cms.services.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascent.cms.core.dao.RoleDAO;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;
import com.ascent.cms.services.impl.BaseServiceImpl;
import com.ascent.cms.services.user.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl implements RoleService
{
	@Autowired
	private RoleDAO roleDAO;

	@Override
	@Transactional(readOnly = true)
	public ListResponse getAll(SearchCriteria searchCriteria)
	{
		return this.roleDAO.getAll(searchCriteria);
	}
}
