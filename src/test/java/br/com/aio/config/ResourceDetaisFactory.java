package br.com.aio.config;

import java.util.Arrays;

import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;

public class ResourceDetaisFactory {
	
	private static ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
	
	public static ResourceOwnerPasswordResourceDetails getDetais(){
		resource.setAccessTokenUri("http://localhost:8086/aio/oauth/token");
		resource.setClientId("smemobile");
		resource.setClientSecret("lamperouge");
		resource.setScope(Arrays.asList("read"));
		resource.setGrantType("password");
		resource.setUsername("kuroyukihime@gmail.com");
		resource.setPassword("12345678");
		
		return resource;
	}
	
	public static ResourceOwnerPasswordResourceDetails getDetais(String username, String password){
		resource.setAccessTokenUri("http://localhost:8086/aio/oauth/token");
		resource.setClientId("smemobile");
		resource.setClientSecret("lamperouge");
		resource.setScope(Arrays.asList("read"));
		resource.setGrantType("password");
		resource.setUsername(username);
		resource.setPassword(password);
		
		return resource;
	}
	

}
