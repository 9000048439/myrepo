package com.sonata.restautomate.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;



//from   w w  w.  j av  a2 s  .c  o m
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class Main {
	public static void generateXML(Service serviceRequest) throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
		Document document=null;
		try
		{
		 document = documentBuilder.parse(serviceRequest.getListOfServices());
		}catch (FileNotFoundException fnfe) {
			createFile(serviceRequest.getListOfServices());
			document = documentBuilder.parse(serviceRequest.getListOfServices());
		}
		Element root = document.getDocumentElement();
		Element rootElement = document.getDocumentElement();
		Collection<Service> svr = new ArrayList<Service>();
		svr.add(serviceRequest);
		for (Service service : svr) {
			root.appendChild(createService(service,document,rootElement));
		}
		DOMSource source = new DOMSource(document);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(serviceRequest.getListOfServices());
		transformer.transform(source, result);
		System.out.println("Completed XML creation");
	}
	private static Element createService(Service service,Document document,Element rootElement)
	{
		Element serviceElement = document.createElement("service");
		serviceElement.setAttribute("name",service.getServiceName());
		rootElement.appendChild(serviceElement);

		Element serviceURL = document.createElement("url");
		serviceURL.appendChild(document.createCDATASection(service.getServiceURL()));
		serviceElement.appendChild(serviceURL);

		Element statusCode = document.createElement("statusCode");
		statusCode.appendChild(document.createTextNode(service.getStatusCode()));
		serviceElement.appendChild(statusCode);
		
		
		Element requestType = document.createElement("requestType");
		requestType.appendChild(document.createTextNode(service.getRequestType()));
		serviceElement.appendChild(requestType);
		
		Element requestBody = document.createElement("request");
		requestBody.appendChild(document.createCDATASection(service.getRequestBody()));
		serviceElement.appendChild(requestBody);
		
		Element responseBody = document.createElement("response");
		responseBody.appendChild(document.createCDATASection(service.getResponse()));
		serviceElement.appendChild(responseBody);
		
		Element username = document.createElement("username");
		username.appendChild(document.createTextNode(service.getUsername()));
		serviceElement.appendChild(username);
		
		Element password = document.createElement("password");
		password.appendChild(document.createTextNode(service.getPassword()));
		serviceElement.appendChild(password);
		
		return serviceElement;
	}
	private static void createFile(String fileName) {
		try {

			File file = new File(fileName);

			if (file.createNewFile()) {
				System.out.println("File is created!");
				FileWriter fw = new FileWriter(file);
				fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<services></services>");
				fw.close();
			} else {
				System.out.println("File already exists.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class Service {
	private String serviceURL;
	private String serviceName;
	private String statusCode;
	private String requestType;
	private String requestBody;
	private String username;
	private String password;
	private String listOfServices;
	private String response;
	
	public String getServiceURL() {
		return serviceURL;
	}
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getRequestBody() {
		return requestBody;
	}
	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getListOfServices() {
		return listOfServices;
	}
	public void setListOfServices(String listOfServices) {
		this.listOfServices = listOfServices+"-services.xml";
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	
	
}