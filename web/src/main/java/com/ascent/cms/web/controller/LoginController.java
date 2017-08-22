package com.ascent.cms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascent.cms.core.constraint.UpdateEntity;
import com.ascent.cms.core.domain.PasswordResetRequest;
import com.ascent.cms.core.vo.LoginRequest;
import com.ascent.cms.core.vo.RefreshedToken;
import com.ascent.cms.core.vo.UserPrincipal;
import com.ascent.cms.services.LoginService;
import com.ascent.cms.services.LogoutService;
import com.ascent.cms.web.util.WebUtils;

@Controller
public class LoginController extends BaseController
{

	@Autowired
	private LoginService loginService;

	@Autowired
	private LogoutService logoutService;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserPrincipal> login(@RequestBody LoginRequest loginRequest)
	{
		validate(loginRequest);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		UserPrincipal principal = loginService.login(loginRequest);
		return new ResponseEntity<UserPrincipal>(principal, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/refreshedtoken", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<RefreshedToken> refreshToken(HttpServletRequest request)
	{

		String jwtToken=WebUtils.getAuthToken(request);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		RefreshedToken refreshedToken = loginService.refreshToken(jwtToken);
		return new ResponseEntity<RefreshedToken>(refreshedToken, headers, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/credentials", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateCredentials(HttpServletRequest request, @RequestBody LoginRequest loginRequest)
	{
		validate(loginRequest, UpdateEntity.class);

		Long userId = WebUtils.getParsedUserId(request);
		loginRequest.setUserId(userId);
		loginService.updatePassword(loginRequest);

		logoutService.logout(userId);

		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@RequestMapping(value = "/resetpasswordrequest", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> createResetPasswordRequest(HttpServletRequest request,
			@RequestBody PasswordResetRequest passwordResetRequest)
	{
		validate(passwordResetRequest);

		loginService.createPasswordResetRequest(passwordResetRequest);

		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/passwordreset", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> resetPassword(HttpServletRequest request,
			@RequestBody PasswordResetRequest passwordResetRequest)
	{
		validate(passwordResetRequest, UpdateEntity.class);

		loginService.resetPassword(passwordResetRequest);

		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
}
