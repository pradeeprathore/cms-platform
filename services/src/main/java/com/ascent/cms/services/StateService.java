package com.ascent.cms.services;

import java.util.List;

import com.ascent.cms.core.domain.Country;
import com.ascent.cms.core.domain.State;

public interface StateService extends BaseService
{

	List<State> findByCountry(Country aCountry);

}
