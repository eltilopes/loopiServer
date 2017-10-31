package br.com.aio.model.repository.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.Categoria;

@Repository
public class CategoriaDao {
	
	@Inject
	private SqlSession sqlSession;

	public List<Categoria> getCatgorias() {
		return sqlSession.selectList("getCatgorias");
	}
	
}
