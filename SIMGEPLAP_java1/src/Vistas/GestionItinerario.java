
package Vistas;

import Recursos.ConexionBD;
import Recursos.Time;
import com.toedter.calendar.JCalendar;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

@SuppressWarnings("serial")
public class GestionItinerario extends javax.swing.JInternalFrame {

    DefaultTableModel dtmUsers = new DefaultTableModel(); 
    DefaultTableModel dtmItineario = new DefaultTableModel(); 
    
    private final String[] headersUsers;
    private final String[] headersItinerario;
    
    private int filaTablaUsuarios, columnaTablaUsuarios, filaTablaItinerario, columnaTablaItinerario;
    private Object seleccionUsuario, seleccionItinerario;
    
    private static Time time;
    
    private final Connection condb; // creo la conexion a la base de datos de este elemento (formulario de gestion del itinerario)
    
    public GestionItinerario( JDesktopPane Escritorio ) {
        initComponents();
        
        setClosable(true);
        this.iconable = true;
        this.setFrameIcon(new ImageIcon(getClass().getResource("/Recursos/Imagenes/iconItinerario.png")));
        Dimension espacioPantalla = Escritorio.getSize(), mySpc = this.getSize();
        this.setLocation(( espacioPantalla.width / 3 ), ( ( espacioPantalla.height - mySpc.height ) / 3 ) );
        this.setToolTipText("Programa los Accesos a Usuarios");
        
        condb = ConexionBD.GetConnection();
        time = new Time();
      
        this.headersUsers = new String[]{"Código", "Identificación", "Nombre", "Rol"};
        dtmUsers.setColumnIdentifiers( headersUsers );
        tablaUsuarios.setModel( dtmUsers );     
        TableColumn col1 = tablaUsuarios.getColumn("Código");
        col1.setPreferredWidth(10);
        TableColumn col2 = tablaUsuarios.getColumn("Identificación");
        col2.setPreferredWidth(10);
        TableColumn col3 = tablaUsuarios.getColumn("Rol");
        col3.setPreferredWidth(15);
        
        this.headersItinerario = new String[]{ "Código", "Nombre", "Fecha disponible" };
        dtmItineario.setColumnIdentifiers( headersItinerario );
        tablaItinerario.setModel( dtmItineario );
        
        mostrarTablaUsuarios();
        MostrarItinerario();
        
        //btnEditarItinerario_.setEnabled(false);
        
    }

    
//  MÉTODOS CONFECCIONADOS POR EL PROGRAMADOR  --->>>>
    private void mostrarTablaUsuarios() {
        try {
            PreparedStatement qry = condb.prepareStatement("select * from usuarios");
            ResultSet rspta = qry.executeQuery();
            ResultSetMetaData metadatos = rspta.getMetaData();

            ArrayList<Object[]> registros = new ArrayList<>();

            while (rspta.next()) {

                Object[] fila = new Object[metadatos.getColumnCount()];

                for (int f = 0; f < fila.length; f++) {
                    fila[f] = rspta.getObject(f + 1);
                }

                String nombreCompleto = fila[2].toString() + " " + fila[3].toString();
                Object[] filtro = new Object[]{fila[0], fila[1], nombreCompleto, fila[8]};

                registros.add(filtro);

            }

            registros.stream().forEach((registro) -> {
                dtmUsers.addRow(registro);
            });

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "" + ex.getLocalizedMessage());
        }
    }

    private void MostrarItinerario() {

        while (tablaItinerario.getRowCount() > 0) {
            for (int i = 0; i < tablaItinerario.getRowCount(); i++) {
                dtmItineario.removeRow(i);
            }
        }

        try {
            PreparedStatement qry = condb.prepareStatement("select * from itinerario");
            ResultSet rspta = qry.executeQuery();
            ResultSetMetaData metadataRspta = rspta.getMetaData();

            while (rspta.next()) {
                Object[] fila = new Object[metadataRspta.getColumnCount()];

                for (int f = 0; f < fila.length; f++) {
                    fila[f] = rspta.getObject(f + 1);
                }

                dtmItineario.addRow(fila);

            }
       
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "" + ex.getLocalizedMessage());
        }

    }
    
