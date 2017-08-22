package com.ascent.cms.core.util;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ascent.cms.core.exception.ValidationFailureException;

@Component
public class BeanValidatorImpl implements BeanValidator
{

	private static final Logger LOGGER = Logger.getLogger(BeanValidatorImpl.class);

	private final Validator validator;

	public BeanValidatorImpl()
	{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		this.validator = factory.getValidator();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ascent.cms.web.util.BeanValidator#validate(T)
	 */
	@Override
	public <T> void validate(T model, Class<?>... groups)
	{

		Set<ConstraintViolation<T>> constraintViolations = validator.validate(model, groups);

		if (CollectionUtils.isNotEmpty(constraintViolations))
		{
			Set<ConstraintViolation<?>> propagatedViolations = new HashSet<ConstraintViolation<?>>(
					constraintViolations.size());
			propagatedViolations.addAll(constraintViolations);
			if (LOGGER.isDebugEnabled())
			{
				LOGGER.debug("ConstraintViolations " + constraintViolations);
			}
			throw new ValidationFailureException(null, propagatedViolations);
		}
	}
}
