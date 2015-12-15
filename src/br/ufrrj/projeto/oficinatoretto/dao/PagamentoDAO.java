package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Pagamento;

public class PagamentoDAO extends GenericDAO<Pagamento>{
	
	public void salvar(Pagamento pagamento) {
        try {
			save(pagamento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Não foi possível salvar o pagamento.");
		}
    }

    public void alterar(Pagamento pagamento) {
        update(pagamento);
    }

    public void excluir(Integer id) {
        Pagamento s = findById(id);
        delete(s);
    }

	@Override
	public Pagamento findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Pagamento) session.createCriteria(persistentClass)
			.add(Restrictions.eq("idPagamento", id)).uniqueResult();
	}

}
