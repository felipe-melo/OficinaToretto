package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.TipoLogradouro;

public class TipoLogradouroDAO extends GenericDAO<TipoLogradouro>{
	
	@Override
	public TipoLogradouro findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (TipoLogradouro) session.createCriteria(persistentClass)
			.add(Restrictions.eq("logr_id", id)).uniqueResult();
	}

}
