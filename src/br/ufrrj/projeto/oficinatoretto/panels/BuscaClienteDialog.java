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
import br.ufrrj.projeto.oficinatoretto.dao.OrcamentoDAO;
import br.ufrrj.projeto.oficinatoretto.model.Carro;
import br.ufrrj.projeto.oficinatoretto.model.Cliente;
import br.ufrrj.projeto.oficinatoretto.model.ClienteFacade;
import br.ufrrj.projeto.oficinatoretto.model.Orcamento;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class BuscaClienteDialog extends JDialog {
	
	private JTextField cpf;
	
	private JScrollPane scrollCliente;
	private DefaultTableModel aModel;

	/**
	 * Create the dialog.
	 */
	public BuscaClienteDialog(ClientePanel pane, ClienteFacade clienteFacade) {
		
		setBounds(100, 100, 421, 141);
		getContentPane().setLayout(null);
		
		cpf = new JTextField();
		cpf.setColumns(10);
		cpf.setBounds(120, 22, 132, 20);
		getContentPane().add(cpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(21, 25, 46, 14);
		getContentPane().add(lblCpf);
		
		JButton filtrar = new JButton("Filtrar");
		filtrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cpf.getText().equals("")) {
					StaticMethods.showAlertMessage("Por favor preencha o cpf do cliente");
				}
				else{
					clienteFacade.findCliente(cpf.getText());
					
					pane.preencherTela();
					dispose();
				}
			}
		});
		filtrar.setBounds(262, 52, 89, 23);
		getContentPane().add(filtrar);
	}

}
