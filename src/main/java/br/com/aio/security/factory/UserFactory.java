package br.com.aio.security.factory;

import br.com.aio.security.entity.Usuario;
import br.com.aio.security.entity.vo.User;

public class UserFactory {
	
	public static User createUserFrom(Usuario usuario){
		User user = new User().newUserWith().name(usuario.getLogin())
				.password(usuario.getSenha())
				.realName(usuario.getNome())
				.firstname(usuario.getNome())
				.build();
		
		return user;
	}

}
