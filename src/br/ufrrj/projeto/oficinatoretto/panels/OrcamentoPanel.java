package br.ufrrj.projeto.oficinatoretto.panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

import br.ufrrj.projeto.oficinatoretto.dao.ClienteDAO;
import br.ufrrj.projeto.oficinatoretto.dao.PecaDAO;
import br.ufrrj.projeto.oficinatoretto.model.Carro;
import br.ufrrj.projeto.oficinatoretto.model.Cliente;
import br.ufrrj.projeto.oficinatoretto.model.Orcamento;
import br.ufrrj.projeto.oficinatoretto.model.OrcamentoFacade;
import br.ufrrj.projeto.oficinatoretto.model.Peca;
import br.ufrrj.projeto.oficinatoretto.model.Reparo;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class OrcamentoPanel extends JLayeredPane {
	
	private JCalendar data;
	private JTextArea comentario;
	
	private JLabel lblValor;
	
	private DefaultListModel nAssociadoReparo;
	private DefaultListModel associadoReparo;
	
	private DefaultListModel nAssociadoPeca;
	private DefaultListModel associadoPeca;
	
	private JTextField cpf;
	private JTextField placa;
	
	private Map<String, Reparo> mapRep = new HashMap<String, Reparo>();
	private Map<String, Peca> mapPec = new HashMap<String, Peca>();
	
	private JButton btnBuscar;
	
	private JCheckBox aprovado;
	
	private BigDecimal valor = new BigDecimal(0);
	
	private OrcamentoFacade orcamentoFacade = new OrcamentoFacade();
	
	public OrcamentoPanel() {
		setLayout(null);
		
		JLabel lblNome = new JLabel("Coment\u00E1rio");
		lblNome.setBounds(59, 128, 116, 14);
		add(lblNome);
		
		JButton btnNewButton_1 = new JButton("Salvar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (canSave()) {
					try {
						
						List<Reparo> reparos = new ArrayList<Reparo>();
						
						for (int i = 0; i < associadoReparo.getSize(); i++) {
							reparos.add(mapRep.get(associadoReparo.getElementAt(i)));
						}
						
						List<Peca> pecas = new ArrayList<Peca>();
						
						for (int i = 0; i < associadoPeca.getSize(); i++) {
							pecas.add(mapPec.get(associadoPeca.getElementAt(i)));
						}
						
						orcamentoFacade.registraOrcamento(data.getDate(), comentario.getText(), aprovado.isSelected());
						
						orcamentoFacade.registraReparos(reparos);
						orcamentoFacade.registraPecas(pecas);
						
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
		
		JLabel lblTipoLogradouro = new JLabel("CPF");
		lblTipoLogradouro.setBounds(59, 72, 89, 14);
		add(lblTipoLogradouro);
		
		nAssociadoReparo = new DefaultListModel();
		associadoReparo = new DefaultListModel();
		
		JList listNAssociado = new JList();
		listNAssociado .setModel(nAssociadoReparo);
		
		JList listAssociado  = new JList();
		listAssociado.setModel(associadoReparo);
		
		try {
			ArrayList<Reparo> lista = (ArrayList<Reparo>) orcamentoFacade.recuperaReparos();
			for (Reparo repa : lista) {
				mapRep.put(repa.getDescricaoBreve(), repa);
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
				valor = valor.add(mapRep.get(listNAssociado.getSelectedValue()).getValor());
				associadoReparo.addElement(listNAssociado.getSelectedValue());
				nAssociadoReparo.removeElement(listNAssociado.getSelectedValue());
				lblValor.setText("Valor: " + valor);
			}
		});
		btnNewButton.setBounds(172, 322, 49, 23);
		add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("<<");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valor = valor.subtract(mapRep.get(listAssociado.getSelectedValue()).getValor());
				nAssociadoReparo.addElement(listAssociado.getSelectedValue());
				associadoReparo.removeElement(listAssociado.getSelectedValue());
				lblValor.setText("Valor: " + valor);
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
		comentario.setBounds(185, 128, 271, 69);
		add(comentario);
		
		cpf = new JTextField();
		cpf.setBounds(185, 69, 179, 20);
		add(cpf);
		cpf.setColumns(10);
		
		JButton btnSearch = new JButton("search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (cpf.getText().equals("")) {
					StaticMethods.showAlertMessage("Por favor preencha o cpf do cliente");
				}else{
					ClienteDAO dao = new ClienteDAO();
					Cliente cliente = dao.findByCpf(cpf.getText());
					if (cliente != null) {
						BuscaCarroDialog buscaCarroDialog = new BuscaCarroDialog(OrcamentoPanel.this, cliente);
						buscaCarroDialog.show();
					}else{
						StaticMethods.showAlertMessage("Cliente não encontrado");
					}
				}
			}
		});
		btnSearch.setBounds(366, 68, 78, 23);
		add(btnSearch);
		
		JLabel label = new JLabel("Associado");
		label.setBounds(602, 266, 116, 14);
		add(label);
		
		JLabel label_1 = new JLabel("N\u00E3o Associado");
		label_1.setBounds(386, 266, 116, 14);
		add(label_1);
		
		JLabel lblPeas = new JLabel("Pe\u00E7as");
		lblPeas.setBounds(386, 233, 89, 14);
		add(lblPeas);
		
		nAssociadoPeca = new DefaultListModel();
		associadoPeca = new DefaultListModel();
		
		JList listNAssociadoPeca = new JList();
		listNAssociadoPeca.setModel(nAssociadoPeca);
		
		JList listAssociadoPeca  = new JList();
		listAssociadoPeca.setModel(associadoPeca);
		
		JButton button_2 = new JButton("<<");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				valor = valor.subtract(mapPec.get(listAssociadoPeca.getSelectedValue()).getValorVenda());
				nAssociadoPeca.addElement(listAssociadoPeca.getSelectedValue());
				associadoPeca.removeElement(listAssociadoPeca.getSelectedValue());
				lblValor.setText("Valor: " + valor);
			}
		});
		button_2.setBounds(526, 356, 49, 23);
		add(button_2);
		
		PecaDAO pecDao = new PecaDAO();
		
		try {
			ArrayList<Peca> lista = (ArrayList<Peca>) pecDao.findAll();
			for (Peca peca : lista) {
				mapPec.put(peca.getDescricao(), peca);
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
				valor = valor.add(mapPec.get(listNAssociadoPeca.getSelectedValue()).getValorVenda());
				associadoPeca.addElement(listNAssociadoPeca.getSelectedValue());
				nAssociadoPeca.removeElement(listNAssociadoPeca.getSelectedValue());
				lblValor.setText("Valor: " + valor);
			}
		});
		button_1.setBounds(526, 322, 49, 23);
		add(button_1);
		
		JLabel lblPlaca = new JLabel("Placa");
		lblPlaca.setBounds(59, 100, 89, 14);
		add(lblPlaca);
		
		placa = new JTextField();
		placa.setEditable(false);
		placa.setColumns(10);
		placa.setBounds(185, 97, 179, 20);
		add(placa);
		
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BuscaOrcamentoDialog dialog = new BuscaOrcamentoDialog(OrcamentoPanel.this, orcamentoFacade);
				dialog.show();
			}
		});
		btnBuscar.setBounds(109, 472, 89, 23);
		add(btnBuscar);
		
		aprovado = new JCheckBox("Aprovar");
		aprovado.setBounds(185, 204, 97, 23);
		add(aprovado);
		
		lblValor = new JLabel("Valor:");
		lblValor.setBounds(602, 11, 116, 14);
		add(lblValor);
	}
	
	public void addCarro(Carro carro) {
		this.placa.setText(carro.getPlaca());
		orcamentoFacade.registraCarro(carro);
	}
	
	public void addOrcamento(Orcamento orcamento) {
		valor = new BigDecimal(0);
		Carro carro = orcamento.getCarro();
		this.cpf.setText(carro.getCliente().getCpf());
		this.placa.setText(carro.getPlaca());
		this.data.setDate(orcamento.getData());
		this.comentario.setText(orcamento.getComentario());
		
		for (Peca p : orcamento.getPecas()) {
			associadoPeca.addElement(p);
			nAssociadoPeca.removeElement(p.getDescricao());
			associadoPeca.addElement(p.getDescricao());
			valor = valor.add(p.getValorVenda());
		}
		
		for (Reparo r : orcamento.getReparos()) {
			associadoReparo.addElement(r.getDescricaoBreve());
			nAssociadoReparo.removeElement(r.getDescricaoBreve());
			valor = valor.add(r.getValor());
		}
		lblValor.setText("Valor: " + valor);
	}
	
	private boolean canSave() {
		if (this.placa.getText().equals("")) {
			StaticMethods.showAlertMessage("Nenhum carro selecionado");
			return false;
		}
		
		if (this.comentario.getText().equals("")) {
			StaticMethods.showAlertMessage("O campo comentário deve ser preenchido");
			return false;
		}
		return true;
	}
}
