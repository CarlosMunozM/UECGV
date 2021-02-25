/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Autoridades;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author DHL-SIS-ING
 */
public class AutoridadesDAO {
    
    private ConexionPostgreSQL connecPostgresql;
    
    public ArrayList<Autoridades> mostrarAutoridades() throws ParseException, SQLException {
        ArrayList<Autoridades> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_autoridades()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Autoridades autoridades = new Autoridades();
                
                autoridades.setJerarquia(consulta.getString("jerarquia"));
                autoridades.setNombre_cargo(consulta.getString("nombre_cargo"));
                autoridades.setApellidos(consulta.getString("apellidos"));
                autoridades.setNombres(consulta.getString("nombres"));
                autoridades.setCorreo_institucional(consulta.getString("correo_institucional"));
                autoridades.setFoto((consulta.getString("foto") == null)? "assets/img/user-default.png":consulta.getString("foto"));
                autoridades.setGenero(consulta.getString("genero"));
                
                lista.add(autoridades);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
}
