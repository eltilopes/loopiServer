package br.com.aio.exception;

import br.com.aio.security.entity.Usuario;

public class CpfAlreadyExistsException extends RuntimeException{

	private static final long serialVersionUID = -4741916422784089446L;
	
	private Usuario user;
	
	public CpfAlreadyExistsException(String message, Throwable cause){
		super(message, cause);
	}
	
	
	public CpfAlreadyExistsException(String message, Throwable cause, Usuario user){
		super(message, cause);
		this.user = user;
	}
	
	public CpfAlreadyExistsException(String message, Usuario user){
		super(message);
		this.user = user;
	}
	
	public CpfAlreadyExistsException(Usuario user){
		this.user = user;
	}
	
	public Usuario getUser() {
		return user;
	}

}
