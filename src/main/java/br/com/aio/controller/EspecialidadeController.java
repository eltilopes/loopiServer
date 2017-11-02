package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.model.entity.Especialidade;
import br.com.aio.model.service.EspecialidadeService;

@RestController
@RequestMapping("/especialidade")
public class EspecialidadeController {
	
	@Inject
	private EspecialidadeService especialidadeService;
	
	
	@RequestMapping(value = "/listar", method = GET)
	public ResponseEntity<List<Especialidade>> getEspecialidades(@RequestParam String login){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(!authentication.getPrincipal().toString().equals(login)){
//				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		} 
		List<Especialidade> especialidades = especialidadeService.getEspecialidades();
		if(especialidades.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Especialidade>>(especialidades, HttpStatus.OK);
	}

}
