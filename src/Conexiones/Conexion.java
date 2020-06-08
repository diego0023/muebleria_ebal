
package Conexiones;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Conexion {
Connection ccn = null;
Statement st = null;

    public Conexion(){//constructor de la clase conexion
        try 
            {
                //la ruta donde se encuantra la base de datos
                String rutafile = "dataBase.accdb";
                //comando para realizar la coneccion de java con acces
                String Url = "jdbc:ucanaccess://" + rutafile;
                ccn = DriverManager.getConnection(Url);
                st = ccn.createStatement();
            } catch (SQLException e) 
                {
                    //si la coneccion falla salta un mensaje con la exeption que ocurre
                    JOptionPane.showMessageDialog(null, "CONEXION ERRONEA " + e);  
                }
    }
    
    public Connection getConnection(){//regresa el estado de la coneccion si es estable o no
        return ccn;
    }
    
    public void Desconexion(){
        try 
            {
                ccn.close(); //cancela la conexion          
                System.exit(0);//cierra el programa
            } catch (SQLException ex) 
                {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}
