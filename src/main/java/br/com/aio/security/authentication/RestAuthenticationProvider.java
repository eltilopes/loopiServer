package br.com.aio.security.authentication;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import br.com.aio.model.service.UsuarioService;
import br.com.aio.security.entity.Role;
import br.com.aio.security.entity.UsuarioAuth;
import br.com.aio.security.service.RoleService;
import br.com.aio.util.ExceptionMessages;

public class RestAuthenticationProvider implements AuthenticationProvider {

	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private RoleService roleService;

	//TODO: ajustar consultas para pegar usuario e as roles
	@SuppressWarnings("unchecked")
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		LinkedHashMap<String, String> deatils = (LinkedHashMap<String, String>) auth.getDetails();
		String senha = DigestUtils.md5Hex((String)auth.getCredentials());
		UsuarioAuth usuario = usuarioService
				.getUser((String)auth.getPrincipal(), senha, deatils.get("client_id"));
		
		if (usuario == null) {
			throw new BadCredentialsException(ExceptionMessages.BAD_CREDENTIALS);
		} 
//		List<Role> roles = roleService.getRoles(usuario.getId());
		List<Role> roles = roleService.getRoles();
//		List<Role> roles = roleService.getRoles();
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.forEach(role -> { 
			authorities.add(role.simpleAuthority());
		});
			usuario.setRoles(roles);
		System.out.println("Login: " + auth.getPrincipal());
		return new RestToken(usuario, null, authorities);
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
