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
@Table(name="fornecedor")
public class Fornecedor implements IEntity{
	
	private static final long serialVersionUID = 4790716137162703694L;
	
	public Fornecedor(String nome, String telefone, String responsavel, Endereco endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.responsavel = responsavel;
		this.endereco = endereco;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="forn_id")
	private Integer idFornecedor;
	
	@Column(name="forn_nome")
	private String nome;
	
	@Column(name="forn_telefone")
	private String telefone;
	
	@Column(name="forn_nome_vendedor")
	private String responsavel;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ende_id")
	private Endereco endereco;
	
	@Override
	public boolean isNew() {
		return getIdFornecedor() == null;
	}

	public Integer getIdFornecedor() {
		return this.idFornecedor;
	}

	public void setUsuario(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getNome() {
		return telefone;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setIdFornecedor(Integer idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

}