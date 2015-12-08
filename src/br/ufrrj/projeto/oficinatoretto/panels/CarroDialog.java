package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufrrj.projeto.oficinatoretto.model.Carro;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class CarroDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField marca;
	private JTextField modelo;
	private JTextField ano;
	private JTextField cor;
	private JTextField placa;

	/**
	 * Create the dialog.
	 */
	public CarroDialog(ClientePanel pane) {
		setBounds(100, 100, 447, 371);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Marca");
			lblNewLabel.setBounds(10, 54, 77, 14);
			contentPanel.add(lblNewLabel);
		}
		
		marca = new JTextField();
		marca.setBounds(93, 51, 160, 20);
		contentPanel.add(marca);
		marca.setColumns(10);
		
		JLabel lblModelo = new JLabel("Modelo");
		lblModelo.setBounds(10, 82, 77, 14);
		contentPanel.add(lblModelo);
		
		modelo = new JTextField();
		modelo.setColumns(10);
		modelo.setBounds(93, 79, 160, 20);
		contentPanel.add(modelo);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(10, 110, 77, 14);
		contentPanel.add(lblAno);
		
		ano = new JTextField();
		ano.setColumns(10);
		ano.setBounds(93, 107, 160, 20);
		contentPanel.add(ano);
		
		JLabel lblCor = new JLabel("Cor");
		lblCor.setBounds(10, 138, 77, 14);
		contentPanel.add(lblCor);
		
		cor = new JTextField();
		cor.setColumns(10);
		cor.setBounds(93, 135, 160, 20);
		contentPanel.add(cor);
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(10, 166, 77, 14);
		contentPanel.add(lblPlaca);
		
		placa = new JTextField();
		placa.setColumns(10);
		placa.setBounds(93, 163, 160, 20);
		contentPanel.add(placa);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Salvar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (canSave()) {
							Carro carro = new Carro(marca.getText(), modelo.getText(), new Integer(ano.getText()), cor.getText(), modelo.getText());
							pane.addCarroToCliente(carro);
							dispose();
						}
					}
				});
				okButton.setActionCommand("Salvar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	private boolean canSave() {
		if (modelo.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo modelo deve ser preenchido");
			return false;
		}
		
		if (marca.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo marca deve ser preenchido");
			return false;
		}
		
		if (ano.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo ano deve ser preenchido");
			return false;
		}
		
		if (cor.getText().equals("")) {
			StaticMethods.showAlertMessage("O cor ano deve ser preenchido");
			return false;
		}
		
		if (placa.getText().equals("")) {
			StaticMethods.showAlertMessage("A placa deve ser preenchido");
			return false;
		}
		return true;
	}
}
