package br.com.aio.model.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.model.entity.NotificationGroup;
import br.com.aio.model.entity.enums.GroupDefault;
import br.com.aio.model.entity.vo.NotificationGroupVo;
import br.com.aio.model.repository.dao.NotificationGroupDao;
import br.com.aio.model.repository.hibernate.NotificationGroupRepository;
import br.com.aio.security.entity.Usuario;

@Service
public class NotificationGroupService {

	@Inject
	public NotificationGroupRepository repository;

	@Inject
	public NotificationGroupDao dao;

	@Inject
	public UsuarioService usuarioService;

	public List<NotificationGroupVo> getGroups() {
		List<NotificationGroupVo> toReturn = new ArrayList<>();
		toReturn.addAll(dao.getGroups());
		Arrays.stream(GroupDefault.values()).forEach(gd -> toReturn.add(new NotificationGroupVo(gd.getName())));
		toReturn.sort((n1, n2) -> n1.getNmGroup().compareToIgnoreCase(n2.getNmGroup()));
		return toReturn;
	}

	public void save(NotificationGroup group) {
		repository.save(group);
	}

	public NotificationGroup getGroupById(Long id) {
		NotificationGroup group = repository.getGroup(id);
		List<Usuario> users = usuarioService.getUsersByGroup(id);
		group.setUsers(users);
		return group;
	}

	public List<NotificationGroup> getGroupByName(String name) {
		List<NotificationGroup> toReturn = new ArrayList<>();
		toReturn.addAll(dao.getGroupByName(name));
		Arrays.stream(GroupDefault.values())
		.filter(gd ->  Pattern.compile("^"+name.toUpperCase()+"(\\w*)").matcher(gd.getName().toUpperCase()).find())
		.forEach(gd -> toReturn.add(new NotificationGroup(gd.getName())));
		return toReturn;
	}

	public void remove(Long idGroup) {
		repository.remove(idGroup);
	}

	public void update(NotificationGroup group) {
		repository.update(group);
		;
	}
}
