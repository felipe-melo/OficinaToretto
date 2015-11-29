package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Gerente;

public class GerenteDAO extends GenericDAO<Gerente>{

	@Override
	public Gerente findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Gerente) session.createCriteria(persistentClass)
			.add(Restrictions.eq("usua_id", id)).uniqueResult();
	}

}
