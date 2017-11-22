package com.sonata.restautomate.services.test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;

public class RestAssuredGPathTest {
	
	private String getDriverListFor2016 = "http://ergast.com/api/f1/2016/drivers.json";
	
	@BeforeTest
	public void initPath() {
		
		RestAssured.rootPath = "MRData.DriverTable.Drivers";
	}
	
	@DataProvider(name = "rangesAndValues")
	public String[][] createTestDataObject() {
		
		return new String[][] {
			{"20","30","22","25"},
			{"30","40","33","31"},
			{"1","9","9","4"}
		};
	}
	
	@Test
	public void extractAndCheckSingleValue() {
		
		given().
		when().
			get(getDriverListFor2016).
		then().
			assertThat().
			body("driverId[-1]",equalTo("wehrlein"));
	}
		
	@Test
	public void extractAndCheckMultipleValues() {
		
		given().
		when().
			get(getDriverListFor2016).
		then().
			assertThat().
			body("driverId",hasItems("alonso","button"));
	}
	
	@Test
	public void extractAndCheckArraySliceSize() {
		
		given().
		when().
			get(getDriverListFor2016).
		then().
			assertThat().
			body("driverId[0..2]",hasSize(3));
	}
	
	/*@Test
	public void extractAndCheckRange() {
		
		given().
		when().
			get(getDriverListFor2016).
		then().
			assertThat().
			body("findAll{Drivers->Drivers.permanentNumber >= \"20\" && Drivers.permanentNumber <= \"30\"}.permanentNumber",hasItem("22")).
			and().
			body("findAll{Drivers->Drivers.permanentNumber >= \"20\" && Drivers.permanentNumber <= \"30\"}.permanentNumber",not(hasItem("33"))).
			and().
			body("findAll{Drivers->Drivers.permanentNumber >= \"20\" && Drivers.permanentNumber <= \"30\"}.permanentNumber",not(hasItem("25")));
	}
	*/
	@Test(dataProvider = "rangesAndValues")
	public void extractAndCheckRangeParameterized(String lowerLimit, String upperLimit, String inCollection, String notInCollection) {
		
		given().
		when().
			get(getDriverListFor2016).
		then().		
			assertThat().
			body("findAll{Drivers->Drivers.permanentNumber >= \"" + lowerLimit + "\" && Drivers.permanentNumber <= \"" + upperLimit + "\"}.permanentNumber",hasItem(inCollection)).
			and().
			body("findAll{Drivers->Drivers.permanentNumber >= \"" + lowerLimit + "\" && Drivers.permanentNumber <= \"" + upperLimit + "\"}.permanentNumber",not(hasItem(notInCollection)));
	}	
}