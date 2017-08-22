package com.ascent.cms.services;

import java.io.Serializable;

import org.hibernate.LockMode;

import com.ascent.cms.core.dao.BaseDAO;

public interface BaseService
{
	public static final String NAME = "name";

	public static final String SPECIALIZATION = "specialization";

	<T> T save(T entity);

	<T> T update(T entity);

	<T> void delete(T entity);

	<T> T saveOrUpdate(T entity);

	<T> T getById(Class<T> entityClass, Serializable id);

	public abstract void setBaseDAO(BaseDAO baseDAO);

	void flush();

	void clear();

	<T> T getByIdWithLockMode(Class<T> entityClass, Serializable id, LockMode lockMode);

	void setSessionReadOnly();

}
