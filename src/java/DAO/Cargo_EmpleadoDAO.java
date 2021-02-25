/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Cargo_Empleado;
import Modelo.Empleado;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Cargo_EmpleadoDAO {
    
    
    private ConexionPostgreSQL connecPostgresql;
    
    public ArrayList<Cargo_Empleado> mostrarCargosEmpleados() throws ParseException, SQLException {
        ArrayList<Cargo_Empleado> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_cargos_empleados()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Cargo_Empleado cargo_empleado = new Cargo_Empleado();
                
                cargo_empleado.setId_cargoEmpleado(consulta.getInt("id_cargoempleado"));
                cargo_empleado.getCargo().setId_cargo(consulta.getInt("id_cargo"));
                cargo_empleado.getCargo().setNombre_cargo(consulta.getString("nombre_cargo"));
                cargo_empleado.getEmpleado().setId_empleado(consulta.getInt("id_empleado"));
                cargo_empleado.getEmpleado().setNombres(consulta.getString("nombres"));
                cargo_empleado.getEmpleado().setApellidos(consulta.getString("apellidos"));
                cargo_empleado.setFecha_inicio(consulta.getDate("fecha_inicio"));
                cargo_empleado.setFecha_fin(consulta.getDate("fecha_fin"));
                
                lista.add(cargo_empleado);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
    public ArrayList<Cargo_Empleado> mostrarCargosEmpleadosFiltroPersona(Empleado cg) throws ParseException, SQLException {
        ArrayList<Cargo_Empleado> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_cargo_empleado_filtro_persona(?)}");
            connecPostgresql.callableStatement.setInt(1, cg.getId_empleado());
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Cargo_Empleado cargo_empleado = new Cargo_Empleado();
                
                cargo_empleado.setId_cargoEmpleado(consulta.getInt("id_cargoempleado"));
                cargo_empleado.getCargo().setId_cargo(consulta.getInt("id_cargo"));
                cargo_empleado.getCargo().setNombre_cargo(consulta.getString("nombre_cargo"));
                cargo_empleado.getEmpleado().setNombres(consulta.getString("nombres"));
                cargo_empleado.getEmpleado().setApellidos(consulta.getString("apellidos"));
                cargo_empleado.setFecha_inicio(consulta.getDate("fecha_inicio"));
                cargo_empleado.setFecha_fin(consulta.getDate("fecha_fin"));
                
                lista.add(cargo_empleado);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
    public boolean modificarCargoEmpleado(Cargo_Empleado cargo_empleado) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_cargo_empleado(?,?)}");
            connecPostgresql.callableStatement.setInt(1, cargo_empleado.getId_cargoEmpleado());   
            connecPostgresql.callableStatement.setString(2, cargo_empleado.getEstado());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean eliminarCargoEmpleado(Cargo_Empleado cargo_empleado) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call eliminar_cargo_empleado(?)}");
            connecPostgresql.callableStatement.setInt(1, cargo_empleado.getEmpleado().getId_empleado());     

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean registrarCargoEmpleado(Cargo_Empleado cargo_empleado) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_cargo_empleado(?,?)}");
            connecPostgresql.callableStatement.setInt(1, cargo_empleado.getCargo().getId_cargo());
            connecPostgresql.callableStatement.setInt(2, cargo_empleado.getEmpleado().getId_empleado());
           
            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }

    }public int obtenerIdCargoEmpleado(int idEmpleado) throws SQLException {
        try {
            int idEmp = 0;
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idcargo_empleado_filtro_persona(?)}");
                connecPostgresql.callableStatement.setInt(1, idEmpleado);
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    idEmp = consulta.getInt(1);
                }
                connecPostgresql.getConnection().close();
            }
            return idEmp;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    

    
}
