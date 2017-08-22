package com.ascent.cms.core.dao;

import java.util.List;

import com.ascent.cms.core.domain.Country;
import com.ascent.cms.core.domain.State;

public interface StateDAO extends BaseDAO
{

	List<State> findByCountry(Country aCountry);

}
