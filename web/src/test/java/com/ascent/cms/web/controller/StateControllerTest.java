package com.ascent.cms.web.controller;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ascent.cms.core.ErrorCode;

public class StateControllerTest extends AbstractControllerTests
{

	@Autowired
	private StateController stateCOntroller;

	@Test
	public void testFindByCountry() throws Exception
	{
		standaloneSetup(stateCOntroller).build().perform(get("/countrys/1/states")).andExpect(status().isOk())
				.andExpect(content().type(APPLICATION_JSON_UTF8)).andExpect(jsonPath("$.[0].name").value("Gujarat"))
				.andExpect(jsonPath("$.[1].name").value("Rajasthan"));
	}

	@Test
	public void testFindByCountry_countryId_Negative() throws Exception
	{
		String url = "/countrys/-10/states";
		verifyErrorMessages(url);
	}

	@Test
	public void testFindByCountry_countryId_Zero() throws Exception
	{
		String url = "/countrys/0/states";
		verifyErrorMessages(url);
	}

	@Test
	public void testFindByCountry_countryId_Does_Not_Exist() throws Exception
	{
		String url = "/countrys/6566/states";
		verifyErrorMessages(url);
	}

	private void verifyErrorMessages(String url) throws Exception
	{
		standaloneSetup(stateCOntroller)
				.build()
				.perform(get(url))
				.andExpect(status().isNotFound())
				.andExpect(content().type(APPLICATION_JSON_UTF8))
				.andExpect(
						jsonPath("$.[0].message").value(
								getMessage(ErrorCode.ResourceNotFoundConstants.COUNTRY_NOT_FOUND)));
	}
}
