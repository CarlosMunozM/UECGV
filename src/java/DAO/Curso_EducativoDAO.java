/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Convivencia;
import Modelo.Curso_Educativo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Curso_EducativoDAO {
    
    private ConexionPostgreSQL connecPostgresql;
    
    public ArrayList<Curso_Educativo> mostrarCursoEducativo() throws ParseException, SQLException {
        ArrayList<Curso_Educativo> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_curso_educativo()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Curso_Educativo curso_educativo = new Curso_Educativo();
                
                curso_educativo.setId_curso(consulta.getInt("id_curso"));
                curso_educativo.setNombre_curso(consulta.getString("nombre_curso"));
                curso_educativo.setTipo(consulta.getString("tipo"));
                
                lista.add(curso_educativo);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
}
