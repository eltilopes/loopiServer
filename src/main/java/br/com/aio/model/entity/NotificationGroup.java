package br.com.aio.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.model.entity.enums.Status;
import br.com.aio.security.entity.Usuario;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "tb_mobile_notification_group")
@Entity
public class NotificationGroup {

	public NotificationGroup() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificationGroup(String nameGroup) {
		super();
		this.nameGroup = nameGroup;
	}

	@Id
	@GeneratedValue(generator = "seq_mobile_notification_group", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_mobile_notification_group", sequenceName="seq_mobile_notification_group")
	@Column(name = "ci_mobile_notification_group")
	private Long id;
	
	@NotBlank
	@Size(min = 2, max = 100)
	@Column(name = "nm_group")
	private String nameGroup;
	
	@Column(name = "dt_criacao")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creationDate = new Date();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="NOTIFICATION_GROUP_HAS_USUARIO", 
	   joinColumns =  @JoinColumn( name = "ci_mobile_notification_group"), 
	   inverseJoinColumns = @JoinColumn(name = "ci_usuario_mobile") 
	)
	private List<Usuario> users;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "fl_ativo")
	private Status status = Status.ATIVO;
	
	@Transient
	private Long qtdUsers;
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Long getQtdUsers() {
		return qtdUsers;
	}

	public void setQtdUsers(Long qtdUsers) {
		this.qtdUsers = qtdUsers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameGroup() {
		return nameGroup;
	}

	public void setNameGroup(String nameGroup) {
		this.nameGroup = nameGroup;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<Usuario> getUsers() {
		return users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
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
		NotificationGroup other = (NotificationGroup) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
