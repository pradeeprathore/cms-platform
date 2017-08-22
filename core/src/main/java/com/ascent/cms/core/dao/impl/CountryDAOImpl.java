package com.ascent.cms.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import com.ascent.cms.core.dao.CountryDAO;
import com.ascent.cms.core.domain.Country;

@Repository
public class CountryDAOImpl extends BaseDAOImpl implements CountryDAO, InitializingBean
{
	private static CountryDAO instance;

	private static final String ALL_COUNTRIES = "from Country order by name";

	public static CountryDAO getInstance()
	{
		return instance;
	}

	@Override
	public List<Country> getAll()
	{
		return getAll(ALL_COUNTRIES);
	}

	@Override
	public void afterPropertiesSet() throws Exception
	{
		instance = this;
	}
}
