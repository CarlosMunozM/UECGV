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
public class Estudiante {
    
    int id_estudiante, numero_hermanos, lugar_ocupa;
    Curso_Educativo curso_educativo = new Curso_Educativo();
    String identificacion, tipo_identificacion, apellidos, nombres,genero, foto, direccion, foto_domicilio, 
            carnet_discapacidad, discapacidad, tipo_discapacidad, historia_clinica, nacionalidad,correo,celular;
    Estudiante_Familiar estudiante_Familiar = new Estudiante_Familiar();

    public Estudiante_Familiar getEstudiante_Familiar() {
        return estudiante_Familiar;
    }

    public void setEstudiante_Familiar(Estudiante_Familiar estudiante_Familiar) {
        this.estudiante_Familiar = estudiante_Familiar;
    }
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
            
    Date fecha_nacimiento; 
    Familiar familiar =new Familiar();
    Referencia referencia= new Referencia();

    public Referencia getReferencia() {
        return referencia;
    }

    public void setReferencia(Referencia referencia) {
        this.referencia = referencia;
    }
    public Familiar getFamiliar() {
        return familiar;
    }

    public void setFamiliar(Familiar familiar) {
        this.familiar = familiar;
    }
    
    public int getId_estudiante() {
        return id_estudiante;
    }

    public void setId_estudiante(int id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public int getNumero_hermanos() {
        return numero_hermanos;
    }

    public void setNumero_hermanos(int numero_hermanos) {
        this.numero_hermanos = numero_hermanos;
    }

    public int getLugar_ocupa() {
        return lugar_ocupa;
    }

    public void setLugar_ocupa(int lugar_ocupa) {
        this.lugar_ocupa = lugar_ocupa;
    }

    public Curso_Educativo getCurso_educativo() {
        return curso_educativo;
    }

    public void setCurso_educativo(Curso_Educativo curso_educativo) {
        this.curso_educativo = curso_educativo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipo_identificacion() {
        return tipo_identificacion;
    }

    public void setTipo_identificacion(String tipo_identificacion) {
        this.tipo_identificacion = tipo_identificacion;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFoto_domicilio() {
        return foto_domicilio;
    }

    public void setFoto_domicilio(String foto_domicilio) {
        this.foto_domicilio = foto_domicilio;
    }

    public String getCarnet_discapacidad() {
        return carnet_discapacidad;
    }

    public void setCarnet_discapacidad(String carnet_discapacidad) {
        this.carnet_discapacidad = carnet_discapacidad;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getTipo_discapacidad() {
        return tipo_discapacidad;
    }

    public void setTipo_discapacidad(String tipo_discapacidad) {
        this.tipo_discapacidad = tipo_discapacidad;
    }

    public String getHistoria_clinica() {
        return historia_clinica;
    }

    public void setHistoria_clinica(String historia_clinica) {
        this.historia_clinica = historia_clinica;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    
    
}
