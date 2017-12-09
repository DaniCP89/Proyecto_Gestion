package controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import vistas.VistaAplicacion;

public class CtrlVistaAplicacion implements ActionListener{
	private VistaAplicacion vista;
	private JTable table;
	
	public CtrlVistaAplicacion(VistaAplicacion v){
		super();
		vista = v;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		
		switch (comando){
		case "LIMPIAR":
			vista.limpiar();
		break;
		case "BORRAR":
			try {
				vista.borrar();
			} catch (Exception e1) {
			}
		break;
		case "SALIR":
			vista.salir();
		break;
		}
	}
}
