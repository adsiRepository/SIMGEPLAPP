
package Controlador;

import Recursos.*;
import Vistas.Menu;
import Vistas.MonitorDial;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.UNINITIALIZED_VALUE;
import simgeplap.*;

public class Incidencias {

    private static Time time = new Time();

    static String aviso = "Se enviará una alerta informativa al correo Electrónico. Deseas hacerlo? \n Se enviará automáticamente en: ";

    //  JOptionPane de Aviso
    public static JOptionPane avisoEnvio = new JOptionPane("", JOptionPane.INFORMATION_MESSAGE);
    public static JDialog dialogAviso;
    

    //Constructor       -------------//
    public Incidencias() {
        Incidencias.time = new Time();
    }
    //-------------------------------//
    
    

    //          Mensaje de Espera para Enviar la Alerta  //#####################################################################################################
    public static void WaitToSend(String DialogoRespectivo, String mensaje, String variable, int lectura) throws Exception {

        Dimension area = new Dimension();
        area.setSize(600, 100);
        avisoEnvio.setMinimumSize(area);
        avisoEnvio.setPreferredSize(area);

        //avisoEnvio.setValue(-2);
        
        avisoEnvio.setOptionType(JOptionPane.OK_CANCEL_OPTION);

        dialogAviso = avisoEnvio.createDialog(DialogoRespectivo);

        Robot bender = new java.awt.Robot();

        Thread t_rest = new Thread() {

            @Override
            @SuppressWarnings("UseSpecificCatch")
            public void run() {
                try {
                    int c = 10;
                    //#####################    do while     ###################

                    //while (avisoEnvio.getValue() == UNINITIALIZED_VALUE){
                                        do {
                        try {
                            avisoEnvio.setMessage(aviso + c);
                            if (c == 0) {
                                bender.keyPress(KeyEvent.VK_ESCAPE);
                                avisoEnvio.setValue(-1);
                                c = 10;
                            } else {
                                c--;
                                Thread.sleep(1000);
                            }

                            if (avisoEnvio.getValue() == null) {
                                avisoEnvio.setValue(5);
                            }
                        } catch (Exception epa) {
                            JOptionPane.showMessageDialog(null, "Error \n" + epa.getLocalizedMessage(), "Ciclo do_while", 0);
                        }

                    } while (avisoEnvio.getValue().equals(UNINITIALIZED_VALUE.toString()));

                    //}while( Integer.parseInt((String) avisoEnvio.getValue()) == -2 );

                    //#############################################################   
                    
                    // Después decida qué hacer según el Valor que se haya elegido en el JOptionPane
                    if (avisoEnvio.getValue() == Object.class.cast(-1) || avisoEnvio.getValue() == Object.class.cast(0)) { // es -1 cuando no se elige nada; es 0 cuando das Aceptar

                        if (Menu.monitor.isIcon()) {
                            try {
                                Menu.monitor.setIcon(false);
                            } catch (PropertyVetoException ex) {
                                System.out.printf(ex.getLocalizedMessage());
                            }
                        }
                        
                        MonitorDial.lbl_loading.setVisible(true);
                        
                        for (int i = 1; i <= 10; i++){
                            
                            if(i % 3 == 0){
                                MonitorDial.lbl_loading.setText("Wait");
                            }
                            else{
                                MonitorDial.lbl_loading.setText(MonitorDial.lbl_loading.getText().concat("."));
                            }
                            
                            if (i == 4){
                                Email.EnviarMail(SIMGEPLAP.mail_user, mensaje);// el método recibe la dirección de destino y la noticia que enviaráSIMGEPLAP.mail_user
                            }
                            
                            if (i == 7){
                                try {
                                Connection mybd = ConexionBD.GetConnection();
                                Statement quest = mybd.createStatement();
                                quest.execute("insert into incidencias (incd_codUser, lectura, factor, fecha, hora) "
                                + "values ('" + SIMGEPLAP.codUser + "','" + lectura + "','" + variable + "','" + time.getDate() + "','" + time.getHour() + "')"); // SIMGEPLAP.user_session
                                JOptionPane.showMessageDialog(null, "Se ha enviado un mensaje de Alerta", "Sobresalto de Lecturas", 2);
                                } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null, "Error en la Base de Datos" + e, "SaveAlert", 0);
                                }
                            }
                            
                            Thread.sleep(570);
                        }
                        
                        MonitorDial.lbl_loading.setVisible(false);

                    } else if (avisoEnvio.getValue() == Object.class.cast(2)) {

                        //JOptionPane.showMessageDialog(null, "Deseas Guardar el registro en la Base de datos? /n no se enviará alerta", "Cancelar", 3);
                        int confirm = JOptionPane.showConfirmDialog(null, "Deseas Guardar el registro en la Base de datos? \n no se enviará alerta", "Cancelar", JOptionPane.YES_NO_OPTION);

                        if (confirm == 0) {
                            try {
                                Connection bd = ConexionBD.GetConnection();
                                Statement qst = bd.createStatement();
                                qst.execute("insert into incidencias (incd_codUser, lectura, factor, fecha, hora) " //time.getDate()                      //time.getHour()
                                        + "values ('" + SIMGEPLAP.codUser + "','" + lectura + "','" + variable + "','" + time.getDate() + "','" + time.getHour() + "')");
                                
                                JOptionPane.showMessageDialog( null, "Registro guardado en la Base de Datos", "SaveAlertDB", JOptionPane.INFORMATION_MESSAGE );
                                
                            } catch (SQLException e) {
                                JOptionPane.showMessageDialog(null, "Error en la Base de Datos" + e, "SaveAlert", 0);
                            }
                        }
                        
                    }

                } catch (Exception em) { //InterruptedException | NullPointerAWTException
                    JOptionPane.showMessageDialog(null, "Error Crono \n" + em.getLocalizedMessage(), "WaitCloseOption", 0);
                }
            }
        };

        //luego inicializo el Hilo 
        t_rest.start();
        dialogAviso.setVisible(true);
        //---
    }


    // MÉTODO PARA EL REGISTRO DE NUEVO USUARIO
    public static boolean newUser(Usuario user) {// sntnc -> de sentencia
        boolean done = false;
        /*
        Connection bd = ConexionBD.GetConnection();

        String sntnc = "insert into usuarios (codUser, id, nombres, apellidos, nick_user, clave_ingreso, telefono, email, rol)"
                + " values ('"+Crypt.GenerarCodUser()+"','" + user.id + "','" + user.name + "','" + user.apes + "','" + user.nick + "',"
                + "'" + Crypt.encryptMD5(user.pass) + "','" + user.tel + "','" + user.email + "','" + user.rol + "')";
        */
        do {
            try {
                saveUser(user);
                done = true;
            } catch (SQLException e) {
                if (e.getErrorCode() == 1062) {
                    done = false;
                }
                else{
                    JOptionPane.showMessageDialog(null, "No se pudo Guardar el Nuevo Usuario \n" + e.getLocalizedMessage(), "Grabado en la Base de Datos", 0);
                }
            }
        } while (done == false);
        
        return done;
    }
    
    private static void saveUser(Usuario user) throws SQLException{
        try (Connection bd = ConexionBD.GetConnection()) {
            String codUser = Crypt.GenerarCodUser();
            String sntnc = "insert into usuarios (codUser, id, nombres, apellidos, nick_user, clave_ingreso, telefono, email, rol)"
                    + " values ('"+codUser+"','" + user.id + "','" + user.name + "','" + user.apes + "','" + user.nick + "',"
                    + "'" + Crypt.encryptMD5(user.pass) + "','" + user.tel + "','" + user.email + "','" + user.rol + "')";
            try (Statement qst = bd.createStatement()) {
                qst.execute(sntnc);
            }
        }
    }    
    
    
    
        // GUARDADO A LA BASE DE DATOS
    public static void SaveAlertApp(String fact, double lec) {
        try {
            Connection bd = ConexionBD.GetConnection();
            Statement qst = bd.createStatement();
            qst.execute("insert into incidencias (lectura, factor, fecha, hora) "
                    + "values ('" + lec + "','" + fact + "','" + time.getDate() + "','" + time.getHour() + "')");
            //JOptionPane.showMessageDialog( null, "Registro guardado en la Base de Datos", "SaveAlertDB", JOptionPane.INFORMATION_MESSAGE );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos" + e, "SaveAlert", 0);
        }
    }
    
    private static Connection conBD;
    private static Statement sttt;
    
    public static void registrarLectura(double temp, double pres, double niv) {
        try {
            conBD = ConexionBD.GetConnection();
            sttt = conBD.createStatement();
            sttt.execute("update lecturas set actualTemp = "+temp+", actualPresion = "+pres+", actualNivel = "+niv+" where 1");
            //JOptionPane.showMessageDialog( null, "Registro guardado en la Base de Datos", "SaveAlertDB", JOptionPane.INFORMATION_MESSAGE );
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la Base de Datos" + e, "SaveAlert", 0);
        }

    }
    
        //---
    
