package com.ascent.cms.web.controller;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.charset.Charset;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:META-INF/spring/applicationContext*.xml",
		"classpath*:META-INF/spring/applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class AbstractControllerTests implements MessageSourceAware
{
	protected static final String PATENT_DATA_SET_XML = "PatentDataSet.xml";

	protected static final String DIAGNOSIS_DATA_SET_XML = "DiagnosisDataSet.xml";

	@Autowired
	protected IDatabaseTester databaseTester;

	protected static final String BY_SPECIALIZATION = "BySpecialization";

	protected static final String SPECIALIZATION = "specialization";

	protected static final String FIND = "find";

	protected static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	protected static final String ERROR_MESSAGE_0 = "$.[0].message";

	protected static final String $_0_NAME = "$.[0].name";

	protected static final String $_1_NAME = "$.[1].name";

	protected static final String URL_SEPERATOR = "/";

	protected static final String GROUPS = "groups";

	protected static final String ERROR_MESSAGE_1 = "$.[1].message";

	protected static final String ERROR_MESSAGE_2 = "$.[2].message";

	private MessageSource messageSource;

	private void setupTestData(String datasetFileName, IDatabaseTester databaseTester) throws MalformedURLException,
			DataSetException, Exception
	{
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new File(datasetFileName));
		databaseTester.setDataSet(dataSet);
		databaseTester.setSetUpOperation(DatabaseOperation.CLEAN_INSERT);
		databaseTester.setTearDownOperation(DatabaseOperation.NONE);
		databaseTester.onSetup();
	}

	protected void setup(final String testDataFileName) throws MalformedURLException, DataSetException, Exception
	{
		String datasetFileName = "./src/test/resources/data" + "/" + testDataFileName;
		setupTestData(datasetFileName, databaseTester);
	}

	@Override
	public void setMessageSource(MessageSource messageSource)
	{
		this.messageSource = messageSource;
	}

	protected String getMessage(final String key)
	{
		return messageSource.getMessage(key, null, BaseController.DEFAULT_LOCALE);
	}

}
