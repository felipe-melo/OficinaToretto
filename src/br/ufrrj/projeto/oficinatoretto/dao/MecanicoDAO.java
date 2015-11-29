package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Gerente;
import br.ufrrj.projeto.oficinatoretto.model.Mecanico;

public class MecanicoDAO extends GenericDAO<Mecanico>{

	@Override
	public Mecanico findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Mecanico) session.createCriteria(persistentClass)
			.add(Restrictions.eq("usua_id", id)).uniqueResult();
	}

}
