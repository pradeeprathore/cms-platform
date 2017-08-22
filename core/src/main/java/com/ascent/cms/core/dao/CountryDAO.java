package com.ascent.cms.core.dao;

import java.util.List;

import com.ascent.cms.core.domain.Country;

public interface CountryDAO extends BaseDAO
{

	public abstract List<Country> getAll();

}
