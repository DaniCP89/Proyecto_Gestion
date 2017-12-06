package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

public class VistaAplicacion {

	private JFrame frmLibros;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAplicacion window = new VistaAplicacion();
					window.frmLibros.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VistaAplicacion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLibros = new JFrame();
		frmLibros.setTitle("Libros");
		frmLibros.setBounds(100, 100, 850, 680);
		frmLibros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibros.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 67, 363, 110);
		frmLibros.getContentPane().add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMateria.setBounds(119, 69, 136, 14);
		frmLibros.getContentPane().add(lblMateria);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 213, 747, 194);
		frmLibros.getContentPane().add(scrollPane_1);
		
		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		textField = new JTextField();
		textField.setBounds(151, 444, 642, 31);
		frmLibros.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(151, 495, 642, 31);
		frmLibros.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.setBounds(151, 567, 104, 23);
		frmLibros.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.setBounds(559, 567, 102, 23);
		frmLibros.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Actualizar");
		btnNewButton_2.setBounds(427, 567, 102, 23);
		frmLibros.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Salir");
		btnNewButton_3.setBounds(689, 567, 104, 23);
		frmLibros.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel = new JLabel("T\u00EDtulo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(46, 452, 95, 14);
		frmLibros.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(46, 503, 89, 14);
		frmLibros.getContentPane().add(lblNewLabel_1);
		
		JButton button = new JButton("Borrar");
		button.setBounds(289, 567, 102, 23);
		frmLibros.getContentPane().add(button);
	}
}
