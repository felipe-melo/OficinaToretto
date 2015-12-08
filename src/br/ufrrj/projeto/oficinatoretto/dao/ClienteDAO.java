package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Cliente;

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

    public void excluir(Integer id) {
        Cliente f = findById(id);
        delete(f);
    }

	@Override
	public Cliente findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Cliente) session.createCriteria(persistentClass)
			.add(Restrictions.eq("clie_id", id)).uniqueResult();
	}

}
