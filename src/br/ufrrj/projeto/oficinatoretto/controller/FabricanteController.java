package br.ufrrj.projeto.oficinatoretto.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.FabricanteDAO;
import br.ufrrj.projeto.oficinatoretto.model.Fabricante;

public class FabricanteController {

    public void salvar(String nome, String telefone) throws Exception {
        Fabricante fabricante = new Fabricante();
        fabricante.setTelefone(telefone);
        fabricante.setNome(nome);
        
        new FabricanteDAO().salvar(fabricante);
    }

    public void alterar(String nome, String telefone) throws Exception {
        Fabricante fabricante = new Fabricante();
        fabricante.setTelefone(telefone);
        fabricante.setNome(nome);

        new FabricanteDAO().alterar(fabricante);
    }

    public List<Fabricante> listaFabricantes() {
        FabricanteDAO dao = new FabricanteDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar fabricante" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new FabricanteDAO().excluir(id);
    }

}