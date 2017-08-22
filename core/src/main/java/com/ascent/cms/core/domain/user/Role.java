package com.ascent.cms.core.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "role")
public class Role
{
	public static final String NAME = "name";

	private Short id;

	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	public Short getId()
	{
		return id;
	}

	@NotBlank
	@Size(max = 50)
	@Column(name = "Name", length = 50, nullable = false)
	public String getName()
	{
		return name;
	}

	public void setId(Short id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
