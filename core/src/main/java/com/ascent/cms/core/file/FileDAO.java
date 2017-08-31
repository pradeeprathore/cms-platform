package com.ascent.cms.core.file;

import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.vo.ResponseFileVO;

public interface FileDAO {

	
		 public ResponseFileVO Uploadfiles(MultipartFile file);
		
}
