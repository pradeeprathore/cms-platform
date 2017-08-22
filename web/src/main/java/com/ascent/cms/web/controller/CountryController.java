package com.ascent.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ascent.cms.core.domain.Country;
import com.ascent.cms.services.CountryService;

@Controller
@RequestMapping("/countrys")
public class CountryController extends BaseController
{

	@Autowired
	private CountryService countryService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll()
	{
		List<Country> countries = countryService.getAll();
		return new ResponseEntity<Object>(countries, HttpStatus.OK);
	}

}
