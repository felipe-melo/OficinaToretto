package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.ufrrj.projeto.oficinatoretto.model.Orcamento;
import br.ufrrj.projeto.oficinatoretto.model.OrcamentoFacade;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class BuscaOrcamentoDialog extends JDialog {

	private JTextField cpf;
	private JTextField placa;
	
	private JScrollPane scrollOrcamento;
	private DefaultTableModel aModel;
	
	private String[] columnNames = {"Data", "CPF", "Placa", "Marca"};
	
	/**
	 * Create the dialog.
	 */
	public BuscaOrcamentoDialog(OrcamentoPanel pane, OrcamentoFacade orcamentoFacade) {
		
		setBounds(100, 100, 589, 341);
		getContentPane().setLayout(null);
		
		cpf = new JTextField();
		cpf.setColumns(10);
		cpf.setBounds(120, 22, 132, 20);
		getContentPane().add(cpf);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(21, 25, 46, 14);
		getContentPane().add(lblCpf);
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(21, 56, 46, 14);
		getContentPane().add(lblPlaca);
		
		placa = new JTextField();
		placa.setColumns(10);
		placa.setBounds(120, 53, 132, 20);
		getContentPane().add(placa);
		
		JLabel lblOrcamentos = new JLabel("Or\u00E7amentos");
		lblOrcamentos.setBounds(21, 84, 108, 14);
		getContentPane().add(lblOrcamentos);
		
		aModel = new DefaultTableModel();
		aModel.setColumnIdentifiers(columnNames);
		
		JTable table = new JTable();
		table.setModel(aModel);
		
		scrollOrcamento = new JScrollPane(table);
		scrollOrcamento.setBounds(21, 109, 538, 145);
		getContentPane().add(scrollOrcamento);
		
		JButton btnSelecionar = new JButton("Selecionar");
		btnSelecionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table.getSelectedRow() < 0){
					StaticMethods.showAlertMessage("Selecione algum orçamento");
				}else{
					Orcamento orc = orcamentoFacade.setOrcamento(table.getSelectedRow());
					pane.addOrcamento(orc);
					dispose();
				}
			}
		});
		btnSelecionar.setBounds(21, 265, 89, 23);
		getContentPane().add(btnSelecionar);
		
		JButton filtrar = new JButton("Filtrar");
		filtrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				for (int i = 0; i < aModel.getRowCount(); i++){
					aModel.removeRow(i);
				}
				
				List<Orcamento> lista = orcamentoFacade.searchOrcamento(cpf.getText(), placa.getText());
				
				for (Orcamento o: lista) {
					addOrcamento(o);
				}
			}
		});
		filtrar.setBounds(262, 52, 89, 23);
		getContentPane().add(filtrar);
	}
	
	public void addOrcamento(Orcamento orcamento) {
		Object[] obj = new Object[4];
		obj[0] = orcamento.getData();
		obj[1] = orcamento.getCarro().getCliente().getCpf();
		obj[2] = orcamento.getCarro().getPlaca();
		obj[3] = orcamento.getCarro().getModelo();
		aModel.addRow(obj);
	}
}
