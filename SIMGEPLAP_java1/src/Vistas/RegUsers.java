
package Vistas;

import Controlador.Crypt;
import Controlador.*;
import static Vistas.Menu.Escritorio;
import java.awt.Dimension;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Grupo de Desarrollo ADSI38
 */
@SuppressWarnings("serial")
public class RegUsers extends javax.swing.JInternalFrame {
    
    private String contraseñaGenerada;

    public RegUsers( JDesktopPane Escritorio ) {
        initComponents();
        
        this.title = "Registro de Usuarios";
        this.closable = true;
        this.iconable = true;
        Dimension ScreenSpace = Escritorio.getSize(), mySpc = this.getSize();
        this.setLocation( ( ScreenSpace.width / 5 ), ( ( ScreenSpace.height - mySpc.height ) / 2 ) );
        this.setFrameIcon(new ImageIcon(getClass().getResource("/Recursos/Imagenes/iconUsers.png")));
        this.setToolTipText("Registra un nuevo Usuario");
    }

    private void limpiar() {
        txtapes.setText(null);
        txtemail.setText(null);
        txtid.setText(null);
        txtname.setText(null);
        txttel.setText(null);
        txtuser.setText(null);
        slcrol.setSelectedIndex(0);
        btnGenerarContraseña_.setEnabled(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtid = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtapes = new javax.swing.JTextField();
        txtuser = new javax.swing.JTextField();
        btnGenerarContraseña_ = new javax.swing.JButton();
        txtemail = new javax.swing.JTextField();
        txttel = new javax.swing.JTextField();
        slcrol = new javax.swing.JComboBox();
        btn_ComprobarUsuario_ = new javax.swing.JButton();
        btn_EliminarRegistro_ = new javax.swing.JButton();
        btn_RegistrarUsuario_ = new javax.swing.JButton();
        btnCancelar_ = new javax.swing.JButton();
        lblid = new javax.swing.JLabel();
        lblname = new javax.swing.JLabel();
        lblapes = new javax.swing.JLabel();
        lbluser = new javax.swing.JLabel();
        lblpass = new javax.swing.JLabel();
        lblemail = new javax.swing.JLabel();
        lbltel = new javax.swing.JLabel();
        lblrol = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 51, 0));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtid.setBackground(new java.awt.Color(227, 227, 227));
        txtid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 3));
        txtid.setMaximumSize(new java.awt.Dimension(8, 20));
        txtid.setMinimumSize(new java.awt.Dimension(8, 20));
        getContentPane().add(txtid, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 130, 200, -1));

        txtname.setBackground(new java.awt.Color(227, 227, 227));
        txtname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 3));
        txtname.setMaximumSize(new java.awt.Dimension(8, 20));
        txtname.setMinimumSize(new java.awt.Dimension(8, 20));
        getContentPane().add(txtname, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, 200, -1));

        txtapes.setBackground(new java.awt.Color(227, 227, 227));
        txtapes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 3));
        txtapes.setMaximumSize(new java.awt.Dimension(8, 20));
        txtapes.setMinimumSize(new java.awt.Dimension(8, 20));
        getContentPane().add(txtapes, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 200, -1));

        txtuser.setBackground(new java.awt.Color(227, 227, 227));
        txtuser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 3));
        txtuser.setMaximumSize(new java.awt.Dimension(8, 20));
        txtuser.setMinimumSize(new java.awt.Dimension(8, 20));
        getContentPane().add(txtuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 200, -1));

        btnGenerarContraseña_.setText("Generar");
        btnGenerarContraseña_.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 3));
        btnGenerarContraseña_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarContraseña_ActionPerformed(evt);
            }
        });
        getContentPane().add(btnGenerarContraseña_, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 200, -1));

        txtemail.setBackground(new java.awt.Color(227, 227, 227));
        txtemail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 3));
        txtemail.setMaximumSize(new java.awt.Dimension(8, 20));
        txtemail.setMinimumSize(new java.awt.Dimension(8, 20));
        getContentPane().add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 330, 200, -1));

        txttel.setBackground(new java.awt.Color(227, 227, 227));
        txttel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 3));
        txttel.setMaximumSize(new java.awt.Dimension(8, 20));
        txttel.setMinimumSize(new java.awt.Dimension(8, 20));
        getContentPane().add(txttel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 370, 200, -1));

        slcrol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Aprendiz", "Control Tecnico" }));
        slcrol.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 0), 3));
        getContentPane().add(slcrol, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 410, 146, -1));

        btn_ComprobarUsuario_.setText("Comprobar");
        btn_ComprobarUsuario_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ComprobarUsuario_ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_ComprobarUsuario_, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 470, 110, -1));

        btn_EliminarRegistro_.setText("Eliminar");
        getContentPane().add(btn_EliminarRegistro_, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 110, -1));

        btn_RegistrarUsuario_.setText("Registrar");
        btn_RegistrarUsuario_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RegistrarUsuario_ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_RegistrarUsuario_, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, 110, -1));

        btnCancelar_.setText("Cancelar");
        btnCancelar_.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelar_ActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar_, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 510, 83, -1));

        lblid.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lblid.setForeground(new java.awt.Color(255, 255, 255));
        lblid.setText("Numero de Identificación:");
        getContentPane().add(lblid, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 150, -1));

        lblname.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lblname.setForeground(new java.awt.Color(255, 255, 255));
        lblname.setText("Nombre/s de Usuario:");
        getContentPane().add(lblname, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        lblapes.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lblapes.setForeground(new java.awt.Color(255, 255, 255));
        lblapes.setText("Apellidos:");
        getContentPane().add(lblapes, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 60, 20));

        lbluser.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lbluser.setForeground(new java.awt.Color(255, 255, 255));
        lbluser.setText("Nick de Usuario:");
        getContentPane().add(lbluser, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 250, 100, -1));

        lblpass.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lblpass.setForeground(new java.awt.Color(255, 255, 255));
        lblpass.setText("Contraseña de Ingreso:");
        getContentPane().add(lblpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 140, -1));

        lblemail.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lblemail.setForeground(new java.awt.Color(255, 255, 255));
        lblemail.setText("Correo Electrónico:");
        getContentPane().add(lblemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 120, -1));

        lbltel.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lbltel.setForeground(new java.awt.Color(255, 255, 255));
        lbltel.setText("Teléfono:");
        getContentPane().add(lbltel, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 370, 60, -1));

        lblrol.setFont(new java.awt.Font("Impact", 0, 14)); // NOI18N
        lblrol.setForeground(new java.awt.Color(255, 255, 255));
        lblrol.setText("Rol:");
        getContentPane().add(lblrol, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 410, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/banerRegUser.png"))); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelar_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelar_ActionPerformed

        limpiar();

    }//GEN-LAST:event_btnCancelar_ActionPerformed

    private void btn_RegistrarUsuario_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RegistrarUsuario_ActionPerformed

        Usuario nuevo = new Usuario( txtid.getText(), txtname.getText(), txtapes.getText(), txtuser.getText(), contraseñaGenerada, txtemail.getText(), txttel.getText(), ""+slcrol.getSelectedItem() );
        if (Incidencias.newUser(nuevo)) {
            limpiar();
            JOptionPane.showMessageDialog(null, "Usuario Registrado", "Grabado en la Base de Datos", JOptionPane.INFORMATION_MESSAGE);
        }

    }//GEN-LAST:event_btn_RegistrarUsuario_ActionPerformed

    private void btnGenerarContraseña_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarContraseña_ActionPerformed

        Object[] desc = {"Aceptar", "Volver a Generar"};

        int elec;
        //while (elec != 0) {
            do {
                contraseñaGenerada = Crypt.GenerarContraseña();
                //parámetros del JOP showOptionDialog -->>
                //JOptionPane.showOptionDialog(componente, dialogo, titulo, OpcionesTipo, TipoMensaje, icono, ArrayOpcionesRespuesta, opcionesEnDialogo);
                //######################################//
                Object[] dialog = {"Password generado para el Usuario\n" + contraseñaGenerada};

                elec = JOptionPane.showOptionDialog(this, dialog, "Incidencias, newUser", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, desc, dialog);

                if ( elec == 0 ){
                    this.btnGenerarContraseña_.setEnabled(false);
                }

            } while (elec != 0 && elec != -1);

            // JOptionPane.showMessageDialog(null, "Password generado para el Usuario\n" + contraseñaGenerada, "Incidencias, newUser", 1);
    }//GEN-LAST:event_btnGenerarContraseña_ActionPerformed

    private void btn_ComprobarUsuario_ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ComprobarUsuario_ActionPerformed

        if (Menu.ventanaProgramacion.isVisible()) {
            Menu.ventanaProgramacion.toFront();
            if (Menu.ventanaProgramacion.isVisible()){
                try {
                    Menu.ventanaProgramacion.setIcon(false);
                } catch (PropertyVetoException ex) {
                    System.out.print(ex.getLocalizedMessage());
                }
            }
        } else {
            Escritorio.add(Menu.ventanaProgramacion);
            Menu.ventanaProgramacion.setVisible(true);
            Menu.ventanaProgramacion.toFront();
        }
        
    }//GEN-LAST:event_btn_ComprobarUsuario_ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar_;
    private javax.swing.JButton btnGenerarContraseña_;
    private javax.swing.JButton btn_ComprobarUsuario_;
    private javax.swing.JButton btn_EliminarRegistro_;
    private javax.swing.JButton btn_RegistrarUsuario_;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblapes;
    private javax.swing.JLabel lblemail;
    private javax.swing.JLabel lblid;
    private javax.swing.JLabel lblname;
    private javax.swing.JLabel lblpass;
    private javax.swing.JLabel lblrol;
    private javax.swing.JLabel lbltel;
    private javax.swing.JLabel lbluser;
    private javax.swing.JComboBox slcrol;
    private javax.swing.JTextField txtapes;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txttel;
    private javax.swing.JTextField txtuser;
    // End of variables declaration//GEN-END:variables
}
