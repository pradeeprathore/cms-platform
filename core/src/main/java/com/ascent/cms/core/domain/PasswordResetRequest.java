package com.ascent.cms.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import com.ascent.cms.core.constants.Patterns;
import com.ascent.cms.core.constraint.UpdateEntity;

@Entity
@Table(name = "password_reset_request")
public class PasswordResetRequest
{
	public static final String EMAIL = "email";

	public static final String EXPIRATION_TIME = "expirationTime";

	public static final String TOKEN = "token";

	private Integer id;

	private Long expirationTime;

	@NotBlank(message = "{passwordResetRequest.email.notBlank}")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{passwordResetRequest.email.email}")
	private String email;

	@NotBlank(groups = { UpdateEntity.class }, message = "{passwordResetRequest.token.notBlank}")
	private String token;

	@NotBlank(groups = { UpdateEntity.class }, message = "{passwordResetRequest.password.notBlank}")
	@Pattern(groups = { UpdateEntity.class }, regexp = Patterns.PASSWORD_REGEX, message = "{passwordResetRequest.password.pattern}")
	private String password;

	@NotBlank(groups = { UpdateEntity.class }, message = "{passwordResetRequest.confirmPassword.notBlank}")
	private String confirmPassword;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	@Column(name = "Expiration_Time", nullable = false)
	public Long getExpirationTime()
	{
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime)
	{
		this.expirationTime = expirationTime;
	}

	@Column(name = "Email", nullable = false)
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	@Column(name = "Token", nullable = false)
	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	@Transient
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Transient
	public String getConfirmPassword()
	{
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword)
	{
		this.confirmPassword = confirmPassword;
	}

}
