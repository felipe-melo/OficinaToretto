package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.NotaFiscal;

public class NotaFiscalDAO extends GenericDAO<NotaFiscal>{
	
	public void salvar(NotaFiscal notaFiscal) throws Exception {
		save(notaFiscal);
    }

    public void alterar(NotaFiscal notaFiscal) {
        update(notaFiscal);
    }

    public void excluir(Integer id) {
        NotaFiscal f = findById(id);
        delete(f);
    }

	@Override
	public NotaFiscal findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (NotaFiscal) session.createCriteria(persistentClass)
			.add(Restrictions.eq("idNotaFiscal", id)).uniqueResult();
	}

}
