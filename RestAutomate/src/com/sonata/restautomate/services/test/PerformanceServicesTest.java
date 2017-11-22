package com.sonata.restautomate.services.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.sonata.restautomate.framework.BaseConfigurations;

public class PerformanceServicesTest  extends BaseConfigurations{
	@Test
	public void testResponseTime(){
		
		RestAssured.baseURI = "https://www.googleapis.com/books/v1/";
		
		String json = given().spec(initSpec()).when().get("volumes?q=turing").asString();		
		given().
		  parameters("q","turing").
		when().
		  get("volumes").
		then().
		 time(lessThan(1000L));
				
	}
	@Test
	public void testResponseTimeAndCondition(){
		
		RestAssured.baseURI = "https://www.googleapis.com/books/v1/";
		
		String json = given().spec(initSpec()).when().get("volumes?q=turing").asString();
		given().
		  parameters("q","turing").
		when().
		  get("volumes").
		then().
		  body("items.volumeInfo.title[1]", equalTo("The Essential Turing") ).
		  and().time(lessThan(1000L));
				
	}
	@Test
	public void testResponseWrongTime(){
		
		RestAssured.baseURI = "https://www.googleapis.com/books/v1/";
		
		String json = given().spec(initSpec()).when().get("volumes?q=turing").asString();		
		given().
		  parameters("q","turing").
		when().
		  get("volumes").
		then().
		 time(lessThan(500L));
				
	}
}
