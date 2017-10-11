package br.com.aio.model.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.exception.BusinessException;
import br.com.aio.model.entity.SecurityAnswer;
import br.com.aio.model.entity.SecurityQuestion;
import br.com.aio.model.repository.dao.SecurityQuestionDao;
import br.com.aio.model.repository.hibernate.SecurityQuestionRepository;
import br.com.aio.model.repository.hibernate.UsuarioRepository;

@Service
public class SecurityQuestionService {
	
	@Inject
	private SecurityQuestionDao dao;
	
	@Inject
	private SecurityQuestionRepository repository;
	
	@Inject
	private SecurityAnswerService securityAnswerService;
	
	public List<SecurityQuestion> getRandomSecurityQuestions(Long idUsuario){
		List<SecurityQuestion> securityQuestions = dao.getRandomSecurityQuestions();
		securityQuestions.stream().forEach(q -> {
			q.setSecurityAnswers(securityAnswerService.getMixedSecurityAnswers(q.getQuery(), idUsuario));
		});
 		return securityQuestions;
	}

	public void compareAnswers(List<SecurityQuestion> securityQuestions) {
		
	}

	@SuppressWarnings("unchecked")
	public void validateQuestions(Map questions, Long idUsuario) {		
		questions.forEach((id, value) -> {
			//get Sql pelo key(id)
			String sqlQuery = dao.getQueryReplySecurity(Long.parseLong((String) id)); 
			//Executar o Sql passando o id do usuario retornando as respostas
			List<SecurityAnswer> securityList = securityAnswerService.getMixedSecurityAnswers(sqlQuery, idUsuario);
			//pega a resposta com o valid 1 e compara com o value(resposta)
			securityList.stream().forEach(q -> {q.validReply((String) value);});
		});
	}
}
