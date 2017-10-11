package br.com.aio.security.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class RestToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = -4004210451416380335L;

	public RestToken(String key, String credentials) {
        super(key, credentials);
    }

    public RestToken(Object key, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(key, credentials, authorities);
    }

    public String getLogin() {
        return (String) super.getPrincipal();
    }

    public String getSenha() {
        return (String) super.getCredentials();
    }

}
