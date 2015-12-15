package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import br.ufrrj.projeto.oficinatoretto.model.Credito;
import br.ufrrj.projeto.oficinatoretto.model.NotaFiscalFacade;
import br.ufrrj.projeto.oficinatoretto.model.Parcela;
import br.ufrrj.projeto.oficinatoretto.model.Peca;
import br.ufrrj.projeto.oficinatoretto.model.Reparo;
import br.ufrrj.projeto.oficinatoretto.model.Servico;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class ServicoPanel extends JLayeredPane {
	
	private JCalendar data;
	
	private JLabel lblValor;

	private DefaultTableModel pecaTableModel = new DefaultTableModel();
	private DefaultTableModel reparoTableModel = new DefaultTableModel();
	private DefaultTableModel parcelaTableModel = new DefaultTableModel();
	
	private JTextField placa;
	
	private BigDecimal valor = new BigDecimal(0);
	
	private NotaFiscalFacade notaFiscalFacade = new NotaFiscalFacade();
	
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
		scrollTableReparo.setBounds(32, 182, 326, 112);
		
		add(scrollTableReparo);
		
		JLabel lblFornecedor = new JLabel("Reparos");
		lblFornecedor.setBounds(32, 157, 89, 14);
		add(lblFornecedor);
		
		placa = new JTextField();
		placa.setEditable(false);
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
		lblPeas.setBounds(386, 154, 89, 14);
		add(lblPeas);
		
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
		scrollPeca.setBounds(386, 179, 326, 112);
		add(scrollPeca);
		
		JButton btnFinalize = new JButton("Finalize");
		btnFinalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					notaFiscalFacade.salvar();
					StaticMethods.showAlertMessage("Nota fiscal salva com sucesso");
					NotaFiscalDialog dialog = new NotaFiscalDialog(notaFiscalFacade);
					dialog.show();
				} catch (Exception e) {
					e.printStackTrace();
					StaticMethods.showAlertMessage("Não foi possível salvar a nota fiscal");
				}			
			}
		});
		btnFinalize.setBounds(32, 472, 89, 23);
		add(btnFinalize);
		
		String[] columnParcelaNames = {"Vencimento", "Paga"};
		parcelaTableModel.setColumnIdentifiers(columnParcelaNames);
		JTable tableParcela = new JTable();
		tableParcela.setModel(parcelaTableModel);
		
		JScrollPane parcelasNaoPagas = new JScrollPane(tableParcela);
		parcelasNaoPagas.setBounds(32, 349, 326, 112);
		add(parcelasNaoPagas);
		
		JLabel lblParcelasNoPagas = new JLabel("Parcelas n\u00E3o pagas");
		lblParcelasNoPagas.setBounds(32, 324, 161, 14);
		add(lblParcelasNoPagas);
	}
	
	public void addServico(Servico serv) {
		valor = new BigDecimal(0);
		this.data.setDate(serv.getOrcamento().getData());
		this.placa.setText(serv.getOrcamento().getCarro().getPlaca());
		
		for (int i = 0; i < pecaTableModel.getRowCount(); i++) {
			pecaTableModel.removeRow(i);
		}
		for (Peca p : serv.getOrcamento().getPecas()) {
			addPeca(p);
			valor = valor.add(p.getValorVenda());
		}
		for (int i = 0; i < reparoTableModel.getRowCount(); i++) {
			reparoTableModel.removeRow(i);
		}
		for (Reparo r : serv.getOrcamento().getReparos()) {
			addReparo(r);
			valor = valor.add(r.getValor());
		}
		for (int i = 0; i < parcelaTableModel.getRowCount(); i++) {
			parcelaTableModel.removeRow(i);
		}
		if (serv.getPagamento() instanceof Credito) {
			for (Parcela p : ((Credito)serv.getPagamento()).getParcelas()) {
				addParcela(p);
			}
		}
		lblValor.setText("Valor: " + valor);
		
		notaFiscalFacade.registraNotaFiscal(serv);
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
	public void addParcela(Parcela parcela) {
		Object[] obj = new Object[4];
		obj[0] = parcela.getVencimento();
		obj[1] = parcela.getPaga();
		parcelaTableModel.addRow(obj);
	}
}
