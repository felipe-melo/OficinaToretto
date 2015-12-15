package br.ufrrj.projeto.oficinatoretto.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="peca")
public class Peca implements IEntity{
	
	private static final long serialVersionUID = 4790716137162703694L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="peca_codigo")
	private Integer idPeca;
	
	@Column(name="peca_descricao")
	private String descricao;
	
	@Column(name="peca_quantidade")
	private Integer quantidade;
	
	@Column(name="peca_valor_compra")
	private BigDecimal valorCompra;
	
	@Column(name="peca_valor_venda")
	private BigDecimal valorVenda;
	
	@OneToOne
	@JoinColumn(name="cate_id")
	private Categoria categoria;
	
	@OneToOne
	@JoinColumn(name="fabr_id")
	private Fabricante fabricante;
	
	@ManyToMany
	@JoinTable(
		name="peca_fornecedor",
		joinColumns=
		@JoinColumn(name="peca_codigo"),
		inverseJoinColumns=
		@JoinColumn(name="forn_id")
	)
	private List<Fornecedor> fornecedores;
	
	Peca() {}
	
	Peca(String descricao, BigDecimal valorCompra, BigDecimal valorVenda, Integer quantidade, Categoria categoria, Fabricante fabricante,
			List<Fornecedor> fornecedores) {
		this.descricao = descricao;
		this.valorCompra = valorCompra;
		this.quantidade = quantidade;
		this.valorVenda = valorVenda;
		this.categoria = categoria;
		this.fabricante = fabricante;
		this.fornecedores = fornecedores;
	}
	
	@Override
	public boolean isNew() {
		return getIdPeca() == null;
	}

	public Integer getIdPeca() {
		return this.idPeca;
	}

	void setIdPeca(Integer idPeca) {
		this.idPeca = idPeca;
	}

	public String getDescricao() {
		return descricao;
	}

	void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	
	void addFornecedor(Fornecedor fornecedor) {
		if (fornecedores == null) fornecedores = new ArrayList<Fornecedor>();
		fornecedores.add(fornecedor);
	}

}
