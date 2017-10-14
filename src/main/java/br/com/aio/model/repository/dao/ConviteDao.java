package br.com.aio.model.repository.dao;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.Convite;

@Repository
public class ConviteDao {
	
	@Inject
	private SqlSession sqlSession;

	public Convite getConvite(String cpf) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("cpf", cpf);
		return sqlSession.selectOne("getConvitePorCpf", parameters);
	}
	
}
