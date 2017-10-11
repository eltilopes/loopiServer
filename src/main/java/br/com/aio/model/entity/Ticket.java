package br.com.aio.model.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.security.entity.Usuario;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Ticket {

	private Integer id;
	
	@NotNull
	private Integer user;
	
	private Usuario usuario;
	
	@NotBlank
	@Size(min = 2, max = 100)
	private String title;
	
	@NotBlank
	@Size(min = 2, max = 1000)
	private String content;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date date;
	
	@NotNull
	private Integer urgency;
	
	private Integer priority;
	
	private Integer attachments;
	
	private List<Document> documents;
	
	private List<Accompaniment> accompaniment;
	
	private Integer status;
	
	public Ticket() { }
	
	
	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public List<Accompaniment> getAccompaniment() {
		if(accompaniment == null){
			accompaniment = new ArrayList<>();
		}
		return accompaniment;
	}

	public void setAccompaniment(List<Accompaniment> accompaniment) {
		this.accompaniment = accompaniment;
	}

	public Integer getId() {
		return id;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getAttachments() {
		return attachments;
	}

	public void setAttachments(Integer attachments) {
		this.attachments = attachments;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public Integer getUrgency() {
		return urgency;
	}

	public void setUrgency(Integer urgency) {
		this.urgency = urgency;
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
		Ticket other = (Ticket) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [id=");
		builder.append(id);
		builder.append(", user=");
		builder.append(user);
		builder.append(", usuario=");
		builder.append(usuario);
		builder.append(", title=");
		builder.append(title);
		builder.append(", content=");
		builder.append(content);
		builder.append(", date=");
		builder.append(date);
		builder.append(", urgency=");
		builder.append(urgency);
		builder.append(", priority=");
		builder.append(priority);
		builder.append(", attachments=");
		builder.append(attachments);
		builder.append(", documents=");
		builder.append(documents);
		builder.append("]");
		return builder.toString();
	}

	
}
