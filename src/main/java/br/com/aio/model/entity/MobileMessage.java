package br.com.aio.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.model.entity.vo.NotificationGroupVo;
import br.com.aio.security.entity.Usuario;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tb_mobile_message")
@Entity
public class MobileMessage {
	
	@Id
	@GeneratedValue(generator = "seq_mobile_message", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_mobile_message", sequenceName="seq_mobile_message")
	@Column(name = "ci_mobile_message")
	private Long id;
	
	@Transient
	private List<NotificationGroupVo> userGroups;
	
	@Transient
	private List<Usuario> users;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "message", cascade = CascadeType.ALL)
	@JsonIgnoreProperties
	private List<UsuarioMobileMessage> usuarioMobileMessages;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="MESSAGE_HAS_NOTIFICATION_GROUP", 
	   joinColumns =  @JoinColumn( name = "ci_mobile_message"), 
	   inverseJoinColumns = @JoinColumn(name = "ci_mobile_notification_group") 
	)
	private List<NotificationGroup> groups;
	
	@Column(name = "ds_titulo")
	@Length(max=255, message="O título tem que conter no máximo 255 caracteres.")
	private String title;
	
	@Column(name = "ds_corpo")
	@Size(max=1000)
	@Length(max=1000, message="A mensagem tem que conter no máximo 1000 caracteres.")
	private String body;
	
	@Column(name = "dt_criacao")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate = new Date();
	
	@Transient
	private String author;
	
	@Transient
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<NotificationGroupVo> getUserGroups() {
		return userGroups;
	}

	public void setUserGroups(List<NotificationGroupVo> userGroups) {
		this.userGroups = userGroups;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<UsuarioMobileMessage> getUsuarioMobileMessages() {
		return usuarioMobileMessages;
	}

	public void setUsuarioMobileMessages(
			List<UsuarioMobileMessage> usuarioMobileMessages) {
		this.usuarioMobileMessages = usuarioMobileMessages;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	

	public List<NotificationGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<NotificationGroup> groups) {
		this.groups = groups;
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
		MobileMessage other = (MobileMessage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
