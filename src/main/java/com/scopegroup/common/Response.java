package com.scopegroup.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

/**
 * @author maysara.mohammed
 * @version 1.0
 * @since 2021-06-11
 */

public class Response {
	private String message; 
	private HashMap<String, Integer> result;
	private int status; 
	private String timeStamp;
	
	/**
	 * 
	 */
	public Response() {
		super();
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		timeStamp = now.format(formatter);
	}
	/**
	 * @param message
	 * @param status
	 */
	public Response(String message, HashMap<String, Integer> result) {
		super();
		this.message = message;
		this.result = result;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the result
	 */
	public HashMap<String, Integer> getResult() {
		return result;
	}
	/**
	 * @param status the status to set
	 */
	public void setResult(HashMap<String, Integer> result) {
		this.result = result;
	}
	/**
	 * @return the timeStamp
	 */
	public String getTimeStamp() {
		return timeStamp;
	}
	
	/**
	 * @return the code
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param code the code to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}


}
