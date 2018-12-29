package br.com.aio.security.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
import javax.validation.constraints.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.model.entity.ApiKey;
import br.com.aio.model.entity.Convite;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "tb_usuario_mobile", schema = "public")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 4530645822525741071L;

	@Id
	@GeneratedValue(generator = "seq_usuario_mobile", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_usuario_mobile", sequenceName="seq_usuario_mobile")
	@Column(name = "ci_usuario_mobile")
	private Long id;
	
	@Transient
	private Integer idUsuarioGlpi;
	
	@NotEmpty
	@Email
	@Column(name = "ds_email")
	private String login;
	
	@NotEmpty
	@Pattern(regexp = "^[0-9]{11}$", message = "{cpf.message}")
	@Column(name = "nr_cpf")
	private String cpf;
	
	@NotEmpty
	@Column(name = "ds_nome")
	private String nome;
	
	@Column(name = "nr_codigo_convite")
	private String codigoConvite;
	
	@NotEmpty
	@Column(name = "ds_url_imagem")
	private String urlImagem;
	
	@NotEmpty
	@Pattern(regexp = "^[0-9]{11}$", message = "{telefone.message}")
	@Column(name = "nr_telefone")
	private String telefone;
	
	//@JsonIgnore
	@NotEmpty
	//@Size(min = 8)
	@Column(name = "ds_senha")
	private String senha;
	
	@JsonIgnore
	@Column(name = "update_lenght")
	private Integer updateLenght = 0;
	
	@Transient
	private String senhaPlana;
	
	@ManyToMany
	@JoinTable(name = "tb_usuario_role", schema = "public",
		joinColumns = @JoinColumn(name = "cd_usuario_mobile"),
		inverseJoinColumns = @JoinColumn(name = "cd_role"))
	@JsonIgnore
	private List<Role> roles;
	
	@Transient
	private List<ApiKey> apiKeys;
	

	public Usuario() { }

	public Usuario(Convite convite) { 
		this.codigoConvite = convite.getCodigoConvite();
		this.nome = convite.getNome();
		this.login = convite.getEmail();
		this.cpf = convite.getCpf();
		this.telefone = convite.getTelefone();
		this.urlImagem = "";
		setSenha(gerarSenhaAleatoria()); 
	}

	public Usuario(String login){
		this.login = login;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIdUsuarioGlpi() {
		return idUsuarioGlpi;
	}

	public void setIdUsuarioGlpi(Integer idUsuarioGlpi) {
		this.idUsuarioGlpi = idUsuarioGlpi;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		if(Objects.nonNull(senha)){
			this.senha = DigestUtils.md5Hex(senha);
			this.senhaPlana = senha;
		}
		else{
			this.senha = null;
		}
	}
	public void setNewSenha(String senha) {
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public List<ApiKey> getApiKeys() {
		return apiKeys;
	}

	public void setApiKeys(List<ApiKey> apiKeys) {
		this.apiKeys = apiKeys;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenhaPlana() {
		return senhaPlana;
	}

	public void setSenhaPlana(String senhaPlana) {
		this.senhaPlana = senhaPlana;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getUpdateLenght() {
		return updateLenght;
	}

	public void setUpdateLenght(Integer updateLenght) {
		this.updateLenght = updateLenght;
	}

	public String getCodigoConvite() {
		return codigoConvite;
	}

	public void setCodigoConvite(String codigoConvite) {
		this.codigoConvite = codigoConvite;
	}

	private String gerarSenhaAleatoria() {

		String senha = "";
		// Determia as letras que poderão estar presente nas chaves
		String letras = "ABCDEFGHIJKLMNOPQRSTUVYWXZ";
		String numeros = "0123456789";
		String caracteres = "@#$&_";

		Random random = new Random();
		int indexLetras = -1;
		int indexNumeros = -1;
		int indexCaracteres = -1;
		for( int i = 0; i < 4; i++ ) {
			   indexLetras = random.nextInt( letras.length() );
			   senha += letras.substring( indexLetras, indexLetras + 1 ) ;
		}
		indexCaracteres = random.nextInt( caracteres.length() );
		senha +=  caracteres.substring( indexCaracteres, indexCaracteres + 1 );
		for( int i = 0; i < 4; i++ ) {
			   indexNumeros = random.nextInt( numeros.length() );
			   senha +=  numeros.substring( indexNumeros, indexNumeros + 1 ) ;
		}
		System.out.println(senha);
		return senha;
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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
