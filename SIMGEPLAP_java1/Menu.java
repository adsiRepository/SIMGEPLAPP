
package Vistas;

import simgeplap.*;
import Controlador.*;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/*
 @author: Grupo de Dasarrollo ADSI38
*/
@SuppressWarnings("serial")
public class Menu extends javax.swing.JFrame {
    
    //Ventanas
    public static RegUsers ventanaNewUser;
    public static GestionItinerario ventanaProgramacion;
    public static ConsolaControl ventanaControl;
    public static Historial ventanaHistorial;
    //################################################################//

  /*
    public static JDesktopPane Escritorio;
    Escritorio.setSize( WIDTH, WIDTH );
    */
    Temperatura.LectorTemperatura panelT;
    Nivel.LectorNivel panelN;
    Flujo.LectorFlujo panelF;

    public Menu() {
        super("Menú Principal");
        initComponents();
        
        {//configuracion de este menu
            this.setExtendedState(MAXIMIZED_BOTH);
            this.setTitle("SIMGEPLAP, SENA ADSI-38");
            setIconImage(new ImageIcon(getClass().getResource("../Recursos/Imagenes/senaLog1.png")).getImage());
            lbl_ActualUser.setText(lbl_ActualUser.getText().concat("\t" + SIMGEPLAP.user_session));
        }
        
        {// instanciacion de las ventanas
            ventanaNewUser = new RegUsers(Escritorio);
            ventanaProgramacion = new GestionItinerario(Escritorio);
            ventanaControl = new ConsolaControl(Escritorio);
            ventanaHistorial = new Historial(Escritorio);
        }
       
        {
            panelT = new Temperatura.LectorTemperatura();
            panelN = new Nivel.LectorNivel();
            panelF = new Flujo.LectorFlujo();
        }
        
        {
            ((BasicInternalFrameUI) frame1.getUI()).setNorthPane(null);
            frame1.setClosable(false);
            frame1.setTitle("Temperatura");
            frame1.setContentPane(panelT);
            new Thread(panelT).start();

            ((BasicInternalFrameUI) frame2.getUI()).setNorthPane(null);
            frame2.setContentPane(panelN);
            frame2.setTitle("Niveles");
            frame2.setClosable(false);
            new Thread(panelN).start();

            ((BasicInternalFrameUI) frame3.getUI()).setNorthPane(null);
            frame3.setContentPane(panelF);
            frame3.setClosable(false);
            frame3.setTitle("Flujo");
            new Thread(panelF).start();
        }
        
        //##################################################//
            
            if (!(SIMGEPLAP.rol_user.equals("Administrador"))) {
            btnNewUser_.setVisible(false);
            }
            
            if (SIMGEPLAP.rol_user.equals("Control Tecnico") || SIMGEPLAP.rol_user.equals("Aprendiz")) {
            btnControlItinerario_.setVisible(false);
            }
            
        //##################################################//

    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Escritorio = new Grafos.Escritorio();//new javax.swing.JDesktopPane();
        lbl_ActualUser = new javax.swing.JLabel();
        btnControlItinerario_ = new javax.swing.JButton();
        btnNewUser_ = new javax.swing.JButton();//new Grafos.MoveButton();
        btnConsultas_ = new javax.swing.JButton();
        btnConfigVariables_ = new javax.swing.JButton();
        frame1 = new javax.swing.JInternalFrame();
        frame3 = new javax.swing.JInternalFrame();
        frame2 = new javax.swing.JInternalFrame();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //Escritorio.getGraphics().drawImage(new Grafos().fondoEscritorio, 0, 0, getWidth(), getHeight(), this);
        Escritorio.setBackground(new java.awt.Color(12, 94, 31));
        Escritorio.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Escritorio.setMinimumSize(new java.awt.Dimension(0, 768));
        Escritorio.setPreferredSize(new java.awt.Dimension(1476, 768));

        lbl_ActualUser.setBackground(new java.awt.Color(51, 255, 0));
        lbl_ActualUser.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        lbl_ActualUser.setText("Usuario: ");
        lbl_ActualUser.setEnabled(false);
        lbl_ActualUser.setFocusable(false);
        lbl_ActualUser.setOpaque(true);

        btnControlItinerario_.setBackground(new java.awt.Color(51, 153, 0));
        btnControlItinerario_.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btnControlItinerario_.setForeground(new java.awt.Color(255, 255, 255));
        btnControlItinerario_.setText("Control Itinerario");
        btnControlItinerario_.setMaximumSize(new java.awt.Dimension(260, 82));
        btnControlItinerario_.setMinimumSize(new java.awt.Dimension(260, 82));
        btnControlItinerario_.setPreferredSize(new java.awt.Dimension(260, 82));
        btnControlItinerario_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnControlItinerario_ActionPerformed(evt);
            }
        });

        btnNewUser_.setBackground(new java.awt.Color(0, 153, 0));
        btnNewUser_.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btnNewUser_.setForeground(new java.awt.Color(255, 255, 255));
        btnNewUser_.setText("Registro de Usuarios");
        btnNewUser_.setMaximumSize(new java.awt.Dimension(260, 82));
        btnNewUser_.setMinimumSize(new java.awt.Dimension(260, 82));
        btnNewUser_.setPreferredSize(new java.awt.Dimension(260, 82));
        btnNewUser_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewUser_ActionPerformed(evt);
            }
        });

        btnConsultas_.setBackground(new java.awt.Color(51, 204, 0));
        btnConsultas_.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btnConsultas_.setForeground(new java.awt.Color(255, 255, 255));
        btnConsultas_.setText("Consultar Incidencias");
        btnConsultas_.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnConsultas_.setMaximumSize(new java.awt.Dimension(260, 82));
        btnConsultas_.setMinimumSize(new java.awt.Dimension(260, 82));
        btnConsultas_.setPreferredSize(new java.awt.Dimension(260, 82));
        btnConsultas_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultas_ActionPerformed(evt);
            }
        });

        btnConfigVariables_.setBackground(new java.awt.Color(0, 153, 0));
        btnConfigVariables_.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btnConfigVariables_.setForeground(new java.awt.Color(255, 255, 255));
        btnConfigVariables_.setText("Configuración de Parámetros");
        btnConfigVariables_.setMaximumSize(new java.awt.Dimension(260, 82));
        btnConfigVariables_.setMinimumSize(new java.awt.Dimension(260, 82));
        btnConfigVariables_.setPreferredSize(new java.awt.Dimension(260, 82));
        btnConfigVariables_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfigVariables_ActionPerformed(evt);
            }
        });

        frame1.setBackground(new java.awt.Color(0, 0, 0));
        frame1.setMaximumSize(new java.awt.Dimension(300, 300));
        frame1.setMinimumSize(new java.awt.Dimension(240, 250));
        frame1.setPreferredSize(new java.awt.Dimension(300, 300));
        frame1.setVisible(true);

        javax.swing.GroupLayout frame1Layout = new javax.swing.GroupLayout(frame1.getContentPane());
        frame1.getContentPane().setLayout(frame1Layout);
        frame1Layout.setHorizontalGroup(
            frame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        frame1Layout.setVerticalGroup(
            frame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        frame3.setBackground(new java.awt.Color(0, 0, 0));
        frame3.setMaximumSize(new java.awt.Dimension(300, 300));
        frame3.setMinimumSize(new java.awt.Dimension(240, 250));
        frame3.setPreferredSize(new java.awt.Dimension(300, 300));
        frame3.setVisible(true);

        javax.swing.GroupLayout frame3Layout = new javax.swing.GroupLayout(frame3.getContentPane());
        frame3.getContentPane().setLayout(frame3Layout);
        frame3Layout.setHorizontalGroup(
            frame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        frame3Layout.setVerticalGroup(
            frame3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        frame2.setBackground(new java.awt.Color(0, 0, 0));
        frame2.setMaximumSize(new java.awt.Dimension(300, 300));
        frame2.setMinimumSize(new java.awt.Dimension(240, 250));
        frame2.setPreferredSize(new java.awt.Dimension(300, 300));
        frame2.setVisible(true);

        javax.swing.GroupLayout frame2Layout = new javax.swing.GroupLayout(frame2.getContentPane());
        frame2.getContentPane().setLayout(frame2Layout);
        frame2Layout.setHorizontalGroup(
            frame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        frame2Layout.setVerticalGroup(
            frame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 221, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(1022, 1022, 1022)
                .addComponent(lbl_ActualUser, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(588, 588, 588)
                .addComponent(btnConfigVariables_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(248, 248, 248)
                .addComponent(btnControlItinerario_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(420, 420, 420)
                .addComponent(btnConsultas_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(btnNewUser_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(70, 70, 70)
                .addComponent(frame3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(frame1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(frame2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EscritorioLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(lbl_ActualUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116)
                .addComponent(btnConfigVariables_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EscritorioLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnControlItinerario_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnConsultas_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EscritorioLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnNewUser_, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(frame3, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frame1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frame2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        Escritorio.setLayer(lbl_ActualUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(btnControlItinerario_, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(btnNewUser_, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(btnConsultas_, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(btnConfigVariables_, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(frame1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(frame3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        Escritorio.setLayer(frame2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, 1366, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Escritorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        
        if (ventanaProgramacion.isVisible()){
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
        }

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

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
       /* try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menubr.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form /
            java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Menubr().setVisible(true);
                try {
                    throw new Exception("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "EventQueue", 0);
                }
            }
        /*
            @Override
            public void run(){
                new Menu().setVisible(true);
                while(true){
                   
                }
            }
            /
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JDesktopPane Escritorio;
    private javax.swing.JButton btnConfigVariables_;
    private javax.swing.JButton btnConsultas_;
    private javax.swing.JButton btnControlItinerario_;
    private javax.swing.JButton btnNewUser_;
    private javax.swing.JInternalFrame frame1;
    private javax.swing.JInternalFrame frame2;
    private javax.swing.JInternalFrame frame3;
    private javax.swing.JLabel lbl_ActualUser;
    // End of variables declaration//GEN-END:variables
}
