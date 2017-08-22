package com.ascent.cms.core.dao;

import java.io.Serializable;

import org.hibernate.LockMode;

import com.ascent.cms.core.constants.DetailMode;

public interface BaseDAO
{

	<T> T save(T entity);

	<T> T update(T entity);

	<T> void delete(T entity);

	<T> T saveOrUpdate(T entity);

	<T> T getById(Class<T> entityClass, Serializable id, DetailMode detailMode);

	void flush();

	void clear();

	<T> T getByIdWithLockMode(Class<T> entityClass, Serializable id, LockMode lockMode);

	public abstract void lock(Object object, LockMode lockMode);

	public abstract void unlock(Object object);

	void setSessionReadOnly();

}
