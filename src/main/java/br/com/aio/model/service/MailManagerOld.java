package br.com.aio.model.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailManagerOld {
	
	private MailSender mailSender = new JavaMailSenderImpl();
	
	private SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
	
	public void sendEmail(String emailAddress){
		simpleMailMessage.setTo(emailAddress);
		simpleMailMessage.setFrom("Alex Band");
		simpleMailMessage.setText("teste de envio de email");
		try {
			mailSender.send(simpleMailMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
