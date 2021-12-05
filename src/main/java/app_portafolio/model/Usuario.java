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
public class Usuario {

    private int id_usuario;
    private String nombre_usuario;
    private String email_usuario;
    private String pass_usuario;
    private int perfil_id_perfil;
    private int cliente_id_cliente;

    public Usuario() {
    }

    public Usuario(int id_usuario, String nombre_usuario,String email_usuario, String pass_usuario, int perfil_id_perfil,
            int cliente_id_cliente) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.email_usuario = email_usuario;
        this.pass_usuario = pass_usuario;
        this.perfil_id_perfil = perfil_id_perfil;
        this.cliente_id_cliente = cliente_id_cliente;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }
    
    
    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getPass_usuario() {
        return pass_usuario;
    }

    public void setPass_usuario(String pass_usuario) {
        this.pass_usuario = pass_usuario;
    }

    public int getPerfil_id_perfil() {
        return perfil_id_perfil;
    }

    public void setPerfil_id_perfil(int perfil_id_perfil) {
        this.perfil_id_perfil = perfil_id_perfil;
    }

    public int getCliente_id_cliente() {
        return cliente_id_cliente;
    }

    public void setCliente_id_cliente(int cliente_id_cliente) {
        this.cliente_id_cliente = cliente_id_cliente;
    }

}
