package br.ufrrj.projeto.oficinatoretto.model;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.NotaFiscalDAO;
import br.ufrrj.projeto.oficinatoretto.dao.OrcamentoDAO;
import br.ufrrj.projeto.oficinatoretto.dao.ServicoDAO;

public class NotaFiscalFacade {
	
	private NotaFiscal notaFiscal = new NotaFiscal();
	
	public void registraNotaFiscal (Servico servico) {
		notaFiscal.setServico(servico);
	}
	
	public boolean hasId(){
		return !notaFiscal.isNew();
	}
	
	public NotaFiscal getNotaFiscal() {
		return this.notaFiscal;
	}

    public void salvar() throws Exception {
    	ServicoDAO dao = new ServicoDAO();
    	Servico serv = dao.getEntityManager().merge(notaFiscal.getServico());
    	notaFiscal.setServico(serv);
        new NotaFiscalDAO().salvar(notaFiscal);
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