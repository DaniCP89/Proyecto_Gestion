package vistas;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableColumnModelListener;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import modelos.Usuario;
import modelos.tLibro;
import modelos.tMateria;

public class VistaAplicacion extends JPanel{
	
	private JFrame frmLibros;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private DefaultListModel<tMateria> modeloLista;
	private DefaultTableModel modeloTabla;
	private JList<tMateria> list;
	ListSelectionModel listSelectionModel;

	public VistaAplicacion(Usuario user) throws Exception {
		
		frmLibros = new JFrame();
		frmLibros.setTitle("Libros");
		frmLibros.setBounds(100, 100, 850, 680);
		frmLibros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibros.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 67, 363, 110);
		frmLibros.getContentPane().add(scrollPane);
		
		list = new JList<tMateria>();
		modeloLista = new DefaultListModel<tMateria>();
		list.setModel(modeloLista);
		scrollPane.setViewportView(list);
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblMateria.setBounds(119, 69, 136, 14);
		frmLibros.getContentPane().add(lblMateria);
		
		/* Daniel Cuevas:
		 * Los modelos de tabla son objetos que implementan la interface TableModel;
		 * a través de ellos es posible personalizar mucho más y mejor el comportamiento de los componentes JTable, 
		 * permitiendo utilizar al máximo sus potencialidades.
		 * 
		 * La clase AbstractTableModel es la que implementa directamente a la interface TableModel, 
		 * aunque es esta clase la que se recomienda extender para utilizarla como modelo de tabla, 
		 * existe un modelo de tabla predeterminado que facilita mucho el trabajo con tablas. 
		 * Este modelo predeterminado es la clase DefaultTableModel
		 */
		String t[] ={"ID","Título"}; // Daniel : Almaceno las columnas en el DefaultTableModel y hago que no sean editable las celdas
		modeloTabla = new DefaultTableModel(null,t){
			public boolean isCellEditable(int filas, int columnas){
				return false;
			}
		};
		table = new JTable();
		table.setModel(modeloTabla);
		table.getTableHeader().setReorderingAllowed(false); // Daniel : No permite mover las columnas
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 213, 747, 194);
		scrollPane_1.setViewportView(table);
		frmLibros.getContentPane().add(scrollPane_1);
		
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
		
		generarContenidoTabla();
		
	}
	
	public void controlador(ActionListener ctrl){
		
		
	}
	
	/*
	 * Programador: Daniel Cuevas
	 * Implementación : metodo donde vinculo el controlador de la lista
	 */
	public void controladorLista(ListSelectionListener ctrLista){
		list.addListSelectionListener(ctrLista);
	}
	
	 public void generarContenidoTabla() throws Exception{
		List<tLibro> listLibros = new ArrayList<tLibro>();
		listLibros = tLibro.ListaLibros();//Daniel: Almaceno una lista con todos los objetos de tLibro
		
		for(int i = 0 ; i < listLibros.size(); i++){//Daniel: En el bucle voy añadiendo filas con las ID y Titulos de tLibros
			Object[] rowData = new Object[2];
			rowData[0]= listLibros.get(i).getID();
			rowData[1]=	listLibros.get(i).getTitulo();	
			modeloTabla.addRow(rowData); // Añado la fila al DafaultModel con el array que contiene ID y Titulo
		}
		
		table.getColumnModel().getColumn(0).setMaxWidth(40);//Daniel: configuro tamaño maximo de la columna ID
		table.getColumnModel().getColumn(1).setMaxWidth(1000); //Daniel: configuro tamaño maximo de la columna Titulo
		
	 }
	 
	 public void setTitulo(String titulo){
		 textField.setText(titulo);
	 }
	 
	 public void setAutor(String autor){
		 textField.setText(autor);
	 }
	 public void mensaje(String mensaje){
		 
	 }
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaAplicacion window = new VistaAplicacion(null);
					window.frmLibros.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}