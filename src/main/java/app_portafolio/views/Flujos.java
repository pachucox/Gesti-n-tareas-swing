/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_portafolio.views;

import app_portafolio.controller.DepartamentoController;
import app_portafolio.controller.FlujoController;

import app_portafolio.model.Flujo;
import app_portafolio.model.Departamento;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mario
 */
public class Flujos extends javax.swing.JFrame {

    DefaultTableModel tabla = new DefaultTableModel();

    DepartamentoController d = new DepartamentoController();
    Object deptos[][] = new Object[d.leerDeptos().size()][2];
    FlujoController controler = new FlujoController();
    final Class[] tiposColumnas = new Class[]{
        int.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        java.lang.String.class,
        int.class,
        java.lang.String.class,};

    String[] columnas = new String[]{
        "ID Flujo",
        "Descripcion",
        "Fecha inicio",
        "Fecha termino",
        "Tipo flujo",
        "ID departamento",
        "Nombre departamento"
    };

    /**
     * Creates new form Flujos
     */
    public Flujos() throws IOException {
        initComponents();
        this.setLocationRelativeTo(null);
        this.cmbDeptos.setModel(llenarComboBoxDeptos());
        this.llenarDeptos();
        this.llenarTabla();
        this.llenarCampos();
    }

    void llenarTabla() throws IOException {
        tabla = (DefaultTableModel) this.tablaFlujo.getModel();
        tabla.setRowCount(0);
        String nombreDepto = "";
        Object matriz[][] = new Object[this.controler.leerFlujos().size()][7];
        for (int i = 0; i < this.controler.leerFlujos().size(); i++) {

            int idDepto = this.controler.leerFlujos().get(i).getDepartamento_id_departamento();
            for (int x = 0; x < this.d.leerDeptos().size(); x++) {
                if (String.valueOf(idDepto).equals(String.valueOf(deptos[x][0]))) {
                    nombreDepto = this.d.leerDeptos().get(x).getNombre_departamento();
                    break;
                }
            }

            matriz[i][0] = String.valueOf((this.controler.leerFlujos().get(i).getId_flujo()));
            matriz[i][1] = this.controler.leerFlujos().get(i).getDescripcion_flujo();
            matriz[i][2] = this.controler.leerFlujos().get(i).getFecha_inicio();
            matriz[i][3] = this.controler.leerFlujos().get(i).getFecha_termino();
            matriz[i][4] = this.controler.leerFlujos().get(i).getTipo_flujo();
            matriz[i][5] = idDepto;
            matriz[i][6] = nombreDepto;
        }
        this.tablaFlujo.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                columnas) {
            //Esta variable nos permite conocer de antemano los tipos de datos de cada columna, dentro del TableModel
            Class[] tipos = tiposColumnas;
        });
    }

    DefaultComboBoxModel llenarComboBoxDeptos() throws IOException {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        combo.addElement("Seleccione");
        for (Departamento lista : this.d.leerDeptos()) {
            combo.addElement(lista.getNombre_departamento());
        }
        return combo;
    }

    void llenarCampos() {
        this.tablaFlujo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaFlujo.getSelectedRow();
                int id = Integer.parseInt(tablaFlujo.getModel().getValueAt(fila, 0).toString());
                String desc = tablaFlujo.getModel().getValueAt(fila, 1).toString();
                String inicio = tablaFlujo.getModel().getValueAt(fila, 2).toString();
                String termino = tablaFlujo.getModel().getValueAt(fila, 3).toString();
                String tipo = tablaFlujo.getModel().getValueAt(fila, 4).toString();
                String depto = tablaFlujo.getModel().getValueAt(fila, 6).toString();

                txtDesc.setText(desc);
                txtInicio.setText(inicio);
                txtTermino.setText(termino);
                txtTipo.setText(tipo);
                cmbDeptos.setSelectedItem(depto);

            }
        });
    }

    void llenarDeptos() throws IOException {
        for (int i = 0; i < this.d.leerDeptos().size(); i++) {
            this.deptos[i][0] = String.valueOf((this.d.leerDeptos().get(i).getId_departamento()));
            this.deptos[i][1] = this.d.leerDeptos().get(i).getNombre_departamento();
        }
    }

    void Agregar() throws IOException {
        Flujo f = new Flujo();
        String depto = this.cmbDeptos.getItemAt(this.cmbDeptos.getSelectedIndex());
        String nombreDepto = "";
        int id = 0;
        tabla = (DefaultTableModel) this.tablaFlujo.getModel();
        f.setDescripcion_flujo(txtDesc.getText());
        f.setFecha_inicio(txtInicio.getText());
        f.setFecha_termino(txtTermino.getText());
        f.setTipo_flujo(txtTipo.getText());

        for (int i = 0; i < this.d.leerDeptos().size(); i++) {
            if (depto.equals(String.valueOf(this.deptos[i][1]))) {
                f.setDepartamento_id_departamento(((this.d.leerDeptos().get(i).getId_departamento())));
                nombreDepto = this.d.leerDeptos().get(i).getNombre_departamento();
                break;
            }
        }

        try {
            id = controler.agregarFlujo(f).getId_flujo();
            this.tabla.addRow(new Object[]{
                id, f.getDescripcion_flujo(), f.getFecha_inicio(), f.getFecha_termino(), f.getTipo_flujo(), f.getDepartamento_id_departamento(), nombreDepto
            });
            JOptionPane.showMessageDialog(null, "Flujo Agregado Correctamente");
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void Actualizar() throws IOException {
        Flujo f = new Flujo();
        int fila = this.tablaFlujo.getSelectedRow();
        int id = Integer.parseInt(this.tablaFlujo.getModel().getValueAt(fila, 0).toString());
        String depto = this.cmbDeptos.getItemAt(this.cmbDeptos.getSelectedIndex());

        for (int i = 0; i < this.d.leerDeptos().size(); i++) {
            if (depto.equals(String.valueOf(this.deptos[i][1]))) {
                f.setDepartamento_id_departamento(((this.d.leerDeptos().get(i).getId_departamento())));
                break;
            }
        }

        f.setId_flujo(id);
        f.setDescripcion_flujo(txtDesc.getText());
        f.setFecha_inicio(txtInicio.getText());
        f.setFecha_termino(txtTermino.getText());
        f.setTipo_flujo(txtTipo.getText());

        this.controler.actualizarFlujo(f);
        JOptionPane.showMessageDialog(null, "Flujo Actualizado Correctamente");
        this.Limpiar();
    }

    void Eliminar() throws IOException {
        int fila = this.tablaFlujo.getSelectedRow();
        int id = Integer.parseInt(this.tablaFlujo.getValueAt(fila, 0).toString());
        this.tabla = (DefaultTableModel) this.tablaFlujo.getModel();
        try {
            this.controler.eliminarFlujo(id);
            tabla.removeRow(fila);
            JOptionPane.showMessageDialog(null, "Flujo eliminado correctamente");
            this.Limpiar();
        } catch (IOException ex) {
            Logger.getLogger(Flujos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Limpiar() {
        this.txtDesc.setText("");
    }

    public static boolean validarFecha(String fecha) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(fecha.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFlujo = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtDesc = new javax.swing.JTextField();
        txtInicio = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        txtTermino = new javax.swing.JTextField();
        cmbDeptos = new javax.swing.JComboBox<>();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnInicio = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tablaFlujo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Flujo", "Descripcion", "Fecha inicio", "Fecha termino", "Tipo flujo", "ID departamento", "Nombre departamento"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaFlujo);

        jLabel1.setText("Fecha inicio: ");

        jLabel2.setText("Fecha termino: ");

        jLabel3.setText("Tipo flujo: ");

        jLabel4.setText("Departamento: ");

        jLabel5.setText("Descripcion flujo: ");

        txtInicio.setText("MM/dd/yyyy");

        txtTermino.setText("MM/dd/yyyy");

        cmbDeptos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txtTipo)
                            .addComponent(txtDesc))
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbDeptos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTermino, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(165, 165, 165)
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimpiar)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTermino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbDeptos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDesc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(btnInicio)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        try {
            if (txtTipo.getText().length() < 4) {
                JOptionPane.showMessageDialog(null, "Tipo debe tener al menos 4 letras");
            } else if (txtDesc.getText().length() < 1) {
                JOptionPane.showMessageDialog(null, "Ingrese una descripción");
            } else if (!validarFecha(txtInicio.getText()) || !validarFecha(txtTermino.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese una fecha con el formato correcto");
            } else if (this.cmbDeptos.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un departamento");
            } else {
                this.Agregar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (this.tablaFlujo.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un flujo en la tabla");
            }else{
                this.Eliminar();
                this.Limpiar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Flujos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            if (this.cmbDeptos.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un departamento");
            } else if (txtDesc.getText().length() < 1) {
                JOptionPane.showMessageDialog(null, "Ingrese una descripción");
            } else if (!validarFecha(txtInicio.getText()) || !validarFecha(txtTermino.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese una fecha con el formato correcto");
            } else if(txtTipo.getText().length() < 4) {
                JOptionPane.showMessageDialog(null, "Tipo debe tener al menos 4 letras");
            } else {
                this.Actualizar();
                this.llenarTabla();
            }
        } catch (IOException ex) {
            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        this.Limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
         try {
            Inicio i = new Inicio();
            i.setVisible(true);
            this.dispose();
        } catch (IOException ex) {
            Logger.getLogger(Flujos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnInicioActionPerformed

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
            java.util.logging.Logger.getLogger(Flujos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Flujos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Flujos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Flujos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> cmbDeptos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaFlujo;
    private javax.swing.JTextField txtDesc;
    private javax.swing.JTextField txtInicio;
    private javax.swing.JTextField txtTermino;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
