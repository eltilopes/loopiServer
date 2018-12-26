package br.com.aio.model.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.exception.BusinessException;
import br.com.aio.exception.CpfAlreadyExistsException;
import br.com.aio.exception.UserAlreadyExistsException;
import br.com.aio.exception.UserNotFoundException;
import br.com.aio.model.entity.ApiKey;
import br.com.aio.model.entity.Convite;
import br.com.aio.model.entity.Mail;
import br.com.aio.model.repository.dao.UsuarioDao;
import br.com.aio.model.repository.hibernate.UsuarioRepository;
import br.com.aio.security.entity.Usuario;
import br.com.aio.security.entity.UsuarioAuth;
import br.com.aio.security.service.RoleService;
import br.com.aio.util.ExceptionMessages;

@Service
public class UsuarioService {
	
	@Inject
	private SecurityQuestionService securityQuestionService;
	@Inject
	private UsuarioRepository repository;
	@Inject
	private UsuarioDao usuarioDao;
	@Inject
	private RoleService roleService;
	@Inject
	private MailManager mailManager;

    private static final String CLIENT_LOOPI = "appLoopi";
    
	public UsuarioAuth getUser(String login, String senha, String client) {
		//TODO: ajustar regra de pegar o usuario que ira logar na app
		if(CLIENT_LOOPI.equals(client)){
			return usuarioDao.getUser(login, senha);	
		}else {
			UsuarioAuth u = new UsuarioAuth();
			u.setId(1l);
			u.setLogin("eltilopes@gmail.com");
			u.setNome("elton");
			u.setSenha("elton");
			return u;			
		}
	}

	public Usuario getUserAndGlpiUser(String login) throws Exception {
		Usuario usuario = usuarioDao.getUser(login);
//		if (Objects.nonNull(usuario)) {
//			User user = restRepository.buscar(login);
//			if (Objects.nonNull(user)) {
//				usuario.setIdUsuarioGlpi(user.getId());
//			} else {
//				usuario.setIdUsuarioGlpi(restRepository.salvar(usuario).getIdUsuarioGlpi());
//			}
//		}
		return usuario;
	}

	public Usuario getUser(String login) {
		return usuarioDao.getUser(login);
	}

