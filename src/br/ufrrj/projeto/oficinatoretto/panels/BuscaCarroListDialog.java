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

import br.ufrrj.projeto.oficinatoretto.dao.CarroDAO;
import br.ufrrj.projeto.oficinatoretto.dao.OrcamentoDAO;
import br.ufrrj.projeto.oficinatoretto.model.Carro;
import br.ufrrj.projeto.oficinatoretto.model.Orcamento;

public class BuscaCarroListDialog extends JDialog {
	
	private JScrollPane scrollPane;
	private DefaultTableModel aModel;
	
	private String[] columnNames = {"Data", "Descrição"};
	
	private Carro carro ;
	private List<Orcamento> orcamento = new ArrayList<Orcamento>();
	private JTextField lblPlaca;

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
				pane.addServico(orcamento.get(table.getSelectedRow()));
				dispose();
			}
		});
		btnSelecionar.setBounds(21, 404, 89, 23);
		getContentPane().add(btnSelecionar);
		
		lblPlaca = new JTextField();
		lblPlaca.setBounds(155, 27, 152, 20);
		getContentPane().add(lblPlaca);
		lblPlaca.setColumns(10);
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(27, 30, 118, 14);
		getContentPane().add(lblPlaca);
		
		JButton buscar = new JButton("Buscar");
		buscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String placa = lblPlaca.getText();
				
				if (placa == null) {
					
				}else{
//					CarroDAO carroDao =  new CarroDAO();
//					carro = carroDao.findByPlaca(placa);
//					
					OrcamentoDAO orcDao = new OrcamentoDAO();
					orcamento = (ArrayList<Orcamento>) orcDao.searchOrcamento(null, placa);
					updateListOfOrcamentos();
				}
			}
		});
		buscar.setBounds(335, 26, 89, 23);
		getContentPane().add(buscar);
		
	}
	
	private void updateListOfOrcamentos(){
		
		for (int i = 0; i < aModel.getRowCount(); i++){
			aModel.removeRow(i);
		}
		
		for (Orcamento orcamento : orcamento) {
			addOrcamentos(orcamento);
		}
	}
	public void addOrcamentos(Orcamento orcamento) {
		Object[] obj = new Object[4];
		obj[0] = orcamento.getData();
		obj[1] = orcamento.getIdOrcamento();
		aModel.addRow(obj);
	}
}
