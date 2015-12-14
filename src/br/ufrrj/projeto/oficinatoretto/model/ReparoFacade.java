package br.ufrrj.projeto.oficinatoretto.model;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.ReparoDAO;
import br.ufrrj.projeto.oficinatoretto.model.Reparo;

public class ReparoFacade {

    public void salvar(Reparo reparo) throws Exception {
        new ReparoDAO().salvar(reparo);
    }

    public void alterar(Reparo reparo) throws Exception {
        new ReparoDAO().alterar(reparo);
    }

    public List<Reparo> listaReparos() {
        ReparoDAO dao = new ReparoDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar reparo" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new ReparoDAO().excluir(id);
    }

}