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
	
	@ManyToOne()
	@JoinColumn(name="clie_id")
	private Cliente cliente;
	
	Carro(){}

	@Override
	public boolean isNew() {
		return this.idCarro == null;
	}

	public Integer getIdCarro() {
		return idCarro;
	}

	void setIdCarro(Integer idCarro) {
		this.idCarro = idCarro;
	}

	public String getMarca() {
		return marca;
	}

	void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getAno() {
		return ano;
	}

	void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getCor() {
		return cor;
	}

	void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	void setPlaca(String placa) {
		this.placa = placa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
