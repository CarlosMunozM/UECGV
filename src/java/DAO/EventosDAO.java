/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Eventos;
import Modelo.Imagen;
import java.sql.Connection;
import java.sql.Date;
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
public class EventosDAO {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r = 0;

    private ConexionPostgreSQL connecPostgresql;

    public boolean agregar(Eventos p) throws SQLException {
        ConexionPostgreSQL cn = new ConexionPostgreSQL();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_evento(?,?,?,?,?,?)}");

            connecPostgresql.callableStatement.setInt(1, p.getId_usuario());
            connecPostgresql.callableStatement.setString(2, p.getNombre_evento());
            connecPostgresql.callableStatement.setString(3, p.getDescripcion());
            connecPostgresql.callableStatement.setDate(4, p.getFeha_inicio());
            connecPostgresql.callableStatement.setDate(5, p.getFecha_fin());
            connecPostgresql.callableStatement.setString(6, p.getRutaimagen());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean modificar(Eventos p) throws SQLException {
        ConexionPostgreSQL cn = new ConexionPostgreSQL();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_evento(?,?,?,?,?,?,?)}");

            connecPostgresql.callableStatement.setInt(1, p.getId_evento());
            connecPostgresql.callableStatement.setInt(2, p.getId_usuario());
            connecPostgresql.callableStatement.setString(3, p.getNombre_evento());
            connecPostgresql.callableStatement.setString(4, p.getDescripcion());
            connecPostgresql.callableStatement.setDate(5, p.getFeha_inicio());
            connecPostgresql.callableStatement.setDate(6, p.getFecha_fin());
            connecPostgresql.callableStatement.setString(7, p.getRutaimagen());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean modificarNoimagen(Eventos p) throws SQLException {
        ConexionPostgreSQL cn = new ConexionPostgreSQL();
        try { 
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_evento_noimagen(?,?,?,?,?,?)}");

            connecPostgresql.callableStatement.setInt(1, p.getId_evento());
            connecPostgresql.callableStatement.setInt(2, p.getId_usuario());
            connecPostgresql.callableStatement.setString(3, p.getNombre_evento());
            connecPostgresql.callableStatement.setString(4, p.getDescripcion());
            connecPostgresql.callableStatement.setDate(5, p.getFeha_inicio());
            connecPostgresql.callableStatement.setDate(6, p.getFecha_fin());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean eliminarEventosImagen(Eventos p) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call eliminar_evento(?)}");
            connecPostgresql.callableStatement.setInt(1, p.getId_evento());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
     public List<Eventos>Listar() throws SQLException{
        List<Eventos>lista=new ArrayList<>();
        connecPostgresql = new ConexionPostgreSQL();
        try{    
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_eventos()}");
            rs = connecPostgresql.callableStatement.executeQuery();
            while(rs.next()){
                Eventos p = new Eventos();
                p.setNombrepersona(rs.getString("nombres")+" "+rs.getString("apellidos"));
                p.setNombre_evento(rs.getString("nombre_evento"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFeha_inicio(Date.valueOf(rs.getString("fecha_inicio")));
                p.setFecha_fin(Date.valueOf(rs.getString("fecha_fin")));
                p.setRutaimagen(rs.getString("imagen"));
                p.setId_evento(Integer.parseInt(rs.getString("id_evento")));
                p.setId_usuario(Integer.parseInt(rs.getString("id_usuario")));
                lista.add(p);
            }
            connecPostgresql.getConnection().close();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            connecPostgresql.getConnection().close();
        }
        return lista;
    }
     
    public ArrayList<String> ListarAnios() throws SQLException{
        ArrayList<String> lista = new ArrayList<>();
        connecPostgresql = new ConexionPostgreSQL();
        try{    
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_anios_eventos()}");
            rs = connecPostgresql.callableStatement.executeQuery();
            while(rs.next()){
                lista.add(rs.getString(1));
            }
            connecPostgresql.getConnection().close();
            
        }catch(SQLException e){
            System.out.println(e.getMessage());
            connecPostgresql.getConnection().close();
            return lista;
        }
        return lista;
    }
    
    public ArrayList<Eventos> ListarEventosAnio(String anio) throws SQLException{
        ArrayList<Eventos> lista = new ArrayList<>();
        connecPostgresql = new ConexionPostgreSQL();
        try{    
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_eventos_filtro_anio(?)}");
             connecPostgresql.callableStatement.setString(1, anio);
            rs = connecPostgresql.callableStatement.executeQuery();
            while(rs.next()){
                Eventos p = new Eventos();
                p.setNombre_evento(rs.getString("nombre_evento"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFeha_inicio(Date.valueOf(rs.getString("fecha_inicio")));
                p.setFecha_fin(Date.valueOf(rs.getString("fecha_fin")));
                p.setRutaimagen(rs.getString("imagen"));
                lista.add(p);
            }
            connecPostgresql.getConnection().close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            connecPostgresql.getConnection().close();
            return lista;
        }
        return lista;
    }
    
    public ArrayList<Eventos> EventosRecientes() throws SQLException{
        ArrayList<Eventos> lista = new ArrayList<>();
        connecPostgresql = new ConexionPostgreSQL();
        try{    
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_eventos_recent()}");
            rs = connecPostgresql.callableStatement.executeQuery();
            while(rs.next()){
                Eventos p = new Eventos();
                p.setNombre_evento(rs.getString("nombre_evento"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setFeha_inicio(Date.valueOf(rs.getString("fecha_inicio")));
                p.setFecha_fin(Date.valueOf(rs.getString("fecha_fin")));
                p.setRutaimagen(rs.getString("imagen"));
                lista.add(p);
            }
            connecPostgresql.getConnection().close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
            connecPostgresql.getConnection().close();
            return lista;
        }
        return lista;
    }
}
