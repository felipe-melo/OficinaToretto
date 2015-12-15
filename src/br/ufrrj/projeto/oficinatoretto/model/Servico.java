package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="servico")
public class Servico implements IEntity{
	
	private static final long serialVersionUID = 4790716137162703694L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="serv_id")
	private Integer idServico;
	
	@Column(name="serv_quilometragem")
	private Integer quilometragem;
	
	@Column(name="serv_quant_gasolina")
	private String quantGasolina;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="paga_id")
	private Pagamento pagamento;
	
	@OneToOne()
	@JoinColumn(name="orca_id")
	private Orcamento orcamento;
	
	Servico() {}
	
	@Override
	public boolean isNew() {
		return getIdServico() == null;
	}

	public Integer getIdServico() {
		return this.idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}
	
	public Pagamento getPagemento() {
		return this.pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Integer getQuilometragem() {
		return quilometragem;
	}

	public void setQuilometragem(Integer quilometragem) {
		this.quilometragem = quilometragem;
	}

	public String getQuantGasolina() {
		return quantGasolina;
	}

	public void setQuantGasolina(String quantGasolina) {
		this.quantGasolina = quantGasolina;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public Orcamento getOrcamento() {
		return orcamento;
	}

	public void setOrcamento(Orcamento orcamento) {
		this.orcamento = orcamento;
	}
	

}
