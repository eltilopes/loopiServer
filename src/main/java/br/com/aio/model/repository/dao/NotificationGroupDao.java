package br.com.aio.model.repository.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.NotificationGroup;
import br.com.aio.model.entity.vo.NotificationGroupVo;

@Repository
public class NotificationGroupDao {
	
	@Inject
	private SqlSession sqlSession;
	
	public List<NotificationGroupVo> getGroups(){
		return sqlSession.selectList("getGroups");
	}

	public List<NotificationGroup> getGroupByName(String name) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", name);
		return sqlSession.selectList("getGroupByName", params);
	}	
}
