package com.ascent.cms.services.user;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ascent.cms.core.constants.DetailMode;
import com.ascent.cms.core.domain.AccessToken;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.vo.AppUserVO;
import com.ascent.cms.core.vo.ListResponse;
import com.ascent.cms.core.vo.SearchCriteria;
import com.ascent.cms.core.vo.UploadedFile;
import com.ascent.cms.services.BaseService;

public interface AppUserService extends BaseService
{

    UploadedFile saveProfilePic(MultipartFile uploadedFile);
    
	public abstract ListResponse getBasic(SearchCriteria searchCriteria);

	public abstract AppUserVO getAppUserById(Long id, DetailMode detailMode);
	
	public abstract AppUserVO SaveUser(AppUserVO appUserVO);

	public abstract AccessToken createAccessToken(AppUser appUser, AccessToken accessToken);

}
