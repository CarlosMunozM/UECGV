/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Imagenes_PresentacionDAO;
import Modelo.Imagenes_Presentacion;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "srvImagenes_Presentacion", urlPatterns = {"/srvImagenes_Presentacion"})
public class srvImagenes_Presentacion extends HttpServlet {

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

            String accion = request.getParameter("accion");

            Imagenes_Presentacion imagenes_Presentacion;
            Imagenes_PresentacionDAO imagenes_PresentacionDAO;

            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");

            ArrayList<Imagenes_Presentacion> listaImgenesPresentacion = new ArrayList<Imagenes_Presentacion>();

            switch (accion) {

                case "registrar_imagen_presentacion":
                    try {
                        imagenes_Presentacion = new Imagenes_Presentacion();
                        imagenes_PresentacionDAO = new Imagenes_PresentacionDAO();

                        imagenes_Presentacion.getUsuario().setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
                        imagenes_Presentacion.setRuta(request.getParameter("ruta"));

                        if (imagenes_PresentacionDAO.registrarImagenPresentacion(imagenes_Presentacion)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "mostrar_imagen_presentacion":
                    try {
                        imagenes_Presentacion = new Imagenes_Presentacion();
                        imagenes_PresentacionDAO = new Imagenes_PresentacionDAO();

                        listaImgenesPresentacion = imagenes_PresentacionDAO.mostrarImagenesPresentacion();

                        out.print(new Gson().toJson(listaImgenesPresentacion));

                        //request.setAttribute("listaCantones", listaCantones);
                        //request.getRequestDispatcher("/Administracion/mostrarUsuarios.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "modificar_imagen_presentacion":
                    try {
                        imagenes_Presentacion = new Imagenes_Presentacion();
                        imagenes_PresentacionDAO = new Imagenes_PresentacionDAO();

                        imagenes_Presentacion.setId_imgPresentacion(Integer.parseInt(request.getParameter("id_imgpresentacion")));
                        imagenes_Presentacion.getUsuario().setId_usuario(Integer.parseInt(request.getParameter("id_usuario")));
                        imagenes_Presentacion.setRuta(request.getParameter("ruta"));

                        if (imagenes_PresentacionDAO.modificarImagenPresentacion(imagenes_Presentacion)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "eliminar_imagen_presentacion":
                    try {
                        imagenes_Presentacion = new Imagenes_Presentacion();
                        imagenes_PresentacionDAO = new Imagenes_PresentacionDAO();

                        imagenes_Presentacion.setId_imgPresentacion(Integer.parseInt(request.getParameter("id_imgpresentacion")));

                        if (imagenes_PresentacionDAO.eliminarImagenPresentacion(imagenes_Presentacion)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
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
