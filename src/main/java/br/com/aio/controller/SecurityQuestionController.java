package br.com.aio.controller;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.Mail;
import br.com.aio.model.service.MailManager;
import br.com.aio.model.service.SecurityQuestionService;
import br.com.aio.model.service.UsuarioService;
import br.com.aio.security.entity.Usuario;

@Controller
@RequestMapping(value = "/pergunta")
public class SecurityQuestionController {

	@Inject
	private SecurityQuestionService securityQuestionService;
	@Inject
	private UsuarioService usuarioService;
	@Inject
	private MailManager mailManager;
	@Resource(name = "messages")
	private Properties messages;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView securityQuestions(@RequestParam String key) {
		Usuario user = usuarioService.getUserByKey(key);
		if (Objects.nonNull(user)) {
			ModelAndView view = new ModelAndView("perguntas");
			view.addObject("questions",
					securityQuestionService.getRandomSecurityQuestions(user.getId()));
//			securityQuestionService.getRandomSecurityQuestions(user.getFuncionario().getId()));
			view.addObject("idUsuario", user.getId().intValue());
			return view;
		} else {
			return new ModelAndView("wrong-key");
		}
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/enviar", method = RequestMethod.POST)
	public ResponseEntity sendAnswers(@RequestBody Map<String, Object> params) throws BindException, IOException {
		String key = (String) params.get("key");
		Map question = (Map) params.get("questions");
		if(question == null || key == null){
			return new ResponseEntity<>(messages.get("bad.request"), HttpStatus.BAD_REQUEST);
		}
		Usuario user = usuarioService.getUserByKey(key);
		if (Objects.isNull(user)) {
			return new ResponseEntity<>(messages.get("security.send.answers.nocontent"),HttpStatus.NO_CONTENT);
		}
		try {
			usuarioService.updateUserByAux(user, user.getLogin(), question);
			return new ResponseEntity<>(messages.get("security.send.answers.ok"), HttpStatus.OK);
		} catch (BusinessException erro) {
			return new ResponseEntity<String>(erro.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(value = "/teste")
	@ResponseBody
	public ResponseEntity<String> teste() {
		Mail mail = new Mail();

		mail.setTo("stanleyaraujo2@gmail.com");
		mail.setFrom("stanleyaraujo2@hotmail.com");
		mail.setSubject("teste de assunto");
		mail.setText("teste de envio de email");

		try {
			mailManager.sendMail(mail);
			return new ResponseEntity<String>("deu certo", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
