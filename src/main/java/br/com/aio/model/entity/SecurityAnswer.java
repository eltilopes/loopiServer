package br.com.aio.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.aio.exception.BusinessException;

@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class SecurityAnswer {
	
	private Long id;
	
	private String description;
	
	private Integer valid;
	
	public SecurityAnswer() { }
	
	public SecurityAnswer(Long id, String description, Integer valid) { 
		this.id = id;
		this.description = description;
		this.valid = valid;
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

	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
	
	public void validReply(String value){
		if(getValid().equals(1) && !getDescription().equals(value)){
			throw new BusinessException("Resposta errada");
		}
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
		SecurityAnswer other = (SecurityAnswer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
