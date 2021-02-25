/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Canton;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class CantonDAO {
    
    private ConexionPostgreSQL connecPostgresql;
    
    public ArrayList<Canton> mostrarCantones() throws ParseException, SQLException {
        ArrayList<Canton> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_cantones()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Canton canton = new Canton();
                
                canton.getProvincia().setId_provincia(consulta.getInt("id_provincia"));
                canton.setId_canton(consulta.getInt("id_canton"));
                canton.setNombre_canton(consulta.getString("nombre_canton"));
                
                lista.add(canton);

            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
}
