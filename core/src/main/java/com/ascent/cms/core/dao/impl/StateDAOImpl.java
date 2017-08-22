package com.ascent.cms.core.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ascent.cms.core.dao.StateDAO;
import com.ascent.cms.core.domain.Country;
import com.ascent.cms.core.domain.State;
import com.ascent.cms.core.util.Assert;

@Repository
public class StateDAOImpl extends BaseDAOImpl implements StateDAO
{

	private static final String FIND_BY_COUNTRY = "from State o where o.country=:country order by o.name";

	@Override
	// @Cacheable(value = "states", key = "#p0")
	public List<State> findByCountry(final Country aCountry)
	{
		Assert.notNull(aCountry, "'aCountry' is required.");

		@SuppressWarnings("unchecked")
		List<State> result = createQuery(FIND_BY_COUNTRY).setParameter(State.COUNTRY, aCountry).list();
		return result;
	}

}
