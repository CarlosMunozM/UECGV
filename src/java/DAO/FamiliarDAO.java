/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Estudiante;
import Modelo.Estudiante_Familiar;
import Modelo.Familiar;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class FamiliarDAO {

    private ConexionPostgreSQL connecPostgresql;

    public ArrayList<Familiar> mostrarFamiliar() throws ParseException, SQLException {
        ArrayList<Familiar> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_familiares()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Familiar familiar = new Familiar();

                familiar.setId_familiar(consulta.getInt("id_familiar"));
                familiar.setIdentificacion(consulta.getString("identificacion"));
                familiar.setTipo_identificacion(consulta.getString("tipo_identificacion"));
                familiar.setApellidos(consulta.getString("apellidos"));
                familiar.setNombres(consulta.getString("nombres"));
                familiar.setCue(consulta.getString("cue"));
                familiar.setCelular(consulta.getString("celular"));
                familiar.setOcupacion(consulta.getString("ocupacion"));
                familiar.setLugar_trabajo(consulta.getString("lugar_trabajo"));
                familiar.setCorreo(consulta.getString("correo"));
                familiar.setNacionalidad(consulta.getString("nacionalidad"));

                lista.add(familiar);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }

    public boolean registrarFamiliar(Familiar familar) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_familiar(?,?,?,?,?,?,?,?,?,?)}");
            connecPostgresql.callableStatement.setString(1, familar.getIdentificacion());
            connecPostgresql.callableStatement.setString(2, familar.getTipo_identificacion());
            connecPostgresql.callableStatement.setString(3, familar.getNombres());
            connecPostgresql.callableStatement.setString(4, familar.getApellidos());
            connecPostgresql.callableStatement.setString(5, familar.getCue());
            connecPostgresql.callableStatement.setString(6, familar.getCelular());
            connecPostgresql.callableStatement.setString(7, familar.getOcupacion());
            connecPostgresql.callableStatement.setString(8, familar.getLugar_trabajo());
            connecPostgresql.callableStatement.setString(9, familar.getCorreo());
            connecPostgresql.callableStatement.setString(10, familar.getNacionalidad());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }

    }
    
    
    public int obtenerIdFamiliar(Familiar familiar) throws SQLException {
        try {
            int idEstudiante = 0;
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idfamiliar_filtro_identificacion(?)}");
                connecPostgresql.callableStatement.setString(1, familiar.getIdentificacion());
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    idEstudiante = consulta.getInt(1);
                }
                connecPostgresql.getConnection().close();
            }
            return idEstudiante;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}
