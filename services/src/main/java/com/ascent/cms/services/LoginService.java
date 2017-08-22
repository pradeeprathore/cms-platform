package com.ascent.cms.services;

import com.ascent.cms.core.domain.Login;
import com.ascent.cms.core.domain.PasswordResetRequest;
import com.ascent.cms.core.vo.LoginRequest;
import com.ascent.cms.core.vo.RefreshedToken;
import com.ascent.cms.core.vo.UserPrincipal;

public interface LoginService extends BaseService
{

	public UserPrincipal login(LoginRequest loginRequest);

	public void updatePassword(LoginRequest loginRequest);

	Login getByUserId(Long userId);

	public void createPasswordResetRequest(PasswordResetRequest passwordResetRequest);

	public void resetPassword(PasswordResetRequest passwordResetRequest);

	void deleteLogin(Login login);

	public RefreshedToken refreshToken(String jwtToken);

}
