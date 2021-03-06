/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_portafolio.views;

import app_portafolio.controller.PerfilController;
import app_portafolio.model.Perfil;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class Perfiles extends javax.swing.JFrame {

    DefaultTableModel tabla = new DefaultTableModel();
    PerfilController controler = new PerfilController();
    final Class[] tiposColumnas = new Class[]{
            int.class,
            java.lang.String.class,
            
     };
    
    String[] columnas = new String[]{
            "ID Perfil",
            "Nombre Perfil"
        };
    /**
     * Creates new form Perfiles
     */
    public Perfiles()throws IOException{
        initComponents();
        this.setLocationRelativeTo(null);
        this.llenarTabla();
        this.llenarCampos();
    }
    
    
    void llenarTabla() throws IOException {
        tabla = (DefaultTableModel) this.tablaPerfiles.getModel();
        tabla.setRowCount(0);
        Object matriz[][] = new Object[this.controler.leerPerfiles().size()][2];
        for (int i = 0; i < this.controler.leerPerfiles().size(); i++) {
            matriz[i][0] = String.valueOf((this.controler.leerPerfiles().get(i).getId_perfil()));
            matriz[i][1] = this.controler.leerPerfiles().get(i).getNombre_perfil();
        }
        this.tablaPerfiles.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                columnas) {
            //Esta variable nos permite conocer de antemano los tipos de datos de cada columna, dentro del TableModel
            Class[] tipos = tiposColumnas;
        });
    }
    
    void llenarCampos(){
        this.tablaPerfiles.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaPerfiles.getSelectedRow();
                int id = Integer.parseInt(tablaPerfiles.getModel().getValueAt(fila, 0).toString());
                String nombre = tablaPerfiles.getModel().getValueAt(fila, 1).toString();
                txtNombre.setText(nombre);
            }
        });
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
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaPerfiles = new javax.swing.JTable();
        btnInicio = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 390));
        setPreferredSize(new java.awt.Dimension(500, 390));
        setSize(new java.awt.Dimension(500, 390));

        jLabel1.setText("Nombre perfil :");

        tablaPerfiles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID Perfil", "Nombre Perfil"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaPerfiles);

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

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

        btnAgregar.setForeground(new java.awt.Color(0, 204, 51));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(222, 222, 222)
                        .addComponent(btnInicio)))
                .addContainerGap(54, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar)
                .addGap(88, 88, 88))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnLimpiar))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addComponent(btnInicio)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (this.tablaPerfiles.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un perfil en la tabla");
            }else{
                this.Eliminar();
                this.Limpiar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            if (this.tablaPerfiles.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un perfil en la tabla");
            }else if(txtNombre.getText().length()<4 ){
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre v??lido");
            }else{
                this.Actualizar();
                this.llenarTabla();
                this.Limpiar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            if (txtNombre.getText().length() < 4) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre con al menos 4 letras");
            } else {
                this.Agregar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        try {
            Inicio i = new Inicio();
            i.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInicioActionPerformed

     
    void Agregar() throws IOException{
        Perfil p = new Perfil();
        int id = 0;
        tabla = (DefaultTableModel) this.tablaPerfiles.getModel();
        p.setNombre_perfil(txtNombre.getText());
       
        try {
            id = controler.agregarPerfil(p).getId_perfil();
            this.tabla.addRow(new Object[]{
                id,p.getNombre_perfil()
            });
            JOptionPane.showMessageDialog(null, "Perfil Agregado Correctamente");
            this.Limpiar();
        } catch (IOException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
      void Actualizar() throws IOException{
       Perfil p = new Perfil();
       int fila = this.tablaPerfiles.getSelectedRow();
       int id = Integer.parseInt(this.tablaPerfiles.getModel().getValueAt(fila, 0).toString());
       
        p.setId_perfil(id);
        p.setNombre_perfil(txtNombre.getText());

        this.controler.actualizarPerfil(p);
       JOptionPane.showMessageDialog(null, "Perfil Actualizado Correctamente");
       this.Limpiar();
   }
    
     void Eliminar() throws IOException {
        int fila = this.tablaPerfiles.getSelectedRow();
        int id = Integer.parseInt(this.tablaPerfiles.getValueAt(fila, 0).toString());
        this.tabla = (DefaultTableModel) this.tablaPerfiles.getModel();
        try {
            this.controler.eliminarPerfil(id);
            tabla.removeRow(fila);
            JOptionPane.showMessageDialog(null, "Perfil eliminado correctamente");
            this.Limpiar();
        } catch (IOException ex) {
            Logger.getLogger(Perfiles.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    void Limpiar() {
        
        txtNombre.setText("");

    }
    
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
            java.util.logging.Logger.getLogger(Perfiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Perfiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Perfiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Perfiles.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Perfiles().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Perfiles.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaPerfiles;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
