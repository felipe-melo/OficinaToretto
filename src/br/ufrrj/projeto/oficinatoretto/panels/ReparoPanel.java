package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

import br.ufrrj.projeto.oficinatoretto.controller.ReparoController;
import br.ufrrj.projeto.oficinatoretto.model.Reparo;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class ReparoPanel extends JLayeredPane {
	
	private JTextField descricaoBreve;
	private JTextField descricaoDetalhada;
	private JTextField valor;
	private JTextField tempoExecucao;
	
	public ReparoPanel() {
		setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canSave()) {
					ReparoController controller = new ReparoController();
					try {
						
						BigDecimal valorBig = new BigDecimal(valor.getText());
						Reparo reparo = new Reparo(descricaoBreve.getText(), descricaoDetalhada.getText(), valorBig, new Integer(tempoExecucao.getText()));
						
						controller.salvar(reparo);
						StaticMethods.showAlertMessage("Reparo salvo com sucesso");
					} catch (Exception e1) {
						e1.printStackTrace();
						StaticMethods.showAlertMessage(e1.getMessage());
					}
				}
			}
		});
		btnNewButton_1.setBounds(10, 472, 89, 23);
		add(btnNewButton_1);
		
		descricaoBreve = new JTextField();
		descricaoBreve.setBounds(136, 59, 179, 20);
		add(descricaoBreve);
		descricaoBreve.setColumns(10);
		
		JLabel lblNome_1 = new JLabel("Descrição Breve");
		lblNome_1.setBounds(10, 62, 116, 14);
		add(lblNome_1);
		
		JLabel lblResponsvel = new JLabel("Descrição Detalhada");
		lblResponsvel.setBounds(10, 96, 116, 14);
		add(lblResponsvel);
		
		descricaoDetalhada = new JTextField();
		descricaoDetalhada.setBounds(136, 90, 179, 20);
		add(descricaoDetalhada);
		descricaoDetalhada.setColumns(10);
		
		JLabel lblTipoLogradouro = new JLabel("Valor");
		lblTipoLogradouro.setBounds(10, 124, 89, 14);
		add(lblTipoLogradouro);
		
		JLabel lblLogradouro = new JLabel("Tempo de Execução");
		lblLogradouro.setBounds(10, 152, 116, 14);
		add(lblLogradouro);
		
		valor = new JTextField();
		valor.setBounds(136, 121, 179, 20);
		add(valor);
		valor.setColumns(10);
		
		tempoExecucao = new JTextField();
		tempoExecucao.setBounds(136, 149, 179, 20);
		add(tempoExecucao);
		tempoExecucao.setColumns(10);
	}
	
	private boolean canSave() {
		
		return true;
	}
}
