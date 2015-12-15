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
@Table(name="endereco")
public class Endereco implements IEntity{
	
	public Endereco(TipoLogradouro tipoLogradouro, String logradouro, String numero, String complemento,
			String bairro, String cidade, String estado, String cep) {
		this.tipoLogradouro = tipoLogradouro;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
	}
	
	public Endereco() {}
	
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
	
	@ManyToOne
	@JoinColumn(name="logr_id")
	private TipoLogradouro tipoLogradouro;
	
	@Override
	public boolean isNew() {
		return getIdEndereco() == null;
	}

	public Integer getIdEndereco() {
		return idEndereco;
	}

	void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getLogradouro() {
		return logradouro;
	}

	void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	void setCep(String cep) {
		this.cep = cep;
	}

	public TipoLogradouro getTipoLogradouro() {
		return tipoLogradouro;
	}

	void setTipoLogradouro(TipoLogradouro tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

}