package br.ufrrj.projeto.oficinatoretto.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="usuario") 
@Inheritance(strategy = InheritanceType.JOINED)//Highly normalized  
@DiscriminatorColumn(name="usua_type")
public abstract class Usuario implements IEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="usua_id")
	private Integer idUsuario;
	
	@Column(name="usua_username")
	private String  userName;
	
	@Column(name="usua_password")
	private String password;
	
	@Column(name="usua_nome")
	private String nome;

	@Override
	public boolean isNew() {
		return idUsuario == null;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUserName() {
		return userName;
	}

	void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	void setNome(String nome) {
		this.nome = nome;
	}

}
