/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Empleado;
import Modelo.Estudiante;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class EstudianteDAO {

    private ConexionPostgreSQL connecPostgresql;

    public boolean registrarEstudiante(Estudiante estudiante) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            connecPostgresql.callableStatement.setInt(1, estudiante.getCurso_educativo().getId_curso());
            connecPostgresql.callableStatement.setString(2, estudiante.getIdentificacion());
            connecPostgresql.callableStatement.setString(3, estudiante.getTipo_identificacion());
            connecPostgresql.callableStatement.setString(4, estudiante.getApellidos());
            connecPostgresql.callableStatement.setString(5, estudiante.getNombres());
            connecPostgresql.callableStatement.setDate(6, new java.sql.Date(estudiante.getFecha_nacimiento().getTime()));
            connecPostgresql.callableStatement.setString(7, estudiante.getGenero());
            connecPostgresql.callableStatement.setString(8, estudiante.getFoto());
            connecPostgresql.callableStatement.setString(9, estudiante.getDireccion());
            connecPostgresql.callableStatement.setString(10, estudiante.getFoto_domicilio());
            connecPostgresql.callableStatement.setString(11, estudiante.getCarnet_discapacidad());
            connecPostgresql.callableStatement.setString(12, estudiante.getDiscapacidad());
            connecPostgresql.callableStatement.setString(13, estudiante.getTipo_discapacidad());
            connecPostgresql.callableStatement.setString(14, estudiante.getHistoria_clinica());
            connecPostgresql.callableStatement.setInt(15, estudiante.getNumero_hermanos());
            connecPostgresql.callableStatement.setInt(16, estudiante.getLugar_ocupa());
            connecPostgresql.callableStatement.setString(17, estudiante.getNacionalidad());
            connecPostgresql.callableStatement.setString(18, estudiante.getCorreo());
            connecPostgresql.callableStatement.setString(19, estudiante.getCelular());
            

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public ArrayList<Estudiante> mostrarEstudiantes() throws ParseException, SQLException {
        ArrayList<Estudiante> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_estudiantes()}");
            consulta = connecPostgresql.callableStatement.executeQuery();

            while (consulta.next()) {
                String bandera = "";
                Estudiante estudiante = new Estudiante();

                estudiante.setId_estudiante(consulta.getInt("id_estudiante"));
                estudiante.getCurso_educativo().setId_curso(consulta.getInt("id_curso"));
                estudiante.getCurso_educativo().setNombre_curso(consulta.getString("nombre_curso"));
                estudiante.getCurso_educativo().setTipo(consulta.getString("tipo_curso"));
                estudiante.setIdentificacion(consulta.getString("identificacion"));
                estudiante.setTipo_identificacion(consulta.getString("tipo_identificacion"));
                estudiante.setApellidos(consulta.getString("apellidos"));
                estudiante.setNombres(consulta.getString("nombres"));
                estudiante.setFecha_nacimiento(consulta.getDate("fecha_nacimiento"));
                estudiante.setCorreo(consulta.getString("correo_personal"));
                estudiante.setCelular(consulta.getString("celular"));
                estudiante.setGenero(consulta.getString("genero"));
                estudiante.setFoto(consulta.getString("foto"));
                estudiante.setDireccion(consulta.getString("direccion"));
               // estudiante.setFoto_domicilio(consulta.getString("foto_domicilio"));
                estudiante.setCarnet_discapacidad(consulta.getString("carnet_discapacidad"));
                estudiante.setDiscapacidad(consulta.getString("discapacidad"));
                estudiante.setTipo_discapacidad(consulta.getString("tipo_discapacidad"));
                estudiante.setHistoria_clinica(consulta.getString("historia_clinica"));
                estudiante.setNumero_hermanos(consulta.getInt("numero_hermanos"));
                estudiante.setLugar_ocupa(consulta.getInt("lugar_ocupa"));
                estudiante.setNacionalidad(consulta.getString("nacionalidad"));
                estudiante.setFoto_domicilio(consulta.getString("foto_domicilio"));
                
                ResultSet consulta_familiar;
                int id = estudiante.getId_estudiante();
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_estudiante_familiar_filtro_persona(?)}");
                connecPostgresql.callableStatement.setInt(1, id);
                consulta_familiar = connecPostgresql.callableStatement.executeQuery();

                while (consulta_familiar.next()) {
                    bandera = "OK";
                    estudiante.getFamiliar().setId_familiar(consulta_familiar.getInt("id_familiar"));
                    estudiante.getFamiliar().setNombres(consulta_familiar.getString("nombres_fam"));
                    estudiante.getFamiliar().setApellidos(consulta_familiar.getString("apellidos_fam"));
                    estudiante.getFamiliar().setCue(consulta_familiar.getString("cue"));
                    estudiante.getFamiliar().setCelular(consulta_familiar.getString("celular"));
                    estudiante.getFamiliar().setOcupacion(consulta_familiar.getString("ocupacion"));
                    estudiante.getFamiliar().setLugar_trabajo(consulta_familiar.getString("lugar_trabajo"));
                    estudiante.getFamiliar().setCorreo(consulta_familiar.getString("correo"));
                    estudiante.getFamiliar().setNacionalidad(consulta_familiar.getString("nacionalidad"));
                    estudiante.getFamiliar().setTipo_identificacion(consulta_familiar.getString("tipo_identificacion"));
                    estudiante.getFamiliar().setIdentificacion(consulta_familiar.getString("identificacion"));
                    //estudiante.getEstudiante_Familiar().setParentesco(consulta_familiar.getString("parentesco"));
            //        estudiante.getFamiliar().getEstudiante_Familiar().setParentesco(consulta_familiar.getString("parentesco"));
            

                    ResultSet consulta_referencia;

                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_estudiante_referencia_filtro_persona(?)}");
                    connecPostgresql.callableStatement.setInt(1, id);
                    consulta_referencia = connecPostgresql.callableStatement.executeQuery();

                    while (consulta_referencia.next()) {
                        estudiante.getReferencia().setIdentificacion(consulta.getString("identificacion"));
                        estudiante.getReferencia().setTipo_identificacion(consulta.getString("tipo_identificacion"));

                        estudiante.getReferencia().setId_referencia(consulta_referencia.getInt("id_referencia"));
                        estudiante.getReferencia().setApellidos(consulta_referencia.getString("apellidos_ref"));
                        estudiante.getReferencia().setNombres(consulta_referencia.getString("nombres_ref"));
                        estudiante.getReferencia().setCelular(consulta_referencia.getString("telefono_ref"));
                        estudiante.getReferencia().setTelefono(consulta_referencia.getString("parentesco"));
                        estudiante.getReferencia().setParentesco(consulta_referencia.getString("celular_ref"));

                    }
                    lista.add(estudiante);
                    break;
                }
                if(bandera.equals("OK")){
                }else{
                    lista.add(estudiante);
                }
            }
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }

    public ArrayList<Estudiante> mostrarEstudiantesFiltroIdentificacion(String e) throws ParseException, SQLException {
        ArrayList<Estudiante> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_estudiantes_filtro_identificacion(?)}");
            connecPostgresql.callableStatement.setString(1, e);
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Estudiante estudiante = new Estudiante();

                estudiante.getCurso_educativo().setId_curso(consulta.getInt("id_curso"));
                estudiante.getCurso_educativo().setNombre_curso(consulta.getString("nombre_curso"));
                estudiante.getCurso_educativo().setTipo(consulta.getString("tipo_curso"));
                estudiante.setIdentificacion(consulta.getString("identificacion"));
                estudiante.setTipo_identificacion(consulta.getString("tipo_identificacion"));
                estudiante.setApellidos(consulta.getString("apellidos"));
                estudiante.setNombres(consulta.getString("nombres"));
                estudiante.setFecha_nacimiento(consulta.getDate("fecha_nacimiento"));
                estudiante.setGenero(consulta.getString("genero"));
                estudiante.setFoto(consulta.getString("foto"));
                estudiante.setDireccion(consulta.getString("direccion"));
                estudiante.setFoto_domicilio(consulta.getString("foto_domicilio"));
                estudiante.setCarnet_discapacidad(consulta.getString("carnet_discapacidad"));
                estudiante.setDiscapacidad(consulta.getString("discapacidad"));
                estudiante.setTipo_discapacidad(consulta.getString("tipo_discapacidad"));
                estudiante.setHistoria_clinica(consulta.getString("historia_clinica"));
                estudiante.setNumero_hermanos(consulta.getInt("numero_hermanos"));
                estudiante.setLugar_ocupa(consulta.getInt("lugar_ocupa"));
                estudiante.setNacionalidad(consulta.getString("nacionalidad"));

                lista.add(estudiante);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }

    public boolean modificarEstudiante(Estudiante estudiante) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_estudiante(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            connecPostgresql.callableStatement.setInt(1, estudiante.getId_estudiante());
            connecPostgresql.callableStatement.setInt(2, estudiante.getCurso_educativo().getId_curso());
            connecPostgresql.callableStatement.setString(3, estudiante.getIdentificacion());
            connecPostgresql.callableStatement.setString(4, estudiante.getTipo_identificacion());
            connecPostgresql.callableStatement.setString(5, estudiante.getApellidos());
            connecPostgresql.callableStatement.setString(6, estudiante.getNombres());
            connecPostgresql.callableStatement.setDate(7, new java.sql.Date(estudiante.getFecha_nacimiento().getTime()));
            connecPostgresql.callableStatement.setString(8, estudiante.getGenero());
            connecPostgresql.callableStatement.setString(9, estudiante.getFoto());
            connecPostgresql.callableStatement.setString(10, estudiante.getDireccion());
            connecPostgresql.callableStatement.setString(11, estudiante.getFoto_domicilio());
            connecPostgresql.callableStatement.setString(12, estudiante.getCarnet_discapacidad());
            connecPostgresql.callableStatement.setString(13, estudiante.getDiscapacidad());
            connecPostgresql.callableStatement.setString(14, estudiante.getTipo_discapacidad());
            connecPostgresql.callableStatement.setString(15, estudiante.getHistoria_clinica());
            connecPostgresql.callableStatement.setInt(16, estudiante.getNumero_hermanos());
            connecPostgresql.callableStatement.setInt(17, estudiante.getLugar_ocupa());
            connecPostgresql.callableStatement.setString(18, estudiante.getNacionalidad());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public boolean eliminarEstudiante(Estudiante empleado) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call eliminar_estudiante(?)}");
            connecPostgresql.callableStatement.setInt(1, empleado.getId_estudiante());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public int obtenerIdEstudiante(Estudiante estudiante) throws SQLException {
        try {
            int idEstudiante = 0;
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idestudiante_filtro_identificacion(?)}");
                connecPostgresql.callableStatement.setString(1, estudiante.getIdentificacion());
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    idEstudiante = consulta.getInt(1);
                }
                connecPostgresql.getConnection().close();
            }
            return idEstudiante;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    
      public String ValidarIdentificacionEstudiante(String identificacion) throws SQLException {
        try {
            String ident = "";
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call validar_identificacion_estudiante(?)}");
                connecPostgresql.callableStatement.setString(1, identificacion);
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    ident = consulta.getString(1);
                }
                connecPostgresql.getConnection().close();
            }
            return ident;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return "";
        }
    }
}
