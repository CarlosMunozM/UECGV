/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Canton;
import Modelo.Cargo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class CargoDAO {
    
    private ConexionPostgreSQL connecPostgresql;
    
    public ArrayList<Cargo> mostrarCargos() throws ParseException, SQLException {
        ArrayList<Cargo> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_cargos()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Cargo cargo = new Cargo();
                
                cargo.setId_cargo(consulta.getInt("id_cargo"));
                cargo.setNombre_cargo(consulta.getString("nombre_cargo"));
                cargo.setJerarquia(consulta.getString("jerarquia"));
                
                lista.add(cargo);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
}
