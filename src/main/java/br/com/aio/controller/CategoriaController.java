package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.Categoria;
import br.com.aio.model.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Inject
	private CategoriaService categoriaService;

	private static final Logger logger = Logger.getLogger(CategoriaController.class);
	
	@RequestMapping(value = "/listar", method = GET)
	public ResponseEntity<List<Categoria>> getCategorias(@RequestParam String login){
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getPrincipal().toString().equals(login)){
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} */
		try {
			List<Categoria> categorias = categoriaService.getCatgorias();
			if(categorias.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Categoria>>(categorias, HttpStatus.OK);
		} catch (BusinessException e) {
			logger.error(e.getStackTrace());
			throw new BusinessException(e.getMessage());
		} 
		
	}

}
