package controladores;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JList;
import javax.swing.JTable;

import modelos.Usuario;
import vistas.VistaAplicacion;

public class CtrlVistaAplicacion implements ActionListener{
	private VistaAplicacion vista;
	private JTable table;
	private Usuario usuario;
	
	public CtrlVistaAplicacion(VistaAplicacion v){
		super();
		vista = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		JList<String> listMateria = vista.getLista();
		String comando = e.getActionCommand();
		usuario = vista.getUsuario();
		table = vista.getTabla();
		switch (comando)
		{
				case "LIMPIAR":
					if(!usuario.getRol().getRolName().equals("invitado")){
						vista.limpiar();
						vista.mensajeExitoso();
					}
					
				break;
				case "BORRAR":
					try {
						if((usuario.getRol().getRolName().equals("usuario")) || (usuario.getRol().getRolName().equals("invitado")) ){
							 vista.mensajeNoPermiso();
						}else if ((usuario.getRol().getRolName().equals("administrador")) && (!table.getSelectionModel().isSelectionEmpty())){
							vista.borrar();
							vista.mensajeExitoso();
						}else{
							vista.getMensaje().setForeground(Color.RED);
							vista.getMensaje().setText("Error BORRAR: Seleccione una materia y un libro.");
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				break;
				case "INSERTAR":
					try {
						if((usuario.getRol().getRolName().equals("usuario")) || (usuario.getRol().getRolName().equals("invitado")) ){
							 vista.mensajeNoPermiso();
						 }else if ((usuario.getRol().getRolName().equals("administrador")) && (!listMateria.isSelectionEmpty()) && (!vista.getTitulo().getText().isEmpty())){
							vista.insertar();
							vista.mensajeExitoso();
						}else{
							vista.getMensaje().setForeground(Color.RED);
							vista.getMensaje().setText("Error INSERTAR: Seleccione una materia, un libro e introduzca un Título.");
						}
					}catch (SQLException e1) {
						e1.printStackTrace();
					}
				break;
				case "ACTUALIZAR":
					try {
						if((usuario.getRol().getRolName().equals("usuario")) || (usuario.getRol().getRolName().equals("invitado")) ){
							 vista.mensajeNoPermiso();
						 }else if ((usuario.getRol().getRolName().equals("administrador")) && (!table.getSelectionModel().isSelectionEmpty()) && (!vista.getTitulo().getText().isEmpty())){
							vista.actualizar();
							vista.mensajeExitoso();
						}else{
							vista.getMensaje().setForeground(Color.RED);
							vista.getMensaje().setText("Error ACTUALIZAR: Seleccione una materia, un libro e introduzca título.");
						}
						
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}
				break;
				case "SALIR":
					vista.salir();
				break;
		}
	}
}
