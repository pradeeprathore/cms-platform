package com.ascent.cms.core.dao.impl;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.ascent.cms.core.dao.PatrikaDAO;
import com.ascent.cms.core.domain.Patrika;
import com.ascent.cms.core.util.Assert;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;

@Repository
public class PatrikaDAOImpl extends BaseDAOImpl implements PatrikaDAO {

	@Override
	public ListResponse getAll(SearchCriteria searchCriteria) {
		//Assert.notNull(searchCriteria, "Search criteria can not  be null");
		
		Criteria criteria=createCriteria(Patrika.class);
		ListResponse result=new ListResponse(criteria.list().size(), criteria.list().size(), criteria.list());
		return result;
	}



}
