
package Otros;

import Recursos.ConexionBD;
import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.*;


public class EnvioAlerta{//extends Thread
    
    //private String user;
    //private int lec;
    private Connection bd;
    private Calendar time;
    
    //Constructor       -------------//
    public EnvioAlerta(){
        this.bd = ConexionBD.GetConnection();
        //this.lec = lec;
        //this.user = user;
        this.time = new GregorianCalendar();
    }
    //-------------------------------//
    
    public void SaveAlert( int lec, String user ){
        
        int año = time.get( Calendar.YEAR );
        int mes = time.get( Calendar.MONTH ) + 1;
        int dia = time.get( Calendar.DAY_OF_MONTH );
        int hr = time.get( Calendar.HOUR_OF_DAY );
        int mn = time.get( Calendar.MINUTE );
        int sc = time.get( Calendar.SECOND );
        
        try{
            String date = ""+año+"-"+mes+"-"+dia;
            String hour = ""+hr+":"+mn+":"+sc;
            Statement qst = bd.createStatement();
            qst.execute( "insert into incidentes (usuario, lectura, fecha, hora) values ('"+user+"','"+lec+"','"+date+"','"+hour+"')" );
            //JOptionPane.showMessageDialog( null, "Registro guardado en la Base de Datos", "SaveAlert", JOptionPane.INFORMATION_MESSAGE );
        }
        catch( SQLException e ){
            JOptionPane.showMessageDialog( null, "Error en la Base de Datos" + e, "SaveAlert", JOptionPane.ERROR_MESSAGE );
        }
        
    }
    
}
