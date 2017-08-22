package com.ascent.cms.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

import com.ascent.cms.core.domain.Patrika;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;
import com.ascent.cms.core.vo.UploadedFile;
import com.ascent.cms.services.PatrikaService;
import com.ascent.cms.services.user.AppUserService;
import com.ascent.cms.web.util.WebUtils;

@Controller
@RequestMapping("/patrika")
public class PatrikaController extends BaseController
{

	@Autowired
	private PatrikaService patrikaService;



	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Patrika> save(@RequestBody Patrika patrika)
	{
		
		Patrika fileName = patrikaService.SavePatrika(patrika);
		return new ResponseEntity<Patrika>(fileName, HttpStatus.OK);
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<ListResponse> getAll(HttpServletRequest request)
	{
		SearchCriteria searchCriteria=WebUtils.getSearchCriteria(request);
		
		ListResponse fileName = patrikaService.getAll(searchCriteria);
		return new ResponseEntity<ListResponse>(fileName, HttpStatus.OK);
	}
}
