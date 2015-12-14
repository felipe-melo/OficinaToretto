package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="categoria")
public class Categoria implements IEntity{
	
	private static final long serialVersionUID = 4790716137162703694L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="cate_id")
	private Integer idCategoria;
	
	@Column(name="cate_nome")
	private String nome;
	
	@OneToOne()
	@JoinColumn(name="cate_super_id")
	private Categoria categoria ;
	
	Categoria() {
	}
	
	@Override
	public boolean isNew() {
		return getIdCategoria() == null;
	}

	public Integer getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
