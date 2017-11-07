package br.com.aio.model.repository.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.model.entity.ServicoProfissional;

@Repository
@Transactional
public class ServicoProfissionalRepository{
	
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

	public void save(ServicoProfissional servicoProfissional ) {
		getSession().save(servicoProfissional);
	}
	
	@SuppressWarnings("unchecked")
	public List<ServicoProfissional> getServicos(){
		return getSession().createCriteria(ServicoProfissional.class).list();
	}

}
