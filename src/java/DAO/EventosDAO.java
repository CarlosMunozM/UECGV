/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Eventos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    int r=0;
    public int agregar(Eventos p){
        ConexionPostgreSQL cn=new ConexionPostgreSQL();
        String sql="INSERT INTO eventos( id_usuario, nombre_evento, descripcion, fecha_inicio,fecha_fin, imagen)\n" +
                   "VALUES ( ?, ?, ?, ?,?, ?);";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, p.getId_usuario());
            ps.setString(2, p.getNombre_evento());
            ps.setString(3, p.getDescripcion());
            ps.setDate(4, p.getFeha_inicio());
            ps.setDate(5, p.getFecha_fin());
            ps.setString(6, p.getRutaimagen());
            ps.executeUpdate();
            con.close();
        }catch(Exception e){}
        return r;
    }
    public List<Eventos>Listar(){
        ConexionPostgreSQL cn=new ConexionPostgreSQL();
        String sql="Select e.nombres, e.apellidos,evt.nombre_evento,evt.descripcion,evt.fecha_inicio,evt.fecha_fin,evt.imagen\n" +
" from eventos as evt inner join usuario u on evt.id_usuario=u.id_usuario\n" +
"   		inner join empleado as e on e.id_empleado=u.id_empleado \n" +
"   		group by e.nombres, e.apellidos,evt.nombre_evento,evt.descripcion,evt.fecha_inicio,evt.fecha_fin,evt.imagen";
        List<Eventos>lista=new ArrayList<>();
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            con.close();
            while(rs.next()){
                Eventos p=new Eventos();
                p.setNombrepersona(rs.getString(1));
                p.setNombre_evento(rs.getString(3));
                p.setDescripcion(rs.getString(4));
                p.setFeha_inicio(Date.valueOf(rs.getString(5)));
                p.setFecha_fin(Date.valueOf(rs.getString(6)));
                p.setRutaimagen(rs.getString(7));
                //p.setRuta(rs.getString(3));
                //p.setEstado(Boolean.valueOf(rs.getString(4)));
                lista.add(p);
            }
            
        }catch(Exception e){}
        return lista;
    }
}
