
package Otros;


import Recursos.ConexionBD;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import org.jfree.data.xy.XYDataset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartPanel;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

// Referencias ->  http://www.javamexico.org/blogs/shadonwk/utilizacion_de_graficos_con_jfreechart_al_fin_pude
// Ejemplos de los Gráficos que se pueden hacer ->  http://www.jfree.org/jfreechart/samples.html
/**
 *
 * @author -------
 */
public class GrafosA {
    //private static final long serialVersionUID = 1L;

    public static XYDataset GenerarSeriesTiempo() {

        TimeSeries serieTemperatura = new TimeSeries("Temperatura");
        TimeSeries serieNivel = new TimeSeries("Nivel");
        TimeSeries serieFlujo = new TimeSeries("Flujo");

        //int incT = 0, incN = 0, incF = 0; //inc de incidencia, T, N, F por Temperatura, Nivel y Flujo.
        try (Connection dtbs = ConexionBD.GetConnection()) {
            PreparedStatement qry = dtbs.prepareStatement("select * from incidentes");
            ResultSet rspta = qry.executeQuery();
            //ResultSetMetaData metadatos = rspta.getMetaData();
            int temporalT = 0;
            int temporalN = 0;
            int temporalF = 0;

            while (rspta.next()) {

                Date date = (Date) rspta.getObject("fecha");
                GregorianCalendar h = new GregorianCalendar();
                h.setTime(date);

                switch (rspta.getString("factor")) {
                    case "Temperatura":
                        if (Integer.parseInt(rspta.getString("lectura")) > temporalT || Integer.parseInt(rspta.getString("lectura")) < temporalT) {
                            serieTemperatura.addOrUpdate(new Day(h.get(Calendar.DAY_OF_MONTH), (h.get(Calendar.MONTH) + 1), h.get(Calendar.YEAR)), Integer.parseInt(rspta.getString("lectura")));
                        }
                        temporalT = Integer.parseInt(rspta.getString("lectura"));
                        break;
                    case "Nivel":
                        if (Integer.parseInt(rspta.getString("lectura")) > temporalN || Integer.parseInt(rspta.getString("lectura")) < temporalN) {
                            serieNivel.addOrUpdate(new Day(h.get(Calendar.DAY_OF_MONTH), (h.get(Calendar.MONTH) + 1), h.get(Calendar.YEAR)), Integer.parseInt(rspta.getString("lectura")));
                        }
                        temporalN = Integer.parseInt(rspta.getString("lectura"));
                        break;
                    case "Flujo":
                        if (Integer.parseInt(rspta.getString("lectura")) > temporalF || Integer.parseInt(rspta.getString("lectura")) < temporalF) {
                            serieFlujo.addOrUpdate(new Day(h.get(Calendar.DAY_OF_MONTH), (h.get(Calendar.MONTH) + 1), h.get(Calendar.YEAR)), Integer.parseInt(rspta.getString("lectura")));
                        }
                        temporalF = Integer.parseInt(rspta.getString("lectura"));
                        break;
                }

                    //System.out.println("" + h.get(Calendar.DATE) + ( h.get(Calendar.MONTH) + 1 ) + h.get(Calendar.YEAR));
            }
                 //System.out.println("T:"+incT+", N:"+incN+", F:"+incF);

        } catch (Exception ex) {
            //Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Grafos, Generar Series Tiempo", 0);
        }

        TimeSeriesCollection series = new TimeSeriesCollection();

        series.addSeries(serieTemperatura);
        series.addSeries(serieNivel);
        series.addSeries(serieFlujo);

        return series;

    }

    //Referencias para crear Dial  -> https://github.com/anilbharadia/jFreeChart-Examples/blob/master/src/DialDemo2.java
    // busqueda google -> https://www.google.com.co/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=dial+con+JFreeChart
    public static ChartPanel DibujarGrafico(XYDataset parametros) {  //  BufferedImage

        JFreeChart ChartTimes = ChartFactory.createTimeSeriesChart("Comportamiento Regular SIMGEPLAP", "Tiempo", "Lecturas", parametros, true, false, false);

        ChartPanel panelChart = new ChartPanel(ChartTimes);

        //XYPlot estructura = ChartTimes.getXYPlot();  
        return panelChart;

        /*
         BufferedImage imagenChart = ChartTimes.createBufferedImage(200, 500);
         return imagenChart;
         */
    }
    
 

}
