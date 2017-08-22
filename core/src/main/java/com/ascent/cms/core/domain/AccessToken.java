package com.ascent.cms.core.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ascent.cms.core.domain.user.AppUser;

@Entity
@Table(name = "access_token")
public class AccessToken
{
	public static final String TOKEN = "token";

	public static final String APP_USER= "appuser";

	private Long id;

	private AppUser appuser;

	private String token;

	private Date createdOn;

	public AccessToken()
	{

	}

	public AccessToken(Long id)
	{
		this.id = id;
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

	@ManyToOne(optional = false)
	@JoinColumn(name = "App_User_Id", referencedColumnName = "Id")
	public AppUser getPatient()
	{
		return appuser;
	}

	public void setPatient(AppUser appUser)
	{
		this.appuser = appUser;
	}

	@Column(name = "Token", nullable = false, length = 15)
	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	@Column(name = "Created_On", nullable = false)
	public Date getCreatedOn()
	{
		return createdOn;
	}

	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}

}
