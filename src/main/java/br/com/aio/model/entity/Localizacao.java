package br.com.aio.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "tb_localizacao", schema = "public")
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class Localizacao {

	@Id
	@GeneratedValue(generator = "seq_localizacao", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_localizacao", sequenceName="seq_localizacao")
	@Column(name = "ci_localizacao")
	private Long id;

	@Column(name = "nr_latitude")
	private Double latitude;

	@Column(name = "nr_longitude")
	private Double longitude;

	@ManyToOne
	@JoinColumn(name = "cd_profissional")
	@JsonIgnore
	private Profissional profissional;

	@Column(name = "dt_localizacao")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonIgnore
	private Date dataLocalizacao;
	
	public Date getDataLocalizacao() {
		return dataLocalizacao;
	}

	public void setDataLocalizacao(Date dataLocalizacao) {
		this.dataLocalizacao = dataLocalizacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	
	
}
