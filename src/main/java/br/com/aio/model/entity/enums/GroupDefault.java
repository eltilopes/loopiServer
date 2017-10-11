package br.com.aio.model.entity.enums;

public enum GroupDefault {
	
	ALL("allGroup", "Todos"),
	MASTERSHIP("mastershipGroup", "Magisterio"),
	SUBSTITUTE("substituteGroup", "Substituto"),
	IDLE_HOURS("idleHoursGroup", "CH Ociosa"),
	ADMINISTRATIVE("administrativeGroup", "Administrativo");
	
	private String bean;
	private String name;

	private GroupDefault(String bean, String name) {
		this.bean = bean;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getBean() {
		return bean;
	}

}
