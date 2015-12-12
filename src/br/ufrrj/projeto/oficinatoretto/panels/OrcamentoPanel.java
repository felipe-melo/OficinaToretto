package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import br.ufrrj.projeto.oficinatoretto.controller.OrcamentoController;
import br.ufrrj.projeto.oficinatoretto.dao.PecaDAO;
import br.ufrrj.projeto.oficinatoretto.dao.ReparoDAO;
import br.ufrrj.projeto.oficinatoretto.model.Carro;
import br.ufrrj.projeto.oficinatoretto.model.Orcamento;
import br.ufrrj.projeto.oficinatoretto.model.Peca;
import br.ufrrj.projeto.oficinatoretto.model.Reparo;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class OrcamentoPanel extends JLayeredPane {
	
	private JCalendar data;
	private JTextArea comentario;
	
	private DefaultListModel nAssociadoReparo;
	private DefaultListModel associadoReparo;
	
	private DefaultListModel nAssociadoPeca;
	private DefaultListModel associadoPeca;
	
	private JTextField carro;
	
	private Map<String, Integer> mapRep = new HashMap<String, Integer>();
	private Map<String, Integer> mapPec = new HashMap<String, Integer>();
	
	public OrcamentoPanel() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Coment\u00E1rio");
		lblNome.setBounds(59, 100, 116, 14);
		add(lblNome);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canSave()) {
					OrcamentoController controller = new OrcamentoController();
					try {
						
						List<Reparo> reparos = new ArrayList<Reparo>();
						
						for (int i = 0; i < associadoReparo.getSize(); i++) {
							Reparo r = new Reparo();
							r.setIdReparo(mapRep.get(associadoReparo.getElementAt(i)));
							reparos.add(r);
						}
						Carro carro = new Carro();
						Orcamento orcamento = new Orcamento(data.getDate(), carro, comentario.getText(), false, reparos);
						controller.salvar(orcamento);
						StaticMethods.showAlertMessage("Orçamento salva com sucesso");
					} catch (Exception e1) {
						e1.printStackTrace();
						StaticMethods.showAlertMessage(e1.getMessage());
					}
				}
			}
		});
		btnNewButton_1.setBounds(10, 472, 89, 23);
		add(btnNewButton_1);
		
		data = new JCalendar(Locale.getDefault());
		data.setBounds(185, 39, 179, 20);
		add(data);
		
		JLabel lblNome_1 = new JLabel("Data");
		lblNome_1.setBounds(59, 42, 89, 14);
		add(lblNome_1);
		
		JLabel lblTipoLogradouro = new JLabel("Carro");
		lblTipoLogradouro.setBounds(59, 72, 89, 14);
		add(lblTipoLogradouro);
		
		nAssociadoReparo = new DefaultListModel();
		associadoReparo = new DefaultListModel();
		
		JList listNAssociado = new JList();
		listNAssociado .setModel(nAssociadoReparo);
		
		JList listAssociado  = new JList();
		listAssociado.setModel(associadoReparo);
		
		ReparoDAO repDao = new ReparoDAO();
		
		try {
			ArrayList<Reparo> lista = (ArrayList<Reparo>) repDao.findAll();
			for (Reparo repa : lista) {
				mapRep.put(repa.getDescricaoBreve(), repa.getIdReparo());
				nAssociadoReparo.addElement(repa.getDescricaoBreve());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JScrollPane scrollNAssociado = new JScrollPane(listNAssociado );
		scrollNAssociado.setBounds(32, 281, 116, 156);
		add(scrollNAssociado);
		
		JScrollPane scrollAssociado = new JScrollPane(listAssociado );
		scrollAssociado.setBounds(248, 281, 116, 156);
		add(scrollAssociado);
		
		JButton btnNewButton = new JButton(">>");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				associadoReparo.addElement(listNAssociado.getSelectedValue());
				nAssociadoReparo.removeElement(listNAssociado.getSelectedValue());
			}
		});
		btnNewButton.setBounds(172, 322, 49, 23);
		add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("<<");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nAssociadoReparo.addElement(listAssociado.getSelectedValue());
				associadoReparo.removeElement(listAssociado.getSelectedValue());
			}
		});
		btnNewButton_2.setBounds(172, 356, 49, 23);
		add(btnNewButton_2);
		
		JLabel lblFornecedor = new JLabel("Reparos");
		lblFornecedor.setBounds(32, 233, 89, 14);
		add(lblFornecedor);
		
		JLabel lblNoAssociado = new JLabel("N\u00E3o Associado");
		lblNoAssociado.setBounds(32, 266, 116, 14);
		add(lblNoAssociado);
		
		JLabel lblAssociado = new JLabel("Associado");
		lblAssociado.setBounds(248, 266, 116, 14);
		add(lblAssociado);
		
		comentario = new JTextArea();
		comentario.setBounds(185, 100, 270, 117);
		add(comentario);
		
		carro = new JTextField();
		carro.setBounds(185, 69, 179, 20);
		add(carro);
		carro.setColumns(10);
		
		JButton button = new JButton("+");
		button.setBounds(366, 68, 41, 23);
		add(button);
		
		JLabel label = new JLabel("Associado");
		label.setBounds(602, 266, 116, 14);
		add(label);
		
		JLabel label_1 = new JLabel("N\u00E3o Associado");
		label_1.setBounds(386, 266, 116, 14);
		add(label_1);
		
		JLabel lblPeas = new JLabel("Pe\u00E7as");
		lblPeas.setBounds(386, 233, 89, 14);
		add(lblPeas);
		
		JButton button_2 = new JButton("<<");
		button_2.setBounds(526, 356, 49, 23);
		add(button_2);
		
		nAssociadoPeca = new DefaultListModel();
		associadoPeca = new DefaultListModel();
		
		JList listNAssociadoPeca = new JList();
		listNAssociado .setModel(nAssociadoPeca);
		
		JList listAssociadoPeca  = new JList();
		listAssociado.setModel(associadoPeca);
		
		PecaDAO pecDao = new PecaDAO();
		
		try {
			ArrayList<Peca> lista = (ArrayList<Peca>) pecDao.findAll();
			for (Peca peca : lista) {
				mapPec.put(peca.getDescricao(), peca.getIdPeca());
				nAssociadoPeca.addElement(peca.getDescricao());
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		JScrollPane scrollNAssociadoPeca = new JScrollPane(listNAssociadoPeca);
		scrollNAssociadoPeca.setBounds(386, 281, 116, 156);
		add(scrollNAssociadoPeca);
		
		JScrollPane scrollAssociadoPeca = new JScrollPane(listAssociadoPeca);
		scrollAssociadoPeca.setBounds(602, 281, 116, 156);
		add(scrollAssociadoPeca);
		
		JButton button_1 = new JButton(">>");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				associadoPeca.addElement(listNAssociadoPeca.getSelectedValue());
				nAssociadoPeca.removeElement(listNAssociadoPeca.getSelectedValue());
			}
		});
		button_1.setBounds(526, 322, 49, 23);
		add(button_1);
	}
	
	private boolean canSave() {
		
		return true;
	}
}
