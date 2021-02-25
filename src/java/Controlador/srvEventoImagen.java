/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.EventosDAO;
import Modelo.Eventos;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "srvEventoImagen", urlPatterns = {"/srvEventoImagen"})
public class srvEventoImagen extends HttpServlet {

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
        String accion=request.getParameter("accion");
        HttpSession session= request.getSession();
        Eventos evt=new Eventos();
        EventosDAO evtdao=new EventosDAO(); 
        switch(accion){
            case "Guardar":
                ArrayList<String> lista=new ArrayList<>();
                try{
                    FileItemFactory file= new DiskFileItemFactory();
                    ServletFileUpload fileUpload=new ServletFileUpload(file);
                    List items=fileUpload.parseRequest(request);
                    for (int i = 0; i < items.size(); i++) {
                        FileItem fileItem=(FileItem)items.get(i);
                        if(!fileItem.isFormField()){
                            File f=new File("D:\\imagenes\\"+fileItem.getName());
                            fileItem.write(f);
                            evt.setRutaimagen("D:\\imagenes\\"+fileItem.getName());
                        }else{
                            lista.add(fileItem.getString());
                        }
                    }
                   evt.setId_usuario(Integer.parseInt(request.getSession().getAttribute("id_usuario").toString()));
                   evt.setNombre_evento(lista.get(0));
                   evt.setDescripcion(lista.get(1));
                   evt.setFeha_inicio(Date.valueOf(lista.get(2)));
                   evt.setFecha_fin(Date.valueOf(lista.get(3)));
                   //evt.setRutaimagen(lista.get(4));
                    evtdao.agregar(evt);
                    
                }catch(Exception e){
                    
                }
                request.getRequestDispatcher("srvEventoImagen?accion=Listar").forward(request,response);
                break;
                case "Listar":
                    List<Eventos> evts= evtdao.Listar();
                    request.setAttribute("eventosmg",evts);
                    request.getRequestDispatcher("/Administracion/imagenesEvento1.jsp").forward(request,response);                       
                break;
            default:
                throw new AssertionError();
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
