package br.ufrrj.projeto.oficinatoretto.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.UsuarioDAO;
import br.ufrrj.projeto.oficinatoretto.model.Usuario;

public class UsuarioController {
	
	public Usuario login(String userName, String password) {
		UsuarioDAO dao = new UsuarioDAO();
        return dao.login(userName, password);
	}

    public void salvar(String userName, String password, String nome) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setUserName(userName);
        usuario.setPassword(password);

        new UsuarioDAO().salvar(usuario);
    }

    public void alterar(Integer id, String userName, String password, String nome) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);
        usuario.setUserName(userName);
        usuario.setPassword(password);
        usuario.setNome(nome);

        new UsuarioDAO().alterar(usuario);
    }

    public List<Usuario> listaUsuarios() {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar usuario " + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new UsuarioDAO().excluir(id);
    }

}