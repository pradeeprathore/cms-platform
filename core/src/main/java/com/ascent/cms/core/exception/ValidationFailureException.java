package com.ascent.cms.core.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;

public class ValidationFailureException extends CoreRuntimeException
{

	private static final long serialVersionUID = 1L;

	private final Set<ConstraintViolation<?>> constraintViolations;

	public <T> ValidationFailureException(String errorCode, Set<ConstraintViolation<?>> constraintViolations)
	{
		super(errorCode);
		this.constraintViolations = constraintViolations;
	}

	public Set<ConstraintViolation<?>> getConstraintViolations()
	{
		return constraintViolations;
	}

}
