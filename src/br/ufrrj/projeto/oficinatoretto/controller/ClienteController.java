package br.ufrrj.projeto.oficinatoretto.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.ClienteDAO;
import br.ufrrj.projeto.oficinatoretto.model.Cliente;

public class ClienteController {

    public void salvar(Cliente cliente) throws Exception {
        new ClienteDAO().salvar(cliente);
    }

    public void alterar(Cliente cliente) throws Exception {
        new ClienteDAO().alterar(cliente);
    }

    public List<Cliente> listaClientes() {
        ClienteDAO dao = new ClienteDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar cliente" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new ClienteDAO().excluir(id);
    }

}