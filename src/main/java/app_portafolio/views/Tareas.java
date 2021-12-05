/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_portafolio.views;

import app_portafolio.controller.FuncionController;
import app_portafolio.controller.TareaController;
import app_portafolio.controller.UsuarioController;
import app_portafolio.model.Funcion;
import app_portafolio.model.Usuario;
import app_portafolio.model.Tarea;
import static app_portafolio.views.Flujos.validarFecha;
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
public class Tareas extends javax.swing.JFrame {
    
    DefaultTableModel tabla = new DefaultTableModel();
    FuncionController  f = new FuncionController();
    Object funciones[][] = new Object[f.leerFunciones().size()][2];
    UsuarioController  u = new UsuarioController();
    Object usuarios[][] = new Object[u.leerUsuarios().size()][2];
    TareaController controler = new TareaController();
    Object tareas[][] = new Object[controler.leerTareas().size()][2];
      final Class[] tiposColumnas = new Class[]{
        int.class,
        java.lang.String.class,
        int.class,
        int.class,
        java.lang.String.class,
        java.lang.String.class,
        int.class,
        int.class,
        int.class,};

    String[] columnas = new String[]{
        "ID tarea",
        "Descripcion",
        "ID creador",
        "ID responsable",
        "Fecha inicio",
        "Fecha limite",
        "ID tarea padre",
        "ID tarea hermana",
        "ID funcion"

    };
    
    

    /**
     * Creates new form Tareas
     */
    public Tareas() throws IOException {
        
        initComponents();
        this.cmbFuncion.setModel(llenarComboBoxFuncion());
        this.cmbResponsable.setModel(llenarComboBoxUsuario());
        this.cmbCreador.setModel(llenarComboBoxUsuario());
        this.cmbHermana.setModel(llenarComboBoxTarea());
        this.cmbPadre.setModel(llenarComboBoxTarea());
        this.setLocationRelativeTo(null);
        llenarTareas();
        llenarFunciones();
        llenarUsuarios();
        llenarTabla();
        llenarCampos();
    }
    
      void llenarTabla() throws IOException {
        tabla = (DefaultTableModel) this.tablaTarea.getModel();
        tabla.setRowCount(0);
      
        Object matriz[][] = new Object[this.controler.leerTareas().size()][9];
        for (int i = 0; i < this.controler.leerTareas().size(); i++) {
            
            matriz[i][0] = String.valueOf((this.controler.leerTareas().get(i).getId_tarea()));
            matriz[i][1] = this.controler.leerTareas().get(i).getDescripcion_tarea();
            matriz[i][2] = this.controler.leerTareas().get(i).getId_creador();
            matriz[i][3] = this.controler.leerTareas().get(i).getId_responsable();
            matriz[i][4] = this.controler.leerTareas().get(i).getFecha_inicio();
            matriz[i][5] = this.controler.leerTareas().get(i).getFecha_limite();
            matriz[i][6] = this.controler.leerTareas().get(i).getId_tareapadre();
            matriz[i][7] = this.controler.leerTareas().get(i).getTarea_id_tarea();
            matriz[i][8] = this.controler.leerTareas().get(i).getFuncion_id_funcion();
           
            
        }
        this.tablaTarea.setModel(new javax.swing.table.DefaultTableModel(
                matriz,
                columnas) {
            //Esta variable nos permite conocer de antemano los tipos de datos de cada columna, dentro del TableModel
            Class[] tipos = tiposColumnas;
        });
    }
      
