package br.com.aio.exception;

public class UserAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = -4741916422784089446L;
	
	public UserAlreadyExistsException(){
		super();
	}
	
	public UserAlreadyExistsException(String message, Throwable cause){
		super(message, cause);
	}
	
	public UserAlreadyExistsException(String message){
		super(message);
	}
	
	public UserAlreadyExistsException(Throwable cause){
		super(cause);
	}

}
