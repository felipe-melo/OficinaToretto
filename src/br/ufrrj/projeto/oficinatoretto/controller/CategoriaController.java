package br.ufrrj.projeto.oficinatoretto.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.CategoriaDAO;
import br.ufrrj.projeto.oficinatoretto.model.Categoria;

public class CategoriaController {

    public void salvar(Categoria categoria) throws Exception {
        new CategoriaDAO().salvar(categoria);
    }

    public void alterar(Categoria categoria) throws Exception {
        new CategoriaDAO().alterar(categoria);
    }

    public List<Categoria> listaCategorias() {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar categoria" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new CategoriaDAO().excluir(id);
    }

}