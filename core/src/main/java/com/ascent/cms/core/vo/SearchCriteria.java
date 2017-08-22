package com.ascent.cms.core.vo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.ascent.cms.core.constants.SearchScope;

public class SearchCriteria
{

	// pagination
	private Integer startPosition;

	private Integer maxResult;

	// search
	private Map<String, String> searchFields;
	// sort

	private Map<String, String> sortFields;

	private SearchScope searchScope;

	public SearchCriteria()
	{
		this(null, null);
	}

	public SearchCriteria(Integer startPosition, Integer maxResult)
	{
		this.startPosition = startPosition;
		this.maxResult = maxResult;
		this.searchFields = new HashMap<String, String>();
		this.sortFields = new HashMap<String, String>();
	}

	// add searhField

	public SearchCriteria addSearchField(String fieldName, String value)
	{
		this.searchFields.put(StringUtils.trim(fieldName), StringUtils.trim(value));
		return this;
	}

	public SearchCriteria addSortField(String fieldName, String value)
	{
		this.sortFields.put(StringUtils.trim(fieldName), StringUtils.trim(value));
		return this;
	}

	// setters

	public void setStartPosition(Integer startPosition)
	{
		this.startPosition = startPosition;
	}

	public void setMaxResult(Integer maxResult)
	{
		this.maxResult = maxResult;
	}

	// getters

	public Integer getStartPosition()
	{
		return startPosition;
	}

	public Integer getMaxResult()
	{
		return maxResult;
	}

	public Map<String, String> getSearchFields()
	{
		return searchFields;
	}

	public boolean hasSearchField(final String fieldName)
	{
		if (getSearchFields().containsKey(fieldName))
		{
			return true;
		}
		return false;
	}

	public String getSearchField(final String fieldName)
	{
		return getSearchFields().get(fieldName);
	}

	public Map<String, String> getSortFields()
	{
		return sortFields;
	}

	public boolean hasSortField(final String fieldName)
	{
		if (getSortFields().containsKey(fieldName))
		{
			return true;
		}
		return false;
	}

	public String getSortField(final String fieldName)
	{
		return getSortFields().get(fieldName);
	}

	public boolean needToSort()
	{
		return getSortFields().isEmpty() == false;
	}

	@Override
	public String toString()
	{
		return ToStringBuilder.reflectionToString(this);
	}

	public SearchScope getSearchScope()
	{
		return searchScope;
	}

	public void setSearchScope(SearchScope searchScope)
	{
		this.searchScope = searchScope;
	}

}
