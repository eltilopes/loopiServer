package br.com.aio.exception;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.aio.model.validation.RestErrorCode;
import br.com.aio.model.validation.RestErrorWrapper;

@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class RestExceptionHandler {
	
	private static final Logger logger = Logger.getLogger(RestExceptionHandler.class);

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.NOT_FOUND.getValue())
			.message(RestErrorCode.NOT_FOUND.getErrorMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleGlobalRuntimeException(RuntimeException ex) {
		if(ex instanceof ConversionFailedException){
			return this.handleConversionFailedException();
		}else{
			RestErrorWrapper errorWrapper = new RestErrorWrapper()
				.withCode(RestErrorCode.INTERNAL_ERROR.getValue())
				.message(RestErrorCode.INTERNAL_ERROR.getErrorMessage())
				.build();
			logger.error(ex.getStackTrace());
			return new ResponseEntity<Object>(errorWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleGlobalException(Exception ex) {
		if(ex instanceof MissingServletRequestParameterException){
			return this.handleMissingServletRequestParameterException();
		}else{
			RestErrorWrapper errorWrapper = new RestErrorWrapper()
				.withCode(RestErrorCode.INTERNAL_ERROR.getValue())
				.message(RestErrorCode.INTERNAL_ERROR.getErrorMessage())
				.build();
			logger.error(ex.getStackTrace());
			return new ResponseEntity<Object>(errorWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleUserAlreadyExistsException(UserAlreadyExistsException ex){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.USER_EXISTS.getValue())
			.message(RestErrorCode.USER_EXISTS.getErrorMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleBadCredentialsException(BadCredentialsException ex){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.LOGIN_FAILURE.getValue())
			.message(RestErrorCode.LOGIN_FAILURE.getErrorMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.LOGIN_FAILURE.getValue())
			.message(RestErrorCode.LOGIN_FAILURE.getErrorMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.LOGIN_FAILURE.getValue())
			.message(RestErrorCode.LOGIN_FAILURE.getErrorMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleInvalidGrantException(InvalidGrantException ex){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.LOGIN_FAILURE.getValue())
			.message(RestErrorCode.LOGIN_FAILURE.getErrorMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleGenericBusinessException(BusinessException ex){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.BUSSINESS_FAILURE.getValue())
			.message(ex.getMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.METHOD_NOT_ALLOWED.getValue())
			.message(RestErrorCode.METHOD_NOT_ALLOWED.getErrorMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler
	@ResponseBody
	public ResponseEntity<Object> handleCpfAlreadyExistsException(CpfAlreadyExistsException e){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.CPF_USED.getValue())
			.message("O CFP informado já foi cadastrado com o email " + e.getUser().getLogin())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.CONFLICT);
	}

	public ResponseEntity<Object> handleConversionFailedException(){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.WRONG_TYPE.getValue())
			.message(RestErrorCode.WRONG_TYPE.getErrorMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<Object> handleMissingServletRequestParameterException(){
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.NOT_FOUND.getValue())
			.message(RestErrorCode.NOT_FOUND.getErrorMessage())
			.build();
		return new ResponseEntity<Object>(errorWrapper, HttpStatus.NOT_FOUND);
	}
}
