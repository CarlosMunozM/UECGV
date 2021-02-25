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
public class Estudiante_Referencia {
 
    int estdReferencia;
    String parentesco;
    Estudiante estudiante = new Estudiante();
    Referencia referencia = new Referencia();

    public int getEstdReferencia() {
        return estdReferencia;
    }

    public void setEstdReferencia(int estdReferencia) {
        this.estdReferencia = estdReferencia;
    }

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }
}
