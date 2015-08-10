
package simgeplap;

import Controlador.Incidencias;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.StandardGradientPaintTransformer;



//Definicion de la Clase  
public class Presion extends Thread{


    
     //MONITOR de FLUJO,    extends JPanel significa que el objeto instanciado de esta clase sera un panel 
@SuppressWarnings("serial")
public static class LectorPresion extends JPanel implements Runnable {// implements Runnable quiere decir que implementará el comportamiento de un hilo
                                                        // es decir será un objeto que ejecutará un proceso por si mismo una vez sea echado a correr
     // variables globales de esta clase   
    @SuppressWarnings("FieldMayBeFinal")
    private DefaultValueDataset datasetP;
    //private int lecP;  // lec de lectura, F de flujo
    protected Random dado;
        

        // CONSTRUCTOR DEL INDICADOR GRAFICO
        public LectorPresion() {

            super(new BorderLayout());

            dado = new Random();
            
            datasetP = new DefaultValueDataset(10D);

            DialPlot manometroT = new DialPlot();

            manometroT.setView(0.0D, 0.0D, 1.0D, 1.0D); // original = 0.0D, 0.0D, 1.0D, 1.0D // esto determina la posicion del JPanel
            manometroT.setDataset(0, datasetP);

            StandardDialFrame disenoStandard = new StandardDialFrame();
            disenoStandard.setBackgroundPaint(Color.YELLOW);
            disenoStandard.setForegroundPaint(Color.BLACK);
            manometroT.setDialFrame(disenoStandard);

            GradientPaint gradientpaint = new GradientPaint(new Point(), Color.BLUE, new Point(), Color.CYAN.brighter());
            DialBackground fondo = new DialBackground(gradientpaint);

            fondo.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_HORIZONTAL));
            manometroT.setBackground(fondo);

            DialTextAnnotation etiquetaCentral = new DialTextAnnotation("lb/hr");
            etiquetaCentral.setFont(new Font("Monospaced", 1, 15));
            etiquetaCentral.setRadius(0.550005555D);
            manometroT.addLayer(etiquetaCentral);

            DialValueIndicator cajaIndicadoraValores = new DialValueIndicator(0);
            cajaIndicadoraValores.setFont(new Font("Dialog", 0, 12));
            cajaIndicadoraValores.setOutlinePaint(Color.GREEN);
            cajaIndicadoraValores.setFrameAnchor(RectangleAnchor.TOP);
            cajaIndicadoraValores.setRadius(0.3333999D);
            cajaIndicadoraValores.setAngle(-91D); // original = -103D
            manometroT.addLayer(cajaIndicadoraValores);

            StandardDialScale escala = new StandardDialScale(0D, 100D, -140D, -260D, 10D, 4);
            escala.setTickRadius(0.90D); // original = 0.88D
            escala.setTickLabelOffset(0.14999999999999999D);
            escala.setTickLabelPaint(new Color(233, 53, 30));
            escala.setTickLabelFont(new Font("Dialog", 0, 14));
            manometroT.addScale(0, escala);

            org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(0);
            pin.setRadius(0.200D); // cantidad original de ceros =  00000000000000  -> 14
            manometroT.addPointer(pin);

            org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer(0);
            pointer.setRadius(0.88D);
            pointer.setOutlinePaint(Color.ORANGE);
            manometroT.addPointer(pointer);

            DialCap pibotAguja = new DialCap();
            pibotAguja.setRadius(0.1100053D); // cantidad original de ceros = 000000000000000
            pibotAguja.setFillPaint(Color.BLUE);
            manometroT.setCap(pibotAguja);

                        //ChartFactory.create
            JFreeChart jfreechart = new JFreeChart(manometroT);
            jfreechart.setTitle("Presion");
            ChartPanel chartpanel = new ChartPanel(jfreechart);
            chartpanel.setPreferredSize(new Dimension(150, 160));
            this.add(chartpanel);

        }
        
        
        // Hilo de ejecucion principal, corazon de la maquina  ----<>
        @Override
        @SuppressWarnings("UseSpecificCatch")
        public void run() { // este es el proceso que se ejecutará una vez echado a correr el objeto instanciado de esta clase-hilo

            long retardo = 1010; // milisegundos, tiempo que tardará la aguja en señalar una nueva lectura

            while (true) { // while true para que el ciclo sea indefinido
                try {

                    if (dado.nextInt(200) == dado.nextInt(210)) {// dado es un objeto de la clase random, en esta linea le digo que lanse 2 dados, uno 
                                                   // de 85 lados y otro de 30 y si caen par ...
                        SIMGEPLAP.lecP = dado.nextInt(100);//... la lectura sera al azar en una rango de 0 a 100
                        datasetP.setValue(SIMGEPLAP.lecP);// luego se la pasamos al valor que debe señalar la aguja del monitor

                      if (SIMGEPLAP.lecP < SIMGEPLAP.minF || SIMGEPLAP.lecP > SIMGEPLAP.maxF) {
                          Incidencias.SaveAlertApp("presion", SIMGEPLAP.lecP);
                      }
                            /*  String mensajeAlerta = "!Lecturas de presión de Presion Anormales, lectura: " + lecP;
                            
                    //        ¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬|
                            //Cuadro de Dialogo JOptionPane -->
                       Controlador.Incidencias.WaitToSend("nivel de Presion inseguro, registrar Alerta?", mensajeAlerta, "Presion", lecP );
                    //¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬
                            
                       //     Incidencias.TomandoEleccion();

                        } else {
                            Thread.sleep(retardo);
                        }*/
                    } else {
                        SIMGEPLAP.lecP = dado.nextInt(SIMGEPLAP.maxF - SIMGEPLAP.minF) + SIMGEPLAP.minF;
                        datasetP.setValue(SIMGEPLAP.lecP);
                    }
                    
                    Incidencias.registrarLectura(SIMGEPLAP.lecT, SIMGEPLAP.lecP, SIMGEPLAP.lecN);
                    
                    Thread.sleep(retardo); 
                      
                } catch (Exception oh) {//
                    JOptionPane.showMessageDialog(null, " Error de Ejecución, Detalle:\n  " + oh.getMessage(), "Flujo, Lector", 0);
                }
            }

        }

    }
    
    
}
