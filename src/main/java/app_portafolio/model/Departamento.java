/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_portafolio.model;

/**
 *
 * @author Mario
 */
public class Departamento {
    
    private int id_departamento;
    private String nombre_departamento;
    private int cliente_id_cliente;

    public Departamento() {
    }

    public Departamento(int id_departamento, String nombre_departamento, int cliente_id_cliente) {
        this.id_departamento = id_departamento;
        this.nombre_departamento = nombre_departamento;
        this.cliente_id_cliente = cliente_id_cliente;
    }

    public int getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(int id_departamento) {
        this.id_departamento = id_departamento;
    }

    public String getNombre_departamento() {
        return nombre_departamento;
    }

    public void setNombre_departamento(String nombre_departamento) {
        this.nombre_departamento = nombre_departamento;
    }

    public int getCliente_id_cliente() {
        return cliente_id_cliente;
    }

    public void setCliente_id_cliente(int cliente_id_cliente) {
        this.cliente_id_cliente = cliente_id_cliente;
    }
    
    
    
}
