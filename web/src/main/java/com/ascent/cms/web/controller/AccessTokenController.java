package com.ascent.cms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascent.cms.core.domain.AccessToken;
import com.ascent.cms.core.domain.user.AppUser;

import com.ascent.cms.services.user.AppUserService;

@Controller
@RequestMapping("/accesstokens")
public class AccessTokenController extends BaseController
{
	@Autowired
	private AppUserService patientService;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<AccessToken> create(HttpServletRequest request, @RequestBody AccessToken accessToken)
	{
	//	Doctor doctor = getLoggedInDoctor(request);
		AccessToken saved = null;
		return new ResponseEntity<AccessToken>(saved, HttpStatus.CREATED);
	}

	// @RequestMapping(vaue="/validation", method = RequestMethod.GET)
	// @ResponseBody
	// public ResponseEntity<AccessToken> get(@RequestParam(value="token")
	// String token patientId)
	// {
	// AccessToken accessToken=patientService.getAccessToken(patientId);
	// return new ResponseEntity<AccessToken>(accessToken, HttpStatus.OK);
	// }

}
