package br.ufrrj.projeto.oficinatoretto.model;

import java.util.List;

import javax.swing.JOptionPane;

import br.ufrrj.projeto.oficinatoretto.dao.ClienteDAO;

public class ClienteFacade {
	
	private Cliente cliente = new Cliente();

    public void registraDadosCliente(String nome, String cpf, String telefone) throws Exception {
		this.cliente.setNome(nome);
		this.cliente.setCpf(cpf);
		this.cliente.setTelefone(telefone);
    }
    
    public void registraDadosEndereco(Integer tipoLogradouro, String logradouro, String numero, String complemento,
			String bairro, String cidade, String estado, String cep) throws Exception {
    	TipoLogradouro tipo = new TipoLogradouro();
    	tipo.setIdTipoLogradouro(tipoLogradouro);
    	Endereco endereco = new Endereco(tipo, logradouro, numero, complemento, bairro, cidade, estado, cep);
    	this.cliente.setEndereco(endereco);
    }
    
    public void registraCarro(String modelo, String marca, Integer ano, String cor, String placa) {
    	Carro carro = new Carro();
		carro.setModelo(modelo);
		carro.setMarca(marca);
		carro.setAno(ano);
		carro.setCor(cor);
		carro.setPlaca(placa);
		carro.setCliente(cliente);
		this.cliente.addCarro(carro);
	}
    
    public void salvaCliente() {
		new ClienteDAO().salvar(this.cliente);
    }
    
    public boolean hasId() {
    	return !cliente.isNew();
    }

    public void alterar() throws Exception {
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
    
    public void findCliente(String cpf){
    	ClienteDAO dao = new ClienteDAO();
    	this.cliente = dao.searchClienteByCPF(cpf);
    }
    
    public Cliente retornaCliente(){
    	return this.cliente;
    }

    public void excluir(Integer id) throws Exception {
        new ClienteDAO().excluir(id);
    }

}
