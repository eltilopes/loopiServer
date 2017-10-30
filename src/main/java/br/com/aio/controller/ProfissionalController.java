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
import br.com.aio.model.entity.Profissional;
import br.com.aio.model.service.ProfissionalService;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {
	
	@Inject
	private ProfissionalService profissionalService;
	
	@RequestMapping(value = "/novo", method = POST)
	public ResponseEntity<Profissional> salvar(@Valid @RequestBody Profissional profissional){
		try {
			profissionalService.salvar(profissional);
			return new ResponseEntity<Profissional>(profissional, HttpStatus.CREATED);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} 
	}
}
