package br.ufrrj.projeto.oficinatoretto.controller;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.CarroDAO;
import br.ufrrj.projeto.oficinatoretto.model.Carro;

public class CarroController {

    public void salvar(Carro carro) throws Exception {
        new CarroDAO().salvar(carro);
    }

    public void alterar(Carro carro) throws Exception {
        new CarroDAO().alterar(carro);
    }

    public List<Carro> listaFabricantes() {
        CarroDAO dao = new CarroDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar carro " + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new CarroDAO().excluir(id);
    }

}