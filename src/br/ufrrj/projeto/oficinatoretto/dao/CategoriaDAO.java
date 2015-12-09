package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Categoria;

public class CategoriaDAO extends GenericDAO<Categoria>{
	
	public void salvar(Categoria categoria) {
        try {
			save(categoria);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Não foi possível salvar o categoria.");
		}
    }

    public void alterar(Categoria categoria) {
        update(categoria);
    }

    public void excluir(Integer id) {
        Categoria f = findById(id);
        delete(f);
    }

	@Override
	public Categoria findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Categoria) session.createCriteria(persistentClass)
			.add(Restrictions.eq("cate_id", id)).uniqueResult();
	}

}
