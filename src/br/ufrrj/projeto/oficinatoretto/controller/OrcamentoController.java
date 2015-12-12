package br.ufrrj.projeto.oficinatoretto.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.OrcamentoDAO;
import br.ufrrj.projeto.oficinatoretto.model.Orcamento;

public class OrcamentoController {

    public void salvar(Orcamento orcamento) throws Exception {
        new OrcamentoDAO().salvar(orcamento);
    }

    public void alterar(Orcamento orcamento) throws Exception {
        new OrcamentoDAO().alterar(orcamento);
    }

    public List<Orcamento> listaOrcamentos() {
        OrcamentoDAO dao = new OrcamentoDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar orcamento" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new OrcamentoDAO().excluir(id);
    }

}