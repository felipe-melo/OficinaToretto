package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufrrj.projeto.oficinatoretto.model.OrcamentoFacade;
import br.ufrrj.projeto.oficinatoretto.util.Parcelas;
import br.ufrrj.projeto.oficinatoretto.util.QuantGasolina;
import br.ufrrj.projeto.oficinatoretto.util.TipoPagamento;

public class ServicoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField quilometragem;
	private JTextField valorPago;
	
	private HashMap<String, QuantGasolina> mapQuantGasolina = new HashMap<String, QuantGasolina>();
	private HashMap<String, TipoPagamento> mapTipoPagamento = new HashMap<String, TipoPagamento>();
	private HashMap<String, String> mapParcelas = new HashMap<String, String>();

	/**
	 * Create the dialog.
	 */
	public ServicoDialog(OrcamentoPanel pane, OrcamentoFacade orcamentoFacade) {
		setBounds(100, 100, 447, 371);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Quilometragem");
			lblNewLabel.setBounds(10, 51, 154, 14);
			contentPanel.add(lblNewLabel);
		}
		
		quilometragem = new JTextField();
		quilometragem.setBounds(174, 48, 160, 20);
		contentPanel.add(quilometragem);
		quilometragem.setColumns(10);
		
		JLabel lblModelo = new JLabel("Quantidade Gasolina");
		lblModelo.setBounds(10, 82, 154, 14);
		contentPanel.add(lblModelo);
		
		JLabel lblAno = new JLabel("Tipo");
		lblAno.setBounds(10, 110, 154, 14);
		contentPanel.add(lblAno);
		
		JLabel lblParcelas = new JLabel("Parcelas");
		lblParcelas.setBounds(10, 141, 154, 14);
		contentPanel.add(lblParcelas);
		
		mapQuantGasolina.put("cheio", QuantGasolina.cheio);
		mapQuantGasolina.put("1/4", QuantGasolina.quarto);
		mapQuantGasolina.put("1/2", QuantGasolina.metade);
		mapQuantGasolina.put("reserva", QuantGasolina.reserva);
		mapQuantGasolina.put("1/3", QuantGasolina.terco);
		
		JComboBox quantGasolina = new JComboBox(mapQuantGasolina.keySet().toArray());
		quantGasolina.setBounds(174, 79, 160, 20);
		contentPanel.add(quantGasolina);
		
		mapParcelas.put("1", "1");
		mapParcelas.put("2", "2");
		mapParcelas.put("3", "3");
		mapParcelas.put("4", "4");
		
		JComboBox parcelas = new JComboBox(mapParcelas.keySet().toArray());
		parcelas.setEnabled(false);
		parcelas.setBounds(174, 138, 160, 20);
		contentPanel.add(parcelas);
		
		JLabel lblValorPago = new JLabel("Valor pago");
		lblValorPago.setBounds(10, 170, 154, 14);
		contentPanel.add(lblValorPago);
		
		valorPago = new JTextField();
		valorPago.setBounds(174, 167, 160, 20);
		contentPanel.add(valorPago);
		valorPago.setColumns(10);
		
		mapTipoPagamento.put("dinheiro", TipoPagamento.dinheiro);
		mapTipoPagamento.put("debito", TipoPagamento.debito);
		mapTipoPagamento.put("crédito", TipoPagamento.credito);
		
		JComboBox tipo = new JComboBox(mapTipoPagamento.keySet().toArray());
		tipo.setBounds(174, 107, 160, 20);
		contentPanel.add(tipo);
		
		tipo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (mapTipoPagamento.get(tipo.getSelectedItem())) {
				
				case dinheiro:
					valorPago.setEditable(true);
					parcelas.setEnabled(false);
					break;
					
				case debito:
					valorPago.setEditable(false);
					parcelas.setEnabled(false);
					break;
					
				case credito:
					valorPago.setEditable(false);
					parcelas.setEnabled(true);
					break;
				}
			}
		});
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton okButton = new JButton("Salvar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (canSave()) {
					orcamentoFacade.registrarServico(quilometragem.getText(), mapQuantGasolina.get(quantGasolina.getSelectedItem()));
					switch (mapTipoPagamento.get(tipo.getSelectedItem())) {
					
					case dinheiro:
						orcamentoFacade.registraDinheiro(valorPago.getText());
						break;
						
					case debito:
						orcamentoFacade.registraDebito();
						break;
						
					case credito:
						orcamentoFacade.registraCredito(mapParcelas.get(parcelas.getSelectedItem()));
						break;
					}
					pane.addServico();
					dispose();
				}
			}
		});
		okButton.setActionCommand("Salvar");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
	
	private boolean canSave() {
		return true;
	}
}
