package br.com.aio.model.validation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;

public class RestErrorWrapper {
	
	private Integer code;
	
	private List<RestError> errors = new ArrayList<>();

	public RestErrorWrapper() { }

	public void addError(String path, String message) {
		RestError error = new RestError(path, message);
		errors.add(error);
	}

	public List<RestError> getErrors() {
		return errors;
	}

	public void setErrors(List<RestError> errors) {
		this.errors = errors;
	}
	
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public RestErrorBuilder withCode(Integer code){
		RestErrorBuilder errorBuilder = new RestErrorBuilder();
		this.code = code;
		this.restErrorBuilders.add(errorBuilder);
		return errorBuilder;
	}
	
	@JsonIgnore
	private List<RestErrorBuilder> restErrorBuilders = new ArrayList<RestErrorBuilder>();
	
	@JsonIgnoreType
	public class RestErrorBuilder{
		
		private String field;
		private String message;
	
		private RestError create(){
			RestError restError = new RestError();
			restError.setField(field);
			restError.setMessage(message);
			return restError;
		}
		
		public RestErrorBuilder field(String field){
			this.field = field;
			return this;
		}
		
		public RestErrorBuilder message(String message){
			this.message = message;
			return this;
		}
		
		public RestErrorWrapper build(){
			errors.add(this.create());
			return RestErrorWrapper.this;
		}
		
	}

}
