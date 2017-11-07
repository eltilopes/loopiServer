package br.com.aio.model.entity.vo;

import java.util.ArrayList;
import java.util.List;

public class LocalizacaoProfissionalVO {
	
	private Double latitude;
	private Double longitude;
	
	public LocalizacaoProfissionalVO(Double latitude, Double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	
	public static List<LocalizacaoProfissionalVO> getLocalizacoes(){
		List<LocalizacaoProfissionalVO> localizacaoProfissionalVOs = new ArrayList<LocalizacaoProfissionalVO>();
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-3.741395, -38.499196));
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-2.741395, -37.499196));
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-4.741395, -36.499196));
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-5.741395, -35.499196));
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-6.741395, -34.499196));
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-7.741395, -33.499196));
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-8.741395, -32.499196));
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-9.741395, -31.499196));
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-3.741395, -30.499196));
		localizacaoProfissionalVOs.add(new LocalizacaoProfissionalVO(-4.741395, -30.499196));
		return localizacaoProfissionalVOs;
		
	}

}
