
package br.com.aio.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.security.entity.Usuario;
import br.com.aio.model.entity.enums.Favorito;

@JsonSerialize
@Entity
@Table(name = "tb_profissional_favorito_usuario", schema = "public")
public class FavoritoProfissionalUsuario{

	@Id
	@GeneratedValue(generator = "seq_profissional_favorito_usuario", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_profissional_favorito_usuario", sequenceName="seq_profissional_favorito_usuario")
	@Column(name = "ci_profissional_favorito_usuario")
	private Long id;

	@Column(name = "dt_criacao")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataCriacao = new Date();

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "fl_favorito")
	private Favorito favorito = Favorito.SIM;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_usuario_mobile")
	private Usuario usuario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_profissional")
	private Profissional profissional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Favorito getFavorito() {
		return favorito;
	}

	public void setFavorito(Favorito favorito) {
		this.favorito = favorito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoritoProfissionalUsuario other = (FavoritoProfissionalUsuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
