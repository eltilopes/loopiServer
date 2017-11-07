package br.com.aio.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.model.entity.Especialidade;
import br.com.aio.model.repository.dao.EspecialidadeDao;

@Service
public class ServicoCardService {
	
	@Inject
	private EspecialidadeDao especialidadeDao;	

	public List<Especialidade> getEspecialidades() {
		return especialidadeDao.getEspecialidades();
	}
	
	
}
