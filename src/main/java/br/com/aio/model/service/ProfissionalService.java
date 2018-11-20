package br.com.aio.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.FavoritoProfissionalUsuario;
import br.com.aio.model.entity.Profissional;
import br.com.aio.model.entity.vo.FiltroVo;
import br.com.aio.model.entity.vo.ServicoCardVo;
import br.com.aio.model.repository.dao.ProfissionalDao;
import br.com.aio.model.repository.hibernate.ProfissionalRepository;
import br.com.aio.security.entity.Usuario;
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

	public List<FavoritoProfissionalUsuario> getFavoritosProfissional(
			FavoritoProfissionalUsuario favoritoProfissionalUsuario) {

		return profissionalDao.getFavoritosProfissional(favoritoProfissionalUsuario);
	}

	@Transactional
	public FavoritoProfissionalUsuario salvarFavoritoProfissional(FavoritoProfissionalUsuario favoritoProfissionalUsuario) {
		try {
			if (favoritoProfissionalUsuarioExiste(favoritoProfissionalUsuario)) {
				throw new BusinessException(ExceptionMessages.FAVORITO_EXISTS);
			}
			repository.saveFavoritoProfissionalUsuario(favoritoProfissionalUsuario);
			
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(ExceptionMessages.INTERNAL_SERVER_ERROR);
		}
		return favoritoProfissionalUsuario;
		
	}

	public FavoritoProfissionalUsuario getFavoritoProfissionalUsuario(Usuario usuario, Profissional profissional) {
		return profissionalDao.getFavoritoProfissionalUsuario(usuario.getId(), profissional.getId());
	}

	public boolean favoritoProfissionalUsuarioExiste(FavoritoProfissionalUsuario favoritoProfissionalUsuario) {
		return (getFavoritoProfissionalUsuario(favoritoProfissionalUsuario.getUsuario(), favoritoProfissionalUsuario.getProfissional()) != null);
	}

	public void removerFavoritoProfissional(FavoritoProfissionalUsuario favoritoProfissionalUsuario) {
		if (!favoritoProfissionalUsuarioExiste(favoritoProfissionalUsuario)) {
			throw new BusinessException(ExceptionMessages.FAVORITO_NOT_EXISTS);
		}
		 repository.deleteFavoritoProfissionalUsuario(favoritoProfissionalUsuario);
	}
	
	
}
