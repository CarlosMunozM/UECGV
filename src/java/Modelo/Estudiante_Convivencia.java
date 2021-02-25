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
public class Estudiante_Convivencia {

    int id_estdConvivencia;
    Estudiante estudiante = new Estudiante();
    Convivencia convivencia = new Convivencia();

    public int getId_estdConvivencia() {
        return id_estdConvivencia;
    }

    public void setId_estdConvivencia(int id_estdConvivencia) {
        this.id_estdConvivencia = id_estdConvivencia;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Convivencia getConvivencia() {
        return convivencia;
    }

    public void setConvivencia(Convivencia convivencia) {
        this.convivencia = convivencia;
    }
    
    
}
