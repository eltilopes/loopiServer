package br.com.aio.model.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.model.entity.Categoria;
import br.com.aio.model.entity.Especialidade;
import br.com.aio.model.entity.SubCategoria;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FiltroVo {

	private Categoria categoria;
    private SubCategoria subCategoria;
    private Especialidade especialidade;
    private String pesquisaToolbar;
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public SubCategoria getSubCategoria() {
		return subCategoria;
	}
	public void setSubCategoria(SubCategoria subCategoria) {
		this.subCategoria = subCategoria;
	}
	public Especialidade getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	public String getPesquisaToolbar() {
		return pesquisaToolbar;
	}
	public String getPesquisaToolbarLike() {
		return "%" + pesquisaToolbar + "%";
	}
	public void setPesquisaToolbar(String pesquisaToolbar) {
		this.pesquisaToolbar = pesquisaToolbar;
	}

	
}
