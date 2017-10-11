package br.com.aio.model.service;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.aio.model.entity.SecurityAnswer;
import br.com.aio.model.repository.dao.SecurityAnswerDao;

@Service
public class SecurityAnswerService {
	
	@Inject
	private SecurityAnswerDao dao;
	
	public List<SecurityAnswer> getMixedSecurityAnswers(String queryRandomAnswer, Object param){
		List<SecurityAnswer> securityAnswers = dao.getRandomSecurityAnswers(queryRandomAnswer, (Long)param);
		Collections.shuffle(securityAnswers);
		return securityAnswers;
	}

	public List<SecurityAnswer> validAnswer(String query, Long id) {
		return dao.getRandomSecurityAnswers(query, id);
	}

}
