package com.ascent.cms.core.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

@Entity
@Table(name = "user_role")
@FetchProfile(name = "fp_userRole_role", fetchOverrides = { @FetchProfile.FetchOverride(entity = UserRole.class, association = "role", mode = FetchMode.JOIN) })
public class UserRole
{

	private Long id;

	private AppUser appUser;

	private Role role;

	public static final String FP_USER_ROLE_ROLE = "fp_userRole_role";

	public static final String APP_USER = "appUser";

	public static final String ROLE = "role";

	public UserRole()
	{

	}

	public UserRole(Role role)
	{
		super();
		this.role = role;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "App_User_Id", referencedColumnName = "Id")
	public AppUser getAppUser()
	{
		return appUser;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	public Long getId()
	{
		return id;
	}

	@ManyToOne(optional = false)
	@JoinColumn(name = "Role_Id", referencedColumnName = "Id")
	public Role getRole()
	{
		return role;
	}

	public void setAppUser(AppUser appUser)
	{
		this.appUser = appUser;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

}
