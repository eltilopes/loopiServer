package br.com.aio.model.entity;

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
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.security.entity.Usuario;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tb_api_key")
@Entity
public class ApiKey {
	
	@Id
	@GeneratedValue(generator = "seq_tb_api_key", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_tb_api_key", sequenceName="seq_tb_api_key")
	@Column(name = "ci_api_key")
	private Long id;
	
	@ManyToMany
	@JoinTable(name = "tb_usuario_mobile_api_key", 
		joinColumns = @JoinColumn(name = "cd_api_key"), 
		inverseJoinColumns = @JoinColumn(name = "cd_usuario_mobile"))
	private List<Usuario> usuarios;
	
	@NotEmpty
	@Transient
	private String login;
	
	@NotEmpty
	@Column(name = "ds_hash")
	private String hash;


	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


}
