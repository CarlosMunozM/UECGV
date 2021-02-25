/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Canton;
import Modelo.Provincia;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class ProvinciaDAO {
    
    private ConexionPostgreSQL connecPostgresql;
    
    public ArrayList<Provincia> mostrarProvincias() throws ParseException, SQLException {
        ArrayList<Provincia> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_provincias()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Provincia provincia = new Provincia();
                
                provincia.setId_provincia(consulta.getInt("id_provincia"));
                provincia.setId_provincia(consulta.getInt("id_provincia"));
                provincia.setNombre_provincia(consulta.getString("nombre_provincia"));
                
                lista.add(provincia);

            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    public String mostrarProvinciasPorCanton(int idcanton) throws ParseException, SQLException {
        String provincia = "";
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_provincia_filtro_canton(?)}");
            connecPostgresql.callableStatement.setInt(1, idcanton);
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {                                           
                provincia=consulta.getString("nombre_provincia");                               
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return provincia;
    }
    
}
