package com.ascent.cms.core.constants;

public enum BloodGroup
{
	A_POSITIVE("A+"), B_POSITIVE("B+"), AB_POSITIVE("AB+"), O_POSITIVE("O+"), A_NEGATIVE("A-"), B_NEGATIVE("B-"), AB_NEGATIVE(
			"AB-"), O_NEGATIVE("O-");

	private String group;

	private BloodGroup(String group)
	{
		this.group = group;
	}

	public String getGroup()
	{
		return group;
	}

}
