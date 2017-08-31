package com.ascent.cms.services.file;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.vo.ResponseFileVO;
import com.ascent.cms.services.BaseService;




public interface FileService extends BaseService
{
	public File getFile(String fileKey);

	public ResponseFileVO fileUpload(MultipartFile file);
}
