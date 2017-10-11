package br.com.aio.model.repository.rest;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

import br.com.aio.security.entity.Usuario;
import br.com.aio.security.entity.vo.User;
import br.com.aio.security.factory.UserFactory;

@Service
public class UsuarioRestRepository {
	
//	@Inject
//	private UserGlpiConsumer consumer;
//	
//	@Inject
//	private GlpiRestTemplate glpiRestTemplate;
	
	@Inject
	private ObjectMapper objectMapper;
	
	public Usuario salvar(Usuario usuario) throws Exception{
		User user = UserFactory.createUserFrom(usuario);
		
		//MultiValueMap<String, String> params = consumer.createUser(user);
		//URI uri = consumer.glpiRootWebserviceUrl();
		//ResponseEntity<String> response = glpiRestTemplate.postForEntity(uri, params, String.class);
		
		JavaType list = objectMapper.getTypeFactory().constructCollectionType(List.class, User.class);
		ObjectReader objectReader = objectMapper.readerFor(list).withRootName("User");
		//List<User> result = objectReader.readValue(response.getBody());
		//usuario.setIdUsuarioGlpi(result.stream().findFirst().get().getId());
		
		return usuario;
	}
	
//	public User buscar(String login) throws Exception{
//		URI uri = consumer.listUsers(login);
//		//ResponseEntity<String> response = glpiRestTemplate.getForEntity(uri, String.class);
//		
//		JavaType list = objectMapper.getTypeFactory().constructCollectionType(List.class, User.class);
//		ObjectReader objectReader = objectMapper.readerFor(list);
//		List<User> result = objectReader.readValue(response.getBody());
//		
//		if(result.isEmpty()){
//			return null;
//		}
//		
//		return result.get(0);
//	}

}
