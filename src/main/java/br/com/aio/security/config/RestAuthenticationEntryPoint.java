package br.com.aio.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.error.DefaultOAuth2ExceptionRenderer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.error.OAuth2ExceptionRenderer;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.ServletWebRequest;

import br.com.aio.model.validation.RestErrorCode;
import br.com.aio.model.validation.RestErrorWrapper;

public class RestAuthenticationEntryPoint extends OAuth2AuthenticationEntryPoint{
	
	private OAuth2ExceptionRenderer exceptionRenderer = new DefaultOAuth2ExceptionRenderer();
	
	private String typeName = OAuth2AccessToken.BEARER_TYPE;

	private String realmName = "oauth";

	public void setRealmName(String realmName) {
		this.realmName = realmName;
	}
	
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		
		RestErrorWrapper errorWrapper = new RestErrorWrapper()
			.withCode(RestErrorCode.LOGIN_FAILURE.getValue())
			.message(RestErrorCode.LOGIN_FAILURE.getErrorMessage())
			.build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Cache-Control", "no-store");
		headers.set("Pragma", "no-cache");
		//headers.set("WWW-Authenticate", String.format("%s %s", OAuth2AccessToken.BEARER_TYPE));
			
		try {
			ResponseEntity<RestErrorWrapper> result = new ResponseEntity<RestErrorWrapper>(errorWrapper, headers, HttpStatus.UNAUTHORIZED);
			result = bindResponse(result, authException);
			exceptionRenderer.handleHttpEntityResponse(result, new ServletWebRequest(request, response));
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected ResponseEntity<RestErrorWrapper> bindResponse(ResponseEntity<RestErrorWrapper> response,
			Exception authException) {
		
		HttpHeaders headers = response.getHeaders();
		String existing = null;
		if (headers.containsKey("WWW-Authenticate")) {
			existing = extractTypePrefix(headers.getFirst("WWW-Authenticate"));
		}
		StringBuilder builder = new StringBuilder();
		builder.append(typeName+" ");
		builder.append("realm=\"" + realmName + "\"");
		if (existing!=null) {
			builder.append(", "+existing);
		}
		HttpHeaders update = new HttpHeaders();
		update.putAll(response.getHeaders());
		update.set("WWW-Authenticate", builder.toString());
		return new ResponseEntity<RestErrorWrapper>(response.getBody(), update, response.getStatusCode());
	}
	
	private String extractTypePrefix(String header) {
		String existing = header;
		String[] tokens = existing.split(" +");
		if (tokens.length > 1 && !tokens[0].endsWith(",")) {
			existing = StringUtils.arrayToDelimitedString(tokens, " ").substring(existing.indexOf(" ") + 1);
		}
		return existing;
	}

}
