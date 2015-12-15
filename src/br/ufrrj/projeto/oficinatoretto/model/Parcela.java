package br.ufrrj.projeto.oficinatoretto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="parcela")
public class Parcela implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="parc_id")
	private Integer idParcela;
	
	@Column(name="parc_vencimento")
	private Date vencimento;
	
	@Column(name="parc_paga")
	private Boolean paga;
	
	@ManyToOne()
	@JoinColumn(name="paga_id")
	private Credito credito;
	
	Parcela() {}
	
	@Override
	public boolean isNew() {
		return getIdParcela() == null;
	}

	public Integer getIdParcela() {
		return idParcela;
	}

	public void setIdParcela(Integer idParcela) {
		this.idParcela = idParcela;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Boolean getPaga() {
		return paga;
	}

	public void setPaga(Boolean paga) {
		this.paga = paga;
	}

	public Credito getCredito() {
		return credito;
	}

	public void setCredito(Credito credito) {
		this.credito = credito;
	}

}
