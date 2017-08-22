package com.ascent.cms.core.dao;

import com.ascent.cms.core.domain.Patrika;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;

public interface PatrikaDAO extends BaseDAO{
	
	ListResponse getAll(SearchCriteria searchCriteria);
	


}
