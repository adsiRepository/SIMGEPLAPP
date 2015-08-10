
//Paquete
package Controlador;

import java.util.*;
import javax.mail.*;
import javax.swing.*;
//import javax.activation.*;
//import javax.mail.Authenticator;
import java.awt.HeadlessException;
//import java.io.IOException;
//import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


                // bibliografia y referencias al final del documento

public class Email {
    
    private static Properties atributos;
    private static final String central = "simgeplap.adsi38@gmail.com";
    private static final String password = "senaadsi";
    private static String contenido;
    private static Session session;
    
    public Email(){
        
    }
    
    public static void EnviarMail( String para, String msg ) throws MessagingException{
        
        //Estos campos definen los Atributos basicos de la conexion al servidor de correo electrónico
        atributos = new Properties();
        atributos.put( "mail.smtp.host", "smtp.gmail.com" );
        atributos.put( "mail.transport.protocol", "smtp" );
        atributos.put( "mail.smtp.auth", "true" );
        atributos.setProperty( "mail.user", central );
        atributos.setProperty( "mail.password", password );
        //configuración de las credenciales para la capa se seguridad de transporte TLS, configuración por defecto -> 
        atributos.setProperty( "mail.smtp.starttls.enable", "true" ); //
        atributos.setProperty( "mail.smtp.ssl.trust", "smtp.gmail.com" ); //
        //los siguientes atributos se deben habilitar para conseguir que funcione en los computadores del Sena ->
        atributos.setProperty( "mail.smtp.port", "465" ); //587 25 465 -> algunas opciones de puerto, por lo general 25 y 465 son los puertos que usa google
        atributos.setProperty( "mail.smtp.ssl.enable", "true" );//
        //##############################//
        // Atributos de prueba, no descomentar ->
        //atributos.setProperty( "mail.smtp.debug", "true" );
        //atributos.setProperty( "mail.smtp.socketFactory.port", "465" );
        //atributos.setProperty( "mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory" );
        //atributos.setProperty( "mail.smtp.socketFactory.fallback", "false" );
        //###############################//
        
        
        
        //Autenticación e inicio de sesión. Como se está utilizando gmail como servidor se debe autenticar una cuenta de este dominio
        session = Session.getInstance( atributos, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication( central, password );//central = simgeplap.adsi38@gmail.com / password= senaadsi
            }
        });
        session.setDebug( true );//esta linea me permite leer los resultados de una manera entendible
        //################################//
        
        // CUERPO DEL MENSAJE
        // Disponible el uso de etiquetas HTML para brindar estructura y diseño
        contenido = "<i><b>Mensaje de Alerta emitido automáticamente por la planta</b></i><br>"
                + "Se han encontrado lecturas anormales en los niveles de estado de la planta. <br>"
                + "Se sugiere realizar una observación detallada. <br>"
                + "<b>"+msg+"</b>";
        //#################################//
        
        try {
        //Estructuración del Mensaje
            //--Formato
            MimeBodyPart cuerpo = new MimeBodyPart();
            cuerpo.setText( contenido );
            cuerpo.setContent( contenido, "text/html" );// esto quiere decir que puedo estructurar el mensaje almacenado en la variable "contenido"  con etiquetas html
            Multipart mpt = new MimeMultipart();
            mpt.addBodyPart( cuerpo );
            //--Datos
            MimeMessage mnsj = new MimeMessage( session );
            mnsj.setFrom( new InternetAddress( central, "SIMGEPLAP ADSI" ) );//linea principal
            mnsj.addRecipient( Message.RecipientType.TO, new InternetAddress( para ) );// aqui recibo la variable que almacena la direccion de email del usuario actual
            mnsj.setSubject( "Alerta SIMGEPLAP" );
            //mnsj.setText( "Peligro revisa la planta" );
            mnsj.setContent( mpt );
            mnsj.setSentDate( new Date() );
            //--Envío
            /*Transport go = session.getTransport( "smtp" );
            go.connect( central, password );
            go.sendMessage( mnsj, mnsj.getAllRecipients() );
            go.close();*/
            Transport.send(mnsj);
            //JOptionPane.showMessageDialog( null, "Email Alertado", "Email, Envío", 1 );
        //##############################
        }
        catch ( MessagingException | HeadlessException ex ) {
            JOptionPane.showMessageDialog( null, "Error de Envio de Correo \n Error: "+ex, "Email: EnviarEmail", 0 );
            //Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        } catch ( UnsupportedEncodingException ex ) {
            JOptionPane.showMessageDialog( null, "UnsupportedEncodingException \n "+ex, "Email: EnviarEmail", 0 );
            //Logger.getLogger(Email.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 
}

/*
BIBLIOGRAFIA

° http://www.linuxhispano.net/2013/10/01/enviar-mails-con-java/


*/
