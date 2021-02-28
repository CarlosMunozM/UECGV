/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.ImagenDAO;
import Modelo.Imagen;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ASUS
 */
@MultipartConfig
@WebServlet(name = "srvImagenPresentacion", urlPatterns = {"/srvImagenPresentacion"})
public class srvImagenPresentacion extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           String accion = request.getParameter("accion");
            HttpSession session = request.getSession();
            Imagen img;
            ImagenDAO imgdao;

            switch (accion) {
                case "Guardar":
                    ArrayList<String> lista = new ArrayList<>();
                    try {
                        img = new Imagen();
                        imgdao = new ImagenDAO();

                        img.setId_usuario(Integer.parseInt(request.getSession().getAttribute("id_usuario").toString()));
                        //img.setEstado(Boolean.valueOf(request.getParameter("txtestado").trim()));    
                        Part arch = request.getPart("RegevtFoto");
                        String n = arch.getSubmittedFileName();
                        if (ValidarFichero(n)) {
                            int longuitud = n.length();
                            String name = String.valueOf(System.currentTimeMillis());
                            String ext = n.substring(longuitud - 4, longuitud);

                            InputStream is = arch.getInputStream();

                            String fileName = this.getServletContext().getRealPath("/Imagenes/Sliders/");
                            File f = new File(f_RutaModificada(fileName) + "\\" + nombrarImagenEmpleado("as", name, ext));
                            String ruta = f.toString();
                            FileOutputStream ous = new FileOutputStream(f);
                            //Ruta para base de datos
                            String rutaBase = "Imagenes/Sliders/" + nombrarImagenEmpleado("as", name, ext) + "";
                            img.setRuta(rutaBase);

                            int dato = is.read();
                            while (dato != -1) {
                                ous.write(dato);
                                dato = is.read();
                            }
                            ous.close();
                            is.close();

                            if (imgdao.agregar(img)) {
                                //response.getWriter().write("ok");
                                
                                img = new Imagen();
                                imgdao = new ImagenDAO();

                                List<Imagen> imgsListar = imgdao.Listar();
                                request.setAttribute("imagenes", imgsListar);
                                
                                //request.getRequestDispatcher("/Administracion/imagenesSlider1.jsp").forward(request, response);
                                
                            } else {
                                response.getWriter().write("No se pudo registrar la imagen");
                            }
                            response.sendRedirect("srvImagenPresentacion?accion=Listar");
                        }
                        
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "Listar":
                    img = new Imagen();
                    imgdao = new ImagenDAO();

                    List<Imagen> imgsListar = imgdao.Listar();
                    request.setAttribute("imagenes", imgsListar);
                    request.getRequestDispatcher("/Administracion/imagenesSlider1.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    try {
                        img = new Imagen();
                        imgdao = new ImagenDAO();

                        img.setId_imgpresentacion(Integer.parseInt(request.getParameter("id")));

                        imgdao.eliminarImagenSlider(img);

                        List<Imagen> img3 = imgdao.Listar();
                        request.setAttribute("imagenes", img3);
                        response.sendRedirect("srvImagenPresentacion?accion=Listar");
                        //request.getRequestDispatcher("/Administracion/imagenesSlider1.jsp").forward(request, response);

                    } catch (Exception e) {
                    }
                    break;
                case "Listar_index":
                    response.setContentType("application/json;charset=UTF-8");
                    imgdao = new ImagenDAO();
                    List<Imagen> imagenes = imgdao.Listar();
                    out.print(new Gson().toJson(imagenes));
                    break;

            }
        }
    }
private String f_RutaModificada(String ruta) {

        int longuitud = ruta.length();
        String entrada = ruta.substring(0, longuitud - 17);
        String rutaConcat = ruta.substring(longuitud - 17, longuitud);
        String modificada = entrada + rutaConcat;
        return modificada;
    }

    private String nombrarImagenEmpleado(String Cedula, String Apellidos, String ext) {
        String NombreImagen = Cedula + "" + Apellidos + "" + ext;
        return NombreImagen;
    }

    private String generarNombre(String codigo) {
        String NombreImagen = "ImagenSlider" + codigo;
        return NombreImagen;
    }

    private boolean ValidarFichero(String nombre) {
        int longuitud = nombre.length();
        String formato = nombre.substring(longuitud - 4, longuitud);
        String hola = "";
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
        } catch (SQLException ex) {
            Logger.getLogger(srvImagenPresentacion.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(srvImagenPresentacion.class.getName()).log(Level.SEVERE, null, ex);
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
