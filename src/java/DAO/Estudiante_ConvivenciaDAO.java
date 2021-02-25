/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Estudiante_Convivencia;
import java.sql.SQLException;

/**
 *
 * @author Carlos
 */
public class Estudiante_ConvivenciaDAO {
    
      private ConexionPostgreSQL connecPostgresql;
       public boolean registrarEstudianteFamiliar( Estudiante_Convivencia estudiante) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_estudiante_convivencia(?,?)}");
            connecPostgresql.callableStatement.setInt(1, estudiante.getEstudiante().getId_estudiante());
            connecPostgresql.callableStatement.setInt(2, estudiante.getConvivencia().getId_convivencia());
            
     

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
