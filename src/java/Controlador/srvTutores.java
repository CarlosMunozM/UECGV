/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.TutoresDAO;
import Modelo.Tutores;
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
@WebServlet(name = "srvTutores", urlPatterns = {"/srvTutores"})
public class srvTutores extends HttpServlet {

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
            String idCurso = request.getParameter("idCurso");
            String paralelo = request.getParameter("paralelo");

            Tutores tutores;
            TutoresDAO tutoresDAO;

            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");

            ArrayList<Tutores> listaTutores = new ArrayList<Tutores>();

            switch (accion) {
                case "registrar_tutor":
                    try {
                        tutores = new Tutores();
                        tutoresDAO = new TutoresDAO();

                       // tutores.getCargo_empleado().setId_cargoEmpleado(Integer.parseInt(request.getParameter("id_cargoempleado")));
                        tutores.getCurso_educativo().setId_curso(Integer.parseInt(request.getParameter("id_curso")));
                        tutores.setFecha_inicio(parseador.parse(request.getParameter("fecha_inicio")));
                        tutores.setFecha_fin(parseador.parse(request.getParameter("fecha_fin")));
                        tutores.setParalelo(request.getParameter("paralelo"));

//                        if (tutoresDAO.registrarTutor(tutores)) {
//                            response.getWriter().write("ok");
//                        } else {
//                            response.getWriter().write("error");
//                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "mostrar_tutores":
                    try {
                        tutores = new Tutores();
                        tutoresDAO = new TutoresDAO();

                        listaTutores = tutoresDAO.mostrarTutores();

                        out.print(new Gson().toJson(listaTutores));

                        //request.setAttribute("listaCantones", listaCantones);
                        //request.getRequestDispatcher("/Administracion/mostrarUsuarios.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "mostrar_tutores_filtro_persona":
                    try {
                        tutores = new Tutores();
                        tutoresDAO = new TutoresDAO();

                       // tutores.getCargo_empleado().getEmpleado().setId_empleado(Integer.parseInt(request.getParameter("id_persona")));

                       // listaTutores = tutoresDAO.mostrarTutoresFiltroPersona(tutores);

                        out.print(new Gson().toJson(listaTutores));

                        //request.setAttribute("listaCantones", listaCantones);
                        //request.getRequestDispatcher("/Administracion/mostrarUsuarios.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case "mostrar_tutores_filtro_curso":
                    try {
                        tutores = new Tutores();
                        tutoresDAO = new TutoresDAO();

                        //tutores.getCurso_educativo().setId_curso(Integer.parseInt(request.getParameter("id_curso")));

                        int idCargoEmpleado = tutoresDAO.mostrarTutoresFiltroCurso(Integer.parseInt(idCurso),paralelo);

                        if(idCargoEmpleado > 0){
                           response.getWriter().write(""+idCargoEmpleado); 
                        }else{
                           response.getWriter().write("");  
                        }
                        //request.setAttribute("listaCantones", listaCantones);
                        //request.getRequestDispatcher("/Administracion/mostrarUsuarios.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "modificar_tutor":
                    try {
                        tutores = new Tutores();
                        tutoresDAO = new TutoresDAO();

                        tutores.setId_tutor(Integer.parseInt(request.getParameter("id_tutor")));
                        //tutores.getCargo_empleado().setId_cargoEmpleado(Integer.parseInt(request.getParameter("id_cargoempleado")));
                        tutores.getCurso_educativo().setId_curso(Integer.parseInt(request.getParameter("id_curso")));
                        tutores.setFecha_inicio(parseador.parse(request.getParameter("fecha_inicio")));
                        tutores.setFecha_fin(parseador.parse(request.getParameter("fecha_fin")));
                        tutores.setParalelo(request.getParameter("paralelo"));

                        if (tutoresDAO.modificarTutor(tutores)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "eliminar_tutor":
                    try {
                        tutores = new Tutores();
                        tutoresDAO = new TutoresDAO();

                        tutores.setId_tutor(Integer.parseInt(request.getParameter("id_tutor")));

                        if (tutoresDAO.eliminarTutor(1)) {
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
