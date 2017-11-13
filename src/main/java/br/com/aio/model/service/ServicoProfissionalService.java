package br.com.aio.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.ServicoProfissional;
import br.com.aio.model.entity.vo.FiltroVo;
import br.com.aio.model.entity.vo.LocalizacaoProfissionalVO;
import br.com.aio.model.entity.vo.ServicoCardVo;
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

	public List<ServicoCardVo> getServicos(FiltroVo filtro) {
		List<LocalizacaoProfissionalVO> localizacaoProfissionalVOs = LocalizacaoProfissionalVO.getLocalizacoes();
		List<ServicoCardVo> servicos = new ArrayList<ServicoCardVo>();
		List<ServicoProfissional> lista = repository.getServicos(filtro);
		int i = 0;
		for(ServicoProfissional sp : lista){
			servicos.add(getServicoCardVo(sp, localizacaoProfissionalVOs.get(i++)));
			if(i == 9) i = 0;
		}
		return servicos;
	}

	private ServicoCardVo getServicoCardVo(ServicoProfissional servicoProfissional, LocalizacaoProfissionalVO localizacaoProfissionalVO) {
		ServicoCardVo servicoCardVo = new ServicoCardVo(servicoProfissional);
		servicoCardVo.setLatitude(localizacaoProfissionalVO.getLatitude());
		servicoCardVo.setLongitude(localizacaoProfissionalVO.getLongitude());
		return servicoCardVo;
	}
	
	
}
