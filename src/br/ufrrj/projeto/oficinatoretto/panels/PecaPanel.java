package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import br.ufrrj.projeto.oficinatoretto.controller.PecaController;
import br.ufrrj.projeto.oficinatoretto.dao.CategoriaDAO;
import br.ufrrj.projeto.oficinatoretto.dao.FabricanteDAO;
import br.ufrrj.projeto.oficinatoretto.dao.FornecedorDAO;
import br.ufrrj.projeto.oficinatoretto.model.Categoria;
import br.ufrrj.projeto.oficinatoretto.model.Fabricante;
import br.ufrrj.projeto.oficinatoretto.model.Fornecedor;
import br.ufrrj.projeto.oficinatoretto.model.Peca;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class PecaPanel extends JLayeredPane {
	
	private JFormattedTextField valorCompra;
	private JTextField descricao;
	private JTextField valorVenda;
	private JComboBox categoria;
	private JComboBox fabricante;
	private JTextField quantidade;
	
	private DefaultListModel nAssociado;
	private DefaultListModel associado;
	
	private Map<String, Integer> mapCat = new HashMap<String, Integer>();
	private Map<String, Integer> mapFab = new HashMap<String, Integer>();
	private Map<String, Integer> mapFor = new HashMap<String, Integer>();
	
	private Map<String, Integer> mapAssociados = new HashMap<String, Integer>();
	
	public PecaPanel() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Valor de Compra");
		lblNome.setBounds(59, 115, 116, 14);
		add(lblNome);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canSave()) {
					PecaController controller = new PecaController();
					try {
						
						Categoria cat = new Categoria();
						cat.setIdCategoria(mapCat.get(categoria.getSelectedItem()));
						
						Fabricante fab = new Fabricante();
						fab.setIdFabricante(mapFab.get(fabricante.getSelectedItem()));
						
						BigDecimal compra = new BigDecimal(valorCompra.getText());
						BigDecimal venda = new BigDecimal(valorVenda.getText());
						
						Peca peca = new Peca(descricao.getText(), compra, venda, new Integer(quantidade.getText()),
								cat, fab);
						
						controller.salvar(peca);
						StaticMethods.showAlertMessage("Peça salva com sucesso");
					} catch (Exception e1) {
						e1.printStackTrace();
						StaticMethods.showAlertMessage(e1.getMessage());
					}
				}
			}
		});
		btnNewButton_1.setBounds(10, 472, 89, 23);
		add(btnNewButton_1);
		
		
		valorCompra = new JFormattedTextField();
		valorCompra.setBounds(185, 112, 179, 20);
		add(valorCompra);
		valorCompra.setColumns(10);
		
		descricao = new JTextField();
		descricao.setBounds(185, 81, 179, 20);
		add(descricao);
		descricao.setColumns(10);
		
		JLabel lblNome_1 = new JLabel("Descri\u00E7\u00E3o");
		lblNome_1.setBounds(59, 84, 89, 14);
		add(lblNome_1);
		
		JLabel lblResponsvel = new JLabel("Valor de Venda");
		lblResponsvel.setBounds(59, 140, 120, 14);
		add(lblResponsvel);
		
		valorVenda = new JTextField();
		valorVenda.setBounds(185, 137, 179, 20);
		add(valorVenda);
		valorVenda.setColumns(10);
		
		JLabel lblTipoLogradouro = new JLabel("Categoria");
		lblTipoLogradouro.setBounds(59, 201, 89, 14);
		add(lblTipoLogradouro);
		
		CategoriaDAO dao = new CategoriaDAO();
		
		try {
			ArrayList<Categoria> lista = (ArrayList<Categoria>) dao.findAll();
			for (Categoria cat : lista) {
				mapCat.put(cat.getNome(), cat.getIdCategoria());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		categoria = new JComboBox(mapCat.keySet().toArray());
		categoria.setBounds(185, 196, 179, 20);
		add(categoria);
		
		JLabel lblLogradouro = new JLabel("Quantidade");
		lblLogradouro.setBounds(59, 171, 77, 14);
		add(lblLogradouro);
		
		quantidade = new JTextField();
		quantidade.setBounds(185, 168, 179, 20);
		add(quantidade);
		quantidade.setColumns(10);
		
		FabricanteDAO fabDao = new FabricanteDAO();
		
		try {
			ArrayList<Fabricante> lista = (ArrayList<Fabricante>) fabDao.findAll();
			for (Fabricante fab : lista) {
				mapFab.put(fab.getNome(), fab.getIdFabricante());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		fabricante = new JComboBox(mapFab.keySet().toArray());
		fabricante.setBounds(185, 227, 179, 20);
		add(fabricante);
		
		JLabel lblFabricante = new JLabel("Fabricante");
		lblFabricante.setBounds(59, 232, 89, 14);
		add(lblFabricante);
		
		FornecedorDAO forDao = new FornecedorDAO();
		
		nAssociado = new DefaultListModel();
		associado = new DefaultListModel();
		
		JList listNAssociado = new JList();
		listNAssociado .setModel(nAssociado);
		
		JList listAssociado  = new JList();
		listAssociado.setModel(associado);
		
		try {
			ArrayList<Fornecedor> lista = (ArrayList<Fornecedor>) forDao.findAll();
			for (Fornecedor forn : lista) {
				mapFor.put(forn.getNome(), forn.getIdFornecedor());
				nAssociado.addElement(forn.getNome());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JScrollPane scrollNAssociado = new JScrollPane(listNAssociado );
		scrollNAssociado.setBounds(59, 280, 116, 156);
		add(scrollNAssociado);
		
		JScrollPane scrollAssociado = new JScrollPane(listAssociado );
		scrollAssociado.setBounds(309, 280, 116, 156);
		add(scrollAssociado);
		
		JButton btnNewButton = new JButton(">>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("\n\n" + listAssociado.getSelectedValue().toString() + "\n\n");
			}
		});
		btnNewButton.setBounds(199, 321, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("<<");
		btnNewButton_2.setBounds(199, 355, 89, 23);
		add(btnNewButton_2);
	}
	
	private boolean canSave() {
		if (descricao.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo descrição deve ser preenchido");
			return false;
		}
		
		if (valorCompra.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo valor compra deve ser preenchido");
			return false;
		}
		
		if (valorVenda.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo valor venda deve ser preenchido");
			return false;
		}
		
		if (quantidade.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo quantidade deve ser preenchido");
			return false;
		}
		return true;
	}
}
