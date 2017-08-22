package com.ascent.cms.core.constants;

public enum AccessLevel
{
	NULL_ACCESS(-1), LEVEL1(1), LEVEL2(2), LEVEL3(3), LEVEL4(4);

	AccessLevel(int precedence)
	{
		this.precedence = precedence;
	}

	private int precedence;

	public int getPrecedence()
	{
		return precedence;
	}

}
