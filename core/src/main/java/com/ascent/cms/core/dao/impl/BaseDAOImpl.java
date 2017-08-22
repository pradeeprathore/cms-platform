package com.ascent.cms.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ascent.cms.core.constants.DetailMode;
import com.ascent.cms.core.dao.BaseDAO;
import com.ascent.cms.core.dao.util.DAOUtils;
import com.ascent.cms.core.util.Assert;

@Repository(value = "baseDAO")
public class BaseDAOImpl implements BaseDAO
{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public <T> T save(T entity)
	{
		Assert.notNull(entity, "'entity' is required");

		this.getCurrentSession().save(entity);
		return entity;
	}

	@Override
	public <T> T update(T entity)
	{
		Assert.notNull(entity, "'entity' is required");

		this.getCurrentSession().update(entity);
		return entity;
	}

	@Override
	public <T> void delete(T entity)
	{
		Assert.notNull(entity, "'entity' is required");
		this.getCurrentSession().delete(entity);
	}

	@Override
	public <T> T saveOrUpdate(T entity)
	{
		Assert.notNull(entity, "'entity' is required");
		this.getCurrentSession().saveOrUpdate(entity);
		return entity;
	}

	@Override
	public <T> T getById(Class<T> entityClass, Serializable id, DetailMode detailMode)
	{
		Assert.notNull(entityClass, "'entityClass' is required");
		Assert.notNull(id, "'id' is required");

		@SuppressWarnings("unchecked")
		T obj = (T) getCurrentSession().get(entityClass, id);
		return obj;
	}

	@Override
	public <T> T getByIdWithLockMode(Class<T> entityClass, Serializable id, LockMode lockMode)
	{
		Assert.notNull(entityClass, "'entityClass' is required");
		Assert.notNull(id, "'id' is required");
		Assert.notNull(lockMode, "'lockMode' is required");

		@SuppressWarnings("unchecked")
		T obj = (T) getCurrentSession().get(entityClass, id, lockMode);
		return obj;
	}

	protected Session getCurrentSession()
	{
		return this.sessionFactory.getCurrentSession();
	}

	protected <T> List<T> getAll(final String queryStr)
	{
		Assert.notBlank(queryStr, "'queryStr' is required");

		Query query = createQuery(queryStr);
		@SuppressWarnings("unchecked")
		List<T> result = query.list();
		return result;
	}

	protected <T> List<T> getAll(final String queryStr, Integer startPosition, Integer maxResults)
	{
		Assert.notBlank(queryStr, "'queryStr' is required");

		Query query = createQuery(queryStr);
		DAOUtils.setPagination(startPosition, maxResults, query);

		@SuppressWarnings("unchecked")
		List<T> result = query.list();
		return result;
	}

	protected Criteria createCriteria(@SuppressWarnings("rawtypes") Class clazz)
	{
		Assert.notNull(clazz, "'clazz' is required");
		return getCurrentSession().createCriteria(clazz);
	}

	protected Query createQuery(final String hql)
	{
		Assert.notBlank(hql, "'hql' is required");
		return getCurrentSession().createQuery(hql);
	}

	@Override
	public void flush()
	{

		getCurrentSession().flush();
	}

	@Override
	public void clear()
	{
		getCurrentSession().clear();
	}

	@Override
	public void lock(Object object, LockMode lockMode)
	{
		getCurrentSession().lock(object, lockMode);
	}

	@Override
	public void unlock(Object object)
	{
		getCurrentSession().lock(object, LockMode.NONE);
	}

	@Override
	public void setSessionReadOnly()
	{
		getCurrentSession().setDefaultReadOnly(true);

	}

}
