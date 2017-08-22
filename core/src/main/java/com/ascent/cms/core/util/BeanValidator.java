package com.ascent.cms.core.util;

public interface BeanValidator
{

	public abstract <T> void validate(T model, Class<?>... groups);

}