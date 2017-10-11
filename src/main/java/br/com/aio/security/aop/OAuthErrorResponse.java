package br.com.aio.security.aop;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.com.aio.security.oauth.ClientRegistrationHandler;
import br.com.aio.security.oauth.RequestMethodNotSupportedHandler;
import br.com.aio.security.oauth.UnauthorizedHandler;

/**
 * 
 * @author Stanley Albuquerque
 *
 */

public class OAuthErrorResponse {
	
	private static final Logger logger = Logger.getLogger(OAuthErrorResponse.class);
	
	@Inject
	private UnauthorizedHandler unauthorizedHandler;
	
	@Inject
	private RequestMethodNotSupportedHandler requestMethodNotSupportedHandler;
	
	@Inject
	private ClientRegistrationHandler clientRegistrationHandler;
	
	public void oAuthErrorIntercept(final ProceedingJoinPoint joinPoint){
		Object[] args = joinPoint.getArgs();
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		
		try {
			for(Object obj : args){
				if(obj instanceof InvalidGrantException){
					callUnauthorizedHandler(request, response);
				}
				if(obj instanceof HttpRequestMethodNotSupportedException){
					callHttpRequestMethodNotSupportedHandler(request, response);
				}
				if(obj instanceof ClientRegistrationException){
					callClientRegistrationHandler(request, response);
				}
				if(obj instanceof Exception){
					throw new RuntimeException();
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}
	
	public void callUnauthorizedHandler(HttpServletRequest request, HttpServletResponse response) throws Exception{
		unauthorizedHandler.handle(request, response);
	}
	
	public void callHttpRequestMethodNotSupportedHandler(HttpServletRequest request, HttpServletResponse response) throws Exception{
		requestMethodNotSupportedHandler.handle(request, response);
	}
	
	public void callClientRegistrationHandler(HttpServletRequest request, HttpServletResponse response) throws Exception{
		clientRegistrationHandler.handle(request, response);
	}

}
