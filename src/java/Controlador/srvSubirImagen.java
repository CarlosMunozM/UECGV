/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import DAO.ActualizarDatosDAO;
import DAO.EstudianteDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author DHL-SIS-ING
 */
@WebServlet(name = "srvSubirImagen", urlPatterns = {"/srvSubirImagen"})
@MultipartConfig
public class srvSubirImagen extends HttpServlet {

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
            throws ServletException, IOException, Exception {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            boolean respuesta = false;
            ActualizarDatosDAO actualizarDatosDAO = new ActualizarDatosDAO();
            String id_estudiante = request.getParameter("idEstd");
            Part fotoPerfil = request.getPart("regFotoAlu");
            String foto = fotoPerfil.getSubmittedFileName();
            if(!foto.equals("")){
                if (ValidarFichero(foto)) {
                    int longuitud = foto.length();
                    String ext = foto.substring(longuitud - 4, longuitud);
                    InputStream inputStream = fotoPerfil.getInputStream();
                    String fileName = this.getServletContext().getRealPath("/Imagenes/Alumnos/");
                    // poner los cositos de manera local (//)
                    File f = new File(f_RutaModificada(fileName) + "//" + nombrarImagenEstudiante(request.getParameter("txtRegIdentificacion"), "Estudiante", ext));
                    String ruta = f.toString();
                    FileOutputStream outputStream = new FileOutputStream(f);
                    //Ruta para base de datos
                    String rutaBase = "Imagenes/Alumnos/" + nombrarImagenEstudiante(request.getParameter("txtRegIdentificacion"), "Estudiante", ext) + "";
                    
                    //Insertar Actualizacion de Foto de Perfil    
                    if(actualizarDatosDAO.actualizarFotoPerfil(rutaBase, Integer.valueOf(id_estudiante))){
                        int dato = inputStream.read();
                        while (dato != -1) {
                            outputStream.write(dato);
                            dato = inputStream.read();
                        }
                        outputStream.close();
                        inputStream.close();
                        respuesta = true;
                    }else{
                        respuesta = false;
                    }
                }
            }else{ respuesta = true; }
            
            //Foto Domicilio
            Part fotoDomicilio = request.getPart("txtRegFotoDomicilio");
            String fotoDom = fotoDomicilio.getSubmittedFileName();
            if(!fotoDom.equals("")){
                if (ValidarFichero(fotoDom)) {
                    int longuitud = fotoDom.length();
                    String ext = fotoDom.substring(longuitud - 4, longuitud);
                    InputStream inputStream = fotoDomicilio.getInputStream();
                    String fileName = this.getServletContext().getRealPath("/Imagenes/Alumnos/");
                    // poner los cositos de manera local (//)
                    File f = new File(f_RutaModificada(fileName) + "//" + nombrarImagenEstudiante(request.getParameter("txtRegIdentificacion"), "Domicilio", ext));
                    String ruta = f.toString();
                    FileOutputStream outputStream = new FileOutputStream(f);
                    //Ruta para base de datos
                    String rutaBase = "Imagenes/Alumnos/" + nombrarImagenEstudiante(request.getParameter("txtRegIdentificacion"), "Domicilio", ext) + "";
                    
                    //Insertar Actualizacion de Foto de Perfil    
                    if(actualizarDatosDAO.actualizarFotoDomicilio(rutaBase, Integer.valueOf(id_estudiante))){
                        int dato = inputStream.read();
                        while (dato != -1) {
                            outputStream.write(dato);
                            dato = inputStream.read();
                        }
                        outputStream.close();
                        inputStream.close();
                        respuesta = true;
                    }else{
                        respuesta = false;
                    }
                }
            }else{ respuesta = true; }
            
            boolean[] res = {respuesta};
            out.print(new Gson().toJson(res));
        }catch(Exception e){
            System.out.println(e);
        }
    }

    private String f_RutaModificada(String ruta) {
    //sumar 10 y 4 o restar
        int longuitud = ruta.length();
        String entrada = ruta.substring(0, longuitud - 18);
        String rutaConcat = ruta.substring(longuitud - 18, longuitud);
        String modificada = entrada + rutaConcat;
        return modificada;
    }

    private String nombrarImagenEstudiante(String Cedula, String Apellidos, String ext) {
        String NombreImagen = Cedula + "" + Apellidos + "" + ext;
        return NombreImagen;
    }

    private String generarNombre(String codigo) {
        String NombreImagen = "ImagenEmpleado" + codigo;
        return NombreImagen;
    }
    
    private boolean ValidarFichero(String nombre) {
        int longuitud = nombre.length();
        String formato = nombre.substring(longuitud - 4, longuitud);
        if (formato.equals(".jpg") || formato.equals(".PNG") || formato.equals(".png") || formato.equals(".gif") || formato.equals(".gif") || formato.equals("JPEG")) {
            return true;
        } else {
            return false;
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
        } catch (Exception ex) {
            Logger.getLogger(srvSubirImagen.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(srvSubirImagen.class.getName()).log(Level.SEVERE, null, ex);
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
