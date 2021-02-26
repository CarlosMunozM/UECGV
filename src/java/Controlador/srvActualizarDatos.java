/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ActualizarDatosDAO;
import DAO.EstudianteDAO;
import Modelo.Convivencia;
import Modelo.Curso_Educativo;
import Modelo.Estudiante;
import Modelo.Estudiante_Convivencia;
import Modelo.Estudiante_Familiar;
import Modelo.Estudiante_Referencia;
import Modelo.Familiar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 *
 * @author DHL-SIS-ING
 */
@WebServlet(name = "srvActualizarDatos", urlPatterns = {"/srvActualizarDatos"})
public class srvActualizarDatos extends HttpServlet {

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
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String modo = request.getParameter("modo");
            ActualizarDatosDAO actualizarDatosDAO = new ActualizarDatosDAO();
            switch(modo){
                case "buscar_estudiante":
                    try {
                        Estudiante estudiante = new Estudiante();
                        String identificacion = request.getParameter("id");
                        estudiante = actualizarDatosDAO.mostrarEstudianteFiltroIdentificacion(identificacion.trim());
                        out.print(new Gson().toJson(estudiante));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "buscar_familiar":
                    try {
                        ArrayList<Estudiante_Familiar> estFamiliar = new ArrayList<>();
                        String id = request.getParameter("id");
                        estFamiliar = actualizarDatosDAO.mostrarFamiliarFiltroId(Integer.valueOf(id));
                        out.print(new Gson().toJson(estFamiliar));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "buscar_referencia":
                    try {
                        Estudiante_Referencia referencia = new Estudiante_Referencia();
                        String id = request.getParameter("id");
                        referencia = actualizarDatosDAO.mostrarReferenciaFiltroId(Integer.valueOf(id));
                        out.print(new Gson().toJson(referencia));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "listar_cursos":
                    try {
                        ArrayList<Curso_Educativo> lista = new ArrayList<>();
                        lista = actualizarDatosDAO.mostrarCursoEducativo();
                        out.print(new Gson().toJson(lista));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "listar_convivencia":
                    try {
                        ArrayList<Convivencia> lista = new ArrayList<>();
                        lista = actualizarDatosDAO.mostrarConvivencia();
                        out.print(new Gson().toJson(lista));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "mostrar_convivencia":
                    try {
                        ArrayList<Estudiante_Convivencia> lista = new ArrayList<>();
                        String id = request.getParameter("id");
                        lista = actualizarDatosDAO.mostrarEstudianteConvivencia(Integer.valueOf(id));
                        out.print(new Gson().toJson(lista));
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "actualizar_datos":
                    try {
                        String datos = request.getParameter("datos");
                        JSONArray jsona = new JSONArray(datos);
                        boolean respuesta = actualizarDatosDAO.actualizarEstudiante(jsona);
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
