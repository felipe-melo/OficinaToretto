package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import br.ufrrj.projeto.oficinatoretto.dao.PecaDAO;
import br.ufrrj.projeto.oficinatoretto.model.Peca;
import br.ufrrj.projeto.oficinatoretto.model.Reparo;
import br.ufrrj.projeto.oficinatoretto.model.Servico;

public class ServicoPanel extends JLayeredPane {
	
	private JCalendar data;
	
	private JLabel lblValor;

	private DefaultTableModel pecaTableModel = new DefaultTableModel();
	private DefaultTableModel reparoTableModel = new DefaultTableModel();
	
	private JTextField placa;
	
	private Map<String, Peca> mapPec = new HashMap<String, Peca>();
	
	private BigDecimal valor = new BigDecimal(0);
	
	public ServicoPanel() {
		setLayout(null);
		
		data = new JCalendar(Locale.getDefault());
		data.setBounds(185, 39, 179, 20);
		add(data);
		
		JLabel placaLabel = new JLabel("Placa do Carro");
		placaLabel.setBounds(59, 72, 89, 14);
		add(placaLabel);
		
		 
		String[] columnReparoNames = {"Reparo", "Valor"};
		reparoTableModel.setColumnIdentifiers(columnReparoNames);
		JTable tableReparo = new JTable();
		tableReparo.setModel(reparoTableModel);
		
		
		JScrollPane scrollTableReparo = new JScrollPane(tableReparo);
		scrollTableReparo.setBounds(32, 281, 332, 156);
		
		add(scrollTableReparo);
		
		JLabel lblFornecedor = new JLabel("Reparos");
		lblFornecedor.setBounds(32, 233, 89, 14);
		add(lblFornecedor);
		
		placa = new JTextField();
		placa.setBounds(185, 69, 179, 20);
		add(placa);
		placa.setColumns(10);
		
		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscaCarroListDialog buscaCarroDialog = new BuscaCarroListDialog(ServicoPanel.this);
				buscaCarroDialog.show();
			}
		});
		btnSearch.setBounds(366, 68, 78, 23);
		add(btnSearch);
		
		JLabel lblPeas = new JLabel("Pe\u00E7as");
		lblPeas.setBounds(386, 233, 89, 14);
		add(lblPeas);
		
		PecaDAO pecDao = new PecaDAO();
		
		try {
			ArrayList<Peca> lista = (ArrayList<Peca>) pecDao.findAll();
			for (Peca peca : lista) {
				mapPec.put(peca.getDescricao(), peca);
//				nAssociadoPeca.addElement(peca.getDescricao());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		lblValor = new JLabel("Valor:");
		lblValor.setBounds(59, 112, 116, 14);
		add(lblValor);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(59, 45, 89, 14);
		add(lblData);
		
		String[] columnPecaNames = {"Peça", "Valor"};
		pecaTableModel.setColumnIdentifiers(columnPecaNames);
		JTable tablePeca = new JTable();
		tablePeca.setModel(pecaTableModel);
		
		JScrollPane scrollPeca = new JScrollPane(tablePeca);
		scrollPeca.setBounds(386, 278, 332, 159);
		add(scrollPeca);
	}
	
	public void addServico(Servico serv) {
		valor = new BigDecimal(0);
		this.data.setDate(serv.getOrcamento().getData());
		for (Peca p : serv.getOrcamento().getPecas()) {
			addPeca(p);
			valor = valor.add(p.getValorVenda());
		}
		
		for (Reparo r : serv.getOrcamento().getReparos()) {
			addReparo(r);
			valor = valor.add(r.getValor());
		}
		lblValor.setText("Valor: " + valor);
	}
	
	public void addPeca(Peca peca) {
		Object[] obj = new Object[4];
		obj[0] = peca.getDescricao();
		obj[1] = peca.getValorVenda();
		pecaTableModel.addRow(obj);
	}
	public void addReparo(Reparo reparo) {
		Object[] obj = new Object[4];
		obj[0] = reparo.getDescricaoBreve();
		obj[1] = reparo.getValor();
		reparoTableModel.addRow(obj);
	}
	
}
