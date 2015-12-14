package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Peca;

public class PecaDAO extends GenericDAO<Peca>{
	
	public void salvar(Peca peca) throws Exception{
        save(peca);
    }

    public void alterar(Peca peca) {
        update(peca);
    }

    public void excluir(Integer id) {
        Peca f = findById(id);
        delete(f);
    }

	@Override
	public Peca findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Peca) session.createCriteria(persistentClass)
			.add(Restrictions.eq("peca_codigo", id)).uniqueResult();
	}

}
