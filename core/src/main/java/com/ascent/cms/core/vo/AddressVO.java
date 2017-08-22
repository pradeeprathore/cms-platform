package com.ascent.cms.core.vo;

import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import com.ascent.cms.core.constraint.Alpha;
import com.ascent.cms.core.constraint.AlphaNumeric;
import com.ascent.cms.core.constraint.NotHtml;
import com.ascent.cms.core.domain.Country;
import com.ascent.cms.core.domain.State;

public class AddressVO
{

	private Long id;

	@Size(max = 255, message = "{address.addressLine1.size}")
	@NotHtml(message = "{address.addressLine1.notHtml}")
	private String addressLine1;

	@Size(max = 255, message = "{address.addressLine2.size}")
	@NotHtml(message = "{address.addressLine2.notHtml}")
	private String addressLine2;

	@Size(max = 50, message = "{address.area.size}")
	@AlphaNumeric(message = "{address.area.alphaNumeric}")
	private String area;

	@Size(max = 50, message = "{address.city.size}")
	@Alpha(message = "{address.city.alpha}")
	private String city;

	@Valid
	private State state;

	@Valid
	private Country country;

	@Size(max = 6, min = 6, message = "{address.zipCode.digit}")
	@Digits(integer = 6, fraction = 0, message = "{address.zipCode.digit}")
	private String zipCode;

	public String getAddressLine1()
	{
		return addressLine1;
	}

	public String getAddressLine2()
	{
		return addressLine2;
	}

	public String getCity()
	{
		return city;
	}

	public Country getCountry()
	{
		return country;
	}

	public Long getId()
	{
		return id;
	}

	public State getState()
	{
		return state;
	}

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
