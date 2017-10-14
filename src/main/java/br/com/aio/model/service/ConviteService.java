package br.com.aio.model.service;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.Convite;
import br.com.aio.model.repository.dao.ConviteDao;
import br.com.aio.model.repository.hibernate.ConviteRepository;
import br.com.aio.util.ExceptionMessages;

@Service
public class ConviteService {
	
	@Inject
	private ConviteDao conviteDao;
	@Inject
	private ConviteRepository repository;

	public Convite solicitarConvite(Convite convite) {
		try {
			// TODO: handle exception
			if (conviteExiste(convite)) {
				throw new BusinessException(ExceptionMessages.CONVITE_EXISTS);
			}
			convite.setChave(getSecretKey(convite.getEmail()));
			repository.save(convite);
			
		} catch (Exception e) {
			throw new BusinessException(ExceptionMessages.INTERNAL_SERVER_ERROR);
		}
		return convite;
	}
	

	public Convite getConviteByCpf(String cpf) {
		return conviteDao.getConvite(cpf);
	}

	public boolean conviteExiste(Convite convite) {
		Objects.requireNonNull(convite.getCpf());
		Objects.requireNonNull(convite.getNome());
		Objects.requireNonNull(convite.getEmail());
		return (conviteDao.getConvite(convite.getCpf()) != null);
	}
	
	private String getSecretKey(String email) throws NoSuchAlgorithmException{
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		myRandom = myRandom.replace("-", "");
		return myRandom.toUpperCase().substring(0,8);
	}
	
	
}
