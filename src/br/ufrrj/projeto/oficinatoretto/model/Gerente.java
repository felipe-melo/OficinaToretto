package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="gerente")
public class Gerente implements IEntity{
	
	private static final long serialVersionUID = -5144420434823865321L;
	
	@Id
	@OneToOne
	@JoinColumn(name="usua_id")
	private Usuario usuario;
	
	@Override
	public boolean isNew() {
		return usuario.getIdUsuario() == null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