//  JOP selector de fechas
    private String fechaOK(){
        JCalendar calendario = new JCalendar();
        Object dialogo[] = {"Seleccione la fecha de Disponibilidad para el Usuario \n\n", calendario};
        Object opciones[] = {"Ok"};
        JOptionPane.showOptionDialog(null, dialogo, "Contraseña", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, dialogo);
        String fechaok = Time.formatofecha.format(calendario.getCalendar().getTime());
        return fechaok;
        //parámetros del JOP showOptionDialog -->>
        //JOptionPane.showOptionDialog(componente, dialogo/contenido, titulo, OpcionesTipo, TipoMensaje, icono, ArrayOpcionesRespuesta, opcionesEnDialogo);
        //######################################//
    }
// ======================================================
    
//  ######################################################################################################################
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnProgramarUsuario_ = new javax.swing.JButton();
        btnEditarItinerario_ = new javax.swing.JButton();
        btnDesprogramarUsuario_ = new javax.swing.JButton();
        ScrollTablaUsuarios = new javax.swing.JScrollPane();
        tablaUsuarios = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        ScrollTablaItinerario = new javax.swing.JScrollPane();
        tablaItinerario = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex){
                return false;
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        btnProgramarUsuario_.setBackground(new java.awt.Color(51, 153, 0));
        btnProgramarUsuario_.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnProgramarUsuario_.setForeground(new java.awt.Color(255, 255, 255));
        btnProgramarUsuario_.setText("Programar");
        btnProgramarUsuario_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProgramarUsuario_ActionPerformed(evt);
            }
        });

        btnEditarItinerario_.setBackground(new java.awt.Color(51, 153, 0));
        btnEditarItinerario_.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnEditarItinerario_.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarItinerario_.setText("Editar Entrada");
        btnEditarItinerario_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarItinerario_ActionPerformed(evt);
            }
        });

        btnDesprogramarUsuario_.setBackground(new java.awt.Color(51, 153, 0));
        btnDesprogramarUsuario_.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        btnDesprogramarUsuario_.setForeground(new java.awt.Color(255, 255, 255));
        btnDesprogramarUsuario_.setText("Eliminar de la Lista");
        btnDesprogramarUsuario_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDesprogramarUsuario_ActionPerformed(evt);
            }
        });

        tablaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaUsuarios.getTableHeader().setReorderingAllowed(false);
        tablaUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaUsuariosMouseClicked(evt);
            }
        });
        ScrollTablaUsuarios.setViewportView(tablaUsuarios);

        tablaItinerario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaItinerario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaItinerarioMouseClicked(evt);
            }
        });
        ScrollTablaItinerario.setViewportView(tablaItinerario);

        jLabel1.setText("Programacion de Acceso a SIMGEPLAP");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/baner37.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(btnDesprogramarUsuario_))
            .addGroup(layout.createSequentialGroup()
                .addGap(491, 491, 491)
                .addComponent(ScrollTablaItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btnProgramarUsuario_, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(160, 160, 160)
                .addComponent(btnEditarItinerario_, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(ScrollTablaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnDesprogramarUsuario_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(ScrollTablaItinerario, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnProgramarUsuario_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(btnEditarItinerario_, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(ScrollTablaUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(4, 4, 4))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Seleccion de un Campo de la tabla Itinerarios
    private void tablaUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaUsuariosMouseClicked
        
        filaTablaUsuarios = tablaUsuarios.rowAtPoint(evt.getPoint());
        columnaTablaUsuarios = tablaUsuarios.columnAtPoint(evt.getPoint());
        
        if( filaTablaUsuarios > -1 && columnaTablaUsuarios > -1 ){
            this.seleccionUsuario = dtmUsers.getValueAt( filaTablaUsuarios, 0);
        }
        
    }//GEN-LAST:event_tablaUsuariosMouseClicked

    
    @SuppressWarnings("UseSpecificCatch")
    private void btnEditarItinerario_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarItinerario_ActionPerformed
       
        try {
            String nuevaFecha = fechaOK();
            
            Statement mando = condb.createStatement();
            
            int editado = mando.executeUpdate("update itinerario set fechaok='"+nuevaFecha+"' where iti_codUser='"+seleccionItinerario+"'");
            
            if ( editado > 0 ){
                JOptionPane.showMessageDialog(this, "Fecha Actualizada");
            }
            MostrarItinerario();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Editar Itinerario", 0);
        }

    }//GEN-LAST:event_btnEditarItinerario_ActionPerformed

    
    private void btnProgramarUsuario_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProgramarUsuario_ActionPerformed
   
        try {
            Statement consulta1 = condb.createStatement();
            ResultSet confirmProg = consulta1.executeQuery("select * from itinerario where iti_codUser='" + seleccionUsuario + "'");

            if (confirmProg.next()) {
                String mensajeVentana = "El Usuario ya está programado, desea editar su tiempo de Acceso?";
                int eleccion = JOptionPane.showConfirmDialog(this, mensajeVentana, "Editar?", JOptionPane.OK_CANCEL_OPTION);
                if (eleccion == 0) {
                    String nuevaFecha = fechaOK();
                    Statement consulta2 = condb.createStatement();
                    int success = consulta2.executeUpdate("update itinerario set fechaok='" + nuevaFecha + "' "
                            + "where iti_codUser='" + confirmProg.getString("iti_codUser") + "'");
                    if (success > 0) {
                        JOptionPane.showMessageDialog(this, "Fecha Actualizada");
                    }
                }
            } else {

                Statement query = condb.createStatement();
                ResultSet hayUser = query.executeQuery("select * from usuarios where codUser='" + seleccionUsuario + "'");

                if (hayUser.next()) {

                    String fechaDisponible = fechaOK();

                    query.execute("insert into itinerario values ('" + hayUser.getString("codUser") + "',"
                            + "'" + hayUser.getString("nombres") + " " + hayUser.getString("apellidos") + "',"
                            + "'" + fechaDisponible + "','" + time.getHour() + "')");

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Gestion Itinerario", 0);
        }

        MostrarItinerario();

    }//GEN-LAST:event_btnProgramarUsuario_ActionPerformed

    private void btnDesprogramarUsuario_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDesprogramarUsuario_ActionPerformed
     
        try {
            Statement query = condb.createStatement();

            String qry = "delete from itinerario where coduser='" + seleccionItinerario + "'";

            int confirm = query.executeUpdate(qry);

            if (confirm > 0) {
                JOptionPane.showMessageDialog(null, "Usuario quitado de la Lista");
                MostrarItinerario();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Desprogramar Usuario", 0);
        }


    }//GEN-LAST:event_btnDesprogramarUsuario_ActionPerformed

    private void tablaItinerarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaItinerarioMouseClicked
        
        filaTablaItinerario = tablaItinerario.rowAtPoint(evt.getPoint());
        columnaTablaItinerario = tablaItinerario.columnAtPoint(evt.getPoint());
        
        if( filaTablaItinerario > -1 && columnaTablaItinerario > -1 ){
            this.seleccionItinerario = dtmItineario.getValueAt( filaTablaItinerario, 0);
        }
        
    }//GEN-LAST:event_tablaItinerarioMouseClicked
  
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane ScrollTablaItinerario;
    private javax.swing.JScrollPane ScrollTablaUsuarios;
    private javax.swing.JButton btnDesprogramarUsuario_;
    private javax.swing.JButton btnEditarItinerario_;
    private javax.swing.JButton btnProgramarUsuario_;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTable tablaItinerario;
    private javax.swing.JTable tablaUsuarios;
    // End of variables declaration//GEN-END:variables
}
