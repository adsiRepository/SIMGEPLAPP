
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

public class Nivel extends Thread{
    
    
    //  El DIAL DE LA VARIABLE NIVEL -----<>
    @SuppressWarnings("serial")
    public static class LectorNivel extends JPanel implements Runnable {
// clase estatica, publica cuya instancia será un JPanel que ejecutará la funcion definida una vez puesto a correr

        DefaultValueDataset datasetN;
        //private int lecN;
        protected Random dado;
        

        public LectorNivel() {

            super(new BorderLayout());

            dado = new Random();

            datasetN = new DefaultValueDataset(10D);

            DialPlot manometroN = new DialPlot();

            manometroN.setView(0.0D, 0.0D, 1.0D, 1.0D); // original = 0.0D, 0.0D, 1.0D, 1.0D // esto determina la posicion del JPanel
            manometroN.setDataset(0, datasetN);

            StandardDialFrame disenoStandard = new StandardDialFrame();
            disenoStandard.setBackgroundPaint(Color.RED);
            disenoStandard.setForegroundPaint(Color.LIGHT_GRAY.brighter());
            manometroN.setDialFrame(disenoStandard);

            GradientPaint gradientpaint = new GradientPaint(new Point(), Color.DARK_GRAY, new Point(), Color.LIGHT_GRAY);
            DialBackground fondo = new DialBackground(gradientpaint);

            fondo.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
            manometroN.setBackground(fondo);

            DialTextAnnotation etiquetaCentral = new DialTextAnnotation("Altura/cm3");
            etiquetaCentral.setFont(new Font("cursive", 0, 14));
            etiquetaCentral.setRadius(0.2999944444999555D);
            etiquetaCentral.setAngle(-115);
            manometroN.addLayer(etiquetaCentral);

            DialValueIndicator cajaIndicadoraValores = new DialValueIndicator(0);// Original = 0
            cajaIndicadoraValores.setFont(new Font("Monospaced", 0, 12));
            cajaIndicadoraValores.setOutlinePaint(Color.BLACK.brighter());
            cajaIndicadoraValores.setFrameAnchor(RectangleAnchor.TOP);
            cajaIndicadoraValores.setRadius(0.2999999000D);
            cajaIndicadoraValores.setAngle(-345D);
            manometroN.addLayer(cajaIndicadoraValores);

            StandardDialScale escala = new StandardDialScale(0D, 100D, -140D, -260D, 10D, 5);
            escala.setTickRadius(0.90D); // original = 0.88D
            escala.setTickLabelOffset(0.14999999999999999D);
            escala.setTickLabelFont(new Font("Cursive", 0, 14));
            escala.setTickLabelPaint(new Color(240, 222, 20));
            manometroN.addScale(0, escala);

            org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(1);
            pin.setRadius(0.554D); // cantidad original de ceros =  00000000000000  -> 14
            pin.setPaint(Color.RED);
            manometroN.addPointer(pin);

            org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer(0);
            manometroN.addPointer(pointer);

            DialCap pibotAguja = new DialCap();
            pibotAguja.setRadius(0.11D); // cantidad original de ceros = 000000000000000
            pibotAguja.setFillPaint(Color.getHSBColor(205, 62, 95));
            manometroN.setCap(pibotAguja);

            //ChartFactory.create
            JFreeChart jfreechart = new JFreeChart(manometroN);
            jfreechart.setTitle("Nivel");
            ChartPanel chartpanel = new ChartPanel(jfreechart);
            chartpanel.setPreferredSize(new Dimension(150, 160));
            this.add(chartpanel);
        }
        
 
        // Hilo de ejecucion principal, corazon de la maquina  ----<>
        @Override
        @SuppressWarnings("UseSpecificCatch")
        public void run() {

            long retardo = 735; // milisegundos

            while (true) {
                try {

                    if (dado.nextInt(260) == dado.nextInt(250)) {//planteo la condición de que sí coinciden dos numeros arrojados al azar el rango varíe entre 0 y 190 °C
                        
                        SIMGEPLAP.lecN = dado.nextInt(100);
                        datasetN.setValue(SIMGEPLAP.lecN);

                        if (SIMGEPLAP.lecN < SIMGEPLAP.minN || SIMGEPLAP.lecN > SIMGEPLAP.maxN) {//si lecT está fuera de estos números entonces...
                            Incidencias.SaveAlertApp("nivel", SIMGEPLAP.lecN);
                        }
                            /*String mensajeAlerta = "";

                            if (lecN < SIMGEPLAP.minN) {
                                mensajeAlerta = "!Niveles muy bajos, lectura: " + lecN;
                            }
                            if (lecN > SIMGEPLAP.maxN) {
                                mensajeAlerta = "¡Niveles muy Altos!, lectura: " + lecN;
                            }
                            
                    //Cuadro de Dialogo -->
                            Controlador.Incidencias.WaitToSend("Precaución, Niveles Anormales, envío de Alerta", mensajeAlerta, "Nivel",lecN);
          //¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬
                            
                          //  Incidencias.TomandoEleccion();
                        } else {
                            Thread.sleep(retardo);
                        }*/
                    } else {
                        SIMGEPLAP.lecN = dado.nextInt(SIMGEPLAP.maxN - SIMGEPLAP.minN) + SIMGEPLAP.minN;
                        datasetN.setValue(SIMGEPLAP.lecN);
                    }
                        Thread.sleep(retardo);
                } catch (Exception oh) {
                    JOptionPane.showMessageDialog(null, " Error de Ejecución, Detalle:\n  " + oh.getLocalizedMessage(), "Nivel, Lector", 0);
                }

            }

        }

    }
   
}
