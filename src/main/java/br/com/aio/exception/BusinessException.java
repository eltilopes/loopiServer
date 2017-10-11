package br.com.aio.exception;

public class BusinessException extends RuntimeException{
	
	private static final long serialVersionUID = -6791980237836588057L;

	public BusinessException(){
		super();
	}
	
	public BusinessException(String message, Throwable cause){
		super(message, cause);
	}
	
	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(Throwable cause){
		super(cause);
	}

}
