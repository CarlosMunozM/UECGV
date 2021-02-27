/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Imagen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
public class ImagenDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r=0;

    private ConexionPostgreSQL connecPostgresql;
   
        public boolean agregar(Imagen p) throws SQLException {
        ConexionPostgreSQL cn=new ConexionPostgreSQL();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_imagen_presentacion(?,?)}");
            connecPostgresql.callableStatement.setInt(1, p.getId_usuario());
            connecPostgresql.callableStatement.setString(2, p.getRuta());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public boolean eliminarImagenSlider(Imagen img) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call eliminar_imagen_presentacion(?)}");
            connecPostgresql.callableStatement.setInt(1, img.getId_imgpresentacion());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    public List<Imagen>Listar() throws SQLException{
        List<Imagen>lista=new ArrayList<>();
        connecPostgresql = new ConexionPostgreSQL();
        try{    
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_imagen_presentacion()}");
            rs = connecPostgresql.callableStatement.executeQuery();
            while(rs.next()){
                Imagen p = new Imagen();
                p.setNombre(rs.getString("nombres")+" "+rs.getString("apellidos"));
                p.setRuta(rs.getString("ruta"));
                p.setId_imgpresentacion(Integer.parseInt(rs.getString("id_imgpresentacion")));
                lista.add(p);
            }
            connecPostgresql.getConnection().close();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            connecPostgresql.getConnection().close();
        }
        return lista;
    }
}
