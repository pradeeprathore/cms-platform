package com.ascent.cms.core.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class AlphaValidator implements ConstraintValidator<Alpha, String>
{

	@Override
	public void initialize(Alpha constraintAnnotation)
	{

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		if (StringUtils.isBlank(value))
		{
			return true;
		}
		if (StringUtils.isAlphaSpace(value))
		{
			return true;
		}
		return false;
	}

}
