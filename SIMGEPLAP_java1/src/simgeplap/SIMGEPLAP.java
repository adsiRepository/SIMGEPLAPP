
package simgeplap;

import Vistas.*;

public class SIMGEPLAP {
    
    public static String codUser;
    public static String user_session;
    public static String mail_user;
    public static String time_access;
    public static String rol_user;
    
    public static Portal puerta;
    
    public static double lecT = 0.0, lecP = 0.0, lecN = 0.0;
    
    public static int minT = 88;
    public static int maxT = 95;
    public static int minN = 35;
    public static int maxN = 55;
    public static int minF = 45;
    public static int maxF = 65;
    
    public static int rangosIniciales[] = {88,95,35,55,45,65};
    
    //public static Acceso ingreso;
    
    public static void main( String[] args ) {
        
        //entrada a loggeo
        /*ingreso = new Acceso();
        ingreso.setVisible( true );*/
        
        //métodos opcionales, hacerlos comentario cuando no operen.
        Menu f = new Menu();
        f.setVisible(true);
        
        //new SplashBienvenida().animar(f);
        
        //entrada por el portal de bienvenida
        /*puerta = new Portal();
        puerta.setVisible(true);*/
        //-----------------------
    
    }

}
