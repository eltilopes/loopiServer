package br.com.aio.model.repository.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import br.com.aio.model.entity.Profissional;
import br.com.aio.model.entity.vo.FiltroVo;

@Repository
public class ProfissionalDao {
	
	@Inject
	private SqlSession sqlSession;

	public List<Profissional> getProfissionais(FiltroVo filtro) {
		List<Profissional> lista = new ArrayList<>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("menorValor", filtro.getMenorValor());
			params.put("ordemAlfabeticaCrescente", filtro.getOrdemAlfabeticaCrescente());
			params.put("pesquisaToolbar", filtro.getPesquisaToolbar());
			params.put("pesquisaToolbarLike", filtro.getPesquisaToolbarLike());
			params.put("idCategoria", Objects.nonNull(filtro.getCategoria()) ? filtro.getCategoria().getId() : null);
			params.put("idSubCategoria", Objects.nonNull(filtro.getSubCategoria()) ? filtro.getSubCategoria().getId() : null);
			params.put("idEspecialidade", Objects.nonNull(filtro.getEspecialidade()) ? filtro.getEspecialidade().getId() : null);
			lista = sqlSession.selectList("getProfissionais", params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