//########################################################################################################################################################
    
 
    
 /*       
 //   CODIGO A PARTE, SUPLEMENTARIO, no usado en este momento               #######################################################################--->>>> 
    
    @SuppressWarnings("UseSpecificCatch")
    public static void Decidiendo(JOptionPane ventanaOpciones, String mensaje, String variable, int lectura) throws InterruptedException, AWTException {
        
            Robot walle = new Robot();
        if (ventanaOpciones.getValue() == Object.class.cast(-1) || ventanaOpciones.getValue() == Object.class.cast(0)) {
            try {
                lbl_loading.setVisible(true);

                for (int p = 0; p < 10; p++) {

                    if (p == 0) {
                        lbl_loading.setText(lbl_loading.getText().concat("."));
                    } else if (p % 3 == 0) {
                        lbl_loading.setText("Wait");
                    } else {
                        lbl_loading.setText(lbl_loading.getText().concat("."));
                    }

                    if (p == 4) { // envio del correo
                        //#########################//
                        //Email.EnviarMail(SIMGEPLAP.mail_user, mensaje);// el método recibe la dirección de destino y la noticia que enviaráSIMGEPLAP.mail_user
                    }

                    if (p == 6) {
                        //Almacenamiento en Base de Datos
                        try {
                            Connection bd = ConexionBD.GetConnection();
                            Statement qst = bd.createStatement();
                            qst.execute("insert into incidentes (usuario, lectura, factor, fecha, hora) "
                                    + "values ('" + "default" + "','" + lectura + "','" + variable + "','" + time.getDate() + "','" + time.getHour() + "')"); // SIMGEPLAP.user_session
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error en la Base de Datos" + e, "SaveAlert", 0);
                        }
                        //#########################//
                    }
                    Thread.sleep(500);
                }
                
                JOptionPane.showMessageDialog(null, "Se ha enviado un mensaje de Alerta", "Falla de Temperatura", 2);
                
                Thread.sleep(1500);
                walle.keyPress(KeyEvent.VK_ESCAPE);
                
                lbl_loading.setVisible(false);

            } catch ( Exception oh) {
                JOptionPane.showMessageDialog(null, "Error: /n" + oh.getMessage(), "Incidencias, Decidiendo", 0);
            }

        } else if (ventanaOpciones.getValue() == Object.class.cast(2)) {

            //JOptionPane.showMessageDialog(null, "Deseas Guardar el registro en la Base de datos? /n no se enviará alerta", "Cancelar", 3);
            int done = JOptionPane.showConfirmDialog(null, "Deseas Guardar el registro en la Base de datos? \n No se enviará alerta", "Cancelar", JOptionPane.YES_NO_OPTION);
            if (done == 0) {
                try {
                    Connection bd = ConexionBD.GetConnection();
                    Statement qst = bd.createStatement();
                    qst.execute("insert into incidentes (usuario, lectura, factor, fecha, hora) "
                            + "values ('" + "default" + "','" + lectura + "','" + variable + "','" + time.getDate() + "','" + time.getHour() + "')");
                    JOptionPane.showMessageDialog( null, "Registro guardado en la Base de Datos", "SaveAlertDB", JOptionPane.INFORMATION_MESSAGE );
                    
                    Thread.sleep(1500);
                    walle.keyPress(KeyEvent.VK_ESCAPE);
                    
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error en la Base de Datos" + e, "SaveAlert", 0);
                }
            }
            
        }
    }
    
*/


}
