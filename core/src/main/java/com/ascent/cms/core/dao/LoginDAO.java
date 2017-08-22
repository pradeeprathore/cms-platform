package com.ascent.cms.core.dao;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.ascent.cms.core.domain.Login;

public interface LoginDAO extends BaseDAO
{
	@Cacheable(value = "logins", unless = "#result == null", key = "#p0")
	Login getByUserId(Long userId);

	@CacheEvict(value = "logins", key = "#p0")
	void deleteLogin(Long userId, Login login);
}
