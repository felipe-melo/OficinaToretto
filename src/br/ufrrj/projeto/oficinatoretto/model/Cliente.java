package br.ufrrj.projeto.oficinatoretto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="cliente")
public class Cliente implements IEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="clie_id")
	private Integer idCliente;
	
	@Column(name="clie_cpf")
	private String cpf;
	
	@Column(name="clie_nome")
	private String nome;
	
	@Column(name="clie_telefone")
	private String telefone;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ende_id")
	private Endereco endereco;
	

	@OneToMany(mappedBy="cliente")
	private List<Carro> carros;

	@Override
	public boolean isNew() {
		return this.idCliente == null;
	}

}
