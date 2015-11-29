package br.ufrrj.projeto.oficinatoretto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Mecanico implements IEntity{
	
	@Id
	@Column(name="usua_id")
	private Integer idUsuario;
	
	@Override
	public boolean isNew() {
		return idUsuario == null;
	}

}
