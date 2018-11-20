package br.com.aio.model.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.ServicoProfissional;
import br.com.aio.model.repository.hibernate.ServicoProfissionalRepository;
import br.com.aio.util.ExceptionMessages;

@Service
public class ServicoProfissionalService {
	
	@Inject
	private ServicoProfissionalRepository repository;

	@Transactional
	public ServicoProfissional salvar(ServicoProfissional servicoProfissional) {
		try {
			// TODO: handle exception
			/*if (servicoProfissionalExiste(servicoProfissional)) {
				throw new BusinessException(ExceptionMessages.CONVITE_EXISTS);
			}*/
			repository.save(servicoProfissional);
			
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(ExceptionMessages.INTERNAL_SERVER_ERROR);
		}
		return servicoProfissional;
	}

//	public List<ServicoCardVo> getServicos(FiltroVo filtro) {
//		List<ServicoCardVo> servicos = new ArrayList<ServicoCardVo>();
//		List<ServicoProfissional> lista = repository.getServicos(filtro);
//		for(ServicoProfissional sp : lista){
//			servicos.add(new ServicoCardVo(sp));
//		}
//		return servicos;
//	}
//	public List<ServicoCardVo> getServicos(Profissional profissional) {
//		List<ServicoCardVo> servicos = new ArrayList<ServicoCardVo>();
//		List<ServicoProfissional> lista = repository.getServicos(profissional);
//		for(ServicoProfissional sp : lista){
//			servicos.add(new ServicoCardVo(sp));
//		}
//		return servicos;
//	}
	
}
