/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Empleado;
import Modelo.Referencia;
import java.sql.ResultSet;

import java.sql.SQLException;

/**
 *
 * @author Carlos
 */
public class ReferenciaDAO {

    private ConexionPostgreSQL connecPostgresql;

    public boolean registrarReferencia(Referencia referencia) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_referencia(?,?,?,?,?,?)}");
            connecPostgresql.callableStatement.setString(1, referencia.getApellidos());
            connecPostgresql.callableStatement.setString(2, referencia.getNombres());
            connecPostgresql.callableStatement.setString(3, referencia.getCelular());
            connecPostgresql.callableStatement.setString(4, referencia.getTelefono());
            connecPostgresql.callableStatement.setString(5, referencia.getIdentificacion());

            connecPostgresql.callableStatement.setString(6, referencia.getTipo_identificacion());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }

    }

    public int obtenerIdReferencia(Referencia referencia) throws SQLException {
        try {
            int idReferencia = 0;
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idreferencia_filtro_identificacion(?)}");
                connecPostgresql.callableStatement.setString(1, referencia.getIdentificacion());
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    idReferencia = consulta.getInt(1);
                }
                connecPostgresql.getConnection().close();
            }
            return idReferencia;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return 0;
        }
    }

}
