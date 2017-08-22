package com.ascent.cms.core.dao.impl;

import java.net.MalformedURLException;

import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.DataSetException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ascent.cms.core.DAOTestingUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:META-INF/spring/applicationContext*.xml",
		"classpath*:META-INF/spring/applicationContext-test.xml" })
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class AbstractDAOTests extends AbstractTransactionalJUnit4SpringContextTests
{

	protected static final String DIAGNOSIS_DATA_SET_XML = "DiagnosisDataSet.xml";

	protected static final String PATENT_DATA_SET_XML = "PatentDataSet.xml";

	@Autowired
	protected IDatabaseTester databaseTester;

	public void setup(final String testDataFileName) throws MalformedURLException, DataSetException, Exception
	{
		String datasetFileName = "./src/test/resources/data" + "/" + testDataFileName;
		DAOTestingUtil.setupTestData(datasetFileName, databaseTester);
		// databaseTester.setTearDownOperation(DatabaseOperation.TRUNCATE_TABLE);
	}

	// @After
	// public void tearDown() throws Exception{
	// this.databaseTester.onTearDown();
	// }
}
