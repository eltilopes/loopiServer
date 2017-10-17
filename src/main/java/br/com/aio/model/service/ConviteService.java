package br.com.aio.model.service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.Convite;
import br.com.aio.model.entity.Mail;
import br.com.aio.model.repository.dao.ConviteDao;
import br.com.aio.model.repository.hibernate.ConviteRepository;
import br.com.aio.security.entity.Usuario;
import br.com.aio.util.ExceptionMessages;
import br.com.aio.model.entity.enums.Status;

@Service
public class ConviteService {
	
	@Inject
	private ConviteDao conviteDao;
	@Inject
	private ConviteRepository repository;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private MailManager mailManager;
	
	private String chave;

	@Transactional
	public Convite solicitarConvite(Convite convite) {
		try {
			// TODO: handle exception
			if (conviteExiste(convite)) {
				throw new BusinessException(ExceptionMessages.CONVITE_EXISTS);
			}
			convite.setDataCriacao(new Date());
			convite.setStatus(Status.ATIVO);
			getNovaChave();
			convite.setChave(chave);
			convite.setUsuarioCashBack(getUsuarioCashBack());
			repository.save(convite);
			enviarEmailConvite(convite.getEmail(), convite.getId());
			
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(ExceptionMessages.INTERNAL_SERVER_ERROR);
		}
		return convite;
	}
	
	private void enviarEmailConvite(String emailAddress, Long idConvite) throws Exception {
		String text = new StringBuilder()
				.append("Você esta recebendo seu convite, clique no link abaixo para finalizar seu cadastro, se caso você não solicitou isto, por favor desconsidere este email.<br />")
				.append("<a href=\"https://p538r.app.goo.gl/?link=http://aio.com.br/meu_perfil/").append(idConvite)
				.append("32654&apn=br.com.aio&afl=http://aio.com.br/meu_perfil")
				.append("\"> Finalizar Cadastro </a>").toString();
		String subject = "AIO - Convite";
		mailManager.sendMail(new Mail("eltilopes@gmail.com", emailAddress, subject, text));
	}

	private Usuario getUsuarioCashBack() {
		return usuarioService.getUsuarioCashBack();
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
	
	private void getNovaChave() throws NoSuchAlgorithmException{
		UUID uuid = UUID.randomUUID();
		chave = uuid.toString();
		chave = chave.replace("-", "").toUpperCase().substring(0,8);
		jaExisteChave();
	}


	private void jaExisteChave() throws NoSuchAlgorithmException {
		if(conviteDao.existeChave(chave)) getNovaChave();
	
	}
	
	
}
