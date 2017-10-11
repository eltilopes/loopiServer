package br.com.aio.model.repository.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.Medico;

@Repository
public class MedicoDao {
	
	@Inject
	private SqlSession sqlSession;
	
	public List<Medico> getMedicos(){
			return sqlSession.selectList("getMedicos");
	}

}
