package br.com.aio.model.entity.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SolicitarPedidoVo {

	private String loginSolicitante;
	private Long idProfissional;
	private List<Long> idServicos;
	private Boolean solicitado;

	public String getLoginSolicitante() {
		return loginSolicitante;
	}
	public void setLoginSolicitante(String loginSolicitante) {
		this.loginSolicitante = loginSolicitante;
	}
	public Long getIdProfissional() {
		return idProfissional;
	}
	public void setIdProfissional(Long idProfissional) {
		this.idProfissional = idProfissional;
	}
	public List<Long> getIdServicos() {
		return idServicos;
	}
	public void setIdServicos(List<Long> idServicos) {
		this.idServicos = idServicos;
	}
	public Boolean getSolicitado() {
		return solicitado;
	}
	public void setSolicitado(Boolean solicitado) {
		this.solicitado = solicitado;
	}
	
	
}
