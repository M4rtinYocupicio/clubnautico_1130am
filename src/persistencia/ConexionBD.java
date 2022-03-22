/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marti
 */
public class ConexionBD implements IConexionBD
{
    
    final String CADENA_CONEXION = "jdbc:mysql://localhost/club_nautico_1130am";
    final String USUARIO = "root";
    final String CONTRASENIA = "root";

    @Override
    public Connection crearConexion() throws SQLException 
    {
        //Establece una conexion a MySQL... Si no puede lanza una SQLException
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASENIA);
        return conexion;
    }
    
}
