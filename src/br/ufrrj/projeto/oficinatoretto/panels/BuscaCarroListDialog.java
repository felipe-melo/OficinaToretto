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

import br.ufrrj.projeto.oficinatoretto.model.Carro;
import br.ufrrj.projeto.oficinatoretto.model.Servico;
import br.ufrrj.projeto.oficinatoretto.model.ServicoFacade;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class BuscaCarroListDialog extends JDialog {
	
	private JScrollPane scrollPane;
	private DefaultTableModel aModel;
	
	private String[] columnNames = {"Data", "Descrição"};
	
	private Carro carro ;
	private List<Servico> servicos = new ArrayList<Servico>();
	private JTextField placa;
	
	private ServicoFacade servicoFacade = new ServicoFacade();

	/**
	 * Create the dialog.
	 */
	public BuscaCarroListDialog(ServicoPanel pane) {
		
		setBounds(100, 100, 632, 488);
		getContentPane().setLayout(null);
		
		JLabel lblServicos = new JLabel("Servi\u00E7os");
		lblServicos.setBounds(21, 146, 108, 14);
		getContentPane().add(lblServicos);
		
		aModel = new DefaultTableModel();
		aModel.setColumnIdentifiers(columnNames);
		
		JTable table = new JTable();
		table.setModel(aModel);
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(21, 171, 538, 224);
		getContentPane().add(scrollPane);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pane.addServico(servicos.get(table.getSelectedRow()));
				dispose();
			}
		});
		btnSelecionar.setBounds(21, 404, 89, 23);
		getContentPane().add(btnSelecionar);
		
		placa = new JTextField();
		placa.setBounds(155, 27, 152, 20);
		getContentPane().add(placa);
		placa.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(27, 30, 118, 14);
		getContentPane().add(lblPlaca);
		
		JButton buscar = new JButton("Buscar");
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (placa.getText().equals("")) {
					StaticMethods.showAlertMessage("Por favor preencha a placa");
				}else{
					servicos = servicoFacade.listaServicos(placa.getText());
					updateListOfServicos();
				}
			}
		});
		buscar.setBounds(335, 26, 89, 23);
		getContentPane().add(buscar);
		
	}
	
	private void updateListOfServicos() {
		
		for (int i = 0; i < aModel.getRowCount(); i++) {
			aModel.removeRow(i);
		}
		
		for (Servico serv: servicos) {
			addOrcamentos(serv);
		}
	}
	
	public void addOrcamentos(Servico serv) {
		Object[] obj = new Object[2];
		obj[0] = serv.getOrcamento().getData();
		obj[1] = serv.getOrcamento().getComentario();
		aModel.addRow(obj);
	}
}
