package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="mecanico")
@DiscriminatorValue("M")
public class Mecanico extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
//	@Id
//	@OneToOne
//	@JoinColumn(name="usua_id")
//	private Usuario usuario;
//	
//	@Override
//	public boolean isNew() {
//		return usuario.getIdUsuario() == null;
//	}

}
