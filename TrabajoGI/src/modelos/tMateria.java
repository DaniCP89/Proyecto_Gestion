
package modelos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tMateria {

	private String ID_Materia;
	private String Nombre;

	private static String BD_NAME = "TrabajoGI1718";

	public tMateria(String id, String n) throws SQLException {
		this.ID_Materia = id;
		this.Nombre = n;
		// Una vez inicializada la metemos en la base de datos
		BD miBD = new BD(BD_NAME);
		miBD.Insert(
				"INSERT INTO tMateria (NOMBRE) VALUES ( '" + this.Nombre + "') ");
	}

	public tMateria(String id) throws SQLException {
		BD miBD = new BD(BD_NAME);
		Object[] tupla = miBD.Select("SELECT * FROM tMateria WHERE ID_MATERIA = '" + id + "' ").get(0);
		this.ID_Materia = (String) tupla[0];
		this.Nombre = (String) tupla[1];

	}
	
	public static String encontrarID (String name) throws SQLException {
		BD miBD = new BD(BD_NAME);
		String id = (String) miBD.SelectEscalar("SELECT ID_Materia FROM tMateria WHERE NOMBRE = '" + name + "' ");
		return id;
	}

	@Override
	public String toString() {
		return "tMateria [ID_Materia=" + ID_Materia + ", Nombre=" + Nombre + "]";
	}

	public static List<tMateria> ListaMaterias() throws SQLException {
		ArrayList<tMateria> lista = new ArrayList<tMateria>();
		// Retorna una lista con todos los objetos de la clase almacenados en la
		// base de datos
		BD miBD = new BD(BD_NAME);
		for (Object[] tupla : miBD.Select("SELECT * FROM tMateria")) {
			String id = (String) tupla[0];
			String n = (String) tupla[1];
			lista.add(new tMateria(id));
		}

		return lista;
	}

	public String getID_Materia() {
		return ID_Materia;
	}

	public void setID_Materia(String iD_Materia) throws SQLException {
		if (iD_Materia != null) {
			BD miBD = new BD(BD_NAME);
			miBD.Update("UPDATE tMateria SET ID_MATERIA = '" + iD_Materia + "' WHERE ID = '" + this.ID_Materia
					+ "' AND NOMBRE = '" + this.Nombre + "' ");
			this.ID_Materia = iD_Materia;
		}
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) throws Exception {
		if (nombre != null) {
			BD miBD = new BD(BD_NAME);
			miBD.Update("UPDATE tMateria SET NOMBRE = '" + nombre + "' WHERE ID = '" + this.ID_Materia
					+ "' AND NOMBRE = '" + this.Nombre + "' ");
			this.Nombre = nombre;
		}
	}

	public void BorrarMateria() throws SQLException {
		// Actualiza el grupo que toca esta cancion dado su nombre
		BD miBD = new BD(BD_NAME);
		miBD.Delete("DELETE FROM tMateria WHERE ID_MATERIA ='" + this.ID_Materia + "'");
		ID_Materia = null;
		Nombre = null;

	}

}
