/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_portafolio.views;

import app_portafolio.controller.ClienteController;
import app_portafolio.model.Cliente;
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
public class Clientes extends javax.swing.JFrame {
    
    
    DefaultTableModel tabla = new DefaultTableModel();
    ClienteController controler = new ClienteController();
    final Class[] tiposColumnas = new Class[]{
            int.class,
            java.lang.String.class,
            java.lang.String.class,
     };
    
    String[] columnas = new String[]{
            "ID",
            "Rut",
            "Nombre"
        };
    

    /**
     * Creates new form Clientes
     */
    public Clientes() throws IOException{
        initComponents();
        this.setLocationRelativeTo(null);
        this.llenarTabla();
        this.llenarCampos();
    }
    
    
    void llenarTabla() throws IOException{
        tabla = (DefaultTableModel) this.tablaClientes.getModel();
        tabla.setRowCount(0);
        Object matriz[][] = new Object[this.controler.leerClientes().size()][3];
        for (int i = 0; i < this.controler.leerClientes().size(); i++) {
            matriz[i][0] = String.valueOf((this.controler.leerClientes().get(i).getId_cliente()));
            matriz[i][1] = this.controler.leerClientes().get(i).getRut_cliente();
            matriz[i][2] = this.controler.leerClientes().get(i).getNombre_cliente();
        }
        this.tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                columnas) {
            //Esta variable nos permite conocer de antemano los tipos de datos de cada columna, dentro del TableModel
            Class[] tipos = tiposColumnas;
        });
    }
    
      void llenarCampos(){
        this.tablaClientes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaClientes.getSelectedRow();
                int id = Integer.parseInt(tablaClientes.getModel().getValueAt(fila, 0).toString());
                String rut = tablaClientes.getModel().getValueAt(fila, 1).toString();
                String nombre = tablaClientes.getModel().getValueAt(fila, 2).toString();
                txtRut.setText(rut);
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
        jLabel2 = new javax.swing.JLabel();
        txtRut = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaClientes = new javax.swing.JTable();
        btnInicio = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setText("Nombre : ");

        jLabel2.setText("Rut : ");

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Rut", "Nombre"
            }
        ));
        jScrollPane1.setViewportView(tablaClientes);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnInicio)
                        .addGap(142, 142, 142)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(74, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(69, 69, 69))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnLimpiar))
                .addGap(59, 59, 59)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(btnInicio)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
    
            try{
            Inicio i = new Inicio();
            i.setVisible(true);
            this.dispose();    
        }catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        try {
            if(txtRut.getText().length() < 9){
                JOptionPane.showMessageDialog(null, "Ingrese un rut válido");
            }else if(txtNombre.getText().length() == 0 ){
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
            }else{
                this.Agregar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (this.tablaClientes.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un cliente en la tabla");
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
            if (this.tablaClientes.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario en la tabla");
            }else if(txtRut.getText().length() < 9){
                JOptionPane.showMessageDialog(null, "Ingrese un rut válido");
            }else if(txtNombre.getText().length()==0){
                JOptionPane.showMessageDialog(null, "Debe ingresar un nombre");
            }else{
                this.Actualizar();
                this.llenarTabla();
                this.Limpiar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

     
    void Agregar() throws IOException{
        Cliente c = new Cliente();
        int id = 0;
        tabla = (DefaultTableModel) this.tablaClientes.getModel();
        c.setRut_cliente(txtRut.getText());
        c.setNombre_cliente(txtNombre.getText());
       
        try {
            id = controler.agregarCliente(c).getId_cliente();
            this.tabla.addRow(new Object[]{
                id, c.getRut_cliente(),c.getNombre_cliente()
            });
            JOptionPane.showMessageDialog(null, "Cliente Agregado Correctamente");
            this.Limpiar();
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
      void Actualizar() throws IOException{
       Cliente c = new Cliente();
       int fila = this.tablaClientes.getSelectedRow();
       int id = Integer.parseInt(this.tablaClientes.getModel().getValueAt(fila, 0).toString());
       
        c.setId_cliente(id);
        c.setRut_cliente(txtRut.getText());
        c.setNombre_cliente(txtNombre.getText());

        this.controler.actualizarCliente(c);
       JOptionPane.showMessageDialog(null, "Cliente Actualizado Correctamente");
       this.Limpiar();
   }
    
     void Eliminar() throws IOException {
        int fila = this.tablaClientes.getSelectedRow();
        int id = Integer.parseInt(this.tablaClientes.getValueAt(fila, 0).toString());
        this.tabla = (DefaultTableModel) this.tablaClientes.getModel();
        try {
            this.controler.eliminarCliente(id);
            tabla.removeRow(fila);
            JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
            this.Limpiar();
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    void Limpiar() {
        txtRut.setText("");
        txtNombre.setText("");

    }

    
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
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Clientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                 try{
                    new Clientes().setVisible(true);    
                }catch (IOException ex) {
                    Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRut;
    // End of variables declaration//GEN-END:variables
}
