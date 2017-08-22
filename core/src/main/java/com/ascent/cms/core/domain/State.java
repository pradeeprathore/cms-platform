package com.ascent.cms.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "state")
public class State
{
	public static final String COUNTRY = "country";

	public static final String NAME = "name";

	@NotNull(message = "state.id.notNull}")
	@Min(value = 1, message = "{state.id.min}")
	private Integer id;

	private String name;

	private Country country;

	@ManyToOne(optional = false)
	@JoinColumn(name = "Country_Id", referencedColumnName = "Id")
	@JsonIgnore
	public Country getCountry()
	{
		return country;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	public Integer getId()
	{
		return id;
	}

	@Column(name = "name", nullable = false, length = 75)
	public String getName()
	{
		return name;
	}

	public void setCountry(Country country)
	{
		this.country = country;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "State [id=" + id + ", name=" + name + ", country=" + country + "]";
	}

}
