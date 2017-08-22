package com.ascent.cms.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ascent.cms.core.constants.DetailMode;
import com.ascent.cms.core.vo.AppUserVO;
import com.ascent.cms.core.vo.ListResponse;

import com.ascent.cms.core.vo.SearchCriteria;

import com.ascent.cms.services.user.AppUserService;
import com.ascent.cms.web.util.WebUtils;

@Controller
@RequestMapping("/user")
public class AppUserController extends BaseController
{

	@Autowired
	private AppUserService appuser;

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<AppUserVO> create(@RequestBody AppUserVO appUserVO, HttpServletRequest request)
	{
		validate(appUserVO);
		
		AppUserVO saved = appuser.SaveUser(appUserVO);
		HttpStatus status = saved.getId() != null ? HttpStatus.OK : HttpStatus.CREATED;
		return new ResponseEntity<AppUserVO>(saved, status);
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<AppUserVO> get(HttpServletRequest request, @PathVariable Long id,
			@RequestParam(required = false) DetailMode detailMode)
	{
		
		AppUserVO patient = appuser.getAppUserById(id, detailMode);
		return new ResponseEntity<AppUserVO>(patient, HttpStatus.OK);
	}

	
}
