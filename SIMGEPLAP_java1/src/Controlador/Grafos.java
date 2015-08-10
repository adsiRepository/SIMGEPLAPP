
package Controlador;

//http://www.javaya.com.ar/detalleconcepto.php?codigo=130&inicio=40  -> Clase Graphics


import Recursos.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.SplashScreen;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.data.general.SeriesException;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

// Referencias ->  http://www.javamexico.org/blogs/shadonwk/utilizacion_de_graficos_con_jfreechart_al_fin_pude
// Ejemplos de los Gráficos que se pueden hacer ->  http://www.jfree.org/jfreechart/samples.html
/**
 *
 * @author -------
 */
public class Grafos {
    //private static final long serialVersionUID = 1L;
    
    private static final Connection dtbs = ConexionBD.GetConnection();

    /**
     *
     */
    public final Image fondoEscritorio = new ImageIcon(getClass().getResource("../Recursos/Imagenes/BannerSIMG.png")).getImage();
    
    public static JFreeChart GenerarSeriesTiempo() { // XYDataset

        TimeSeries serieTemperatura = new TimeSeries("Temperatura");
        TimeSeries serieNivel = new TimeSeries("Nivel");
        TimeSeries serieFlujo = new TimeSeries("Flujo");

        //int incT = 0, incN = 0, incF = 0; //inc de incidencia, T, N, F por Temperatura, Nivel y Flujo.
        try {
            PreparedStatement qry = dtbs.prepareStatement("select * from incidencias");
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
                            temporalT = Integer.parseInt(rspta.getString("lectura"));
                        }
                        //temporalT = Integer.parseInt(rspta.getString("lectura"));
                        break;
                    case "Nivel":
                        if (Integer.parseInt(rspta.getString("lectura")) > temporalN || Integer.parseInt(rspta.getString("lectura")) < temporalN) {
                            serieNivel.addOrUpdate(new Day(h.get(Calendar.DAY_OF_MONTH), (h.get(Calendar.MONTH) + 1), h.get(Calendar.YEAR)), Integer.parseInt(rspta.getString("lectura")));
                            temporalN = Integer.parseInt(rspta.getString("lectura"));
                        }
                        //temporalN = Integer.parseInt(rspta.getString("lectura"));
                        break;
                    case "Flujo":
                        if (Integer.parseInt(rspta.getString("lectura")) > temporalF || Integer.parseInt(rspta.getString("lectura")) < temporalF) {
                            serieFlujo.addOrUpdate(new Day(h.get(Calendar.DAY_OF_MONTH), (h.get(Calendar.MONTH) + 1), h.get(Calendar.YEAR)), Integer.parseInt(rspta.getString("lectura")));
                            temporalF = Integer.parseInt(rspta.getString("lectura"));
                        }
                        //temporalF = Integer.parseInt(rspta.getString("lectura"));
                        break;
                }

            }

        } catch (SQLException | NullPointerException | SeriesException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Grafos, Generar Series Tiempo", 0);
        }

        TimeSeriesCollection series = new TimeSeriesCollection();

        series.addSeries(serieTemperatura);
        series.addSeries(serieNivel);
        series.addSeries(serieFlujo);
        
        JFreeChart GraficoIncidencias_tiempo = ChartFactory.createTimeSeriesChart("Comportamiento Regular SIMGEPLAP", "Tiempo", "Lecturas", series, true, false, false);

        return GraficoIncidencias_tiempo;
 //return series;
        
    }
    
    
    
    // en construccion ---<>
    public static JFreeChart BalanceDiario( String fecha ){
        
        TimeSeries serieTemperatura = new TimeSeries("Temperatura");
        TimeSeries serieNivel = new TimeSeries("Nivel");
        TimeSeries serieFlujo = new TimeSeries("Flujo");

        //int incT = 0, incN = 0, incF = 0; //inc de incidencia, T, N, F por Temperatura, Nivel y Flujo.
        try {
            PreparedStatement qry = dtbs.prepareStatement("select * from incidentes");
            ResultSet rspta = qry.executeQuery();
            //ResultSetMetaData metadatos = rspta.getMetaData();

            Date fechaBD;
            GregorianCalendar fchBd = new GregorianCalendar();
            GregorianCalendar fechaBusq = new GregorianCalendar();
            
            while (rspta.next()) {

                fechaBD = (Date) rspta.getObject("fecha");
                fchBd.setTime(fechaBD);
                fechaBusq.setTime(Time.formatofecha.parse(fecha));
                
                if ( fchBd.equals(fechaBusq) ){
                    
                    switch (rspta.getString("factor")) {
                    case "Temperatura":
                            serieTemperatura.addOrUpdate(new Day(fchBd.get(Calendar.HOUR_OF_DAY), (fchBd.get(Calendar.MINUTE)), fchBd.get(Calendar.YEAR)), Integer.parseInt(rspta.getString("lectura")));
                            
                        break;
                    case "Nivel":
                            serieNivel.addOrUpdate(new Day(fchBd.get(Calendar.DAY_OF_MONTH), (fchBd.get(Calendar.MONTH) + 1), fchBd.get(Calendar.YEAR)), Integer.parseInt(rspta.getString("lectura")));
            
                        break;
                    case "Flujo":
                            serieFlujo.addOrUpdate(new Day(fchBd.get(Calendar.DAY_OF_MONTH), (fchBd.get(Calendar.MONTH) + 1), fchBd.get(Calendar.YEAR)), Integer.parseInt(rspta.getString("lectura")));
           
                        break;
                }
                    
                }

            }

        } catch (SQLException | NullPointerException | SeriesException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Grafos, Generar Series Tiempo", 0);
        } catch (ParseException ex) {
            Logger.getLogger(Grafos.class.getName()).log(Level.SEVERE, null, ex);
        }

        TimeSeriesCollection series = new TimeSeriesCollection();

        series.addSeries(serieTemperatura);
        series.addSeries(serieNivel);
        series.addSeries(serieFlujo);
    
        JFreeChart balanceDia;
        //ChartFactory.createTimeSeriesChart(titulo, ejeTiempo(ejex), ejeValores(ejey), datos);
        
        balanceDia = ChartFactory.createTimeSeriesChart("Balance de "+Time.formatofecha.format(Time.hoy), "Hora", "Lecturas", null);
        return balanceDia;
    }
    
    
    
    
    public static ChartPanel DisplayGrafico(JFreeChart esquema) {
        ChartPanel panelChart = new ChartPanel(esquema);
        return panelChart;
    }

    
    
    @SuppressWarnings("FinallyDiscardsException")
    public static byte[] chartToPNG(JFreeChart esquema /*, OutputStream ruta*/ ) {
        
        BufferedImage imagenChart = esquema.createBufferedImage(500, 300);
        byte[] imgPNG = null;
        
        try {
            //ImageIO.write(imagenChart, "png", ruta);
            imgPNG = ChartUtilities.encodeAsPNG(imagenChart);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Grafos, Exportar Imagen a .png", 0);     
        }
        
        return imgPNG;
        
    }
    
    
 //Referencias para crear Dial  -> https://github.com/anilbharadia/jFreeChart-Examples/blob/master/src/DialDemo2.java
    // busqueda google -> https://www.google.com.co/webhp?sourceid=chrome-instant&ion=1&espv=2&ie=UTF-8#q=dial+con+JFreeChart
    
    
  
    
    //http://www.jc-mouse.net/java/anadir-splash-screen-en-javanetbeans
    public static class SplashBienvenida{

        private SplashScreen splash;
        
        public SplashBienvenida() {
            splash = SplashScreen.getSplashScreen();
        }
        
        public void animar(Component ini){
            if (splash != null) {
                Graphics2D grf = splash.createGraphics();
                String load = "Loading...";
                try {
                    for (int i = 1; i <= 9; i++) {
                        grf.drawString(load, 0, 0);
                        BasicStroke trazo = new BasicStroke();
                        grf.setStroke(trazo);
                        grf.setColor(new Color(232, 190, 39));
                        splash.update();
                        Thread.sleep(500);
                    }
                    splash.close();
                    ini.setVisible(true);
                } catch (InterruptedException ex) {
                    System.out.printf(ex.getLocalizedMessage());
                }
            }
        }
        
    }
    
    
    //Aqui se crea y se diseña lo que será el fondo/entorno de la aplicación 
    @SuppressWarnings("serial")
    public static class Escritorio extends JDesktopPane implements ChangeListener{ // de la clase jdesktoppane 
        private final Image fondoEscritorio;
        
        public Escritorio(){
            super();
            fondoEscritorio = new ImageIcon(getClass().getResource("../Recursos/Imagenes/baner34.png")).getImage();
        }
        
        @Override  //      pintar componente parametro propio
        protected void paintComponent(Graphics grf){
            super.paintComponent(grf);
            //grf.drawImage(fondoEscritorio, (getWidth()/2)-(getWidth()/4) , 80, getWidth()/2, getHeight()-(getHeight()/5), null, this);
            grf.drawImage(fondoEscritorio, 0 , 0, getWidth(), getHeight(), null, this);//-(getHeight()/8)
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            
        }
        
    }
    
    
    
    
    
