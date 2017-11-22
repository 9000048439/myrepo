package com.sonata.restautomate.framework;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
/*
 * @XmlType(name = "", propOrder = { "url", "request", "reponseType",
 * "requestAttributes", "reponseAttributes" })
 */
public class Service {

	@XmlElement(required = true)
	@XmlSchemaType(name = "anyURI")
	public String url;
	@XmlElement(required = true)
	protected String request;
	@XmlElement(required = true)
	protected String reponseType;
	protected Service.RequestAttributes requestAttributes;
	protected Service.ReponseAttributes reponseAttributes;
	@XmlAttribute(name = "name")
	protected String name;
	public String baseURL;
	@XmlElement(required = true)
	protected String requestType;
	@XmlElement(required = true)
	protected String response;
	@XmlElement(required = true)
	protected int statusCode;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getBaseURL() {
		return baseURL;
	}

	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}

	/**
	 * Gets the value of the url property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUrl() {
		// return baseURL+url;
		if (baseURL == null) {
			return url;
		}
		return baseURL + url;
	}

	/**
	 * Sets the value of the url property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUrl(String value) {
		this.url = this.url + value;
	}

	/**
	 * Gets the value of the request property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * Sets the value of the request property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRequest(String value) {
		this.request = value;
	}

	/**
	 * Gets the value of the reponseType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getReponseType() {
		return reponseType;
	}

	/**
	 * Sets the value of the reponseType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setReponseType(String value) {
		this.reponseType = value;
	}

	/**
	 * Gets the value of the requestAttributes property.
	 * 
	 * @return possible object is {@link Service.RequestAttributes }
	 * 
	 */
	public Service.RequestAttributes getRequestAttributes() {
		return requestAttributes;
	}

	/**
	 * Sets the value of the requestAttributes property.
	 * 
	 * @param value
	 *            allowed object is {@link Service.RequestAttributes }
	 * 
	 */
	public void setRequestAttributes(Service.RequestAttributes value) {
		this.requestAttributes = value;
	}

	/**
	 * Gets the value of the reponseAttributes property.
	 * 
	 * @return possible object is {@link Service.ReponseAttributes }
	 * 
	 */
	public Service.ReponseAttributes getReponseAttributes() {
		return reponseAttributes;
	}

	/**
	 * Sets the value of the reponseAttributes property.
	 * 
	 * @param value
	 *            allowed object is {@link Service.ReponseAttributes }
	 * 
	 */
	public void setReponseAttributes(Service.ReponseAttributes value) {
		this.reponseAttributes = value;
	}

	/**
	 * Gets the value of the name property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the name property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setName(String value) {
		this.name = value;
	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained within
	 * this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="response-entry" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;simpleContent>
	 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
	 *                 &lt;attribute name="resname" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *                 &lt;attribute name="resvalue" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *               &lt;/extension>
	 *             &lt;/simpleContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "responseEntry" })
	public static class ReponseAttributes {

		@XmlElement(name = "response-entry")
		protected List<Service.ReponseAttributes.ResponseEntry> responseEntry;

		/**
		 * Gets the value of the responseEntry property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a snapshot.
		 * Therefore any modification you make to the returned list will be present
		 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
		 * for the responseEntry property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getResponseEntry().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link Service.ReponseAttributes.ResponseEntry }
		 * 
		 * 
		 */
		public List<Service.ReponseAttributes.ResponseEntry> getResponseEntry() {
			if (responseEntry == null) {
				responseEntry = new ArrayList<Service.ReponseAttributes.ResponseEntry>();
			}
			return this.responseEntry;
		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content contained within
		 * this class.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;simpleContent>
		 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
		 *       &lt;attribute name="resname" type="{http://www.w3.org/2001/XMLSchema}string" />
		 *       &lt;attribute name="resvalue" type="{http://www.w3.org/2001/XMLSchema}string" />
		 *     &lt;/extension>
		 *   &lt;/simpleContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "value" })
		public static class ResponseEntry {

			@XmlValue
			protected String value;
			@XmlAttribute(name = "resname")
			protected String resname;
			@XmlAttribute(name = "resvalue")
			protected String resvalue;

			/**
			 * Gets the value of the value property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getValue() {
				return value;
			}

			/**
			 * Sets the value of the value property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setValue(String value) {
				this.value = value;
			}

			/**
			 * Gets the value of the resname property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getResname() {
				return resname;
			}

			/**
			 * Sets the value of the resname property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setResname(String value) {
				this.resname = value;
			}

			/**
			 * Gets the value of the resvalue property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getResvalue() {
				return resvalue;
			}

			/**
			 * Sets the value of the resvalue property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setResvalue(String value) {
				this.resvalue = value;
			}

		}

	}

	/**
	 * <p>
	 * Java class for anonymous complex type.
	 * 
	 * <p>
	 * The following schema fragment specifies the expected content contained within
	 * this class.
	 * 
	 * <pre>
	 * &lt;complexType>
	 *   &lt;complexContent>
	 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	 *       &lt;sequence>
	 *         &lt;element name="request-entry" maxOccurs="unbounded" minOccurs="0">
	 *           &lt;complexType>
	 *             &lt;simpleContent>
	 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
	 *                 &lt;attribute name="reqname" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *                 &lt;attribute name="reqvalue" type="{http://www.w3.org/2001/XMLSchema}string" />
	 *               &lt;/extension>
	 *             &lt;/simpleContent>
	 *           &lt;/complexType>
	 *         &lt;/element>
	 *       &lt;/sequence>
	 *     &lt;/restriction>
	 *   &lt;/complexContent>
	 * &lt;/complexType>
	 * </pre>
	 * 
	 * 
	 */
	@XmlAccessorType(XmlAccessType.FIELD)
	@XmlType(name = "", propOrder = { "requestEntry" })
	public static class RequestAttributes {

		@XmlElement(name = "request-entry")
		protected List<Service.RequestAttributes.RequestEntry> requestEntry;

		/**
		 * Gets the value of the requestEntry property.
		 * 
		 * <p>
		 * This accessor method returns a reference to the live list, not a snapshot.
		 * Therefore any modification you make to the returned list will be present
		 * inside the JAXB object. This is why there is not a <CODE>set</CODE> method
		 * for the requestEntry property.
		 * 
		 * <p>
		 * For example, to add a new item, do as follows:
		 * 
		 * <pre>
		 * getRequestEntry().add(newItem);
		 * </pre>
		 * 
		 * 
		 * <p>
		 * Objects of the following type(s) are allowed in the list
		 * {@link Service.RequestAttributes.RequestEntry }
		 * 
		 * 
		 */
		public List<Service.RequestAttributes.RequestEntry> getRequestEntry() {
			if (requestEntry == null) {
				requestEntry = new ArrayList<Service.RequestAttributes.RequestEntry>();
			}
			return this.requestEntry;
		}

		/**
		 * <p>
		 * Java class for anonymous complex type.
		 * 
		 * <p>
		 * The following schema fragment specifies the expected content contained within
		 * this class.
		 * 
		 * <pre>
		 * &lt;complexType>
		 *   &lt;simpleContent>
		 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
		 *       &lt;attribute name="reqname" type="{http://www.w3.org/2001/XMLSchema}string" />
		 *       &lt;attribute name="reqvalue" type="{http://www.w3.org/2001/XMLSchema}string" />
		 *     &lt;/extension>
		 *   &lt;/simpleContent>
		 * &lt;/complexType>
		 * </pre>
		 * 
		 * 
		 */
		@XmlAccessorType(XmlAccessType.FIELD)
		@XmlType(name = "", propOrder = { "value" })
		public static class RequestEntry {

			@XmlValue
			protected String value;
			@XmlAttribute(name = "reqname")
			protected String reqname;
			@XmlAttribute(name = "reqvalue")
			protected String reqvalue;

			/**
			 * Gets the value of the value property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getValue() {
				return value;
			}

			/**
			 * Sets the value of the value property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setValue(String value) {
				this.value = value;
			}

			/**
			 * Gets the value of the reqname property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getReqname() {
				return reqname;
			}

			/**
			 * Sets the value of the reqname property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setReqname(String value) {
				this.reqname = value;
			}

			/**
			 * Gets the value of the reqvalue property.
			 * 
			 * @return possible object is {@link String }
			 * 
			 */
			public String getReqvalue() {
				return reqvalue;
			}

			/**
			 * Sets the value of the reqvalue property.
			 * 
			 * @param value
			 *            allowed object is {@link String }
			 * 
			 */
			public void setReqvalue(String value) {
				this.reqvalue = value;
			}

		}

	}

}
