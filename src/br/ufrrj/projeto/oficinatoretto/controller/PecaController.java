package br.ufrrj.projeto.oficinatoretto.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.PecaDAO;
import br.ufrrj.projeto.oficinatoretto.model.Peca;

public class PecaController {

    public void salvar(Peca peca) throws Exception {
        new PecaDAO().salvar(peca);
    }

    public void alterar(Peca peca) throws Exception {
        new PecaDAO().alterar(peca);
    }

    public List<Peca> listaPecas() {
        PecaDAO dao = new PecaDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar peca" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new PecaDAO().excluir(id);
    }

}