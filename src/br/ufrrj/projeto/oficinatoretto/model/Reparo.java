package br.ufrrj.projeto.oficinatoretto.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="reparo")
public class Reparo implements IEntity{
	
	private static final long serialVersionUID = 4790716137162703694L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="repa_id")
	private Integer idReparo;
	
	@Column(name="repa_descricao_breve")
	private String descricaoBreve;
	
	@Column(name="repa_descricao_detalhada")
	private String descricaoDetalhada;
	
	@Column(name="repa_valor")
	private BigDecimal valor;
	
	@Column(name="repa_tempo_execucao")
	private Integer tempoExecucao;
	
	public Reparo() {
		
	}
	
	public Reparo(String descricaoBreve, String descricaoDetalhada, BigDecimal valor, Integer tempoExecucao) {
		this.descricaoBreve = descricaoBreve;
		this.descricaoDetalhada = descricaoDetalhada;
		this.valor = valor;
		this.tempoExecucao = tempoExecucao;
	}
	
	@Override
	public boolean isNew() {
		return getIdReparo() == null;
	}

	public Integer getIdReparo() {
		return this.idReparo;
	}

	void setIdReparo(Integer idReparo) {
		this.idReparo = idReparo;
	}

	public String getDescricaoBreve() {
		return descricaoBreve;
	}

	void setDescricaoBreve(String descricaoBreve) {
		this.descricaoBreve = descricaoBreve;
	}

	public String getDescricaoDetalhada() {
		return descricaoDetalhada;
	}

	void setDescricaoDetalhada(String descricaoDetalhada) {
		this.descricaoDetalhada = descricaoDetalhada;
	}

	public BigDecimal getValor() {
		return valor;
	}

	void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Integer getTempoExecucao() {
		return tempoExecucao;
	}

	void setTempoExecucao(Integer tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}

}
