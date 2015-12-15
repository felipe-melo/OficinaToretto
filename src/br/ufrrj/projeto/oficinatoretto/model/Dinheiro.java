package br.ufrrj.projeto.oficinatoretto.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="dinheiro")
@DiscriminatorValue("D")
public class Dinheiro extends Pagamento {
	
	private static final long serialVersionUID = -5144420434823865321L;
	
	@Column(name="dinh_valor_pago")
	private BigDecimal valorPago;

	public BigDecimal getValorPago() {
		return valorPago;
	}

	void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

}
