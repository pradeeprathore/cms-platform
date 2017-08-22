package com.ascent.cms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.vo.UploadedFile;


@Controller
@RequestMapping("/fileuploads")
public class FileUploadController extends BaseController
{


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UploadedFile> saveQualificationFile(
			@RequestParam(value = "file", required = true) MultipartFile file)
	{
		this.validate(file);
		UploadedFile fileName = null;
		return new ResponseEntity<UploadedFile>(fileName, HttpStatus.OK);
	}
}
