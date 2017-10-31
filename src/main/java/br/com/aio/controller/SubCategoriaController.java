package br.com.aio.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.aio.model.entity.SubCategoria;
import br.com.aio.model.service.SubCategoriaService;

@RestController
@RequestMapping("/subcategoria")
public class SubCategoriaController {
	
	@Inject
	private SubCategoriaService subCategoriaService;
	
	
	@RequestMapping(value = "/listar", method = GET)
	public ResponseEntity<List<SubCategoria>> getSubCategorias(@RequestParam String login){
		/*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(!authentication.getPrincipal().toString().equals(login)){
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		} */
		List<SubCategoria> subCategorias = subCategoriaService.getSubCatgorias();
		if(subCategorias.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<SubCategoria>>(subCategorias, HttpStatus.OK);
	}

}