      void llenarCampos() {
        this.tablaTarea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int fila = tablaTarea.getSelectedRow();
                int id = Integer.parseInt(tablaTarea.getModel().getValueAt(fila, 0).toString());
                String descripcion = tablaTarea.getModel().getValueAt(fila, 1).toString();
                String fechaI = tablaTarea.getModel().getValueAt(fila, 4).toString();
                String fechaL = tablaTarea.getModel().getValueAt(fila,5).toString();
                txtDescripcion.setText(descripcion);
                txtFechaInicio.setText(fechaI);
                txtFechaLimite.setText(fechaL);
            }
        });
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
    
    
     DefaultComboBoxModel llenarComboBoxTarea() throws IOException {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        combo.addElement("Seleccione");
        for (Tarea lista : this.controler.leerTareas()) {
            combo.addElement(lista.getDescripcion_tarea());
        }
        return combo;
    }
    
    
    DefaultComboBoxModel llenarComboBoxFuncion() throws IOException {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        combo.addElement("Seleccione");
        for (Funcion lista : this.f.leerFunciones()) {
            combo.addElement(lista.getNombre_funcion());
        }
        return combo;
    }

    DefaultComboBoxModel llenarComboBoxUsuario() throws IOException {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        combo.addElement("Seleccione");
        for (Usuario lista : this.u.leerUsuarios()) {
            combo.addElement(lista.getNombre_usuario());
        }
        return combo;
    }

    void llenarFunciones() throws IOException {
        for (int i = 0; i < this.f.leerFunciones().size(); i++) {
            this.funciones[i][0] = String.valueOf((this.f.leerFunciones().get(i).getId_funcion()));
            this.funciones[i][1] = this.f.leerFunciones().get(i).getNombre_funcion();
        }
    }

    void llenarUsuarios() throws IOException {
        for (int i = 0; i < this.u.leerUsuarios().size(); i++) {
            this.usuarios[i][0] = String.valueOf((this.u.leerUsuarios().get(i).getId_usuario()));
            this.usuarios[i][1] = this.u.leerUsuarios().get(i).getNombre_usuario();
        }
    }
    
     void llenarTareas() throws IOException {
        for (int i = 0; i < this.controler.leerTareas().size(); i++) {
            this.tareas[i][0] = String.valueOf((this.controler.leerTareas().get(i).getId_tarea()));
            this.tareas[i][1] = this.controler.leerTareas().get(i).getDescripcion_tarea();
        }
    }
    
    void Agregar() throws IOException {
        Tarea t = new Tarea();
        String funcion = this.cmbFuncion.getItemAt(this.cmbFuncion.getSelectedIndex());
        String creador = this.cmbCreador.getItemAt(this.cmbCreador.getSelectedIndex());
        String responsable = this.cmbResponsable.getItemAt(this.cmbResponsable.getSelectedIndex());
        String hermana =((this.cmbHermana.getItemAt(this.cmbHermana.getSelectedIndex())));
        String padre=((this.cmbPadre.getItemAt(this.cmbPadre.getSelectedIndex())));
        
        
        int id = 0;
        tabla = (DefaultTableModel) this.tablaTarea.getModel();
 
        t.setDescripcion_tarea(txtDescripcion.getText());
        t.setFecha_inicio(txtFechaInicio.getText());
        t.setFecha_limite(txtFechaLimite.getText());
       
        for (int i = 0; i < this.controler.leerTareas().size(); i++) {
            if (hermana.equals(String.valueOf(this.tareas[i][1]))) {
                t.setTarea_id_tarea(((this.controler.leerTareas().get(i).getId_tarea())));
                break;
            }
        }
        for (int i = 0; i < this.controler.leerTareas().size(); i++) {
            if (padre.equals(String.valueOf(this.tareas[i][1]))) {
                t.setId_tareapadre(((this.controler.leerTareas().get(i).getId_tarea())));
                break;
            }
        }
        

        for (int i = 0; i < this.f.leerFunciones().size(); i++) {
            if (funcion.equals(String.valueOf(this.funciones[i][1]))) {
                t.setFuncion_id_funcion(((this.f.leerFunciones().get(i).getId_funcion())));
                break;
            }
        }
        for (int i = 0; i < this.u.leerUsuarios().size(); i++) {
            if (creador.equals(String.valueOf(this.usuarios[i][1]))) {
                t.setId_creador(((this.u.leerUsuarios().get(i).getId_usuario())));
                break;
            }
        }
        for (int i = 0; i < this.u.leerUsuarios().size(); i++) {
            if (responsable.equals(String.valueOf(this.usuarios[i][1]))) {
                t.setId_responsable(((this.u.leerUsuarios().get(i).getId_usuario())));
                break;
            }
        }
       
        try {
            id = controler.agregarTarea(t).getId_tarea();
            this.tabla.addRow(new Object[]{
                id, t.getDescripcion_tarea(),t.getId_creador(),t.getId_responsable(), t.getFecha_inicio(),t.getFecha_limite(),t.getId_tareapadre(),t.getTarea_id_tarea(),t.getFuncion_id_funcion()
            });
            this.Limpiar();
            JOptionPane.showMessageDialog(null, "Tarea Agregada Correctamente");
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    void Actualizar() throws IOException {
        Tarea t = new Tarea();
        int fila = this.tablaTarea.getSelectedRow();
        int id = Integer.parseInt(this.tablaTarea.getModel().getValueAt(fila, 0).toString());
        String funcion = this.cmbFuncion.getItemAt(this.cmbFuncion.getSelectedIndex());
        String creador = this.cmbCreador.getItemAt(this.cmbCreador.getSelectedIndex());
        String responsable = this.cmbResponsable.getItemAt(this.cmbResponsable.getSelectedIndex());
        String hermana = ((this.cmbHermana.getItemAt(this.cmbHermana.getSelectedIndex())));
        String padre = ((this.cmbPadre.getItemAt(this.cmbPadre.getSelectedIndex())));

        for (int i = 0; i < this.controler.leerTareas().size(); i++) {
            if (hermana.equals(String.valueOf(this.tareas[i][1]))) {
                t.setTarea_id_tarea(((this.controler.leerTareas().get(i).getId_tarea())));
                break;
            }
        }
        for (int i = 0; i < this.controler.leerTareas().size(); i++) {
            if (padre.equals(String.valueOf(this.tareas[i][1]))) {
                t.setId_tareapadre(((this.controler.leerTareas().get(i).getId_tarea())));
                break;
            }
        }

        for (int i = 0; i < this.f.leerFunciones().size(); i++) {
            if (funcion.equals(String.valueOf(this.funciones[i][1]))) {
                t.setFuncion_id_funcion(((this.f.leerFunciones().get(i).getId_funcion())));
                break;
            }
        }
        for (int i = 0; i < this.u.leerUsuarios().size(); i++) {
            if (creador.equals(String.valueOf(this.usuarios[i][1]))) {
                t.setId_creador(((this.u.leerUsuarios().get(i).getId_usuario())));
                break;
            }
        }
         for (int i = 0; i < this.u.leerUsuarios().size(); i++) {
            if (responsable.equals(String.valueOf(this.usuarios[i][1]))) {
                t.setId_responsable(((this.u.leerUsuarios().get(i).getId_usuario())));
                break;
            }
        }

        t.setId_tarea(id);
        t.setDescripcion_tarea(txtDescripcion.getText());
        t.setFecha_inicio(txtFechaInicio.getText());
        t.setFecha_limite(txtFechaLimite.getText());
        
        this.controler.actualizarTarea(t);
        JOptionPane.showMessageDialog(null, "Tarea Actualizado Correctamente");
        this.Limpiar();
    }
    
     void Eliminar() throws IOException {
        int fila = this.tablaTarea.getSelectedRow();
        int id = Integer.parseInt(this.tablaTarea.getValueAt(fila, 0).toString());
        this.tabla = (DefaultTableModel) this.tablaTarea.getModel();
        try {
            this.controler.eliminarTarea(id);
            tabla.removeRow(fila);
            JOptionPane.showMessageDialog(null, "Tarea eliminada correctamente");
            this.Limpiar();
        } catch (IOException ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     void Limpiar () {
         
         txtDescripcion.setText("");
         txtFechaInicio.setText("");
         txtFechaLimite.setText("");
         this.cmbCreador.setSelectedIndex(0);
         this.cmbFuncion.setSelectedIndex(0);
         this.cmbHermana.setSelectedIndex(0);
         this.cmbPadre.setSelectedIndex(0);
         this.cmbResponsable.setSelectedIndex(0);
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
        btnInicio = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtFechaLimite = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtFechaInicio = new javax.swing.JTextField();
        cmbResponsable = new javax.swing.JComboBox<>();
        cmbCreador = new javax.swing.JComboBox<>();
        cmbPadre = new javax.swing.JComboBox<>();
        cmbHermana = new javax.swing.JComboBox<>();
        cmbFuncion = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTarea = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Tareas");

        btnInicio.setText("Inicio");
        btnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInicioActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha inicio: ");

        jLabel4.setText("Responsable: ");

        jLabel5.setText("Fecha límite: ");

        jLabel3.setText("Tarea padre: ");

        jLabel6.setText("Tarea hermana: ");

        jLabel7.setText("Función: ");

        jLabel8.setText("Creador: ");

        jLabel9.setText("Descripcion: ");

        txtFechaLimite.setText("MM/dd/yyyy");

        txtFechaInicio.setText("MM/dd/yyyy");

        cmbResponsable.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbCreador.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbPadre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbHermana.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbFuncion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tablaTarea.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID tarea", "Descripcion", "ID creador", "ID responsable", "Fecha inicio", "Fecha limite", "ID tarea padre", "ID tarea hermana", "ID funcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaTarea);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(324, 324, 324)
                        .addComponent(btnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpiar))
                            .addComponent(jLabel1))))
                .addContainerGap(74, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtFechaInicio)
                            .addComponent(cmbResponsable, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbHermana, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addGap(15, 15, 15)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFechaLimite)
                    .addComponent(txtDescripcion)
                    .addComponent(cmbCreador, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbPadre, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(162, 162, 162))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(txtFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(cmbResponsable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCreador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(cmbPadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbHermana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbFuncion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnEliminar)
                    .addComponent(btnActualizar)
                    .addComponent(btnLimpiar))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInicio)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInicioActionPerformed
        try{
            Inicio i = new Inicio();
            i.setVisible(true);
            this.dispose();    
        }catch (IOException ex) {
            Logger.getLogger(Tareas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }//GEN-LAST:event_btnInicioActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        try {
            if (txtDescripcion.getText().length() < 4) {
                JOptionPane.showMessageDialog(null, "Descripción debe contener al menos 4 letras");
            } else if (this.cmbCreador.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un creador");
            } else if (this.cmbResponsable.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un responsable");
            }else if (!validarFecha(txtFechaInicio.getText()) || !validarFecha(txtFechaLimite.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese una fecha con el formato correcto");
            }else if(this.cmbFuncion.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar una función");
            }else {
                this.Agregar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Tareas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            if (this.tablaTarea.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar una tarea en la tabla");
            } else {
                this.Eliminar();
            }
        } catch (IOException ex) {
            Logger.getLogger(Tareas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        try {
            if(tablaTarea.getSelectedRow()== -1 ){
                JOptionPane.showMessageDialog(null, "Debe seleccionar una tarea de la tabla");
            } else if(txtDescripcion.getText().length() < 4) {
                JOptionPane.showMessageDialog(null, "Descripción debe contener al menos 4 letras");
            } else if (this.cmbCreador.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe seleccionar un creador");
            } else if (this.cmbResponsable.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar un responsable");
            }else if (!validarFecha(txtFechaInicio.getText()) || !validarFecha(txtFechaLimite.getText())) {
                JOptionPane.showMessageDialog(null, "Ingrese una fecha con el formato correcto");
            }else if(this.cmbFuncion.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Debe Seleccionar una función");
            }else {
                this.Actualizar();
                this.llenarTabla();
            }
        } catch (IOException ex) {
            Logger.getLogger(Tareas.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(Tareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tareas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    new Tareas().setVisible(true);
                }catch (IOException ex) {
                    Logger.getLogger(Tareas.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> cmbCreador;
    private javax.swing.JComboBox<String> cmbFuncion;
    private javax.swing.JComboBox<String> cmbHermana;
    private javax.swing.JComboBox<String> cmbPadre;
    private javax.swing.JComboBox<String> cmbResponsable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaTarea;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtFechaLimite;
    // End of variables declaration//GEN-END:variables
}
