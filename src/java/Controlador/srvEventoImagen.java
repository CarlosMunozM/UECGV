package Controlador;

import DAO.EventosDAO;
import Modelo.Eventos;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

/**
 *
 * @author ASUS
 */
@MultipartConfig
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
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            request.setCharacterEncoding("UTF-8");
            /* TODO output your page here. You may use following sample code. */
            String accion = request.getParameter("accion");
            HttpSession session = request.getSession();
            Eventos evt;
            EventosDAO evtdao;
            Gson gson;
            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
            if(request.getParameter("txtid_evento") != null && !request.getParameter("txtid_evento").equals("")){ accion = "Modificar"; }
            switch (accion) {
                case "Guardar":
                    boolean bandera = false;
                    ArrayList<String> lista = new ArrayList<>();
                    try {
                        //datos del dialog
                        evt = new Eventos();
                        evtdao = new EventosDAO();
                        evt.setId_usuario(Integer.parseInt(request.getSession().getAttribute("id_usuario").toString()));

                        evt.setNombre_evento(request.getParameter("txtnombre_evento").trim());
                        evt.setDescripcion(request.getParameter("txtdescripcion").trim());
                        evt.setFeha_inicio(Date.valueOf(request.getParameter("txtfecha_inicio")));
                        //evt.setFeha_inicio((Date) parseador.parse(request.getParameter("txtfecha_inicio").trim()));
                        evt.setFecha_fin(Date.valueOf(request.getParameter("txtfecha_fin")));
                        //evt.setFecha_fin((Date) parseador.parse(request.getParameter("txtfecha_fin").trim()));
                        //Part arch= (Part) fileItem;
                        Part arch = request.getPart("RegevtFoto");
                        String n = arch.getSubmittedFileName();
                        if (ValidarFichero(n)) {
                            int longuitud = n.length();
                            String name = String.valueOf(System.currentTimeMillis());
                            String ext = n.substring(longuitud - 4, longuitud);
                            InputStream is = arch.getInputStream();
                            String fileName = this.getServletContext().getRealPath("/Imagenes/Eventos/");
                            File f = new File(f_RutaModificada(fileName) + "" + nombrarImagenEmpleado(evt.getNombre_evento(), name, ext));
                            String ruta = f.toString();
                            FileOutputStream ous = new FileOutputStream(f);
                            //Ruta para base de datos
                            String rutaBase = "Imagenes/Eventos/" + nombrarImagenEmpleado(evt.getNombre_evento(), name, ext) + "";
                            evt.setRutaimagen(rutaBase);

                            //evt.setRutaimagen(lista.get(4));
                            if(evtdao.agregar(evt)){
                                int dato = is.read();
                                while (dato != -1) {
                                    ous.write(dato);
                                    dato = is.read();
                                }    
                                bandera = true;
                            }else{
                                bandera = false;
                            }   
                            ous.close();
                            is.close();
                        }

                    } catch (NumberFormatException | IOException | ServletException | SQLException e) {
                        bandera = false;
                        throw e;
                    }
                    out.println(bandera);
                    //request.getRequestDispatcher("srvEventoImagen?accion=Listar").forward(request, response);
                    break;
                case "Modificar":
                    bandera = false;
                    lista = new ArrayList<>();
                    Part arch = request.getPart("RegevtFoto");
                    String n = arch.getSubmittedFileName();
                    if (!n.equals("")) {
                        try {
                            //datos del dialog
                            evt = new Eventos();
                            evtdao = new EventosDAO();
                            evt.setId_evento(Integer.parseInt(request.getParameter("txtid_evento")));
                            evt.setId_usuario(Integer.parseInt(request.getSession().getAttribute("id_usuario").toString()));
                            evt.setNombre_evento(request.getParameter("txtnombre_evento").trim());
                            evt.setDescripcion(request.getParameter("txtdescripcion").trim());
                            evt.setFeha_inicio(Date.valueOf(request.getParameter("txtfecha_inicio")));
                            evt.setFecha_fin(Date.valueOf(request.getParameter("txtfecha_fin")));
                            if (ValidarFichero(n)) {
                                int longuitud = n.length();
                                String name = String.valueOf(System.currentTimeMillis());
                                String ext = n.substring(longuitud - 4, longuitud);
                                InputStream is = arch.getInputStream();
                                String fileName = this.getServletContext().getRealPath("/Imagenes/Eventos/");
                                //File f = new File(f_RutaModificada(fileName) + "\\" + nombrarImagenEmpleado(evt.getNombre_evento(), name, ext));
                                File f = new File(f_RutaModificada(fileName) + "" + nombrarImagenEmpleado(evt.getNombre_evento(), name, ext));
                                String ruta = f.toString();
                                FileOutputStream ous = new FileOutputStream(f);
                                //Ruta para base de datos
                                String rutaBase = "Imagenes/Eventos/" + nombrarImagenEmpleado(evt.getNombre_evento(), name, ext) + "";
                                evt.setRutaimagen(rutaBase);

                                //evt.setRutaimagen(lista.get(4));
                                if (evtdao.modificar(evt)) {
                                    int dato = is.read();
                                    while (dato != -1) {
                                        ous.write(dato);
                                        dato = is.read();
                                    }
                                    bandera = true;
                                } else {
                                    bandera = false;
                                }
                                ous.close();
                                is.close();
                            }
                        } catch (NumberFormatException | IOException | SQLException e) {
                            bandera = false;
                            throw e;
                        }
                    }else{
                        evt = new Eventos();
                        evtdao = new EventosDAO();
                        evt.setId_evento(Integer.parseInt(request.getParameter("txtid_evento")));
                        evt.setId_usuario(Integer.parseInt(request.getSession().getAttribute("id_usuario").toString()));
                        evt.setNombre_evento(request.getParameter("txtnombre_evento").trim());
                        evt.setDescripcion(request.getParameter("txtdescripcion").trim());
                        evt.setFeha_inicio(Date.valueOf(request.getParameter("txtfecha_inicio")));
                        evt.setFecha_fin(Date.valueOf(request.getParameter("txtfecha_fin")));
                        if(evtdao.modificarNoimagen(evt)){
                            bandera = true;
                        }else{
                            bandera = false;
                        }   
                    }
                    out.println(bandera);  
                    break;
                case "Listar":
                    evt = new Eventos();
                    evtdao = new EventosDAO();
                    List<Eventos> evts = evtdao.Listar();
                    request.setAttribute("eventosmg", evts);
                    request.getRequestDispatcher("/Administracion/imagenesEvento1.jsp").forward(request, response);
                    break;
                case "Eliminar":
                    try {
                        evt = new Eventos();
                        evtdao = new EventosDAO();

                        evt.setId_evento(Integer.parseInt(request.getParameter("id")));

                        evtdao.eliminarEventosImagen(evt);

                        List<Eventos> img3 = evtdao.Listar();
                        request.setAttribute("imagenes", img3);
                        response.sendRedirect("srvEventoImagen?accion=Listar");
                        //request.getRequestDispatcher("/Administracion/imagenesSlider1.jsp").forward(request, response);

                    } catch (Exception e) {
                    }
                    break;
                case "Listar_Anio":
                    response.setContentType("application/json;charset=UTF-8");
                    request.setCharacterEncoding("UTF-8");
                    gson = new Gson();
                    evtdao = new EventosDAO();
                    ArrayList<String> lista_anio = new ArrayList<>();
                    lista_anio = evtdao.ListarAnios();
                    String jsonAnio = gson.toJson(lista_anio);
                    out.print(jsonAnio);
                    break;
                case "Listar_Eventos":
                    response.setContentType("application/json;charset=UTF-8");
                    request.setCharacterEncoding("UTF-8");
                    gson = new Gson();
                    evtdao = new EventosDAO();
                    ArrayList<Eventos> lista_evento = new ArrayList<>();
                    String anio = request.getParameter("anio");
                    lista_evento = evtdao.ListarEventosAnio(anio);
                    String jsonEvento = gson.toJson(lista_evento);
                    out.print(jsonEvento);
                    break;
                case "Eventos_Recientes":
                    response.setContentType("application/json;charset=UTF-8");
                    request.setCharacterEncoding("UTF-8");
                    gson = new Gson();
                    evtdao = new EventosDAO();
                    lista_evento = new ArrayList<>();
                    lista_evento = evtdao.EventosRecientes();
                    jsonEvento = gson.toJson(lista_evento);
                    out.print(jsonEvento);
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private String f_RutaModificada(String ruta) {

        int longuitud = ruta.length();
//        String entrada = ruta.substring(0, longuitud - 27);
//        String rutaConcat = ruta.substring(longuitud - 21, longuitud);
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
        String NombreImagen = "ImagenEmpleado" + codigo;
        return NombreImagen;
    }

    private boolean ValidarFichero(String nombre) {
        int longuitud = nombre.length();
        String formato = nombre.substring(longuitud - 4, longuitud);
        if (formato.equals(".jpg") || formato.equals("jpg") || formato.equals(".PNG") || formato.equals(".png") || formato.equals("png") || 
                formato.equals("gif") || formato.equals(".gif")) {
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
            Logger.getLogger(srvEventoImagen.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(srvEventoImagen.class.getName()).log(Level.SEVERE, null, ex);
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
