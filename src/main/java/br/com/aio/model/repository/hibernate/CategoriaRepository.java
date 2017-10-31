package br.com.aio.model.repository.hibernate;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoriaRepository{
	
	@Inject
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sf) {
		sessionFactory = sf;
	}

	protected Session getSession() {
		return getSessionFactory().getCurrentSession();
	}


}
