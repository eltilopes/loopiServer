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
import br.com.aio.model.entity.FavoritoProfissionalUsuario;
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
	
	@RequestMapping(value = "/favoritos", method = POST)
	public ResponseEntity<List<FavoritoProfissionalUsuario>> getFavoritosProfissional(@Valid @RequestBody FavoritoProfissionalUsuario favoritoProfissionalUsuario){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(!authentication.getPrincipal().toString().equals(login)){
//				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		} 
		List<FavoritoProfissionalUsuario> favoritos = profissionalService.getFavoritosProfissional(favoritoProfissionalUsuario);
		if(favoritos.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<FavoritoProfissionalUsuario>>(favoritos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/favorito", method = POST)
	public ResponseEntity<FavoritoProfissionalUsuario> setFavoritoProfissional(@Valid @RequestBody FavoritoProfissionalUsuario favoritoProfissionalUsuario){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(!authentication.getPrincipal().toString().equals(login)){
//				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		} 
		try {
			profissionalService.salvarFavoritoProfissional(favoritoProfissionalUsuario);
			return new ResponseEntity<FavoritoProfissionalUsuario>(favoritoProfissionalUsuario, HttpStatus.CREATED);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} 
	}
	
	@RequestMapping(value = "/desfavoritar", method = POST)
	public ResponseEntity<FavoritoProfissionalUsuario> deleteFavoritoProfissional(@Valid @RequestBody FavoritoProfissionalUsuario favoritoProfissionalUsuario){
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if(!authentication.getPrincipal().toString().equals(login)){
//				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		} 
		try {
			profissionalService.removerFavoritoProfissional(favoritoProfissionalUsuario);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} 
	}
	
	

}
