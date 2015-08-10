
package Vistas;

import Controlador.Grafos;
import simgeplap.*;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/*
 @author: Grupo de Dasarrollo ADSI3
*/
public class Menu extends javax.swing.JFrame {

    public static RegUsers ventanaNewUser;
    public static GestionItinerario ventanaProgramacion;
    public static ConsolaControl ventanaControl;
    public static Historial ventanaHistorial;
    public static PruebaMovil ventanaMovil;

    /*Temperatura.LectorTemperatura panelT;
    Nivel.LectorNivel panelN;
    Flujo.LectorFlujo panelF;*/

    
    public static MonitorDial monitor;
    
    public static JComponent tbar;
    
    public Menu() {
        super("Menú Principal");
        initComponents();
        
        confBotones();
        
        {//configuracion de este menu
            this.setExtendedState(MAXIMIZED_BOTH);
            this.setTitle("SIMGEPLAP, SENA ADSI-38");
            setIconImage(new ImageIcon(getClass().getResource("../Recursos/Imagenes/senaLog1.png")).getImage());
            //lbl_ActualUser.setText(lbl_ActualUser.getText().concat("\t" + SIMGEPLAP.user_session));
        }
        
        {// instanciacion de las ventanas
            ventanaNewUser = new RegUsers(Escritorio);
            ventanaProgramacion = new GestionItinerario(Escritorio);
            ventanaControl = new ConsolaControl(Escritorio);
            ventanaHistorial = new Historial(Escritorio);
            ventanaMovil = new PruebaMovil();
        }
       
        monitor = new MonitorDial(Escritorio);
        Escritorio.add(monitor);
        monitor.setVisible(true);
        monitor.toFront();
        
        
        /*{
            panelT = new Temperatura.LectorTemperatura();
            panelN = new Nivel.LectorNivel();
            panelF = new Flujo.LectorFlujo();
        }*/
        
        /*{
            ((BasicInternalFrameUI) frame1.getUI()).setNorthPane(null);
            frame1.setClosable(false);
            frame1.setTitle("Temperatura");
            frame1.setContentPane(panelT);
            //new Thread(panelT).start();

            ((BasicInternalFrameUI) frame2.getUI()).setNorthPane(null);
            frame2.setContentPane(panelN);
            frame2.setTitle("Niveles");
            frame2.setClosable(false);
            //new Thread(panelN).start();

            ((BasicInternalFrameUI) frame3.getUI()).setNorthPane(null);
            frame3.setContentPane(panelF);
            frame3.setClosable(false);
            frame3.setTitle("Flujo");
            //new Thread(panelF).start();
        }*/
        
      //###########  ENTORNO SEGUN USUARIO  ###############//
            
        /*    if (!(SIMGEPLAP.rol_user.equals("Administrador"))) {
            btnNewUser_.setVisible(false);
            }
            
            if (SIMGEPLAP.rol_user.equals("Control Tecnico") || SIMGEPLAP.rol_user.equals("Aprendiz")) {
            btnControlItinerario_.setVisible(false);
            }
         */   
        //##################################################//

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Escritorio = new Grafos.Escritorio();//new javax.swing.JDesktopPane();
        lbl_ActualUser = new javax.swing.JLabel();
        btnControlItinerario_ = new Grafos.MoveButton(Escritorio);//new javax.swing.JButton();
        btnNewUser_ = new javax.swing.JButton();
        btnConsultas_ = new javax.swing.JButton();
        btnConfigVariables_ = new javax.swing.JButton();
        txtstt = new javax.swing.JTextField();
        btn_prueba_movil_ = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Escritorio.setBackground(new java.awt.Color(102, 102, 0));
        Escritorio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbl_ActualUser.setBackground(new java.awt.Color(223, 141, 30));
        lbl_ActualUser.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lbl_ActualUser.setText("Usuario: ");
        lbl_ActualUser.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_ActualUser.setEnabled(false);
        lbl_ActualUser.setFocusable(false);
        lbl_ActualUser.setOpaque(true);

        btnControlItinerario_.setText("Control Itinerario");
        btnControlItinerario_.setMaximumSize(new java.awt.Dimension(200, 80));
        btnControlItinerario_.setMinimumSize(new java.awt.Dimension(200, 80));
        btnControlItinerario_.setPreferredSize(new java.awt.Dimension(200, 80));
        btnControlItinerario_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnControlItinerario_ActionPerformed(evt);
            }
        });

        btnNewUser_.setText("Registro de Usuarios");
        btnNewUser_.setMaximumSize(new java.awt.Dimension(200, 80));
        btnNewUser_.setMinimumSize(new java.awt.Dimension(200, 80));
        btnNewUser_.setPreferredSize(new java.awt.Dimension(200, 80));
        btnNewUser_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewUser_ActionPerformed(evt);
            }
        });

        btnConsultas_.setText("Consultar Incidencias");
        btnConsultas_.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConsultas_.setMaximumSize(new java.awt.Dimension(200, 80));
        btnConsultas_.setMinimumSize(new java.awt.Dimension(200, 80));
        btnConsultas_.setPreferredSize(new java.awt.Dimension(200, 80));
        btnConsultas_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultas_ActionPerformed(evt);
            }
        });

        btnConfigVariables_.setText("Configuración de Parámetros");
        btnConfigVariables_.setMaximumSize(new java.awt.Dimension(200, 80));
        btnConfigVariables_.setMinimumSize(new java.awt.Dimension(200, 80));
        btnConfigVariables_.setPreferredSize(new java.awt.Dimension(200, 80));
        btnConfigVariables_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigVariables_ActionPerformed(evt);
            }
        });

        btn_prueba_movil_.setText("Prueba Movil");
        btn_prueba_movil_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prueba_movil_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EscritorioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnControlItinerario_, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfigVariables_, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(534, 534, 534))
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(178, 178, 178)
                .addComponent(txtstt, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(302, 302, 302)
                .addComponent(lbl_ActualUser, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(btn_prueba_movil_, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(btnNewUser_, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConsultas_, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(266, 266, 266))
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_ActualUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtstt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(btnControlItinerario_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EscritorioLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNewUser_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnConsultas_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(EscritorioLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(btn_prueba_movil_, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(110, 110, 110)
                .addComponent(btnConfigVariables_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Escritorio.setLayer(lbl_ActualUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(btnControlItinerario_, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(btnNewUser_, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(btnConsultas_, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(btnConfigVariables_, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(txtstt, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(btn_prueba_movil_, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewUser_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewUser_ActionPerformed

        if (ventanaNewUser.isVisible()){
            ventanaNewUser.toFront();
            if (ventanaNewUser.isIcon()){
                try {
                    ventanaNewUser.setIcon(false);
                } catch (PropertyVetoException ex) {
                    System.out.printf(ex.getLocalizedMessage());
                }
            }
        }
        else{
            Escritorio.add(ventanaNewUser);
            ventanaNewUser.setVisible(true);
            ventanaNewUser.toFront();
        }
        
    }//GEN-LAST:event_btnNewUser_ActionPerformed

    private void btnConsultas_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultas_ActionPerformed

        if (ventanaHistorial.isVisible()){
            ventanaHistorial.toFront();
            if (ventanaHistorial.isIcon()){
                try {
                    ventanaHistorial.setIcon(false);
                } catch (PropertyVetoException ex) {
                    System.out.printf(ex.getLocalizedMessage());
                }
            }
        }
        else{
            Escritorio.add(ventanaHistorial);
            ventanaHistorial.setVisible(true);
            ventanaHistorial.toFront();
        }
        
    }//GEN-LAST:event_btnConsultas_ActionPerformed

    private void btnControlItinerario_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnControlItinerario_ActionPerformed
        
       /* if (ventanaProgramacion.isVisible()){
            ventanaProgramacion.toFront();
            if (ventanaProgramacion.isIcon()){
                try {
                    ventanaProgramacion.setIcon(false);
                } catch (PropertyVetoException ex) {
                    System.out.printf(ex.getLocalizedMessage());
                }
            }
        }
        else{
            Escritorio.add(ventanaProgramacion);
            ventanaProgramacion.setVisible(true);
            ventanaProgramacion.toFront();
        }*/
        
    }//GEN-LAST:event_btnControlItinerario_ActionPerformed

    private void btnConfigVariables_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfigVariables_ActionPerformed
        
        if (ventanaControl.isVisible()){
            ventanaControl.toFront();
            if (ventanaControl.isIcon()){
                try {
                    ventanaControl.setIcon(false);
                } catch (PropertyVetoException ex) {
                    System.out.printf(ex.getLocalizedMessage());
                }
            }
        }
        else{
            Escritorio.add(ventanaControl);
            ventanaControl.setVisible(true);
            ventanaControl.toFront();
        }
        
    }//GEN-LAST:event_btnConfigVariables_ActionPerformed

    private void btn_prueba_movil_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prueba_movil_ActionPerformed
        
        
        if (ventanaMovil.isVisible()){
            ventanaMovil.toFront();
            if (ventanaMovil.isIcon()){
                try {
                    ventanaMovil.setIcon(false);
                } catch (PropertyVetoException ex) {
                    System.out.printf(ex.getLocalizedMessage());
                }
            }
        }
        else{
            Escritorio.add(ventanaMovil);
            ventanaMovil.setVisible(true);
            ventanaMovil.toFront();
        }
        
        
    }//GEN-LAST:event_btn_prueba_movil_ActionPerformed

    
    private void confBotones(){
        
        {
            btnNewUser_.setBackground(new java.awt.Color(0, 153, 0));
            btnNewUser_.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
            btnNewUser_.setForeground(new java.awt.Color(255, 255, 255));
            btnNewUser_.setText("Registro de Usuarios");
            btnNewUser_.setMaximumSize(new java.awt.Dimension(260, 82));
            btnNewUser_.setMinimumSize(new java.awt.Dimension(260, 82));
            btnNewUser_.setPreferredSize(new java.awt.Dimension(260, 82));
            
            
            btnConfigVariables_.setBackground(new java.awt.Color(0, 153, 0));
            btnConfigVariables_.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
            btnConfigVariables_.setForeground(new java.awt.Color(255, 255, 255));
            btnConfigVariables_.setText("Parametros de Procesos");
            btnConfigVariables_.setMaximumSize(new java.awt.Dimension(260, 82));
            btnConfigVariables_.setMinimumSize(new java.awt.Dimension(260, 82));
            btnConfigVariables_.setPreferredSize(new java.awt.Dimension(260, 82));


            btnControlItinerario_.setBackground(new java.awt.Color(0, 153, 0));
            btnControlItinerario_.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
            btnControlItinerario_.setForeground(new java.awt.Color(255, 255, 255));
            btnControlItinerario_.setText("Accesos al Sistema");
            btnControlItinerario_.setMaximumSize(new java.awt.Dimension(260, 82));
            btnControlItinerario_.setMinimumSize(new java.awt.Dimension(260, 82));
            btnControlItinerario_.setPreferredSize(new java.awt.Dimension(260, 82));


            btnConsultas_.setBackground(new java.awt.Color(0, 153, 0));
            btnConsultas_.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
            btnConsultas_.setForeground(new java.awt.Color(255, 255, 255));
            btnConsultas_.setText("Consultas y Registros");
            btnConsultas_.setMaximumSize(new java.awt.Dimension(260, 82));
            btnConsultas_.setMinimumSize(new java.awt.Dimension(260, 82));
            btnConsultas_.setPreferredSize(new java.awt.Dimension(260, 82));
            
            lbl_ActualUser.setBackground(new java.awt.Color(51, 255, 0));
            lbl_ActualUser.setFont(new java.awt.Font("Impact", 0, 18));
            
        }
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
//Create and display the form 
            java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Menu().setVisible(true);
            }
        });
    } 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane Escritorio;
    private javax.swing.JButton btnConfigVariables_;
    private javax.swing.JButton btnConsultas_;
    public static javax.swing.JButton btnControlItinerario_;
    private javax.swing.JButton btnNewUser_;
    private javax.swing.JButton btn_prueba_movil_;
    private javax.swing.JLabel lbl_ActualUser;
    public static javax.swing.JTextField txtstt;
    // End of variables declaration//GEN-END:variables
}
