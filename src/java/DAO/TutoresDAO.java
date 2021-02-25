/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Referencia;
import Modelo.Tutores;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class TutoresDAO {
    
     private ConexionPostgreSQL connecPostgresql;

    public boolean registrarTutor(Tutores tutores, int idCargoEmpleado) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_tutor(?,?,?)}");
            
            connecPostgresql.callableStatement.setInt(1, idCargoEmpleado);
            connecPostgresql.callableStatement.setInt(2, tutores.getCurso_educativo().getId_curso());
            connecPostgresql.callableStatement.setString(3, tutores.getParalelo());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }

    }
    
    public ArrayList<Tutores> mostrarTutores() throws ParseException, SQLException {
        ArrayList<Tutores> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_tutores()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Tutores tutor = new Tutores();

                tutor.setId_tutor(consulta.getInt("id_tutor"));
               // tutor.getCargo_empleado().setId_cargoEmpleado(consulta.getInt("id_cargoempleado"));
                //tutor.getCargo_empleado().getEmpleado().setId_empleado(consulta.getInt("id_empleado"));
                //tutor.getCargo_empleado().getEmpleado().setApellidos(consulta.getString("apellidos"));
                //tutor.getCargo_empleado().getEmpleado().setNombres(consulta.getString("nombres"));
                tutor.getCurso_educativo().setId_curso(consulta.getInt("id_curso"));
                tutor.getCurso_educativo().setNombre_curso(consulta.getString("nombre_curso"));
                tutor.getCurso_educativo().setTipo(consulta.getString("tipo"));
                tutor.setParalelo(consulta.getString("paralelo"));
                tutor.setFecha_inicio(consulta.getDate("fecha_inicio"));
                tutor.setFecha_fin(consulta.getDate("fecha_fin"));
                
                lista.add(tutor);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
    public int mostrarTutoresFiltroPersona(int idEmpleado) throws ParseException, SQLException {
        int retid = 0;
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_tutores_filtro_persona(?)}");
            connecPostgresql.callableStatement.setInt(1, idEmpleado);
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {
                
                retid = consulta.getInt("id_tutor");
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return retid;
    }
    
    public int mostrarTutoresFiltroCurso(int idCurso,String paralelo) throws ParseException, SQLException {
        int idCarEmp = 0;
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_tutores_filtro_curso(?,?)}");
            connecPostgresql.callableStatement.setInt(1, idCurso);
            connecPostgresql.callableStatement.setString(2, paralelo);
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {
                idCarEmp = consulta.getInt("id_cargoempleado");
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return idCarEmp;
    }
    
    public boolean modificarTutor(Tutores tutores) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_tutor(?,?,?,?,?,?)}");
            
            connecPostgresql.callableStatement.setInt(1, tutores.getId_tutor());
           // connecPostgresql.callableStatement.setInt(2, tutores.getCargo_empleado().getId_cargoEmpleado());
            connecPostgresql.callableStatement.setInt(3, tutores.getCurso_educativo().getId_curso());
            connecPostgresql.callableStatement.setString(4, tutores.getParalelo());
            connecPostgresql.callableStatement.setDate(5, new java.sql.Date(tutores.getFecha_inicio().getTime()));
            connecPostgresql.callableStatement.setDate(6, new java.sql.Date(tutores.getFecha_fin().getTime()));

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean eliminarTutor(int cargoEmplead) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call eliminar_tutor(?)}");
            
            connecPostgresql.callableStatement.setInt(1, cargoEmplead);

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
}
