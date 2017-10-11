package br.com.aio.exception;

public class UserNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -4741916422784089446L;
	
	public UserNotFoundException(){
		super();
	}
	
	public UserNotFoundException(String message, Throwable cause){
		super(message, cause);
	}
	
	public UserNotFoundException(String message){
		super(message);
	}
	
	public UserNotFoundException(Throwable cause){
		super(cause);
	}

}