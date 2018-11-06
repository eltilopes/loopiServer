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
import br.com.aio.model.entity.enums.Status;
import br.com.aio.model.repository.dao.ConviteDao;
import br.com.aio.model.repository.hibernate.ConviteRepository;
import br.com.aio.security.entity.Usuario;
import br.com.aio.util.ExceptionMessages;

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
	
	private String codigoConvite;

	@Transactional
	public Usuario solicitarConvite(Convite convite) {
		Usuario usuario = new Usuario();
		try {
			// TODO: handle exception
			if (conviteExiste(convite)) {
				throw new BusinessException(ExceptionMessages.CONVITE_EXISTS);
			}
			convite.setDataCriacao(new Date());
			convite.setStatus(Status.ATIVO);
			//getNovaChave();
			convite.setUsuarioCashBack(getUsuarioCashBack(convite));
			repository.save(convite);
			usuario = new Usuario(convite);
			String senha = usuario.getSenhaPlana();
			usuario = usuarioService.saveUser(usuario);
			//usuario.setCodigoConvite(senha);
			if (!usuario.getCpf().isEmpty()) {
				enviarEmailConvite(senha,convite.getEmail(), convite.getId());
			}
			
		} catch (BusinessException e) {
			throw new BusinessException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessException(ExceptionMessages.INTERNAL_SERVER_ERROR);
		}
		return usuario;
	}
	
	private void enviarEmailConvite( String senha,String emailAddress, Long idConvite) throws Exception {
		String text = new StringBuilder()
				.append("Voc� esta recebendo seu convite, clique no link abaixo para finalizar seu cadastro, se caso voc� n�o solicitou isto, por favor desconsidere este email.<br />")
				.append("<a href=\"https://p538r.app.goo.gl/?link=http://aio.com.br/meu_perfil/id_convite:").append(idConvite)
				.append("&apn=br.com.aio&afl=http://aio.com.br/meu_perfil")
				.append("\"> Finalizar Cadastro </a><br />")
				.append("Sua senha: ").append(senha).append("<br />").toString();
		String subject = "AIO - Convite";
		mailManager.sendMail(new Mail("eltilopes@gmail.com", emailAddress, subject, text));
		System.out.println(emailAddress);
	}

	private Usuario getUsuarioCashBack(Convite convite) {
		Usuario usuario = null;
		if(Objects.nonNull(convite.getCodigoConvite())){
			usuario = usuarioService.getUserByCodigoConvite(convite.getCodigoConvite());
		}
		if(Objects.isNull(usuario)){
			usuario = usuarioService.getUsuarioCashBack();
		}
		convite.setCodigoConvite(usuario.getCodigoConvite());
		return usuario;
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
	
	private void getNovoCodigoConvite() throws NoSuchAlgorithmException{
		UUID uuid = UUID.randomUUID();
		codigoConvite = uuid.toString();
		codigoConvite = codigoConvite.replace("-", "").toUpperCase().substring(0,8);
		System.out.println(codigoConvite);
		jaExisteCodigoConvite();
	}


	private void jaExisteCodigoConvite() throws NoSuchAlgorithmException {
		if(conviteDao.existeCodigoConvite(codigoConvite)) getNovoCodigoConvite();
	
	}
	
	
}