// Diseño del portal de inicio    
    public static class MiPortal extends JDesktopPane{
        private final Image fondoEntrada;
        
        public MiPortal(){
            super();
            this.fondoEntrada = new ImageIcon(getClass().getResource("../Recursos/Imagenes/fondoPortal.png")).getImage();
        }
        
        @Override
        public void paintComponent(Graphics grf){
            super.paintComponent(grf);
            grf.drawImage(fondoEntrada, 0, 0, getWidth(), getHeight(), Color.yellow, this);
        }
        
    }
    

    public static class MiAcceso extends JInternalFrame {

        private final Image fondo;

        public MiAcceso() {
            super();
            this.fondo = new ImageIcon(getClass().getResource("../Recursos/Imagenes/fondoAcceso.png")).getImage();
        }
        
        @Override
        public void paintComponent(Graphics grf){
            super.paintComponent(grf);
            grf.drawImage(fondo, 0, 0, getWidth(), getHeight(), Color.yellow, this);
        }
        
    }
    
    
// clase que me permite mover los botones en pantalla
    // fuentes = http://www.jc-mouse.net/java/crear-y-mover-objetos-en-tiempo-de-ejecucion
public static class MoveButton extends JButton implements MouseMotionListener, MouseListener, FocusListener, ComponentListener, ContainerListener{
        private static final long serialVersionUID = 8L;
 
