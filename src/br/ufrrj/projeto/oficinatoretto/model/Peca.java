package br.ufrrj.projeto.oficinatoretto.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	
	public Peca() {
		
	}
	
	public Peca(String descricao, BigDecimal valorCompra, BigDecimal valorVenda, Integer quantidade, Categoria categoria, Fabricante fabricante) {
		this.descricao = descricao;
		this.valorCompra = valorCompra;
		this.quantidade = quantidade;
		this.valorVenda = valorVenda;
		this.categoria = categoria;
		this.fabricante = fabricante;
	}
	
	@Override
	public boolean isNew() {
		return getIdPeca() == null;
	}

	public Integer getIdPeca() {
		return this.idPeca;
	}

	public void setIdPeca(Integer idPeca) {
		this.idPeca = idPeca;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

}
