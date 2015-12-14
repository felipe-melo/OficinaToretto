package br.ufrrj.projeto.oficinatoretto.model;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.FornecedorDAO;

public class FornecedorFacade {
	
	private Fornecedor fornecedor = new Fornecedor();
	
	public void registraDadosFornecedor(String nome, String telefone, String responsavel) throws Exception {
		fornecedor.setNome(nome);
		fornecedor.setTelefone(telefone);
		fornecedor.setResponsavel(responsavel);
    }
    
    public void registraDadosEndereco(Integer tipoLogradouro, String logradouro, String numero, String complemento,
			String bairro, String cidade, String estado, String cep) throws Exception {
    	TipoLogradouro tipo = new TipoLogradouro();
    	tipo.setIdTipoLogradouro(tipoLogradouro);
    	Endereco endereco = new Endereco(tipo, logradouro, numero, complemento, bairro, cidade, estado, cep);
    	fornecedor.setEndereco(endereco);
    }

    public void salvar() throws Exception {
        new FornecedorDAO().salvar(fornecedor);
    }

    public void alterar(Fornecedor fornecedor) throws Exception {
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