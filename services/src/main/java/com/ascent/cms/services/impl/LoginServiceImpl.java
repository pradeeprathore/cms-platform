package com.ascent.cms.services.impl;

import java.io.File;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascent.cms.core.EmailSender;
import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.constants.JWTConstants;
import com.ascent.cms.core.constants.RoleName;
import com.ascent.cms.core.dao.AppUserDAO;
import com.ascent.cms.core.dao.LoginDAO;
import com.ascent.cms.core.dao.PasswordResetRequestDAO;
import com.ascent.cms.core.domain.Login;
import com.ascent.cms.core.domain.PasswordResetRequest;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.domain.user.Role;
import com.ascent.cms.core.domain.user.UserRole;
import com.ascent.cms.core.exception.AuthenticationFailureException;
import com.ascent.cms.core.exception.InvalidArgumentException;
import com.ascent.cms.core.exception.ResourceNotFoundException;
import com.ascent.cms.core.util.Assert;
import com.ascent.cms.core.util.CoreUtil;
import com.ascent.cms.core.vo.LoginRequest;
import com.ascent.cms.core.vo.RefreshedToken;
import com.ascent.cms.core.vo.UserPrincipal;
import com.ascent.cms.services.LoginService;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

@Service("loginService")
@Transactional
public class LoginServiceImpl extends BaseServiceImpl implements LoginService
{
	private static final String ISSUER_CLAIM = "http://marspls.com";

	private static final long PASSWORD_RESET_TOKEN_EXPIRATION_TIME_OUT = 6 * 60 * 60 * 1000;

	@Autowired
	private AppUserDAO appUserDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private LoginDAO loginDAO;

	@Autowired
	private PasswordResetRequestDAO passwordResetRequestDAO;

	@Autowired
	private EmailSender emailSender;

	private String generateToken(Long userId, String rolesStr)
	{
		// Generate random 256-bit (32-byte) shared secret
//		SecureRandom random = new SecureRandom();
//		byte[] sharedSecret = new byte[32];
//		random.nextBytes(sharedSecret);

		// Create HMAC signer
		JWSSigner signer = new MACSigner(JWTConstants.SECURITY_KEY.getBytes());

		// Prepare JWT with claims set
		JWTClaimsSet claimsSet = new JWTClaimsSet();
		claimsSet.setSubjectClaim(userId.toString());
		claimsSet.setIssuerClaim(ISSUER_CLAIM);
		claimsSet.setExpirationTimeClaim(System.currentTimeMillis() + JWTConstants.SESSION_TIME_OUT);
		claimsSet.setCustomClaim(JWTConstants.CLAIM_USER_ID, userId);
		
		claimsSet.setCustomClaim(JWTConstants.CLAIM_ROLE, rolesStr);
		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

		// Apply the HMAC protection
		try
		{
			signedJWT.sign(signer);
		} catch (JOSEException e)
		{
			throw new AuthenticationFailureException(ErrorCode.AuthConstants.INVALID_USER_NAME_OR_PASSWORD,
					"Invalid Username or password");
		}

		// Serialize to compact form, produces something like
		// eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
		String jwtToken = signedJWT.serialize();
		return jwtToken;
	}

	@Override
	@Transactional(readOnly = false)
	public UserPrincipal login(LoginRequest loginRequest)
	{

		AppUser appUser = appUserDAO.findByEmail(loginRequest.getUserName());
		if (appUser == null)
		{
			throw new AuthenticationFailureException(ErrorCode.AuthConstants.INVALID_USER_NAME_OR_PASSWORD,
					"Invalid Username or password");
		}
		boolean passwordMatches = passwordEncoder.matches(loginRequest.getPassword(), appUser.getPassword());

		if (!passwordMatches)
		{
			throw new AuthenticationFailureException(ErrorCode.AuthConstants.INVALID_USER_NAME_OR_PASSWORD,
					"Invalid Username or password");
		}
		if (!appUser.isEnabled())
		{
			throw new AuthenticationFailureException(ErrorCode.AuthConstants.USER_IS_DISABLED, "User is disabled.");
		}
		if (!appUser.isActive())
		{
			throw new AuthenticationFailureException(ErrorCode.AuthConstants.USER_IS_INACTIVE, "User is inactive.");
		}

		UserPrincipal userPrincipal = new UserPrincipal();
		userPrincipal.setAuthenticatedOn(new Date());
		userPrincipal.setExpirationTime(System.currentTimeMillis()+JWTConstants.SESSION_TIME_OUT);
		copyProperties(userPrincipal, appUser);

		if (!appUser.isLoginCreated())
		{
			userPrincipal.setFirstLogin(true);
		}

		for (UserRole current : appUser.getAssignedRoles())
		{
			userPrincipal.addRole(current.getRole());
		
		}
		
		StringBuilder roleBuilder = new StringBuilder();
		for (Role current : userPrincipal.getRoles())
		{
			roleBuilder.append(current.getName());
			roleBuilder.append(",");
		}

		String authToken = generateToken(userPrincipal.getId(), roleBuilder.toString());
		userPrincipal.setAuthToken(authToken);

		return userPrincipal;
	}

