package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Reparo;

public class ReparoDAO extends GenericDAO<Reparo>{
	
	public void salvar(Reparo reparo) {
        try {
			save(reparo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Não foi possível salvar o reparo.");
		}
    }

    public void alterar(Reparo reparo) {
        update(reparo);
    }

    public void excluir(Integer id) {
        Reparo f = findById(id);
        delete(f);
    }

	@Override
	public Reparo findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Reparo) session.createCriteria(persistentClass)
			.add(Restrictions.eq("repa_id", id)).uniqueResult();
	}

}
