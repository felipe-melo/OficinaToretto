package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="mecanico")
public class Mecanico implements IEntity{
	
	@Id
	@OneToOne
	@JoinColumn(name="usua_id")
	private Usuario usuario;
	
	@Override
	public boolean isNew() {
		return usuario.getIdUsuario() == null;
	}

}
