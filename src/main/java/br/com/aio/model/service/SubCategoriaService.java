package br.com.aio.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.model.entity.SubCategoria;
import br.com.aio.model.repository.dao.SubCategoriaDao;

@Service
public class SubCategoriaService {
	
	@Inject
	private SubCategoriaDao subCategoriaDao;	

	public List<SubCategoria> getSubCatgorias() {
		return subCategoriaDao.getSubCatgorias();
	}
	
	
}
