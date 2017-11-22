package com.sonata.restautomate.framework;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import java.io.File;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.testng.Assert;

import com.beust.jcommander.ParameterException;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.specification.ResponseSpecification;

public class RestUtils {
	private static String CONTENT_TYPE = "application/json";
	public static Map<String, Service> servicesList = null;
	
	
	public static void setup(String className,String configFile) {
		Map<String, Service> servicesList = new HashMap<String, Service>();
		Services services = null;
		try {
			printMessage(className,"loading");
			File file = new File("config\\"+configFile);
			//validateXMLSchema("config\\services.xsd","config\\services.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Services.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			services = (Services) jaxbUnmarshaller.unmarshal(file);
			String baseURL = services.getBaseURL();

			for (Service services2 : services.getService()) {
				services2.setBaseURL(baseURL);
				if (services2.getName() != null) {
					servicesList.put(services2.getName(), services2);
				}
			}
			printMessage(className,"start");
			RestUtils.servicesList=servicesList;
		} catch (JAXBException e) {
			e.printStackTrace();
			printMessage(className,"problem");
		}
		RestAssured.port=80;
	}

	public static Object executeGetService(Service service) {
		try {
			return given().contentType(CONTENT_TYPE).log().everything().expect().statusCode(service.getStatusCode())
					.log().everything().when().get(service.getUrl()).asString();
		} catch (Exception e) {
			if (e instanceof UnknownHostException) {
				Assert.assertEquals("404", service.getStatusCode(), "Service Not Avaliable");

			}
			if (e instanceof ParameterException) {
				Assert.assertEquals("404", service.getStatusCode(), e.getMessage());
			}
			e.printStackTrace();
		}

		return null;
	}

	// https://blog.philipphauer.de/testing-restful-services-java-best-practices/#use-rest-assured
	public static Object executePostService(Service service) {

		try {
			return given().contentType(CONTENT_TYPE).body(service.getRequest()).log().everything().expect()
					.statusCode(service.getStatusCode()).log().everything().when().post(service.getUrl()).asString();
		} catch (Exception e) {
			if (e instanceof UnknownHostException) {
				System.out.println("Service Not Avaliable");
				Assert.assertEquals("404", service.getStatusCode(), "Service Not Avaliable");

			}
			if (e instanceof ParameterException) {
				Assert.assertEquals("404", service.getStatusCode(), e.getMessage());
			}
			e.printStackTrace();
		}

		return null;
	}

	public static void printRequestDetails(Service service) {
		System.out.println(service.getUrl());
		System.out.println("Given Request " + service.getRequest());
		System.out.println("Given Response " + service.getResponse());
		System.out.println("Given Request Type " + service.getRequestType());
		System.out.println("Given Status Code " + service.getStatusCode());
	}

	public static Object executeService(String serviceName) {

		Service service = servicesList.get(serviceName);
		if (service != null) {
			printRequestDetails(service);
			if (service.getName() != null && service.getName().isEmpty()) {
				throw new ParameterException("Please verify basic parameters are missing in request");
			}
		}
		if (service != null && service.getRequestType().trim().length() > 0) {

			if (service.getRequestType().equalsIgnoreCase("GET")) {
				return executeGetService(service);

			} else if (service.getRequestType().equalsIgnoreCase("POST")) {
				return executePostService(service);
			}
		}
		return null;

	}

	public static void verifyResponseData(String serviceName, String paramName, String expectedValue) {
		Service service = servicesList.get(serviceName);
		if (service != null) {
			if (service.url != null && service.url.trim().isEmpty()) {
				throw new ParameterException("Please verify basic parameters are missing in request");
			}
			printRequestDetails(service);
			try {
				given().contentType(CONTENT_TYPE).body(service.getRequest()).log().everything().expect()
						.body(paramName, equalTo(expectedValue)).statusCode(service.getStatusCode()).log().everything()
						.when().post(service.getUrl());
			} catch (Exception e) {
				if (e instanceof UnknownHostException) {
					System.out.println("Service Not Avaliable");
					Assert.assertEquals("404", service.getStatusCode(), "Service Not Avaliable");

				}
				if (e instanceof ParameterException) {
					Assert.assertEquals("404", service.getStatusCode(), e.getMessage());
				}
				e.printStackTrace();
			}
		}
	}

	public static void verifyResponseData(String serviceName, Map<String, String> params) {
		Service service = servicesList.get(serviceName);
		if (service != null) {
			if (service.url != null && service.url.trim().isEmpty()) {
				throw new ParameterException("Please verify basic parameters are missing in request");
			}
			printRequestDetails(service);
			try {
				ResponseSpecification response = given().contentType(CONTENT_TYPE).body(service.getRequest()).log()
						.everything().expect();
				if (params != null) {
					for (Map.Entry<String, String> entry : params.entrySet()) {
						response = response.body(entry.getKey(), equalTo(entry.getValue())).and();
					}
				}
				response.statusCode(service.getStatusCode()).log().everything().when().post(service.getUrl());
			} catch (Exception e) {
				if (e instanceof UnknownHostException) {
					System.out.println("Service Not Avaliable");
					Assert.assertEquals("404", service.getStatusCode(), "Service Not Avaliable");

				}
				if (e instanceof ParameterException) {
					Assert.assertEquals("404", service.getStatusCode(), e.getMessage());
				}
				e.printStackTrace();
			}
		}
	}

	// Performance Check

	public static void getRestServiceResponseTime(String serviceName, Long time) {
		Service service = servicesList.get(serviceName);
		if (service != null) {
			if (service.url != null && service.url.trim().isEmpty()) {
				throw new ParameterException("Please verify basic parameters are missing in request");
			}
			printRequestDetails(service);
			try {
				given().contentType(CONTENT_TYPE).body(service.getRequest()).log().everything().expect()
						.statusCode(service.getStatusCode()).log().everything().when().post(service.getUrl()).then()
						.time(lessThan(time));
			} catch (Exception e) {
				if (e instanceof UnknownHostException) {
					System.out.println("Service Not Avaliable");
					Assert.assertEquals("404", service.getStatusCode(), "Service Not Avaliable");

				}
				if (e instanceof ParameterException) {
					Assert.assertEquals("404", service.getStatusCode(), e.getMessage());
				}
				e.printStackTrace();
			}
		}
	}
	public static void printMessage(String ClassName,String message)
	{
		if(message!=null)
		{
		if(message.equalsIgnoreCase("complete"))
		{
		System.out.println("************ "+ClassName+" Testcases Completed ************");
		}
		else if(message.equalsIgnoreCase("start"))
		{
		System.out.println("************ "+ClassName+" testcases started ************");
		}
		else if(message.equalsIgnoreCase("problem"))
		{
		System.out.println("************ Problem in Loading configuration file ************");
		}
		else if(message.equalsIgnoreCase("loading"))
		{
		System.out.println("************ Loading configuration file ************");
		}
		}

	}
	public static void shutdown()
	{
		RestAssured.reset();
	}
}
