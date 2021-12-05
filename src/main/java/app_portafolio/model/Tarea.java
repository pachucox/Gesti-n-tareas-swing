
package app_portafolio.model;

/**
 *
 * @author Mario
 */
public class Tarea {
    
    private int id_tarea;
    private String descripcion_tarea;
    private int id_responsable;
    private int id_creador;
    private String fecha_inicio;
    private String fecha_limite;
    private String fecha_termino;
    private int id_tareapadre;
    private int tarea_id_tarea;
    private int funcion_id_funcion;

    public Tarea() {
    }

    public Tarea(int id_tarea, String descripcion_tarea, int id_responsable, int id_creador, String fecha_inicio, String fecha_limite, String fecha_termino, int id_tareapadre, int tarea_id_tarea, int funcion_id_funcion) {
        this.id_tarea = id_tarea;
        this.descripcion_tarea = descripcion_tarea;
        this.id_responsable = id_responsable;
        this.id_creador = id_creador;
        this.fecha_inicio = fecha_inicio;
        this.fecha_limite = fecha_limite;
        this.fecha_termino = fecha_termino;
        this.id_tareapadre = id_tareapadre;
        this.tarea_id_tarea = tarea_id_tarea;
        this.funcion_id_funcion = funcion_id_funcion;
    }

    public int getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(int id_tarea) {
        this.id_tarea = id_tarea;
    }

    public String getDescripcion_tarea() {
        return descripcion_tarea;
    }

    public void setDescripcion_tarea(String descripcion_tarea) {
        this.descripcion_tarea = descripcion_tarea;
    }

    public int getId_responsable() {
        return id_responsable;
    }

    public void setId_responsable(int id_responsable) {
        this.id_responsable = id_responsable;
    }

    public int getId_creador() {
        return id_creador;
    }

    public void setId_creador(int id_creador) {
        this.id_creador = id_creador;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_limite() {
        return fecha_limite;
    }

    public void setFecha_limite(String fecha_limite) {
        this.fecha_limite = fecha_limite;
    }

    public String getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public int getId_tareapadre() {
        return id_tareapadre;
    }

    public void setId_tareapadre(int id_tareapadre) {
        this.id_tareapadre = id_tareapadre;
    }

    public int getTarea_id_tarea() {
        return tarea_id_tarea;
    }

    public void setTarea_id_tarea(int tarea_id_tarea) {
        this.tarea_id_tarea = tarea_id_tarea;
    }

    public int getFuncion_id_funcion() {
        return funcion_id_funcion;
    }

    public void setFuncion_id_funcion(int funcion_id_funcion) {
        this.funcion_id_funcion = funcion_id_funcion;
    }
    
    
    
}
