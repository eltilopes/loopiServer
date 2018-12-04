package br.com.aio.model.validation;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class RestError {

	private String field;

	private String message;
	
	public RestError(){
		
	}

	public RestError(String field, String message) {
		System.out.println("field / " + field + "  --  message / " + message);
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		System.out.println("field / " + field);
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		System.out.println("message / " + message);
		this.message = message;
	}

}
