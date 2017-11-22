package com.sonata.restautomate.framework;

import static com.jayway.restassured.RestAssured.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.filter.log.RequestLoggingFilter;
import com.jayway.restassured.filter.log.ResponseLoggingFilter;
import com.jayway.restassured.specification.RequestSpecification;

public class BaseConfigurations {

	public BaseConfigurations() {

		try {
			final String fileName = "logs\\logger_"
					+ new SimpleDateFormat("dd-MM-yyyy(HH-mm)'.txt'")
							.format(new Date());
			System.setOut(new PrintStream(new FileOutputStream(fileName, true)));
			RestAssured.config = config().logConfig(
					new LogConfig(new PrintStream(new FileOutputStream(
							fileName, true)), true));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public static RequestSpecification initSpec() {
		RequestSpecification spec = new RequestSpecBuilder()
				.addFilter(new ResponseLoggingFilter())
				.addFilter(new RequestLoggingFilter()).build();
		return spec;
	}

	public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
		
        System.out.println("=========Xml validation==============");
		try {
			SchemaFactory factory = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdPath));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(new File(xmlPath)));
		} catch (IOException | SAXException e) {
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		return true;
	}
	
	public static String getClassName() {
	    String className = Thread.currentThread().getStackTrace()[2].getClassName();
	    int lastIndex = className.lastIndexOf('.');
	    return className.substring(lastIndex + 1);
	}
}
