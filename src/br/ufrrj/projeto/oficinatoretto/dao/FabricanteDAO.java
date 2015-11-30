package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Fabricante;

public class FabricanteDAO extends GenericDAO<Fabricante>{
	
	public void salvar(Fabricante fabricante) {
        try {
			save(fabricante);
		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi possível salvar o fabricante.");
		}
    }

    public void alterar(Fabricante fabricante) {
        update(fabricante);
    }

    public void excluir(Integer id) {
        Fabricante f = findById(id);
        delete(f);
    }

	@Override
	public Fabricante findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Fabricante) session.createCriteria(persistentClass)
			.add(Restrictions.eq("fabr_id", id)).uniqueResult();
	}

}
