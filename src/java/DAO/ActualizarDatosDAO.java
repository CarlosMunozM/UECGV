/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Convivencia;
import Modelo.Curso_Educativo;
import Modelo.Estudiante;
import Modelo.Estudiante_Convivencia;
import Modelo.Estudiante_Familiar;
import Modelo.Estudiante_Referencia;
import Modelo.Familiar;
import Modelo.Referencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author DHL-SIS-ING
 */
public class ActualizarDatosDAO {
    
    private ConexionPostgreSQL connecPostgresql;
    
    public Estudiante mostrarEstudianteFiltroIdentificacion(String identificacion) throws ParseException, SQLException {
        Estudiante estudiante = new Estudiante();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_estudiantes_filtro_identificacion(?)}");
            connecPostgresql.callableStatement.setString(1, identificacion);
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String fecha = dateFormat.format(consulta.getDate("fecha_nacimiento"));
                Date date = dateFormat.parse(fecha);
                
                estudiante.setId_estudiante(consulta.getInt("id_estudiante"));
                estudiante.getCurso_educativo().setId_curso(consulta.getInt("id_curso"));
                estudiante.getCurso_educativo().setNombre_curso(consulta.getString("nombre_curso"));
                estudiante.getCurso_educativo().setTipo(consulta.getString("tipo_curso"));
                estudiante.setIdentificacion(identificacion);
                estudiante.setTipo_identificacion(consulta.getString("tipo_identificacion"));
                estudiante.setApellidos(consulta.getString("apellidos"));
                estudiante.setNombres(consulta.getString("nombres"));
                estudiante.setFecha_nacimiento(date);  //consulta.getDate("fecha_nacimiento"));
                estudiante.setGenero(consulta.getString("genero"));
                estudiante.setFoto((consulta.getString("foto") == null || consulta.getString("foto").equals(""))? "assets/img/user-default.png":consulta.getString("foto"));
                estudiante.setDireccion(consulta.getString("direccion"));
                estudiante.setFoto_domicilio(consulta.getString("foto_domicilio"));
                estudiante.setCarnet_discapacidad(consulta.getString("carnet_discapacidad"));
                estudiante.setDiscapacidad(consulta.getString("discapacidad"));
                estudiante.setTipo_discapacidad(consulta.getString("tipo_discapacidad"));
                estudiante.setHistoria_clinica(consulta.getString("historia_clinica"));
                estudiante.setNumero_hermanos(consulta.getInt("numero_hermanos"));
                estudiante.setLugar_ocupa(consulta.getInt("lugar_ocupa"));
                estudiante.setNacionalidad(consulta.getString("nacionalidad"));
                estudiante.setCorreo(consulta.getString("correo_personal"));
                estudiante.setCelular(consulta.getString("celular"));
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return estudiante;
    }
    
    public ArrayList<Estudiante_Familiar> mostrarFamiliarFiltroId(int id) throws ParseException, SQLException {
        ArrayList<Estudiante_Familiar> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_estudiante_familiar_filtro_persona(?)}");
            connecPostgresql.callableStatement.setInt(1, id);
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {
                Estudiante_Familiar estFamiliar = new Estudiante_Familiar();
                Familiar familiar = new Familiar();
                familiar.setId_familiar(consulta.getInt("id_familiar"));
                familiar.setTipo_identificacion(consulta.getString("tipo_identificacion"));
                familiar.setIdentificacion(consulta.getString("identificacion"));
                familiar.setApellidos(consulta.getString("apellidos_fam"));
                familiar.setNombres(consulta.getString("nombres_fam"));
                familiar.setCue(consulta.getString("cue"));
                familiar.setCelular(consulta.getString("celular"));
                familiar.setOcupacion(consulta.getString("ocupacion"));
                familiar.setLugar_trabajo(consulta.getString("lugar_trabajo"));
                familiar.setCorreo(consulta.getString("correo"));
                familiar.setNacionalidad(consulta.getString("nacionalidad"));
                estFamiliar.setFamiliar(familiar);
                estFamiliar.setId_estdFamiliar(consulta.getInt("id_estdfamiliar"));
                estFamiliar.setParentesco(consulta.getString("parentesco"));
                lista.add(estFamiliar);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
    public Estudiante_Referencia mostrarReferenciaFiltroId(int id) throws ParseException, SQLException {
        Estudiante_Referencia estudiante_Referencia = new Estudiante_Referencia();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_estudiante_referencia_filtro_persona(?)}");
            connecPostgresql.callableStatement.setInt(1, id);
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {
                Referencia referencia = new Referencia();
                referencia.setId_referencia(consulta.getInt("id_referencia"));
                referencia.setTipo_identificacion(consulta.getString("tipo_identificacion_ref"));
                referencia.setIdentificacion(consulta.getString("identificacion_ref"));
                referencia.setNombres(consulta.getString("nombres_ref"));
                referencia.setApellidos(consulta.getString("apellidos_ref"));
                referencia.setCelular(consulta.getString("celular_ref"));
                referencia.setTelefono(consulta.getString("telefono_ref"));
                estudiante_Referencia.setReferencia(referencia);
                estudiante_Referencia.setEstdReferencia(consulta.getInt("id_estdreferencia"));
                estudiante_Referencia.setParentesco(consulta.getString("parentesco"));
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return estudiante_Referencia;
    }
    
    public ArrayList<Curso_Educativo> mostrarCursoEducativo() throws ParseException, SQLException {
        ArrayList<Curso_Educativo> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_curso_educativo()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {
                Curso_Educativo curso_Educativo = new Curso_Educativo();
                curso_Educativo.setId_curso(consulta.getInt("id_curso"));
                curso_Educativo.setNombre_curso(consulta.getString("nombre_curso"));
                curso_Educativo.setTipo(consulta.getString("tipo"));
                lista.add(curso_Educativo);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
    public ArrayList<Convivencia> mostrarConvivencia() throws ParseException, SQLException {
        ArrayList<Convivencia> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_convivencia()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {
                Convivencia convivencia = new Convivencia();
                convivencia.setId_convivencia(consulta.getInt("id_convivencia"));
                convivencia.setFamiliar(consulta.getString("familiar"));
                lista.add(convivencia);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
    public ArrayList<Estudiante_Convivencia> mostrarEstudianteConvivencia(int id) throws ParseException, SQLException {
        ArrayList<Estudiante_Convivencia> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_estudiante_convivencia_filtro_persona(?)}");
            connecPostgresql.callableStatement.setInt(1, id);
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {
                Estudiante_Convivencia  estudiante_Convivencia = new Estudiante_Convivencia();
                estudiante_Convivencia.setId_estdConvivencia(consulta.getInt("id_estdconvivencia"));
                Convivencia convivencia = new Convivencia();
                convivencia.setId_convivencia(consulta.getInt("id_convivencia"));
                convivencia.setFamiliar(consulta.getString("familiar"));
                estudiante_Convivencia.setConvivencia(convivencia);
                lista.add(estudiante_Convivencia);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
    public boolean actualizarEstudiante(JSONObject datos) throws ParseException, SQLException {
        boolean respuesta = false;
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            //Actualizar Alumnos
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_estudiante(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante")); //id
            connecPostgresql.callableStatement.setInt(2, datos.getInt("sltRegCurso")); //id_curso
            connecPostgresql.callableStatement.setString(3, datos.getString("txtRegIdentificacion")); //identificacion
            connecPostgresql.callableStatement.setString(4, datos.getString("sltRegTipoIdentificacion")); //tipo identificacion
            connecPostgresql.callableStatement.setString(5, datos.getString("txtRegApellidos")); //apellidos
            connecPostgresql.callableStatement.setString(6, datos.getString("txtRegNombres")); //nombres
            String fecha = datos.getString("txtRegFecha");
            fecha = fecha.replace("-", "/");
            Date fecha_nacimiento = new Date(fecha);
            connecPostgresql.callableStatement.setDate(7, new java.sql.Date(fecha_nacimiento.getTime())); //fecha
            connecPostgresql.callableStatement.setString(8, datos.getString("sltRegGenero"));
            connecPostgresql.callableStatement.setString(9, ""); //foto 50 regFotoAlu
            connecPostgresql.callableStatement.setString(10, datos.getString("txtRegDireccion"));
            connecPostgresql.callableStatement.setString(11, ""); // foto domicilio 51 txtRegFotoDomicilio
            connecPostgresql.callableStatement.setString(12, datos.getString("regCrntDiscAlu"));
            connecPostgresql.callableStatement.setString(13, datos.getString("regDiscapacidadAlu"));
            connecPostgresql.callableStatement.setString(14, datos.getString("sltRegTipoDiscp"));
            connecPostgresql.callableStatement.setString(15, datos.getString("txtRegHistoriaClinica"));
            connecPostgresql.callableStatement.setInt(16, datos.getInt("txtRegNumHermanos")); /// Numero Hermanos
            connecPostgresql.callableStatement.setInt(17, datos.getInt("txtRegLugHermanos")); // Lugar Ocupa
            connecPostgresql.callableStatement.setString(18, datos.getString("txtRegNacionalidad"));
            connecPostgresql.callableStatement.setString(19, datos.getString("txtRegEmail").toLowerCase());
            connecPostgresql.callableStatement.setString(20, datos.getString("txtRegCelular"));
            consulta = connecPostgresql.callableStatement.executeQuery();
            
            //Actualizar Referencia
            int id_referencia = 0;
            if(datos.getInt("id_referencia") == 0){
                //No existe se registra
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_referencia(?,?,?,?,?,?)}");
                connecPostgresql.callableStatement.setString(1, datos.getString("txtRegApellidos")); //apellidos
                connecPostgresql.callableStatement.setString(2, datos.getString("txtRegNombres")); //nombres
                connecPostgresql.callableStatement.setString(3, datos.getString("txtRegCelular")); //celular
                connecPostgresql.callableStatement.setString(4, datos.getString("txtRegtelefono")); //telefono
                connecPostgresql.callableStatement.setString(5, datos.getString("txtRegFamiliarIdentificacionRef")); //identificacion
                connecPostgresql.callableStatement.setString(6, datos.getString("txtTipoIdeRef")); //tipo_identificacion
                consulta = connecPostgresql.callableStatement.executeQuery();    
                
                //Consultar ID
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idreferencia_filtro_todo(?,?,?,?,?,?)}");
                connecPostgresql.callableStatement.setString(1, datos.getString("txtRegApellidos")); //apellidos
                connecPostgresql.callableStatement.setString(2, datos.getString("txtRegNombres")); //nombres
                connecPostgresql.callableStatement.setString(3, datos.getString("txtRegCelular")); //celular
                connecPostgresql.callableStatement.setString(4, datos.getString("txtRegtelefono")); //telefono
                connecPostgresql.callableStatement.setString(5, datos.getString("txtRegFamiliarIdentificacionRef")); //identificacion
                connecPostgresql.callableStatement.setString(6, datos.getString("txtTipoIdeRef")); //tipo_identificacion
                consulta = connecPostgresql.callableStatement.executeQuery();       
                while (consulta.next()) {
                    id_referencia = consulta.getInt(1);
                }
                
            }else{
                //Ya existe se actualiza
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_referencia(?,?,?,?,?,?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_referencia")); //ID
                connecPostgresql.callableStatement.setString(2, datos.getString("txtRegApellidos")); //apellidos
                connecPostgresql.callableStatement.setString(3, datos.getString("txtRegNombres")); //nombres
                connecPostgresql.callableStatement.setString(4, datos.getString("txtRegCelular")); //celular
                connecPostgresql.callableStatement.setString(5, datos.getString("txtRegtelefono")); //telefono
                connecPostgresql.callableStatement.setString(6, datos.getString("txtRegFamiliarIdentificacionRef")); //identificacion
                connecPostgresql.callableStatement.setString(7, datos.getString("txtTipoIdeRef")); //tipo_identificacion
                
                id_referencia = datos.getInt("id_referencia");
                consulta = connecPostgresql.callableStatement.executeQuery();
            }
            
            //Insertar Relacion Estudiante_Referencia
            if(id_referencia != 0){
                int id_estreferencia = datos.getInt("id_estdReferencia");
                if(id_estreferencia == 0){
                    //No existe se registra
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_referencia(?,?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante")); //id_estudiante
                    connecPostgresql.callableStatement.setInt(2, id_referencia); //id_referencia
                    connecPostgresql.callableStatement.setString(3, datos.getString("txtRefParentesco")); //parentesco
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }else{
                    //Ya existe se actualiza
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_estudiante_referencia(?,?,?,?)}");
                    connecPostgresql.callableStatement.setInt(1, id_estreferencia); //id_estdreferencia
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("id_estudiante")); //id_estudiante
                    connecPostgresql.callableStatement.setInt(3, id_referencia); //id_referencia
                    connecPostgresql.callableStatement.setString(4, datos.getString("txtRefParentesco")); //parentesco
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            
            ///Padre
            int id_padre = 0;
            if(datos.getInt("id_padre") == 0){
                //Buscar si ya existe en la tabla
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idfamiliar_filtro_identificacion(?)}");
                connecPostgresql.callableStatement.setString(1, datos.getString("txtRegFamiliarIdentificacionPadre"));
                consulta = connecPostgresql.callableStatement.executeQuery();    
                while (consulta.next()) {
                    id_padre = consulta.getInt(1);
                }
                
                if(id_padre == 0){
                    //No existe se registra
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_familiar(?,?,?,?,?,?,?,?,?,?)}");
                    connecPostgresql.callableStatement.setString(1, datos.getString("txtRegFamiliarIdentificacionPadre"));
                    connecPostgresql.callableStatement.setString(2, datos.getString("txtTipoIdePadre"));
                    connecPostgresql.callableStatement.setString(3, datos.getString("txtRegApellidosPadre")); 
                    connecPostgresql.callableStatement.setString(4, datos.getString("txtRegNombresPadre"));
                    connecPostgresql.callableStatement.setString(5, datos.getString("txtRegCuePadre"));
                    connecPostgresql.callableStatement.setString(6, datos.getString("txtRegCelularPadre"));
                    connecPostgresql.callableStatement.setString(7, datos.getString("txtRegFamiliarOcupacionPadre"));
                    connecPostgresql.callableStatement.setString(8, datos.getString("txtRegFamiliarLugarPadre"));
                    connecPostgresql.callableStatement.setString(9, datos.getString("txtRegEmailPadre").toLowerCase());
                    connecPostgresql.callableStatement.setString(10, datos.getString("txtRegNacionalidadPadre")); 
                    consulta = connecPostgresql.callableStatement.executeQuery();    
                    
                    //Consultar ID
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idfamiliar_filtro_identificacion(?)}");
                    connecPostgresql.callableStatement.setString(1, datos.getString("txtRegFamiliarIdentificacionPadre"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                    while (consulta.next()) {
                        id_padre = consulta.getInt(1);
                    }
                }
            }else{
                //Ya existe se actualiza
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_familiar(?,?,?,?,?,?,?,?,?,?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_padre")); 
                connecPostgresql.callableStatement.setString(2, datos.getString("txtRegFamiliarIdentificacionPadre"));
                connecPostgresql.callableStatement.setString(3, datos.getString("txtTipoIdePadre"));
                connecPostgresql.callableStatement.setString(4, datos.getString("txtRegApellidosPadre")); 
                connecPostgresql.callableStatement.setString(5, datos.getString("txtRegNombresPadre"));
                connecPostgresql.callableStatement.setString(6, datos.getString("txtRegCuePadre"));
                connecPostgresql.callableStatement.setString(7, datos.getString("txtRegCelularPadre"));
                connecPostgresql.callableStatement.setString(8, datos.getString("txtRegFamiliarOcupacionPadre"));
                connecPostgresql.callableStatement.setString(9, datos.getString("txtRegFamiliarLugarPadre"));
                connecPostgresql.callableStatement.setString(10, datos.getString("txtRegEmailPadre").toLowerCase());
                connecPostgresql.callableStatement.setString(11, datos.getString("txtRegNacionalidadPadre")); 
                
                id_padre = datos.getInt("id_padre");
                consulta = connecPostgresql.callableStatement.executeQuery();
            }
            
            //Insertar Relacion Estudiante_Familiar
            if(id_padre != 0){
                int id_familiar1 = datos.getInt("id_familiar1");
                if(id_familiar1 == 0){
                    //No existe se registra
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_familiar(?,?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, id_padre);
                    connecPostgresql.callableStatement.setString(3, "PADRE"); //parentesco
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }else{
                    //Ya existe se actualiza
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_estudiante_familiar(?,?,?,?)}");
                    connecPostgresql.callableStatement.setInt(1, id_familiar1);
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(3, id_padre);
                    connecPostgresql.callableStatement.setString(4, "PADRE"); //parentesco
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            
            //MADRE
            int id_madre = 0;
            if(datos.getInt("id_madre") == 0){
                //Buscar si ya existe en la tabla
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idfamiliar_filtro_identificacion(?)}");
                connecPostgresql.callableStatement.setString(1, datos.getString("txtRegFamiliarIdentificacionMadre"));
                consulta = connecPostgresql.callableStatement.executeQuery();    
                while (consulta.next()) {
                    id_madre = consulta.getInt(1);
                }
                
                if(id_madre == 0){
                    //No existe se registra
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_familiar(?,?,?,?,?,?,?,?,?,?)}");
                    connecPostgresql.callableStatement.setString(1, datos.getString("txtRegFamiliarIdentificacionMadre"));
                    connecPostgresql.callableStatement.setString(2, datos.getString("txtTipoIdeMadre"));
                    connecPostgresql.callableStatement.setString(3, datos.getString("txtRegApellidosMadre")); 
                    connecPostgresql.callableStatement.setString(4, datos.getString("txtRegNombresMadre"));
                    connecPostgresql.callableStatement.setString(5, datos.getString("txtRegCueMadre"));
                    connecPostgresql.callableStatement.setString(6, datos.getString("txtRegCelularMadre"));
                    connecPostgresql.callableStatement.setString(7, datos.getString("txtRegFamiliarOcupacionMadre"));
                    connecPostgresql.callableStatement.setString(8, datos.getString("txtRegFamiliarLugarMadre"));
                    connecPostgresql.callableStatement.setString(9, datos.getString("txtRegEmailMadre").toLowerCase());
                    connecPostgresql.callableStatement.setString(10, datos.getString("txtRegNacionalidadMadre")); 
                    consulta = connecPostgresql.callableStatement.executeQuery();    
                    
                    //Consultar ID
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idfamiliar_filtro_identificacion(?)}");
                    connecPostgresql.callableStatement.setString(1, datos.getString("txtRegFamiliarIdentificacionMadre"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                    while (consulta.next()) {
                        id_madre = consulta.getInt(1);
                    }
                }
            }else{
                //Ya existe se actualiza
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_familiar(?,?,?,?,?,?,?,?,?,?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_madre")); 
                connecPostgresql.callableStatement.setString(2, datos.getString("txtRegFamiliarIdentificacionMadre"));
                connecPostgresql.callableStatement.setString(3, datos.getString("txtTipoIdeMadre"));
                connecPostgresql.callableStatement.setString(4, datos.getString("txtRegApellidosMadre")); 
                connecPostgresql.callableStatement.setString(5, datos.getString("txtRegNombresMadre"));
                connecPostgresql.callableStatement.setString(6, datos.getString("txtRegCueMadre"));
                connecPostgresql.callableStatement.setString(7, datos.getString("txtRegCelularMadre"));
                connecPostgresql.callableStatement.setString(8, datos.getString("txtRegFamiliarOcupacionMadre"));
                connecPostgresql.callableStatement.setString(9, datos.getString("txtRegFamiliarLugarMadre"));
                connecPostgresql.callableStatement.setString(10, datos.getString("txtRegEmailMadre").toLowerCase());
                connecPostgresql.callableStatement.setString(11, datos.getString("txtRegNacionalidadMadre")); 
                
                id_madre = datos.getInt("id_madre");
                consulta = connecPostgresql.callableStatement.executeQuery();
            }
            
            //Insertar Relacion Estudiante_Familiar
            if(id_madre != 0){
                int id_familiar2 = datos.getInt("id_familiar2");
                if(id_familiar2 == 0){
                    //No existe se registra
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_familiar(?,?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, id_madre);
                    connecPostgresql.callableStatement.setString(3, "MADRE"); //parentesco
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }else{
                    //Ya existe se actualiza
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_estudiante_familiar(?,?,?,?)}");
                    connecPostgresql.callableStatement.setInt(1, id_familiar2);
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(3, id_madre);
                    connecPostgresql.callableStatement.setString(4, "MADRE"); //parentesco
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
           
            //Convivencia
            //Borrar Convivencias
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call eliminar_estudiante_convivencia_v(?)}");
            connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
            consulta = connecPostgresql.callableStatement.executeQuery();
            if(datos.has("MADRE")){
                int id = 0;
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idconvivencia(?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                connecPostgresql.callableStatement.setInt(2, datos.getInt("MADRE"));
                consulta = connecPostgresql.callableStatement.executeQuery();
                while (consulta.next()) {
                    id = consulta.getInt(1);
                }
                if(id == 0){
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("MADRE"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            if(datos.has("PADRE")){
                int id = 0;
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idconvivencia(?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                connecPostgresql.callableStatement.setInt(2, datos.getInt("PADRE"));
                consulta = connecPostgresql.callableStatement.executeQuery();
                while (consulta.next()) {
                    id = consulta.getInt(1);
                }
                if(id == 0){
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("PADRE"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            if(datos.has("HERMANOS")){
                int id = 0;
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idconvivencia(?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                connecPostgresql.callableStatement.setInt(2, datos.getInt("HERMANOS"));
                consulta = connecPostgresql.callableStatement.executeQuery();
                while (consulta.next()) {
                    id = consulta.getInt(1);
                }
                if(id == 0){
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("HERMANOS"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            if(datos.has("TÍO")){
                int id = 0;
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idconvivencia(?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                connecPostgresql.callableStatement.setInt(2, datos.getInt("TÍO"));
                consulta = connecPostgresql.callableStatement.executeQuery();
                while (consulta.next()) {
                    id = consulta.getInt(1);
                }
                if(id == 0){
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("TÍO"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            if(datos.has("TÍA")){
                int id = 0;
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idconvivencia(?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                connecPostgresql.callableStatement.setInt(2, datos.getInt("TÍA"));
                consulta = connecPostgresql.callableStatement.executeQuery();
                while (consulta.next()) {
                    id = consulta.getInt(1);
                }
                if(id == 0){
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("TÍA"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            if(datos.has("PRIMOS")){
                int id = 0;
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idconvivencia(?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                connecPostgresql.callableStatement.setInt(2, datos.getInt("PRIMOS"));
                consulta = connecPostgresql.callableStatement.executeQuery();
                while (consulta.next()) {
                    id = consulta.getInt(1);
                }
                if(id == 0){
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("PRIMOS"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            if(datos.has("ABUELO")){
                int id = 0;
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idconvivencia(?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                connecPostgresql.callableStatement.setInt(2, datos.getInt("ABUELO"));
                consulta = connecPostgresql.callableStatement.executeQuery();
                while (consulta.next()) {
                    id = consulta.getInt(1);
                }
                if(id == 0){
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("ABUELO"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            if(datos.has("ABUELA")){
                int id = 0;
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idconvivencia(?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                connecPostgresql.callableStatement.setInt(2, datos.getInt("ABUELA"));
                consulta = connecPostgresql.callableStatement.executeQuery();
                while (consulta.next()) {
                    id = consulta.getInt(1);
                }
                if(id == 0){
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("ABUELA"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            if(datos.has("SOLO")){
                int id = 0;
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idconvivencia(?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                connecPostgresql.callableStatement.setInt(2, datos.getInt("SOLO"));
                consulta = connecPostgresql.callableStatement.executeQuery();
                while (consulta.next()) {
                    id = consulta.getInt(1);
                }
                if(id == 0){
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getInt("id_estudiante"));
                    connecPostgresql.callableStatement.setInt(2, datos.getInt("SOLO"));
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }               
            respuesta = true;
        } catch (SQLException ex) {
            respuesta = false;
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return respuesta;
    }
    
    public boolean actualizarFotoPerfil(String ruta, int id) throws ParseException, SQLException {
        boolean respuesta = false;
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_estudiante_fotoperfil(?,?)}");
            connecPostgresql.callableStatement.setInt(1, id);
            connecPostgresql.callableStatement.setString(2, ruta);
            consulta = connecPostgresql.callableStatement.executeQuery();
            respuesta = true;

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            respuesta = false;
        }
        connecPostgresql.getConnection().close();
        return respuesta;
    }
    
    public boolean actualizarFotoDomicilio(String ruta, int id) throws ParseException, SQLException {
        boolean respuesta = false;
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_estudiante_fotodomicilio(?,?)}");
            connecPostgresql.callableStatement.setInt(1, id);
            connecPostgresql.callableStatement.setString(2, ruta);
            consulta = connecPostgresql.callableStatement.executeQuery();
            respuesta = true;

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            respuesta = false;
        }
        connecPostgresql.getConnection().close();
        return respuesta;
    }
}