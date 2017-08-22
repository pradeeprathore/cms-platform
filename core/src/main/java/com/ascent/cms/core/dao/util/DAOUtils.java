package com.ascent.cms.core.dao.util;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.constants.DatePattern;
import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.exception.InvalidArgumentException;
import com.ascent.cms.core.vo.AddressVO;

import com.ascent.cms.core.vo.AppUserVO;
import com.ascent.cms.core.vo.SearchCriteria;


public class DAOUtils
{
	private static final String ORDER_ASC = "asc";

	private static final Logger logger = Logger.getLogger(DAOUtils.class);

	public static void setPagination(Integer startPosition, Integer maxResult, Criteria criteria)
	{
		if (startPosition != null)
		{
			criteria.setFirstResult(startPosition);
		}
		if (maxResult != null)
		{
			criteria.setMaxResults(maxResult);
		}
	}

	public static void setPagination(Integer startPosition, Integer maxResult, Query query)
	{
		if (startPosition != null)
		{
			query.setFirstResult(startPosition);
		}
		if (maxResult != null)
		{
			query.setMaxResults(maxResult);
		}
	}

	public static Date parseDate(String dateToParse)
	{
		Date date = null;
		try
		{
			date = DateUtils.parseDate(dateToParse, DatePattern.INDIAN_SHORT.getPattern());
		} catch (ParseException e)
		{
			throw new InvalidArgumentException(ErrorCode.CommonConstants.INVALID_DATE);
		}
		return date;
	}

	public static Long getResultCount(Criteria criteria)
	{
		criteria.setProjection(Projections.rowCount());
		Long resultCount = (Long) criteria.uniqueResult();
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		return resultCount;
	}

	public static void setSortOrder(SearchCriteria searchCriteria, Criteria criteria, String sortField, String property)
	{
		if (searchCriteria.hasSortField(sortField))
		{
			String sortDirection = searchCriteria.getSortField(sortField);
			if (ORDER_ASC.equals(sortDirection))
			{
				criteria.addOrder(Order.asc(property));
			} else
			{
				criteria.addOrder(Order.desc(property));
			}
		}
	}

	public static void setSortOrder(SearchCriteria searchCriteria, Criteria criteria, String sortField, String prop1,
			String prop2)
	{
		if (searchCriteria.hasSortField(sortField))
		{
			String sortDirection = searchCriteria.getSortField(sortField);
			if (ORDER_ASC.equals(sortDirection))
			{
				criteria.addOrder(Order.asc(prop1)).addOrder(Order.asc(prop2));
			} else
			{
				criteria.addOrder(Order.desc(prop1)).addOrder(Order.desc(prop2));
			}
		}
	}

	

	

	

	

	public static Date removeTimePart(Date dateToTrimm)
	{
		dateToTrimm = DateUtils.setHours(dateToTrimm, 0);
		dateToTrimm = DateUtils.setMinutes(dateToTrimm, 0);
		dateToTrimm = DateUtils.setSeconds(dateToTrimm, 0);
		return dateToTrimm;
	}

	public static Long parseRegistrationId(String idStr)
	{
		Long id = null;
		try
		{
			id = Long.parseLong(idStr);
		} catch (NumberFormatException e)
		{
			throw new InvalidArgumentException(ErrorCode.CommonConstants.INVALID_REGISTRATION_ID);
		}
		return id;
	}

	public static Long parseInvoiceId(String invoiceIdStr)
	{
		Long id = null;
		try
		{
			id = Long.parseLong(invoiceIdStr);
		} catch (NumberFormatException e)
		{
			throw new InvalidArgumentException(ErrorCode.CommonConstants.INVALID_INVOICE_ID);
		}
		return id;
	}

	public static Float parseDefaultPrice(String defaultPriceStr)
	{
		Float defaultPrice = null;
		try
		{
			if (defaultPriceStr == "")
			{
				defaultPrice = null;
			} else
			{
				defaultPrice = Float.valueOf(defaultPriceStr);
			}

		} catch (NumberFormatException e)
		{
			throw new InvalidArgumentException(ErrorCode.CommonConstants.INVALID_DEFAULT_PRICE);
		}
		return defaultPrice;
	}

}
