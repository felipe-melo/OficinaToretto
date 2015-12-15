package br.ufrrj.projeto.oficinatoretto.model;

import java.math.BigDecimal;
import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.CategoriaDAO;
import br.ufrrj.projeto.oficinatoretto.dao.PecaDAO;

public class PecaFacade {
	
	private Peca peca = new Peca();
	
	public void registraPeca(String descricao, String valorCompra, String valorVenda, Integer quant) {
		BigDecimal compra = new BigDecimal(valorCompra);
		BigDecimal venda = new BigDecimal(valorVenda);
		peca.setDescricao(descricao);
		peca.setValorCompra(compra);
		peca.setValorVenda(venda);
		peca.setQuantidade(quant);
	}
	
	public void registraFabricante(Integer idFabricante) {
		Fabricante fabricante = new Fabricante();
		fabricante.setIdFabricante(idFabricante);
		peca.setFabricante(fabricante);
	}
	
	public void addFornecedor(Integer idFornecedor) {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setIdFornecedor(idFornecedor);
		peca.addFornecedor(fornecedor);
	}
	
	public void registraCategoria(Integer idCategoria) {
		Categoria categoria = new Categoria();
		categoria.setIdCategoria(idCategoria);
		peca.setCategoria(categoria);
	}
	
	public List<Categoria> retornaCategorias() {
		CategoriaDAO dao = new CategoriaDAO();
		try {
			return dao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    public void salvar() throws Exception {
        new PecaDAO().salvar(peca);
    }

    public void alterar() throws Exception {
        new PecaDAO().alterar(peca);
    }

    public List<Peca> listaPecas() {
        PecaDAO dao = new PecaDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar peca" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new PecaDAO().excluir(id);
    }

}