package br.ufrrj.projeto.oficinatoretto.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente implements IEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="clie_id")
	private Integer idCliente;
	
	@Column(name="clie_nome")
	private String nome;
	
	@Column(name="clie_cpf")
	private String cpf;
	
	@Column(name="clie_telefone")
	private String telefone;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ende_id")
	private Endereco endereco;
	

	@OneToMany(mappedBy="cliente", cascade=CascadeType.ALL)
	private List<Carro> carros;
	
	Cliente(){}

	@Override
	public boolean isNew() {
		return this.idCliente == null;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}
	
	public void addCarro(Carro carro) {
		if (this.carros == null) this.carros = new ArrayList<Carro>();
		this.carros.add(carro);
	}
}
