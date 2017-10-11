package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.exception.BusinessException;
import br.com.aio.exception.UserNotFoundException;
import br.com.aio.model.entity.MessageStatus;
import br.com.aio.model.entity.MobileMessage;
import br.com.aio.model.service.MobileMessageService;

/**
 * 
 * @author Stanley Albuquerque
 *
 */

@RestController
@RequestMapping("/mensagem")
public class MobileMessageController {
	
	@Inject
	public MobileMessageService messageService;
	
	@RequestMapping(value = "/novo", method = POST)
	public ResponseEntity<MobileMessage> saveMessage(@Valid @RequestBody MobileMessage mobileMessage) throws Exception{
		try{
			messageService.saveMessage(mobileMessage);				
		}catch(UserNotFoundException e){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<MobileMessage>(mobileMessage, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/listar", method = GET)
	public ResponseEntity<List<MobileMessage>> getMessages(@RequestParam String login) throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getPrincipal().toString().equals(login)){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} 
		List<MobileMessage> messages = messageService.getMobileMessagesNotRemove(login);
		if(messages.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<MobileMessage>>(messages, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/recebido", method = GET)
	public ResponseEntity<?> confirmMessageReceived(@RequestParam Long idMensagem, @RequestParam String login){
		messageService.updateMessageStatus(idMensagem, MessageStatus.RECEIVED, login);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getPrincipal().toString().equals(login)){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} 
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/lido", method = GET)
	public ResponseEntity<?> confirmMessageRead(@RequestParam Long idMensagem, @RequestParam String login){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getPrincipal().toString().equals(login)){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} 
		try{
			messageService.updateMessageStatus(idMensagem, MessageStatus.READ, login);
		}catch(BusinessException e){
			throw new BusinessException(e.getMessage());
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
		
	@RequestMapping(value = "/lidas", method = GET)
	public ResponseEntity<?> confirmMessagesRead(@RequestParam("ids[]") List<Long> ids, @RequestParam(value="login", required=false) String login){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getPrincipal().toString().equals(login)){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} 
		messageService.updateMessagesStatus(ids, MessageStatus.READ, login);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@SuppressWarnings("serial")
	@RequestMapping(value = "/remover", method = GET)
	public ResponseEntity<?> confirmMessagesRemove(@RequestParam("id") Long id, @RequestParam(value="login") String login){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getPrincipal().toString().equals(login)){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} 
		
		messageService.updateMessagesStatus(new ArrayList<Long>(){{add(id);}}, MessageStatus.REMOVE, login);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/removerAll", method = GET)
	public ResponseEntity<?> confirmMessagesRemoveAll(@RequestParam("ids[]") List<Long> ids, @RequestParam(value="login") String login){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getPrincipal().toString().equals(login)){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} 
		messageService.updateMessagesStatus(ids, MessageStatus.REMOVE, login);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accessMessage", method = GET)
	public ResponseEntity<Boolean> accessMessage(@RequestParam("id") Long idMessage, @RequestParam(value="login") String login){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if(!authentication.getPrincipal().toString().equals(login)){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} 
		return new ResponseEntity<Boolean>(messageService.accessMessage(idMessage, login), HttpStatus.OK);
	}
	
}
