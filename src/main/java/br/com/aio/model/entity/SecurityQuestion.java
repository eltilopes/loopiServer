package br.com.aio.model.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityQuestion {
	
	private Long id;
	
	private String description;
	
	@JsonIgnore
	private String query;
	
	private List<SecurityAnswer> securityAnswers;
	
	public SecurityQuestion(){ }
	
	public SecurityQuestion(Long id, String description, List<SecurityAnswer> securityAnswers){
		this.id = id;
		this.description = description;
		this.securityAnswers = securityAnswers;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public List<SecurityAnswer> getSecurityAnswers() {
		return securityAnswers;
	}

	public void setSecurityAnswers(List<SecurityAnswer> securityAnswers) {
		this.securityAnswers = securityAnswers;
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
		SecurityQuestion other = (SecurityQuestion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
