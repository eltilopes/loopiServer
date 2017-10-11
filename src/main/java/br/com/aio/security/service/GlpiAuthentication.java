package br.com.aio.security.service;
//package br.com.aio.security.service;
//
//import javax.inject.Inject;
//
//import org.apache.log4j.Logger;
//import org.springframework.http.HttpEntity;
//import org.springframework.web.client.RestTemplate;
//
//import br.com.aio.model.consumer.LoginGlpiConsumer;
//import br.com.aio.security.entity.UsuarioGlpi;
//
//public class GlpiAuthentication {
//	
//	private static final Logger logger = Logger.getLogger(GlpiAuthentication.class);
//	
//	private static final String LOGIN = "glpi";
//	
//	private static final String SENHA = "glpi_disable";
//	
//	@Inject
//	private LoginGlpiConsumer consumer;
//	
//	public String getAuthenticationToken(){
//		logger.info("criando session no glpi");
//		RestTemplate template = new RestTemplate();
//		HttpEntity<UsuarioGlpi> response = template.getForEntity(consumer.doLogin(LOGIN, SENHA), UsuarioGlpi.class);
//		return response.getBody().getSession();
//	}
//
//}
