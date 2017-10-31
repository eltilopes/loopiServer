package br.com.aio.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.model.entity.Categoria;
import br.com.aio.model.repository.dao.CategoriaDao;

@Service
public class CategoriaService {
	
	@Inject
	private CategoriaDao categoriaDao;	

	public List<Categoria> getCatgorias() {
		return categoriaDao.getCatgorias();
	}
	
	
}
