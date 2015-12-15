package br.ufrrj.projeto.oficinatoretto.model;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.ReparoDAO;
import br.ufrrj.projeto.oficinatoretto.model.Reparo;

public class ReparoFacade {
	
	Reparo reparo = new Reparo();
	
	public void registraReparo(String descricaoBreve, String descricaoDetalhada, String valor, String tempoExecucao) {
		this.reparo.setDescricaoBreve(descricaoBreve);
		this.reparo.setDescricaoDetalhada(descricaoDetalhada);
		this.reparo.setValor(new BigDecimal(valor));
		this.reparo.setTempoExecucao(new Integer(tempoExecucao));
	}

    public void salvar() throws Exception {
        new ReparoDAO().salvar(reparo);
        reparo = new Reparo();
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