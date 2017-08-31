package com.ascent.cms.core.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.dao.impl.BaseDAOImpl;
import com.ascent.cms.core.domain.Patrika;
import com.ascent.cms.core.vo.ResponseFileVO;


@Repository
@Transactional
public class FileDAOImpl  extends BaseDAOImpl implements FileDAO{

	
	String rootPath = "/var/lib/tomcat7/webapps/shang-shakti-file/";
	@Override
	public ResponseFileVO Uploadfiles(MultipartFile file) {
		// TODO Auto-generated method stub
		ResponseFileVO response=new  ResponseFileVO();
		String ext = file.getContentType();
		
		String arr[] = ext.split("/");
		
		if (!file.isEmpty()) {
			try {			
				byte[] bytes = file.getBytes();
				File dir = null;
				dir = new File(rootPath + String.valueOf(new Date().getDate()) + String.valueOf(new Date().getMonth())
						+ String.valueOf(new Date().getYear()));
			
				if (!dir.exists())
					dir.mkdirs();
				File serverFile = new File(
						dir.getAbsolutePath() + File.separator + new Date().getTime() + "." + arr[1]);
				//Logger.getLogger("System").debug("Path -- >" + serverFile);
				Logger.getLogger("====>>>>>>>"+serverFile.toString());
				Logger.getLogger("====>>>>>>>"+serverFile.getAbsolutePath().toString());
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.flush();
				stream.close();
				
				try{
					Patrika patrika= new Patrika();
					patrika.setFilePath(serverFile.getAbsolutePath());
					patrika.setTitle(file.getOriginalFilename());
					patrika.setCreatedOn(new  Date());
					save(patrika);
					System.out.println("file succesfully uploaded");
					response =new ResponseFileVO();	
					response.setMassage("sucess");
					response.setFilePath(serverFile.getAbsolutePath());
				}catch(Exception e){
					System.out.println(""+e);
					e.printStackTrace();
					
				}
				
			 
			}
			catch(Exception e){
				System.out.println(""+e);
			}
			
		}
		return response;
	}

	}


