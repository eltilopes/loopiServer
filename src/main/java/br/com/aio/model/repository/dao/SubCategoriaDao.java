package br.com.aio.model.repository.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.SubCategoria;

@Repository
public class SubCategoriaDao {
	
	@Inject
	private SqlSession sqlSession;

	public List<SubCategoria> getSubCatgorias() {
		return sqlSession.selectList("getSubCatgorias");
	}
	
}
