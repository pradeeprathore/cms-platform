package com.ascent.cms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascent.cms.services.LogoutService;
import com.ascent.cms.web.util.WebUtils;

@Controller
@RequestMapping("/logout")
public class LogoutController extends BaseController
{

	@Autowired
	private LogoutService logoutService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> logout(HttpServletRequest request)
	{
		String userId = WebUtils.getUserId(request);

		if (StringUtils.isNotBlank(userId))
		{
			logoutService.logout(Long.valueOf(userId));
		}

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

}
