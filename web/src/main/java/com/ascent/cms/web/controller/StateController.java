package com.ascent.cms.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascent.cms.core.domain.Country;
import com.ascent.cms.core.domain.State;
import com.ascent.cms.services.StateService;

@Controller
@RequestMapping("/countrys/{id}/states")
public class StateController extends BaseController
{

	@Autowired
	private StateService stateService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> getByCountry(@PathVariable Short id)
	{
		List<State> states = stateService.findByCountry(new Country(id));
		return new ResponseEntity<Object>(states, HttpStatus.OK);
	}

}
