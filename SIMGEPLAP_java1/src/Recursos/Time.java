
package Recursos;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.*;


public class Time {
    
    public static int año, mes, dia, hora, min, sec;
    public static String date, hour;
    public static Date hoy = new Date();
    public static SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");

    
    public Time(){
        Calendar fecha = new GregorianCalendar();
        
        this.año = fecha.get(Calendar.YEAR);
        this.mes = fecha.get(Calendar.MONTH);
        this.dia = fecha.get(Calendar.DAY_OF_MONTH);
        this.hora = fecha.get(Calendar.HOUR_OF_DAY);
        this.min = fecha.get(Calendar.MINUTE);
        this.sec = fecha.get(Calendar.SECOND);
        this.date = ""+ año + "-" + ( mes + 1 ) + "-" + dia ;
        this.hour = "" + hora + ":" + min + ":" + sec ; //Hora Actual: %02d:%02d:%02d %n
    }
    
    /*
    public static String Hoy(){
        hoy =
    }
    */
    
    public String getHour(){
        return hour;
    }
    
    public String getDate(){
        return date;
    }
    
}
