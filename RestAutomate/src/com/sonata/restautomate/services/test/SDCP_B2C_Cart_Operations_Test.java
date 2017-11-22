package com.sonata.restautomate.services.test;

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

public class SDCP_B2C_Cart_Operations_Test extends BaseConfigurations {
	
	
	@BeforeClass
	public static void setup() {
		RestUtils.setup(getClassName(), "sdcp-b2c-cart-services.xml");
	}
	@Test
	public void verifygetCartServiceResponseCode() {
		String result = (String) executeService("getCart");
		System.out.println("getCart Response " + result);
	}
	
	
	@Test
	public void verifygetCartServiceResponseBodySingleData() {
		 verifyResponseData("getCart","status","pass");
	}
	
	@Test
	public void verifygetCartServiceResponseBodyMultipleData() {
		HashMap<String, String> params=new HashMap<String, String>();
		params.put("status","pass1");
	    verifyResponseData("getCart", params);
	}
	
	
	@Test
	public void verifyaddToCartServiceResponseCode() {
		String result = (String) executeService("addToCart");
		System.out.println("addToCart Response " + result);
	}
	
	@Test
	public void verifyaddToCartServiceResponseBodySingleData() {
		 verifyResponseData("addToCart","status","pass");
	}
	
	
	@AfterClass
	public static void tearDown() {
		servicesList = null;
		RestUtils.servicesList=null;
		RestUtils.printMessage(getClassName(),"complete");
		RestUtils.shutdown();
	}

}
