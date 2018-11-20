package br.com.aio.model.repository.hibernate;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;

import br.com.aio.model.entity.FavoritoProfissionalUsuario;
import br.com.aio.model.entity.Profissional;
import br.com.aio.model.entity.ServicoProfissional;
import br.com.aio.model.entity.vo.FiltroVo;

@Repository
@Transactional
public class ProfissionalRepository{
	
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

	public void save(Profissional profissional ) {
		getSession().save(profissional);
	}
	
	public void saveFavoritoProfissionalUsuario(FavoritoProfissionalUsuario favoritoProfissionalUsuario ) {
		getSession().save(favoritoProfissionalUsuario);
	}
	
	public void deleteFavoritoProfissionalUsuario(FavoritoProfissionalUsuario favoritoProfissionalUsuario ) {
		getSession().delete(favoritoProfissionalUsuario);
	}
	

	@SuppressWarnings("unchecked")
	public List<Profissional> getProfissionais(FiltroVo filtro){
		Criteria crit = getSession().createCriteria(Profissional.class, "profissional");
        crit = crit.createAlias("profissional.especialidade", "especialidade");
        crit = crit.createAlias("profissional.usuario", "usuario");
        crit = crit.createAlias("especialidade.subCategoria", "subCategoria");
        crit = crit.createAlias("subCategoria.categoria", "categoria");
        crit = crit.createAlias("profissional.servicos", "servico");
		filtrarEspecialidadeSubCataegoriaCataegoria(filtro, crit);       
		filtrarPesquisaToolbar(filtro, crit);
		ordenarValorNomeProfissional(filtro, crit);
        List<Profissional> profissionais = (List<Profissional>) crit.list();   
        System.out.println("Total Servi�os : " + profissionais.size());
        return profissionais;
	}
	
	@SuppressWarnings("unchecked")
	public List<ServicoProfissional> getServicos(Profissional profissional){
		Criteria crit = getSession().createCriteria(ServicoProfissional.class, "servico");
        crit = crit.createAlias("servico.profissional", "profissional");
        crit.add(Restrictions.eq("profissional.id", profissional.getId()));
        List<ServicoProfissional> servicos = (List<ServicoProfissional>) crit.list();   
        System.out.println("Total Servi�os : " + servicos.size());
        return servicos;
	}

	private void ordenarValorNomeProfissional(FiltroVo filtro, Criteria crit) {
		if(filtro.getMenorValor()!=null){
			crit.addOrder(filtro.getMenorValor() ? Order.asc("servico.valor") : Order.desc("servico.valor"));
		}
		if(filtro.getOrdemAlfabeticaCrescente()!=null){
			crit.addOrder(filtro.getOrdemAlfabeticaCrescente() ? Order.asc("usuario.nome") : Order.desc("usuario.nome"));
		}
	}

	private void filtrarPesquisaToolbar(FiltroVo filtro, Criteria crit) {
		if (!Strings.isNullOrEmpty(filtro.getPesquisaToolbar())) {
			crit.add(Restrictions.or(Restrictions.ilike("servico.nome", filtro.getPesquisaToolbarLike()), 
					Restrictions.or(Restrictions.ilike("usuario.nome", filtro.getPesquisaToolbarLike()), 
							Restrictions.or(Restrictions.ilike("servico.descricao", filtro.getPesquisaToolbarLike()), 
									Restrictions.or(Restrictions.ilike("especialidade.descricao", filtro.getPesquisaToolbarLike()), 
											Restrictions.or(Restrictions.ilike("subCategoria.descricao", filtro.getPesquisaToolbarLike()), 
													Restrictions.ilike("categoria.descricao", filtro.getPesquisaToolbarLike())))))));
        }
	}

	private void filtrarEspecialidadeSubCataegoriaCataegoria(FiltroVo filtro, Criteria crit) {
		if (filtro.getEspecialidade() != null && filtro.getEspecialidade().getId() != null ) {
            crit.add(Restrictions.eq("especialidade.id", filtro.getEspecialidade().getId()));
        } else if (filtro.getSubCategoria() != null && filtro.getSubCategoria().getId() != null ) {
            crit.add(Restrictions.eq("subCategoria.id", filtro.getSubCategoria().getId()));
        }  else if (filtro.getCategoria() != null && filtro.getCategoria().getId() != null ) {
            crit.add(Restrictions.eq("categoria.id", filtro.getCategoria().getId()));
        }
	}

}
