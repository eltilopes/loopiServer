package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.Convite;
import br.com.aio.model.service.ConviteService;
import br.com.aio.security.entity.Usuario;

@RestController
@RequestMapping("/convite")
public class ConviteController {
	
	@Inject
	private ConviteService conviteService;
	
	@RequestMapping(value = "/solicitar", method = POST)
	public ResponseEntity<Usuario> solicitarConvite(@Valid @RequestBody Convite convite){
		try {
			Usuario usuario = conviteService.solicitarConvite(convite);
			return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} 
	}
}
