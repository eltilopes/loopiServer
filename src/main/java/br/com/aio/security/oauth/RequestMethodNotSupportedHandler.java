package br.com.aio.security.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
public class RequestMethodNotSupportedHandler {
	
	private OAuth2ExceptionRenderer exceptionRenderer = new DefaultOAuth2ExceptionRenderer();
	private static final Logger logger = Logger.getLogger(RequestMethodNotSupportedHandler.class);
	
	public void handle(HttpServletRequest request, HttpServletResponse response) 
			throws IOException,
			ServletException {
		
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.METHOD_NOT_ALLOWED.getValue())
			.message(RestErrorCode.METHOD_NOT_ALLOWED.getErrorMessage())
			.build();
		
		try {
			ResponseEntity<RestErrorWrapper> result = new ResponseEntity<RestErrorWrapper>(errorWrapper, HttpStatus.METHOD_NOT_ALLOWED);
			exceptionRenderer.handleHttpEntityResponse(result, new ServletWebRequest(request, response));
		} catch (Exception e) {

			logger.error(e.toString());
			throw new RuntimeException(ExceptionMessages.INTERNAL_SERVER_ERROR);
		}
	}

}
