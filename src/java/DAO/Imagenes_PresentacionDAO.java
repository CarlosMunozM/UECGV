/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Imagenes_Presentacion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Imagenes_PresentacionDAO {
    
    private ConexionPostgreSQL connecPostgresql;

    public boolean registrarImagenPresentacion(Imagenes_Presentacion imagenes_presentacion) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_imagen_presentacion(?,?)}");
            
            connecPostgresql.callableStatement.setInt(1, imagenes_presentacion.getUsuario().getId_usuario());
            connecPostgresql.callableStatement.setString(2, imagenes_presentacion.getRuta());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public ArrayList<Imagenes_Presentacion> mostrarImagenesPresentacion() throws ParseException, SQLException {
        ArrayList<Imagenes_Presentacion> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_imagen_presentacion()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Imagenes_Presentacion imagen_presentacion = new Imagenes_Presentacion();

                imagen_presentacion.setId_imgPresentacion(consulta.getInt("id_imgpresentacion"));
                imagen_presentacion.getUsuario().setId_usuario(consulta.getInt("id_usuario"));
                imagen_presentacion.setRuta(consulta.getString("ruta"));
                
                lista.add(imagen_presentacion);
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
    public boolean modificarImagenPresentacion(Imagenes_Presentacion imagenes_presentacion) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_imagen_presentacion(?,?,?)}");
            
            connecPostgresql.callableStatement.setInt(1, imagenes_presentacion.getId_imgPresentacion());
            connecPostgresql.callableStatement.setInt(2, imagenes_presentacion.getUsuario().getId_usuario());
            connecPostgresql.callableStatement.setString(3, imagenes_presentacion.getRuta());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean eliminarImagenPresentacion(Imagenes_Presentacion imagenes_presentacion) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call eliminar_imagen_presentacion(?)}");
            
            connecPostgresql.callableStatement.setInt(1, imagenes_presentacion.getId_imgPresentacion());

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
