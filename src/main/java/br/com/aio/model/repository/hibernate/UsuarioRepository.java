package br.com.aio.model.repository.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.security.entity.Usuario;

@Repository
@Transactional
public class UsuarioRepository{
	
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

	public void save(Usuario user) {
		getSession().save(user);
	}
	
	public void update(Usuario user) {
		getSession().update(user);
	}
	@SuppressWarnings("unchecked")
	public List<Usuario> getUsers(){
		return getSession().createCriteria(Usuario.class).list();
	}

}
