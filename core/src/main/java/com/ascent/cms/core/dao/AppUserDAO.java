package com.ascent.cms.core.dao;

import java.util.List;

import com.ascent.cms.core.domain.user.AppUser;

public interface AppUserDAO extends BaseDAO
{

	void save(AppUser appUser);

	List<AppUser> getAll();

	AppUser findByUserName(String userName);

	AppUser findByEmail(String email);

}
