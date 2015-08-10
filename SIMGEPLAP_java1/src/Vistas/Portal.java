
package Vistas;

import Recursos.ConexionBD;
import Controlador.*;
import Recursos.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author khu8
 */
@SuppressWarnings("serial")
public final class Portal extends javax.swing.JFrame {
    
    //Variables Globales        ##################################################################
    DefaultTableModel dtm = new DefaultTableModel(); 
    
    public static ingresoUsuario login;
    
    //Constructor                   ####################################################################################################################################
    public Portal() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        String columnas[] = {"Usuario","Nombre","Disponibilidad"};
        
        dtm.setColumnIdentifiers( columnas );
        JTableHeader titulotabla = new JTableHeader();
        titulotabla.setTable(itinerario);
        itinerario.setModel(dtm);
        
        MostrarItinerario();
        
    }
//#############################################################################################################################################################
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        deskPortal = new Grafos.MiPortal();//new javax.swing.JDesktopPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        itinerario = new javax.swing.JTable();
        btn_Entrar_ = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        deskPortal.setPreferredSize(new java.awt.Dimension(535, 569));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 0), 5));

        itinerario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        itinerario.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(itinerario);

        btn_Entrar_.setFont(new java.awt.Font("Impact", 0, 18)); // NOI18N
        btn_Entrar_.setForeground(new java.awt.Color(255, 102, 51));
        btn_Entrar_.setText("ENTRAR");
        btn_Entrar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Entrar_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deskPortalLayout = new javax.swing.GroupLayout(deskPortal);
        deskPortal.setLayout(deskPortalLayout);
        deskPortalLayout.setHorizontalGroup(
            deskPortalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deskPortalLayout.createSequentialGroup()
                .addGroup(deskPortalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deskPortalLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(deskPortalLayout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(btn_Entrar_, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        deskPortalLayout.setVerticalGroup(
            deskPortalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deskPortalLayout.createSequentialGroup()
                .addContainerGap(354, Short.MAX_VALUE)
                .addComponent(btn_Entrar_, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        deskPortal.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        deskPortal.setLayer(btn_Entrar_, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(deskPortal, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(deskPortal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Entrar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Entrar_ActionPerformed

        login = new ingresoUsuario();
        login.setVisible(true);
        
    }//GEN-LAST:event_btn_Entrar_ActionPerformed

    public void MostrarItinerario(){
        
        try {
            try (Connection dtbs = ConexionBD.GetConnection()) {
                PreparedStatement qry = dtbs.prepareStatement( "select * from itinerario" );
                ResultSet rspta = qry.executeQuery();
                ResultSetMetaData metadatos = rspta.getMetaData();
                
                ArrayList<Object[]> registros = new ArrayList<>();
                
                while ( rspta.next() ){
                    Object[] fila = new Object[ metadatos.getColumnCount() ];
                    
                    for ( int f = 0; f < fila.length; f++ ){
                        fila[f] = rspta.getObject( f + 1 );
                    }
                    
                    registros.add( fila );
                    
                }
                
                registros.stream().forEach((registro) -> {
                    dtm.addRow( registro );
                });
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    /**
     * @param args the command line arguments
     */
   /* @SuppressWarnings("UseSpecificCatch")
    public static void main(String args[]) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(Portal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        // Create and display the form 
        java.awt.EventQueue.invokeLater(() -> {
            new Portal().setVisible(true);
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Entrar_;
    private javax.swing.JDesktopPane deskPortal;
    private javax.swing.JTable itinerario;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables


    /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

}
