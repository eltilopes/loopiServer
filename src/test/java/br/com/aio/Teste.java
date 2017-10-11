package br.com.aio;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.aio.model.entity.Mail;
import br.com.aio.model.service.MailManager;

public class Teste {
	
	public static void main(String[] args) throws Exception {
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/root-context.xml");
		
		MailManager mailManager = (MailManager) context.getBean("mailManager");
		
		Mail mail = new Mail();
		
		mail.setTo("stanleyaraujo2@gmail.com");
		mail.setFrom("stanleyaraujo2@hotmail.com");
		mail.setSubject("teste de assunto");
		mail.setText("teste de envio de email");
	
		mailManager.sendMail(mail);
			
//		@SuppressWarnings("resource")
//		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/root-context.xml");
//	
//		UserGlpiConsumer consumer = context.getBean(UserGlpiConsumer.class);
//	
//		ObjectMapper objectMapper = new ObjectMapper();
//		
//		GlpiRestTemplate restTemplate = context.getBean(GlpiRestTemplate.class);
//		
//		String login = "kuroyukihime@gmail.com";
//		
//		URI uri = consumer.listUsers(login);
//		
//		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
//		
//		JavaType list = objectMapper.getTypeFactory().constructCollectionType(List.class, User.class);
//		ObjectReader objectReader = objectMapper.readerFor(list);
//		List<User> result = objectReader.readValue(response.getBody());
//		
//		result.get(0);
		//b0999gc1o1t5a4v87jsshrfgf7

	}

}
