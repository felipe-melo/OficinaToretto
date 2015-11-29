package br.ufrrj.projeto.oficinatoretto.windows;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ufrrj.projeto.oficinatoretto.controller.UsuarioController;
import br.ufrrj.projeto.oficinatoretto.model.Usuario;
import br.ufrrj.projeto.oficinatoretto.util.StaticMethods;

public class LoginWindow extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField login;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow frame = new LoginWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Login");
		label.setBounds(60, 104, 125, 14);
		contentPane.add(label);
		
		login = new JTextField();
		login.setColumns(10);
		login.setBounds(60, 129, 201, 20);
		contentPane.add(login);
		
		JLabel label_1 = new JLabel("Senha");
		label_1.setBounds(60, 179, 125, 14);
		contentPane.add(label_1);
		
		password = new JPasswordField();
		password.setBounds(60, 204, 201, 20);
		contentPane.add(password);
		
		JButton button = new JButton("Logar");
		button.addActionListener(this);
		button.setBounds(60, 251, 88, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Sair");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button_1.setBounds(189, 251, 72, 23);
		contentPane.add(button_1);
	}
	
	private boolean canLogin() {
		if (this.login.getText().equals("") || this.password.getPassword().length == 0) {
			StaticMethods.showAlertMessage("Login ou senha inválidos");
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (this.canLogin()) {
			UsuarioController controller = new UsuarioController();
			try {
				String pass = new String (this.password.getPassword());
				Usuario usuario = controller.login(this.login.getText(), pass); 
				setVisible(false);
		        new MainWindow(usuario).setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
				StaticMethods.showAlertMessage(e.getMessage());
			}
		}
	}
}
