package br.com.aio.security.oauth;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

public class BaseClientDetaisBuilder {

	private String clientId;
	
	private String secret;

	private Collection<String> authorizedGrantTypes = new LinkedHashSet<String>();

	public ClientDetails build() {
		BaseClientDetails result = new BaseClientDetails();
		result.setClientId(clientId);
		result.setAuthorizedGrantTypes(authorizedGrantTypes);
		result.setClientSecret(secret);
		
		return result;
	}

	public BaseClientDetaisBuilder authorizedGrantTypes(String... authorizedGrantTypes) {
		for (String grant : authorizedGrantTypes) {
			this.authorizedGrantTypes.add(grant);
		}
		return this;
	}

	public BaseClientDetaisBuilder secret(String secret) {
		this.secret = secret;
		return this;
	}
	
	public BaseClientDetaisBuilder withClient(String clientId) {
		this.clientId = clientId;
		return this;
	}

}
