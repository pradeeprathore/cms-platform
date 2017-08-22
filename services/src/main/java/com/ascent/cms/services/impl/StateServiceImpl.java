package com.ascent.cms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.dao.CountryDAO;
import com.ascent.cms.core.dao.StateDAO;
import com.ascent.cms.core.domain.Country;
import com.ascent.cms.core.domain.State;
import com.ascent.cms.core.exception.ResourceNotFoundException;
import com.ascent.cms.core.util.Assert;
import com.ascent.cms.services.StateService;

@Service
@Transactional
public class StateServiceImpl extends BaseServiceImpl implements StateService
{
	@Autowired
	private StateDAO stateDAO;

	@Autowired
	private CountryDAO countryDAO;

	@Override
	@Transactional(readOnly = true)
	public List<State> findByCountry(Country aCountry)
	{
		Assert.notNull(aCountry, "'aCountry' is required.");

		if (countryDAO.getById(Country.class, aCountry.getId(), null) == null)
		{
			throw new ResourceNotFoundException(ErrorCode.ResourceNotFoundConstants.COUNTRY_NOT_FOUND);
		}

		return this.stateDAO.findByCountry(aCountry);
	}

}
