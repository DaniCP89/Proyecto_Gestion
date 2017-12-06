package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;

public class VistaLogin {

	private JFrame frmLogin;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin window = new VistaLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 500, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(144, 55, 279, 32);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(144, 121, 279, 32);
		frmLogin.getContentPane().add(textField_1);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(51, 64, 83, 14);
		frmLogin.getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setBounds(51, 128, 83, 14);
		frmLogin.getContentPane().add(lblContrasea);
		
		JButton btnNewButton = new JButton("Iniciar sesi\u00F3n");
		btnNewButton.setBounds(304, 184, 119, 32);
		frmLogin.getContentPane().add(btnNewButton);
		
		JLabel lblUsuarioOContrasea = new JLabel("Usuario o contrase\u00F1a incorrectos");
		lblUsuarioOContrasea.setForeground(Color.RED);
		lblUsuarioOContrasea.setBounds(86, 193, 208, 14);
		frmLogin.getContentPane().add(lblUsuarioOContrasea);
	}
}
