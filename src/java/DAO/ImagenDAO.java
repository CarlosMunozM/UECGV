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
    public int agregar(Imagen p){
        ConexionPostgreSQL cn=new ConexionPostgreSQL();
        String sql="INSERT INTO imagenes_presentacion (id_usuario, ruta) VALUES (?, ?)";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.setInt(1, p.getId_usuario());
            ps.setString(2, p.getRuta());
            //ps.setBoolean(3, p.isEstado());
            ps.executeUpdate();
            con.close();
        }catch(Exception e){}
        return r;
    }
    public List<Imagen>Listar(){
        ConexionPostgreSQL cn=new ConexionPostgreSQL();
        String sql="Select e.nombres, e.apellidos,imgp.ruta,imgp.estado,imgp.id_imgpresentacion\n" +
" from imagenes_presentacion as imgp inner join usuario u on imgp.id_usuario=u.id_usuario\n" +
"inner join empleado as e on e.id_empleado=u.id_empleado\n" +
"group by imgp.id_imgpresentacion,e.nombres, e.apellidos,imgp.ruta,imgp.estado";
        List<Imagen>lista=new ArrayList<>();
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            con.close();
            while(rs.next()){
                Imagen p=new Imagen();
                p.setNombre(rs.getString(1));
                p.setRuta(rs.getString(3));
                p.setEstado(Boolean.valueOf(rs.getString(4)));
                lista.add(p);
            }
            
        }catch(Exception e){}
        return lista;
    }
}
