package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Orcamento;

public class OrcamentoDAO extends GenericDAO<Orcamento>{
	
	public void salvar(Orcamento orcamento) {
        try {
			save(orcamento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Não foi possível salvar o orcamento.");
		}
    }

    public void alterar(Orcamento orcamento) {
        update(orcamento);
    }

    public void excluir(Integer id) {
        Orcamento f = findById(id);
        delete(f);
    }

	@Override
	public Orcamento findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Orcamento) session.createCriteria(persistentClass)
			.add(Restrictions.eq("orca_id", id)).uniqueResult();
	}

}
