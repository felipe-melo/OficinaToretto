package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.ufrrj.projeto.oficinatoretto.model.FabricanteFacade;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class FabricantePanel extends JLayeredPane {
	
	private MaskFormatter mascara;
	private JFormattedTextField telefone;
	private JTextField nome;
	
	FabricanteFacade fabricanteFacade = new FabricanteFacade(); 
	
	public FabricantePanel() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Telefone");
		lblNome.setBounds(10, 87, 46, 14);
		add(lblNome);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canSave()) {
					try {
						fabricanteFacade.regristraFabricante(nome.getText(), telefone.getText());
						fabricanteFacade.salvar();
						StaticMethods.showAlertMessage("Fabricante salvo com sucesso");
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
		telefone.setBounds(86, 84, 179, 20);
		mascara.install(telefone);
		add(telefone);
		telefone.setColumns(10);
		
		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setBounds(10, 62, 46, 14);
		add(lblNome_1);
		
		nome = new JTextField();
		nome.setBounds(86, 59, 179, 20);
		add(nome);
		nome.setColumns(10);
	}
	
	private boolean canSave() {
		if (telefone.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo telefone deve ser preenchido");
			return false;
		}
		
		if (nome.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo nome deve ser preenchido");
			return false;
		}
		
		if (telefone.getText().length() < 14) {
			StaticMethods.showAlertMessage("Telefone inv�lido");
			return false;
		}
		return true;
	}
}
