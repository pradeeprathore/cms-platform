package com.ascent.cms.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascent.cms.core.dao.CountryDAO;
import com.ascent.cms.core.domain.Country;
import com.ascent.cms.services.CountryService;

@Service
@Transactional
public class CountryServiceImpl extends BaseServiceImpl implements CountryService
{
	@Autowired
	private CountryDAO countryDAO;

	@Transactional(readOnly = true)
	public List<Country> getAll()
	{
		return this.countryDAO.getAll();
	}

}
