package br.com.aio.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.aio.config.ResourceDetaisFactory;
import br.com.aio.config.ServiceRunning;
import br.com.aio.dao.UsuarioDao;
import br.com.aio.security.entity.Usuario;

@ContextConfiguration(locations = {"classpath:spring/root-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SecurityTest {
	
	@Inject
	private ServiceRunning serviceRunning;
	
	@Inject
	@Qualifier("usuarioDaoTeste")
	private UsuarioDao usuarioDao;
	
	private ResourceOwnerPasswordResourceDetails resource = ResourceDetaisFactory.getDetais();
	
	@Test
	public void loginWithOutCorrectValues(){
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("teste", "teste");
		try {
			restTemplate.postForEntity(serviceRunning.getUrl("/oauth/token"), form, String.class);
			fail("Expected exception");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.UNAUTHORIZED, e.getStatusCode());
		}
	}
	
	@Test
	public void accesForbiddenResource(){
		OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resource);
		MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
		form.add("cpf", "36452122304");
		try {
			restTemplate.getForEntity(serviceRunning.getUrl("/rh/frequencia"), String.class, form);
			fail("Expected exception");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.FORBIDDEN, e.getStatusCode());
		}
	}
	
	@Test
	public void createNewUser() throws SQLException{
		MultiValueMap<String, String> values = new LinkedMultiValueMap<String, String>();
		
		String login = usuarioDao.getUnusedLogin();
		values.add("login", login);
		values.add("senha", "12345678");
		values.add("cpf", usuarioDao.getUnusedCpf());
		
		RestTemplate template = new RestTemplate();
		ResponseEntity<Usuario> response = template.postForEntity(serviceRunning.getUrl("/usuario/novo"), values, Usuario.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(login, response.getBody().getLogin());
	}
	
	@Test
	public void userWithOutEmail(){
		MultiValueMap<String, String> values = new LinkedMultiValueMap<String, String>();
		values.add("senha", "12345678");
		values.add("cpf", "17149622372");
		
		RestTemplate template = new RestTemplate();
		
		try {
			template.postForEntity(serviceRunning.getUrl("/usuario/novo"), values, Usuario.class);
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.BAD_REQUEST, e.getStatusCode()); 
		}
	}
	
	@Test
	public void createNewUserUsingGet(){
		MultiValueMap<String, String> values = new LinkedMultiValueMap<String, String>();
		values.add("login", "ling@gmail.com");
		values.add("senha", "12345678");
		values.add("cpf", "11954523149");
		
		RestTemplate template = new RestTemplate();
		try {
			template.getForEntity(serviceRunning.getUrl("/usuario/novo"), String.class, values);	
			fail("Expected exception");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.METHOD_NOT_ALLOWED, e.getStatusCode()); 
		}
	}

}
