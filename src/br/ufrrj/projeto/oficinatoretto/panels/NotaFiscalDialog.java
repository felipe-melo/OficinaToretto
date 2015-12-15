package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import br.ufrrj.projeto.oficinatoretto.dao.PecaDAO;
import br.ufrrj.projeto.oficinatoretto.model.NotaFiscalFacade;
import br.ufrrj.projeto.oficinatoretto.model.Peca;
import br.ufrrj.projeto.oficinatoretto.model.Reparo;
import br.ufrrj.projeto.oficinatoretto.model.Servico;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class NotaFiscalDialog extends JDialog {
	
	private JCalendar data;
	
	private JLabel lblValor;

	private DefaultTableModel pecaTableModel = new DefaultTableModel();
	private DefaultTableModel reparoTableModel = new DefaultTableModel();
	
	private Map<String, Peca> mapPec = new HashMap<String, Peca>();
	
	private BigDecimal valor = new BigDecimal(0);
	
	public NotaFiscalDialog(NotaFiscalFacade notaFiscalFacade) {
		getContentPane().setLayout(null);
		
		data = new JCalendar(Locale.getDefault());
		data.setBounds(185, 39, 179, 20);
		getContentPane().add(data);
		
		 
		String[] columnReparoNames = {"Reparo", "Valor"};
		reparoTableModel.setColumnIdentifiers(columnReparoNames);
		JTable tableReparo = new JTable();
		tableReparo.setModel(reparoTableModel);
		
		for (Reparo r : notaFiscalFacade.getNotaFiscal().getServico().getOrcamento().getReparos()) {
			this.addReparo(r);
		}
		
		JScrollPane scrollTableReparo = new JScrollPane(tableReparo);
		scrollTableReparo.setBounds(32, 195, 332, 156);
		
		getContentPane().add(scrollTableReparo);
		
		JLabel lblFornecedor = new JLabel("Reparos");
		lblFornecedor.setBounds(32, 170, 89, 14);
		getContentPane().add(lblFornecedor);
		
		JLabel lblPeas = new JLabel("Pe\u00E7as");
		lblPeas.setBounds(386, 170, 89, 14);
		getContentPane().add(lblPeas);
		
		PecaDAO pecDao = new PecaDAO();
		
		try {
			ArrayList<Peca> lista = (ArrayList<Peca>) pecDao.findAll();
			for (Peca peca : lista) {
				mapPec.put(peca.getDescricao(), peca);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		lblValor = new JLabel("Valor: ");
		lblValor.setBounds(59, 70, 116, 14);
		lblValor.setText("Valor: " + notaFiscalFacade.getNotaFiscal().getServico().getOrcamento().getValor().toString());
		getContentPane().add(lblValor);
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(59, 45, 89, 14);
		getContentPane().add(lblData);
		
		String[] columnPecaNames = {"Peça", "Valor"};
		pecaTableModel.setColumnIdentifiers(columnPecaNames);
		JTable tablePeca = new JTable();
		tablePeca.setModel(pecaTableModel);
		
		JScrollPane scrollPeca = new JScrollPane(tablePeca);
		scrollPeca.setBounds(386, 192, 332, 159);
		getContentPane().add(scrollPeca);
		
		for (Peca p : notaFiscalFacade.getNotaFiscal().getServico().getOrcamento().getPecas()) {
			this.addPeca(p);
		}
		
		JLabel lblNmeroDaNota = new JLabel("N\u00FAmero do Servi\u00E7o");
		lblNmeroDaNota.setBounds(59, 95, 116, 14);
		getContentPane().add(lblNmeroDaNota);
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
