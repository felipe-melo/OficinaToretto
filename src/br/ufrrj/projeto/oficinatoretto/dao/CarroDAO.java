package br.ufrrj.projeto.oficinatoretto.dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Carro;

public class CarroDAO extends GenericDAO<Carro>{
	
	public void salvar(Carro carro) {
        try {
			save(carro);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("N�o foi poss�vel salvar o carro.");
		}
    }

    public void alterar(Carro carro) {
        update(carro);
    }

    public void excluir(Integer id) {
        Carro c = findById(id);
        delete(c);
    }

	@Override
	public Carro findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Carro) session.createCriteria(persistentClass)
			.add(Restrictions.eq("carr_id", id)).uniqueResult();
	}
	
	public Carro findByPlaca(String placa)
	{
		Session session = (Session) getEntityManager().getDelegate();
        return (Carro) session.createCriteria(persistentClass)
			.add(Restrictions.eq("carr_placa", placa)).uniqueResult();
	}
}
