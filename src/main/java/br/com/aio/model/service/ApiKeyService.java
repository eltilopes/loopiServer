package br.com.aio.model.service;

import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

import br.com.aio.model.entity.ApiKey;
import br.com.aio.model.repository.dao.ApiKeyDao;
import br.com.aio.security.entity.Usuario;

@Service
public class ApiKeyService {
	
	@Inject
	private ApiKeyDao dao;
	
	@Inject
	private UsuarioService userService;
	
	public ApiKey save(ApiKey apiKey) throws Exception{		
		Usuario usuario = userService.getUser(apiKey.getLogin());
		apiKey.setUsuarios(Lists.newArrayList(usuario));
		if(!userService.userAlreadyHadThisApiKey(apiKey, usuario)){
			ApiKey foundApiKey = dao.getApiKey(apiKey);
			if(Objects.nonNull(foundApiKey)){
				apiKey.setId(foundApiKey.getId());
				apiKey.getUsuarios().addAll(userService.getUsersByApiKey(apiKey.getId()));
				dao.saveOrUpdateApiKey(apiKey);
			}else{
				dao.saveOrUpdateApiKey(apiKey);
			}
		}
		return apiKey;
	}

}
