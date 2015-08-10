package Otros;

import java.awt.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.*;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
import simgeplap.Temperatura;


public class DialExample extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;

    //Temperatura t = new Temperatura(); 
    DemoPanel demo = new DemoPanel();
    
    public DialExample() {
        super("Dialing");
        initComponents();
        setDefaultCloseOperation(3);
        //t.start();
        setContentPane(demo);
        //DemoPanel dial = new DemoPanel();
        new Thread(demo).start();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtChanges = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(235, 235, 235)
                .addComponent(txtChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(310, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(453, Short.MAX_VALUE)
                .addComponent(txtChanges, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//###################################################################################################################################################################################################    
    
public static class DemoPanel extends JPanel implements Runnable{//ChangeListener Runnable,
        private static final long serialVersionUID = 1L;

                DefaultValueDataset dataset1;
                DefaultValueDataset dataset2;
                JSlider slider1;
                JSlider slider2;
  
    @Override
    public void run() {
        while (true) {
            try {
                dataset1.setValue(new Random().nextInt(99));
                dataset2.setValue(new Random().nextInt(99));
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(DialExample.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

 /*   // Metodo de ChangeListener                           
       @Override
    public void stateChanged(ChangeEvent nuevaAccion) {

        dataset1.setValue(slider1.getValue());
        dataset2.setValue(slider2.getValue());
        //dataset2.setValue(Integer.parseInt(txtChanges.getText()));

    }
*/
                public DemoPanel(){
                    
                        super(new BorderLayout());
                        dataset1 = new DefaultValueDataset(10D);
                        dataset2 = new DefaultValueDataset(50D);
                        
                        
                        DialPlot dialplot = new DialPlot();
                        
                        dialplot.setView(0.0D, 0.0D, 1.0D, 1.0D);
                        dialplot.setDataset(0, dataset1);
                        dialplot.setDataset(1, dataset2);
                        
                        StandardDialFrame standarddialframe = new StandardDialFrame();
                        standarddialframe.setBackgroundPaint(Color.WHITE);
                        standarddialframe.setForegroundPaint(Color.gray);
                        dialplot.setDialFrame(standarddialframe);
                        
                        GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
                        DialBackground dialbackground = new DialBackground(gradientpaint);
                        
                        dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
                        dialplot.setBackground(dialbackground);
                        
                        DialTextAnnotation dialtextannotation = new DialTextAnnotation("Temperature");
                        dialtextannotation.setFont(new Font("Dialog", 1, 14));
                        dialtextannotation.setRadius(0.69999999999999996D);
                        dialplot.addLayer(dialtextannotation);
                        
                        DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
                        dialvalueindicator.setFont(new Font("Dialog", 0, 10));
                        dialvalueindicator.setOutlinePaint(Color.darkGray);
                        dialvalueindicator.setRadius(0.59999999999999998D);
                        dialvalueindicator.setAngle(-103D);
                        dialplot.addLayer(dialvalueindicator);
                        
                        DialValueIndicator dialvalueindicator1 = new DialValueIndicator(1);
                        dialvalueindicator1.setFont(new Font("Dialog", 0, 10));
                        dialvalueindicator1.setOutlinePaint(Color.red);
                        dialvalueindicator1.setRadius(0.59999999999999998D);
                        dialvalueindicator1.setAngle(-77D);
                        dialplot.addLayer(dialvalueindicator1);
                        
                        StandardDialScale standarddialscale = new StandardDialScale(0D, 160D, -120D, -300D, 10D, 4);
                        standarddialscale.setTickRadius(0.88D);
                        standarddialscale.setTickLabelOffset(0.14999999999999999D);
                        standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
                        dialplot.addScale(0, standarddialscale);
                        
                        StandardDialScale standarddialscale1 = new StandardDialScale(0.0D, 100D, -120D, -300D, 10D, 4);
                        standarddialscale1.setTickRadius(0.5D);
                        standarddialscale1.setTickLabelOffset(0.14999999999999999D);
                        standarddialscale1.setTickLabelFont(new Font("Dialog", 0, 10));
                        standarddialscale1.setMajorTickPaint(Color.red);
                        standarddialscale1.setMinorTickPaint(Color.red);
                        dialplot.addScale(1, standarddialscale1);
                        
                        dialplot.mapDatasetToScale(1, 1);
                        
                        StandardDialRange standarddialrange = new StandardDialRange(90D, 100D, Color.blue);
                        standarddialrange.setScaleIndex(1);
                        standarddialrange.setInnerRadius(0.58999999999999997D);
                        standarddialrange.setOuterRadius(0.58999999999999997D);
                        dialplot.addLayer(standarddialrange);
                        
                        org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(1);
                        pin.setRadius(0.55000000000000004D);
                        dialplot.addPointer(pin);
                        
                        org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer(0);
                        dialplot.addPointer(pointer);
                        
                        DialCap dialcap = new DialCap();
                        dialcap.setRadius(0.10000000000000001D);
                        dialplot.setCap(dialcap);
                        
                        //ChartFactory.create
                        
                        JFreeChart jfreechart = new JFreeChart(dialplot);
                        jfreechart.setTitle("Dial Demo 2");
                        ChartPanel chartpanel = new ChartPanel(jfreechart);
                        chartpanel.setPreferredSize(new Dimension(300, 300));
                        JPanel jpanel = new JPanel(new GridLayout(2, 2));
                        jpanel.add(new JLabel("Outer Needle:"));
                        jpanel.add(new JLabel("Inner Needle:"));
                        
                        slider1 = new JSlider(0, 160);
                        slider1.setMajorTickSpacing(20);
                        slider1.setPaintTicks(true);
                        slider1.setPaintLabels(true);
                        //slider1.addChangeListener(this);
                        jpanel.add(slider1);
                        
                        slider2 = new JSlider(0, 100);
                        slider2.setMajorTickSpacing(20);
                        slider2.setPaintTicks(true);
                        slider2.setPaintLabels(true);
                        //slider2.addChangeListener(this);
                        jpanel.add(slider2);
                        
                        this.add(chartpanel);
                        this.add(jpanel, "South");
                        
                }

        
        }
    
    //$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$4
    
    
//################################################################################################################################################################################################### 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DialExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DialExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DialExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialExample.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //(new Thread(new DemoPanel())).start();
        
        DialExample execute = new DialExample();
        execute.pack();
        /*DemoPanel dial = new DemoPanel();
        new Thread(dial).start();*/
        execute.setVisible(true);
        //System.out.print(Temperatura.lecT);
        
    }
        
        /* Create and display the form */
  /*      java.awt.EventQueue.invokeLater(() -> {
            new DialExample().setVisible(true);
        });
    }
*/
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField txtChanges;
    // End of variables declaration//GEN-END:variables

}