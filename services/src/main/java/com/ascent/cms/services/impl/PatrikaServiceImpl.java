package com.ascent.cms.services.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.dao.PatrikaDAO;
import com.ascent.cms.core.domain.Patrika;
import com.ascent.cms.core.exception.InvalidArgumentException;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;
import com.ascent.cms.core.vo.UploadedFile;
import com.ascent.cms.services.PatrikaService;

@Service
public class PatrikaServiceImpl extends BaseServiceImpl implements PatrikaService{
	
	@Autowired
	PatrikaDAO patrikaDAO;
	
	private final String PATRIKA_PATH = File.separator + "patrika";
	private final String DOT = ".";

	private final double FILE_SIZE = 1048576;
	
	@Override
	public Patrika SavePatrika(Patrika patrika) {
		
		
			
			save(patrika);
		
		return patrika;
	}

	@Override
	public ListResponse getAll(SearchCriteria searchCriteria) {
		
		return patrikaDAO.getAll(searchCriteria);
	}

}
