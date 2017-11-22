package com.sonata.restautomate.util;

/**
 * This Exception class is used to validate basic Parameters from request
 */
public class ParametersMissingException extends Exception {

	private static final long serialVersionUID = 1L;
	private String message = null;

	public ParametersMissingException() {
		super();
	}

	public ParametersMissingException(String message) {
		super(message);
		this.message = message;
	}

	public ParametersMissingException(Throwable cause) {
		super(cause);
	}

	@Override
	public String toString() {
		return message;
	}

	@Override
	public String getMessage() {
		return message;
	}
}
