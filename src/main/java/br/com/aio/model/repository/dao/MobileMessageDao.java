package br.com.aio.model.repository.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeSet;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.SqlSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.exception.BusinessException;
import br.com.aio.exception.UserNotFoundException;
import br.com.aio.model.entity.MessageStatus;
import br.com.aio.model.entity.MobileMessage;
import br.com.aio.model.entity.NotificationGroup;
import br.com.aio.model.entity.UsuarioMobileMessage;
import br.com.aio.model.entity.enums.GroupDefault;
import br.com.aio.security.entity.Usuario;

@Repository
@Transactional
public class MobileMessageDao {
	
	private SqlSession session;
	private SessionFactory sessionFactory;
	
	@Inject
	private UsuarioDao usuarioDao;
	
	@Autowired 
	private ApplicationContext applicationContext;
	
	@Inject
	public MobileMessageDao(SqlSession session, SessionFactory sessionFactory) {
		this.session = session;
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@SuppressWarnings("unchecked")
	public Optional<List<Usuario>> getUsers(String s){
		try {
			return Optional.of(((Callable<List<Usuario>>) applicationContext.getBean(s)).call());
		} catch (Exception e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

	public MobileMessage saveMessage(MobileMessage message){
		List<UsuarioMobileMessage> usuarioMobileMessages = new ArrayList<UsuarioMobileMessage>();
		if (CollectionUtils.isNotEmpty(message.getUsers())){
			message.getUsers().stream().filter(us -> Objects.nonNull(us.getLogin())).forEach(u -> {
				usuarioMobileMessages.add(createUserMobileMessage(message, u));
			});			
		}
		
		if (CollectionUtils.isNotEmpty(message.getGroups())) {
			List<NotificationGroup> groups  = message.getGroups().stream().filter(group -> Objects.nonNull(group.getId())).collect(Collectors.toList());
			if (CollectionUtils.isNotEmpty(groups)) {
				usuarioDao.getUsersByGroups(groups, 
						usuarioMobileMessages
							.stream()
								.map(UsuarioMobileMessage::getUsuario)
									.collect(Collectors.toList()))				
									.forEach(u -> {usuarioMobileMessages.add(createUserMobileMessage(message, u));}
									);	
			}
			List<NotificationGroup> groupsDefault  = message.getGroups().stream().filter(group -> Objects.isNull(group.getId())).collect(Collectors.toList());

			if (CollectionUtils.isNotEmpty(groupsDefault)) {
				List<String> strs = groupsDefault.stream().map(NotificationGroup::getNameGroup).collect(Collectors.toList());
//				List<Usuario> groupsDefaultVo = new ArrayList<>(); 
//				Stream.of(GroupDefault.values())
//							.filter(g -> strs.contains(g.getName()))
//							.map(GroupDefault::getBean)
//							.collect(Collectors.toList())
//							.forEach(string->{
//								try {
//									groupsDefaultVo.addAll(
//											((Callable<List<Usuario>>) applicationContext.getBean(string)).call()
//									);
//								} catch (Exception e) {
//									e.printStackTrace();
//									throw new GlpiException("Ocorreu um erro ao recuperar os usuários do grupo");
//								}
//							});
				
				
				
				List<Usuario> groupsDefaultVo = Stream.of(GroupDefault.values())
				.filter(g -> strs.contains(g.getName()))
				.map(GroupDefault::getBean)
				.map(s -> { 
					return getUsers(s).orElseThrow(() -> new BusinessException("Ocorreu um erro ao recuperar os usuários do grupo"));
				})
				.flatMap( v -> v.stream() ).collect(Collectors.toList());
				
				groupsDefaultVo.forEach(u -> usuarioMobileMessages.add(createUserMobileMessage(message, u)));
				
				List<NotificationGroup> groupsRemove  = message.getGroups().stream().filter(group -> Objects.isNull(group.getId())).collect(Collectors.toList());

				message.getGroups().removeAll(groupsRemove);
			}
			if(CollectionUtils.isEmpty(groupsDefault) && CollectionUtils.isEmpty(groups)){
				message.setGroups(null);
			}
		}else{
			message.setGroups(null);
		}
		
		message.setUsuarioMobileMessages(
				usuarioMobileMessages.stream().collect(
						Collectors.toCollection( 
								() -> new TreeSet<UsuarioMobileMessage>(
										(m1, m2) -> m1.getUsuario().getLogin().compareTo(m2.getUsuario().getLogin()))
										) 
				   ).stream().collect(Collectors.toList()
				) 
			);
		
		if(message.getUsuarioMobileMessages().isEmpty()){
			throw new UserNotFoundException();
		}
		
		getSession().save(message);

		return message;
	}

	private UsuarioMobileMessage createUserMobileMessage(MobileMessage message, 
			 Usuario u) {
		UsuarioMobileMessage usuarioMobileMessage = new UsuarioMobileMessage();
		usuarioMobileMessage.setMessage(message);
		usuarioMobileMessage.setUsuario(u);
		usuarioMobileMessage.setMessageStatus(MessageStatus.SEND);
//		usuarioMobileMessages.add(usuarioMobileMessage);
		return usuarioMobileMessage;
	}
	
	public List<MobileMessage> getMobileMessagesNotRemove(Long idUsuarioMobile){
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("id", idUsuarioMobile);
		parameters.put("status", MessageStatus.REMOVE.ordinal());
		return session.selectList("listUserMessage", parameters);
	}

	public void updateMessageStatus(Long idMensagem, MessageStatus status, Usuario user) {
		UsuarioMobileMessage usuarioMessage = (UsuarioMobileMessage) getSession().createCriteria(UsuarioMobileMessage.class)
				.add(Restrictions.eq("message.id", idMensagem))
				.add(Restrictions.eq("usuario.id", user.getId()))
				.add(Restrictions.ne("messageStatus", status))
				.uniqueResult();
		if(Objects.isNull(usuarioMessage)){
			throw new BusinessException("Não foi encontrada nenhuma mensagem para alteração.");
		}
		usuarioMessage.setMessageStatus(status);
		getSession().update(usuarioMessage);
	}

	@SuppressWarnings("unchecked")
	public void updateMessagesStatus(List<Long> idsMens, MessageStatus status, Usuario user) {
		List<UsuarioMobileMessage> listMessage = (List<UsuarioMobileMessage>) getSession().createCriteria(UsuarioMobileMessage.class)
				.add(Restrictions.in("message.id", idsMens))
				.add(Restrictions.eq("usuario.id", user.getId()))
				.add(Restrictions.ne("messageStatus", status))
				.list();
		listMessage.forEach(t -> {
			t.setMessageStatus(status);
			getSession().update(t);
		});
		
	}

}
