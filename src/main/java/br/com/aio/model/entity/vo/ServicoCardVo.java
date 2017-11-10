package br.com.aio.model.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.model.entity.Categoria;
import br.com.aio.model.entity.Especialidade;
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
	private Double latitude;
	private Double longitude;

	public ServicoCardVo(ServicoProfissional servicoProfissional) {
		id = servicoProfissional.getId();
		title = servicoProfissional.getProfissional().getUsuario().getNome();
		thumbnail = servicoProfissional.getProfissional().getUrlImagem();
		categoria = servicoProfissional.getProfissional().getCategoria();
		subCategoria = servicoProfissional.getProfissional().getSubCategoria();
		especialidade = servicoProfissional.getEspecialidade();
		preco = servicoProfissional.getValor();
		tempo = servicoProfissional.getTempo();
		estrelas = 3;
		favorito = true;
		descricao = servicoProfissional.getNome();
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

}
