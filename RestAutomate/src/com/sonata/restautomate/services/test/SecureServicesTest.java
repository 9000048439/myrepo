package com.sonata.restautomate.services.test;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sonata.restautomate.framework.BaseConfigurations;
import com.sonata.restautomate.util.Person;

public class SecureServicesTest extends BaseConfigurations{
@Test
public void secureServiceTestWithWrongCredentails()
{
	 given().spec(initSpec()).auth().preemptive().basic("username", "password").when().get("http://localhost:9999/rest/people").then().statusCode(401);
}
@Test
public void secureServiceTestCorrectCredentails()
{
	 given().spec(initSpec()).auth().preemptive().basic("user", "pass").when().get("http://localhost:9999/rest/people").then().statusCode(200);
}

@Test
public void verifyPostRequestWrongCredentails() {
	
	Person person = new Person();
	person.setOrganisation("SonataSoftware");
	person.setName("Kumara");
	given().spec(initSpec()).auth().preemptive().basic("user", "wrongpass").
	       contentType("application/json; charset=UTF-16").
	       body(person).
	when().
	      post("http://localhost:9999/rest/people").then().statusCode(200);
}
@Test
public void verifyPostRequestWithVaildCr() {
	
	Person person = new Person();
	person.setOrganisation("SonataSoftware");
	person.setName("Kumara");
	String response= given().spec(initSpec()).auth().preemptive().basic("user", "pass").
	       contentType("application/json; charset=UTF-16").
	       body(person).
	when().
	      post("http://localhost:9999/rest/people").asString();
	Assert.assertEquals(response, "\"message\":\"Person added with id=6\"");
}
}
