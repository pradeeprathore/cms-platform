package com.ascent.cms.core.vo;

import java.util.List;

public class ListResponse
{

	private long iTotalRecords;

	private long iTotalDisplayRecords;

	private String sEcho;

	private List<?> aaData;

	public ListResponse()
	{

	}

	public ListResponse(long iTotalRecords, long iTotalDisplayRecords, List<?> aaData)
	{
		this(iTotalRecords, iTotalDisplayRecords, null, aaData);
	}

	public ListResponse(long iTotalRecords, long iTotalDisplayRecords, String sEcho, List<?> aaData)
	{
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
		this.sEcho = sEcho;
		this.aaData = aaData;
	}

	public long getiTotalRecords()
	{
		return iTotalRecords;
	}

	public void setiTotalRecords(long iTotalRecords)
	{
		this.iTotalRecords = iTotalRecords;
	}

	public long getiTotalDisplayRecords()
	{
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(long iTotalDisplayRecords)
	{
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho()
	{
		return sEcho;
	}

	public void setsEcho(String sEcho)
	{
		this.sEcho = sEcho;
	}

	public List<?> getAaData()
	{
		return aaData;
	}

	public void setAaData(List<?> aaData)
	{
		this.aaData = aaData;
	}

}
