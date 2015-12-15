package br.ufrrj.projeto.oficinatoretto.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cartao_credito")
@DiscriminatorValue("C")
public class Credito extends Pagamento {
	
	private static final long serialVersionUID = -5144420434823865321L;
	
	@Column(name="cred_quantidade_parcelas")
	private Integer quantParcelas;
	
	@OneToMany(mappedBy="credito", cascade=CascadeType.ALL)
	private List<Parcela> parcelas;

	public Integer getQuantParcelas() {
		return quantParcelas;
	}

	void setQuantParcelas(Integer quantParcelas) {
		parcelas = new ArrayList<Parcela>(); 
		for (int i = 0; i < quantParcelas; i++) {
			Parcela parcela = new Parcela();
			parcela.setPaga(false);
			parcela.setCredito(this);
			
			Calendar cal = Calendar.getInstance(); 
			cal.add(Calendar.MONTH, i + 1);
			
			parcela.setVencimento(cal.getTime());
			parcelas.add(parcela);
		}
		this.quantParcelas = quantParcelas;
	}

	public List<Parcela> getParcelas() {
		return parcelas;
	}

	void setParcelas(List<Parcela> parcelas) {
		this.parcelas = parcelas;
	}

}
