package br.com.aio.security.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "tb_role", schema = "public")
@JsonSerialize
public class Role implements GrantedAuthority{
	
	private static final long serialVersionUID = 3176814900832143343L;
	
	@Id
	@Column(name = "ci_role")
	private Long id;

	@Column(name = "nm_role")
	@JsonIgnore
	private String label;
	
	@JsonProperty("role")
	public String getAuthority() {
		return label;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@JsonIgnore
	public String getUsedLabel(){
		if( getLabel() != null ){
			return getLabel().trim();
		}else{
			return getId() == null ? null : getId().toString();
		}
	}
	
	public SimpleGrantedAuthority simpleAuthority(){
		return new SimpleGrantedAuthority(getAuthority());
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
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
