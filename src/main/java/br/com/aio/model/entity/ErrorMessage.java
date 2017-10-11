package br.com.aio.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class ErrorMessage {
	
	private String error;
	
	@JsonProperty("error_description")
	private String errorDescription;

    public ErrorMessage() {}

    public ErrorMessage(String error, String errorDescription) {
       this.error = error;
       this.errorDescription = errorDescription;
    }
    
    public ErrorMessage(String errorDescription) {
       this.errorDescription = errorDescription;
    }

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
    
}
