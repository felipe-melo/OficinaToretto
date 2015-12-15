package br.ufrrj.projeto.oficinatoretto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Orcamento;
import br.ufrrj.projeto.oficinatoretto.model.Servico;

public class ServicoDAO extends GenericDAO<Servico> {

	public void salvar(Servico servico) throws Exception {
		save(servico);
	}

	public void alterar(Servico servico) {
		update(servico);
	}

	public void excluir(Integer id) {
		Servico s = findById(id);
		delete(s);
	}

	@Override
	public Servico findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
		return (Servico) session.createCriteria(persistentClass).add(Restrictions.eq("idServico", id)).uniqueResult();
	}

	public List<Servico> findByCar(String car) {
		Session session = (Session) getEntityManager().getDelegate();

		Criteria criteria = session.createCriteria(persistentClass);

		// criteria.createAlias("carro", "car");
		criteria.createAlias("orcamento", "orc");
		if (car != null && !car.equals("")) {
			criteria.add(Restrictions.eq("orc.carro.placa", car));
		}
		
		return (List<Servico>) criteria.list();
	}

}
