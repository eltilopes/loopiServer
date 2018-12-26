package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.exception.BusinessException;
import br.com.aio.exception.CpfAlreadyExistsException;
import br.com.aio.exception.UserAlreadyExistsException;
import br.com.aio.exception.UserNotFoundException;
import br.com.aio.model.entity.ApiKey;
import br.com.aio.model.service.ApiKeyService;
import br.com.aio.model.service.UsuarioService;
import br.com.aio.security.entity.Role;
import br.com.aio.security.entity.Usuario;
import br.com.aio.util.ExceptionMessages;

/**
 * 
 * @author Stanley Albuquerque
 *
 */

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private ApiKeyService apiKeyService;
	
	@RequestMapping(value = "/novo", method = POST)
	public ResponseEntity<Usuario> saveUser (@Valid Usuario user){
		String login = user.getLogin();
		try {
			usuarioService.saveUser(user);
			user.setSenha(null);
			return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
		} catch (CpfAlreadyExistsException e) {
			user.setId(usuarioService.getUserByCpf(user.getCpf()).getId());
			user.setLogin(login);
			usuarioService.preUpdateUser(user);
			return new ResponseEntity<Usuario>(user, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} catch (UserAlreadyExistsException e) {
			throw new UserAlreadyExistsException(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/editar", method = POST)
	public ResponseEntity<Usuario> editUser(@Valid @RequestBody Usuario user){
		if(Objects.isNull(user.getId())){
			throw new BusinessException(ExceptionMessages.ID_REQUIRED);
		}
		Usuario userOld = usuarioService.getUserByCpf(user.getCpf());
		if(Objects.isNull(userOld)){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try{
			usuarioService.updateUser(user, userOld.getLogin());
		}catch(UserNotFoundException e){
			throw new BusinessException(e.getMessage());
		}
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/resgatar/senha", method = POST)
	public ResponseEntity<String> passwordRecover(@RequestParam String senha, @RequestParam String cpf){
		Usuario user = usuarioService.getUserByCpf(cpf);
		if(Objects.isNull(user)){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		user.setSenha(senha);
		usuarioService.preUpdatePassword(user);
		return new ResponseEntity<String>(user.getLogin(), HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/roles", method = GET)
	public ResponseEntity<List<Role>> getUserRoles(@RequestParam("login") String login){
		List<Role> roles = usuarioService.getRoleService().getRoles(login);
		if(Objects.isNull(roles)){ 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/apikey", method = POST)
	public ResponseEntity<ApiKey> salvarApiKey(@Valid ApiKey apiKey) throws Exception{
		return new ResponseEntity<ApiKey>(apiKeyService.save(apiKey), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/listar")
	public ResponseEntity<List<Usuario>> getUsers(
			@RequestParam(required  = false) String cpf, 
			@RequestParam(required = false) String nome){
		List<Usuario> usuarios = usuarioService.getUsers(cpf, "elt");
		if(usuarios.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/admin/novo", method = RequestMethod.POST)
	public ResponseEntity<Usuario> createAdminUser(Usuario usuario){
		usuarioService.saveUser(usuario);
		usuario.setSenha(null);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/buscar")
	public ResponseEntity<Usuario> getUser(@RequestParam("login") String login){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getPrincipal().toString().equals(login)){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} 
		
		Usuario usuario = usuarioService.getUser(login);
		if(Objects.isNull(usuario)){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		usuario.setSenha(null);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/logout/{login:.+}", method= RequestMethod.DELETE)
	public ResponseEntity<Usuario> logout(@PathVariable("login") String login){
		usuarioService.logout(login);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@RequestMapping(value = "/logout", method = GET)
	public ResponseEntity<String> usuarioLogout(@RequestParam("cpf") String cpf){
		Usuario usuario = usuarioService.getUserByCpf(cpf);
		usuarioService.logout(usuario.getLogin());
		return new ResponseEntity<String>(cpf, HttpStatus.OK);
	}
}
