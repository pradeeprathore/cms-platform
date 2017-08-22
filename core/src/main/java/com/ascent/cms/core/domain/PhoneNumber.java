package com.ascent.cms.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "phone_number")
public class PhoneNumber implements Serializable
{

	private static final long serialVersionUID = -3575472790200531910L;

	private Long id;

	@NotBlank(message = "{phoneNumber.phoneNumber.notBlank}")
	@Size(max = 11, min = 10, message = "{phoneNumber.phoneNumber.size}")
	@Pattern(regexp = "[0-9]+", message = "{phoneNumber.phoneNumber.regex}")
	private String phoneNumber;

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

	@Column(name = "Phone_Number", nullable = false, length = 13)
	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

}
