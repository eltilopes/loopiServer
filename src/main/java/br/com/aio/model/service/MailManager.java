package br.com.aio.model.service;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.inject.Inject;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.sun.mail.util.MailSSLSocketFactory;

import br.com.aio.model.entity.Mail;

@Component
public class MailManager {

	@Inject
	private JavaMailSenderImpl mailSender;

	public void sendMail(Mail Mail) throws Exception {
		MimeMessage mimiMessage = mailSender.createMimeMessage();

		MimeMessageHelper mimeMessageHelper;
		mimeMessageHelper = new MimeMessageHelper(mimiMessage, true);

		mimeMessageHelper.setTo(Mail.getTo());
		mimeMessageHelper.setText(Mail.getText(), true);
		mimeMessageHelper.setSubject(Mail.getSubject());
		mimeMessageHelper.setFrom(Mail.getFrom());

		MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
		mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
		mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
		mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
		mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
		CommandMap.setDefaultCommandMap(mc);

		mailSender.setProtocol("smtp");

		MailSSLSocketFactory socketFactory = new MailSSLSocketFactory();
		socketFactory.setTrustAllHosts(true);

		mailSender.send(mimiMessage);
	}

}
