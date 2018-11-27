package com.example.exceptions;

import java.util.ArrayList;
import java.util.List;

public class EkaException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ErrorDO> errors = new ArrayList<ErrorDO>();

	public EkaException() {
		super();
	}
	
	public EkaException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public EkaException(Throwable cause) {
		super(cause);
	}
	
	public EkaException(String errorCode, Object[] params) {
		accumulateError(errorCode, params);
	}
	
	public EkaException(String message) {
		super(message);
	}

	public void addError(String errorCode, Object[] params) {
		accumulateError(errorCode, params);
	}

	public void addError(String errorCode) {
		accumulateError(errorCode, null);
	}

	public boolean isEmptyException() {
		return this.errors.isEmpty();
	}

	public List<ErrorDO> getErrors() {
		return this.errors;
	}
	
	protected void accumulateError(String errorCode, Object[] params) {		
		this.errors.add(new ErrorDO(errorCode, getSafeParameters(params)));
	}
	
	protected Object[] getSafeParameters(Object[] params) {
		Object[] parameters = params;
		if(parameters == null) {
			parameters = new Object[]{};
		}
		return parameters;
	}
}