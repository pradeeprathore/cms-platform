package com.ascent.cms.services;

import java.util.List;

import com.ascent.cms.core.domain.Country;

public interface CountryService extends BaseService
{

	public abstract List<Country> getAll();

}
