package com.ascent.cms.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ascent.cms.core.vo.SearchCriteria;
import com.ascent.cms.web.util.WebUtils;

public class QueryParameterSettingInterceptor extends HandlerInterceptorAdapter
{

	private static final String I_SORT_COL = "iSortCol_";

	private static final String S_SORT_DIR = "sSortDir_";

	private static final String S_SEARCH = "sSearch_";

	private static final String B_SEARCHABLE = "bSearchable_";

	private static final String M_DATA_PROP = "mDataProp_";

	private static final String I_COLUMNS = "iColumns";

	private static final String I_DISPLAY_LENGTH = "iDisplayLength";

	private static final String I_DISPLAY_START = "iDisplayStart";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (!HttpMethod.GET.toString().equals(request.getMethod()))
		{
			return true;
		}

		SearchCriteria searchRequest = new SearchCriteria();
		// extract pagination related params
		setPaginationParams(request, searchRequest);

		Integer totalField = WebUtils.getRequestParamAsInt(request, I_COLUMNS);
		if (totalField != null && totalField > 0)
		{
			Map<Integer, String> fieldNames = getFieldNames(request, totalField);
			for (int i = 0; i < totalField; i++)
			{
				addToSearchFields(request, searchRequest, fieldNames, i);
				addToSortFields(request, searchRequest, fieldNames, i);
			}
		}
		request.setAttribute(WebUtils.SEARCH_CRITERIA, searchRequest);
		return true;
	}

	private Map<Integer, String> getFieldNames(HttpServletRequest request, Integer totalField)
	{
		Map<Integer, String> fieldNames = new HashMap<Integer, String>();
		for (int i = 0; i < totalField; i++)
		{
			fieldNames.put(Integer.valueOf(i), WebUtils.getRequestParam(request, M_DATA_PROP + i));
		}
		return fieldNames;
	}

	// if this field is searchable and not empty than add it to search fields.
	private void addToSearchFields(HttpServletRequest request, SearchCriteria searchRequest,
			Map<Integer, String> fieldNames, int i)
	{
		if (WebUtils.getRequestParamAsBoolean(request, B_SEARCHABLE + i) == true)
		{
			String valueToSearch = WebUtils.getRequestParam(request, S_SEARCH + i);
			if (StringUtils.isNotBlank(valueToSearch))
			{
				searchRequest.addSearchField(fieldNames.get(Integer.valueOf(i)), valueToSearch);
			}
		}
	}

	// if the field is sortable and has sort dir than add it to sort fields.
	private void addToSortFields(HttpServletRequest request, SearchCriteria searchRequest,
			Map<Integer, String> fieldNames, int i)
	{
		Integer sortFieldIndex = WebUtils.getRequestParamAsInt(request, I_SORT_COL + i);
		String sortDirection = WebUtils.getRequestParam(request, S_SORT_DIR + i);
		if (sortFieldIndex != null)
		{
			searchRequest.addSortField(fieldNames.get(sortFieldIndex), sortDirection);
		}
	}

	private void setPaginationParams(HttpServletRequest request, SearchCriteria searchRequest)
	{
		Integer startPosition = WebUtils.getRequestParamAsInt(request, I_DISPLAY_START);
		searchRequest.setStartPosition(startPosition);

		Integer maxResult = WebUtils.getRequestParamAsInt(request, I_DISPLAY_LENGTH);
		searchRequest.setMaxResult(maxResult);
	}
}
