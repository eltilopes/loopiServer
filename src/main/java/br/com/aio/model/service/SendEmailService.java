package br.com.aio.model.service;

import javax.inject.Inject;

import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.Mail;

@Repository
public class SendEmailService {

	@Inject
	private MailManager mailManager;
	
	public boolean sendErro(String login, String erro){
		String text = new StringBuilder().append("Um erro ocorreu com o usuário: ")
				.append(login).append(" usava o sistema.")
				.append("</ br>")
				.append("Erro: ")
				.append(erro).toString();
		try {
			Mail mail = new Mail("eltilopes@gmail.com", "eltilopes@gmail.com", "Ocorreu um erro na aplicação", text);
			mailManager.sendMail(mail);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
