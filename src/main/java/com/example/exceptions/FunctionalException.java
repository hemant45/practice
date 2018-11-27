package com.example.exceptions;

import java.util.Map;


public class FunctionalException extends EkaException {

	
	private static final long serialVersionUID = 1L;
	private String message;
	private Map<String,Object> paramsMap;
	
	public FunctionalException() {
		
	}
	
	public FunctionalException(String message) {
		super(message);
		this.message = message;
	}
	public FunctionalException(String message,Map<String,Object> paramsMap) {
		super(message);
		this.message = message;
		this.paramsMap = paramsMap;
	}
	
	public FunctionalException(String errorCode, Throwable t) {
		super(errorCode, t);
		this.message = errorCode;
	}

	public FunctionalException(String errorCode, Object[] params) {
		super(errorCode, params);
		this.message = errorCode;
	}
	
	@Override
	public String getMessage() {
	    return this.message;
	}
	
	public Map<String,Object> getParamsMap() {
	    return this.paramsMap;
	}

}
