package br.com.aio.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.security.entity.Usuario;
import br.com.aio.model.entity.enums.Status;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tb_convite", schema = "public")
public class Convite{

	@Id
	@GeneratedValue(generator = "seq_convite", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_convite", sequenceName="seq_convite")
	@Column(name = "ci_convite")
	private Long id;
	
	@NotEmpty
	@Email
	@Column(name = "ds_email")
	private String email;
	
	@NotEmpty
	@Pattern(regexp = "^[0-9]{11}$", message = "{cpf.message}")
	@Column(name = "nr_cpf")
	private String cpf;
	
	@NotEmpty
	@Column(name = "nm_convite")
	private String nome;
	
	@Column(name = "ds_chave")
	private String chave;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "fl_status")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name = "cd_usuario_mobile")
	private Usuario usuarioCashBack;
	
	@Column(name = "dt_criacao")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date dataCriacao;
	
	public Convite(){
		status = Status.ATIVO;
		dataCriacao = new Date();
	}
	
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

	public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Usuario getUsuarioCashBack() {
		return usuarioCashBack;
	}

	public void setUsuarioCashBack(Usuario usuarioCashBack) {
		this.usuarioCashBack = usuarioCashBack;
	}

	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
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
		Convite other = (Convite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
