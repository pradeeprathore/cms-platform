package com.ascent.cms.services.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.domain.Patrika;
import com.ascent.cms.core.file.FileDAO;
import com.ascent.cms.core.vo.ResponseFileVO;
import com.ascent.cms.services.impl.BaseServiceImpl;




@Service
public class FileServiceImpl extends BaseServiceImpl implements FileService
{
	
	@Autowired 
	FileDAO fileDAO;
	
	
	@Override
	public File getFile(String fileKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseFileVO fileUpload(MultipartFile file) {
		return fileDAO.Uploadfiles(file);
}
}