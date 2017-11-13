package br.com.aio.model.repository.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import br.com.aio.model.entity.ServicoProfissional;
import br.com.aio.model.entity.vo.FiltroVo;

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
	public List<ServicoProfissional> getServicos(FiltroVo filtro){
		Criteria crit = getSession().createCriteria(ServicoProfissional.class, "servico");
		if (filtro.getEspecialidade() != null && filtro.getEspecialidade().getId() != null ) {
            crit = crit.createAlias("servico.especialidade", "especialidade");
            crit.add(Restrictions.eq("especialidade.id", filtro.getEspecialidade().getId()));
        } else if (filtro.getSubCategoria() != null && filtro.getSubCategoria().getId() != null ) {
            crit = crit.createAlias("servico.especialidade", "especialidade");
            crit = crit.createAlias("especialidade.subCategoria", "subCategoria");
            crit.add(Restrictions.eq("subCategoria.id", filtro.getSubCategoria().getId()));
        }  else if (filtro.getCategoria() != null && filtro.getCategoria().getId() != null ) {
            crit = crit.createAlias("servico.especialidade", "especialidade");
            crit = crit.createAlias("especialidade.subCategoria", "subCategoria");
            crit = crit.createAlias("subCategoria.categoria", "categoria");
            crit.add(Restrictions.eq("categoria.id", filtro.getCategoria().getId()));
        }       
		if (!Strings.isNullOrEmpty(filtro.getPesquisaToolbar())) {
            crit = crit.createAlias("servico.especialidade", "especialidade");
            crit = crit.createAlias("servico.profissional", "profissional");
            crit = crit.createAlias("profissional.usuario", "usuario");
            crit = crit.createAlias("especialidade.subCategoria", "subCategoria");
            crit = crit.createAlias("subCategoria.categoria", "categoria");
			crit.add(Restrictions.or(Restrictions.ilike("servico.nome", filtro.getPesquisaToolbarLike()), 
					Restrictions.or(Restrictions.ilike("usuario.nome", filtro.getPesquisaToolbarLike()), 
							Restrictions.or(Restrictions.ilike("servico.descricao", filtro.getPesquisaToolbarLike()), 
									Restrictions.or(Restrictions.ilike("especialidade.descricao", filtro.getPesquisaToolbarLike()), 
											Restrictions.or(Restrictions.ilike("subCategoria.descricao", filtro.getPesquisaToolbarLike()), 
													Restrictions.ilike("categoria.descricao", filtro.getPesquisaToolbarLike())))))));
        }
        List<ServicoProfissional> servicos = (List<ServicoProfissional>) crit.list();   
        System.out.println("Total Serviços : " + servicos.size());
        return servicos;
	}

}
