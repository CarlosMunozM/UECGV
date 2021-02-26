/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CorreoDAO;
import DAO.UsuarioDAO;
import Modelo.Correo;
import Modelo.Empleado;
import Modelo.Usuario;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "srvUsuario", urlPatterns = {"/srvUsuario"})
public class srvUsuario extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //para que reconozca la ñ y los acentos
            request.setCharacterEncoding("UTF-8");

            String accion = request.getParameter("accion"), codigo = "", clave = "", nombreUser = "";
            String nombreContactenos = "", correoContactenos = "", asuntoContactenos = "", mensajeContactenos = "", estructuraMensaje = "";
            char[] caracteres = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '@', '$'};

            Usuario usuario;
            Empleado empleado;
            Correo correo;
            CorreoDAO correoDAO;
            UsuarioDAO usuarioDAO;

            HttpSession session = request.getSession();
            ArrayList<Usuario> listaCuentasUsuarios = new ArrayList<Usuario>();
            ArrayList<Empleado> listaEmpleadosCuentasUsuarios = new ArrayList<Empleado>();

            switch (accion) {
                case "iniciarSesion":
                    try {

                        usuario = new Usuario();
                        usuarioDAO = new UsuarioDAO();
                        usuario.setUsuario(request.getParameter("usuario"));
                        usuario.setClave(request.getParameter("clave"));

                        usuario = usuarioDAO.iniciarSesion(usuario);

                        if (usuario != null) {

                            session.setAttribute("id_usuario", usuario.getId_usuario());
                            session.setAttribute("id_empleado", usuario.getEmpleado().getId_empleado());
                            session.setAttribute("nombres", usuario.getEmpleado().getNombres());
                            session.setAttribute("apellidos", usuario.getEmpleado().getApellidos());

                            response.getWriter().write(usuario.getCambiar_clave());
                        } else {
                            response.getWriter().write("");
                        }

                    } catch (Exception ex) {
                        System.out.print(ex.getMessage());
                        out.print(ex.getMessage());
                    }
                    break;
                case "actualizarClave":
                    try {
                        usuario = new Usuario();
                        usuarioDAO = new UsuarioDAO();

                        usuario.setUsuario(request.getParameter("usuario"));
                        usuario.setClave(request.getParameter("clave"));

                        if (usuarioDAO.actualizarClave(usuario)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "buscarUsuario":

                    try {
                        usuario = new Usuario();
                        usuarioDAO = new UsuarioDAO();
                        usuario.setUsuario(request.getParameter("usuario"));
                        if (usuarioDAO.buscarUsuario(usuario)) {
                            response.getWriter().write("usuarioOcupado");
                        } else {
                            response.getWriter().write("usuarioLibre");
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        out.print(ex.getMessage());
                    }
                    break;
                case "cerrar_sesion":
                    try {
                        request.getSession().invalidate();
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "mostrar_cuentas_usuarios":
                    try {
                        usuario = new Usuario();
                        usuarioDAO = new UsuarioDAO();

                        usuario.setId_usuario(Integer.parseInt(request.getSession().getAttribute("id_usuario").toString()));
                        listaCuentasUsuarios = usuarioDAO.mostrarCuentasUsuarios(usuario);

                        request.setAttribute("listaCuentasUsuarios", listaCuentasUsuarios);
                        request.getRequestDispatcher("/Administracion/mostrarCuentasUsuarios.jsp").forward(request, response);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "registrar_usuario":
                    try {
                        usuario = new Usuario();
                        correo = new Correo();
                        usuarioDAO = new UsuarioDAO();
                        correoDAO = new CorreoDAO();

                        usuario.getEmpleado().setId_empleado(Integer.parseInt(request.getParameter("id_empleado")));
                        usuario.setUsuario(request.getParameter("usuario"));

                        if (!request.getParameter("correo").equals("")) {
                            usuario.getEmpleado().setCorreo_institucional(request.getParameter("correo").trim());

                            for (int i = 0; i < 8; i++) {
                                clave += caracteres[new Random().nextInt(38)];
                            }

                            correo = new Correo();
                            correo.setAsunto("Cuenta de Usuario");
                            correo.setMensaje("Su usuario es: " + usuario.getUsuario() + "\n\nSu clave es: " + clave + "");
                            correo.setDestino(usuario.getEmpleado().getCorreo_institucional());
                            if (correoDAO.enviarCorreo(correo)) {
                                usuario.setClave(clave);
                                if (usuarioDAO.registrarUsuario(usuario)) {
                                    response.getWriter().write("ok");
                                } else {
                                    response.getWriter().write("error");
                                }
                            } else {
                                response.getWriter().write("error");
                            }
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "modificar_usuario":
                    try {
                        usuario = new Usuario();
                        usuarioDAO = new UsuarioDAO();

                        usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
                        usuario.setUsuario(request.getParameter("usuario"));
                        //usuario.setClave(request.getParameter("clave"));

                        if (usuarioDAO.modificarUsuario(usuario)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "eliminar_usuario":
                    try {
                        usuario = new Usuario();
                        usuarioDAO = new UsuarioDAO();

                        usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));

                        if (!usuarioDAO.eliminarUsuario(usuario)) {
                            request.setAttribute("mensaje", "Error al eliminar Usuario!");
                        }
                        
                        usuario.setId_usuario(Integer.parseInt(request.getSession().getAttribute("id_usuario").toString()));
                        listaCuentasUsuarios = usuarioDAO.mostrarCuentasUsuarios(usuario);

                        request.setAttribute("listaCuentasUsuarios", listaCuentasUsuarios);
                        request.getRequestDispatcher("/Administracion/mostrarCuentasUsuarios.jsp").forward(request, response);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "mostrar_empleados_sin_usuario":
                    try {

                        usuarioDAO = new UsuarioDAO();

                        listaEmpleadosCuentasUsuarios = usuarioDAO.mostrarEmpleadosSinUsuario();

                        request.setAttribute("listaEmpleadosCuentasUsuarios", listaEmpleadosCuentasUsuarios);
                        request.getRequestDispatcher("/Administracion/registrarCuentasUsuarios.jsp").forward(request, response);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "buscarCorreoInstitucional":
                    try {
                        empleado = new Empleado();
                        usuarioDAO = new UsuarioDAO();

                        empleado.setCorreo_institucional(request.getParameter("correo"));
                        if (usuarioDAO.buscarCorreoInstitucionalEmpleado(empleado)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("");
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "enviarCodigo":
                    try {
                        usuario = new Usuario();
                        correo = new Correo();
                        usuarioDAO = new UsuarioDAO();
                        correoDAO = new CorreoDAO();

                        for (int i = 0; i < 5; i++) {
                            codigo += caracteres[new Random().nextInt(38)];
                        }

                        usuario.getEmpleado().setCorreo_institucional(request.getParameter("correo").trim());
                        usuario.setId_usuario(usuarioDAO.obtenerIdUsuario(usuario));
                        usuario.setCodigo_correo(codigo);

                        correo.setAsunto("Recuperar Clave");
                        correo.setMensaje("Su código es: " + usuario.getCodigo_correo());
                        correo.setDestino(usuario.getEmpleado().getCorreo_institucional());

                        if (correoDAO.enviarCorreo(correo)) {

                            if (usuarioDAO.modificarCodigoCorreo(usuario)) {
                                response.getWriter().write(String.valueOf(usuario.getId_usuario()));
                            } else {
                                response.getWriter().write("Error al guardar código en la Base de Datos");
                            }

                        } else {
                            response.getWriter().write("Error al enviar email");
                        }

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "recuperarClave":
                    try {
                        usuario = new Usuario();
                        correo = new Correo();
                        usuarioDAO = new UsuarioDAO();
                        correoDAO = new CorreoDAO();

                        usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
                        usuario.setCodigo_correo(request.getParameter("codigo"));

                        if (!request.getParameter("correo").equals("")) {
                            usuario.getEmpleado().setCorreo_institucional(request.getParameter("correo").trim());
                            nombreUser = usuarioDAO.verificarCodigo(usuario);
                            if (!nombreUser.equals("")) {

                                for (int i = 0; i < 8; i++) {
                                    clave += caracteres[new Random().nextInt(38)];
                                }

                                correo = new Correo();
                                correo.setAsunto("Recuperar Clave");
                                correo.setMensaje("Su usuario es: " + nombreUser + "\n\nSu clave es: " + clave + "");
                                correo.setDestino(usuario.getEmpleado().getCorreo_institucional());
                                if (correoDAO.enviarCorreo(correo)) {
                                    usuario.setClave(clave);
                                    if (usuarioDAO.cambiarClaveRequerido(usuario)) {
                                        response.getWriter().write("ok");
                                    } else {
                                        response.getWriter().write("");
                                    }
                                } else {
                                    response.getWriter().write("");
                                }

                            } else {
                                response.getWriter().write("Código Incorrecto");
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "recuperarClaveAdministracion":
                    try {
                        usuario = new Usuario();
                        correo = new Correo();
                        usuarioDAO = new UsuarioDAO();
                        correoDAO = new CorreoDAO();

                        usuario.setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
                        usuario.setUsuario(request.getParameter("usuario"));

                        if (!request.getParameter("correo").equals("")) {
                            usuario.getEmpleado().setCorreo_institucional(request.getParameter("correo").trim());

                            for (int i = 0; i < 8; i++) {
                                clave += caracteres[new Random().nextInt(38)];
                            }

                            correo = new Correo();
                            correo.setAsunto("Recuperar Clave");
                            correo.setMensaje("Su usuario es: " + usuario.getUsuario() + "\n\nSu clave es: " + clave + "");
                            correo.setDestino(usuario.getEmpleado().getCorreo_institucional());
                            if (correoDAO.enviarCorreo(correo)) {
                                usuario.setClave(clave);
                                if (usuarioDAO.cambiarClaveRequerido(usuario)) {
                                    request.setAttribute("confirmacion", "Credenciales enviadas al correo institucional del usuario");
                                } else {
                                    request.setAttribute("mensaje", "No se pudo realizar la recuperación de clave!");
                                }
                            } else {
                                request.setAttribute("mensaje", "No se pudo realizar la recuperación de clave!");
                            }
                        } else {
                            request.setAttribute("mensaje", "No se pudo realizar la recuperación de clave!");
                        }

                        usuario.setId_usuario(Integer.parseInt(request.getSession().getAttribute("id_usuario").toString()));
                        listaCuentasUsuarios = usuarioDAO.mostrarCuentasUsuarios(usuario);

                        request.setAttribute("listaCuentasUsuarios", listaCuentasUsuarios);
                        request.getRequestDispatcher("/Administracion/mostrarCuentasUsuarios.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "modificarClaveNoRequerido":
                    try {
                        usuario = new Usuario();
                        usuarioDAO = new UsuarioDAO();

                        usuario.setId_usuario(Integer.parseInt(request.getSession().getAttribute("id_usuario").toString()));
                        usuario.setClave(request.getParameter("txtModPassClave"));

                        if (usuarioDAO.cambiarClaveNoRequerido(usuario)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                
                case "contactenos":
                    try {
                        correo = new Correo();
                        correoDAO = new CorreoDAO();
                        
                        nombreContactenos = request.getParameter("nombre");
                        correoContactenos = request.getParameter("correo");
                        asuntoContactenos = request.getParameter("asunto");
                        mensajeContactenos = request.getParameter("mensaje");
                        
                        if (!correoContactenos.equals("")) {
                            
                            estructuraMensaje = "<b>Nombre: </b>"+ nombreContactenos +"<br>";
                            estructuraMensaje += "<b>Correo: </b>"+ correoContactenos +"<br>";
                            estructuraMensaje += "<b>Asunto: </b>"+ asuntoContactenos +"<br>";
                            estructuraMensaje += "<b>Mensaje: </b><br>"+ mensajeContactenos +"<br>";
                            
                            correo = new Correo();
                            correo.setAsunto("Pregunta");
                            correo.setMensaje(estructuraMensaje);
                            correo.setDestino("uemcarmelinagranjavquevedo@gmail.com");
                            
                            if (correoDAO.enviarCorreoHTML(correo)) {
                                response.getWriter().write("ok");
                            } else {
                                response.getWriter().write("No se pudo enviar el mensaje");
                            }
                        } else {
                            response.getWriter().write("Correo Inválido");
                        }
                    } catch (Exception ex) {
                        response.getWriter().write("No se pudo enviar el mensaje");
                        System.out.println(ex.getMessage());
                    }
                    break;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
