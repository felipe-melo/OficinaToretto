package br.ufrrj.projeto.oficinatoretto.model;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.ClienteDAO;

public class ClienteFacade {
	
	private Cliente cliente = new Cliente();

    public void registraDadosCliente(String nome, String cpf, String telefone) throws Exception {
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setTelefone(telefone);
    }
    
    public void registraDadosEndereco(Integer tipoLogradouro, String logradouro, String numero, String complemento,
			String bairro, String cidade, String estado, String cep) throws Exception {
    	TipoLogradouro tipo = new TipoLogradouro();
    	tipo.setIdTipoLogradouro(tipoLogradouro);
    	Endereco endereco = new Endereco(tipo, logradouro, numero, complemento, bairro, cidade, estado, cep);
    	cliente.setEndereco(endereco);
    }
    
    public void salvaCliente() {
		new ClienteDAO().salvar(cliente);
    }
    
    public void addCarroToCliente(Carro carro) {
    	cliente.addCarro(carro);
    }

    public void alterar(Cliente cliente) throws Exception {
        new ClienteDAO().alterar(cliente);
    }

    public List<Cliente> listaClientes() {
        ClienteDAO dao = new ClienteDAO();
        try {
            return dao.findAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas ao localizar cliente" + e.getLocalizedMessage());
        }
        return null;
    }

    public void excluir(Integer id) throws Exception {
        new ClienteDAO().excluir(id);
    }

}