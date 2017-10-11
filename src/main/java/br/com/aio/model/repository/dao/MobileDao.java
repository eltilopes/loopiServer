package br.com.aio.model.repository.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.vo.MobileVersionVo;

@Repository
public class MobileDao {

	private SqlSession session;
	
	@Inject
	public MobileDao(SqlSession session) {
		this.session = session;
	}
	
	public MobileVersionVo getVersionActive(Integer versionCode){
		Map<String, Object> params = new LinkedHashMap<String, Object>();
		params.put("versionCode", versionCode);
		return session.selectOne("versionActive", params);
	}
}
