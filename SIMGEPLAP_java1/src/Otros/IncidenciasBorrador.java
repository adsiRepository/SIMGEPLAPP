
package Otros;

/*
import Controlador.Incidencias;
import static Controlador.Incidencias.benderClose;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.UNINITIALIZED_VALUE;
*/


public class IncidenciasBorrador {
    
                //   OPCIONES DENTRO DEL THREAD  ---->

    //      BLOQUES DE INTENTOS DE CODIGO DENTRO DEL HILO 
  
    //####################################################################################################################################################################3   
        
//####################################################################################################################################################################3             // intento con el hilo y en while
      
                    
                   /* 
                    while ( avisoEnvio.getValue() == UNINITIALIZED_VALUE ) {
                        avisoEnvio.setMessage(aviso + i);
                        if (i == 0) {
                            bender.keyPress(KeyEvent.VK_ESCAPE);
                            avisoEnvio.setValue(-1);
                            i = 10;
                        } else {
                            i--;
                            Thread.sleep(1000);
                        }
                    }
                    */ 
    
 //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||| //############################################################# ################################      
                    
                    
//###################################### CON TIMER DENTRO DEL HILO ###################################################################################################3
                    //TImer
     /*  Timer timer;
        TimerTask job;

        timer = new Timer();

        job = new TimerTask() {

            int i = 10;

            @Override
            public void run() {
                try {
                    if ( avisoEnvio.getValue() == UNINITIALIZED_VALUE ) {
                        avisoEnvio.setMessage(aviso + i);
                        i--;
                    } else {
                        //answ = (int) avisoEnvio.getValue();
                        timer.cancel();
                    }
                    if ( i == 0 ) {
                        bender.keyPress(KeyEvent.VK_ESCAPE); // 27 valor entero que arroja la tecla esc
                        //answ = (int) avisoEnvio.getValue();
                        //widw.setVisible(false);
                    }
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (UnsupportedOperationException e) {
                    JOptionPane.showMessageDialog(null, "Error:\n" + e, "Temp, void CountToSend", 0);
                }
            }
        };
        
        timer.schedule(job, 0, 1000);
                    */
 //####################################################################################################################################################################3          

 //#############################################################      ========================================================00    
                    
                 /*   while ( c > -1 ) {
                        if ( avisoEnvio.getValue() == UNINITIALIZED_VALUE ) {  //   dialogAviso.isVisible()  avisoEnvio.getValue() == UNINITIALIZED_VALUE
                            //  por ifs
                            if (c == 0) {
                                bender.keyPress(KeyEvent.VK_ESCAPE);
                                //c = 10;
                                //remaining.interrupt();
                            } else {
                                avisoEnvio.setMessage(aviso + c);
                            }
                        } else { // if (!dialogAviso.isAlwaysOnTop()) 
                            /*if (c == 0) {
                                dialogAviso.setVisible(false);
                                answ = -1;          
                            }*/
                            //remaining.interrupt();
                    //        c = -1;
               //        }
               //         c--;
                     
                    /*  // por switch
                     switch ( c ){
                     case 0: 
                     {
                     if ( dialogAviso.isVisible() ) {
                     //jopMsg.setVisible( false );
                     bender.keyPress( KeyEvent.VK_ESCAPE );
                     }
                     break;
                     }
                     /*case 10:
                     {
                     avisoEnvio.setMessage( aviso + c );
                     break;
                     }/
                     default:
                     {
                     avisoEnvio.setMessage( aviso + c );
                     }
                     } 
                     Thread.sleep(1000);
                     }*/
 //####################################################################################################################################################################        
  
