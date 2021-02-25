/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Carlos
 */
public class Usuario {
    
    int id_usuario;
    String usuario, clave, cambiar_clave, codigo_correo;
    
    Empleado empleado = new Empleado();
    Cargo_Empleado cargo_empleado = new Cargo_Empleado();

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cargo_Empleado getCargo_Empleado() {
        return cargo_empleado;
    }

    public void setCargo_Empleado(Cargo_Empleado cargo_empleado) {
        this.cargo_empleado = cargo_empleado;
    }

    public String getCambiar_clave() {
        return cambiar_clave;
    }

    public void setCambiar_clave(String cambiar_clave) {
        this.cambiar_clave = cambiar_clave;
    }

    public String getCodigo_correo() {
        return codigo_correo;
    }

    public void setCodigo_correo(String codigo_correo) {
        this.codigo_correo = codigo_correo;
    }
    
    
    
}
