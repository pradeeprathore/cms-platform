package com.ascent.cms.services.impl.utility;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ascent.cms.core.ErrorCode;
import com.ascent.cms.core.constants.BloodGroup;
import com.ascent.cms.core.constants.DetailMode;
import com.ascent.cms.core.dao.AppUserDAO;

import com.ascent.cms.core.domain.Address;

import com.ascent.cms.core.domain.Country;

import com.ascent.cms.core.domain.State;

import com.ascent.cms.core.domain.user.AppUser;
import com.ascent.cms.core.exception.InvalidArgumentException;
import com.ascent.cms.core.util.Assert;
import com.ascent.cms.core.vo.AddressVO;

import com.ascent.cms.services.impl.BaseServiceImpl;
import com.ascent.cms.services.util.ServiceUtils;
import com.ascent.cms.services.utility.PatientExcelSheetDataService;

@Service
@Transactional
public class PatientExcelSheetDataServiceImp extends BaseServiceImpl implements PatientExcelSheetDataService
{

	/*@Autowired
	private PatientDAO patientDAO;*/

	@Autowired
	private AppUserDAO appUserDAO;

	public final String INPUTDIR = "/var/lib/data/hot";

	public final String OUTPUTDIR = "/var/lib/data/cold/";

	public final String DOBMONTHDAY = "-01-01";

	public final String DOBFORMAT = "yyyy-MM-dd";

	

	private static Address toAddress(AddressVO addressVO)
	{
		Address address = new Address();
		address.setAddressLine1(addressVO.getAddressLine1());
		address.setAddressLine1(addressVO.getAddressLine2());
		address.setArea(addressVO.getArea());
		address.setCity(addressVO.getCity());
		address.setCountry(addressVO.getCountry());
		address.setState(addressVO.getState());
		address.setZipCode(addressVO.getZipCode());
		return address;
	}
}
