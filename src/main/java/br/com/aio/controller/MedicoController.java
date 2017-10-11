package br.com.aio.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.model.entity.Medico;
import br.com.aio.model.service.MedicoService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * 
 * @author Elton Lopes
 *
 */

@RestController
@RequestMapping("/medico")
public class MedicoController {
	
	@Inject
	private MedicoService medicoService;
	
	@RequestMapping(value = "/listar" , method = GET )
	public ResponseEntity<List<Medico>> getMedicos(){
		List<Medico> medicos = medicoService.getMedicos();
		if(medicos.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
	}
}
