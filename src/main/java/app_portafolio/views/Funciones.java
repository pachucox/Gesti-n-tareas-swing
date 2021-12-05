/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_portafolio.views;

import app_portafolio.controller.FlujoController;
import app_portafolio.controller.FuncionController;

import app_portafolio.model.Flujo;
import app_portafolio.model.Funcion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class Funciones extends javax.swing.JFrame {

    DefaultTableModel tabla = new DefaultTableModel();
    FlujoController f = new FlujoController();
    Object flujos[][] = new Object[f.leerFlujos().size()][2];
    FuncionController controler = new FuncionController();
    final Class[] tiposColumnas = new Class[]{
        int.class,
        java.lang.String.class,
        int.class,
        java.lang.String.class,};

    String[] columnas = new String[]{
        "ID funcion",
        "Nombre funcion",
        "ID flujo",
        "Descripcion flujo"
    };

    /**
     * Creates new form Funciones
     */
    public Funciones() throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cmbFlujos.setModel(llenarComboBoxFlujos());
        this.llenarFlujos();
        this.llenarTabla();
        this.llenarCampos();
    }

    void llenarTabla() throws IOException {
        tabla = (DefaultTableModel) this.tablaFunciones.getModel();
        tabla.setRowCount(0);

        String descFlujo = "";
        Object matriz[][] = new Object[this.controler.leerFunciones().size()][4];
        for (int i = 0; i < this.controler.leerFunciones().size(); i++) {
            int idFlujo = this.controler.leerFunciones().get(i).getFlujo_id_flujo();
            for (int x = 0; x < this.f.leerFlujos().size(); x++) {
                if (String.valueOf(idFlujo).equals(String.valueOf(flujos[x][0]))) {
                    descFlujo = this.f.leerFlujos().get(x).getDescripcion_flujo();
                    break;
                }
            }

            matriz[i][0] = String.valueOf((this.controler.leerFunciones().get(i).getId_funcion()));
            matriz[i][1] = this.controler.leerFunciones().get(i).getNombre_funcion();
            matriz[i][2] = idFlujo;
            matriz[i][3] = descFlujo;

        }
        this.tablaFunciones.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                columnas) {
            //Esta variable nos permite conocer de antemano los tipos de datos de cada columna, dentro del TableModel
            Class[] tipos = tiposColumnas;
        });
    }

    void llenarCampos() {
        this.tablaFunciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaFunciones.getSelectedRow();
                int id = Integer.parseInt(tablaFunciones.getModel().getValueAt(fila, 0).toString());
                String nombre = tablaFunciones.getModel().getValueAt(fila, 1).toString();
                String flujo = tablaFunciones.getModel().getValueAt(fila, 3).toString();
                txtNombre.setText(nombre);
                cmbFlujos.setSelectedItem(flujo);
            }
        });
    }

    DefaultComboBoxModel llenarComboBoxFlujos() throws IOException {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        combo.addElement("Seleccione");
        for (Flujo lista : this.f.leerFlujos()) {
            combo.addElement(lista.getDescripcion_flujo());
        }
        return combo;
    }

    void llenarFlujos() throws IOException {
        for (int i = 0; i < this.f.leerFlujos().size(); i++) {
            this.flujos[i][0] = String.valueOf((this.f.leerFlujos().get(i).getId_flujo()));
            this.flujos[i][1] = this.f.leerFlujos().get(i).getDescripcion_flujo();
        }
    }

    void Agregar() throws IOException {
        Funcion f = new Funcion();
        String descFlujo = "";
        String flujo = this.cmbFlujos.getItemAt(this.cmbFlujos.getSelectedIndex());
        int id = 0;
        tabla = (DefaultTableModel) this.tablaFunciones.getModel();
        f.setNombre_funcion(txtNombre.getText());

        for (int i = 0; i < this.f.leerFlujos().size(); i++) {
            if (flujo.equals(String.valueOf(this.flujos[i][1]))) {
                f.setFlujo_id_flujo(((this.f.leerFlujos().get(i).getId_flujo())));
                descFlujo = this.f.leerFlujos().get(i).getDescripcion_flujo();
                break;
            }
        }

        try {
            id = controler.agregarFuncion(f).getId_funcion();
            this.tabla.addRow(new Object[]{
                id, f.getNombre_funcion(), f.getFlujo_id_flujo(), descFlujo
            });
            JOptionPane.showMessageDialog(null, "Funcion Agregada Correctamente");
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Actualizar() throws IOException {
        Funcion fu = new Funcion();
        int fila = this.tablaFunciones.getSelectedRow();
        int id = Integer.parseInt(this.tablaFunciones.getModel().getValueAt(fila, 0).toString());

        fu.setId_funcion(id);
        fu.setNombre_funcion(txtNombre.getText());
        String flujo = this.cmbFlujos.getItemAt(this.cmbFlujos.getSelectedIndex());

        for (int i = 0; i < this.f.leerFlujos().size(); i++) {
            if (flujo.equals(String.valueOf(this.flujos[i][1]))) {
                fu.setFlujo_id_flujo(((this.f.leerFlujos().get(i).getId_flujo())));
                break;
            }
        }

        this.controler.actualizarFuncion(fu);
        JOptionPane.showMessageDialog(null, "Funcion Actualizado Correctamente");
        this.Limpiar();
    }
    
      void Eliminar() throws IOException {
        int fila = this.tablaFunciones.getSelectedRow();
        int id = Integer.parseInt(this.tablaFunciones.getValueAt(fila, 0).toString());
        this.tabla = (DefaultTableModel) this.tablaFunciones.getModel();
        try {
            this.controler.eliminarFuncion(id);
            tabla.removeRow(fila);
            JOptionPane.showMessageDialog(null, "Funcion eliminada correctamente");
            this.Limpiar();
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Limpiar() {
        txtNombre.setText("");
        this.cmbFlujos.setSelectedIndex(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnInicio = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFunciones = new javax.swing.JTable();
        cmbFlujos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        btnAgregar.setForeground(new java.awt.Color(0, 204, 51));
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
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

        jLabel1.setText("Flujo: ");

        jLabel2.setText("Nombre: ");

        tablaFunciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID funcion", "Nombre funcion", "ID flujo", "Descripcion flujo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaFunciones);

        cmbFlujos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addComponent(btnInicio)
                .addContainerGap(218, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(cmbFlujos, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar)
                        .addGap(78, 78, 78))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFlujos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnLimpiar))
                .addGap(42, 42, 42)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnInicio)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed

        try {
            Inicio i = new Inicio();
            i.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        try {
            if (txtNombre.getText().length() < 1) {
                JOptionPane.showMessageDialog(null, "Ingrese un nombre");
            } else if (txtNombre.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
            } else {
                this.Agregar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (this.tablaFunciones.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una funcion en la tabla");
            } else {
                this.Eliminar();
                this.Limpiar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            if (this.tablaFunciones.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una funcion en la tabla");
            } else if (txtNombre.getText().length() < 3) {
                JOptionPane.showMessageDialog(null, "Nombre debe tener al menos 3 letras");
            } else if (this.cmbFlujos.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un flujo");
            } else {
                this.Actualizar();
                this.llenarTabla();
                this.Limpiar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Funciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Funciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Funciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Funciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Flujos().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Flujos.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> cmbFlujos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaFunciones;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
