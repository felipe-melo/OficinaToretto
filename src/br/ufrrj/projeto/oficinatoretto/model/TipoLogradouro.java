package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="logradouro")
public class TipoLogradouro implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="logr_id")
	private Integer idTipoLogradouro;
	
	@Column(name="logr_tipo")
	private String tipo;
	
	@Override
	public boolean isNew() {
		return getIdTipoLogradouro() == null;
	}

	public Integer getIdTipoLogradouro() {
		return idTipoLogradouro;
	}

	void setIdTipoLogradouro(Integer idTipoLogradouro) {
		this.idTipoLogradouro = idTipoLogradouro;
	}
	
	public String getTipo() {
		return tipo;
	}

	void setTipo(String tipo) {
		this.tipo = tipo;
	}

}
