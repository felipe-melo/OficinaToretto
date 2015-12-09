package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fabricante")
public class Fabricante implements IEntity{
	
	private static final long serialVersionUID = 4790716137162703694L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="fabr_id")
	private Integer idFabricante;
	
	@Column(name="fabr_nome")
	private String nome;
	
	@Column(name="fabr_telefone")
	private String telefone;
	
	public Fabricante() {
		
	}
	
	public Fabricante(String nome, String telefone) {
		this.nome = nome;
		this.telefone = telefone;
	}
	
	@Override
	public boolean isNew() {
		return getIdFabricante() == null;
	}

	public Integer getIdFabricante() {
		return this.idFabricante;
	}

	public void setIdFabricante(Integer idFabricante) {
		this.idFabricante = idFabricante;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
