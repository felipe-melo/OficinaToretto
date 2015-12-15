package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="cartao_debito")
@DiscriminatorValue("Deb")
public class Debito extends Pagamento {
	
	private static final long serialVersionUID = -5144420434823865321L;


}
