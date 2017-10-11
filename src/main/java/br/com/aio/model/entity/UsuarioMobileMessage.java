package br.com.aio.model.entity;

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
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

import br.com.aio.security.entity.Usuario;

@Entity
@Table(name = "tb_usuario_mobile_message",
	uniqueConstraints = @UniqueConstraint(columnNames = {"cd_usuario_mobile", "cd_mobile_message"})
)
@JsonIgnoreType
public class UsuarioMobileMessage {
	
	@Id
	@GeneratedValue(generator = "seq_usuario_mobile_message", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_usuario_mobile_message", sequenceName="seq_usuario_mobile_message")
	@Column(name = "ci_usuario_mobile_message")
	private Long id;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "fl_status")
	private MessageStatus messageStatus;
	
	@ManyToOne
	@JoinColumn(name = "cd_usuario_mobile")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "cd_mobile_message")
	private MobileMessage message;

	public MessageStatus getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}

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

	public MobileMessage getMessage() {
		return message;
	}

	public void setMessage(MobileMessage message) {
		this.message = message;
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
		UsuarioMobileMessage other = (UsuarioMobileMessage) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
