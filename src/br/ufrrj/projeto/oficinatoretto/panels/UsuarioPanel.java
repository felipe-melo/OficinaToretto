package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class UsuarioPanel extends JLayeredPane {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public UsuarioPanel() {
		setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(79, 41, 132, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 44, 46, 14);
		add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 69, 46, 14);
		add(lblCpf);
		
		textField_1 = new JTextField();
		textField_1.setBounds(79, 66, 132, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 94, 46, 14);
		add(lblTelefone);
		
		textField_2 = new JTextField();
		textField_2.setBounds(79, 91, 132, 20);
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
	}
}
