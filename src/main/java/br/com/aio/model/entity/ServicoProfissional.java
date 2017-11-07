package br.com.aio.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
@Entity
@Table(name = "tb_servico_profissional", schema = "public")
public class ServicoProfissional{

	@Id
	@GeneratedValue(generator = "seq_servico_profissional", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_servico_profissional", sequenceName="seq_servico_profissional")
	@Column(name = "ci_servico_profissional")
	private Long id;

	@NotEmpty
	@Column(name = "nm_servico_profissional",nullable = false)
	private String nome;
	
	@Column(name = "ds_servico_profissional",nullable = false)
	private String descricao;
	
	@Column(name = "nr_tempo",nullable = false)
	private Integer tempo;

	@Column(name = "nr_valor",nullable = false)
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name = "cd_especialidade")
	private Especialidade especialidade ;
	
	@ManyToOne
	@JoinColumn(name = "cd_profissional")
	private Profissional profissional;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
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
		ServicoProfissional other = (ServicoProfissional) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
