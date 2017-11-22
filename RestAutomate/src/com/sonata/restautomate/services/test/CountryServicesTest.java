package com.sonata.restautomate.services.test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.path.xml.XmlPath.with;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.xml.element.Node;

public class CountryServicesTest {
	@Test
	public void checkHeaderData() {
	        
	  given().
	    pathParam("country","us").
	    pathParam("zipcode","90210").
	  when().
	    get("http://api.zippopotam.us/{country}/{zipcode}").
	  then().
	    assertThat().
	    statusCode(200).
	  and().
	    contentType(ContentType.JSON);
	}
	@Test
	public void checkCityForZipCode() {
	        
	  given().
	    pathParam("country","us").
	    pathParam("zipcode","90210").
	  when().
	    get("http://api.zippopotam.us/{country}/{zipcode}").
	  then().
	    assertThat().
	    body("places.'place name'[0]",equalTo("Beverly Hills"));
	}
	
	@Test
	public void verifyXmlResponseWithMultipleConditions()
	{
		get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/").
        then().
            assertThat().body("customer.id", equalTo("12212")).
        and().
            assertThat().body("customer.firstName", equalTo("John")).
        and().
            assertThat().body("customer.lastName", equalTo("Doe"));     
	}
	@Test
	public void verifyXmlResponseWithcount()
	{
		String xml = get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts").asString();
		int count = with(xml).get("accounts.size()");
		Assert.assertTrue(count>0);

	}
	@Test
	public void verifyXmlResponseSingleField()
	{
		String xml = get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts").asString();  
		String accountNumber = with(xml).get("accounts.account[0].id");
		Assert.assertEquals(accountNumber, "12456");

	}
	@Test
	public void verifyXmlResponseSingleFieldWrong()
	{
		String xml = get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts").asString();  
		String accountNumber = with(xml).get("accounts.account[0].id");
		Assert.assertEquals(accountNumber, "0000000");

	}
	@Test
	public void verifyXmlResponseWithConditions()
	{
		String xml = get("http://parabank.parasoft.com/parabank/services/bank/customers/12212/accounts").asString();
		List<Node> itemsBetweenTenAndTwenty = with(xml).get("accounts.account.findAll { account -> def id = account.id.toFloat(); id >= 12900 && id <= 13344 }");
		Assert.assertEquals(itemsBetweenTenAndTwenty.size(), 0);

	}
}
