/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import entidades.Socio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author marti
 */
public class SociosDAO implements ISociosDAO
{

    private IConexionBD conexion;

    public SociosDAO(IConexionBD conexion) 
    {
        this.conexion = conexion;
    }
    
    
            
    @Override
    public boolean agregar(Socio socio) 
    {
        try
        {
            //Establece una conexion a MySQL... Si no puede lanza una SQLException
            Connection conexion = this.conexion.crearConexion();
            
            //Creamos un objeto Statement que nos permite enviar codigo SQL a la BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("INSERT INTO socios(nombre, curp) VALUES ('%s\', '%s');");
            
            //Este metodo se utiliza para hacer operaciones de modificacion de datos(INSART, DELETE, UPDATE)
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            if(registrosAfectados==1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean actualizar(Socio socio) 
    {
        try
        {
            //Establece una conexion a MySQL... Si no puede lanza una SQLException
            Connection conexion = this.conexion.crearConexion();
            
            //Creamos un objeto Statement que nos permite enviar codigo SQL a la BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("UPDATE socios SET nombre= '%s', curp = '%s' WHERE id_socio = %d;", 
                    socio.getNombre(), socio.getCurp(), socio.getIdSocio());
            
            //Este metodo se utiliza para hacer operaciones de modificacion de datos(INSART, DELETE, UPDATE)
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            if(registrosAfectados==1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
            return false;
        }
        
    }

    @Override
    public boolean eliminar(Long idSocio) 
    {
        try
        {
            //Establece una conexion a MySQL... Si no puede lanza una SQLException
            Connection conexion = this.conexion.crearConexion();
            
            //Creamos un objeto Statement que nos permite enviar codigo SQL a la BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("DELETE FROM socios WHERE id_socio = %d;", idSocio);
            
            //Este metodo se utiliza para hacer operaciones de modificacion de datos(INSART, DELETE, UPDATE)
            int registrosAfectados = comandoSQL.executeUpdate(codigoSQL);
            
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            if(registrosAfectados==1)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    @Override
    public Socio consultar(Long idSocio) 
    {
        Socio socio = null;
        try
        {
            //Establece una conexion a MySQL... Si no puede lanza una SQLException
            Connection conexion = this.conexion.crearConexion();
            
            //Creamos un objeto Statement que nos permite enviar codigo SQL a la BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("SELECT id_socio, nombre, curp FROM socios WHERE id_socio = %d;", idSocio);
            
            //Este metodo se utiliza para hacer consultas(SELECT)
            ResultSet resultados = comandoSQL.executeQuery(codigoSQL);
            
            //Si hay un registro
            if(resultados.next())
            {
                Long id = resultados.getLong("id_socio");
                String nombre = resultados.getString("Nombre");
                String curp = resultados.getString("curp");
                socio = new Socio(idSocio, nombre, curp);
            }
            
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            return socio;
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
            return socio;
        }
    }

    @Override
    public List<Socio> consultarTodos() 
    {
        List<Socio> listaSocios = new ArrayList<>();
        try
        {
            //Establece una conexion a MySQL... Si no puede lanza una SQLException
            Connection conexion = this.conexion.crearConexion();
            
            //Creamos un objeto Statement que nos permite enviar codigo SQL a la BD
            Statement comandoSQL = conexion.createStatement();
            
            String codigoSQL = String.format("SELECT id_socio, nombre, curp FROM socios");
            
            //Este metodo se utiliza para hacer consultas(SELECT)
            ResultSet resultados = comandoSQL.executeQuery(codigoSQL);
            
            //Mientras haya resultados que leer... los leemos
            while(resultados.next())
            {
                Long idSocio = resultados.getLong("id_socio");
                String nombre = resultados.getString("Nombre");
                String curp = resultados.getString("curp");
                Socio socio = new Socio(idSocio, nombre, curp);
                listaSocios.add(socio);
            }
            
            //Cerramos la conexion para evitar desperdicio de recursos
            conexion.close();
            
            return listaSocios;
        }
        catch(SQLException ex)
        {
            System.err.println(ex.getMessage());
            return listaSocios;
        }
        
    }
    
}
