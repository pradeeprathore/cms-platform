package com.ascent.cms.services.impl;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.LockMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.dao.BaseDAO;
import com.ascent.cms.core.exception.InvalidArgumentException;
import com.ascent.cms.core.util.BeanValidator;
import com.ascent.cms.services.BaseService;

@Transactional
public class BaseServiceImpl implements BaseService
{

	@Autowired
	private BaseDAO baseDAO;

	@Autowired
	private BeanValidator beanValidator;

	@Override
	public void setBaseDAO(BaseDAO baseDAO)
	{
		this.baseDAO = baseDAO;
	}

	@Override
	public <T> T save(T entity)
	{
		T saved = baseDAO.save(entity);
		return saved;
	}

	@Override
	public <T> T update(T entity)
	{
		return baseDAO.update(entity);
	}

	@Override
	public <T> void delete(T entity)
	{
		baseDAO.delete(entity);
	}

	@Override
	public <T> T saveOrUpdate(T entity)
	{
		return baseDAO.saveOrUpdate(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public <T> T getById(Class<T> entityClass, Serializable id)
	{
		return baseDAO.getById(entityClass, id, null);
	}

	@Override
	@Transactional(readOnly = true)
	public <T> T getByIdWithLockMode(Class<T> entityClass, Serializable id, LockMode lockMode)
	{
		return baseDAO.getByIdWithLockMode(entityClass, id, lockMode);
	}

	@Override
	public void flush()
	{
		this.baseDAO.flush();
	}

	@Override
	public void clear()
	{
		this.baseDAO.clear();
	}

	protected void validateName(String aName)
	{
		if (StringUtils.isBlank(aName))
		{
			throw new InvalidArgumentException(ErrorCode.CommonConstants.REQUIRED_PARAM_MISSING, NAME);
		}
	}



	protected <T> void validate(final T model, Class<?>... groups)
	{
		this.beanValidator.validate(model, groups);
	}

	@Override
	public void setSessionReadOnly()
	{
		this.baseDAO.setSessionReadOnly();
	}

}