        private int newX, newY;
        
        private Point pOrigen, pFinal, coordenadas;
        
        private int x, y;
        
        public MoveButton(JDesktopPane vyro){
            super.
            addMouseMotionListener(this);
            addMouseListener(this);
            addFocusListener(this);
            
            
        }
        
        @Override
        public void mousePressed(MouseEvent ev) {
            this.pOrigen = getScreenLocation(ev);
            this.pFinal = this.getLocation();
            this.x = ev.getX();
            this.y = ev.getY();
        }
        
        private Point getScreenLocation(MouseEvent evt) {
            Point cursor = evt.getPoint();
            Point target_location = this.getLocationOnScreen();
            return new Point((int) (target_location.getX() + cursor.getX()), (int) (target_location.getY() + cursor.getY()));
        }
        
        @Override
        public void mouseDragged(MouseEvent ev) { // evento de arrastrar con el raton
            try {
// añadir margen o fronteras al boton
                // nueva posicion x, ...            y,   ...   tamaño:    ancho,                  alto
                //this.setBounds(ev.getX(), ev.getY(), this.getWidth(), this.getHeight());
                //Point coords = MouseInfo.getPointerInfo().getLocation();
                //this.setLocation(coords.x, coords.y);
                //this.setLocation(this.getX() + ev.getX() - (this.getWidth()/2), this.getY() + ev.getY() - (this.getHeight()/2));

                /*Point actual = this.getScreenLocation(ev);
                coordenadas = new Point( (int) actual.getX() - (int) pOrigen.getX(), (int) actual.getY() - (int) pOrigen.getY());
                
                Point newLocation = new Point( (int) (this.pFinal.getX() + coordenadas.getX()), (int) (this.pFinal.getY() + coordenadas.getY()));
                this.setLocation(newLocation);*/
                
                if(ev.getXOnScreen() < x){
                    if((ev.getYOnScreen()-23) < y){
                        this.setLocation(0, 0);
                    }else{
                        this.setLocation(0, (ev.getYOnScreen() - y) - 23);
                    }
                }
                else{
                    if((ev.getYOnScreen()-23) < y){
                        this.setLocation((ev.getXOnScreen() - x), 0);
                    }else{
                        this.setLocation((ev.getXOnScreen() - x) , (ev.getYOnScreen() - y) - 23);
                    }
                }
                
                Vistas.Menu.txtstt.setText("x en pantalla = "+ev.getXOnScreen()+", en el boton = "+x+"; y en pantalla = "+ev.getYOnScreen()+", coord y = "+this.getY()+"; y en el boton = "+y);

            } catch (Exception eh) {
                JOptionPane.showMessageDialog(this, eh.getLocalizedMessage(), "Class MoveButton", 0);
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            this.newX = this.getLocation().x;
            this.newY = this.getLocation().y;
            this.setLocation(newX, newY);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            this.setLocation(newX, newY);
        }

        @Override
        public void componentResized(ComponentEvent e) {
            this.setLocation(newX, newY);
        }

        @Override
        public void componentMoved(ComponentEvent e) {
            this.setLocation(newX, newY);
            
        }

        @Override
        public void componentShown(ComponentEvent e) {
            this.setLocation(newX, newY);
        }

        @Override
        public void componentHidden(ComponentEvent e) {
            this.setLocation(newX, newY);
        }

        @Override
        public void componentAdded(ContainerEvent e) {
            this.setLocation(newX, newY);
        }

        @Override
        public void componentRemoved(ContainerEvent e) {
            this.setLocation(newX, newY);
        }

    }
    

    
}
