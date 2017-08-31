package com.ascent.cms.services.impl.user;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.EmailSender;
import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.constants.DetailMode;
import com.ascent.cms.core.constants.RoleName;
import com.ascent.cms.core.dao.AppUserDAO;
import com.ascent.cms.core.dao.RoleDAO;
import com.ascent.cms.core.domain.AccessToken;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.domain.user.Role;
import com.ascent.cms.core.domain.user.UserRole;
import com.ascent.cms.core.exception.InvalidArgumentException;
import com.ascent.cms.core.util.EntityContext;
import com.ascent.cms.core.vo.AppUserVO;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;
import com.ascent.cms.core.vo.UploadedFile;
import com.ascent.cms.services.impl.BaseServiceImpl;
import com.ascent.cms.services.user.AppUserService;
import com.ascent.cms.services.util.ServiceUtils;

@Service("appUserService")
public class AppUserServiceImpl extends BaseServiceImpl implements AppUserService
{
	private final double FILE_SIZE = 1048576;
	
	public static final String DIRECTORY_PATH = File.separator + "user" + File.separator + "profile";
	
	private final String DOT = ".";

	@Autowired
	AppUserDAO appUserDAO;
	
	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional(readOnly = false)
	@Override
	public UploadedFile saveProfilePic(MultipartFile uploadedFile)
	{

		if (FILE_SIZE < uploadedFile.getSize())
		{
			throw new InvalidArgumentException(ErrorCode.InvalidRequest.INVALID_FILE_SIZE);
		}
		try
		{

			File directory = new File(DIRECTORY_PATH);
			File fileToStore = new File(directory + File.separator + RandomStringUtils.randomAlphanumeric(15) + DOT
					+ FilenameUtils.getExtension(uploadedFile.getOriginalFilename()));

			FileUtils.writeByteArrayToFile(fileToStore, uploadedFile.getBytes());

			UploadedFile uploadedFinalFile = new UploadedFile();
			uploadedFinalFile.setFilePath(fileToStore.getName());
			uploadedFinalFile.setFileName(fileToStore.getName());
			return uploadedFinalFile;
		} catch (IOException e)
		{
			e.printStackTrace();
			return new UploadedFile();
		}
	}


	@Override
	public ListResponse getBasic(SearchCriteria searchCriteria) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AppUserVO getAppUserById(Long id, DetailMode detailMode) {
		return toAppUserVO(appUserDAO.getById(AppUser.class, id, detailMode))	;
		
	}


	@Override
	public AccessToken createAccessToken(AppUser appUser,
			AccessToken accessToken) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AppUserVO SaveUser(AppUserVO appUserVO,EntityContext context) {
		validate(appUserVO);
		AppUser appuser=toAppUser(appUserVO);
		
		String tempPassword = UUID.randomUUID().toString().substring(1, 10);
		appuser.setPassword(passwordEncoder.encode(tempPassword));
		appuser.setCreatedOn(new Date());
		Role role = roleDAO.findByName(RoleName.ROLE_USER);
		appuser.assignRole(new UserRole(role));
		appuser.setUserName(appUserVO.getMobile());
		//========set mail id as for login
		appuser.setEmail(appUserVO.getEmail());
		appuser.setLoginCreated(false);
		appuser.setActive(true);
		appuser.setEnabled(true);
		appuser.setCreatedOn(new Date());
		try{
			EmailSender sender=context.getEmailSender();
			if(sender!=null){
			sender.sendRegistrationEmail(appUserVO.getEmail(), tempPassword);}
			appUserDAO.save(appuser);
		}catch(Exception e){
			System.out.println(e);}
		
		return appUserVO;
	}
	
	private AppUser toAppUser(AppUserVO appUserVO){
		AppUser appuser=new AppUser(appUserVO.getFirstName(), appUserVO.getMiddleName(), appUserVO.getLastName());
		appuser.setMobile(appUserVO.getMobile());
		return appuser;
	}
	
	private AppUserVO toAppUserVO(AppUser appUser){
		AppUserVO appuserVO=new AppUserVO(appUser.getId(),appUser.getFirstName(), appUser.getMiddleName(), appUser.getLastName());
		appuserVO.setMobile(appUser.getMobile());
		return appuserVO;
	}

}
