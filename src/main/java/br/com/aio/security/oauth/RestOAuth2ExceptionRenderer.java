package br.com.aio.security.oauth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.oauth2.http.converter.jaxb.JaxbOAuth2ExceptionMessageConverter;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import br.com.aio.model.validation.RestErrorCode;
import br.com.aio.model.validation.RestErrorWrapper;

public class RestOAuth2ExceptionRenderer implements OAuth2ExceptionRenderer{

	private final Logger logger = Logger.getLogger(RestOAuth2ExceptionRenderer.class);
	
	private List<HttpMessageConverter<?>> messageConverters = geDefaultMessageConverters();

	public void setMessageConverters(List<HttpMessageConverter<?>> messageConverters) {
		this.messageConverters = messageConverters;
	}
	
	@Override
	public void handleHttpEntityResponse(HttpEntity<?> responseEntity,
			ServletWebRequest webRequest) throws Exception {
		if (responseEntity == null) {
			return;
		}
		HttpInputMessage inputMessage = createHttpInputMessage(webRequest);
		HttpOutputMessage outputMessage = createHttpOutputMessage(webRequest);
		ResponseEntity<?> response = null;
		
		if (responseEntity instanceof ResponseEntity && outputMessage instanceof ServerHttpResponse) {
			response = (ResponseEntity<?>) responseEntity;
			((ServerHttpResponse) outputMessage).setStatusCode(response.getStatusCode());
		}
		HttpHeaders entityHeaders = responseEntity.getHeaders();
		if (!entityHeaders.isEmpty()) {
			outputMessage.getHeaders().putAll(entityHeaders);
		}
		
		Object body = responseEntity.getBody();
		if (body != null) {
			HttpStatus httpStatusCode = response.getStatusCode();
			RestErrorCode errorCode = RestErrorCode.GET.getByHttpStatus(httpStatusCode);	
			RestErrorWrapper restError = new RestErrorWrapper().withCode(errorCode.getValue()).message(errorCode.getErrorMessage()).build();
			
			ResponseEntity<?> resp = new ResponseEntity<>(restError, response.getHeaders(), httpStatusCode);
			writeWithMessageConverters(resp.getBody(), inputMessage, outputMessage);
		}
		else {
			outputMessage.getBody();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void writeWithMessageConverters(Object returnValue, HttpInputMessage inputMessage,
			HttpOutputMessage outputMessage) throws IOException, HttpMediaTypeNotAcceptableException {
		List<MediaType> acceptedMediaTypes = inputMessage.getHeaders().getAccept();
		if (acceptedMediaTypes.isEmpty()) {
			acceptedMediaTypes = Collections.singletonList(MediaType.ALL);
		}
		MediaType.sortByQualityValue(acceptedMediaTypes);
		Class<?> returnValueType = returnValue.getClass();
		List<MediaType> allSupportedMediaTypes = new ArrayList<MediaType>();
		for (MediaType acceptedMediaType : acceptedMediaTypes) {
			for (HttpMessageConverter messageConverter : messageConverters) {
				if (messageConverter.canWrite(returnValueType, acceptedMediaType)) {
					messageConverter.write(returnValue, acceptedMediaType, outputMessage);
					if (logger.isDebugEnabled()) {
						MediaType contentType = outputMessage.getHeaders().getContentType();
						if (contentType == null) {
							contentType = acceptedMediaType;
						}
						logger.debug("Written [" + returnValue + "] as \"" + contentType + "\" using ["
								+ messageConverter + "]");
					}
					return;
				}
			}
		}
		for (HttpMessageConverter messageConverter : messageConverters) {
			allSupportedMediaTypes.addAll(messageConverter.getSupportedMediaTypes());
		}
		throw new HttpMediaTypeNotAcceptableException(allSupportedMediaTypes);
	}
	
	private List<HttpMessageConverter<?>> geDefaultMessageConverters() {
		List<HttpMessageConverter<?>> result = new ArrayList<HttpMessageConverter<?>>();
		result.addAll(new RestTemplate().getMessageConverters());
		result.add(new JaxbOAuth2ExceptionMessageConverter());
		return result;
	}

	private HttpInputMessage createHttpInputMessage(NativeWebRequest webRequest) throws Exception {
		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		return new ServletServerHttpRequest(servletRequest);
	}

	private HttpOutputMessage createHttpOutputMessage(NativeWebRequest webRequest) throws Exception {
		HttpServletResponse servletResponse = (HttpServletResponse) webRequest.getNativeResponse();
		return new ServletServerHttpResponse(servletResponse);
	}

}
