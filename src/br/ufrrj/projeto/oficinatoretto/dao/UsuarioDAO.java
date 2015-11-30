package br.ufrrj.projeto.oficinatoretto.dao;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.ufrrj.projeto.oficinatoretto.model.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	public Usuario login(String userName, String password) throws IllegalArgumentException {
		Query query = entityManager.createQuery("SELECT u from Usuario u WHERE u.userName = :user and u.password = :pwd");
		query.setParameter("user", userName);
		query.setParameter("pwd", password);
		try {
			return (Usuario) query.getSingleResult();
		} catch (Exception e){
			throw new IllegalArgumentException("Usuario ou senha inválido.");
		}
	}
	
	public void salvar(Usuario usuario) {
        try {
			save(usuario);
		} catch (Exception e) {
			throw new IllegalArgumentException("Não foi possível salvar o usuário.");
		}
    }

    public void alterar(Usuario usuario) {
        update(usuario);
    }

    public void excluir(Integer id) {
        Usuario c = findById(id);
        delete(c);
    }

	@Override
	public Usuario findById(Integer id) {
		Session session = (Session) getEntityManager().getDelegate();
        return (Usuario) session.createCriteria(persistentClass)
			.add(Restrictions.eq("usua_id", id)).uniqueResult();
	}

}
