package com.ascent.cms.web.controller;

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
import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.vo.UploadedFile;
import com.ascent.cms.services.user.AppUserService;
import com.ascent.cms.web.util.WebUtils;

@Controller
@RequestMapping("/profile")
public class ProfileController extends BaseController
{

		@Autowired
	private AppUserService appUserService;



	
	@RequestMapping(value = "/profilePic", method = RequestMethod.POST)
	public ResponseEntity<UploadedFile> saveProfilePic(
			@RequestParam(value = "file", required = true) MultipartFile file)
	{
		this.validate(file);
		UploadedFile fileName = appUserService.saveProfilePic(file);
		return new ResponseEntity<UploadedFile>(fileName, HttpStatus.OK);
	}
}
