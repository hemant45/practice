package com.example.exceptions;
import java.io.Serializable;

public class ErrorDO implements Serializable {

	private static final long serialVersionUID = 3612420651637127017L;
	private String key = null;
	private transient Object[] params = null;

	public String getKey() {
		return this.key;
	}

	public Object[] getParams() {
		return this.params;
	}

	public ErrorDO() {

	}

	public ErrorDO(String pKey, Object[] pParams) {
		this.key = pKey;
		this.params = pParams;
	}

}