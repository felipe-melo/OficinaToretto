package br.ufrrj.projeto.oficinatoretto.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.FornecedorDAO;
import br.ufrrj.projeto.oficinatoretto.model.Fornecedor;

public class FornecedorController {

    public void salvar(String nome, String telefone) throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setTelefone(telefone);
        fornecedor.setNome(nome);
        
        new FornecedorDAO().salvar(fornecedor);
    }

    public void alterar(String nome, String telefone) throws Exception {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setTelefone(telefone);
        fornecedor.setNome(nome);

        new FornecedorDAO().alterar(fornecedor);
    }

    public List<Fornecedor> listaFornecedors() {
        FornecedorDAO dao = new FornecedorDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar fornecedor" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new FornecedorDAO().excluir(id);
    }

}