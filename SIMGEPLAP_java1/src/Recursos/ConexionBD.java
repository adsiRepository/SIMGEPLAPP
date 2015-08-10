
package Recursos;

import java.sql.Connection; //LIBRERIA DE CONEXION ConexionBD
import java.sql.DriverManager; //DRIVER DE MANEJO DE DATOS
import java.sql.SQLException; //OMITIR O ENVIAR MENSAJES DE ERROR SQL
import javax.swing.JOptionPane;     

public class ConexionBD {
    
    public static Connection GetConnection(){
        
        Connection cnx = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");//Libreria "Mysql Connector" que hay que descargar y añadir al proyecto
            String host = "jdbc:mysql://localhost/simgeplapp";//editar esta cadena según la configuración del localhost y el nombre de la base de datos
            //String host = "jdbc:mysql://mysql.hostinger.es/u812060061_simge";
            String user = "root";
            //String user = "u812060061_ads38";
            String pass = "";
            //String pass = "simgeplapp";
            cnx = DriverManager.getConnection( host, user, pass );
        }
        catch( ClassNotFoundException ex ){
            JOptionPane.showMessageDialog( null, "Error Tecnico ConexionBD "+ex.toString(), "BD, ClassNotFound", JOptionPane.ERROR_MESSAGE );
            cnx=null;
        }
        catch( SQLException ex ){
            JOptionPane.showMessageDialog( null, "Clave o usuario erroneos "+ex.toString(), "BD, SQL Exception", JOptionPane.ERROR_MESSAGE );
            cnx = null;
        }
        catch( Exception ex ){
            JOptionPane.showMessageDialog( null, "Error en la Conexión con la BD"+ex.toString(), "BD, Exception", JOptionPane.ERROR_MESSAGE );
            cnx=null;
        }
        finally{
            return cnx;
        }
        
    }
    
}
