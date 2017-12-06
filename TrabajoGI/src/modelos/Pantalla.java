package modelos;
import java.sql.SQLException;
import java.util.*;

public class Pantalla 
{
    private static String BD_NAME = "TrabajoGI1718";
    
	private String pantalla;


    public static List<Pantalla> ListaPantalla() throws SQLException
    {

		ArrayList<Pantalla> lista = new ArrayList<Pantalla>();
		BD miBD = new BD(BD_NAME);
		for(Object[]tupla : miBD.Select("SELECT pantalla FROM tPantalla")){
			String p = (String)tupla[0];
			lista.add(new Pantalla(p));
		}
		// Retorna una lista con todos los obejtos de la clase almacenados en la base de datos

		return lista;
    }
    
    public Pantalla(String p) throws SQLException
    {
	// Crea el objeto cargando sus valores de la base de datos. Si no existe lo inserta

    	BD miBD = new BD(BD_NAME);
    	
		int num = (Integer) miBD.SelectEscalar("SELECT COUNT(*) FROM tPantalla WHERE pantalla ='"+ p + "'");
		if (num==0)	miBD.Insert("INSERT INTO tPantalla VALUES ('" + p +"')"); 
		
        pantalla = p;        
    }
    
	public void setPantalla(String value) throws SQLException 
	{
		// Actualiza el atributo en memoria y en la base de datos
		BD miBD = new BD(BD_NAME);
		miBD.Update("UPDATE tPantalla SET pantalla = '"+ value + "' WHERE pantalla = '" + pantalla + "'");
		pantalla = value;
		
	}

	public String getPantalla() 
	{
		return pantalla;
	}    

	public String tostring() 
	{
		return pantalla;
	}    


}
