package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.ufrrj.projeto.oficinatoretto.controller.ClienteController;
import br.ufrrj.projeto.oficinatoretto.dao.TipoLogradouroDAO;
import br.ufrrj.projeto.oficinatoretto.model.Carro;
import br.ufrrj.projeto.oficinatoretto.model.Cliente;
import br.ufrrj.projeto.oficinatoretto.model.Endereco;
import br.ufrrj.projeto.oficinatoretto.model.TipoLogradouro;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class ClientePanel extends JLayeredPane {
	
	private JTextField nome;
	private JTextField cpf;
	private JTextField telefone;
	private JTextField numero;
	private JTextField logradouro;
	private JTextField complemento;
	private JTextField bairro;
	private JTextField cidade;
	private JTextField estado;
	private JTextField cep;
	
	private JComboBox tipoLogradouro;
	
	private List<Carro> carros;
	private JScrollPane scrollPane;
	
	private DefaultTableModel aModel;
	
	private String[] columnNames = {"Marca", "Modelo", "Ano", "Placa"};
	
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
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
		
		telefone = new JTextField();
		telefone.setBounds(109, 94, 132, 20);
		add(telefone);
		telefone.setColumns(10);
		
		JLabel lblCarros = new JLabel("Carros");
		lblCarros.setBounds(10, 254, 46, 14);
		add(lblCarros);
		
		JButton btnAdicionar = new JButton("Novo");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CarroDialog carroDialog = new CarroDialog(ClientePanel.this);
				carroDialog.show();
			}
		});
		btnAdicionar.setBounds(67, 250, 89, 23);
		add(btnAdicionar);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBounds(122, 472, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (ClientePanel.this.canSave()) {
					ClienteController controller = new ClienteController();
					try {
						
						TipoLogradouro tipo = new TipoLogradouro();
						tipo.setIdTipoLogradouro(map.get(tipoLogradouro.getSelectedItem()));
						Endereco endereco = new Endereco(tipo, logradouro.getText(), numero.getText(), complemento.getText(),
								bairro.getText(), cidade.getText(), estado.getText(), cep.getText());
						
						Cliente cliente = new Cliente(nome.getText(), cpf.getText(), telefone.getText(), endereco, ClientePanel.this.carros);
						
						for (Carro car : ClientePanel.this.carros) {
							car.setCliente(cliente);
						}
						
						controller.salvar(cliente);
						StaticMethods.showAlertMessage("Cliente salvo com sucesso");
					} catch (Exception e1) {
						e1.printStackTrace();
						StaticMethods.showAlertMessage(e1.getMessage());
					}
				}
			}
		});
		btnNewButton_1.setBounds(10, 472, 89, 23);
		add(btnNewButton_1);
		
		JLabel lblTipoLogradouro = new JLabel("Tipo Logradouro");
		lblTipoLogradouro.setBounds(10, 142, 89, 14);
		add(lblTipoLogradouro);
		
		TipoLogradouroDAO dao = new TipoLogradouroDAO();
		
		try {
			ArrayList<TipoLogradouro> lista = (ArrayList<TipoLogradouro>) dao.findAll();
			for (TipoLogradouro tipo : lista) {
				map.put(tipo.getTipo(), tipo.getIdTipoLogradouro());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		tipoLogradouro = new JComboBox(map.keySet().toArray());
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
		
		aModel = new DefaultTableModel();
		aModel.setColumnIdentifiers(columnNames);
		
		JTable table = new JTable();
		table.setModel(aModel);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(45, 298, 538, 145);
		add(scrollPane, BorderLayout.CENTER);
	}
	
	public void addCarroToCliente(Carro carro) {
		if (this.carros == null) this.carros = new ArrayList<Carro>();
		Object[] obj = new Object[4];
		obj[0] = carro.getMarca();
		obj[1] = carro.getModelo();
		obj[2] = carro.getAno();
		obj[3] = carro.getPlaca();
		aModel.addRow(obj);
		this.carros.add(carro);
	}
	
	private boolean canSave(){
		
		if (nome.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo nome deve ser preenchido");
			return false;
		}
		
		if (cpf.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo CPF deve ser preenchido");
			return false;
		}
		
		if (telefone.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo CPF deve ser preenchido");
			return false;
		}
		
		if (telefone.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo CPF deve ser preenchido");
			return false;
		}
		
		/*if (tipoLogradouro.getSelectedIndex() == 0){
			StaticMethods.showAlertMessage("O campo tipo logradouro deve ser preenchido");
			return false;
		}*/
		
		if (logradouro.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo logradouro deve ser preenchido");
			return false;
		}
		
		if (numero.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo n�mero deve ser preenchido");
			return false;
		}
		
		if (complemento.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo n�mero deve ser preenchido");
			return false;
		}
		
		if (bairro.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo bairro deve ser preenchido");
			return false;
		}
		
		if (cidade.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo n�mero deve ser preenchido");
			return false;
		}
		
		if (estado.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo estado deve ser preenchido");
			return false;
		}
		
		if (cep.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo n�mero deve ser preenchido");
			return false;
		}
		
		return true;
	}
}
