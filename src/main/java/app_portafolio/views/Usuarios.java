/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_portafolio.views;

import app_portafolio.controller.ClienteController;
import app_portafolio.controller.PerfilController;
import app_portafolio.controller.UsuarioController;
import app_portafolio.model.Perfil;
import app_portafolio.model.Cliente;
import app_portafolio.model.Usuario;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class Usuarios extends javax.swing.JFrame {

    DefaultTableModel tabla = new DefaultTableModel();
    PerfilController  p = new PerfilController();
    Object perfiles[][] = new Object[p.leerPerfiles().size()][2];
    ClienteController c = new ClienteController();
    Object clientes[][] = new Object[c.leerClientes().size()][2];
    UsuarioController controler = new UsuarioController();
    final Class[] tiposColumnas = new Class[]{
        int.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        int.class,
        java.lang.String.class,
        int.class,
        java.lang.String.class,};

    String[] columnas = new String[]{
        "ID",
        "Nombre",
        "Pass",
        "Email",
        "ID Perfil",
        "Nombre Perfil",
        "ID Cliente",
        "Nombre Cliente"

    };

    /**
     * Creates new form Usuarios
     */
    public Usuarios() throws IOException {
        initComponents();
        this.cmbPerfiles.setModel(llenarComboBoxRol());
        this.cmbClientes.setModel(llenarComboBoxCliente());
        this.setLocationRelativeTo(null);
        this.llenarClientes();
        this.llenarPerfiles();
        this.llenarTabla();
        this.llenarCampos();
     
    }

    void llenarTabla() throws IOException {
        tabla = (DefaultTableModel) this.tablaUsuario.getModel();
        tabla.setRowCount(0);
        String nombreCliente = "";
        String nombrePerfil = "";
        Object matriz[][] = new Object[this.controler.leerUsuarios().size()][8];
        for (int i = 0; i < this.controler.leerUsuarios().size(); i++) {
            
          
            int idCliente = this.controler.leerUsuarios().get(i).getCliente_id_cliente();
            int idPerfil = this.controler.leerUsuarios().get(i).getPerfil_id_perfil();
        
            for (int x = 0; x < this.p.leerPerfiles().size(); x++) {
                if (String.valueOf(idPerfil).equals(String.valueOf(perfiles[x][0]))) {
                    nombrePerfil = this.p.leerPerfiles().get(x).getNombre_perfil();
                    break;
                }
            }

            for (int x = 0; x < this.c.leerClientes().size(); x++) {
                if (String.valueOf(idCliente).equals(String.valueOf(clientes[x][0]))) {
                    nombreCliente = this.c.leerClientes().get(x).getNombre_cliente();
                    break;
                }
            }

            matriz[i][0] = String.valueOf((this.controler.leerUsuarios().get(i).getId_usuario()));
            matriz[i][1] = this.controler.leerUsuarios().get(i).getNombre_usuario();
            matriz[i][2] = this.controler.leerUsuarios().get(i).getPass_usuario();
            matriz[i][3] = this.controler.leerUsuarios().get(i).getEmail_usuario();
            matriz[i][4] = idPerfil;
            matriz[i][5]= nombrePerfil;
            matriz[i][6] = idCliente;
            matriz[i][7] =  nombreCliente;
           
            
        }
        this.tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                columnas) {
            //Esta variable nos permite conocer de antemano los tipos de datos de cada columna, dentro del TableModel
            Class[] tipos = tiposColumnas;
        });
    }

    void llenarCampos() {
        this.tablaUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaUsuario.getSelectedRow();
                int id = Integer.parseInt(tablaUsuario.getModel().getValueAt(fila, 0).toString());
                String nombre = tablaUsuario.getModel().getValueAt(fila, 1).toString();
                String pass = tablaUsuario.getModel().getValueAt(fila, 2).toString();
                String email = tablaUsuario.getModel().getValueAt(fila,3).toString();
                txtNombreUsuario.setText(nombre);
                txtPass.setText(pass);
                txtEmail.setText(email);
            }
        });
    }

    DefaultComboBoxModel llenarComboBoxRol() throws IOException {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        combo.addElement("Seleccione");
        for (Perfil lista : this.p.leerPerfiles()) {
            combo.addElement(lista.getNombre_perfil());
        }
        return combo;
    }

    DefaultComboBoxModel llenarComboBoxCliente() throws IOException {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        combo.addElement("Seleccione");
        for (Cliente lista : this.c.leerClientes()) {
            combo.addElement(lista.getNombre_cliente());
        }
        return combo;
    }

    void llenarClientes() throws IOException {
        for (int i = 0; i < this.c.leerClientes().size(); i++) {
            this.clientes[i][0] = String.valueOf((this.c.leerClientes().get(i).getId_cliente()));
            this.clientes[i][1] = this.c.leerClientes().get(i).getNombre_cliente();
        }
    }
    
    void llenarPerfiles() throws IOException {
        for (int i = 0; i < this.p.leerPerfiles().size(); i++) {
            this.perfiles[i][0] = String.valueOf((this.p.leerPerfiles().get(i).getId_perfil()));
            this.perfiles[i][1] = this.p.leerPerfiles().get(i).getNombre_perfil();
        }
    }

    void Eliminar() throws IOException {
        int fila = this.tablaUsuario.getSelectedRow();
        int id = Integer.parseInt(this.tablaUsuario.getValueAt(fila, 0).toString());
        this.tabla = (DefaultTableModel) this.tablaUsuario.getModel();
        try {
            this.controler.eliminarUsuario(id);
            tabla.removeRow(fila);
            JOptionPane.showMessageDialog(null, "Usuario eliminado correctamente");
            this.Limpiar();
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Agregar() throws IOException {
        Usuario u = new Usuario();
        String perfil = this.cmbPerfiles.getItemAt(this.cmbPerfiles.getSelectedIndex());
        String nombrePerfil = "";
        String cliente = this.cmbClientes.getItemAt(this.cmbClientes.getSelectedIndex());
        String nombreCliente= "";
        int id = 0;
        tabla = (DefaultTableModel) this.tablaUsuario.getModel();
        u.setNombre_usuario(txtNombreUsuario.getText());
        u.setPass_usuario(txtPass.getText());
        u.setEmail_usuario(txtEmail.getText());

        for (int i = 0; i < this.p.leerPerfiles().size(); i++) {
            if (perfil.equals(String.valueOf(this.perfiles[i][1]))) {
                u.setPerfil_id_perfil(((this.p.leerPerfiles().get(i).getId_perfil())));
                nombrePerfil = this.p.leerPerfiles().get(i).getNombre_perfil();
                break;
            }
        }
        for (int i = 0; i < this.c.leerClientes().size(); i++) {
            if (cliente.equals(String.valueOf(this.clientes[i][1]))) {
                u.setCliente_id_cliente(((this.c.leerClientes().get(i).getId_cliente())));
                nombreCliente = this.c.leerClientes().get(i).getNombre_cliente();
                break;
            }
        }
        try {
            id = controler.agregarUsuario(u).getId_usuario();
            this.tabla.addRow(new Object[]{
                id, u.getNombre_usuario(), u.getPass_usuario(),u.getEmail_usuario(), u.getPerfil_id_perfil(),nombrePerfil, u.getCliente_id_cliente(),nombreCliente
            });
            JOptionPane.showMessageDialog(null, "Usuario Agregado Correctamente");
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Actualizar() throws IOException {
        Usuario u = new Usuario();
        int fila = this.tablaUsuario.getSelectedRow();
        int id = Integer.parseInt(this.tablaUsuario.getModel().getValueAt(fila, 0).toString());
        String perfil = this.cmbPerfiles.getItemAt(this.cmbPerfiles.getSelectedIndex());
        String cliente = this.cmbClientes.getItemAt(this.cmbClientes.getSelectedIndex());

        for (int i = 0; i < this.p.leerPerfiles().size(); i++) {
            if (perfil.equals(String.valueOf(this.perfiles[i][1]))) {
                u.setPerfil_id_perfil(((this.p.leerPerfiles().get(i).getId_perfil())));
                break;
            }
        }
        for (int i = 0; i < this.c.leerClientes().size(); i++) {
            if (cliente.equals(String.valueOf(this.clientes[i][1]))) {
                u.setCliente_id_cliente(((this.c.leerClientes().get(i).getId_cliente())));
                break;
            }
        }

        u.setId_usuario(id);
        u.setNombre_usuario(txtNombreUsuario.getText());
        u.setPass_usuario(txtPass.getText());
        u.setEmail_usuario(txtEmail.getText());
        
        this.controler.actualizarUsuario(u);
        JOptionPane.showMessageDialog(null, "Usuario Actualizado Correctamente");
        this.Limpiar();
    }

    void Limpiar() {
        txtNombreUsuario.setText("");
        txtPass.setText("");
        txtEmail.setText("");
        this.cmbClientes.setSelectedIndex(0);
        this.cmbPerfiles.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaUsuario = new javax.swing.JTable();
        btnInicio = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        cmbPerfiles = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btnAgregar = new javax.swing.JButton();
        cmbClientes = new javax.swing.JComboBox<>();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Usuarios");

        tablaUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Nombre", "Pass", "Email", "ID Perfil", "Nombre Perfil", "ID Cliente", "Nombre Cliente"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaUsuario);

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nombre usuario :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Contraseña usuario :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Perfil usuario :");

        cmbPerfiles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Cliente usuario :");

        btnAgregar.setForeground(new java.awt.Color(0, 204, 51));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        cmbClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnEliminar.setForeground(new java.awt.Color(255, 51, 51));
        btnEliminar.setText("ELIMINAR");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnLimpiar.setForeground(new java.awt.Color(51, 102, 255));
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Email usuario :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(331, 331, 331))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(285, 285, 285)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel6))
                                        .addGap(28, 28, 28)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtNombreUsuario)
                                            .addComponent(txtPass)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(18, 18, 18)
                                                .addComponent(cmbPerfiles, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addGap(18, 18, 18)
                                                .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAgregar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnActualizar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEliminar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar)))))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(cmbPerfiles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cmbClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnLimpiar))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(btnInicio)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed

        try {
            Inicio i = new Inicio();
            i.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        try {
            if (txtNombreUsuario.getText().length() < 4) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario debe contener al menos 4 letras");
            } else if (this.cmbClientes.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente");
            } else if (this.cmbPerfiles.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un perfil");
            } else {
                this.Agregar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (this.tablaUsuario.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario en la tabla");
            } else {
                this.Eliminar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            if (this.tablaUsuario.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario en la tabla");
            } else if (txtNombreUsuario.getText().length() < 5) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario debe contener al menos 5 letras");
            } else if (this.cmbClientes.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente");
            } else if (this.cmbPerfiles.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un perfil");
            } else {
                this.Actualizar();
                this.llenarTabla();
            }
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Usuarios().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnInicio;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbClientes;
    private javax.swing.JComboBox<String> cmbPerfiles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaUsuario;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNombreUsuario;
    private javax.swing.JPasswordField txtPass;
    // End of variables declaration//GEN-END:variables
}
