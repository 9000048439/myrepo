package com.sonata.restautomate.services.test;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.beust.jcommander.ParameterException;
import com.sonata.restautomate.framework.BaseConfigurations;
import com.sonata.restautomate.framework.Service;
import com.sonata.restautomate.framework.Services;

public class RezopiaServiceTestes extends BaseConfigurations {

	private static Map<String, Service> servicesList = null;

	public static String CONTENT_TYPE = "text/plain";

	private Object executeGetService(String serviceName, int statusCode) {
		Service service = servicesList.get(serviceName);
		if (service != null) {
			if (service.getName() != null && service.getName().isEmpty()) {
				throw new ParameterException(
						"Please verify basic parameters are missing in request");
			}
			try {
				return given().contentType(CONTENT_TYPE).log().everything()
						.expect().statusCode(statusCode).log().everything()
						.when().get(service.getUrl()).asString();
			} catch (Exception e) {
				if (e instanceof UnknownHostException) {
					Assert.assertEquals("404", statusCode,
							"Service Not Avaliable");

				}
				if (e instanceof ParameterException) {
					Assert.assertEquals("404", statusCode, e.getMessage());
				}
				e.printStackTrace();
			}
		}
		return null;
	}

	// https://blog.philipphauer.de/testing-restful-services-java-best-practices/#use-rest-assured
	private Object executePostService(String serviceName, int statusCode) {
		Service service = servicesList.get(serviceName);
		if (service != null) {
			if (service.url != null && service.url.trim().isEmpty()) {
				throw new ParameterException(
						"Please verify basic parameters are missing in request");
			}
			System.out.println(service.getUrl());
			System.out.println("Request " + service.getRequest());
			System.out.println("Response " + service.getResponse());
			try {
				return given().contentType(CONTENT_TYPE)
						.body(service.getRequest()).log().everything().expect()
						.statusCode(statusCode).log().everything().when()
						.post(service.getUrl()).asString();
			} catch (Exception e) {
				if (e instanceof UnknownHostException) {
					System.out.println("Service Not Avaliable");
					Assert.assertEquals("404", statusCode,
							"Service Not Avaliable");

				}
				if (e instanceof ParameterException) {
					Assert.assertEquals("404", statusCode, e.getMessage());
				}
				e.printStackTrace();
			}
		}
		return null;
	}

