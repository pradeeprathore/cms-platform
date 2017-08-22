package com.ascent.cms.core.dao;

import java.util.List;

import com.ascent.cms.core.domain.PasswordResetRequest;

public interface PasswordResetRequestDAO extends BaseDAO
{

	List<PasswordResetRequest> findByEmail(String email);

	PasswordResetRequest findByToken(String token);

	public abstract List<PasswordResetRequest> findNonExpiredRequests();

}
