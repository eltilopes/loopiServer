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
import br.com.aio.model.entity.ServicoProfissional;
import br.com.aio.model.service.ServicoProfissionalService;

@RestController
@RequestMapping("/servico")
public class ServicoProfissionalController {
	
	@Inject
	private ServicoProfissionalService servicoProfissionalService;
	
	@RequestMapping(value = "/novo", method = POST)
	public ResponseEntity<ServicoProfissional> salvar(@Valid @RequestBody ServicoProfissional servicoProfissional){
		try {
			servicoProfissionalService.salvar(servicoProfissional);
			return new ResponseEntity<ServicoProfissional>(servicoProfissional, HttpStatus.CREATED);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} 
	}
}
