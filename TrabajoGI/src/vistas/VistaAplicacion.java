package vistas;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListModel;
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
import javax.swing.event.ListSelectionEvent;
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
	private DefaultListModel<String> modeloLista;
	private DefaultTableModel modeloTabla;
	private JList<String> listMateria;
	private ListSelectionModel select;
	private boolean aux = true;
	
	public VistaAplicacion(Usuario user) throws Exception {
		
		frmLibros = new JFrame();
		frmLibros.setTitle("Libros");
		frmLibros.setBounds(100, 100, 850, 680);
		frmLibros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibros.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(282, 67, 363, 110);
		frmLibros.getContentPane().add(scrollPane);
		
		listMateria = new JList<String>();
		modeloLista = new DefaultListModel<String>();
		listMateria.setModel(modeloLista);
		scrollPane.setViewportView(listMateria);
		
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
		table.setFocusable(false); //Daniel: Quito el focus de las celdas, para que cuando seleccione una celda se vea toda la fila seleccionada y no el focus de la celda
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getColumnModel().getColumn(0).setMaxWidth(40);//Daniel: configuro tamaño maximo de la columna ID
		table.getColumnModel().getColumn(1).setMaxWidth(1000); //Daniel: configuro tamaño maximo de la columna Titulo
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(46, 213, 747, 194);
		scrollPane_1.setViewportView(table);
		frmLibros.getContentPane().add(scrollPane_1);
		
		textField = new JTextField();
		textField.setBounds(151, 444, 642, 31);
		textField.setHorizontalAlignment(JTextField.LEFT);// Daniel Cuevas: He tenido que alinear el text a la izquierda debido a que los titulos de los libros son grandes
		
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
		controlTabla();
		
	}
	
	public void controladorVista(ActionListener ctrl){
		/*
		 * Para controlar los botones mayormente
		 */
	}
	
	/*
	 * Apartado 3
	 * Programador: Daniel Cuevas
	 * Implementación : metodo donde vinculo el controlador de la lista
	 */
	public void controladorLista(ListSelectionListener ctrLista){
		listMateria.addListSelectionListener(ctrLista);
	}
	
	 public void generarContenidoTabla() throws Exception{
		List<tLibro> listLibros = new ArrayList<tLibro>();
		listLibros = tLibro.ListaLibros();//Daniel: Almaceno una lista con todos los objetos de tLibro de la base da datos
		
		for(int i = 0 ; i < listLibros.size(); i++){//Daniel: En el bucle voy añadiendo filas con las ID y Titulos de tLibros
			Object[] rowData = new Object[2];
			rowData[0]= listLibros.get(i).getID();
			rowData[1]=	listLibros.get(i).getTitulo();	
			modeloTabla.addRow(rowData); // Añado la fila al DafaultModel con el array rowData que contiene ID y Titulo
		}
	 }
	 
	 /*
	  * Apartado 3
	  * Daniel Cuevas : En este metodo cargo la tabla con los libros de la base da datos
	  * y cuando se pulsa en un determinado libro se carga la lista de materias, teniendo el focus en su materia relacionada
	  */
		public void controlTabla() {
			
			select = table.getSelectionModel();
			select.addListSelectionListener(new ListSelectionListener() {//Daniel:  obtengo el SelectionListener para controlar los cambios
				public void valueChanged(ListSelectionEvent e) {// En cada cambio de seleción de la tabla:
					tLibro libro;
					if (!select.isSelectionEmpty()) {
						if (!e.getValueIsAdjusting()){
							try {
								libro = new tLibro(table.getSelectedRow()+1);	//Obtengo fila seleccionada y creo un objeto libro, el cual contendra todos los datos de la base da datos											
								tMateria materia = new tMateria(libro.getID_Materia());// Creo un objeto materia con el ID_Materia del objeto libro anterior
								cargarTextField(libro);// Cargo valores en los TextField
								if(aux){
									cargarListaMaterias(materia); // Cargo la lista de materias solo una vez, sino cada vez que pulse un libro se vuelve a cargar
									aux = false;
								}
								boolean scrollIntoView = true;
								listMateria.setSelectedValue(materia.getNombre(), scrollIntoView);// Cambio el focus de la lista materias dependiendo del libro que seleccione
							} catch (Exception e1) {
								
							}
						}
					}
				}
			});
		}
	/*
	 * Daniel: Método auxiliar que añade a la lista de materias las materias de la base de datos
	 * Apartado 3
	 */
	 public void cargarListaMaterias(tMateria materia) throws Exception{
			for(int i = 0; i< materia.ListaMaterias().size() ; i++ ){
				modeloLista.addElement(materia.ListaMaterias().get(i).getNombre());
			}
	 }
	 /*
	  * Daniel: Método auxiliar donde introduzco los valores en los campos textField de Título y autor
	  * Apartado 3
	  */
	 public void cargarTextField(tLibro libro) throws Exception{
			textField.setText(libro.getTitulo());
			textField_1.setText(libro.getAutor());
	 }
	 
	 public void mensaje(String mensaje){
		 //Jairo create un JLabel en la base de la vista para escribir mensajes ahi, aunque no se si hace falta todavia.
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