package br.ufrrj.projeto.oficinatoretto.dao;

import br.ufrrj.projeto.oficinatoretto.modelo.Usuario;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	/*public Usuario login(String userName, String password) throws IllegalArgumentException {
		Query query = em.createQuery("SELECT u from Usuario u WHERE u.userName = :user and u.password = :pwd");
		query.setParameter("user", userName);
		query.setParameter("pwd", password);
		
		try {
			return (Usuario) query.getSingleResult();
		} catch (Exception e){
			throw new IllegalArgumentException("Usuario ou senha inválido.");
		}
	}*/
	
	public void salvar(Usuario usuario) {
        save(usuario);
    }

    public void alterar(Usuario usuario) {
        update(usuario);
    }

    public void excluir(Integer id) {
        Usuario c = findById(id);
        delete(c);
    }

}
