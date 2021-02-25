/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Cargo_EmpleadoDAO;
import Modelo.Cargo_Empleado;
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
@WebServlet(name = "srvCargo_Empleado", urlPatterns = {"/srvCargo_Empleado"})
public class srvCargo_Empleado extends HttpServlet {

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

            Cargo_Empleado cargo_empleado;
            Cargo_EmpleadoDAO cargo_empleadoDAO;
            
            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");

            ArrayList<Cargo_Empleado> listaCargos_Empleado = new ArrayList<Cargo_Empleado>();

            switch (accion) {
                case "mostrar_cargos_empleados":
                    try {
                        cargo_empleadoDAO = new Cargo_EmpleadoDAO();

                        listaCargos_Empleado = cargo_empleadoDAO.mostrarCargosEmpleados();

                        out.print(new Gson().toJson(listaCargos_Empleado));

                        //request.setAttribute("listaCantones", listaCantones);
                        //request.getRequestDispatcher("/Administracion/mostrarUsuarios.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "mostrar_cargos_empleados_filtro_persona":
                    try {
                        cargo_empleado = new Cargo_Empleado();
                        cargo_empleadoDAO = new Cargo_EmpleadoDAO();

                        cargo_empleado.getEmpleado().setId_empleado(Integer.parseInt(request.getParameter("id_empleado")));

                      //  listaCargos_Empleado = cargo_empleadoDAO.mostrarCargosEmpleadosFiltroPersona(cargo_empleado);

                       // out.print(new Gson().toJson(listaCargos_Empleado));

                        //request.setAttribute("listaCantones", listaCantones);
                        //request.getRequestDispatcher("/Administracion/mostrarUsuarios.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "modificar_cargo_empleado":
                    try {
                        cargo_empleado = new Cargo_Empleado();
                        cargo_empleadoDAO = new Cargo_EmpleadoDAO();

                        cargo_empleado.getEmpleado().setId_empleado(Integer.parseInt(request.getParameter("id_empleado")));
                        cargo_empleado.getCargo().setId_cargo(Integer.parseInt(request.getParameter("id_cargo")));
                        cargo_empleado.setFecha_inicio(parseador.parse(request.getParameter("fecha_inicio")));
                        cargo_empleado.setFecha_fin(parseador.parse(request.getParameter("fecha_fin")));

                        if (cargo_empleadoDAO.modificarCargoEmpleado(cargo_empleado)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "eliminar_cargo_empleado":
                    try {
                        cargo_empleado = new Cargo_Empleado();
                        cargo_empleadoDAO = new Cargo_EmpleadoDAO();

                        cargo_empleado.getEmpleado().setId_empleado(Integer.parseInt(request.getParameter("id_empleado")));

                        if (cargo_empleadoDAO.eliminarCargoEmpleado(cargo_empleado)) {
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
