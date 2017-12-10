package vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelos.BD;
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
	private JButton bLimpiar,bSalir, bBorrar, bInsertar, bActualizar;
	private JTextField lTitulo, lAutor;
	private Usuario usuario;
	private JLabel mensaje;
	
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
		table.setFillsViewportHeight(false); // No deja espacio abajo cuando se van eliminando filas.
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
		
		bInsertar = new JButton("INSERTAR");
		bInsertar.setBounds(151, 567, 104, 23);
		frmLibros.getContentPane().add(bInsertar);
		
		bLimpiar = new JButton("LIMPIAR");
		bLimpiar.setBounds(559, 567, 102, 23);
		frmLibros.getContentPane().add(bLimpiar);
		
		bActualizar = new JButton("ACTUALIZAR");
		bActualizar.setBounds(427, 567, 120, 23);// Daniel: (Posicion pixel Izquierda, posicion pixel Superior, anchura, altura)
		frmLibros.getContentPane().add(bActualizar);
		
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
		
		mensaje = new JLabel();
		mensaje.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
		mensaje.setBounds(10, 616, 600, 14);
		frmLibros.getContentPane().add(mensaje);
		
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
		
		bInsertar.addActionListener(ctrl);
		bInsertar.setActionCommand("INSERTAR");
		
		bActualizar.addActionListener(ctrl);
		bActualizar.setActionCommand("ACTUALIZAR");
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
	 
	 public void insertar() throws SQLException {

		String titulo = lTitulo.getText();
		String autor = lAutor.getText();
		String materia = listMateria.getSelectedValue();
		String idMat = (String) tMateria.encontrarID(materia);	
			
		tLibro lib = new tLibro(titulo,autor,idMat); 
			 
		Object[] obj = new Object[2]; 
		obj[0]= lib.getID();
		obj[1] = lib.getTitulo();
			
		modeloTabla.addRow(obj);
	 }
	 
	public void actualizar() throws SQLException {

		int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
		tLibro libro = new tLibro(id);

		String titulo = lTitulo.getText();
		String autor = lAutor.getText();
		String materia = listMateria.getSelectedValue();
		String idMatApp = tMateria.encontrarID(materia);

		String idMatLib = libro.getID_Materia();

		if (!titulo.equals(libro.getTitulo())) {
			libro.setTitulo(titulo);
			modeloTabla.setValueAt(titulo, table.getSelectedRow(), 1);
		}
		if (!autor.equals(libro.getAutor())) {
			libro.setAutor(autor);
		}
		if (!idMatApp.equals(idMatLib)) {
			libro.setID_Materia(idMatApp);
		}
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
	 public JTextField getTitulo(){
		 return lTitulo;
	 }
	 public JLabel getMensaje(){
		 return mensaje;
	 }
	 public void mensajeExitoso(){
		 mensaje.setForeground(new Color(0, 204, 0));
		 mensaje.setText("Operazión con exito");
	 }
	 public void mensajeNoPermiso(){
		 mensaje.setForeground(Color.RED);
		 mensaje.setText("Error: usuario sin permiso");
	 }
	 
	 //Daniel: Apartado 4. Método auxiliar que utiliza el controlador de la vista para cerrar la ventana
	 public void salir(){
		 frmLibros.dispose();
	 }
}