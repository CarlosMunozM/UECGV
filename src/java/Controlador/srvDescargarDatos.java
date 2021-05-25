/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.DescargarDAO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author DHL-SIS-ING
 */
@WebServlet(name = "srvDescargarDatos", urlPatterns = {"/srvDescargarDatos"})
public class srvDescargarDatos extends HttpServlet {

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
            throws ServletException, IOException, Exception{
        //response.setContentType("text/html;charset=UTF-8");	
        response.setContentType("application/vnd.ms-excel;base64");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String seccion = request.getParameter("seccion");
            DescargarDAO descargarDAO = new DescargarDAO();
            File datos = null;
            switch(seccion){
                case "alumnos":
                    datos = descargarDAO.descargarDatosAlumnos();
                    if(datos != null){
                        FileInputStream in = new FileInputStream(datos);
                        byte[] bytes = new byte[(int)datos.length()];
                        in.read(bytes);
                        //Hacemos el encode de los bytes leídos
                        String encodedBase64 = new String(Base64.encodeBase64(bytes));
                        response.setHeader("Content-disposition", "attachment; filename=" + datos.getName());
                        //Evitar error de ya haber escrito
                        response.flushBuffer();
                        //Escribimos en el objeto response el contenido del mismo
                        //response.getOutputStream().write(encodedBase64.getBytes());
                        //out.println(encodedBase64.getBytes());
                        out.println(encodedBase64);
                        //Cerramos el InputStream
                        in.close();
                    }
                    break;
                case "empleados":
                    datos = descargarDAO.descargarDatosEmpleados();
                    if(datos != null){
                        FileInputStream in = new FileInputStream(datos);
                        byte[] bytes = new byte[(int)datos.length()];
                        in.read(bytes);
                        //Hacemos el encode de los bytes leídos
                        String encodedBase64 = new String(Base64.encodeBase64(bytes));
                        response.setHeader("Content-disposition", "attachment; filename=" + datos.getName());
                        //Evitar error de ya haber escrito
                        response.flushBuffer();
                        //Escribimos en el objeto response el contenido del mismo
                        //response.getOutputStream().write(encodedBase64.getBytes());
                        //out.println(encodedBase64.getBytes());
                        out.println(encodedBase64);
                        //Cerramos el InputStream
                        in.close();
                    }
                    break;
            }
        }catch(Exception e){
            throw e;
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(srvDescargarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(srvDescargarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(srvDescargarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(srvDescargarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(srvDescargarDatos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(srvDescargarDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
