package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import controladores.CtrlLogin;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;

public class VistaLogin {

	private JFrame frmLogin;
	private JTextField nombreUsuario;
	private JTextField passwordUsuario;
	JButton iniciarSesion = new JButton("Iniciar sesi\u00F3n");
	JLabel errorMSG = new JLabel("Usuario o contrase\u00F1a incorrectos");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaLogin window = new VistaLogin();
					CtrlLogin ctrl = new CtrlLogin(window);
					window.controladorLogin(ctrl);
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
		frmLogin.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frmLogin = new JFrame();
		frmLogin.setTitle("LOGIN");
		frmLogin.setBounds(100, 100, 500, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		nombreUsuario = new JTextField();
		nombreUsuario.setBounds(144, 55, 279, 32);
		frmLogin.getContentPane().add(nombreUsuario);
		nombreUsuario.setColumns(10);
		
		passwordUsuario = new JTextField();
		passwordUsuario.setColumns(10);
		passwordUsuario.setBounds(144, 121, 279, 32);
		frmLogin.getContentPane().add(passwordUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsuario.setBounds(51, 64, 83, 14);
		frmLogin.getContentPane().add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contrase\u00F1a");
		lblContrasea.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblContrasea.setBounds(51, 128, 83, 14);
		frmLogin.getContentPane().add(lblContrasea);
		
		
		iniciarSesion.setBounds(304, 184, 119, 32);
		frmLogin.getContentPane().add(iniciarSesion);
		
		
		errorMSG.setForeground(Color.RED);
		errorMSG.setBounds(86, 193, 208, 14);
		frmLogin.getContentPane().add(errorMSG);
		errorMSG.setVisible(false);
	}
	public void controladorLogin(ActionListener controlador) {
		
		iniciarSesion.addActionListener(controlador);
		iniciarSesion.setActionCommand("INICIARSESION");				

	}
	
	public String getNombreUsuario() {
		return nombreUsuario.getText();
	}
	public String getPassword() {
		return passwordUsuario.getText();
	}
	public void displayErrorMsg() {
		errorMSG.setVisible(true);
	}
	public void loginCorrecto() {
		nombreUsuario.setText("login correcto");
	}
	public void cerrarVista() {
		frmLogin.dispose();
	}
}
