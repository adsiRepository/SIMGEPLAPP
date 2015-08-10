package Otros;

import Otros.DialExample.DemoPanel;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.data.general.DefaultValueDataset;

public class DialEx2 extends javax.swing.JInternalFrame implements Runnable {

    DefaultValueDataset dataset1;
    DefaultValueDataset dataset2;
    
    @Override
    public void run() {
        
        while(true){
            try {
                lbltest.setText(""+new Random().nextInt(15));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DialEx2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public DialEx2() {
        initComponents();
        setContentPane(new DemoPanel());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbltest = new javax.swing.JLabel();

        lbltest.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(261, Short.MAX_VALUE)
                .addComponent(lbltest)
                .addGap(99, 99, 99))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(lbltest)
                .addContainerGap(158, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
    public static void main(String[] args){
        DialEx2 test = new DialEx2();
        test.pack();
        test.setVisible(true);
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbltest;
    // End of variables declaration//GEN-END:variables

   
}
