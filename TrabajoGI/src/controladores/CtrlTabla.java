package controladores;

import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vistas.VistaAplicacion;
import modelos.Usuario;
import modelos.tLibro;
import modelos.tMateria;

public class CtrlTabla implements ListSelectionListener {
	
	private VistaAplicacion vista;
	private Usuario usuario;
	private boolean aux= true;
	private JList<String> listMateria;
	private JTable table;
	
	public CtrlTabla(VistaAplicacion vista){
		this.vista = vista;
	}
	
	
	public void valueChanged(ListSelectionEvent e) {
		tLibro libro;
		usuario = vista.getUsuario();
		table = vista.getTabla();
		listMateria = vista.getLista();
		
		if (!table.getSelectionModel().isSelectionEmpty()) {
			if (!e.getValueIsAdjusting()){
				try {
					if(!usuario.getRol().getRolName().equals("invitado")){
						int id = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);// obtengo el valor id del libro seleccionado
						libro = new tLibro(id);	//creo un objeto libro con el titulo obtenido anteriormente, el cual contendra todos los datos de la base da datos
						tMateria materia = new tMateria(libro.getID_Materia());// Creo un objeto materia con el ID_Materia del objeto libro anterior
						vista.cargarTextField(libro);// Cargo valores en los TextField
						if(aux){
							vista.cargarListaMaterias(materia); // Cargo la lista de materias solo una vez, sino cada vez que pulse un libro se vuelve a cargar
							aux = false;
						}
						boolean scrollIntoView = true;
						listMateria.setSelectedValue(materia.getNombre(), scrollIntoView);// Cambio el focus de la lista materias dependiendo del libro que seleccione
					}
					
				} catch (Exception e1) {
				}
			}
		}

	}

}
