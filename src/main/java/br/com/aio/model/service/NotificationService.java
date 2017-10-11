package br.com.aio.model.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.common.exceptions.InvalidRequestException;
import org.springframework.stereotype.Service;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.MulticastResult;
import com.google.android.gcm.server.Sender;

import br.com.aio.model.entity.ApiKey;
import br.com.aio.model.entity.MobileMessage;
import br.com.aio.model.entity.UsuarioMobileMessage;
import br.com.aio.model.repository.dao.ApiKeyDao;
import br.com.aio.security.entity.Usuario;

/**
 * 
 * @author Stanley Albuquerque
 *
 */

@Service
public class NotificationService {
	
	private static final Logger logger = Logger.getLogger(NotificationService.class);

	private List<String> devices = Collections.emptyList();
	private ApiKeyDao apiKeyDao;
	private Sender sender;
	
	@Inject
	public NotificationService(ApiKeyDao apiKeyDao, @Qualifier("sender") Sender sender) {
		this.apiKeyDao = apiKeyDao;
		this.sender = sender;
	}

	public MobileMessage sendNotification(MobileMessage mobileMessage) throws Exception {
		this.deviceKeys(mobileMessage);
		
		Message message = new Message.Builder()
				.timeToLive(2419200)
				 .delayWhileIdle(false)
				.addData("title", URLEncoder.encode(mobileMessage.getTitle(), "UTF-8"))
				.addData("id", mobileMessage.getId().toString())
				.addData("message", URLEncoder.encode(mobileMessage.getBody(), "UTF-8"))
				.addData("creationDate", String.valueOf(mobileMessage.getCreationDate().getTime()))
				.addData("author", URLEncoder.encode("Comunicação SME", "UTF-8"))
				.build();
		
		MulticastResult result = sender.send(message, devices, 1);
		
		logger.debug(result.toString());

		if (result.getResults() != null) {
			if (result.getCanonicalIds() != 0) {
				//colocar logica se as mensagens funcionaram ou não para atualizar os status das mensagens
			}
		} else {
			logger.error(result.getFailure(), new InvalidRequestException("não foi possivel enviar a notificação"));
		}
		return mobileMessage;
	}
	
	private void deviceKeys(MobileMessage mobileMessage){
		devices = new ArrayList<String>();
		
		List<Usuario> users = mobileMessage.getUsuarioMobileMessages()
								.stream().map(UsuarioMobileMessage::getUsuario)
								.collect(Collectors.toList());
				
		if(Objects.nonNull(users)){
			apiKeyDao.getApiKeys(users).forEach(key -> devices.add(key.getHash()));
		}
	}
	
}
