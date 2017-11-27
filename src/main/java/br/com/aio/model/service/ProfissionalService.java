package br.com.aio.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.Profissional;
import br.com.aio.model.entity.vo.FiltroVo;
import br.com.aio.model.entity.vo.ServicoCardVo;
import br.com.aio.model.repository.dao.ProfissionalDao;
import br.com.aio.model.repository.hibernate.ProfissionalRepository;
import br.com.aio.util.ExceptionMessages;

@Service
public class ProfissionalService {
	
	@Inject
	private ProfissionalDao profissionalDao;	

	@Inject
	private ProfissionalRepository repository;

	@Transactional
	public Profissional salvar(Profissional profissional) {
		try {
			// TODO: handle exception
			/*if (servicoProfissionalExiste(servicoProfissional)) {
				throw new BusinessException(ExceptionMessages.CONVITE_EXISTS);
			}*/
			repository.save(profissional);
			
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(ExceptionMessages.INTERNAL_SERVER_ERROR);
		}
		return profissional;
	}
	
	public List<ServicoCardVo> getProfissionais(FiltroVo filtro) {
		List<ServicoCardVo> servicos = new ArrayList<ServicoCardVo>();
		List<Profissional> lista = profissionalDao.getProfissionais(filtro);
		for(Profissional profissional : lista){
			servicos.add(new ServicoCardVo(profissional));
		}
		return servicos;
	}
	
	
}
