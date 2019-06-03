package com.kd.tv.exception;

public class GenericServiceException extends RuntimeException {

	private static final long serialVersionUID = 4024864088214822555L;

	public GenericServiceException() {
		super();
	}

	public GenericServiceException(String message) {
		super(message);
	}
}
