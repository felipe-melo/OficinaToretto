package br.ufrrj.projeto.oficinatoretto.model;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.OrcamentoDAO;
import br.ufrrj.projeto.oficinatoretto.dao.PecaDAO;
import br.ufrrj.projeto.oficinatoretto.dao.ReparoDAO;

public class OrcamentoFacade {
	
	private Orcamento orcamento = new Orcamento();
	
	public void registraOrcamento (Date data, String comentario, Boolean aprovado) {
		orcamento.setData(data);
		orcamento.setComentario(comentario);
		orcamento.setAprovado(aprovado);
	}
	
	public void registraCarro(Carro carro) {
		this.orcamento.setCarro(carro);
	}
	
	public void registraPecas(List<Peca> pecas) {
		this.orcamento.setPecas(pecas);
	}
	
	public void registraReparos(List<Reparo> reparos) {
		this.orcamento.setReparos(reparos);
	}
	
	public List<Peca> recuperaPecas() {
		PecaDAO dao = new PecaDAO();
		try {
			return dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Reparo> recuperaReparos() {
		ReparoDAO dao = new ReparoDAO();
		try {
			return dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

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
    
    public List<Orcamento> searchOrcamento(String cpf, String placa) {
    	OrcamentoDAO dao = new OrcamentoDAO();
    	return dao.searchOrcamento(cpf, placa);
    }

    public void excluir(Integer id) throws Exception {
        new OrcamentoDAO().excluir(id);
    }

}