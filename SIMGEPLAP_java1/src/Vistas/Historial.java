/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controlador.PDF;
import Recursos.*;
import static Vistas.Menu.Escritorio;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.*;

/**
 *
 * @author Usuario
 */
@SuppressWarnings("serial")
public class Historial extends javax.swing.JInternalFrame {
    
    DefaultTableModel dtm = new DefaultTableModel();
    private final String encabezadosTabla[] = {"Registro No.","Usuario","Lectura","Variable","Fecha Incidente","Hora Incidente"};

    
    public Historial( JDesktopPane contenedor ) {
        initComponents();
        
        setClosable(true);
        this.title = "Registro de Incidencias";
        this.iconable = true;
        this.resizable = true;
        this.setFrameIcon(new ImageIcon(getClass().getResource("/Recursos/Imagenes/iconHistorial.png")));
        this.setToolTipText("Consulta el Comportamiento de la Planta");
        
        Dimension areaTrabajo = contenedor.getSize();
        //Dimension espacioOcupado = this.getSize(); // no borrar esta línea
        /*int ancho = ( areaTrabajo.width - espacioOcupado.width ) / 2;    //Este par de lineas se ocupan de posicionar el JInternalFrame en todo el centro de la pantalla
        int altura = ( areaTrabajo.height - espacioOcupado.height ) / 2;*/
        int ancho = ( areaTrabajo.width / 4 );
        int altura = ( areaTrabajo.height / 2 ) * 1;
        this.setLocation( ancho, 10 );
        
        dtm.setColumnIdentifiers(encabezadosTabla);
        TablaRegistros.setModel(dtm);
        MostrarRegistros();
    }

      private void MostrarRegistros(){
        
         //try {
             try (Connection dtbs = ConexionBD.GetConnection()) {
                 PreparedStatement qry = dtbs.prepareStatement( "select * from incidencias" );
                 ResultSet rspta = qry.executeQuery();
                 ResultSetMetaData metadatos = rspta.getMetaData();
                 
                 ArrayList<Object[]> incidencias = new ArrayList<>();
                 
                 while ( rspta.next() ){
                     
                     Object[] fila = new Object[ metadatos.getColumnCount() ];
                     
                     for ( int i = 0; i < fila.length; i++ ){
                         fila[i] = rspta.getObject(i + 1);
                     }
                     incidencias.add( fila );
                 }
                 
                 incidencias.stream().forEach((incidente) -> {
                     dtm.addRow(incidente);
                 });
       //      }
            
        } catch (SQLException ex) {
            //Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, ex.getLocalizedMessage(), "Historial Tabla", 0);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scroll_TablaRegistros = new javax.swing.JScrollPane();
        TablaRegistros = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        btn_verBalance_ = new javax.swing.JButton();
        btnTestPDF = new javax.swing.JButton();
        lblfondo = new javax.swing.JLabel();

        TablaRegistros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        TablaRegistros.getTableHeader().setReorderingAllowed(false);
        scroll_TablaRegistros.setViewportView(TablaRegistros);

        btn_verBalance_.setBackground(new java.awt.Color(51, 153, 0));
        btn_verBalance_.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btn_verBalance_.setForeground(new java.awt.Color(255, 255, 255));
        btn_verBalance_.setText("Ver Balance");
        btn_verBalance_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_verBalance_ActionPerformed(evt);
            }
        });

        btnTestPDF.setBackground(new java.awt.Color(51, 153, 0));
        btnTestPDF.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnTestPDF.setForeground(new java.awt.Color(255, 255, 255));
        btnTestPDF.setText("PDF");
        btnTestPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestPDFActionPerformed(evt);
            }
        });

        lblfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/baner36.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(480, 480, 480)
                .addComponent(btn_verBalance_, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(220, 220, 220)
                .addComponent(btnTestPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scroll_TablaRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblfondo)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btn_verBalance_, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnTestPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(scroll_TablaRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(lblfondo)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_verBalance_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_verBalance_ActionPerformed
        
        Estadistica grafico = new Estadistica();
        Escritorio.add( grafico );
        grafico.setVisible( true );
        grafico.toFront();
        
    }//GEN-LAST:event_btn_verBalance_ActionPerformed

    private void btnTestPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestPDFActionPerformed
       
        PDF.crear_yGuardarPDF();
        
    }//GEN-LAST:event_btnTestPDFActionPerformed

  
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaRegistros;
    private javax.swing.JButton btnTestPDF;
    private javax.swing.JButton btn_verBalance_;
    private javax.swing.JLabel lblfondo;
    private javax.swing.JScrollPane scroll_TablaRegistros;
    // End of variables declaration//GEN-END:variables
}
