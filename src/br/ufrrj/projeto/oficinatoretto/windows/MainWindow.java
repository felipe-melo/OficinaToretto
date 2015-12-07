package br.ufrrj.projeto.oficinatoretto.windows;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import br.ufrrj.projeto.oficinatoretto.model.Usuario;
import br.ufrrj.projeto.oficinatoretto.panels.FabricantePanel;
import br.ufrrj.projeto.oficinatoretto.panels.FornecedorPanel;
import br.ufrrj.projeto.oficinatoretto.panels.ClientePanel;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public MainWindow(Usuario usuario) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 764, 539);
		contentPane.add(tabbedPane);
		
		ClientePanel clientePanel = new ClientePanel();
		tabbedPane.addTab("Cliente", null, clientePanel, null);
		
		FabricantePanel fabricantePanel = new FabricantePanel();
		tabbedPane.addTab("Fabricante", null, fabricantePanel, null);
		
		FornecedorPanel fornecedorPanel = new FornecedorPanel();
		tabbedPane.addTab("Fornecedor", null, fornecedorPanel, null);
	}
}
