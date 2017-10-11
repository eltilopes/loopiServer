package br.com.aio.model.repository.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.security.entity.Role;

@Repository
public class RoleDao {
	
	//TODO: ajustar todas as consultas do myBatis
	private SqlSession sqlSession;
	
	@Inject
	public RoleDao(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	
	public List<Role> getRoles(Long idUsuario){
		return sqlSession.selectList("listaRoles", idUsuario);
	}
	
	public Role getRole(Long id){
		return sqlSession.selectOne("getRole", id);
	}
	
	public List<Role> getRoles(){
		return sqlSession.selectList("getAllRoles");
	}

	public List<Role> getRoles(String login) {
		return sqlSession.selectList("listaRolesPorLogin", login);
	}

	public List<Role> getRolesByCPF(String cpf) {
		return sqlSession.selectList("getRolesByCPF", cpf);
	}

}
