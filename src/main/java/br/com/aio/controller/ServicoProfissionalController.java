package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.ServicoProfissional;
import br.com.aio.model.entity.vo.FiltroVo;
import br.com.aio.model.entity.vo.ServicoCardVo;
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
	
	@RequestMapping(value = "/listar", method = POST)
	public ResponseEntity<List<ServicoCardVo>> getServicos(@Valid @RequestBody FiltroVo filtroVo){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(!authentication.getPrincipal().toString().equals(login)){
//				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		} 
		List<ServicoCardVo> servicos = servicoProfissionalService.getServicos(filtroVo);
		if(servicos.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<ServicoCardVo>>(servicos, HttpStatus.OK);
	}
}
