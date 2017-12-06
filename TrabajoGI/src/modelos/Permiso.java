package modelos;
import java.sql.SQLException;
import java.util.*;


public class Permiso 
{

    private static String BD_NAME = "TrabajoGI1718";
    
	private String rolName;
	private String pantalla;
    private int acceso;
    private int modificacion;


    public static List<Permiso> ListaPermisosRol(String rolName) throws SQLException
    {

		ArrayList<Permiso> lista = new ArrayList<Permiso>(); 		
		// Retorna una lista con todos los obejtos de la clase almacenados en la base de datos
		BD miBD = new BD(BD_NAME);
		
		for(Object[] tupla: miBD.Select("SELECT pantalla FROM tPermiso WHERE rolName = '" + rolName + "'"))
		{
			lista.add(new Permiso(rolName, (String)tupla[0]));
		}
		return lista;
    }
    
    public Permiso(String r, String p) throws SQLException
    {
		// Crea el objeto cargando sus valores de la base de datos
		BD miBD = new BD(BD_NAME);
		
    	Object[] tupla = miBD.Select("SELECT * FROM tPermiso WHERE " 
    			+ "rolName = '" + r + "' AND "
    			+ "pantalla= '" + p + "'").get(0);
    	

    	rolName = (String)tupla[0];
    	pantalla  = (String)tupla[1];
        acceso = (int)tupla[2];
        modificacion = (int)tupla[3];
    }

    public Permiso(String r, String p, int a, int m) throws SQLException
    {
		// Crea el objeto y lo inserta en la base de datos
		BD miBD = new BD(BD_NAME);
		
    	miBD.Insert("INSERT INTO tPermiso VALUES(' " + r + "'," 
    			+ "'" + p + "', " + (a) + ", " + (m) + ")");
    	
    	rolName = r;
    	pantalla  = p;
        acceso = a;
        modificacion = m;

    }
    
	public void setRolName(String value) throws SQLException 
	{
		// Actualiza el atributo en memoria y en la base de datos
		BD miBD = new BD(BD_NAME);
    	miBD.Update("UPDATE tPermiso SET rolName = '" + value + "' WHERE " 
    			+ "rolName = '" + rolName + "' AND "
    			+ "pantalla= '" + pantalla + "'");
    	
    	rolName = value;
	}

	public String getRolName() 
	{
		return rolName;
	}
    
    public String getPantalla() 
    {
    	return pantalla; 
    }
    
    public void setPantalla(String value) throws SQLException 
    {
		// Actualiza el atributo en memoria y en la base de datos
		BD miBD = new BD(BD_NAME);
    	miBD.Update("UPDATE tPermiso SET pantalla = '" + value + "' WHERE " 
    			+ "rolName = '" + rolName + "' AND "
    			+ "pantalla= '" + pantalla + "'");
    	
    	pantalla = value;
    }
    

    public int getAcceso() 
    { 
    	return acceso; 
    }
        
    public void setAcceso(int value) throws SQLException 
    { 
		// Actualiza el atributo en memoria y en la base de datos
		BD miBD = new BD(BD_NAME);
    	miBD.Update("UPDATE tPermiso SET acceso = " + (value) + " WHERE " 
    			+ "rolName = '" + rolName + "' AND "
    			+ "pantalla= '" + pantalla + "'");
    	
    	acceso = value;
    }

    
    public int getModificacion() 
    { 
    	return modificacion; 
    }
    
    public void setModificacion(int value) throws SQLException 
    { 
		// Actualiza el atributo en memoria y en la base de datos
		BD miBD = new BD(BD_NAME);
    	miBD.Update("UPDATE tPermiso SET modificacion = " + (value) + " WHERE " 
    			+ "rolName = '" + rolName + "' AND "
    			+ "pantalla= '" + pantalla + "'");
    	
    	modificacion = value;
    }


}