 //(((((((((((((((((((())))))))))))))))))))))))))))))))))))))))())))))))))))))()(((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((()())))))))))))))))))))))))))))9
    
 
    //OPCIONES DE DAILOGO DE ESPERA
          /*
        try {
        //Thread.sleep(2000);
        int i = 10;
            while (i >= 0) {
                lblclock.setText( i + " segundos" );
                if (i == 0) {
                    lblwrnsend.setVisible( false );
                    lblclock.setVisible( false );
                    bender.keyPress(KeyEvent.VK_ESCAPE);
                }
                else{
                    i--;
                    Thread.sleep(1000);
                }
            }
        } catch ( InterruptedException ex ) {
            JOptionPane.showMessageDialog( null, " Error de Ejecución \n Detalle: " + ex, "Temp, WaitToClose", 0 );
            //Logger.getLogger(Automatic.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    
   
    // Función inservible  ---<> 
 /*   public static class JOptionAviso extends JOptionPane implements Runnable {
        
        JDialog avs;
        String cartel = "Se enviará una alerta informativa al correo Electrónico. Deseas hacerlo? \n Se enviará automáticamente en: ";
        String variable;
        
        public JOptionAviso(String detallePanel){
            Dimension area = new Dimension();
            //area.setSize( (this.getRootPane().getSize().width/3)*2, (this.getRootPane().getSize().height/5)*3.5 );
            area.setSize( 400, 130 );
            this.setMinimumSize(area);
            this.setPreferredSize(area);
            variable = detallePanel;
            avs = this.createDialog(variable);
            this.setMessageType(JOptionPane.WARNING_MESSAGE);
            this.setOptionType(JOptionPane.OK_CANCEL_OPTION);
            avs.setVisible(true);
            this.setMessage(cartel);
        }
        
        @Override
        public void run() {
            
            
            try {
                
                //Robot bender = new java.awt.Robot();
                
                try {
                    
                    int c = 10;
                    //#####################    do while     ###################
                    //while( getValue() == UNINITIALIZED_VALUE ){
                    do{
                        
                        //this.setMessage(cartel + c );
                        /*
                        switch ((int) getValue()) {
                                case -1:
                                    JOptionPane.showMessageDialog(null, "No elegiste nada", "Alerta!", -1);
                                    Thread.sleep(2000);
                                    bender.keyPress( KeyEvent.VK_ESCAPE );
                                break;
                                case 0:
                                    JOptionPane.showMessageDialog(null, "Aceptaste", "Alerta!", -1);
                                    Thread.sleep(2000);
                                    bender.keyPress( KeyEvent.VK_ESCAPE );
                                break;
                                case 2:
                                    JOptionPane.showMessageDialog(null, "Cancelaste", "Alerta!", -1);
                                    Thread.sleep(2000);
                                    bender.keyPress( KeyEvent.VK_ESCAPE );
                                break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Cerraste", "Alerta!", -1);
                                    Thread.sleep(2000);
                                    bender.keyPress( KeyEvent.VK_ESCAPE );
                                break;
                            }
                        
                        if (c == 0) {
                            
                            //bender.keyPress( KeyEvent.VK_ESCAPE );
                            
                            benderClose();
                            
                            setValue(-1);
                            
                            this.setVisible(false);
                            
                            
                            c = 10;
                            
                        } else {
                            c--;
                            Thread.sleep(1000);
                        }
                    } while( this.getValue() == UNINITIALIZED_VALUE );
                    
                    //#############################################################
                    
                    Incidencias.Decidiendo(this);
                    
                    
                } catch ( HeadlessException | InterruptedException em ) { //InterruptedException | NullPointer
                    JOptionPane.showMessageDialog(null, "Error Crono \n" + em.getLocalizedMessage(), "public static class JOptionAviso", 0);
                }
                
                     } catch (AWTException ex) {
                Logger.getLogger(Incidencias.class.getName()).log(Level.SEVERE, null, ex);
            }

            
        }
        
      
        
        
    }
   */ 
    
    
    
     
    /*
    public static void Decidiendo() {
        
        Thread controlAvisoConfirmacion = new Thread(){
            @Override
            public void run(){
                
            }
        };

        if (Incidencias.avisoEnvio.getValue() == Object.class.cast(-1)) { // ejemplo de como iba -> (int) Incidencias.avisoEnvio.getValue() == -1
            JOptionPane.showMessageDialog(null, "eres un pendejo que no elegiste", "Alerta!", -1);
            
            //Save();
        } else if (Incidencias.avisoEnvio.getValue() == Object.class.cast(0)) {
            JOptionPane.showMessageDialog(null, "aceptaste como toda una puta", "Alerta!", -1);
            //Save();
        } else if (Incidencias.avisoEnvio.getValue() == Object.class.cast(2)) {
            JOptionPane.showMessageDialog(null, "igual, suerte ", "Alerta!", -1);
            //Thread.sleep(1000);
        } else {
            JOptionPane.showMessageDialog(null, "salida forzada", "Alerta!", -1);
        }
 
    } */
    
    
    
    
}
