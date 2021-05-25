/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.EmpleadoDAO;
import DAO.EstudianteDAO;
import DAO.Estudiante_ConvivenciaDAO;
import DAO.Estudiante_FamiliarDAO;
import DAO.Estudiante_ReferenciaDAO;
import DAO.FamiliarDAO;
import DAO.ReferenciaDAO;
import Modelo.Convivencia;
import Modelo.Empleado;
import Modelo.Referencia;
import Modelo.Estudiante;
import Modelo.Familiar;
import Modelo.Estudiante_Referencia;
import Modelo.Estudiante_Familiar;
import Modelo.Estudiante_Convivencia;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
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
 * @author Carlos
 */
@MultipartConfig
@WebServlet(name = "srvEstudiante", urlPatterns = {"/srvEstudiante"})
public class srvEstudiante extends HttpServlet {

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

            Familiar familiar;
            Familiar familiar2;
            FamiliarDAO familiarDAO;
            Estudiante estudiante;
            EstudianteDAO estudianteDAO;
            Referencia referencia;
            ReferenciaDAO referenciaDAO;
            Estudiante_Referencia estudiante_referencia;
            Estudiante_ReferenciaDAO estudiante_referenciaDAO;
            Estudiante_Familiar estudiantefPapa;
            Estudiante_Familiar estudiantefMama;
            Estudiante_FamiliarDAO estudiante_familiarDAO;
            Estudiante_Convivencia estudiante_convivencia;
            Estudiante_ConvivenciaDAO estudiante_convivenciaDAO;

            HttpSession session = request.getSession();
            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

