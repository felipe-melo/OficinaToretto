package br.ufrrj.projeto.oficinatoretto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Orcamento;

public class OrcamentoDAO extends GenericDAO<Orcamento>{
	
	public void salvar(Orcamento orcamento) {
        try {
			save(orcamento);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Não foi possível salvar o orcamento.");
		}
    }

    public void alterar(Orcamento orcamento) {
        update(orcamento);
    }

    public void excluir(Integer id) {
        Orcamento f = findById(id);
        delete(f);
    }

	@Override
	public Orcamento findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Orcamento) session.createCriteria(persistentClass)
			.add(Restrictions.eq("orca_id", id)).uniqueResult();
	}
	
	public List<Orcamento> searchOrcamento(String cpf, String placa) {
		Session session = (Session) getEntityManager().getDelegate();
		
		Criteria criteria = session.createCriteria(persistentClass);
        
		criteria.createAlias("carro", "car");
		if (cpf != null && !cpf.equals("")){
			criteria.createAlias("car.cliente", "cli");
			criteria.add(Restrictions.eq("cli.cpf", cpf));
		}
		if (placa != null && !placa.equals("")) {
			criteria.add(Restrictions.eq("car.placa", placa));
		}
		criteria.add(Restrictions.eq("aprovado", false));
        
        return (List<Orcamento>) criteria.list();
	}

}
