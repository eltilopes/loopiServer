package br.com.aio.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "tb_sub_categoria", schema = "public")
@JsonSerialize
public class SubCategoria implements Serializable {
	
	private static final long serialVersionUID = -3616721920404525109L;

	@Id
	@GeneratedValue(generator = "seq_sub_categoria", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_sub_categoria", sequenceName="seq_sub_categoria")
	@Column(name = "ci_sub_categoria")
	private Long id;

	@Column(name = "ds_sub_categoria")
	@JsonIgnore
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "cd_categoria")
	private Categoria categoria;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
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
		SubCategoria other = (SubCategoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
