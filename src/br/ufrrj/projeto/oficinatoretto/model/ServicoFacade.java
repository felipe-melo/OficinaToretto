package br.ufrrj.projeto.oficinatoretto.model;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.ServicoDAO;

public class ServicoFacade {
	
	private Servico servico = new Servico();

    public List<Servico> listaServicos(String placa) {
        ServicoDAO dao = new ServicoDAO();
        try {
            return dao.findByCar(placa);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar serviço " + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new ServicoDAO().excluir(id);
    }

}