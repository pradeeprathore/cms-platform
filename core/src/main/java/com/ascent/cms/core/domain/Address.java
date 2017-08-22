package com.ascent.cms.core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.validator.constraints.NotBlank;

import com.ascent.cms.core.constraint.Alpha;
import com.ascent.cms.core.constraint.AlphaNumeric;
import com.ascent.cms.core.constraint.NotHtml;

@Entity
@Table(name = "address")
@FetchProfile(name = "address_fetch_profile", fetchOverrides = {
		@FetchProfile.FetchOverride(entity = Address.class, association = "state", mode = FetchMode.JOIN),
		@FetchProfile.FetchOverride(entity = Address.class, association = "country", mode = FetchMode.JOIN) })
public class Address
{
	public static final String ADDRESS_FETCH_ROFILE = "address_fetch_profile";

	public static final String AREA = "area";

	public static final String CITY = "city";

	public static final String STATE = "state";

	public static final String COUNTRY = "country";

	private Long id;

	@NotBlank(message = "{address.addressLine1.notBlank}")
	@Size(max = 255, message = "{address.addressLine1.size}")
	@NotHtml(message = "{address.addressLine1.notHtml}")
	private String addressLine1;

	@Size(max = 255, message = "{address.addressLine2.size}")
	@NotHtml(message = "{address.addressLine2.notHtml}")
	private String addressLine2;

	@NotBlank(message = "{address.area.notBlank}")
	@Size(max = 50, message = "{address.area.size}")
	@AlphaNumeric(message = "{address.area.alphaNumeric}")
	private String area;

	@NotBlank(message = "{address.city.notBlank}")
	@Size(max = 50, message = "{address.city.size}")
	@Alpha(message = "{address.city.alpha}")
	private String city;

	@NotNull(message = "{address.state.notNull}")
	@Valid
	private State state;

	@NotNull(message = "{address.country.notNull}")
	@Valid
	private Country country;

	@NotBlank(message = "{address.zipCode.notBlank}")
	@Size(max = 6, min = 6, message = "{address.zipCode.digit}")
	@Digits(integer = 6, fraction = 0, message = "{address.zipCode.digit}")
	private String zipCode;

	@Column(name = "Address_Line1", length = 255)
	public String getAddressLine1()
	{
		return addressLine1;
	}

	@Column(name = "Address_Line2", length = 255)
	public String getAddressLine2()
	{
		return addressLine2;
	}

	@Column(name = "City", length = 50, nullable = false)
	public String getCity()
	{
		return city;
	}

	@ManyToOne(fetch = FetchType.EAGER,  optional = true)
	@JoinColumn(name = "Country_Id", referencedColumnName = "Id")
	public Country getCountry()
	{
		return country;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	public Long getId()
	{
		return id;
	}

	@ManyToOne(fetch = FetchType.EAGER,optional=true)
	@JoinColumn(name = "State_Id", referencedColumnName = "Id")
	public State getState()
	{
		return state;
	}

	@Column(name = "Zip_Code", length = 6)
	public String getZipCode()
	{
		return zipCode;
	}

	public void setAddressLine1(String addressLine1)
	{
		this.addressLine1 = addressLine1;
	}

	public void setAddressLine2(String addressLine2)
	{
		this.addressLine2 = addressLine2;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public void setCountry(Country country)
	{
		this.country = country;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setState(State state)
	{
		this.state = state;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode = zipCode;
	}

	@Column(name = "Area", nullable = false, length = 50)
	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	@Override
	public String toString()
	{
		return "Address [id=" + id + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", area="
				+ area + ", city=" + city + ", state=" + state + ", country=" + country + ", zipCode=" + zipCode + "]";
	}

}
