package br.com.aio.model.service;

import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.MessageStatus;
import br.com.aio.model.entity.MobileMessage;
import br.com.aio.model.repository.dao.MobileMessageDao;
import br.com.aio.model.repository.dao.UsuarioDao;
import br.com.aio.security.entity.Usuario;
import br.com.aio.util.ExceptionMessages;


@Service
public class MobileMessageService {
	
	@Inject
	public NotificationService notificationService;
	
	@Inject
	public UsuarioService usuarioService;
	
	@Inject
	public UsuarioDao usuarioDao;
	
	@Inject
	public MobileMessageDao mobileMessageDao;
	
	public MobileMessage saveMessage(MobileMessage message) throws Exception{
		try{
			MobileMessage mobileMessage = mobileMessageDao.saveMessage(message);
			notificationService.sendNotification(mobileMessage);
			mobileMessage.setUsuarioMobileMessages(null);
			return mobileMessage;
		}catch(DataIntegrityViolationException erro){
			throw new BusinessException("Mensagem já foi enviada para o usuário.");
		}
	}
	
	public List<MobileMessage> getMobileMessagesNotRemove(String login) throws Exception{
		Usuario user = usuarioService.getUser(login);
		if(Objects.isNull(user)){
			throw new BusinessException(ExceptionMessages.USER_NOT_EXISTS);
		}
		return mobileMessageDao.getMobileMessagesNotRemove(user.getId());
	}

	public void updateMessageStatus(Long idMensagem, MessageStatus status, String login){
		Usuario user = usuarioService.getUser(login);
		mobileMessageDao.updateMessageStatus(idMensagem, status, user);
	}

	public void updateMessagesStatus(List<Long> idsMens, MessageStatus read, String login) {
		Usuario user = usuarioService.getUser(login);
		mobileMessageDao.updateMessagesStatus(idsMens, read, user);
	}
	
	public Boolean accessMessage(Long idMensagem, String login) {
		return usuarioDao.userAlreadyHadThisMessage(idMensagem, login);
	}
}
