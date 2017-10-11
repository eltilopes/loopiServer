package br.com.aio.model.repository.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import br.com.aio.model.entity.ApiKey;
import br.com.aio.security.entity.Usuario;

@Repository
@Transactional
public class ApiKeyDao {
	
	@Inject
	private SqlSession session;
	
	@Inject
	private SessionFactory sessionFactory;
	
	private String inClausule;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	
	public List<ApiKey> getApiKeys(List<Usuario> usuarios){
		StringBuilder builder = new StringBuilder();
		
		List<List<Usuario>> listOfListUsers = Lists.partition(usuarios, 1000);
		
		List<ApiKey> results = new ArrayList<ApiKey>();
		
		listOfListUsers.forEach(listOfUsers-> {
			listOfUsers.forEach(u -> {
				builder.append(u.getId().toString()).append(",");
			});
			
			inClausule = builder.toString();
			
		    if (inClausule.length() > 0) {
		    	inClausule = inClausule.substring(0, inClausule.length() -1);
		    }
			
			results.addAll(session.selectList("listaApiKey", inClausule));
		});
		
		return results;
	}
	
	public ApiKey saveOrUpdateApiKey(ApiKey apiKey){
		getSession().saveOrUpdate(apiKey);
		return apiKey;
	}
	
	public ApiKey getApiKey(ApiKey apiKey){
		return session.selectOne("getApiKeyByHash", apiKey.getHash());
	}

}
