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
public class Flujo {

    private int id_flujo;
    private String descripcion_flujo;
    private String fecha_inicio;
    private String fecha_termino;
    private String tipo_flujo;
    private int departamento_id_departamento;

    public Flujo() {
    }

    public Flujo(int id_flujo, String descripcion_flujo, String fecha_inicio, String fecha_termino, String tipo_flujo, int departamento_id_departamento) {
        this.id_flujo = id_flujo;
        this.descripcion_flujo = descripcion_flujo;
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
        this.tipo_flujo = tipo_flujo;
        this.departamento_id_departamento = departamento_id_departamento;
    }

    public int getId_flujo() {
        return id_flujo;
    }

    public void setId_flujo(int id_flujo) {
        this.id_flujo = id_flujo;
    }

    public String getDescripcion_flujo() {
        return descripcion_flujo;
    }

    public void setDescripcion_flujo(String descripcion_flujo) {
        this.descripcion_flujo = descripcion_flujo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public String getTipo_flujo() {
        return tipo_flujo;
    }

    public void setTipo_flujo(String tipo_flujo) {
        this.tipo_flujo = tipo_flujo;
    }

    public int getDepartamento_id_departamento() {
        return departamento_id_departamento;
    }

    public void setDepartamento_id_departamento(int departamento_id_departamento) {
        this.departamento_id_departamento = departamento_id_departamento;
    }
    
    

   
    
    

    
}
