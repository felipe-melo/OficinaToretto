package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import br.ufrrj.projeto.oficinatoretto.dao.CategoriaDAO;
import br.ufrrj.projeto.oficinatoretto.model.Categoria;
import br.ufrrj.projeto.oficinatoretto.model.CategoriaFacade;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class CategoriaPanel extends JLayeredPane {
	
	private JTextField nome;
	
	JComboBox superCategoria;
	
	CategoriaFacade categoriaFacade = new CategoriaFacade();
	
	private Map<String, Integer> map = new HashMap<String, Integer>();
	
	public CategoriaPanel() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Super Categoria");
		lblNome.setBounds(37, 107, 83, 14);
		add(lblNome);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canSave()) {
					try {
						categoriaFacade.registraCategoria(nome.getText(), map.get(superCategoria.getSelectedItem()));
						categoriaFacade.salvar();
						StaticMethods.showAlertMessage("Categoria salvo com sucesso");
					} catch (Exception e1) {
						e1.printStackTrace();
						StaticMethods.showAlertMessage("Erro ao salvar categoria");
					}
				}
			}
		});
		btnNewButton_1.setBounds(10, 472, 89, 23);
		add(btnNewButton_1);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(37, 82, 46, 14);
		add(lblNome_1);
		
		nome = new JTextField();
		nome.setBounds(169, 79, 179, 20);
		add(nome);
		nome.setColumns(10);
		
		CategoriaDAO dao = new CategoriaDAO();
		
		try {
			ArrayList<Categoria> lista = (ArrayList<Categoria>) dao.findAll();
			for (Categoria cat : lista) {
				map.put(cat.getNome(), cat.getIdCategoria());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		superCategoria = new JComboBox(map.keySet().toArray());
		superCategoria.setSelectedIndex(-1);
		superCategoria.setBounds(169, 107, 179, 20);
		add(superCategoria);
	}
	
	private boolean canSave() {
		
		if (nome.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo nome deve ser preenchido");
			return false;
		}
		return true;
	}
}
