package br.com.aio.model.repository.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.SecurityAnswer;

@Repository
public class SecurityAnswerDao {
	
	@Inject
	private SqlSession session;
	
	public SecurityAnswer getSecurityAnswer(String query, Object id){
		return session.selectOne("getSecurityAnswer", new StringBuilder().append(query).append(((Long) id).intValue()).toString());
	}

	public List<SecurityAnswer> getRandomSecurityAnswers(String query, Long param) {
		query = query.replace("{value}", param.toString());
		return session.selectList("getRandomSecurityAnswers", 
				new StringBuilder()
					.append(query)
					.toString());
	}
}
