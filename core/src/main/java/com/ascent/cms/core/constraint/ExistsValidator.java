package com.ascent.cms.core.constraint;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.ReflectionUtils;

import com.ascent.cms.core.dao.CountryDAO;
import com.ascent.cms.core.dao.impl.CountryDAOImpl;

public class ExistsValidator implements ConstraintValidator<Exists, Object>
{

	private static final String ID = "id";

	// TODO: Need to improve this.
	private CountryDAO countryDAO = CountryDAOImpl.getInstance();

	@Override
	public void initialize(Exists constraintAnnotation)
	{

	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context)
	{
		if (value == null)
		{
			return true;
		}

		Field idField = ReflectionUtils.findField(value.getClass(), ID);
		if (idField == null)
		{
			return false;
		}

		ReflectionUtils.makeAccessible(idField);

		Serializable id = (Serializable) ReflectionUtils.getField(idField, value);
		Object found = countryDAO.getById(value.getClass(), id, null);
		return found != null;
	}

}