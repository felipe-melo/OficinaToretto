package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="carro")
public class Carro implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="carr_id")
	private Integer idCarro;
	
	@Column(name="carr_marca")
	private String marca;
	
	@Column(name="carr_modelo")
	private String modelo;
	
	@Column(name="carr_ano")
	private Integer ano;
	
	@Column(name="carr_cor")
	private String cor;
	
	@Column(name="carr_placa")
	private String placa;
	
	@ManyToOne
	@JoinColumn(name="clie_id")
	private Cliente cliente;

	@Override
	public boolean isNew() {
		return this.idCarro == null;
	}

}
