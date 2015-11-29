package br.ufrrj.projeto.oficinatoretto.controle;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.UsuarioDAO;
import br.ufrrj.projeto.oficinatoretto.modelo.Usuario;

public class UsuarioController {
	
	private Date formatarData(String data) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return new Date( formatter.parse(data).getTime() );
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
            JOptionPane.showMessageDialog(null, "Problemas ao localizar usuarion" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new UsuarioDAO().excluir(id);
    }

    public Usuario buscaUsuarioPorNome(String nome) throws Exception {
        UsuarioDAO dao = new UsuarioDAO();
        return dao.findByName(nome);
    }

}