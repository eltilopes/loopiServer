package br.com.aio.model.repository.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.model.entity.Medico;
import br.com.aio.security.entity.Usuario;

@Repository
@Transactional
public class MedicoRepository{
	
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

	public void save(Medico medico) {
		getSession().save(medico);
	}
	
	public void update(Usuario medico) {
		getSession().update(medico);
	}
	@SuppressWarnings("unchecked")
	public List<Medico> getMedicos(){
		return getSession().createCriteria(Medico.class).list();
	}

}
