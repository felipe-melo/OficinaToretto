package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.ufrrj.projeto.oficinatoretto.dao.TipoLogradouroDAO;
import br.ufrrj.projeto.oficinatoretto.model.Endereco;
import br.ufrrj.projeto.oficinatoretto.model.Fornecedor;
import br.ufrrj.projeto.oficinatoretto.model.FornecedorFacade;
import br.ufrrj.projeto.oficinatoretto.model.TipoLogradouro;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class FornecedorPanel extends JLayeredPane {
	
	private MaskFormatter mascara;
	private JFormattedTextField telefone;
	private JTextField nome;
	private JTextField responsavel;
	private JComboBox tipoLogradouro;
	private JTextField logradouro;
	private JTextField numero;
	private JTextField complemento;
	private JTextField bairro;
	private JTextField cidade;
	private JTextField estado;
	private JTextField cep;
	
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	private FornecedorFacade fornecedorFacade = new FornecedorFacade();
	
	public FornecedorPanel() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Telefone");
		lblNome.setBounds(10, 87, 46, 14);
		add(lblNome);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canSave()) {
					try {
						
						fornecedorFacade.registraDadosEndereco(map.get(tipoLogradouro.getSelectedItem()), logradouro.getText(), numero.getText(), complemento.getText(),
								bairro.getText(), cidade.getText(), estado.getText(), cep.getText());
						fornecedorFacade.registraDadosFornecedor(nome.getText(), telefone.getText(), responsavel.getText());
						fornecedorFacade.salvar();
						
						StaticMethods.showAlertMessage("Fornecedor salvo com sucesso");
					} catch (Exception e1) {
						e1.printStackTrace();
						StaticMethods.showAlertMessage(e1.getMessage());
					}
				}
			}
		});
		btnNewButton_1.setBounds(10, 472, 89, 23);
		add(btnNewButton_1);
		
		try {
			mascara = new MaskFormatter("(##) ####-####");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		telefone = new JFormattedTextField();
		telefone.setBounds(136, 81, 179, 20);
		mascara.install(telefone);
		add(telefone);
		telefone.setColumns(10);
		
		nome = new JTextField();
		nome.setBounds(136, 50, 179, 20);
		add(nome);
		nome.setColumns(10);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(10, 62, 46, 14);
		add(lblNome_1);
		
		JLabel lblResponsvel = new JLabel("Responsável");
		lblResponsvel.setBounds(10, 112, 67, 14);
		add(lblResponsvel);
		
		responsavel = new JTextField();
		responsavel.setBounds(136, 106, 179, 20);
		add(responsavel);
		responsavel.setColumns(10);
		
		JLabel lblTipoLogradouro = new JLabel("Tipo Logradouro");
		lblTipoLogradouro.setBounds(10, 140, 89, 14);
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
		tipoLogradouro.setBounds(136, 135, 179, 20);
		add(tipoLogradouro);
		
		JLabel lblLogradouro = new JLabel("Logradouro");
		lblLogradouro.setBounds(10, 168, 77, 14);
		add(lblLogradouro);
		
		logradouro = new JTextField();
		logradouro.setBounds(136, 165, 179, 20);
		add(logradouro);
		logradouro.setColumns(10);
		
		JLabel lblBairro = new JLabel("N\u00FAmero");
		lblBairro.setBounds(10, 193, 46, 14);
		add(lblBairro);
		
		numero = new JTextField();
		numero.setBounds(136, 190, 179, 20);
		add(numero);
		numero.setColumns(10);
		
		JLabel lblComplemento = new JLabel("Complemento");
		lblComplemento.setBounds(10, 218, 89, 14);
		add(lblComplemento);
		
		complemento = new JTextField();
		complemento.setBounds(136, 215, 179, 20);
		add(complemento);
		complemento.setColumns(10);
		
		JLabel lblBairro_1 = new JLabel("Bairro");
		lblBairro_1.setBounds(10, 243, 46, 14);
		add(lblBairro_1);
		
		bairro = new JTextField();
		bairro.setBounds(136, 240, 179, 20);
		add(bairro);
		bairro.setColumns(10);
		
		JLabel lblCidade = new JLabel("Cidade");
		lblCidade.setBounds(10, 268, 46, 14);
		add(lblCidade);
		
		cidade = new JTextField();
		cidade.setBounds(136, 265, 179, 20);
		add(cidade);
		cidade.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 293, 46, 14);
		add(lblEstado);
		
		estado = new JTextField();
		estado.setBounds(136, 296, 179, 20);
		add(estado);
		estado.setColumns(10);
		
		JLabel lblCep = new JLabel("CEP");
		lblCep.setBounds(10, 318, 46, 14);
		add(lblCep);
		
		cep = new JTextField();
		cep.setBounds(136, 327, 179, 20);
		add(cep);
		cep.setColumns(10);
	}
	
	private boolean canSave() {
		if (telefone.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo telefone deve ser preenchido");
			return false;
		}
		
		if (telefone.getText().length() < 14) {
			StaticMethods.showAlertMessage("Telefone inválido");
			return false;
		}
		return true;
	}
}
