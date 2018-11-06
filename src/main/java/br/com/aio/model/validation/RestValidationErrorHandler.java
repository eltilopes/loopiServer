package br.com.aio.model.validation;

import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestValidationErrorHandler {
	
	private MessageSource messageSource;
	
	@Inject
	public RestValidationErrorHandler(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	@ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<RestErrorWrapper> processValidationError(Exception ex) {   
		BindingResult result = null;
		if(ex instanceof BindException){
			result = ((BindException) ex).getBindingResult();
			
		}
		else if(ex instanceof MethodArgumentNotValidException){
			result = ((MethodArgumentNotValidException) ex).getBindingResult();
		}
		System.out.println(ex.getMessage());
        return new ResponseEntity<RestErrorWrapper>(getErrors(result), HttpStatus.BAD_REQUEST);
    }
	
	private RestErrorWrapper getErrors(BindingResult result){
		RestErrorWrapper errorWrapper = new RestErrorWrapper();
	    errorWrapper.setCode(RestErrorCode.FIELD_ERROR.getValue());
	    for (FieldError error: result.getFieldErrors()) {
            errorWrapper.addError(error.getField(), getErrorMessage(error));
        }
	    return errorWrapper;
	}
 
    private String getErrorMessage(FieldError fieldError) {
        Locale currentLocale =  LocaleContextHolder.getLocale();
        String errorMessage = messageSource.getMessage(fieldError, currentLocale);
        return errorMessage;
    }

}
