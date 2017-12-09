package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelos.Usuario;
import org.apache.derby.iapi.store.access.AccessFactory;
import vistas.VistaAplicacion;
import vistas.VistaLogin;

public class CtrlLogin implements ActionListener{
	
	private VistaLogin vistaLogin;
		
	public CtrlLogin (VistaLogin vl) {
		this.vistaLogin=vl;
	}
		
	public void actionPerformed(ActionEvent e) {
		
		if (e.getActionCommand().equals("INICIARSESION")) {
			String nombre = vistaLogin.getNombreUsuario();
			String password = vistaLogin.getPassword();
			try {
				Usuario usuario = new Usuario(nombre,password);
				vistaLogin.cerrarVista();
				
				VistaAplicacion vistaAplicacion = new VistaAplicacion(usuario);
				CtrlVistaAplicacion controladorAplicacion = new CtrlVistaAplicacion(vistaAplicacion);
				CtrlTabla controladorTabla = new CtrlTabla(vistaAplicacion);
				vistaAplicacion.controladorVista(controladorAplicacion);
				vistaAplicacion.controlTabla(controladorTabla);
					
			} catch (Error e1) {
				vistaLogin.displayErrorMsg();
			}
			catch(Exception e2) {
				vistaLogin.displayErrorMsg();
			}
		}	
	}	
}
	