            ArrayList<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
            ArrayList<Familiar> liatafamiliar = new ArrayList<Familiar>();
            ArrayList<Estudiante> EstudianteID = new ArrayList<Estudiante>();
            switch (accion) {
                case "registrar_estudiante":
                    try {

                        estudiante = new Estudiante();
                        estudianteDAO = new EstudianteDAO();
                        familiarDAO = new FamiliarDAO();
                        familiar = new Familiar();
                        familiar2 = new Familiar();
                        referencia = new Referencia();
                        //convivencia=new Convivencia();
                        referenciaDAO = new ReferenciaDAO();
                        estudiante_familiarDAO = new Estudiante_FamiliarDAO();
                        estudiante_referenciaDAO = new Estudiante_ReferenciaDAO();

                        estudiante.getCurso_educativo().setId_curso(Integer.parseInt(request.getParameter("sltRegCurso")));
                        estudiante.setIdentificacion(request.getParameter("txtRegIdentificacion"));
                        estudiante.setTipo_identificacion(request.getParameter("sltRegTipoIdentificacion"));
                        estudiante.setApellidos(request.getParameter("txtRegApellidos"));
                        estudiante.setNombres(request.getParameter("txtRegNombres"));
                        estudiante.setFecha_nacimiento(parseador.parse(request.getParameter("txtRegFecha")));
                        estudiante.setGenero(request.getParameter("sltRegGenero"));
                        estudiante.setDireccion(request.getParameter("txtRegDireccion"));
                        estudiante.setCarnet_discapacidad(request.getParameter("txtRegCarnet"));
                        estudiante.setDiscapacidad(request.getParameter("txtRegDiscapacidad"));
                        if (request.getParameter("sltRegTipoDiscp").equals("1")) {
                            estudiante.setTipo_discapacidad("NINGUNA");
                        }
                        if (request.getParameter("sltRegTipoDiscp").equals("2")) {
                            estudiante.setTipo_discapacidad("FISÍCA");
                        }
                        if (request.getParameter("sltRegTipoDiscp").equals("3")) {
                            estudiante.setTipo_discapacidad("MENTAL");
                        }
                        estudiante.setTipo_discapacidad(request.getParameter("sltRegTipoDiscp"));
                        estudiante.setHistoria_clinica(request.getParameter("txtRegHistoriaClinica"));
                        if (request.getParameter("txtRegNumHermanos").equals("")) {
                            estudiante.setNumero_hermanos(Types.NULL);
                            estudiante.setLugar_ocupa(Types.NULL);
                        } else {
                            estudiante.setNumero_hermanos(Integer.parseInt(request.getParameter("txtRegNumHermanos")));
                            estudiante.setLugar_ocupa(Integer.parseInt(request.getParameter("txtRegLugHermanos")));
                        }

                        estudiante.setNacionalidad(request.getParameter("txtRegNacionalidad"));
                        estudiante.setCorreo(request.getParameter("txtRegEmail"));
                        estudiante.setCelular(request.getParameter("txtRegCelular"));
                        //si el archivo de foto es vacio registrar solo datos de estudiantes sin fotos 
//                        Part SinFoto = request.getPart("txtRegFoto");
//                        //!"".equals(SinPadre)
//                        if (!"".equals(SinFoto)) {
//                            estudianteDAO.registrarEstudiante(estudiante);
//                        } else {
                        Part arch = request.getPart("txtRegFoto");
                        String n = arch.getSubmittedFileName();
                        if (!"".equals(n)) {
                            if (ValidarFichero(n)) {
                                int longuitud = n.length();
                                String ext = n.substring(longuitud - 4, longuitud);
                                InputStream is = arch.getInputStream();
                                String fileName = this.getServletContext().getRealPath("/Imagenes/Alumnos/");
                                // poner los cositos //
                                File f = new File(f_RutaModificada(fileName) + "" + nombrarImagenEmpleado(estudiante.getIdentificacion(), "Estudiante", ext));
                                String ruta = f.toString();
                                FileOutputStream ous = new FileOutputStream(f);
                                //Ruta para base de datos
                                String rutaBase = "Imagenes/Alumnos/" + nombrarImagenEmpleado(estudiante.getIdentificacion(), "Estudiante", ext) + "";
                                estudiante.setFoto(rutaBase);

                                Part archdomicilio = request.getPart("txtRegFotoDomicilio");
                                String nd = archdomicilio.getSubmittedFileName();
                                if (ValidarFichero(nd)) {
                                    int longuitudD = nd.length();
                                    String extD = nd.substring(longuitudD - 4, longuitudD);
                                    InputStream isD = archdomicilio.getInputStream();
                                    String fileNameDomi = this.getServletContext().getRealPath("/Imagenes/Alumnos/");
                                    File fd = new File(f_RutaModificada(fileNameDomi) + "" + nombrarImagenEmpleado(estudiante.getIdentificacion(), "Domicilio", ext));
                                    String rutad = fd.toString();
                                    FileOutputStream ousD = new FileOutputStream(fd);
                                    //Ruta para base de datos
                                    String rutaBaseDomicilio = "Imagenes/Alumnos/" + nombrarImagenEmpleado(estudiante.getIdentificacion(), "Domicilio", ext) + "";
                                    estudiante.setFoto_domicilio(rutaBaseDomicilio);

                                    if (estudianteDAO.registrarEstudiante(estudiante)) {
                                        int dato = is.read();
                                        while (dato != -1) {
                                            ous.write(dato);
                                            dato = is.read();
                                        }
                                        ous.close();
                                        is.close();

                                        int datoD = isD.read();
                                        while (datoD != -1) {
                                            ousD.write(datoD);
                                            datoD = isD.read();
                                        }
                                        ousD.close();
                                        isD.close();
                                    }
                                }

                            }
                        }
                        estudianteDAO.registrarEstudiante(estudiante);

                        //registrar padre
                        String SinPadre = request.getParameter("txtRegFamiliarIdentificacionPadre");
                        if (!"".equals(SinPadre)) {
                            familiar.setTipo_identificacion(request.getParameter("txtTipoIdePadre"));
                            familiar.setIdentificacion(request.getParameter("txtRegFamiliarIdentificacionPadre"));
                            familiar.setNombres(request.getParameter("txtRegNombresPadre"));
                            familiar.setApellidos(request.getParameter("txtRegApellidosPadre"));
                            familiar.setCue(request.getParameter("txtRegCuePadre"));
                            familiar.setCelular(request.getParameter("txtRegCelularPadre"));
                            familiar.setOcupacion(request.getParameter("txtRegFamiliarOcupacionPadre"));
                            familiar.setLugar_trabajo(request.getParameter("txtRegFamiliarLugarPadre"));
                            familiar.setCorreo(request.getParameter("txtRegEmailPadre"));
                            familiar.setNacionalidad(request.getParameter("txtRegNacionalidadPadre"));
                            familiarDAO.registrarFamiliar(familiar);

                        }
                        String SinMadre = request.getParameter("txtRegFamiliarIdentificacionMadre");
                        if (!"".equals(SinMadre)) {
                            //Registrar madre
                            familiar2.setTipo_identificacion(request.getParameter("sltRegFamiliarTipoIdentificacionMadre"));
                            familiar2.setIdentificacion(request.getParameter("txtRegFamiliarIdentificacionMadre"));
                            familiar2.setNombres(request.getParameter("txtRegNombresMadre"));
                            familiar2.setApellidos(request.getParameter("txtRegApellidosMadre"));
                            familiar2.setCue(request.getParameter("txtRegCueMadre"));
                            familiar2.setCelular(request.getParameter("txtRegCelularPadre"));
                            familiar2.setOcupacion(request.getParameter("txtRegFamiliarOcupacionMadre"));
                            familiar2.setLugar_trabajo(request.getParameter("txtRegFamiliarLugarMadre"));
                            familiar2.setCorreo(request.getParameter("txtRegEmailMadre"));
                            familiar2.setNacionalidad(request.getParameter("txtRegNacionalidadMadre"));
                            familiarDAO.registrarFamiliar(familiar2);

                        }
                        String SinReferencia = request.getParameter("txtRegCedulaRef");
                        if (!"".equals(SinReferencia)) {
                            referencia.setIdentificacion(request.getParameter("txtRegCedulaRef"));
                            referencia.setTipo_identificacion(request.getParameter("sltRegTipoIdentificacionRef"));
                            referencia.setNombres(request.getParameter("txtRegNombresRef"));
                            referencia.setApellidos(request.getParameter("txtRegApellidosRef"));
                            referencia.setCelular(request.getParameter("txtRegCelularRef"));
                            referencia.setTelefono(request.getParameter("txtRegtelefonoRef"));
                            referencia.setParentesco(request.getParameter("txtRegParentescoRef"));
                            referenciaDAO.registrarReferencia(referencia);
                        }

                        //llamar funcion para obtener id estudiante por medio de la identificacion (cedula)
                        estudianteDAO = new EstudianteDAO();
                        estudiantefPapa = new Estudiante_Familiar();
                        estudiantefMama = new Estudiante_Familiar();
                        estudiante_referencia = new Estudiante_Referencia();
                        estudiante_convivencia = new Estudiante_Convivencia();
                        estudiante_convivenciaDAO = new Estudiante_ConvivenciaDAO();

                        int idest = estudianteDAO.obtenerIdEstudiante(estudiante);
                        int idfa = familiarDAO.obtenerIdFamiliar(familiar);
                        int idfa2 = familiarDAO.obtenerIdFamiliar(familiar2);
                        int idref = referenciaDAO.obtenerIdReferencia(referencia);
//
                        //insertar tablas intermedias relacion de padres 
                        if (idest > 0 && idfa > 0) {
                            estudiantefPapa.setParentesco("PADRE");
                            estudiantefPapa.setIdFamiliar(idfa);
                            estudiantefPapa.setIdestudiante(idest);
                            estudiante_familiarDAO.registrarEstudianteFamiliar(estudiantefPapa);
                        }

                        if (idest > 0 && idfa2 > 0) {
                            estudiantefMama.setParentesco("MADRE");
                            estudiantefMama.setIdFamiliar(idfa2);
                            estudiantefMama.setIdestudiante(idest);
                            estudiante_familiarDAO.registrarEstudianteFamiliar(estudiantefMama);
                        }
                        //relacion de referencia 
                        if (idref > 0 && idest > 0) {
                            estudiante_referencia.getEstudiante().setId_estudiante(idest);
                            estudiante_referencia.getReferencia().setId_referencia(idref);
                            estudiante_referencia.setParentesco(request.getParameter("txtRegParentescoRef"));
                            estudiante_referenciaDAO.registrarEstudianteReferencia(estudiante_referencia);
                        }
                        //relacion de convivencia 
                        String[] results = request.getParameterValues("listaFamiliaresAlu");
                        if (results != null) {
                            if (results.length > 0) {
                                for (int i = 0; i < results.length; i++) {
                                    int idconv = Integer.parseInt(results[i]);
                                    estudiante_convivencia.getEstudiante().setId_estudiante(idest);
                                    estudiante_convivencia.getConvivencia().setId_convivencia(idconv);
                                    estudiante_convivenciaDAO.registrarEstudianteFamiliar(estudiante_convivencia);

                                }
                            }
                        }

                        response.getWriter().write("ok");
                    } catch (IOException | NumberFormatException | SQLException | ParseException | ServletException ex) {
                        System.out.println(ex.getMessage());
                        response.getWriter().write("ERROR ");

                    }
                    break;
                case "mostrar_estudiante":
                    try {
                        // estudiante = new Estudiante();
                        estudianteDAO = new EstudianteDAO();

                        listaEstudiantes = estudianteDAO.mostrarEstudiantes();
                        //liatafamiliar=familiarDAO.mostrarFamiliar();

                        request.setAttribute("listaUsuarios", listaEstudiantes);

                        request.getRequestDispatcher("/Administracion/mostrarEstudiantes.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "mostrar_estudiante_filtro_persona":
                    try {
                        estudiante = new Estudiante();
                        estudianteDAO = new EstudianteDAO();

                        estudiante.setId_estudiante(Integer.parseInt(request.getParameter("identificacion")));
                        //listaEstudiantes = estudianteDAO.mostrarEstudiantesFiltroIdentificacion(estudiante);

                        out.print(new Gson().toJson(listaEstudiantes));

                        //request.setAttribute("listaCantones", listaCantones);
                        //request.getRequestDispatcher("/Administracion/mostrarUsuarios.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "modificar_estudiante":
                    try {
                        estudiante = new Estudiante();
                        estudianteDAO = new EstudianteDAO();

                        estudiante.setId_estudiante(Integer.parseInt(request.getParameter("id_estudiante")));
                        estudiante.getCurso_educativo().setId_curso(Integer.parseInt(request.getParameter("id_curso")));
                        estudiante.setIdentificacion(request.getParameter("identificacion"));
                        estudiante.setTipo_identificacion(request.getParameter("tipo_identificacion"));
                        estudiante.setApellidos(request.getParameter("apellidos"));
                        estudiante.setNombres(request.getParameter("nombres"));
                        estudiante.setFecha_nacimiento(parseador.parse(request.getParameter("fecha_nacimiento")));
                        estudiante.setGenero(request.getParameter("genero"));
                        estudiante.setFoto(request.getParameter("foto"));
                        estudiante.setDireccion(request.getParameter("direccion"));
                        estudiante.setFoto_domicilio(request.getParameter("foto_domicilio"));
                        estudiante.setCarnet_discapacidad(request.getParameter("carnet_discapacidad"));
                        estudiante.setDiscapacidad(request.getParameter("discapacidad"));
                        estudiante.setTipo_discapacidad(request.getParameter("tipo_discapacidad"));
                        estudiante.setHistoria_clinica(request.getParameter("historia_clinica"));
                        estudiante.setNumero_hermanos(Integer.parseInt(request.getParameter("numero_hermanos")));
                        estudiante.setLugar_ocupa(Integer.parseInt(request.getParameter("lugar_ocupa")));
                        estudiante.setNacionalidad(request.getParameter("nacionalidad"));

                        if (estudianteDAO.modificarEstudiante(estudiante)) {
                            response.getWriter().write("ok");
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "eliminar_estudiante":
                    try {
                        estudiante = new Estudiante();
                        estudianteDAO = new EstudianteDAO();

                        estudiante.setId_estudiante(Integer.parseInt(request.getParameter("id_estudiante")));

                        if (estudianteDAO.eliminarEstudiante(estudiante)) {
                            response.getWriter().write("ok");
                            response.sendRedirect("srvEstudiante?accion=mostrar_estudiante");
                        } else {
                            response.getWriter().write("error");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "mostrar_estudiante_identificacion":
                    try {
                        estudiante = new Estudiante();
                        estudiante.setIdentificacion(request.getParameter("Identificacion"));
                        request.setAttribute("cedula", estudiante.getIdentificacion());

                        request.getRequestDispatcher("/Administracion/FormularioModificarEstudiante.jsp").forward(request, response);
                    } catch (Exception e) {
                    }
            }
        }
    }

    private String f_RutaModificada(String ruta) {
//sumar 10 y 4 o restar
        int longuitud = ruta.length();
//        String entrada = ruta.substring(0, longuitud - 28);
//        String rutaConcat = ruta.substring(longuitud - 22, longuitud);
        String entrada = ruta.substring(0, longuitud - 18);
        String rutaConcat = ruta.substring(longuitud - 18, longuitud);
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
