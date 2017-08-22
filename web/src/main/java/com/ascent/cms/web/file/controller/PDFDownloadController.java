package com.ascent.cms.web.file.controller;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import com.ascent.cms.web.controller.BaseController;

@Controller
@RequestMapping("/pdf")
public class PDFDownloadController extends BaseController implements ServletContextAware
{
	private static final String ATTRIBUTE_TEMP_DIR = "javax.servlet.context.tempdir";

	private static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";

	private static final String HEADER_CONTENT_TYPE = "Content-Type";

	private static final String MIME_TYPE_APPLICATION_PDF = "application/pdf";

	
	private ServletContext servletContext;

	@Override
	public void setServletContext(final ServletContext servletContext)
	{
		this.servletContext = servletContext;
	}

	
		
	private File getTempDir()
	{
		return (File) this.servletContext.getAttribute(ATTRIBUTE_TEMP_DIR);
	}
}
