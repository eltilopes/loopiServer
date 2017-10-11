package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.model.service.SendEmailService;

@RestController
@RequestMapping("/email")
public class SendEmailController {
	
	@Inject
	private SendEmailService sendEmail;

	@RequestMapping(value = "/error", method = POST)
	public ResponseEntity<Boolean> sendError(@RequestParam("login") String login,@RequestParam("error") String error) throws Exception{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getPrincipal().toString().equals(login)){
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} 
		return new ResponseEntity<Boolean>(sendEmail.senErro(login, error), HttpStatus.OK);
	}
}
