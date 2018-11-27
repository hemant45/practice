package com.example.practice;


public enum HTTPResponseStatusMessage {

	
	STATUS_200 (200,"Connection details are valid"),
	STATUS_400 (400,"Invalid Request"),	
	STATUS_401 (401,"Username or Password is incorrect"),	
	STATUS_404 (404,"Requested resource not found"),	
	STATUS_500 (500,"Server could not process the request");
	
	private final int code;
	private final String respMessage;
	
	HTTPResponseStatusMessage(int code, String respMessage){
		this.code=code;
		this.respMessage=respMessage;
	}
	
	public String getMessage() {
		return respMessage;
	}
	
	public static HTTPResponseStatusMessage getMessage(int code) {
		for(HTTPResponseStatusMessage respMessage:values()) {
						
			if(respMessage.code==code) {
				return respMessage;
			}
		}
		throw new RuntimeException("Connection details are not valid");
	}
	
}
