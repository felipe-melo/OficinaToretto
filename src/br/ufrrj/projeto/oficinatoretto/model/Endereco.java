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
@Table(name="endereco")
public class Endereco implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ende_id")
	private Integer idEndereco;
	
	@Column(name="ende_logradouro")
	private String logradouro;
	
	@Column(name="ende_numero")
	private String numero;
	
	@Column(name="ende_complemento")
	private String complemento;
	
	@Column(name="ende_bairro")
	private String bairro;
	
	@Column(name="ende_cidade")
	private String cidade;
	
	@Column(name="ende_estado")
	private String estado;
	
	@Column(name="ende_cep")
	private String cep;
	
	@OneToOne
	@JoinColumn(name="logr_id")
	private TipoLogradouro tipoLogradouro;
	
	@Override
	public boolean isNew() {
		return getIdEndereco() == null;
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

}