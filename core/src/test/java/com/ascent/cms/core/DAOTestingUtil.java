package com.ascent.cms.core;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;


import com.ascent.cms.core.domain.user.AppUser;

public class DAOTestingUtil
{

	public static void setupTestData(String datasetFileName, IDatabaseTester databaseTester)
			throws MalformedURLException, DataSetException, Exception
	{
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(datasetFileName));
		databaseTester.setDataSet(dataSet);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setTearDownOperation(DatabaseOperation.NONE);
		databaseTester.onSetup();
	}

	public static AppUser createNewpatent()
	{
		AppUser patent = new AppUser();
		patent.setFirstName("Uttam");
		patent.setMiddleName("singh");
		patent.setLastName("deora");
		patent.setMobile("7507357467");
		patent.setDateOfBirth(new Date());
		patent.setEmail("uttam@abc.com");
		patent.setUserName("userName");
		patent.setPassword("password");
		return patent;
	}

}
