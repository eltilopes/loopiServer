package br.com.aio.security.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.model.repository.dao.RoleDao;
import br.com.aio.security.entity.Role;

@Service
public class RoleService{
	
	@Inject
	private RoleDao dao;
	
	public List<Role> getRoles(Long idUsuario) {
		return dao.getRoles(idUsuario);
	}
	
	public Role getRole(Long id){
		return dao.getRole(id);
	}

	public List<Role> getRoles(String login) {
		return dao.getRoles(login);
	}
	
	public List<Role> getRoles(){
		return dao.getRoles();
	}

	public List<Role> getRolesByCPF(String cpf) {
		return dao.getRolesByCPF(cpf);
	}

}
