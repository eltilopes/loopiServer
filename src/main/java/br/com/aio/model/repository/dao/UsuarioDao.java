package br.com.aio.model.repository.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.ApiKey;
import br.com.aio.model.entity.NotificationGroup;
import br.com.aio.security.entity.Usuario;
import br.com.aio.security.entity.UsuarioAuth;

@Repository
public class UsuarioDao {
	
	@Inject
	private SqlSession sqlSession;
	
	public String preUpdate(Usuario user){
		String uuid = UUID.randomUUID().toString();
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", user.getLogin());
		parameters.put("senha", user.getSenha());
		parameters.put("idUsuario", user.getId());
		parameters.put("chave", uuid);
		int sqlRow = sqlSession.update("updateUserAux", parameters);
		
		if(sqlRow == 0){
			sqlSession.insert("insertUserAux", parameters);
		}
		return uuid;
	}
	public void updateLenghtUser(Usuario user) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("update_lenght", user.getUpdateLenght() + 1);
		parameters.put("idUsuario", user.getId());
		sqlSession.update("updateLenghtUser", parameters);
	}
	public BigDecimal getLenghtUpdateUser(Usuario user) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("idUsuario", user.getId());
		return sqlSession.selectOne("getLenghtUpdateUser", parameters);
	}
	public void disableUser(Usuario user) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ativo", 0);
		parameters.put("idUsuario", user.getId());
		sqlSession.update("disableUser", parameters);
	}
	public UsuarioAuth getUser(String login, String senha){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);
		parameters.put("senha", senha);
		return sqlSession.selectOne("buscarUsuarioLoginSenha", parameters);
	}
	
	public Usuario getUser(String login){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);
		return sqlSession.selectOne("buscarUsuarioLogin", parameters);
	}
	
	public List<Usuario> getUsers(String cpf, String nome){
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("cpf", cpf);
		params.put("nome", nome);
		return sqlSession.selectList("getUsers", params);
	}

	public boolean userAlreadyHadThisApiKey(ApiKey key, Usuario usuario) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("apiKey", key.getHash());
		params.put("idUser", usuario.getId());
		return sqlSession.selectOne("userAlreadyHadThisApiKey", params);
	}
	public boolean userAlreadyHadThisMessage(Long idMessage, String login) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("idMessage", idMessage);
		params.put("login", login);
		return sqlSession.selectOne("userAlreadyHadThisMessage", params);
	}
	
	public List<Usuario> getUsersByApiKey(Long idApiKey) {
		return sqlSession.selectList("getUsersByApiKey", idApiKey);
	}
	
	public Usuario getUserByCpf(String cpf){
		return sqlSession.selectOne("getUserByCpf", cpf);
	}
	
	public Usuario getUserByKey(String key){
		return sqlSession.selectOne("getUserByKey", key);
	}

	public Map<String, String> getValuesByUserHelper(Usuario user) {
		return sqlSession.selectOne("getValuesByUserHelper", user.getId());
	}

	public void setInvalidUserHelper(Usuario user) {
		sqlSession.update("setInvalidUserHelper", user.getId());
	}
	public List<Usuario> getUsersByGroup(Long idGroup) {
		return sqlSession.selectList("getUsersByGroup", idGroup);
	}
	public List<Usuario> getUsersByGroups(List<NotificationGroup> groups, List<Usuario> notUsers) {
		List<Long> ids = groups.stream().map(NotificationGroup::getId).collect(Collectors.toList());
		List<Long> idsNot = notUsers.stream().map(Usuario::getId).collect(Collectors.toList());
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("ids", ids.isEmpty() ? null : ids);
		parameters.put("idsNot", idsNot.isEmpty() ? null  : idsNot);
		return sqlSession.selectList("getUsersByGroups", parameters);
	}
	public UsuarioAuth getUserApiSme(String login, String senha) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);
		parameters.put("senha", senha);
		return sqlSession.selectOne("buscarUsuarioApiSmeLoginSenha", parameters);
	}
	public void logout(String login) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("login", login);
		sqlSession.delete("logout", parameters);

	}
	public int updateUser(Usuario user) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("senha", user.getSenha());
		parameters.put("login", user.getLogin());
		parameters.put("idUsuario", user.getId());
		return sqlSession.update("updateUser", parameters);
	}
	public List<Usuario> getUsersByGroupDefault(Map<String, Integer> parameters) {
		return sqlSession.selectList("getUsersByGroupDefault", parameters);
	}
	public List<Usuario> getUserIdleHours() {
		return sqlSession.selectList("getUserIdleHours");
	}
	public Usuario getUserByCodigoConvite(String codigoConvite) {
		return sqlSession.selectOne("getUserByCodigoConvite", codigoConvite);
	}


}
