package br.com.aio.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tb_medico", schema = "public")
public class Medico implements Serializable{

	private static final long serialVersionUID = -8843276551557861517L;

	@Id
	@GeneratedValue(generator = "seq_medico", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_medico", sequenceName="seq_medico")
	@Column(name = "ci_medico")
	private Long id;
	
	@NotEmpty
	@Email
	@Column(name = "ds_email")
	private String email;

	@NotEmpty
	@Column(name = "ds_crm")
	private String crm;
	
	@NotEmpty
	@Pattern(regexp = "^[0-9]{11}$", message = "{cpf.message}")
	@Column(name = "nr_cpf")
	private String cpf;
	
	@NotEmpty
	@Column(name = "nm_medico")
	private String nome;
	
	@NotEmpty
	@Column(name = "nm_url_foto")
	private String urlFoto;
	
	@ManyToMany
	@JoinTable(name = "tb_especialidade_medico", schema = "public",
		joinColumns = @JoinColumn(name = "cd_medico"),
		inverseJoinColumns = @JoinColumn(name = "cd_especialidade"))
	@JsonIgnore
	private List<Especialidade> especialidades;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
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
		Medico other = (Medico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
