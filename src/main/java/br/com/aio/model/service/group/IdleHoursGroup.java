package br.com.aio.model.service.group;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.model.repository.dao.UsuarioDao;
import br.com.aio.security.entity.Usuario;

@Service
public class IdleHoursGroup implements Callable<List<Usuario>>{

	@Inject
	public UsuarioDao usuarioDao;
	
	@Override
	public List<Usuario> call() throws Exception {
		return usuarioDao.getUserIdleHours();
	}

}
