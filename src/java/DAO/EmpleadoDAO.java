/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Empleado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class EmpleadoDAO {

    private ConexionPostgreSQL connecPostgresql;

    public boolean registrarEmpleado(Empleado empleado) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            connecPostgresql.callableStatement.setInt(1, empleado.getCanton().getId_canton());
            connecPostgresql.callableStatement.setString(2, empleado.getIdentificacion());
            connecPostgresql.callableStatement.setString(3, empleado.getTipo_identificacion());
            connecPostgresql.callableStatement.setString(4, empleado.getApellidos());
            connecPostgresql.callableStatement.setString(5, empleado.getNombres());
            connecPostgresql.callableStatement.setString(6, empleado.getEstado_civil());
            connecPostgresql.callableStatement.setString(7, empleado.getGenero());
            connecPostgresql.callableStatement.setString(8, empleado.getDireccion());
            connecPostgresql.callableStatement.setString(9, empleado.getTelefono());
            connecPostgresql.callableStatement.setString(10, empleado.getCorreo_personal());
            connecPostgresql.callableStatement.setString(11, empleado.getContrato());
            connecPostgresql.callableStatement.setString(12, empleado.getEscalafon());
            connecPostgresql.callableStatement.setString(13, empleado.getFoto());
            connecPostgresql.callableStatement.setString(14, empleado.getNacionalidad());
            connecPostgresql.callableStatement.setDate(15, new java.sql.Date(empleado.getFecha_nacimiento().getTime()));
            connecPostgresql.callableStatement.setString(16, empleado.getCelular());
            connecPostgresql.callableStatement.setString(17, empleado.getCorreo_institucional());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public ArrayList<Empleado> mostrarEmpleados() throws ParseException, SQLException {
        ArrayList<Empleado> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_empleados()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Empleado empleado = new Empleado();

                empleado.setId_empleado(consulta.getInt("id_empleado"));
                empleado.getCanton().getProvincia().setId_provincia(consulta.getInt("id_provincia"));
                empleado.getCanton().getProvincia().setNombre_provincia(consulta.getString("nombre_provincia"));
                empleado.getCanton().setId_canton(consulta.getInt("id_canton"));
                empleado.getCanton().setNombre_canton(consulta.getString("nombre_canton"));
                empleado.setIdentificacion(consulta.getString("identificacion"));
                empleado.setTipo_identificacion(consulta.getString("tipo_identificacion"));
                empleado.setApellidos(consulta.getString("apellidos"));
                empleado.setNombres(consulta.getString("nombres"));
                empleado.setEstado_civil(consulta.getString("estado_civil"));
                empleado.setGenero(consulta.getString("genero"));
                empleado.setDireccion(consulta.getString("direccion"));
                empleado.setTelefono(consulta.getString("telefono"));
                empleado.setCorreo_personal(consulta.getString("correo_personal"));
                empleado.setContrato(consulta.getString("contrato"));
                empleado.setEscalafon(consulta.getString("escalafon"));
                empleado.setFoto(consulta.getString("foto"));
                empleado.setNacionalidad(consulta.getString("nacionalidad"));
                empleado.setFecha_nacimiento(consulta.getDate("fecha_nacimiento"));
                empleado.setCelular(consulta.getString("celular"));
                empleado.setCorreo_institucional(consulta.getString("correo_institucional"));
                
                ResultSet consulta_cargo;
                int id=empleado.getId_empleado();
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_cargo_empleado_filtro_persona(?)}");
                connecPostgresql.callableStatement.setInt(1,id );
                consulta_cargo = connecPostgresql.callableStatement.executeQuery();

                while (consulta_cargo.next()) {
                    empleado.getCargo().setId_cargo(consulta_cargo.getInt("id_cargo"));
                    empleado.getCargo().setNombre_cargo(consulta_cargo.getString("nombre_cargo"));
                    
                    ResultSet consulta_tutores;
                    int idem=empleado.getId_empleado();
                    connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_tutores_filtro_persona(?)}");
                    connecPostgresql.callableStatement.setInt(1,idem );
                    consulta_tutores = connecPostgresql.callableStatement.executeQuery();
                    String bandera="";
                    while(consulta_tutores.next()){
                        empleado.getTutores().setId_tutor(consulta_tutores.getInt("id_tutor"));
                       // empleado.getTutores().getCargo_empleado().setId_cargoEmpleado(consulta_tutores.getInt("id_cargoempleado"));
                        empleado.getTutores().getCurso_educativo().setId_curso(consulta_tutores.getInt("id_curso"));
                        empleado.getTutores().getCurso_educativo().setNombre_curso(consulta_tutores.getString("nombre_curso"));
                        empleado.getTutores().getCurso_educativo().setTipo(consulta_tutores.getString("tipo"));
                        empleado.getTutores().setParalelo(consulta_tutores.getString("paralelo"));
                        empleado.getTutores().setFecha_inicio(consulta_tutores.getDate("fecha_inicio"));
                        empleado.getTutores().setFecha_fin(consulta_tutores.getDate("fecha_fin"));
                        bandera ="ok";
                        lista.add(empleado);  
                    }
                    if(bandera.equals("")){
                        empleado.getTutores().setId_tutor(0);
                       // empleado.getTutores().getCargo_empleado().setId_cargoEmpleado(consulta_tutores.getInt("id_cargoempleado"));
                        empleado.getTutores().getCurso_educativo().setId_curso(0);
                        empleado.getTutores().getCurso_educativo().setNombre_curso("");
                        empleado.getTutores().getCurso_educativo().setTipo("");
                        empleado.getTutores().setParalelo("");
                        empleado.getTutores().setFecha_inicio(null);
                        empleado.getTutores().setFecha_fin(null);
                        lista.add(empleado);  
                    } 
                    
                      
                }
                
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }

    public ArrayList<Empleado> mostrarEmpleadosFiltroPersona(Empleado e) throws ParseException, SQLException {
        ArrayList<Empleado> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_empleados_filtro_persona(?)}");
            connecPostgresql.callableStatement.setInt(1, e.getId_empleado());
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Empleado empleado = new Empleado();

                empleado.getCanton().getProvincia().setId_provincia(consulta.getInt("id_provincia"));
                empleado.getCanton().getProvincia().setNombre_provincia(consulta.getString("nombre_provincia"));
                empleado.getCanton().setId_canton(consulta.getInt("id_canton"));
                empleado.getCanton().setNombre_canton(consulta.getString("nombre_canton"));
                empleado.setIdentificacion(consulta.getString("identificacion"));
                empleado.setTipo_identificacion(consulta.getString("tipo_identificacion"));
                empleado.setApellidos(consulta.getString("apellidos"));
                empleado.setNombres(consulta.getString("nombres"));
                empleado.setEstado_civil(consulta.getString("estado_civil"));
                empleado.setGenero(consulta.getString("genero"));
                empleado.setDireccion(consulta.getString("direccion"));
                empleado.setTelefono(consulta.getString("telefono"));
                empleado.setCorreo_personal(consulta.getString("correo_personal"));
                empleado.setContrato(consulta.getString("contrato"));
                empleado.setEscalafon(consulta.getString("escalafon"));
                empleado.setFoto(consulta.getString("foto"));
                empleado.setNacionalidad(consulta.getString("nacionalidad"));
                empleado.setFecha_nacimiento(consulta.getDate("fecha_nacimiento"));
                empleado.setCelular(consulta.getString("celular"));
                empleado.setCorreo_institucional(consulta.getString("correo_institucional"));

                lista.add(empleado);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }

    public boolean modificarEmpleado(Empleado empleado) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            connecPostgresql.callableStatement.setInt(1, empleado.getId_empleado());
            connecPostgresql.callableStatement.setInt(2, empleado.getCanton().getId_canton());
            connecPostgresql.callableStatement.setString(3, empleado.getIdentificacion());
            connecPostgresql.callableStatement.setString(4, empleado.getTipo_identificacion());
            connecPostgresql.callableStatement.setString(5, empleado.getApellidos());
            connecPostgresql.callableStatement.setString(6, empleado.getNombres());
            connecPostgresql.callableStatement.setString(7, empleado.getEstado_civil());
            connecPostgresql.callableStatement.setString(8, empleado.getGenero());
            connecPostgresql.callableStatement.setString(9, empleado.getDireccion());
            connecPostgresql.callableStatement.setString(10, empleado.getTelefono());
            connecPostgresql.callableStatement.setString(11, empleado.getCorreo_personal());
            connecPostgresql.callableStatement.setString(12, empleado.getContrato());
            connecPostgresql.callableStatement.setString(13, empleado.getEscalafon());
            connecPostgresql.callableStatement.setString(14, empleado.getFoto());
            connecPostgresql.callableStatement.setString(15, empleado.getNacionalidad());
            connecPostgresql.callableStatement.setDate(16, new java.sql.Date(empleado.getFecha_nacimiento().getTime()));
            connecPostgresql.callableStatement.setString(17, empleado.getCelular());
            connecPostgresql.callableStatement.setString(18, empleado.getCorreo_institucional());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }

    }
    
    public boolean eliminarEmpleado(Empleado empleado) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call eliminar_empleado(?)}");
            connecPostgresql.callableStatement.setInt(1, empleado.getId_empleado());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public int obtenerIdEmpleado(Empleado empleado) throws SQLException {
        try {
            int idEmpleado = 0;
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idempleado_filtro_identificacion(?)}");
                connecPostgresql.callableStatement.setString(1, empleado.getIdentificacion());
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    idEmpleado = consulta.getInt(1);
                }
                connecPostgresql.getConnection().close();
            }
            return idEmpleado;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    public String ValidarIdentificacion(String identificacion) throws SQLException {
        try {
            String ident = "";
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call validar_identificacion_empleado(?)}");
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
    public String ValidarCorreoPersonal(String correoPersonal) throws SQLException {
        try {
            String valido = "";
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call validar_correo_personal_empleado(?)}");
                connecPostgresql.callableStatement.setString(1, correoPersonal);
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    valido = consulta.getString(1);
                }
                connecPostgresql.getConnection().close();
            }
            return valido;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return "";
        }
    }
    public String ValidarCorreoInstitucional(String correoInstitucional) throws SQLException {
        try {
            String valido = "";
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call validar_correo_institucional_empleado(?)}");
                connecPostgresql.callableStatement.setString(1, correoInstitucional);
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    valido = consulta.getString(1);
                }
                connecPostgresql.getConnection().close();
            }
            return valido;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return "";
        }
    }
}
