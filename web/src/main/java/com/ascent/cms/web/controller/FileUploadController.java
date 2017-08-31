package com.ascent.cms.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.vo.ResponseFileVO;
import com.ascent.cms.core.vo.UploadedFile;
import com.ascent.cms.services.file.FileService;


@Controller
@RequestMapping("/fileuploads")
public class FileUploadController extends BaseController
{

	@Autowired 
	FileService fileService;


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ResponseFileVO> saveQualificationFile(
			@RequestParam(value = "file", required = true) MultipartFile file)
	{
		this.validate(file);
		ResponseFileVO fileName = fileService.fileUpload(file);
		return new ResponseEntity<ResponseFileVO>(fileName, HttpStatus.OK);
	}
}
