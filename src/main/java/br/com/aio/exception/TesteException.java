package br.com.aio.exception;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

public class TesteException extends OAuth2Exception{

	private static final long serialVersionUID = -999596560804695574L;

	public TesteException(String msg) {
		super(msg);
	}

}
