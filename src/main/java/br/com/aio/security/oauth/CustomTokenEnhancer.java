package br.com.aio.security.oauth;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.aio.model.repository.dao.UsuarioDao;
import br.com.aio.security.entity.UsuarioAuth;
import br.com.aio.security.service.RoleService;

public class CustomTokenEnhancer implements TokenEnhancer{

	private List<TokenEnhancer> delegates = Collections.emptyList();
	
	@Inject
	private RoleService roleService;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	public void setTokenEnhancers(List<TokenEnhancer> delegates) {
	    this.delegates = delegates;
	}

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
		final Map<String, Object> userInformation = new LinkedHashMap<String, Object>(); 
		
		//userInformation.put("roles", roleService.getRoles(authentication.getPrincipal().toString()));
		userInformation.put("roles", ((UsuarioAuth)authentication.getPrincipal()).getRoles());
		//userInformation.put("user", usuarioDao.getUser(authentication.getPrincipal().toString()));
		userInformation.put("user", authentication.getPrincipal());
		
		token.setAdditionalInformation(userInformation);
		OAuth2AccessToken result = token;
		
		for (TokenEnhancer enhancer : delegates) {
	        result = enhancer.enhance(result, authentication);
	    }
		
	    return result;
	}

}
