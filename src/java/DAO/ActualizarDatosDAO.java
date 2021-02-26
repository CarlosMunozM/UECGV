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
    
    public boolean actualizarEstudiante(JSONArray datos) throws ParseException, SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            
            //Actualizar Alumnos
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_estudiante(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            connecPostgresql.callableStatement.setInt(1, datos.getJSONObject(44).getInt("value")); //id
            connecPostgresql.callableStatement.setInt(2, datos.getJSONObject(9).getInt("value")); //id_curso
            connecPostgresql.callableStatement.setString(3, datos.getJSONObject(1).getString("value")); //identificacion
            connecPostgresql.callableStatement.setString(4, datos.getJSONObject(0).getString("value")); //tipo identificacion
            connecPostgresql.callableStatement.setString(5, datos.getJSONObject(6).getString("value")); //apellidos
            connecPostgresql.callableStatement.setString(6, datos.getJSONObject(5).getString("value")); //nombres
            String fecha = datos.getJSONObject(4).getString("value");
            fecha = fecha.replace("-", "/");
            Date fecha_nacimiento = new Date(fecha);
            connecPostgresql.callableStatement.setDate(7, new java.sql.Date(fecha_nacimiento.getTime())); //fecha
            connecPostgresql.callableStatement.setString(8, datos.getJSONObject(3).getString("value"));
            connecPostgresql.callableStatement.setString(9, ""); //foto 50
            connecPostgresql.callableStatement.setString(10, datos.getJSONObject(10).getString("value"));
            connecPostgresql.callableStatement.setString(11, ""); // foto domicilio 51
            connecPostgresql.callableStatement.setString(12, datos.getJSONObject(15).getString("value"));
            connecPostgresql.callableStatement.setString(13, datos.getJSONObject(14).getString("value"));
            connecPostgresql.callableStatement.setString(14, datos.getJSONObject(13).getString("value"));
            connecPostgresql.callableStatement.setString(15, datos.getJSONObject(16).getString("value"));
            connecPostgresql.callableStatement.setInt(16, datos.getJSONObject(11).getInt("value")); /// Numero Hermanos
            connecPostgresql.callableStatement.setInt(17, datos.getJSONObject(12).getInt("value")); // Lugar Ocupa
            connecPostgresql.callableStatement.setString(18, datos.getJSONObject(2).getString("value"));
            connecPostgresql.callableStatement.setString(19, datos.getJSONObject(7).getString("value"));
            connecPostgresql.callableStatement.setString(20, datos.getJSONObject(8).getString("value"));
            consulta = connecPostgresql.callableStatement.executeQuery();
            
            //Actualizar Referencia
            int id_referencia = 0;
            if(datos.getJSONObject(49).getInt("value") == 0){
                //No existe se registra
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_referencia(?,?,?,?,?,?)}");
                connecPostgresql.callableStatement.setString(1, datos.getJSONObject(40).getString("value")); //apellidos
                connecPostgresql.callableStatement.setString(2, datos.getJSONObject(39).getString("value")); //nombres
                connecPostgresql.callableStatement.setString(3, datos.getJSONObject(43).getString("value")); //celular
                connecPostgresql.callableStatement.setString(4, datos.getJSONObject(42).getString("value")); //telefono
                connecPostgresql.callableStatement.setString(5, datos.getJSONObject(38).getString("value")); //identificacion
                connecPostgresql.callableStatement.setString(6, datos.getJSONObject(37).getString("value")); //tipo_identificacion
                consulta = connecPostgresql.callableStatement.executeQuery();    
                
                //Consultar ID
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idreferencia_filtro_todo(?,?,?,?,?,?)}");
                connecPostgresql.callableStatement.setString(1, datos.getJSONObject(40).getString("value")); //apellidos
                connecPostgresql.callableStatement.setString(2, datos.getJSONObject(39).getString("value")); //nombres
                connecPostgresql.callableStatement.setString(3, datos.getJSONObject(43).getString("value")); //celular
                connecPostgresql.callableStatement.setString(4, datos.getJSONObject(42).getString("value")); //telefono
                connecPostgresql.callableStatement.setString(5, datos.getJSONObject(38).getString("value")); //identificacion
                connecPostgresql.callableStatement.setString(6, datos.getJSONObject(37).getString("value")); //tipo_identificacion
                consulta = connecPostgresql.callableStatement.executeQuery();       
                while (consulta.next()) {
                    id_referencia = consulta.getInt(1);
                }
                
            }else{
                //Ya existe se actualiza
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_referencia(?,?,?,?,?,?,?)}");
                connecPostgresql.callableStatement.setInt(1, datos.getJSONObject(49).getInt("value")); //ID
                connecPostgresql.callableStatement.setString(2, datos.getJSONObject(40).getString("value")); //apellidos
                connecPostgresql.callableStatement.setString(3, datos.getJSONObject(39).getString("value")); //nombres
                connecPostgresql.callableStatement.setString(4, datos.getJSONObject(43).getString("value")); //celular
                connecPostgresql.callableStatement.setString(5, datos.getJSONObject(42).getString("value")); //telefono
                connecPostgresql.callableStatement.setString(6, datos.getJSONObject(38).getString("value")); //identificacion
                connecPostgresql.callableStatement.setString(7, datos.getJSONObject(37).getString("value")); //tipo_identificacion
                id_referencia = datos.getJSONObject(49).getInt("value");
                consulta = connecPostgresql.callableStatement.executeQuery();
            }
            
            //Insertar Relacion Estudiante_Referencia
            if(id_referencia != 0){
                int id_estreferencia = datos.getJSONObject(50).getInt("value");
                if(id_estreferencia == 0){
                    //No existe se registra
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_referencia(?,?,?)}");
                    connecPostgresql.callableStatement.setInt(1, datos.getJSONObject(44).getInt("value")); //id_estudiante
                    connecPostgresql.callableStatement.setInt(2, id_referencia); //id_referencia
                    connecPostgresql.callableStatement.setString(3, datos.getJSONObject(41).getString("value")); //parentesco
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }else{
                    //Ya existe se actualiza
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_estudiante_referencia(?,?,?,?)}");
                    connecPostgresql.callableStatement.setInt(1, id_estreferencia); //id_estdreferencia
                    connecPostgresql.callableStatement.setInt(2, datos.getJSONObject(44).getInt("value")); //id_estudiante
                    connecPostgresql.callableStatement.setInt(3, id_referencia); //id_referencia
                    connecPostgresql.callableStatement.setString(4, datos.getJSONObject(41).getString("value")); //parentesco
                    consulta = connecPostgresql.callableStatement.executeQuery();
                }
            }
            
            return true;    
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return false;
    }
}