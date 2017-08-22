package com.ascent.cms.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login")
public class Login
{
	public static final String AUTH_TOKEN = "authToken";

	public static final String USER_ID = "userId";

	private Long id;

	private Long userId;

	private String authToken;

	private Long lastLoginTime;

	private String role;

	public Login()
	{

	}

	public Login(Long userId, String authToken, Long lastLoginTime, String role)
	{
		super();
		this.userId = userId;
		this.authToken = authToken;
		this.lastLoginTime = lastLoginTime;
		this.role = role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(name = "User_Id", nullable = false)
	public Long getUserId()
	{
		return userId;
	}

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	@Column(name = "Auth_Token", nullable = false, length = 100)
	public String getAuthToken()
	{
		return authToken;
	}

	public void setAuthToken(String authToken)
	{
		this.authToken = authToken;
	}

	@Column(name = "Last_Login_Time", nullable = false)
	public Long getLastLoginTime()
	{
		return lastLoginTime;
	}

	public void setLastLoginTime(Long lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

	@Column(name = "Role", nullable = false)
	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

}
