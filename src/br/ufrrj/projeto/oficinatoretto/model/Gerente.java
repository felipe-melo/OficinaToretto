package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="gerente")
@DiscriminatorValue("G")
public class Gerente extends Usuario{
	
	private static final long serialVersionUID = -5144420434823865321L;
	
//	
//	@Override
//	public boolean isNew() {
//		return usuario.getIdUsuario() == null;
//	}
//
//	public Usuario getUsuario() {
//		return usuario;
//	}
//
//	public void setUsuario(Usuario usuario) {
//		this.usuario = usuario;
//	}

}