	@BeforeClass
	public static void setup() {
		servicesList = new HashMap<String, Service>();
		Services services = null;
		try {
			System.out
					.println("================================Loading configuration file================================");
			File file = new File("config\\services.xml");
			validateXMLSchema("config\\services.xsd","config\\services.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Services.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			services = (Services) jaxbUnmarshaller.unmarshal(file);
			String baseURL = services.getBaseURL();

			for (Service services2 : services.getService()) {
				services2.setBaseURL(baseURL);
				if (services2.getName() != null) {
					servicesList.put(services2.getName(), services2);
					// System.out.println(services2);
				}
			}
			// System.out.println("Map contains " + servicesList);
			System.out
					.println("================================AllAppsServicesRestTest testcases started================================");

		} catch (JAXBException e) {
			e.printStackTrace();
			System.out
					.println("================================Problem in Loading configuration file================================");
		}
	}

	// Get User Profile
	@Test
	public void getUserProfile() {
		String result = (String) executeGetService("getUserProfile", 200);
		System.out.println("getUserProfile Response " + result);
	}

	// Get Create User Profile
	@Test
	public void createUserProfile() {

		String result = (String) executePostService("createUserProfile", 200);
		System.out.println("createUserProfile Response " + result);
	}

	// Get Cancel User Booking
	@Test
	public void cancelUserBooking() {
		String result = (String) executePostService("cancelUserBooking", 200);
		System.out.println("cancelUserBooking Response " + result);

	}

	// Get get Order Cancel Penalty
	@Test(enabled = false)
	public void getOrderCancelPenalty() {
		String result = (String) executePostService("getOrderCancelPenalty",
				200);
		System.out.println("getOrderCancelPenalty Response " + result);
	}

	// Get Remove Train Item
	@Test(enabled = false)
	public void removeTrainItem() {
		String result = (String) executePostService("removeTrainItem", 200);
		System.out.println("removeTrainItem Response " + result);
	}

	// Get load SSR Details
	@Test(enabled = false)
	public void loadSSRDetails() {
		String result = (String) executePostService("loadSSRDetails", 200);
		System.out.println("loadSSRDetails Response " + result);
	}

	// Get My Booking Details
	@Test(enabled = false)
	public void getMyBooking() {
		String result = (String) executePostService("getMyBooking", 200);
		System.out.println("getMyBooking Response " + result);
	}

	// Get Train Service Availability
	@Test(enabled = false)
	public void getTrainServiceAvailability() {
		String result = (String) executePostService(
				"getTrainServiceAvailability", 200);
		System.out.println("getTrainServiceAvailability Response " + result);
	}

	// Get Train Details
	@Test(enabled = false)
	public void getTrainDetails() {
		String result = (String) executePostService("getTrainDetails", 200);
		System.out.println("getTrainDetails Response " + result);
	}

	// Get Meal By Train Meal ID
	@Test(enabled = false)
	public void getMealByTrainMealID() {
		String result = (String) executePostService("getMealByTrainMealID", 200);
		System.out.println("getMealByTrainMealID Response " + result);
	}

	// Get Meal By Train Name
	@Test(enabled = false)
	public void getMealByTrainName() {
		String result = (String) executePostService("getMealByTrainName", 200);
		System.out.println("getMealByTrainName Response " + result);
	}

	// Get Train Service List
	@Test(enabled = false)
	public void getTrainServiceList() {
		String result = (String) executePostService("getTrainServiceList", 200);
		System.out.println("getTrainServiceList Response " + result);
	}

	// Get Train Fare Rule
	@Test(enabled = false)
	public void getTrainFareRule() {
		String result = (String) executePostService("getTrainFareRule", 200);
		System.out.println("getTrainFareRule Response " + result);
	}

	// Get Delete Cart Item
	@Test(enabled = false)
	public void deleteCartItem() {
		String result = (String) executePostService("deleteCartItem", 200);
		System.out.println("deleteCartItem Response " + result);
	}

	// Get view Cart Items
	@Test(enabled = false)
	public void viewCartItems() {
		String result = (String) executePostService("viewCartItems", 200);
		System.out.println("viewCartItems Response " + result);
	}

	// Get Add To Cart
	@Test(enabled = false)
	public void addToCart() {
		String result = (String) executePostService("addToCart", 200);
		System.out.println("addToCart Response " + result);
	}

	// Get Add Meal
	@Test(enabled = false)
	public void addMeal() {
		String result = (String) executePostService("addMeal", 200);
		System.out.println("addMeal Response " + result);
	}

	// Get Credit Card Details
	@Test(enabled = false)
	public void getCreditCardDetails() {
		String result = (String) executePostService("getCreditCardDetails", 200);
		System.out.println("getCreditCardDetails Response " + result);
	}

	// Get Prepaid Meal
	@Test(enabled = false)
	public void getPrepaidMeal() {
		String result = (String) executePostService("getPrepaidMeal", 200);
		System.out.println("getPrepaidMeal Response " + result);
	}

	// Get Payment Policy
	@Test(enabled = false)
	public void getPaymentPolicy() {
		String result = (String) executePostService("getPaymentPolicy", 200);
		System.out.println("getPaymentPolicy Response " + result);
	}

	// Get Booking LookUp
	@Test(enabled = false)
	public void getBookingLookUp() {
		String result = (String) executePostService("getBookingLookUp", 200);
		System.out.println("getBookingLookUp Response " + result);
	}

	// Get Passenger Info
	@Test(enabled = false)
	public void getPassengerInfo() {
		String result = (String) executePostService("getPassengerInfo", 200);
		System.out.println("getPassengerInfo Response " + result);
	}

	// Get Booking Attribute
	@Test(enabled = false)
	public void getBookingAttribute() {
		String result = (String) executePostService("getBookingAttribute", 200);
		System.out.println("getBookingAttribute Response " + result);
	}

	// Get Booking Info
	@Test(enabled = false)
	public void getBookingInfo() {
		String result = (String) executePostService("getBookingInfo", 200);
		System.out.println("getBookingInfo Response " + result);
	}

	// Get Train View Details
	@Test(enabled = false)
	public void getTrainViewDetails() {
		String result = (String) executePostService("getTrainViewDetails", 200);
		System.out.println("getTrainViewDetails Response " + result);
	}

	// Get Extension Attribute
	@Test(enabled = false)
	public void getExtensionAttribute() {
		String result = (String) executePostService("getExtensionAttribute",
				200);
		System.out.println("getExtensionAttribute Response " + result);
	}

	// Get Checkout Login
	@Test(enabled = false)
	public void getCheckoutLogin() {
		String result = (String) executePostService("getCheckoutLogin", 200);
		System.out.println("getCheckoutLogin Response " + result);
	}

	// Get Checkout Guest
	@Test(enabled = false)
	public void getCheckoutGuest() {
		String result = (String) executePostService("getCheckoutGuest", 200);
		System.out.println("getCheckoutGuest Response " + result);
	}

	// Get Save Meal
	@Test(enabled = false)
	public void getSaveMeal() {
		String result = (String) executePostService("getSaveMeal", 200);
		System.out.println("getSaveMeal Response " + result);
	}

	// Get Delete Meal
	@Test(enabled = false)
	public void getDeleteMeal() {
		String result = (String) executePostService("getDeleteMeal", 200);
		System.out.println("getDeleteMeal Response " + result);
	}

	// Get Create Booking
	@Test(enabled = false)
	public void createbooking() {
		String result = (String) executePostService("createbooking", 200);
		System.out.println("createbooking Response " + result);
	}

	@AfterClass
	public static void tearDown() {
		servicesList = null;
		System.out
				.println("================================AllAppsServicesRestTest Testcases Completed ================================");
	}

}
