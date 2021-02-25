/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Estudiante_Familiar;
import java.sql.SQLException;

/**
 *
 * @author Carlos
 */
public class Estudiante_FamiliarDAO {
     private ConexionPostgreSQL connecPostgresql;
       public boolean registrarEstudianteFamiliar(Estudiante_Familiar estudiante) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_familiar(?,?,?)}");
            connecPostgresql.callableStatement.setInt(1, estudiante.getIdestudiante());
            connecPostgresql.callableStatement.setInt(2, estudiante.getIdFamiliar());
            connecPostgresql.callableStatement.setString(3, estudiante.getParentesco());
     

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
