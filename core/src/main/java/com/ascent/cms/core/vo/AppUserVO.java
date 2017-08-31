package com.ascent.cms.core.vo;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.validator.constraints.NotBlank;

import com.ascent.cms.core.constraint.Alpha;
import com.ascent.cms.core.constraint.AlphaNumeric;
import com.ascent.cms.core.constraint.NotHtml;
import com.ascent.cms.core.util.JsonDateDeserializer;

public class AppUserVO
{

	private Long id;

	@NotBlank(message = "{patient.firstName.notBlank}")
	@Size(max = 30, message = "{patient.firstName.size}")
	@Alpha(message = "{patient.firstName.regex}")
	private String firstName;

	@NotBlank(message = "{patient.lastName.notBlank}")
	@Size(max = 30, message = "{patient.lastName.size}")
	@Alpha(message = "{patient.lastName.regex}")
	private String lastName;

	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "{patient.email.email}")
	private String email;

	@NotBlank(message = "{patient.mobile.notBlank}")
	@Size(max = 11, min = 10, message = "{patient.mobile.size}")
	@Pattern(regexp = "[0-9]+", message = "{patient.mobile.regex}")
	private String mobile;

	// @NotBlank(message = "{patient.sex.notBlank}")
	@Size(max = 25, message = "{patient.sex.size}")
	@Alpha(message = "{patient.sex.regex}")
	private String sex;

	//@NotNull(message = "{patient.dateOfBirth.notNull}")
	@Past(message = "{patient.dateOfBirth.past}")
	private Date dateOfBirth;

	@Size(max = 11, min = 10, message = "{patient.homePhone.size}")
	@Pattern(regexp = "[0-9]+", message = "{patient.homePhone.regex}")
	private String homePhone;

	@Size(max = 25, message = "{patient.bloodGroup.size}")
	private String bloodGroup;

	// @NotNull(message = "{patient.address.notNull}")
	@Valid
	private AddressVO address;

	@Size(max = 25, message = "{patient.saluation.size}")
	@Alpha(message = "{patient.saluation.regex}")
	private String saluation;

	@Size(max = 30, message = "{patient.middleName.size}")
	@Alpha(message = "{patient.middleName.regex}")
	private String middleName;

	@Size(max = 20, message = "{patient.uniqueId.size}")
	private String uniqueId;

	@Size(max = 50, message = "{patient.maritalStatus.size}")
	@Alpha(message = "{patient.maritalStatus.regex}")
	private String maritalStatus;

	@Size(max = 11, min = 10, message = "{patient.otherContactNumber.size}")
	@Pattern(regexp = "[0-9]+", message = "{patient.otherContactNumber.regex}")
	private String otherContactNumber;

	@Size(max = 255, message = "{patient.profilePicPath.size}")
	private String profilePicPath;
	private String profilePicName;

	@Size(max = 255, message = "{patient.interest.size}")
	@AlphaNumeric(message = "{patient.interest.alphaNumeric}")
	private String interest;

	@Size(max = 100, message = "{patient.languages.size}")
	@Alpha(message = "{patient.languages.regex}")
	private String languages;

	@Size(max = 255, message = "{patient.notes.size}")
	@NotHtml(message = "{patient.notes.notHtml}")
	private String notes;

	@Pattern(regexp = "[0-9]+", message = "{patient.age.regex}")
	private String age;

	public AppUserVO()
	{
		this(null);
	}

	public AppUserVO(Long id)
	{
		this.id = id;
	}

	public AppUserVO(Long id, String firstName, String lastName)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public AppUserVO(Long id, String firstName, String lastName, String mobile)
	{
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
	}

	

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}

	public Date getDateOfBirth()
	{
		return dateOfBirth;
	}

	@JsonDeserialize(using = JsonDateDeserializer.class)
	public void setDateOfBirth(Date dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

	public String getHomePhone()
	{
		return homePhone;
	}

	public void setHomePhone(String homePhone)
	{
		this.homePhone = homePhone;
	}

	public String getBloodGroup()
	{
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup)
	{
		this.bloodGroup = bloodGroup;
	}

	public AddressVO getAddress()
	{
		return address;
	}

	public void setAddress(AddressVO address)
	{
		this.address = address;
	}

	public String getSaluation()
	{
		return saluation;
	}

	public void setSaluation(String saluation)
	{
		this.saluation = saluation;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getUniqueId()
	{
		return uniqueId;
	}

	public void setUniqueId(String uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	public String getMaritalStatus()
	{
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus)
	{
		this.maritalStatus = maritalStatus;
	}

	public String getOtherContactNumber()
	{
		return otherContactNumber;
	}

	public void setOtherContactNumber(String otherContactNumber)
	{
		this.otherContactNumber = otherContactNumber;
	}

	public String getProfilePicPath()
	{
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath)
	{
		this.profilePicPath = profilePicPath;
	}

	public String getProfilePicName() {
		return profilePicName;
	}

	public void setProfilePicName(String profilePicName) {
		this.profilePicName = profilePicName;
	}

	

	public String getNotes()
	{
		return notes;
	}

	public void setNotes(String notes)
	{
		this.notes = notes;
	}

	public String getInterest()
	{
		return interest;
	}

	public void setInterest(String interest)
	{
		this.interest = interest;
	}

	public String getAge()
	{
		return age;
	}

	public void setAge(String age)
	{
		this.age = age;
	}

	public String getLanguages()
	{
		return languages;
	}

	public void setLanguages(String languages)
	{
		this.languages = languages;
	}



}
