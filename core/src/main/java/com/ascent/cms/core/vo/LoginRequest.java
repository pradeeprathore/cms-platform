package com.ascent.cms.core.vo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.NotBlank;

import com.ascent.cms.core.constants.Patterns;
import com.ascent.cms.core.constraint.UpdateEntity;

public class LoginRequest
{

	private Long userId;

	//@NotBlank(message = "{loginRequest.userName.notBlank}")
	//@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{loginRequest.userName.email}")
	private String userName;
	
	@NotBlank(message = "{loginRequest.mobile.notBlank}")
	//@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{loginRequest.userName.email}")
	private String mobile;

	@NotBlank(groups = { UpdateEntity.class, Default.class }, message = "{loginRequest.password.notBlank}")
	@Size(groups = { UpdateEntity.class }, max = 15, message = "{loginRequest.password.size}")
	@Pattern(groups = { UpdateEntity.class }, regexp = Patterns.PASSWORD_REGEX, message = "{loginRequest.password.pattern}")
	private String password;

	@NotBlank(groups = { UpdateEntity.class }, message = "{loginRequest.confirmPassword.notBlank}")
	@Size(groups = { UpdateEntity.class }, max = 15, message = "{loginRequest.confirmPassword.size}")
	private String confirmPassword;

	@NotBlank(groups = { UpdateEntity.class }, message = "{loginRequest.oldPassword.notBlank}")
	@Size(groups = { UpdateEntity.class }, max = 15, message = "{loginRequest.oldPassword.size}")
	private String oldPassword;

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

	public String getOldPassword()
	{
		return oldPassword;
	}

	public void setOldPassword(String oldPassword)
	{
		this.oldPassword = oldPassword;
	}

	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}
	
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

}
