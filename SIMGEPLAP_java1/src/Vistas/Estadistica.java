
package Vistas;

import Controlador.*;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Estadistica extends javax.swing.JInternalFrame {

    
    public Estadistica() {
        initComponents();
        
        setClosable(true);
        this.title = "Balance";
        this.iconable = true;
        this.resizable = true;
        this.maximizable = true;
        this.setFrameIcon(new ImageIcon(getClass().getResource("/Recursos/Imagenes/iconEstads.png")));
        this.setToolTipText("Graficos Estadisticos");
        
        panelLineaTiempo1.setLayout(new java.awt.BorderLayout());
        panelLineaTiempo1.add( Grafos.DisplayGrafico( Grafos.GenerarSeriesTiempo() ) );
        panelLineaTiempo1.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        panelLineaTiempo1 = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();

        javax.swing.GroupLayout panelLineaTiempo1Layout = new javax.swing.GroupLayout(panelLineaTiempo1);
        panelLineaTiempo1.setLayout(panelLineaTiempo1Layout);
        panelLineaTiempo1Layout.setHorizontalGroup(
            panelLineaTiempo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 961, Short.MAX_VALUE)
        );
        panelLineaTiempo1Layout.setVerticalGroup(
            panelLineaTiempo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(panelLineaTiempo1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(281, 281, 281)
                        .addComponent(lblImage, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JPanel panelLineaTiempo1;
    // End of variables declaration//GEN-END:variables
}
