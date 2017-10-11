package br.com.aio.model.entity.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReposicaoVo {
		
	private Integer minRepor;
	
	private Integer minReposto;
	
	private String local;

	public Integer getMinRepor() {
		return minRepor;
	}

	public void setMinRepor(Integer minRepor) {
		this.minRepor = minRepor;
	}

	public Integer getMinReposto() {
		return minReposto;
	}

	public void setMinReposto(Integer minReposto) {
		this.minReposto = minReposto;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	
}
