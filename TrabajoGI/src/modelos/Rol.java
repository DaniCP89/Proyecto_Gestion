package modelos;
import java.sql.SQLException;
import java.util.*;


public class Rol 
{
    private static String BD_NAME = "TrabajoGI1718";
    
    private String rolName;
    private String rolDes;
    private int admin;
    private List<Permiso> permisos;

	public static List<Rol> ListaRoles() throws SQLException
	{
		ArrayList<Rol> lista = new ArrayList<Rol>(); 
		// Retorna una lista con todos los obejtos de la clase almacenados en la base de datos
		BD miBD = new BD(BD_NAME);
					
		for(Object[] tupla: miBD.Select("SELECT rolName FROM tRol"))
		{
			lista.add(new Rol((String)tupla[0]));
		}
		return lista;
	}
	
    public Rol(String name) throws SQLException
    {
		// Crea el objeto cargando sus valores de la base de datos
      	BD miBD = new BD(BD_NAME);			
        Object[] tupla = miBD.Select("SELECT * FROM tRol WHERE rolName='"+name+"'").get(0);
      		
              rolName = (String)tupla[0];
              rolDes = (String)tupla[1];
              admin = (Integer)tupla[2];
              permisos = Permiso.ListaPermisosRol(rolName);              
    }
    
    public Rol(String name, String des, int adm) throws SQLException
    {
		// Crea el objeto y lo inserta en la base de datos
    	BD miBD = new BD(BD_NAME);			
        miBD.Insert("INSERT INTO tRol VALUES ('" + name + "', '" + des + "', " + (adm)+")");
        rolName = name;
        rolDes = des;        
        admin = adm;
        permisos = new ArrayList<Permiso>();              
    }
	
    public String getRolName() 
    { 
    	return rolName; 
    }
        
    public void setRolName(String value) throws SQLException 
    { 
		// Actualiza el atributo en memoria y en la base de datos
    	BD miBD = new BD(BD_NAME);	
    	miBD.Update("UPDATE tRol SET rolName = '" + value 
    			+ "' WHERE rolName ='"+ this.rolName + "'");
    	
    	rolName = value;
    	
    }

    public String getRolDes() 
    { 
    	return rolDes; 
    }
    
    public void setRolDes(String value) throws SQLException 
    {
		// Actualiza el atributo en memoria y en la base de datos
    	BD miBD = new BD(BD_NAME);
    	miBD.Update("UPDATE tRol SET rolDes = '" + value 
    			+ "' WHERE rolName ='"+ this.rolName + "'");
    	
    	rolDes = value;    	
    }

    public int getAdmin()
    { 
    	return admin; 
    }
    
    public void setAdmin(boolean value)
    {
    	throw new Error("Un Rol no puede concederse permisos de administración a sí mismo.");
    }
    public void setAdmin(Rol other, int value) throws SQLException
    {
    	if (admin!=1) throw new Error("Rol sin permiso para establecer administradores.");

		// Actualiza el atributo admin de other en memoria y en la base de datos
    	BD miBD = new BD(BD_NAME);
    	miBD.Update("UPDATE tRol SET admin = " + (value) 
    			+ " WHERE rolName ='"+ other.getRolName() + "'");
    	
    	other.admin = value;
    }

    
    public int Acceso(String pantalla)
    {
        for (Permiso p : permisos)
        {
        	if (p.getPantalla().compareToIgnoreCase(pantalla)==0) 
        		return p.getAcceso();
        }
        return 0;
    }

    public int Modificacion(String pantalla)
    {
        for (Permiso p : permisos)
        {
            if (p.getPantalla().compareToIgnoreCase(pantalla)==0) 
            	return p.getModificacion();
        }
        return 0;
    }

    public void AddPermiso(Permiso p)
    {
        if (!permisos.contains(p))
        {
            permisos.add(p);
        }
    }
    
}
