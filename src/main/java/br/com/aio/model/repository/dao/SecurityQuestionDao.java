package br.com.aio.model.repository.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.SecurityQuestion;

@Repository
public class SecurityQuestionDao {
	
	@Inject
	private SqlSession session;
	
	public List<SecurityQuestion> getRandomSecurityQuestions(){
		return session.selectList("getRandomSecurityQuestions");
	}

	public String getQueryReplySecurity(Long id) {
		return session.selectOne("getQueryReplySecurity", id);
	}
}
