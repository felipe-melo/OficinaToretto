package br.ufrrj.projeto.oficinatoretto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Cliente;
import br.ufrrj.projeto.oficinatoretto.model.Orcamento;

public class ClienteDAO extends GenericDAO<Cliente>{
	
	public void salvar(Cliente cliente) {
        try {
			save(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Não foi possível salvar o cliente.");
		}
    }

    public void alterar(Cliente cliente) {
        update(cliente);
    }
    
    public Cliente searchClienteByCPF(String cpf){
    	Session session = (Session) getEntityManager().getDelegate();
		
		Criteria criteria = session.createCriteria(persistentClass);
        
		if (cpf != null && !cpf.equals("")){
			criteria.add(Restrictions.eq("cpf", cpf));
		}
		
        return (Cliente) criteria.uniqueResult();
    }

    public void excluir(Integer id) {
        Cliente f = findById(id);
        delete(f);
    }

	@Override
	public Cliente findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Cliente) session.createCriteria(persistentClass)
			.add(Restrictions.eq("idCliente", id)).uniqueResult();
	}
	
	public Cliente findByCpf(String cpf) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Cliente) session.createCriteria(persistentClass).add(Restrictions.eq("cpf", cpf)).uniqueResult();
	}

}
