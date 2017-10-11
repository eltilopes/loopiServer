package br.com.aio.security.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.error.DefaultOAuth2ExceptionRenderer;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import br.com.aio.model.validation.RestErrorCode;
import br.com.aio.model.validation.RestErrorWrapper;
import br.com.aio.util.ExceptionMessages;

@Component
public class ClientRegistrationHandler {
	
	private OAuth2ExceptionRenderer exceptionRenderer = new DefaultOAuth2ExceptionRenderer();

	public void handle(HttpServletRequest request, HttpServletResponse response) 
			throws IOException,
			ServletException {
		
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.CLIENT_REGISTRATION.getValue())
			.message(RestErrorCode.CLIENT_REGISTRATION.getErrorMessage())
			.build();
		
		try {
			ResponseEntity<RestErrorWrapper> result = new ResponseEntity<RestErrorWrapper>(errorWrapper, HttpStatus.BAD_REQUEST);
			exceptionRenderer.handleHttpEntityResponse(result, new ServletWebRequest(request, response));
		} catch (Exception e) {
			throw new RuntimeException(ExceptionMessages.INTERNAL_SERVER_ERROR);
		}
	}

}
