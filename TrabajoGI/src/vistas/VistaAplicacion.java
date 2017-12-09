package vistas;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelos.Usuario;
import modelos.tLibro;
import modelos.tMateria;

public class VistaAplicacion extends JPanel{
	
	private JFrame frmLibros;
	private JTable table;
	private DefaultListModel<String> modeloLista;
	private DefaultTableModel modeloTabla;
	private JList<String> listMateria;
	private ListSelectionModel select;
	private boolean aux = true;
	private JButton bLimpiar,bSalir, bBorrar; ;
	private JTextField lTitulo, lAutor;
	private Usuario usuario;
	
	//---------------------------------------------------------------------------- INICIO EESTRUCTURA VISTA APLICACION ------------------------------------------------------------------------
	public VistaAplicacion(Usuario user) throws Exception {
		
		usuario = user;
		frmLibros = new JFrame();
		frmLibros.setTitle("Libros");
		frmLibros.setBounds(100, 100, 850, 680);
		frmLibros.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLibros.getContentPane().setLayout(null);
		frmLibros.setVisible(true);
		
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
		
		lTitulo = new JTextField();
		lTitulo.setBounds(151, 444, 642, 31);
		lTitulo.setHorizontalAlignment(JTextField.LEFT);// Daniel Cuevas: He tenido que alinear el text a la izquierda debido a que los titulos de los libros son grandes
		
		frmLibros.getContentPane().add(lTitulo);
		lTitulo.setColumns(10);
		
		lAutor = new JTextField();
		lAutor.setColumns(10);
		lAutor.setBounds(151, 495, 642, 31);
		frmLibros.getContentPane().add(lAutor);
		
		JButton btnNewButton = new JButton("INSERTAR");
		btnNewButton.setBounds(151, 567, 104, 23);
		frmLibros.getContentPane().add(btnNewButton);
		
		bLimpiar = new JButton("LIMPIAR");
		bLimpiar.setBounds(559, 567, 102, 23);
		frmLibros.getContentPane().add(bLimpiar);
		
		JButton btnNewButton_2 = new JButton("ACTUALIZAR");
		btnNewButton_2.setBounds(427, 567, 120, 23);// Daniel: (Posicion pixel Izquierda, posicion pixel Superior, anchura, altura)
		frmLibros.getContentPane().add(btnNewButton_2);
		
		bSalir = new JButton("SALIR");
		bSalir.setBounds(689, 567, 104, 23);
		frmLibros.getContentPane().add(bSalir);
		
		JLabel lblNewLabel = new JLabel("T\u00EDtulo");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(46, 452, 95, 14);
		frmLibros.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Autor");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(46, 503, 89, 14);
		frmLibros.getContentPane().add(lblNewLabel_1);
		
		bBorrar = new JButton("BORRAR");
		bBorrar.setBounds(289, 567, 102, 23);
		frmLibros.getContentPane().add(bBorrar);
		
		generarContenidoTabla();
	}
	//--------------------------------------------------------------------------FIN ESTRUCTURA VISTA APLICACION ------------------------------------------------------------------------------
	
	
	//--------------------------------------------------------------------LOS TRES CONTROLADORES BOTONES , TABLA y LISTA ---------------------------------------------------------------------
	//Daniel :CONTROLADOR DE BOTONES
	public void controladorVista(ActionListener ctrl){
		bLimpiar.addActionListener(ctrl);
		bLimpiar.setActionCommand("LIMPIAR");
		
		bBorrar.addActionListener(ctrl);
		bBorrar.setActionCommand("BORRAR");
		
		bSalir.addActionListener(ctrl);
		bSalir.setActionCommand("SALIR");
	}
	
	//Apartado 3. Daniel Cuevas : CONTROLADOR TABLA.
	public void controlTabla(ListSelectionListener ctr) {
		table.getSelectionModel().addListSelectionListener(ctr);
	}
	
	 //Apartado 3. Daniel: CONTROLADOR LISTA DE MATERIAS
	public void controladorLista(ListSelectionListener ctrLista){
		listMateria.addListSelectionListener(ctrLista);
	}
	//----------------------------------------------------------------------- FIN CONTROLADORES ------------------------------------------------------------------------------------------
	
	//---------------------------------------------------------------------- METODOS AUXILIARES -------------------------------------------------------------------------------------------
	
	//Daniel: METODO QUE GENERA EL CONTENIDO DE LA TABLA CUANDO SE INICIA LA VISTA
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
	 
	 //Apartado 3. Daniel: Método auxiliar que añade a la lista de materias las materias de la base de datos
	 public void cargarListaMaterias(tMateria materia) throws Exception{
			for(int i = 0; i< materia.ListaMaterias().size() ; i++ ){
				modeloLista.addElement(materia.ListaMaterias().get(i).getNombre());
			}
	 }
	
	 //Apartado 3.Daniel: Método auxiliar donde introduzco los valores en los campos textField de Título y autor
	 public void cargarTextField(tLibro libro) throws Exception{
		lTitulo.setText(libro.getTitulo());
		lAutor.setText(libro.getAutor());
	 }
	 
	 /*
	  * Daniel: Apartado 4. Método auxiliar que es llamado por el controladorVista que elimina el contenido
	  * de los dos textFields y quita la seleccion del Jtable. Tambien lo utilizo cuando borro una libro.
	  */
	 public void limpiar(){
		 lTitulo.setText("");;
		 lAutor.setText("");
		 table.clearSelection();
	 }
	 /*
	  *  Daniel : Apartado 7. Método que utiliza el controlador cuando se pulsa el boton BORRAR
	  *  Elimina el libro de la base de datos y elimina la fila donde se seleccionó el libro.
	  *  Tambien limpio los campos textFiled del libro a borrar.
	  */
	 public void borrar() throws Exception{
		 int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0); // obtengo valor ID de la fila seleccionada 
		 tLibro libro = new tLibro(id);// Creo objeto libro con el id obtenido
		 libro.BorrarLibro(); // metodo para borrar libro de la Base de datos
		 modeloTabla.removeRow(table.getSelectedRow());// Elimina la fila que getSelectedRow() devuelve.
		 limpiar();
	 }
	
	 public JTable getTabla(){
		 return table;
	 }
	 public JList<String> getLista(){
		 return listMateria;
	 }
	 public Usuario getUsuario(){
		 return usuario;
	 }
	 
	 public void mensaje(String mensaje){
		 //Jairo create un JLabel en la base de la vista para escribir mensajes ahi
		 //Para poner por ejemplo : Libro borrado con exito, Libro insertado con exito, Libro modificado con exito...
	 }
	 
	 //Daniel: Apartado 4. Método auxiliar que utiliza el controlador de la vista para cerrar la ventana
	 public void salir(){
		 frmLibros.dispose();
	 }
}