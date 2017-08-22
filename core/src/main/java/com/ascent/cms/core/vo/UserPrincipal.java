package com.ascent.cms.core.vo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.ascent.cms.core.constants.AccessLevel;
import com.ascent.cms.core.domain.user.Role;

public class UserPrincipal
{

	private Long id;

	private Long doctorId;
	
	private Integer clinicId;

	private Date authenticatedOn;

	private String authToken;

	private String firstName;

	private String middleName;

	private String lastName;

	private String userName;
	
	private String profilePicPath;

	private String doctorName;

	private Set<Role> roles;

	private boolean firstLogin;

	private AccessLevel accessLevel;

	private Boolean billingEnabled;

	private String labName;
	
	private String clinicName;

	private String medicalShopName;
	
	private long expirationTime;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getAuthenticatedOn()
	{
		return authenticatedOn;
	}

	public void setAuthenticatedOn(Date authenticatedOn)
	{
		this.authenticatedOn = authenticatedOn;
	}

	public String getAuthToken()
	{
		return authToken;
	}

	public void setAuthToken(String authToken)
	{
		this.authToken = authToken;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	public Set<Role> getRoles()
	{
		return roles;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}

	public void addRole(Role role)
	{
		if (this.roles == null)
		{
			this.roles = new HashSet<Role>();
		}
		this.roles.add(role);
	}

	public boolean isFirstLogin()
	{
		return firstLogin;
	}

	public void setFirstLogin(boolean firstLogin)
	{
		this.firstLogin = firstLogin;
	}

	public AccessLevel getAccessLevel()
	{
		return accessLevel;
	}

	public void setAccessLevel(AccessLevel accessLevel)
	{
		this.accessLevel = accessLevel;
	}

	public String getLabName()
	{
		return labName;
	}

	public void setLabName(String labName)
	{
		this.labName = labName;
	}

	public String getMedicalShopName()
	{
		return medicalShopName;
	}

	public void setMedicalShopName(String medicalShopName)
	{
		this.medicalShopName = medicalShopName;
	}

	public Long getDoctorId()
	{
		return doctorId;
	}

	public void setDoctorId(Long doctorId)
	{
		this.doctorId = doctorId;
	}

	public String getDoctorName()
	{
		return doctorName;
	}

	public void setDoctorName(String doctorName)
	{
		this.doctorName = doctorName;
	}

	public Boolean getBillingEnabled()
	{
		return billingEnabled;
	}

	public void setBillingEnabled(Boolean billingEnabled)
	{
		this.billingEnabled = billingEnabled;
	}
	
	public String getClinicName() {
		return clinicName;
	}

	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}

	public Integer getClinicId() {
		return clinicId;
	}

	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}

	public long getExpirationTime()
	{
		return expirationTime;
	}

	public void setExpirationTime(long expirationTime)
	{
		this.expirationTime = expirationTime;
	}
	
	
	
	
}
