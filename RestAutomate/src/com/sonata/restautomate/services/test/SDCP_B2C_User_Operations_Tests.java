package com.sonata.restautomate.services.test;

import static com.jayway.restassured.RestAssured.given;
import static com.sonata.restautomate.framework.RestUtils.executeService;
import static com.sonata.restautomate.framework.RestUtils.getRestServiceResponseTime;
import static com.sonata.restautomate.framework.RestUtils.servicesList;
import static com.sonata.restautomate.framework.RestUtils.verifyResponseData;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sonata.restautomate.framework.BaseConfigurations;
import com.sonata.restautomate.framework.RestUtils;

public class SDCP_B2C_User_Operations_Tests extends BaseConfigurations {
	
	
	@BeforeClass
	public static void setup() {
		RestUtils.setup(getClassName(), "USERLOGIN-services.xml");
	}

	@Test
	public void verifyUserLoginServiceResponseCode() {
		String result = (String) executeService("userLoginService");
		System.out.println("userLoginService Response " + result);
	}
	@Test
	public void verifyUserLoginServiceResponseBodySingleData() {
	    verifyResponseData("userLoginService","status","Pass");
	}
	@Test
	public void verifyUserLoginServiceResponseBodyMultipleData() {
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("message", "Login Success");
		params.put("status","Pass");
		params.put("responseList","Pass");
	    verifyResponseData("userLoginService",params);
	}
	@Test
	public void verifyUserLoginServiceResponseData() {
		String result = (String) executeService("userLoginService_wrongcredentails");
		System.out.println("userLoginService Response " + result);
		Assert.assertEquals(servicesList.get("userLoginService_wrongcredentails").getResponse(), result);
	}
	
	
/*	
	@Test
	public void secureServiceLoginTestWithWrongCredentails()
	{
		 given().spec(initSpec()).auth().preemptive().basic("username", "password").when().get("http://localhost/globus/user/userLogin").then().statusCode(401);
	}
	
	
	@Test
	public void secureServiceLoginTestCorrectCredentails()
	{
		 given().spec(initSpec()).auth().preemptive().basic("vpn1@gmail.com", "sonata").when().get("http://localhost/globus/user/userLogin").then().statusCode(200);
	}*/


	@Test
	public void testResponseTime(){
	getRestServiceResponseTime("userLoginService",100L);
	}
	
	@AfterClass
	public static void tearDown() {
		servicesList = null;
		RestUtils.servicesList=null;
		RestUtils.printMessage(getClassName(),"complete");
		RestUtils.shutdown();
	}

}
