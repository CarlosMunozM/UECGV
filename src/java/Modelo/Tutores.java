/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Carlos
 */
public class Tutores {
    
    int id_tutor;
    String paralelo;
    Date fecha_inicio, fecha_fin;
    
//    Cargo_Empleado cargo_empleado = new Cargo_Empleado();
    Curso_Educativo curso_educativo = new Curso_Educativo();

    public int getId_tutor() {
        return id_tutor;
    }

    public void setId_tutor(int id_tutor) {
        this.id_tutor = id_tutor;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

//    public Cargo_Empleado getCargo_empleado() {
//        return cargo_empleado;
//    }
//
//    public void setCargo_empleado(Cargo_Empleado cargo_empleado) {
//        this.cargo_empleado = cargo_empleado;
//    }

    public Curso_Educativo getCurso_educativo() {
        return curso_educativo;
    }

    public void setCurso_educativo(Curso_Educativo curso_educativo) {
        this.curso_educativo = curso_educativo;
    }
    
    
}
