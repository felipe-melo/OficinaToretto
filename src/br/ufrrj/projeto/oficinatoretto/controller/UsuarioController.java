package br.ufrrj.projeto.oficinatoretto.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.ClienteDAO;
import br.ufrrj.projeto.oficinatoretto.model.Usuario;

public class UsuarioController {
	
	public Usuario login(String userName, String password) {
		ClienteDAO dao = new ClienteDAO();
        return dao.login(userName, password);
	}

    public void salvar(String userName, String password, String nome) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setUserName(userName);
        usuario.setPassword(password);

        new ClienteDAO().salvar(usuario);
    }

    public void alterar(Integer id, String userName, String password, String nome) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);
        usuario.setUserName(userName);
        usuario.setPassword(password);
        usuario.setNome(nome);

        new ClienteDAO().alterar(usuario);
    }

    public List<Usuario> listaUsuarios() {
        ClienteDAO dao = new ClienteDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar usuarion" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new ClienteDAO().excluir(id);
    }

}