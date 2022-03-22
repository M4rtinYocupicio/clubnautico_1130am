/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club_nautico;

import entidades.Socio;
import guis.SociosForm;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import persistencia.ConexionBD;
import persistencia.IConexionBD;
import persistencia.ISociosDAO;
import persistencia.SociosDAO;
/**
 *
 * @author marti
 */
public class Principal
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {     
        IConexionBD conexionBD= new ConexionBD();
        ISociosDAO sociosDAO = new SociosDAO(conexionBD);
        
        List<Socio> listaSocios = sociosDAO.consultarTodos();
        listaSocios.forEach(socio -> {
            System.out.println(socio);
                });
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new SociosForm(sociosDAO).setVisible(true);
            }
        });
    }
    
}
