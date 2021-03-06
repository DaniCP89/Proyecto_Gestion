
package modelos;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class tLibro {
	private int ID;
	private String Titulo;
	private String Autor;
	private String ID_Materia;
	private static String BD_NAME = "TrabajoGI1718";

	public tLibro(String t, String a, String iM) throws SQLException {
		
		this.Titulo = t;
		this.Autor = a;
		this.ID_Materia = iM;
		// Una vez inicializada la metemos en la base de datos
		BD miBD = new BD(BD_NAME);
		miBD.Insert("INSERT INTO tLibro (TITULO,AUTOR,ID_MATERIA) VALUES ('" + this.Titulo
				+ "', '" + this.Autor + "', '" + this.ID_Materia + "') ");
		this.ID = (int)(long) miBD.SelectEscalar("Select MAX(ID) from tLibro");
		
	}
	
	public tLibro(int id) throws SQLException {
		//Dado el ID creamos el objeto
		
		BD miBD = new BD(BD_NAME);
		
		Object [] tupla = miBD.Select("SELECT * FROM tLibro WHERE ID = "+id+" ").get(0);
		
		this.ID= id;
		this.Titulo=(String) tupla[1];
		this.Autor=(String) tupla[2];
		this.ID_Materia=(String) tupla[3];

	}
	/*
	 * Daniel Cuevas: he necesitado este constructor para cuando obtenga el titulo de la tabla, rellenar el campo textField del autor y desplegar la lista de materias asociadas
	 * Dado que en la tabla esta rellena con ID numerio y en la base de datos son letras.
	 */

	public static List<tLibro> ListaLibros() throws SQLException {

		ArrayList<tLibro> lista = new ArrayList<tLibro>();
		// Retorna una lista con todos los objetos de la clase almacenados en la
		// base de datos
		BD miBD = new BD(BD_NAME);
		for (Object[] tupla : miBD.Select("SELECT * FROM tLibro")) {
			lista.add(new tLibro((int)(long)tupla[0]));
			
		}

		return lista;
	}

	public int getID() {
		return ID;
	}
	

	public void setID(int id) throws SQLException {
		if (id > 0) {
			this.ID = id;
			BD miBD = new BD(BD_NAME);
			miBD.Update("UPDATE tLibro SET ID = " + this.ID + " WHERE TITULO = '" + this.Titulo + "' AND Autor = '"
					+ this.Autor + "' AND ID_MATERIA = '" + this.ID_Materia + "' ");
		}
	}

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String t) throws SQLException {
		if (t != null) {
			this.Titulo = t;
			BD miBD = new BD(BD_NAME);
			miBD.Update("UPDATE tLibro SET TITULO = '" + this.Titulo + "' WHERE ID = " + this.ID + " AND Autor = '"
					+ this.Autor + "' AND ID_MATERIA = '" + this.ID_Materia + "' ");
		}
	}

	public String getAutor() {
		return Autor;
	}

	public void setAutor(String a) throws SQLException {
		if (a != null) {
			this.Autor = a;
			BD miBD = new BD(BD_NAME);
			miBD.Update("UPDATE tLibro SET AUTOR = '" + this.Autor + "' WHERE ID = " + this.ID + " AND TITULO = '"
					+ this.Titulo + "' AND ID_MATERIA = '" + this.ID_Materia + "' ");
		}

	}

	public String getID_Materia() {
		return ID_Materia;
	}

	public void setID_Materia(String iM) throws SQLException {
		if (iM != null) {
			this.ID_Materia = iM;
			BD miBD = new BD(BD_NAME);
			miBD.Update("UPDATE tLibro SET ID_MATERIA = '" + this.ID_Materia + "' WHERE ID = " + this.ID
					+ " AND TITULO = '" + this.Titulo + "' AND AUTOR = '" + this.Autor + "' ");
		}

	}
	public void BorrarLibro() throws SQLException {
		// Actualiza el grupo que toca esta cancion dado su nombre
		BD miBD = new BD(BD_NAME);
		miBD.Delete("DELETE FROM tLibro WHERE ID =" + ID + ""); //Daniel: he tenido que quitar las '' de this.ID ya que es una variable tipo int
		ID = -1;
		Titulo = null;
		Autor = null;
		ID_Materia=null;

	}

	@Override
	public String toString() {
		return "tLibro [ID=" + ID + ", Titulo=" + Titulo + ", Autor=" + Autor + ", ID_Materia=" + ID_Materia + "]";
	}
}
