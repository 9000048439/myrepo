package com.sonata.restautomate.services.test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.xml.XmlPath.with;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.sonata.restautomate.framework.BaseConfigurations;

public class DependentServicesTest extends BaseConfigurations {
	String accountNumber = "";
	@BeforeClass
	public void setUp() 
	{
		 RestAssured.port = 80;
	}

	@Test
	public void retriveAccountInformation() {
		String xml = given().spec(initSpec()).when().get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts").asString();
		accountNumber = with(xml).get("accounts.account[2].id");
		Assert.assertEquals(accountNumber, "12678");
	}

	@Test(dependsOnMethods = { "retriveAccountInformation" })
	public void retriveTransactionsInformationBasedOnAccount() {
		String xml =given().spec(initSpec()).when()
				.get("http://parabank.parasoft.com/parabank/services/bank/accounts/" + accountNumber + "/transactions")
				.asString();
		int count = with(xml).get("transactions.transaction.size()");
		Assert.assertEquals(count, 2);

	}
	@AfterClass
	public void tearDown()
	{
		RestAssured.reset();
	}
}
