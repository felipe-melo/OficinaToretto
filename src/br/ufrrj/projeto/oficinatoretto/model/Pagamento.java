package br.ufrrj.projeto.oficinatoretto.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="pagamento")
@Inheritance(strategy = InheritanceType.JOINED)//Highly normalized  
@DiscriminatorColumn(name="paga_type")
public abstract class Pagamento implements IEntity{
	
	private static final long serialVersionUID = 4790716137162703694L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="paga_id")
	private Integer idPagamento;
	
	@Column(name="paga_valor")
	private BigDecimal valor;
	
	@Column(name="paga_data")
	private Date data;
	
	Pagamento() {}
	
	@Override
	public boolean isNew() {
		return getIdPagamento() == null;
	}

	public Integer getIdPagamento() {
		return this.idPagamento;
	}

	public void setIdServico(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setIdPagamento(Integer idPagamento) {
		this.idPagamento = idPagamento;
	}
}
