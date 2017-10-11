package br.com.aio.model.service.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import br.com.aio.model.entity.enums.GroupDefault;
import br.com.aio.model.service.UsuarioService;
import br.com.aio.security.entity.Usuario;


public abstract class AbstractGroupDefault implements Callable<List<Usuario>> {

	@Inject
	public UsuarioService usuarioService;
	
	@Override
	public List<Usuario> call() throws Exception {
		Map<String, Integer> parameters = new HashMap<>();
		parameters.put(getGroup().getName().toLowerCase(), 1);
		return usuarioService.getUsersByGroupDefault(parameters);
	}
	
	abstract GroupDefault getGroup();

}
