package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.ufrrj.projeto.oficinatoretto.dao.ClienteDAO;
import br.ufrrj.projeto.oficinatoretto.model.Carro;
import br.ufrrj.projeto.oficinatoretto.model.Cliente;

public class BuscaCarroDialog extends JDialog {
	private JTextField nome;
	private JTextField cpf;
	private JTextField telefone;
	private JTextField logradouro;
	private JTextField numero;
	private JTextField complemento;
	private JTextField bairro;
	private JTextField cidade;
	private JTextField estado;
	private JTextField cep;
	
	private JTextField tipoLogradouro;
	
	private JScrollPane scrollPane;
	private DefaultTableModel aModel;
	
	private String[] columnNames = {"Marca", "Modelo", "Ano", "Placa"};
	
	private List<Carro> carros = new ArrayList<Carro>();

	/**
	 * Create the dialog.
	 */
	public BuscaCarroDialog(OrcamentoPanel pane, Cliente cliente) {
		
		setBounds(100, 100, 632, 488);
		getContentPane().setLayout(null);
		
		nome = new JTextField();
		nome.setEditable(false);
		nome.setColumns(10);
		nome.setBounds(120, 22, 132, 20);
		getContentPane().add(nome);
		nome.setText(cliente.getNome());
		
		JLabel label = new JLabel("Nome");
		label.setBounds(21, 25, 46, 14);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("CPF");
		label_1.setBounds(21, 56, 46, 14);
		getContentPane().add(label_1);
		
		cpf = new JTextField();
		cpf.setEditable(false);
		cpf.setColumns(10);
		cpf.setBounds(120, 53, 132, 20);
		getContentPane().add(cpf);
		cpf.setText(cliente.getCpf());
		
		JLabel label_2 = new JLabel("Telefone");
		label_2.setBounds(21, 87, 46, 14);
		getContentPane().add(label_2);
		
		telefone = new JTextField();
		telefone.setEditable(false);
		telefone.setColumns(10);
		telefone.setBounds(120, 84, 132, 20);
		getContentPane().add(telefone);
		telefone.setText(cliente.getTelefone());
		
		JLabel label_4 = new JLabel("Tipo Logradouro");
		label_4.setBounds(21, 118, 89, 14);
		getContentPane().add(label_4);
		
		logradouro = new JTextField();
		logradouro.setEditable(false);
		logradouro.setColumns(10);
		logradouro.setBounds(120, 146, 132, 20);
		getContentPane().add(logradouro);
		logradouro.setText(cliente.getEndereco().getLogradouro());
		
		JLabel label_5 = new JLabel("Logradouro");
		label_5.setBounds(21, 149, 89, 14);
		getContentPane().add(label_5);
		
		numero = new JTextField();
		numero.setEditable(false);
		numero.setColumns(10);
		numero.setBounds(427, 22, 132, 20);
		getContentPane().add(numero);
		numero.setText(cliente.getEndereco().getNumero());
		
		JLabel label_3 = new JLabel("N\u00FAmero");
		label_3.setBounds(328, 25, 46, 14);
		getContentPane().add(label_3);
		
		JLabel label_6 = new JLabel("Complemento");
		label_6.setBounds(328, 56, 89, 14);
		getContentPane().add(label_6);
		
		complemento = new JTextField();
		complemento.setEditable(false);
		complemento.setColumns(10);
		complemento.setBounds(427, 53, 132, 20);
		getContentPane().add(complemento);
		complemento.setText(cliente.getEndereco().getComplemento());
		
		bairro = new JTextField();
		bairro.setEditable(false);
		bairro.setColumns(10);
		bairro.setBounds(427, 84, 132, 20);
		getContentPane().add(bairro);
		bairro.setText(cliente.getEndereco().getBairro());
		
		cidade = new JTextField();
		cidade.setEditable(false);
		cidade.setColumns(10);
		cidade.setBounds(427, 115, 132, 20);
		getContentPane().add(cidade);
		cidade.setText(cliente.getEndereco().getCidade());
		
		estado = new JTextField();
		estado.setEditable(false);
		estado.setColumns(10);
		estado.setBounds(427, 146, 132, 20);
		getContentPane().add(estado);
		estado.setText(cliente.getEndereco().getEstado());
		
		cep = new JTextField();
		cep.setEditable(false);
		cep.setColumns(10);
		cep.setBounds(427, 177, 132, 20);
		getContentPane().add(cep);
		cep.setText(cliente.getEndereco().getCep());
		
		JLabel label_7 = new JLabel("CEP");
		label_7.setBounds(328, 180, 46, 14);
		getContentPane().add(label_7);
		
		JLabel label_8 = new JLabel("Estado");
		label_8.setBounds(328, 149, 46, 14);
		getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("Cidade");
		label_9.setBounds(328, 118, 46, 14);
		getContentPane().add(label_9);
		
		JLabel label_10 = new JLabel("Bairro");
		label_10.setBounds(328, 87, 46, 14);
		getContentPane().add(label_10);
		
		JLabel lblCarros = new JLabel("Carros");
		lblCarros.setBounds(21, 225, 108, 14);
		getContentPane().add(lblCarros);
		
		tipoLogradouro = new JTextField();
		tipoLogradouro.setEditable(false);
		tipoLogradouro.setBounds(120, 115, 132, 20);
		getContentPane().add(tipoLogradouro);
		tipoLogradouro.setColumns(10);
		tipoLogradouro.setText(cliente.getEndereco().getTipoLogradouro().getTipo());
		
		aModel = new DefaultTableModel();
		aModel.setColumnIdentifiers(columnNames);
		
		JTable table = new JTable();
		table.setModel(aModel);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(21, 250, 538, 145);
		getContentPane().add(scrollPane);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pane.addCarro(carros.get(table.getSelectedRow()));
				dispose();
			}
		});
		btnSelecionar.setBounds(21, 404, 89, 23);
		getContentPane().add(btnSelecionar);
		
		for (Carro car : cliente.getCarros()) {
			this.addCarro(car);
		}
	}
	
	public void addCarro(Carro carro) {
		this.carros.add(carro);
		Object[] obj = new Object[4];
		obj[0] = carro.getMarca();
		obj[1] = carro.getModelo();
		obj[2] = carro.getAno();
		obj[3] = carro.getPlaca();
		aModel.addRow(obj);
	}
}
