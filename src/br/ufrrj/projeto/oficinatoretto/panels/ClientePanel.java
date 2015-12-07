package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class ClientePanel extends JLayeredPane {
	private JTextField nome;
	private JTextField cpf;
	private JTextField textField_2;
	private JTextField numero;
	private JTextField logradouro;
	private JTextField complemento;
	private JTextField bairro;
	private JTextField cidade;
	private JTextField estado;
	private JTextField cep;
	public ClientePanel() {
		setLayout(null);
		
		nome = new JTextField();
		nome.setBounds(109, 44, 132, 20);
		add(nome);
		nome.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 44, 46, 14);
		add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 69, 46, 14);
		add(lblCpf);
		
		cpf = new JTextField();
		cpf.setBounds(109, 69, 132, 20);
		add(cpf);
		cpf.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 94, 46, 14);
		add(lblTelefone);
		
		textField_2 = new JTextField();
		textField_2.setBounds(109, 94, 132, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		List list = new List();
		list.setBounds(10, 279, 735, 150);
		add(list);
		
		JLabel lblCarros = new JLabel("Carros");
		lblCarros.setBounds(10, 254, 46, 14);
		add(lblCarros);
		
		JButton btnAdicionar = new JButton("Novo");
		btnAdicionar.setBounds(67, 250, 89, 23);
		add(btnAdicionar);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(122, 472, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.setBounds(10, 472, 89, 23);
		add(btnNewButton_1);
		
		JLabel lblTipoLogradouro = new JLabel("Tipo Logradouro");
		lblTipoLogradouro.setBounds(10, 142, 89, 14);
		add(lblTipoLogradouro);
		
		JComboBox tipoLogradouro = new JComboBox();
		tipoLogradouro.setBounds(109, 139, 132, 20);
		add(tipoLogradouro);
		
		JLabel lblNmero = new JLabel("N\u00FAmero");
		lblNmero.setBounds(352, 47, 46, 14);
		add(lblNmero);
		
		numero = new JTextField();
		numero.setBounds(451, 44, 132, 20);
		add(numero);
		numero.setColumns(10);
		
		logradouro = new JTextField();
		logradouro.setColumns(10);
		logradouro.setBounds(109, 183, 132, 20);
		add(logradouro);
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(10, 186, 46, 14);
		add(lblLogradouro);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(352, 72, 46, 14);
		add(lblComplemento);
		
		complemento = new JTextField();
		complemento.setColumns(10);
		complemento.setBounds(451, 69, 132, 20);
		add(complemento);
		
		JLabel lblBairo = new JLabel("Bairro");
		lblBairo.setBounds(352, 97, 46, 14);
		add(lblBairo);
		
		bairro = new JTextField();
		bairro.setColumns(10);
		bairro.setBounds(451, 94, 132, 20);
		add(bairro);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(352, 125, 46, 14);
		add(lblCidade);
		
		cidade = new JTextField();
		cidade.setColumns(10);
		cidade.setBounds(451, 122, 132, 20);
		add(cidade);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(352, 153, 46, 14);
		add(lblEstado);
		
		estado = new JTextField();
		estado.setColumns(10);
		estado.setBounds(451, 150, 132, 20);
		add(estado);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(352, 186, 46, 14);
		add(lblCep);
		
		cep = new JTextField();
		cep.setColumns(10);
		cep.setBounds(451, 183, 132, 20);
		add(cep);
	}
}
