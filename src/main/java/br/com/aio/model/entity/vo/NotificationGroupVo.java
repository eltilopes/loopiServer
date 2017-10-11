package br.com.aio.model.entity.vo;

public class NotificationGroupVo {

	private Long idGroup;	
	private String nmGroup;
	private Long qtdUsers;
	private Integer status;
	private boolean editable = true;
	
	
	public NotificationGroupVo() {
		super();
	}

	public NotificationGroupVo(String nmGroup) {
		super();
		this.nmGroup = nmGroup;
		this.editable = false;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getIdGroup() {
		return idGroup;
	}

	public void setIdGroup(Long idGroup) {
		this.idGroup = idGroup;
	}

	public String getNmGroup() {
		return nmGroup;
	}

	public void setNmGroup(String nmGroup) {
		this.nmGroup = nmGroup;
	}

	public Long getQtdUsers() {
		return qtdUsers;
	}

	public void setQtdUsers(Long qtdUsers) {
		this.qtdUsers = qtdUsers;
	}
}
