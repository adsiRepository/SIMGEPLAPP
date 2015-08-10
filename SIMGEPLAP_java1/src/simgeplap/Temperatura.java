
package simgeplap;

import Controlador.Incidencias;
import java.awt.*;
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

/**
 *
 * @author 
 */
public class Temperatura extends Thread {

//  Codigo del marcador grafico, del reloj indicador      
           //hereda de la clase JPanel por lo que será un panel que se comportará como un hilo 
@SuppressWarnings("serial")
public static class LectorTemperatura extends JPanel implements Runnable {

        DefaultValueDataset datasetT; // parámetros de la aguja del reloj
        
        //public static int lecT; // lectura Temperatura baahh
        private Random dado;
        
        
        // Método Constructor, donde se configuran todos los aspectos graficos y demas definiciones de variables
        public LectorTemperatura() {

            super(new BorderLayout());

            dado = new Random();
            
            datasetT = new DefaultValueDataset(10D);

            DialPlot manometroT = new DialPlot(); // esto es el reloj

            manometroT.setView(0.0D, 0.0D, 1.0D, 1.0D); // original = 0.0D, 0.0D, 1.0D, 1.0D // esto determina la posicion del JPanel
            manometroT.setDataset(0, datasetT);

            StandardDialFrame disenoStandard = new StandardDialFrame();
            disenoStandard.setBackgroundPaint(Color.RED);
            disenoStandard.setForegroundPaint(Color.BLACK);
            manometroT.setDialFrame(disenoStandard);

            GradientPaint ColorGradiente = new GradientPaint(new Point(), new Color(130, 77, 37), new Point(), Color.RED);
            DialBackground fondo = new DialBackground(ColorGradiente);

            fondo.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
            manometroT.setBackground(fondo);

            DialTextAnnotation etiquetaCentral = new DialTextAnnotation("°C");
            etiquetaCentral.setFont(new Font("Arial", 1, 14));
            etiquetaCentral.setRadius(0.336665577D);
            etiquetaCentral.setAngle(-99D);
            manometroT.addLayer(etiquetaCentral);

            DialValueIndicator cajaIndicadoraValores = new DialValueIndicator(0);
            cajaIndicadoraValores.setFont(new Font("Dialog", 0, 12));
            cajaIndicadoraValores.setOutlinePaint(Color.BLACK);
            cajaIndicadoraValores.setFrameAnchor(RectangleAnchor.TOP);
            cajaIndicadoraValores.setRadius(0.299888000D);
            cajaIndicadoraValores.setAngle(-167D);
            manometroT.addLayer(cajaIndicadoraValores);

            /*
             DialValueIndicator dialvalueindicator1 = new DialValueIndicator(1);
             dialvalueindicator1.setFont(new Font("Dialog", 0, 10));
             dialvalueindicator1.setOutlinePaint(Color.red);
             dialvalueindicator1.setRadius(0.59999999999999998D);
             dialvalueindicator1.setAngle(-77D);
             manometroN.addLayer(dialvalueindicator1);
             */
            
            StandardDialScale escala = new StandardDialScale(30D, 130D, -140D, -260D, 10D, 10);
            escala.setTickRadius(0.90D); // original = 0.88D
            escala.setTickLabelOffset(0.14999999999999999D);
            escala.setTickLabelFont(new Font("Arial", 0, 14));
            escala.setTickLabelPaint(new Color(191, 248, 19));
            escala.setMajorTickPaint(Color.DARK_GRAY);
            manometroT.addScale(0, escala);

            /*StandardDialScale escalaInterior = new StandardDialScale(0.0D, 100D, -120D, -300D, 10D, 4);
             escalaInterior.setTickRadius(0.5D);
             escalaInterior.setTickLabelOffset(0.14999999999999999D);
             escalaInterior.setTickLabelFont(new Font("Dialog", 0, 10));
             escalaInterior.setMajorTickPaint(Color.red);
             escalaInterior.setMinorTickPaint(Color.red);
             manometroN.addScale(1, escalaInterior);*/
                        //manometroT.mapDatasetToScale(1, 1);
            /*StandardDialRange standarddialrange = new StandardDialRange(90D, 100D, Color.blue);
             standarddialrange.setScaleIndex(1);
             standarddialrange.setInnerRadius(0.58999999999999997D);
             standarddialrange.setOuterRadius(0.58999999999999997D);
             manometroN.addLayer(standarddialrange);*/
            
            org.jfree.chart.plot.dial.DialPointer.Pin pin = new org.jfree.chart.plot.dial.DialPointer.Pin(1);
            pin.setRadius(0.554D); // cantidad original de ceros =  00000000000000  -> 14
            manometroT.addPointer(pin);

            org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer(0);
            manometroT.addPointer(pointer);

            DialCap pibotAguja = new DialCap();
            pibotAguja.setRadius(0.11D); // cantidad original de ceros = 000000000000000
            pibotAguja.setFillPaint(Color.BLUE);
            manometroT.setCap(pibotAguja);

                        //ChartFactory.create
            JFreeChart jfreechart = new JFreeChart(manometroT);
            jfreechart.setTitle("Temperatura");
            ChartPanel chartpanel = new ChartPanel(jfreechart);
            chartpanel.setPreferredSize(new Dimension(150, 160));
            this.add(chartpanel);
            //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            
        }
        
        
        // Hilo de ejecucion principal, corazon de la maquina  ----<>
        @Override
        @SuppressWarnings("UseSpecificCatch")
        public void run() {

            long retardo = 478; // milisegundos en que tarda la aguja en indicar un nuevo valor

            while (true) { // es decir que será un bucle indefinido
                try {

                    if (dado.nextInt(350) == dado.nextInt(350)) {// probabilidad, estoy tirando 2 dados 1 de a lados y otro de b lados
                                    // si caen par ->
                        SIMGEPLAP.lecT = dado.nextInt(190);//la lectura será al azar entre 0 y 190
                        datasetT.setValue(SIMGEPLAP.lecT);  // lo agregará a los datos del reloj

                        if (SIMGEPLAP.lecT < SIMGEPLAP.minT || SIMGEPLAP.lecT > SIMGEPLAP.maxT) {//si lecT está fuera de estos números entonces...
                            Incidencias.SaveAlertApp("temperatura", SIMGEPLAP.lecT);
                        }
                    /*         String mensajeTemperatura = "";
                            
                            if (lecT < SIMGEPLAP.minT) {
                                mensajeTemperatura = "!Alerta, niveles bajos de Temperatura, lectura: " + lecT;
                            }
                            if (lecT > SIMGEPLAP.maxT) {
                                mensajeTemperatura = "¡Alerta, Altos niveles de Temperatura, lectura: " + lecT;
                            }
                            
//Cuadro de Dialogo -->
                            Incidencias.WaitToSend( "Sobresalto de Temperatura", mensajeTemperatura, "Temperatura", lecT ); // creo el JOptionPane que esperará para enviar la alerta
//¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬|

                        } else {
                            Thread.sleep(retardo);
                        }*/
                    } else {
                        SIMGEPLAP.lecT = dado.nextInt(SIMGEPLAP.maxT - SIMGEPLAP.minT) + SIMGEPLAP.minT;
                        datasetT.setValue(SIMGEPLAP.lecT);
                    }
                    
                        Thread.sleep(retardo);
          
                } catch (Exception oh) {//
                    JOptionPane.showMessageDialog(null, " Error de Ejecución, Detalle:\n  " + oh.getMessage(), "Temperatura, LectorTemperatura", 0);
                }
            }

        }

    }
    
}

/*Links de Informacion
http://chuwiki.chuidiang.org/index.php?title=Generar_n%C3%BAmeros_aleatorios_en_Java
manecillas moviles en java: https://www.youtube.com/watch?v=FriWAOflGYo

*/
