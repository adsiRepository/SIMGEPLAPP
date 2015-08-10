
package Vistas;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import simgeplap.*;

@SuppressWarnings("serial")
public class ConsolaControl extends javax.swing.JInternalFrame {

    
    public ConsolaControl( JDesktopPane Escritorio ) {
        initComponents();
        
        {
            this.closable = true;
            this.iconable = true;
            this.resizable = false;
            this.maximizable = false;
            this.title = "Configuración de Parámetros";
            this.setFrameIcon(new ImageIcon(getClass().getResource("/Recursos/Imagenes/iconConf.png")));
            Dimension ScreenSpace = Escritorio.getSize(), mySpc = this.getSize();
            this.setLocation( ScreenSpace.width / 8 , ( ScreenSpace.height - mySpc.height ) / 2  );
            this.setToolTipText("Configura los Rangos de las Variables");
        }
        
        {
            txt_minT.setText("" + SIMGEPLAP.minT);
            txt_maxT.setText("" + SIMGEPLAP.maxT);
            txt_minN.setText("" + SIMGEPLAP.minN);
            txt_maxN.setText("" + SIMGEPLAP.maxN);
            txt_minF.setText("" + SIMGEPLAP.minF);
            txt_maxF.setText("" + SIMGEPLAP.maxF);
        }
       
       
    }
   
    private void noLetras(JTextField campo, KeyEvent ev){
        if(campo.getText().length() <= 3){
            char typing = ev.getKeyChar();
            if((typing < '0' || typing > '9')){
                ev.consume();
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "\trango muy alto", "Cuidado", 0);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txt_minT = new javax.swing.JTextField();
        txt_maxT = new javax.swing.JTextField();
        txt_minN = new javax.swing.JTextField();
        txt_maxN = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_minF = new javax.swing.JTextField();
        txt_maxF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnAceptar_ = new javax.swing.JButton();
        btnReestablecer_ = new javax.swing.JButton();
        lblfondo = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setForeground(new java.awt.Color(204, 204, 204));
        jLabel1.setText("Temperatura:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, -1, -1));

        txt_minT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_minTKeyTyped(evt);
            }
        });
        getContentPane().add(txt_minT, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 61, 34));

        txt_maxT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_maxTKeyTyped(evt);
            }
        });
        getContentPane().add(txt_maxT, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 61, 34));

        txt_minN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_minNKeyTyped(evt);
            }
        });
        getContentPane().add(txt_minN, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 61, 34));

        txt_maxN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_maxNKeyTyped(evt);
            }
        });
        getContentPane().add(txt_maxN, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 250, 61, 34));

        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Nivel:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Flujo:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        txt_minF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_minFKeyTyped(evt);
            }
        });
        getContentPane().add(txt_minF, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 61, 34));

        txt_maxF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_maxFKeyTyped(evt);
            }
        });
        getContentPane().add(txt_maxF, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 61, 34));

        jLabel4.setForeground(new java.awt.Color(204, 204, 204));
        jLabel4.setText("Controlar el Comportamiento de la Planta ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, -1, -1));

        jLabel5.setForeground(new java.awt.Color(204, 204, 204));
        jLabel5.setText("Establecer los Niveles para el proceso Requerido");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel6.setForeground(new java.awt.Color(204, 204, 204));
        jLabel6.setText("min");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, -1, -1));

        jLabel7.setForeground(new java.awt.Color(204, 204, 204));
        jLabel7.setText("max");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 180, -1, -1));

        btnAceptar_.setText("Aceptar");
        btnAceptar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptar_ActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar_, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 95, -1));

        btnReestablecer_.setText("Reestablecer");
        btnReestablecer_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReestablecer_ActionPerformed(evt);
            }
        });
        getContentPane().add(btnReestablecer_, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, -1, -1));

        lblfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/fondoConfiguraciones.png"))); // NOI18N
        lblfondo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(lblfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptar_ActionPerformed
      
        
        if ( !txt_minT.equals("") ){
            SIMGEPLAP.minT = Integer.parseInt(txt_minT.getText());
        }
        if ( !txt_maxT.equals("") ){
            SIMGEPLAP.maxT = Integer.parseInt(txt_maxT.getText());
        }
        if ( !txt_minN.equals("") ){
            SIMGEPLAP.minN = Integer.parseInt(txt_minN.getText());
        }
        if ( !txt_maxN.equals("") ){
            SIMGEPLAP.maxN = Integer.parseInt(txt_maxN.getText());
        }
        if ( !txt_minF.equals("") ){
            SIMGEPLAP.minF = Integer.parseInt(txt_minF.getText());
        }
        if ( !txt_maxF.equals("") ){
            SIMGEPLAP.maxF = Integer.parseInt(txt_maxF.getText());
        }
        
        JOptionPane.showMessageDialog(this, "Se Editado los Rangos Respectivamente", "Mensaje de Confirmación", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
        
    }//GEN-LAST:event_btnAceptar_ActionPerformed

    private void btnReestablecer_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReestablecer_ActionPerformed
        
        SIMGEPLAP.minT = SIMGEPLAP.rangosIniciales[0];
        SIMGEPLAP.maxT = SIMGEPLAP.rangosIniciales[1];
        SIMGEPLAP.minN = SIMGEPLAP.rangosIniciales[2];
        SIMGEPLAP.maxN = SIMGEPLAP.rangosIniciales[3];
        SIMGEPLAP.minF = SIMGEPLAP.rangosIniciales[4];
        SIMGEPLAP.maxF = SIMGEPLAP.rangosIniciales[5];
        
        JOptionPane.showMessageDialog(rootPane, "Valores reestablecidos", "Reestablecer Rangos", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_btnReestablecer_ActionPerformed

    private void txt_minTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_minTKeyTyped

        noLetras(txt_minT, evt);
        
    }//GEN-LAST:event_txt_minTKeyTyped

    private void txt_maxTKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_maxTKeyTyped
       
        noLetras(txt_maxT, evt);
        
    }//GEN-LAST:event_txt_maxTKeyTyped

    private void txt_minNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_minNKeyTyped

        noLetras(txt_minN, evt);

    }//GEN-LAST:event_txt_minNKeyTyped

    private void txt_maxNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_maxNKeyTyped

        noLetras(txt_maxN, evt);

    }//GEN-LAST:event_txt_maxNKeyTyped

    private void txt_minFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_minFKeyTyped

        noLetras(txt_minF, evt);

    }//GEN-LAST:event_txt_minFKeyTyped

    private void txt_maxFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_maxFKeyTyped
        
        noLetras(txt_maxF, evt);
        
    }//GEN-LAST:event_txt_maxFKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar_;
    private javax.swing.JButton btnReestablecer_;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblfondo;
    private javax.swing.JTextField txt_maxF;
    private javax.swing.JTextField txt_maxN;
    private javax.swing.JTextField txt_maxT;
    private javax.swing.JTextField txt_minF;
    private javax.swing.JTextField txt_minN;
    private javax.swing.JTextField txt_minT;
    // End of variables declaration//GEN-END:variables
}