	public Usuario saveUser(Usuario usuario) {
		try {
			// TODO: handle exception
			if (userExists(usuario)) {
				throw new UserAlreadyExistsException(ExceptionMessages.USER_EXISTS);
			}
			
			Usuario user = getUserByCpf(usuario.getCpf());
			
			if (Objects.nonNull(user)) {
				throw new CpfAlreadyExistsException(ExceptionMessages.CPF_USED, user);
			}
			
			usuario.setRoles(roleService.getRolesByCPF(usuario.getCpf()));
			repository.save(usuario);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario saveUser(Convite convite ) {

		Usuario usuario = new Usuario(convite);
		try {
			// TODO: handle exception
			if (userExists(usuario)) {
				throw new UserAlreadyExistsException(ExceptionMessages.USER_EXISTS);
			}
			
			Usuario user = getUserByCpf(usuario.getCpf());
			
			if (Objects.nonNull(user)) {
				throw new CpfAlreadyExistsException(ExceptionMessages.CPF_USED, user);
			}
			
			usuario.setRoles(roleService.getRolesByCPF(usuario.getCpf()));
			repository.save(usuario);
			
			
		} catch (Exception e) {
			usuario = new Usuario();
			e.printStackTrace();
		}
		return usuario;
	}

	public Usuario getUserByCpf(String cpf) {
		return usuarioDao.getUserByCpf(cpf);
	}

	public List<Usuario> getUsers(String cpf, String nome) {
		return usuarioDao.getUsers(cpf, nome);
	}
	
	public List<Usuario> getUsersByGroup(Long idGroup) {
		return usuarioDao.getUsersByGroup(idGroup);
	}

	public Usuario preUpdateUser(Usuario user) {
		validateLenghtUpdate(user);
		addUserDependencies(user);
		String uuid = usuarioDao.preUpdate(user);
		try {
			sendMailChangeUser(user.getLogin(), uuid);
		} catch (Exception e) {
			throw new BusinessException(ExceptionMessages.WRONG_MAIL);
		}
		return user;
	}
	public void preUpdatePassword(Usuario user) {
		validateLenghtUpdate(user);
		String uuid = usuarioDao.preUpdate(user);
		try {
			sendMailChangePassword(user.getLogin(), uuid);
		} catch (Exception e) {
			throw new BusinessException(ExceptionMessages.WRONG_MAIL);
		}
	}

	private void validateLenghtUpdate(Usuario user) {
		BigDecimal lenght = usuarioDao.getLenghtUpdateUser(user);
		if (lenght.compareTo(new BigDecimal(2)) == 1) {
			usuarioDao.disableUser(user);
			throw new BusinessException("Usu�rio Bloqueado");
		} else {
			user.setUpdateLenght(lenght.intValue());
			usuarioDao.updateLenghtUser(user);
		}
	}

	public void updateUser(Usuario user, String loginOld) {
		try {
			user.setSenha(user.getSenha());
			repository.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UserNotFoundException(ExceptionMessages.USER_NOT_EXISTS);
		}
		//usuarioDao.logout(loginOld);
		//updateEmailUserGlpi(user.getIdUsuarioGlpi(), user.getLogin());
	}

//	private void updateEmailUserGlpi(Integer idGlpi, String loginNew) {
//		if(Objects.isNull(idGlpi)){
//			return;
//		}
//		glpiRestTemplate.getForEntity(consumer.updateUser(idGlpi, loginNew), String.class);
//	}

//	public String doLoginAndGetToken(Usuario user) {
//		MultiValueMap<String, String> params = consumer.doLogin(user.getLogin(), user.getSenhaPlana());
//		URI uri = consumer.createUri();
//		HttpEntity<DefaultOAuth2AccessToken> response = restTemplate.postForEntity(uri, params, DefaultOAuth2AccessToken.class);
//		return response.getBody().toString();
//	}

	
	private void sendMailChangeUser(String emailAddress, String uuid) throws Exception {
		String text = new StringBuilder()
				.append("Voc� fez o recadastro no SME Mobile, clique no link abaixo para confirmar, se caso voc� n�o solicitou isto, por favor desconsidere este email.<br />")
				//.append("<a href=\"http://api.sme.fortaleza.ce.gov.br/aio/pergunta/?key=").append(uuid)
				.append("<a href=\"http://172.23.7.125:8080/aio/pergunta/?key=").append(uuid)
				.append("\"> Confirmar Recadastro </a>").toString();
		String subject = "SME Mobile - Recadastro";

		sendMail(emailAddress, subject, text);
	}

	private void sendMailChangePassword(String emailAddress, String uuid) throws Exception {
		String text = new StringBuilder()
				.append("Voc� solicitou a altera��o de senha no AIO Mobile, clique no link abaixo para confirmar, se caso voc� n�o solicitou isto, por favor desconsidere este email.<br />")
				.append("<a href=\"https://p538r.app.goo.gl/?link=http://aio.com.br/meu_perfil/chave:").append(uuid)
				.append("&apn=br.com.aio&afl=http://aio.com.br/meu_perfil")
				.append("\"> Confirmar Altera��o de Senha </a>")
				.append("<br>").append("Chave: ").append(uuid).toString();
		String subject = "AIO Mobile - Altera��o de Senha";

		sendMail(emailAddress, subject, text);
	}

	private void sendMail(String emailAddress, String subject, String text) throws Exception {
		Mail mail = new Mail("eltilopes@gmail.com", emailAddress, subject, text);
		mailManager.sendMail(mail);
	}

	public List<Usuario> getUsersByApiKey(Long idApiKey) {
		return usuarioDao.getUsersByApiKey(idApiKey);
	}

	public boolean userAlreadyHadThisApiKey(ApiKey apiKey, Usuario usuario) {
		return usuarioDao.userAlreadyHadThisApiKey(apiKey, usuario);
	}

	public boolean userExists(Usuario usuario) {
		Objects.requireNonNull(usuario.getLogin());
		Objects.requireNonNull(usuario.getCpf());
		return (usuarioDao.getUser(usuario.getLogin()) != null) && (usuarioDao.getUserByCpf(usuario.getCpf()) != null);
	}

	private Usuario addUserDependencies(Usuario user) {
		user.setRoles(roleService.getRoles(user.getId()));
		return user;
	}

	public Usuario getUserByKey(String key) {
		return usuarioDao.getUserByKey(key);
	}

	public RoleService getRoleService() {
		return roleService;
	}

	@SuppressWarnings("rawtypes")
	public void updateUserByAux(Usuario user, String loginOld, Map questions) {
		try{
			//pegava id do funcionario vinculado as questoes
			securityQuestionService.validateQuestions(questions, user.getId());
			// Atualizar dados usuario
			Map<String, String> valuesUpdate = usuarioDao.getValuesByUserHelper(user);
			user.setLogin(valuesUpdate.get("DS_EMAIL"));
			user.setNewSenha(valuesUpdate.get("DS_SENHA"));
			user.setUpdateLenght(0);
			updateUser(user, loginOld);
		}finally{
			usuarioDao.setInvalidUserHelper(user);
		}
	}

	public void logout(String login) {
		usuarioDao.logout(login);
	}

	public List<Usuario> getUsersByGroupDefault(Map<String, Integer> parameters) {
		return usuarioDao.getUsersByGroupDefault(parameters);
	}

	public Usuario getUsuarioCashBack() {
		//TODO: Aplicar a regra que pega o usuario Rodger , Renan ou Elton
		Usuario u = new Usuario();
		u.setId(1l);
		u.setLogin("eltilopes@gmail.com");
		u.setNome("elton");
		u.setCpf("92871259372");
		u.setCodigoConvite("B794A597");
		return u;
	}

	public Usuario getUserByCodigoConvite(String codigoConvite) {
		return usuarioDao.getUserByCodigoConvite(codigoConvite);
		
	}
}
