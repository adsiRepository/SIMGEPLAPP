
package Otros;


//import java.awt.Robot; //http://jc-mouse.blogspot.com/2010/04/respuesta-cerrar-joptionpane.html
import java.awt.*;
//import java.util.*;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import simgeplap.*;

/*
 *@author Grupo de Desarrollo ADSI38
*/
public class Monitor extends javax.swing.JInternalFrame{ 
/*    
    
    private static final long serialVersionUID = 1L;
// implements Runnable
    
    Temperatura temperatura;
    Nivel nivel;
    Flujo flujo;
    /*Timer counter;
    TimerTask job;
    //Robot bender;
    //Thread miHilo;

    public Monitor( JDesktopPane Escritorio ) {
        initComponents();
        
        Dimension areaTrabajo = Escritorio.getSize();
        //Dimension espacioOcupado = this.getSize(); // no borrar esta línea
        /*int ancho = ( areaTrabajo.width - espacioOcupado.width ) / 2;
        int altura = ( areaTrabajo.height - espacioOcupado.height ) / 2;
        int ancho = ( areaTrabajo.width / 5 ) * 3;
        int altura = ( areaTrabajo.height / 3 ) * 2;
        this.setLocation( ancho, altura );
        this.title = "Monitor";
        this.iconable = true;
        lbl_loading.setVisible( false );
        lblwarnSend.setVisible( false );
        lblclock.setVisible( false );
        
         //Inicio de Obtención de Lecturas
        try {
            temperatura = new Temperatura( txt_temp, lbl_loading, lblwarnSend, lblclock );
        } catch (AWTException ex) {
            JOptionPane.showMessageDialog(rootPane, ""+ex, "Error en Monitor, Constructor", 0);
            //Logger.getLogger(Monitor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        temperatura.start();
        nivel = new Nivel( txt_nivel, lbl_loading );
        nivel.start();
        flujo = new Flujo( txt_flujo );
        flujo.start();
        //############################//
        
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbl_temp = new javax.swing.JLabel();
        lbl_nivel = new javax.swing.JLabel();
        lbl_flujo = new javax.swing.JLabel();
        txt_temp = new javax.swing.JTextField();
        txt_nivel = new javax.swing.JTextField();
        txt_flujo = new javax.swing.JTextField();
        lbl_loading = new javax.swing.JLabel();
        lblwarnSend = new javax.swing.JLabel();
        lblclock = new javax.swing.JLabel();

        lbl_temp.setText("Temperatura:");

        lbl_nivel.setText("Nivel:");

        lbl_flujo.setText("Flujo:");

        txt_temp.setEditable(false);

        txt_nivel.setEditable(false);

        txt_flujo.setEditable(false);

        lbl_loading.setText("Wait");

        lblwarnSend.setText("Enviando Email en:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(lblwarnSend)
                            .addGap(31, 31, 31))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(lblclock, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lbl_flujo)
                    .addComponent(lbl_nivel)
                    .addComponent(lbl_temp))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_temp, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nivel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_flujo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(lbl_loading, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_temp)
                    .addComponent(txt_temp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nivel)
                    .addComponent(txt_nivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_flujo)
                    .addComponent(txt_flujo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblwarnSend)
                    .addComponent(lbl_loading, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblclock, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbl_flujo;
    private javax.swing.JLabel lbl_loading;
    private javax.swing.JLabel lbl_nivel;
    private javax.swing.JLabel lbl_temp;
    private javax.swing.JLabel lblclock;
    private javax.swing.JLabel lblwarnSend;
    private javax.swing.JTextField txt_flujo;
    private javax.swing.JTextField txt_nivel;
    private javax.swing.JTextField txt_temp;
    // End of variables declaration//GEN-END:variables



}
