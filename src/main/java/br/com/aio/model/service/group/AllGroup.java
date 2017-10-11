package br.com.aio.model.service.group;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.model.repository.hibernate.UsuarioRepository;
import br.com.aio.security.entity.Usuario;

@Service
public class AllGroup implements Callable<List<Usuario>>{

	@Inject
	public UsuarioRepository usuarioRepositorio;
	
	@Override
	public List<Usuario> call() throws Exception {
		return usuarioRepositorio.getUsers();
	}

}
