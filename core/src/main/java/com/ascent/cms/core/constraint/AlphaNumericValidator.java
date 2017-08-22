package com.ascent.cms.core.constraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class AlphaNumericValidator implements ConstraintValidator<AlphaNumeric, String>
{

	@Override
	public void initialize(AlphaNumeric constraintAnnotation)
	{

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context)
	{
		if (StringUtils.isBlank(value))
		{
			return true;
		}
		if (StringUtils.isNumeric(value))
		{
			return false;
		}
		if (StringUtils.isAlphanumericSpace(value))
		{
			return true;
		}
		return false;
	}

}
