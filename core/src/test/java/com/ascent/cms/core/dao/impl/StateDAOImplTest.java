package com.ascent.cms.core.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ascent.cms.core.dao.StateDAO;
import com.ascent.cms.core.domain.Country;
import com.ascent.cms.core.domain.State;

public class StateDAOImplTest extends AbstractPatentRelatedDAOTests
{

	@Autowired
	private StateDAO stateDAO;

	@Test(expected = NullPointerException.class)
	public void testFindByCountry_Country_Is_Null()
	{
		this.stateDAO.findByCountry(null);
	}

	@Test()
	public void testFindByCountry_Country_Does_Not_Exist()
	{
		short countryId = 123;
		Country aCountry = new Country(countryId);
		List<State> result = this.stateDAO.findByCountry(aCountry);
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test()
	public void testFindByCountry_Country_Does_Exist()
	{
		short countryId = 1;
		Country aCountry = new Country(countryId);
		List<State> result = this.stateDAO.findByCountry(aCountry);
		assertNotNull(result);
		assertEquals(2, result.size());
		// test order
		assertEquals("Gujarat", result.get(0).getName());
		assertEquals("Rajasthan", result.get(1).getName());
	}

	@Test()
	public void testFindByCountry_Different_Country()
	{
		short countryId = 2;
		Country aCountry = new Country(countryId);
		List<State> result = this.stateDAO.findByCountry(aCountry);
		assertNotNull(result);
		assertEquals(2, result.size());
		// test order
		assertEquals("Balochistan", result.get(0).getName());
		assertEquals("Punjab", result.get(1).getName());
	}
}
