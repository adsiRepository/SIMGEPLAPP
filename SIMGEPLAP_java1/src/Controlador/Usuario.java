
package Controlador;

import Recursos.*;
import Vistas.*;
import simgeplap.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import static simgeplap.SIMGEPLAP.puerta;


public class Usuario { // Clase Usuario_original, en ella estará configurado todo lo que concierne al manejo del usuario en el programa
    
    private static Connection bd = ConexionBD.GetConnection();
    public static Menu menu;
    
    public String  id, name, apes, nick, pass, email, tel, rol; // 
    
    public Usuario( String id, String name, String apellidos, String nick, String pass, String email, String telefono, String rol ){
        this.id = id;
        this.name = name;
        this.apes = apellidos;
        this.nick = nick;
        this.pass = pass;
        this.email = email;
        this.tel = telefono;
        this.rol = rol;
    }
    
    public Usuario(){ // 
    }

    private static String JOPContraseña() {
        JPasswordField campoContraseña = new JPasswordField();
        Object obj[] = {"Contraseña:" + "\n", campoContraseña};
        Object[] opciones = {"Aceptar", "Cancelar"};
                                                                                                                  
        int op = JOptionPane.showOptionDialog(puerta, obj, "Contraseña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, obj);
        //parámetros del JOP showOptionDialog -->>
        //JOptionPane.showOptionDialog(componente, dialogo, titulo, OpcionesTipo, TipoMensaje, icono, ArrayOpcionesRespuesta, opcionesEnDialogo);
        //######################################//
        if ( op == 0 ){
            String password = new String(campoContraseña.getPassword());
            String passEncrypt = Crypt.encryptMD5(password);
            return passEncrypt;
        }else{
            return null;
        }
    }
    
    
    
    @SuppressWarnings("UseSpecificCatch")
    public static void ValidarIngreso(String user, String pass) {
        try {
            String passCrypted = Crypt.encryptMD5(pass);
            
            if (user != null && pass != null) {

                String sentence = "select * from usuarios where nick_user='" + user + "'";
                Statement query = bd.createStatement();
                ResultSet HayUsuario = query.executeQuery(sentence);

                if (HayUsuario.next()) {

//#############         Validacion de disponibilidad en la programacion  ###########################################################//
                    // aqui verifica por el código del usuario si este está incluido en la programación
                    String solicitud = "select * from itinerario where iti_codUser='"+ HayUsuario.getString("codUser") + "'";
                    Statement quest = bd.createStatement();
                    ResultSet programado = quest.executeQuery(solicitud);

                    if (programado.next()) {

                        //formateo las variables que permitiran comparar las fechas
                        GregorianCalendar prog = new GregorianCalendar();
                        prog.setTime(Time.formatofecha.parse(programado.getString("fechaok")));
                        String ahora = Time.formatofecha.format(Time.hoy);
                        GregorianCalendar loggeo = new GregorianCalendar();
                        loggeo.setTime(Time.formatofecha.parse(ahora));
               //####################################################################################3

                        //  VERIFICACION DE FECHAS 
                        if (loggeo.before(prog) || loggeo.after(prog)) { // si el momento en el que estas entrando no es el de la fecha en la tabla, no podra entrar 
                            // pero si es administrador sí podra acceder
                            if (HayUsuario.getString("rol").equals("Administrador")) {
                                
                                if (HayUsuario.getString("clave_ingreso").equals(passCrypted)) {
                                    SIMGEPLAP.codUser = HayUsuario.getString("codUser");
                                    SIMGEPLAP.user_session = HayUsuario.getString("nombres");
                                    SIMGEPLAP.mail_user = HayUsuario.getString("email");
                                    SIMGEPLAP.rol_user = HayUsuario.getString("rol");
                                    menu = new Menu();
                                    SIMGEPLAP.puerta.dispose();
                                    Portal.login.dispose();
                                    menu.setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(puerta, "Has escribido mal tus datos");
                                }

                            } else { // sino es adminnistrador o Control tEcnico
                                JOptionPane.showMessageDialog(puerta, "No te es permitido acceder en este Momento");
                            }

                        } else {  // en este caso si está dentro de las fechas de acceso, entonces le pide la contraseña

                            if (HayUsuario.getString("clave_ingreso").equals(passCrypted)) {
                                SIMGEPLAP.codUser = HayUsuario.getString("codUser");
                                SIMGEPLAP.user_session = HayUsuario.getString("nombres");
                                SIMGEPLAP.mail_user = HayUsuario.getString("email");
                                SIMGEPLAP.rol_user = HayUsuario.getString("rol");
                                menu = new Menu();
                                SIMGEPLAP.puerta.dispose();
                                Portal.login.dispose();
                                menu.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(puerta, "Has escribido mal tus datos");
                            }

                            //##################$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$44
                        }

                    } else { // sino, si no está programado verifique si es Administrador o Control Técnico

                        if (HayUsuario.getString("rol").equals("Administrador") || HayUsuario.getString("rol").equals("Control Tecnico")) {

                            if (HayUsuario.getString("clave_ingreso").equals(passCrypted)) {
                                SIMGEPLAP.codUser = HayUsuario.getString("codUser");
                                SIMGEPLAP.user_session = HayUsuario.getString("nombres");
                                SIMGEPLAP.mail_user = HayUsuario.getString("email");
                                SIMGEPLAP.rol_user = HayUsuario.getString("rol");
                                menu = new Menu();
                                SIMGEPLAP.puerta.dispose();
                                Portal.login.dispose();
                                menu.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(puerta, "Has escribido mal tus datos");
                            }

                        } else { // No está programado ni es Administrador ni Control
                            JOptionPane.showMessageDialog(puerta, "No tienes ningun acceso programado en este momento, \n "
                                    + "debes solicitar una programación de acceso");
                        }

                    }
                    // Sí en definitiva el usuario No existe ---<>
                } else {
                    JOptionPane.showMessageDialog(puerta, "Usuario no Válido", "Usuario, Validación", 0);
                }
                //########################################################################################################################################################//
                bd.close();
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(puerta, "Error: \n" + ex, "Usuario, Validación", 0);
        }

    }
 
}
