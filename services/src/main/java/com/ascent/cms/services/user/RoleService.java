package com.ascent.cms.services.user;

import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;
import com.ascent.cms.services.BaseService;

public interface RoleService extends BaseService
{

	ListResponse getAll(SearchCriteria searchCriteria);

}
