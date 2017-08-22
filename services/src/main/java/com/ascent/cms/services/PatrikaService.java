package com.ascent.cms.services;

import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.domain.Patrika;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;

public interface PatrikaService extends BaseService{
	
	Patrika SavePatrika(Patrika patrika);
	
	ListResponse getAll(SearchCriteria searchCriteria);

}
