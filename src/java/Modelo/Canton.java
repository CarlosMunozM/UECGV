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
public class Canton {

    int id_canton;
    String nombre_canton;
    
    Provincia provincia = new Provincia();

    public int getId_canton() {
        return id_canton;
    }

    public void setId_canton(int id_canton) {
        this.id_canton = id_canton;
    }

    public String getNombre_canton() {
        return nombre_canton;
    }

    public void setNombre_canton(String nombre_canton) {
        this.nombre_canton = nombre_canton;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
    
    
}
