package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="nota")
public class NotaFiscal implements IEntity{
	
	private static final long serialVersionUID = 4790716137162703694L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="nota_id")
	private Integer idNotaFiscal;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="nota_numero")
	private Integer numero;
	
	@OneToOne()
	@JoinColumn(name="serv_id")
	private Servico servico;
	
	NotaFiscal() {}
	
	@Override
	public boolean isNew() {
		return getIdNotaFiscal() == null;
	}

	public Integer getIdNotaFiscal() {
		return this.idNotaFiscal;
	}

	public void setIdNotaFiscal(Integer idNotaFiscal) {
		this.idNotaFiscal = idNotaFiscal;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}
