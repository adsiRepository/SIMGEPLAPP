
package Vistas;

import static Vistas.Menu.Escritorio;
import static Vistas.Menu.ventanaHistorial;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import simgeplap.Presion.LectorPresion;
import simgeplap.Nivel.LectorNivel;
import simgeplap.Temperatura.LectorTemperatura;


public class MonitorDial extends javax.swing.JInternalFrame {
    
    LectorTemperatura panelT;
    LectorNivel panelN;
    LectorPresion panelF;
    
    protected final Image miFondo = new ImageIcon(getClass().getResource("/Recursos/Imagenes/fondoMonitor.png")).getImage();
    
    @Override
    protected void paintComponent(Graphics grf){
        super.paintComponent(grf);
        grf.drawImage(miFondo, 0, 0, getWidth(), getHeight(), null, this);
    }
    
    public MonitorDial( JDesktopPane panelContenedor ) {
        initComponents();
        
        this.title = "Panel de Monitoreo";
        this.iconable = true;
        this.resizable = true;
        this.setFrameIcon(new ImageIcon(getClass().getResource("/Recursos/Imagenes/iconMonitor.png")));
        Dimension areaTrabajo = panelContenedor.getSize();
        //Dimension espacioOcupado = this.getSize(); // no borrar esta línea
        int ancho = ( areaTrabajo.width / 8 ) * 3;
        int altura = ( areaTrabajo.height / 3 );
        this.setLocation( ancho, altura );
        this.setToolTipText("Monitorea las Variables");

        
        lbl_loading.setVisible(false);
        panelT = new LectorTemperatura();
        panelN = new LectorNivel();
        panelF = new LectorPresion();
        
        ((BasicInternalFrameUI)frameInternoNivel.getUI()).setNorthPane(null);
        frameInternoNivel.setClosable(false);
        frameInternoNivel.setTitle("Temperatura");
        frameInternoNivel.setContentPane(panelT);
        new Thread(panelT).start();
        
        ((BasicInternalFrameUI)frameInternoTemperatura.getUI()).setNorthPane(null);
        frameInternoTemperatura.setContentPane(panelN);
        frameInternoTemperatura.setTitle("Niveles");
        frameInternoTemperatura.setClosable(false);
        new Thread(panelN).start();
        
        ((BasicInternalFrameUI)frameInternoFlujo.getUI()).setNorthPane(null);
        frameInternoFlujo.setContentPane(panelF);
        frameInternoFlujo.setClosable(false);
        frameInternoFlujo.setTitle("Flujo");
        new Thread(panelF).start();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVerHistorial_ = new javax.swing.JButton();
        frameInternoFlujo = new javax.swing.JInternalFrame();
        frameInternoTemperatura = new javax.swing.JInternalFrame();
        frameInternoNivel = new javax.swing.JInternalFrame();
        lbl_loading = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnToBack_ = new javax.swing.JButton();

        setBackground(new java.awt.Color(51, 51, 51));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        btnVerHistorial_.setText("Observar Historial");
        btnVerHistorial_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerHistorial_ActionPerformed(evt);
            }
        });

        frameInternoFlujo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        frameInternoFlujo.setAutoscrolls(true);
        frameInternoFlujo.setMaximumSize(new java.awt.Dimension(300, 300));
        frameInternoFlujo.setMinimumSize(new java.awt.Dimension(150, 150));
        frameInternoFlujo.setPreferredSize(new java.awt.Dimension(230, 230));
        try {
            frameInternoFlujo.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        frameInternoFlujo.setVisible(true);

        javax.swing.GroupLayout frameInternoFlujoLayout = new javax.swing.GroupLayout(frameInternoFlujo.getContentPane());
        frameInternoFlujo.getContentPane().setLayout(frameInternoFlujoLayout);
        frameInternoFlujoLayout.setHorizontalGroup(
            frameInternoFlujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        frameInternoFlujoLayout.setVerticalGroup(
            frameInternoFlujoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        frameInternoTemperatura.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        frameInternoTemperatura.setAutoscrolls(true);
        frameInternoTemperatura.setMaximumSize(new java.awt.Dimension(300, 300));
        frameInternoTemperatura.setMinimumSize(new java.awt.Dimension(150, 150));
        frameInternoTemperatura.setPreferredSize(new java.awt.Dimension(230, 230));
        try {
            frameInternoTemperatura.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        frameInternoTemperatura.setVisible(true);

        javax.swing.GroupLayout frameInternoTemperaturaLayout = new javax.swing.GroupLayout(frameInternoTemperatura.getContentPane());
        frameInternoTemperatura.getContentPane().setLayout(frameInternoTemperaturaLayout);
        frameInternoTemperaturaLayout.setHorizontalGroup(
            frameInternoTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        frameInternoTemperaturaLayout.setVerticalGroup(
            frameInternoTemperaturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        frameInternoNivel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        frameInternoNivel.setAutoscrolls(true);
        frameInternoNivel.setMaximumSize(new java.awt.Dimension(300, 300));
        frameInternoNivel.setMinimumSize(new java.awt.Dimension(150, 150));
        frameInternoNivel.setPreferredSize(new java.awt.Dimension(230, 230));
        try {
            frameInternoNivel.setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        frameInternoNivel.setVisible(true);

        javax.swing.GroupLayout frameInternoNivelLayout = new javax.swing.GroupLayout(frameInternoNivel.getContentPane());
        frameInternoNivel.getContentPane().setLayout(frameInternoNivelLayout);
        frameInternoNivelLayout.setHorizontalGroup(
            frameInternoNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );
        frameInternoNivelLayout.setVerticalGroup(
            frameInternoNivelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        lbl_loading.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        lbl_loading.setForeground(new java.awt.Color(204, 204, 0));
        lbl_loading.setText("Wait");

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 42)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 153, 0));
        jLabel1.setText("SIMGEPLAP");

        btnToBack_.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        btnToBack_.setText("Segundo Plano");
        btnToBack_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToBack_ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnToBack_, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(frameInternoNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(frameInternoTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(frameInternoFlujo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(btnVerHistorial_, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(114, 114, 114)
                                .addComponent(lbl_loading, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(frameInternoFlujo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frameInternoNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(frameInternoTemperatura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnVerHistorial_, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_loading, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnToBack_))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerHistorial_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerHistorial_ActionPerformed
        
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
        
    }//GEN-LAST:event_btnVerHistorial_ActionPerformed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained

        this.toFront();
        btnToBack_.setVisible(true);

    }//GEN-LAST:event_formFocusGained

    private void btnToBack_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToBack_ActionPerformed
        this.toBack();
    }//GEN-LAST:event_btnToBack_ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnToBack_;
    private javax.swing.JButton btnVerHistorial_;
    private javax.swing.JInternalFrame frameInternoFlujo;
    private javax.swing.JInternalFrame frameInternoNivel;
    private javax.swing.JInternalFrame frameInternoTemperatura;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel lbl_loading;
    // End of variables declaration//GEN-END:variables
}
