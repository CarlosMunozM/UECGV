/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Clases.ConexionPostgreSQL;
import Modelo.Empleado;
import Modelo.Usuario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class UsuarioDAO {

    private ConexionPostgreSQL connecPostgresql;
    
    public ArrayList<Usuario> mostrarCuentasUsuarios(Usuario u) throws ParseException, SQLException {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_usuarios(?)}");
            connecPostgresql.callableStatement.setInt(1, u.getId_usuario());
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Usuario usuario = new Usuario();
                
                usuario.setId_usuario(consulta.getInt("id_usuario"));
                usuario.setUsuario(consulta.getString("usuario"));
                usuario.getEmpleado().setId_empleado(consulta.getInt("id_empleado"));
                usuario.getEmpleado().setApellidos(consulta.getString("apellidos"));
                usuario.getEmpleado().setNombres(consulta.getString("nombres"));
                usuario.getEmpleado().setIdentificacion(consulta.getString("identificacion"));
                usuario.getEmpleado().setCorreo_institucional(consulta.getString("correo_institucional"));
                
                lista.add(usuario);

            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }
    
    public ArrayList<Empleado> mostrarEmpleadosSinUsuario() throws ParseException, SQLException {
        ArrayList<Empleado> lista = new ArrayList<>();
        try {
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call mostrar_empleados_sin_usuario()}");
            consulta = connecPostgresql.callableStatement.executeQuery();
            while (consulta.next()) {

                Empleado empleado = new Empleado();
                
                empleado.setId_empleado(consulta.getInt("id_empleado"));
                empleado.setApellidos(consulta.getString("apellidos"));
                empleado.setNombres(consulta.getString("nombres"));
                empleado.setIdentificacion(consulta.getString("identificacion"));
                empleado.setCorreo_institucional(consulta.getString("correo_institucional"));
                
                lista.add(empleado);

            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }
        connecPostgresql.getConnection().close();
        return lista;
    }

    public Usuario iniciarSesion(Usuario user) throws SQLException {
        Usuario usuario = null;
        ResultSet consulta;

        try {
            connecPostgresql = new ConexionPostgreSQL();

            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call iniciar_sesion (?,?)}");
                connecPostgresql.callableStatement.setString(1, user.getUsuario());
                connecPostgresql.callableStatement.setString(2, user.getClave());

                consulta = connecPostgresql.callableStatement.executeQuery();

                if (consulta.next()) {
                    usuario = new Usuario();
                    usuario.setId_usuario(consulta.getInt("id_usuario"));
                    usuario.getEmpleado().setId_empleado(consulta.getInt("id_empleado"));
                    usuario.getEmpleado().setApellidos(consulta.getString("apellidos"));
                    usuario.getEmpleado().setNombres(consulta.getString("nombres"));
                    usuario.setCambiar_clave(consulta.getString("cambiar_clave"));
                }
            }

        } catch (Exception ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
        }

        connecPostgresql.getConnection().close();
        return usuario;
    }

    public boolean actualizarClave(Usuario usuarios) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call actualizar_clave(?,?)}");
            connecPostgresql.callableStatement.setString(1, usuarios.getUsuario());
            connecPostgresql.callableStatement.setString(2, usuarios.getClave());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean buscarUsuario(Usuario user) throws SQLException {
        try {
            String usuario = "";
            boolean b = false;
            ResultSet consulta;
            connecPostgresql = new ConexionPostgreSQL();
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call buscar_usuario(?)}");
                connecPostgresql.callableStatement.setString(1, user.getUsuario());
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    usuario = consulta.getString(1);

                    if (usuario.equals("si")) {
                        b = true;
                    }
                }
                connecPostgresql.getConnection().close();
            }
            return b;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean registrarUsuario(Usuario usuario) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call registrar_usuario(?,?,?)}");

            connecPostgresql.callableStatement.setInt(1, usuario.getEmpleado().getId_empleado());
            connecPostgresql.callableStatement.setString(2, usuario.getUsuario());
            connecPostgresql.callableStatement.setString(3, usuario.getClave());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public boolean modificarUsuario(Usuario usuario) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_usuario(?,?)}");

            connecPostgresql.callableStatement.setInt(1, usuario.getId_usuario());
            connecPostgresql.callableStatement.setString(2, usuario.getUsuario());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean eliminarUsuario(Usuario usuario) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call eliminar_usuario(?)}");

            connecPostgresql.callableStatement.setInt(1, usuario.getId_usuario());

            connecPostgresql.callableStatement.executeUpdate();
            connecPostgresql.getConnection().close();
            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
     public boolean buscarCorreoInstitucionalEmpleado(Empleado empleado) throws SQLException {
        try {
            String correo = "";
            boolean b = false;
            ResultSet consulta;
            connecPostgresql = new ConexionPostgreSQL();
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call buscar_correo_institucional_empleado(?)}");
                connecPostgresql.callableStatement.setString(1, empleado.getCorreo_institucional());
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    correo = consulta.getString(1);

                    if (correo.equals("si")) {
                        b = true;
                    }
                }
                connecPostgresql.getConnection().close();
            }
            return b;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public int obtenerIdUsuario(Usuario usuario) throws SQLException {
        try {
            int idUsuario = 0;
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call obtener_idusuario(?)}");
                connecPostgresql.callableStatement.setString(1, usuario.getEmpleado().getCorreo_institucional());
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    idUsuario = consulta.getInt(1);
                }
                connecPostgresql.getConnection().close();
            }
            return idUsuario;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return 0;
        }
    }
    
    public boolean modificarCodigoCorreo(Usuario usuario) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_codigo_correo (?,?)}");
                connecPostgresql.callableStatement.setInt(1, usuario.getId_usuario());
                connecPostgresql.callableStatement.setString(2, usuario.getCodigo_correo());
                connecPostgresql.callableStatement.executeUpdate();
                connecPostgresql.getConnection().close();
            } else {
                return false;
            }

            return true;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }

    public String verificarCodigo(Usuario usuario) throws SQLException {
        try {
            String nombre = "";
            connecPostgresql = new ConexionPostgreSQL();
            ResultSet consulta;
            if (connecPostgresql.getMessage().equals("ok")) {
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call verificar_codigo_correo(?,?)}");
                connecPostgresql.callableStatement.setInt(1, usuario.getId_usuario());
                connecPostgresql.callableStatement.setString(2, usuario.getCodigo_correo());
                consulta = connecPostgresql.callableStatement.executeQuery();
                if (consulta.next()) {
                    nombre = consulta.getString(1);
                    if (nombre == null) {
                        nombre = "";
                    }
                }
                connecPostgresql.getConnection().close();
            }
            return nombre;
        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return "";
        }
    }
    
    public boolean cambiarClaveRequerido(Usuario usuario) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            String metodo = "";
            if (connecPostgresql.getMessage().equals("ok")) { 
                
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_clave_requerido(?,?)}");
                connecPostgresql.callableStatement.setInt(1, usuario.getId_usuario());
                connecPostgresql.callableStatement.setString(2, usuario.getClave());

                connecPostgresql.callableStatement.executeQuery();
                connecPostgresql.getConnection().close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
    
    public boolean cambiarClaveNoRequerido(Usuario usuario) throws SQLException {
        try {
            connecPostgresql = new ConexionPostgreSQL();
            String metodo = "";
            if (connecPostgresql.getMessage().equals("ok")) { 
                
                connecPostgresql.callableStatement = connecPostgresql.connection.prepareCall("{call modificar_clave_no_requerido(?,?)}");
                connecPostgresql.callableStatement.setInt(1, usuario.getId_usuario());
                connecPostgresql.callableStatement.setString(2, usuario.getClave());

                connecPostgresql.callableStatement.executeQuery();
                connecPostgresql.getConnection().close();
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            connecPostgresql.getConnection().close();
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
