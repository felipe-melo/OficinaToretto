package br.ufrrj.projeto.oficinatoretto.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.OrcamentoDAO;
import br.ufrrj.projeto.oficinatoretto.dao.PecaDAO;
import br.ufrrj.projeto.oficinatoretto.dao.ReparoDAO;
import br.ufrrj.projeto.oficinatoretto.dao.ServicoDAO;
import br.ufrrj.projeto.oficinatoretto.util.Parcelas;
import br.ufrrj.projeto.oficinatoretto.util.QuantGasolina;

public class OrcamentoFacade {
	
	private Orcamento orcamento = new Orcamento();
	private Servico servico = new Servico();
	private List<Orcamento> lista;
	
	public void registraOrcamento (Date data, String comentario) {
		orcamento.setData(data);
		orcamento.setComentario(comentario);
		orcamento.setAprovado(false);
	}
	
	public void registrarServico(String quilometragem, QuantGasolina quant) {
		servico.setQuilometragem(new Integer(quilometragem));
		servico.setQuantGasolina(quant.name());
	}
	
	public void registraDinheiro(String valorPago) {
		BigDecimal valor = new BigDecimal(valorPago);
		Dinheiro dinheiro = new Dinheiro();
		dinheiro.setValorPago(valor);
		dinheiro.setValor(orcamento.getValor());
		dinheiro.setData(new Date());
		servico.setPagamento(dinheiro);
		servico.setOrcamento(orcamento);
	}
	
	public void registraDebito() {
		Debito debito = new Debito();
		debito.setValor(orcamento.getValor());
		debito.setData(new Date());
		servico.setPagamento(debito);
		servico.setOrcamento(orcamento);
	}
	
	public void registraCredito(String quantParcelas) {
		Credito credito = new Credito();
		credito.setQuantParcelas(new Integer(quantParcelas));
		credito.setValor(orcamento.getValor());
		credito.setData(new Date());
		servico.setPagamento(credito);
		servico.setOrcamento(orcamento);
	}
	
	public void registraCarro(Carro carro) {
		this.orcamento.setCarro(carro);
	}
	
	public void salvarServico() throws Exception {
		ServicoDAO dao = new ServicoDAO();
		OrcamentoDAO orcDao = new OrcamentoDAO();
		orcamento.setAprovado(true);
		orcamento = orcDao.getEntityManager().merge(orcamento);
		dao.salvar(servico);
	}
	
	public void registraPecas(List<Peca> pecas) {
		this.orcamento.setPecas(pecas);
	}
	
	public void registraReparos(List<Reparo> reparos) {
		this.orcamento.setReparos(reparos);
	}
	
	public boolean hasId(){
		return !orcamento.isNew();
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

    public void salvar() throws Exception {
        new OrcamentoDAO().salvar(orcamento);
    }

    public void alterar() throws Exception {
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
    
    public Orcamento setOrcamento(Integer index) {
    	orcamento = lista.get(index);
    	return orcamento;
    }
    
    public List<Orcamento> searchOrcamento(String cpf, String placa) {
    	OrcamentoDAO dao = new OrcamentoDAO();
    	return this.lista = dao.searchOrcamento(cpf, placa);
    }

    public void excluir(Integer id) throws Exception {
        new OrcamentoDAO().excluir(id);
    }

}