package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Fornecedor;

public class FornecedorDAO extends GenericDAO<Fornecedor>{
	
	public void salvar(Fornecedor fabricante) {
        try {
			save(fabricante);
		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi possível salvar o fabricante.");
		}
    }

    public void alterar(Fornecedor fabricante) {
        update(fabricante);
    }

    public void excluir(Integer id) {
        Fornecedor f = findById(id);
        delete(f);
    }

	@Override
	public Fornecedor findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Fornecedor) session.createCriteria(persistentClass)
			.add(Restrictions.eq("fabr_id", id)).uniqueResult();
	}

}
