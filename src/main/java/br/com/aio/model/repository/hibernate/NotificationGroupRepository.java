package br.com.aio.model.repository.hibernate;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.aio.model.entity.NotificationGroup;

@Repository
@Transactional
public class NotificationGroupRepository {

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

	public void save(NotificationGroup ng) {
		getSession().save(ng);
	}
	
	public void update(NotificationGroup ng) {
		getSession().update(ng);
	}

	public void remove(Long idGroup) {
		Query query = getSession().createQuery("update NotificationGroup set fl_ativo = 0" + " where id = :id");
		query.setParameter("id", idGroup); 
		int result = query.executeUpdate();
		
	}
	public NotificationGroup getGroup(Long id) {
		return (NotificationGroup) getSession().get(NotificationGroup.class, id);
	}
	
}
