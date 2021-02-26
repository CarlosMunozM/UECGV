/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.EmpleadoDAO;
import DAO.EstudianteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author fcoel
 */
@WebServlet(name = "srvValidacion", urlPatterns = {"/srvValidacion"})
public class srvValidacion extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            String accion = request.getParameter("accion");
            String valor = request.getParameter("valor");
            EmpleadoDAO empleadoDAO;
            EstudianteDAO estudianteDAO;
            switch (accion) {

                case "validar_identificacion":
                    try {
                        empleadoDAO = new EmpleadoDAO();
                        String existeIdent = empleadoDAO.ValidarIdentificacion(valor.trim());
                        response.getWriter().write(""+existeIdent);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "validar_correoPersonal":
                    try {
                        empleadoDAO = new EmpleadoDAO();
                        String existeCorreoPers = empleadoDAO.ValidarCorreoPersonal(valor.trim());
                        response.getWriter().write(""+existeCorreoPers);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "validar_correoInstitucional":
                    try {                                                                      
                        empleadoDAO = new EmpleadoDAO();
                        String existeCorreoInst = empleadoDAO.ValidarCorreoInstitucional(valor.trim());
                        response.getWriter().write(""+existeCorreoInst);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    case "validad_identificacionEstudiante":
                    try {                                                                      
                        estudianteDAO = new EstudianteDAO();
                        String existeCedulaEst = estudianteDAO.ValidarIdentificacionEstudiante(valor.trim());
                        response.getWriter().write(""+existeCedulaEst);
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
