package Otros;

/**
 * @file AppletPasarParametros.java
 * @version 1.1
 * @author Linea de Codigo (http://lineadecodigo.com)
 * @date   7-junio-2007
 * @url  http://lineadecodigo.com/2007/06/07/insertar-datos-con-jdbc/
 * @description Realizar inserciones de datos en una BD mediante INSERT y JDBC.  
 */

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InsertarDatos {

	public static void main(String[] args) {
		
		Connection con = null;
	    PreparedStatement stmt = null;
		

		// Definimos el driver y la url
	    String sDriver = "com.mysql.jdbc.Driver";
	    String sURL = "jdbc:mysql://localhost:3306/myfirstdb";

	    
	    // Cargamos el driver y realizamos la conexi�n
	    try{
		Class.forName(sDriver).newInstance();    
		con = DriverManager.getConnection(sURL,"root","");
		      

		String sISBN = "84-9815-212-7";
		String sTitulo = "Yo, Claudio";
		String sDescripcion="Supuesta autobiograf�a de Claudio";
		String sCategoria = "novela historica";
		int idAutor = 3;
		      
		stmt = con.prepareStatement
                   ("INSERT INTO libros VALUES (?,?,?,?,?)");

	    stmt.setString(1,sISBN);
		stmt.setInt(2,idAutor);
		stmt.setString(3,sTitulo);
		stmt.setString(4,sDescripcion);
		stmt.setString(5,sCategoria);
		      
		int retorno = stmt.executeUpdate();
				      
		System.out.println(retorno);      
		      
	    }catch (SQLException sqle){
                
	    	System.out.println("SQLState: " + sqle.getSQLState());
	    	System.out.println("SQLErrorCode: " + sqle.getErrorCode());
                System.out.println(sqle.getLocalizedMessage());
                if ( sqle.getErrorCode() == 1062 ){
                    System.out.println("Aquí el Aviso");
                }
                
                //sqle.printStackTrace();
	    	
	    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
            
            finally {
	    	if (con != null) {
	    		try{
	  	      		stmt.close();
	  	      		con.close();
	    		} catch(Exception e){
	    			e.printStackTrace();
	    		}
	    	}
	    }		
	}

}
