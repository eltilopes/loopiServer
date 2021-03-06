package br.com.aio.model.entity.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.model.entity.Categoria;
import br.com.aio.model.entity.Especialidade;
import br.com.aio.model.entity.Profissional;
import br.com.aio.model.entity.ServicoProfissional;
import br.com.aio.model.entity.SubCategoria;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicoCardVo {

	private Long id;
	private String title;
	private String descricao;
	private String thumbnail;
	private Categoria categoria;
	private SubCategoria subCategoria;
	private Especialidade especialidade;
	private Double preco;
	private Integer tempo;
	private Integer estrelas;
	private Boolean favorito;
	private Integer favoritos;
	private Double latitude;
	private Double longitude;
	private String duracao;
	private List<ServicoProfissional> servicos;

	public ServicoCardVo(Profissional profissional) {
		id = profissional.getId();
		title = profissional.getUsuario().getNome();
		thumbnail = profissional.getUrlImagem();
		categoria = profissional.getCategoria();
		subCategoria = profissional.getSubCategoria();
		especialidade = profissional.getEspecialidade();
		latitude = profissional.getLocalizacao().getLatitude();
		longitude = profissional.getLocalizacao().getLongitude();
		estrelas = 3;
		favorito = profissional.getFavorito() ;
		favoritos = profissional.getFavoritos();
		if(!profissional.getServicos().isEmpty() && profissional.getServicos().size()==1){
			preco = profissional.getServicos().get(0).getValor();
			tempo = profissional.getServicos().get(0).getTempo();
			descricao = profissional.getServicos().get(0).getNome();
		}
		setServicos(profissional.getServicos());
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public Integer getEstrelas() {
		return estrelas;
	}

	public void setEstrelas(Integer estrelas) {
		this.estrelas = estrelas;
	}

	public Boolean getFavorito() {
		return favorito;
	}

	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ServicoProfissional> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoProfissional> servicos) {
		this.servicos = servicos;
	}

	public String getDuracao() {
		return duracao;
	}

	public void setDuracao(String duracao) {
		this.duracao = duracao;
	}

	public Integer getFavoritos() {
		return favoritos;
	}

	public void setFavoritos(Integer favoritos) {
		this.favoritos = favoritos;
	}

}