	@Override
	@Transactional(readOnly = false)
	public void updatePassword(LoginRequest loginRequest)
	{
		if (!loginRequest.getPassword().equals(loginRequest.getConfirmPassword()))
		{
			throw new InvalidArgumentException(ErrorCode.CommonConstants.PASSWORD_DOES_NOT_MATCH);
		}

		AppUser user = getById(AppUser.class, loginRequest.getUserId());
		if (user == null)
		{
			throw new ResourceNotFoundException(ErrorCode.ResourceNotFoundConstants.APPUSER_NOT_FOUND);
		}

		user.setPassword(passwordEncoder.encode(loginRequest.getConfirmPassword()));
		if (!user.isLoginCreated())
		{
			user.setLoginCreated(true);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Login getByUserId(Long userId)
	{
		return this.loginDAO.getByUserId(userId);
	}

	@Override
	@Transactional(readOnly = false)
	public void createPasswordResetRequest(PasswordResetRequest passwordResetRequest)
	{
		AppUser appUser = appUserDAO.findByEmail(passwordResetRequest.getEmail());
		if (appUser == null)
		{
			throw new InvalidArgumentException(ErrorCode.InvalidRequest.EMAIL_NOT_FOUND);
		}

		// check is user has already raised the reset password request
		List<PasswordResetRequest> existing = passwordResetRequestDAO.findByEmail(passwordResetRequest.getEmail());
		if (CollectionUtils.isNotEmpty(existing))// not
		// expired
		{
			throw new InvalidArgumentException(ErrorCode.InvalidRequest.RESET_PASSWORD_REQUEST_ALREADY_EXIST);
		}

		passwordResetRequest.setExpirationTime(System.currentTimeMillis() + PASSWORD_RESET_TOKEN_EXPIRATION_TIME_OUT);
		String token = UUID.randomUUID().toString();
		passwordResetRequest.setToken(passwordEncoder.encode(token));
		super.save(passwordResetRequest);

		// send email with link having the token.
		emailSender.sendResetPasswordRequestedEmail(passwordResetRequest.getEmail(), token);
	}

	@Override
	@Transactional(readOnly = false)
	public void resetPassword(PasswordResetRequest passwordResetRequest)
	{
		if (!passwordResetRequest.getPassword().equals(passwordResetRequest.getConfirmPassword()))
		{
			throw new InvalidArgumentException(ErrorCode.CommonConstants.PASSWORD_DOES_NOT_MATCH);
		}

		List<PasswordResetRequest> passwordResetRequests = passwordResetRequestDAO.findNonExpiredRequests();
		if (CollectionUtils.isEmpty(passwordResetRequests))
		{
			throw new InvalidArgumentException(ErrorCode.InvalidRequest.RESET_PASSWORD_REQUEST_EXPIRED);
		}

		String token = passwordResetRequest.getToken();
		for (PasswordResetRequest current : passwordResetRequests)
		{
			if (passwordEncoder.matches(token, current.getToken()))
			{
				AppUser appUser = appUserDAO.findByEmail(current.getEmail());
				appUser.setPassword(passwordEncoder.encode(passwordResetRequest.getPassword()));
				super.update(appUser);
				super.delete(current);

				// there won't be more than one non expired requests by one
				// email.
				break;
			}
		}

		// send email that password reset is success ful.
	}

	@Override
	@Transactional(readOnly = false)
	public void deleteLogin(Login login)
	{
		this.loginDAO.deleteLogin(login.getUserId(), login);
	}
	
	@Override
	public RefreshedToken refreshToken(String jwtToken)
	{
		Assert.notBlank(jwtToken, "'jwtToken' is required");
		Long userId  =(Long)CoreUtil.getClaim(JWTConstants.CLAIM_USER_ID, jwtToken);
		String rolesStr =(String) CoreUtil.getClaim(JWTConstants.CLAIM_ROLE, jwtToken);
		
		String refreshedToken = generateToken(userId, rolesStr);
		
		return new RefreshedToken(refreshedToken, System.currentTimeMillis()+JWTConstants.SESSION_TIME_OUT);
	}

	private void copyProperties(UserPrincipal userPrincipal, AppUser appUser)
	{
		userPrincipal.setFirstName(appUser.getFirstName());
		userPrincipal.setId(appUser.getId());
		userPrincipal.setLastName(appUser.getLastName());
		userPrincipal.setUserName(appUser.getUserName());
		userPrincipal.setMiddleName(appUser.getMiddleName());
		if (appUser.getProfilePicPath() != null)
		{
			File profilePic = new File(appUser.getProfilePicPath());
			userPrincipal.setProfilePicPath(profilePic.getName());
		}

	}
}
