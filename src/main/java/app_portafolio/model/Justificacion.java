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
public class Justificacion {
    
    private int id_justificacion;
    private String descripcion_justificacion;
    private int tarea_id_tarea;

    public Justificacion() {
    }

    public Justificacion(int id_justificacion, String descripcion_justificacion, int tarea_id_tarea) {
        this.id_justificacion = id_justificacion;
        this.descripcion_justificacion = descripcion_justificacion;
        this.tarea_id_tarea = tarea_id_tarea;
    }

    public int getId_justificacion() {
        return id_justificacion;
    }

    public void setId_justificacion(int id_justificacion) {
        this.id_justificacion = id_justificacion;
    }

    public String getDescripcion_justificacion() {
        return descripcion_justificacion;
    }

    public void setDescripcion_justificacion(String descripcion_justificacion) {
        this.descripcion_justificacion = descripcion_justificacion;
    }

    public int getTarea_id_tarea() {
        return tarea_id_tarea;
    }

    public void setTarea_id_tarea(int tarea_id_tarea) {
        this.tarea_id_tarea = tarea_id_tarea;
    }
    
    
    
}
