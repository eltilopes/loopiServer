package br.com.aio.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.security.entity.Usuario;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tb_profissional", schema = "public")
public class Profissional {

	@Id
	@GeneratedValue(generator = "seq_profissional", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_profissional", sequenceName="seq_profissional")
	@Column(name = "ci_profissional")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cd_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "cd_categoria")
    private Categoria categoria;
	
	@ManyToOne
	@JoinColumn(name = "cd_sub_categoria")
    private SubCategoria subCategoria;
	
	@ManyToOne
	@JoinColumn(name = "cd_especialidade")
    private Especialidade especialidade;
	
	@OneToMany(mappedBy = "profissional", fetch = FetchType.LAZY)
	@JsonIgnore
    private List<ServicoProfissional> servicos;

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public List<ServicoProfissional> getServicos() {
		return servicos;
	}

	public void setServicos(List<ServicoProfissional> servicos) {
		this.servicos = servicos;
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
		Profissional other = (Profissional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
