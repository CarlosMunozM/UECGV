/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Cargo_Empleado;
import Modelo.Convivencia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class ConvivenciaDAO {
    
    private ConexionPostgreSQL connecPostgresql;
    
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
    
}
