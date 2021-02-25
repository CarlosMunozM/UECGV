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
public class Imagenes_Presentacion {

    int id_imgPresentacion;
    String ruta;
    Usuario usuario = new Usuario();

    public int getId_imgPresentacion() {
        return id_imgPresentacion;
    }

    public void setId_imgPresentacion(int id_imgPresentacion) {
        this.id_imgPresentacion = id_imgPresentacion;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
