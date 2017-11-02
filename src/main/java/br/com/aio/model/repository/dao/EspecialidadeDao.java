package br.com.aio.model.repository.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.Especialidade;

@Repository
public class EspecialidadeDao {
	
	@Inject
	private SqlSession sqlSession;

	public List<Especialidade> getEspecialidades() {
		return sqlSession.selectList("getEspecialidades");
	}
	
}
