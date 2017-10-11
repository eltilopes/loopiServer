package br.com.aio.model.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.model.entity.Medico;
import br.com.aio.model.repository.dao.MedicoDao;

@Service
public class MedicoService {
	
//	verificar o save no UsuarioService
//	@Inject
//	private MedicoRepository repository;
	
	@Inject
	private MedicoDao medicoDao;

	public List<Medico> getMedicos() {
		return medicoDao.getMedicos();
	}
	
}
