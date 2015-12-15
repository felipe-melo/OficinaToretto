package br.ufrrj.projeto.oficinatoretto.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="orcamento")
public class Orcamento implements IEntity{
	
	private static final long serialVersionUID = 4790716137162703694L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="orca_id")
	private Integer idOrcamento;
	
	@Column(name="orca_data")
	private Date data;
	
	@OneToOne
	@JoinColumn(name="carr_id")
	private Carro carro;
	
	@Column(name="orca_comentario")
	private String comentario;
	
	@Column(name="orca_aprovado")
	private Boolean aprovado;
	
	@ManyToMany
	@JoinTable(
		name="orcamento_reparo",
		joinColumns=
		@JoinColumn(name="orca_id"),
		inverseJoinColumns=
		@JoinColumn(name="repa_id")
	)
	private List<Reparo> reparos;
	
	@ManyToMany
	@JoinTable(
		name="troca",
		joinColumns=
		@JoinColumn(name="orca_id"),
		inverseJoinColumns=
		@JoinColumn(name="peca_codigo")
	)
	private List<Peca> pecas;
	
	@Transient
	private BigDecimal valor;
	
	Orcamento() {}
	
	@Override
	public boolean isNew() {
		return getIdOrcamento() == null;
	}

	public Integer getIdOrcamento() {
		return this.idOrcamento;
	}

	void setIdOrcamento(Integer idOrcamento) {
		this.idOrcamento = idOrcamento;
	}

	public Date getData() {
		return data;
	}

	void setData(Date data) {
		this.data = data;
	}

	public Carro getCarro() {
		return carro;
	}

	void setCarro(Carro carro) {
		this.carro = carro;
	}

	public String getComentario() {
		return comentario;
	}

	void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public List<Reparo> getReparos() {
		return reparos;
	}

	void setReparos(List<Reparo> reparos) {
		this.reparos = reparos;
	}
	
	public List<Peca> getPecas() {
		return pecas;
	}

	void setPecas(List<Peca> pecas) {
		this.pecas = pecas;
	}

	public BigDecimal getValor() {
		
		valor = new BigDecimal(0);
		
		for (Reparo r: reparos) {
			valor = valor.add(r.getValor());
		}
		
		for (Peca p: pecas) {
			valor = valor.add(p.getValorVenda());
		}
		
		return valor;
	}

}
