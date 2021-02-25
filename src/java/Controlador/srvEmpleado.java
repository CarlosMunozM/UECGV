/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.CantonDAO;
import DAO.CargoDAO;
import DAO.Cargo_EmpleadoDAO;
import DAO.Curso_EducativoDAO;
import DAO.EmpleadoDAO;
import DAO.TutoresDAO;
import Modelo.Canton;
import Modelo.Cargo;
import Modelo.Cargo_Empleado;
import Modelo.Curso_Educativo;
import Modelo.Empleado;
import Modelo.Tutores;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
@WebServlet(name = "srvEmpleado", urlPatterns = {"/srvEmpleado"})
public class srvEmpleado extends HttpServlet {

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
            //String idemp = request.getParameter("id");

            Empleado empleado;
            EmpleadoDAO empleadoDAO;
            CantonDAO cantonDAO;
            CargoDAO cargoDAO;
            Cargo_Empleado cargoEmpleado;
            Cargo_EmpleadoDAO cargoEmpleadoDAO;
            Tutores tutores;
            TutoresDAO tutoresDAO;
            Curso_EducativoDAO cursoEducativoDAO;
            HttpSession session = request.getSession();
            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
            ArrayList<Canton> listaCantones = new ArrayList<Canton>();
            ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
            ArrayList<Cargo> listaCargo = new ArrayList<Cargo>();
            ArrayList<Cargo_Empleado> listaCargoEmpleado = new ArrayList<Cargo_Empleado>();
            ArrayList<Curso_Educativo> listaCursoEducativo= new ArrayList<Curso_Educativo>();

            switch (accion) {

                case "mostrar_formRegistro":
                    try {                                                                      
                        cantonDAO = new CantonDAO();
                        cargoDAO = new CargoDAO();
                        listaCantones = cantonDAO.mostrarCantones();
                        listaCargo =cargoDAO.mostrarCargos();
                        cursoEducativoDAO = new Curso_EducativoDAO();
                        listaCursoEducativo = cursoEducativoDAO.mostrarCursoEducativo();

                        request.setAttribute("listaCargos", listaCargo);
                        request.setAttribute("listaCantones", listaCantones);
                        request.setAttribute("listaCursoEducativo", listaCursoEducativo);
                        
                        request.getRequestDispatcher("/Administracion/registrarEmpleados.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "registrar_empleado":
                    try {
                        empleado = new Empleado();
                        empleadoDAO = new EmpleadoDAO();

                        empleado.getCanton().setId_canton(Integer.parseInt(request.getParameter("RegEmpIdCanton").trim()));
                        empleado.setIdentificacion(request.getParameter("RegEmpIdentificacion").trim());
                        empleado.setTipo_identificacion(request.getParameter("RegEmpTipoIdentificacion").trim());
                        empleado.setApellidos(request.getParameter("RegEmpApellidos").toUpperCase().trim());
                        empleado.setNombres(request.getParameter("RegEmpNombres").toUpperCase().trim());
                        empleado.setEstado_civil(request.getParameter("RegEmpEstadoCivil").trim());
                        //empleado.getCanton().setId_canton(Integer.parseInt(request.getParameter("RegEmpIdCanton")));
                        empleado.setDireccion(request.getParameter("RegEmpDireccion").trim());
                        empleado.setTelefono(request.getParameter("RegEmpTelefono").trim());
                        empleado.setCorreo_personal(request.getParameter("RegEmpCorreoPersonal").toLowerCase().trim());
                        empleado.setContrato(request.getParameter("RegEmpContrato").trim());
                        empleado.setEscalafon(request.getParameter("RegEmpEscalafon").trim());
                        empleado.setGenero(request.getParameter("RegEmpGenero").trim());
                        empleado.setNacionalidad(request.getParameter("RegEmpNacionalidad").trim());
                        empleado.setFecha_nacimiento(parseador.parse(request.getParameter("RegEmpFechaNacimiento").trim()));
                        empleado.setCelular(request.getParameter("RegEmpCelular").trim());
                        empleado.setCorreo_institucional(request.getParameter("RegEmpCorreoInstitucional").toLowerCase().trim());
                        
                        Part arch = request.getPart("RegEmpFoto");
                        String n = arch.getSubmittedFileName();
                        if (ValidarFichero(n)) {
                            int longuitud = n.length();
                            String ext = n.substring(longuitud - 4, longuitud);
                            InputStream is = arch.getInputStream();
                            String fileName = this.getServletContext().getRealPath("/Imagenes/Empleados/");
                            File f = new File(f_RutaModificada(fileName) + "\\" + nombrarImagenEmpleado(empleado.getIdentificacion(),"Empleado", ext));
                            String ruta = f.toString();
                            FileOutputStream ous = new FileOutputStream(f);
                            //Ruta para base de datos
                            String rutaBase = "Imagenes/Empleados/" + nombrarImagenEmpleado(empleado.getIdentificacion(),"Empleado", ext) + "";
                            empleado.setFoto(rutaBase);
                            if (empleadoDAO.registrarEmpleado(empleado)) {                               
                                int dato = is.read();
                                while (dato != -1) {                               
                                    ous.write(dato);
                                    dato = is.read();
                                }
                                ous.close();
                                is.close();
                                cargoEmpleado = new Cargo_Empleado();                            
                                cargoEmpleadoDAO = new Cargo_EmpleadoDAO();                            
                                int idEmp = empleadoDAO.obtenerIdEmpleado(empleado);                            
                                if(idEmp>0){                               
                                    cargoEmpleado.getEmpleado().setId_empleado(idEmp);                                
                                    cargoEmpleado.getCargo().setId_cargo(Integer.parseInt(request.getParameter("RegEmpIdCargo")));                                                               
                                    if(cargoEmpleadoDAO.registrarCargoEmpleado(cargoEmpleado)){
                                        if(cargoEmpleado.getCargo().getId_cargo() == 5){
                                            String notutor=request.getParameter("RegEmpIdCurso");
                                            if(notutor != ""){                                           
                                                String idCargEmpAct = request.getParameter("RegIDCargoEmpleado");                                           
                                                tutores = new Tutores();                                               
                                                tutoresDAO = new TutoresDAO();
                                                if(idCargEmpAct.equals("")){                                                                                                                                              
                                                    int idcar= cargoEmpleadoDAO.obtenerIdCargoEmpleado(idEmp);
                                                    tutores.setParalelo(request.getParameter("RegEmpParalelo"));                                               
                                                    tutores.getCurso_educativo().setId_curso(Integer.parseInt(notutor));                                                                                     
                                                    if(tutoresDAO.registrarTutor(tutores, idcar)){                                                                                                
                                                        session.setAttribute("registrar", "ok");                                                                                                                                     
                                                        response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                                                                
                                                    }else{                                                                                                   
                                                        session.setAttribute("registrar", "error");                                                                                                                                     
                                                        response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                               
                                                    }                                            
                                                }else{
                                                    if(tutoresDAO.eliminarTutor(Integer.parseInt(idCargEmpAct))){
                                                        int idcar= cargoEmpleadoDAO.obtenerIdCargoEmpleado(idEmp);
                                                        tutores.setParalelo(request.getParameter("RegEmpParalelo"));                                               
                                                        tutores.getCurso_educativo().setId_curso(Integer.parseInt(notutor));
                                                        if(tutoresDAO.registrarTutor(tutores, idcar)){                                                                                                                                                       
                                                            session.setAttribute("registrar", "ok");                                                                                                                                                                                            
                                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                                                                                                                   
                                                        }else{                                                                                                                                                          
                                                            session.setAttribute("registrar", "error");                                                                                                                                                                                            
                                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                                                                   
                                                        }
                                                    }else{
                                                        session.setAttribute("registrar", "error");                                                                                                                                     
                                                        response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                                    }
                                                }
                                            }else{
                                                session.setAttribute("registrar", "ok");                                    
                                                response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                            }
                                        }else{
                                            session.setAttribute("registrar", "ok");                                    
                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                        }                                                                        
                                    }else{
                                    session.setAttribute("registrar", "error");
                                    response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                
                                    }                            
                                }else{                                
                                    session.setAttribute("registrar", "error");                                
                                    response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                         
                                }                       
                            } else {                           
                                session.setAttribute("registrar", "error");                           
                                response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                      
                            }
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "mostrar_empleados":
                    try {
                        empleado = new Empleado();                       
                        empleadoDAO = new EmpleadoDAO();
                        cantonDAO = new CantonDAO();
                        cargoDAO = new CargoDAO();
                        cursoEducativoDAO = new Curso_EducativoDAO();
                        listaCursoEducativo = cursoEducativoDAO.mostrarCursoEducativo();

                        listaCantones = cantonDAO.mostrarCantones();
                        listaCargo =cargoDAO.mostrarCargos();
                        listaEmpleados = empleadoDAO.mostrarEmpleados();
                                                
                        request.setAttribute("listaCargos", listaCargo);
                        request.setAttribute("listaCantones", listaCantones);
                        request.setAttribute("listaUsuarios", listaEmpleados);
                        request.setAttribute("listaCursoEducativo", listaCursoEducativo);
                        request.getRequestDispatcher("/Administracion/mostrarEmpleados.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "mostrar_empleados_filtro_persona":
                    try {
                        empleado = new Empleado();
                        empleadoDAO = new EmpleadoDAO();

                        empleado.setId_empleado(Integer.parseInt(request.getParameter("id_empleado")));

                        listaEmpleados = empleadoDAO.mostrarEmpleadosFiltroPersona(empleado);

                        out.print(new Gson().toJson(listaEmpleados));

                        //request.setAttribute("listaCantones", listaCantones);
                        //request.getRequestDispatcher("/Administracion/mostrarUsuarios.jsp").forward(request, response);
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "modificar_empleado":
                    try {
                        empleado = new Empleado();
                        empleadoDAO = new EmpleadoDAO();
                        cargoEmpleadoDAO = new Cargo_EmpleadoDAO();
                        cargoEmpleado= new Cargo_Empleado();
                        
                        
                        empleado.setId_empleado(Integer.parseInt(request.getParameter("EditEmpIdEmpleado").trim()));
                        empleado.getCanton().setId_canton(Integer.parseInt(request.getParameter("EditEmpIdCanton").trim()));
                        empleado.setIdentificacion(request.getParameter("EditEmpIdentificacion").trim());
                        empleado.setTipo_identificacion(request.getParameter("EditEmpTipoIdentificacion").trim());
                        empleado.setApellidos(request.getParameter("EditEmpApellidos").toUpperCase().trim());
                        empleado.setNombres(request.getParameter("EditEmpNombres").toUpperCase().trim());
                        empleado.setEstado_civil(request.getParameter("EditEmpEstadoCivil").trim());
                        empleado.setGenero(request.getParameter("EditEmpGenero").trim());
                        empleado.setDireccion(request.getParameter("EditEmpDireccion").trim());
                        empleado.setTelefono(request.getParameter("EditEmpTelefono").trim());
                        empleado.setCorreo_personal(request.getParameter("EditEmpEmail").toLowerCase().trim());
                        empleado.setContrato(request.getParameter("EditEmpContrato").trim());
                        empleado.setEscalafon(request.getParameter("EditEmpEscalafon").trim());
                        
                        empleado.setNacionalidad(request.getParameter("EditEmpNacionalidad").trim());
                        empleado.setFecha_nacimiento(parseador.parse(request.getParameter("EditEmpFechaNacimiento").trim()));
                        empleado.setCelular(request.getParameter("EditEmpCelular").trim());
                        empleado.setCorreo_institucional(request.getParameter("EditEmpEmailInstitucional").toLowerCase().trim());
                        
                        Part arch = request.getPart("EditEmpFoto");
                        String n = arch.getSubmittedFileName();
                        if(n == null || n == ""){                          
                            empleado.setFoto(request.getParameter("EditEmpAuxFoto"));  
                        }else{
                            int longuitud = n.length();
                            String ext = n.substring(longuitud - 4, longuitud);
                            InputStream is = arch.getInputStream();
                            String fileName = this.getServletContext().getRealPath("/Imagenes/Empleados/");
                            File f = new File(f_RutaModificada(fileName) + "\\" + nombrarImagenEmpleado(empleado.getIdentificacion(),"Empleado", ext));
                            String ruta = f.toString();
                            FileOutputStream ous = new FileOutputStream(f);
                            //Ruta para base de datos
                            String rutaBase = "Imagenes/Empleados/" + nombrarImagenEmpleado(empleado.getIdentificacion(),"Empleado", ext) + "";
                            empleado.setFoto(rutaBase); 
                            int dato = is.read();
                                while (dato != -1) {                               
                                    ous.write(dato);
                                    dato = is.read();
                                }
                                ous.close();
                                is.close();
                        }                      
                        if (empleadoDAO.modificarEmpleado(empleado)) {
                        listaCargoEmpleado = cargoEmpleadoDAO.mostrarCargosEmpleadosFiltroPersona(empleado);    
                            if(listaCargoEmpleado.size()>0){
                                String cargo_actual=request.getParameter("EditEmpIdCargo");
                               if(listaCargoEmpleado.get(0).getCargo().getId_cargo() == Integer.parseInt(cargo_actual)){  //comparar si cambia de cargo
                                   if(cargo_actual.equals("5")){
                                       String notutoria = request.getParameter("EditEmpIdCurso");
                                       if(!"".equals(notutoria)){
                                           tutores = new Tutores();
                                           tutoresDAO = new TutoresDAO();
                                           String txtidCargEmpl= request.getParameter("EditIDCargoEmpleado");                                      
                                           if(txtidCargEmpl.equals("")){                                               
                                               int idcar= cargoEmpleadoDAO.obtenerIdCargoEmpleado(Integer.parseInt(request.getParameter("EditEmpIdEmpleado").trim()));
                                               
                                               tutores.setParalelo(request.getParameter("EditEmpParalelo"));                                               
                                               tutores.getCurso_educativo().setId_curso(Integer.parseInt(notutoria));
                                               if(tutoresDAO.registrarTutor(tutores, idcar)){                                                                                                                                                        
                                                   session.setAttribute("actualizar", "ok");                                                                                                                                     
                                                   response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                                                                
                                               }else{                                                                                                   
                                                   session.setAttribute("actualizar", "error");                                                                                                                                     
                                                   response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                               
                                               }                                              
                                           }else{
                                               if(tutoresDAO.eliminarTutor(Integer.parseInt(txtidCargEmpl))){                                                       
                                                   int idcar= cargoEmpleadoDAO.obtenerIdCargoEmpleado(Integer.parseInt(request.getParameter("EditEmpIdEmpleado").trim()));
                                                   
                                                       tutores.setParalelo(request.getParameter("EditEmpParalelo"));                                               
                                                       tutores.getCurso_educativo().setId_curso(Integer.parseInt(notutoria));
                                                       
                                                       if(tutoresDAO.registrarTutor(tutores, idcar)){                                                                                                                                                                                                               
                                                           session.setAttribute("actualizar", "ok");                                                                                                                                                                                            
                                                           response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                                                                                                                   
                                                       }else{                                                                                                                                                                                                                      
                                                           session.setAttribute("actualizar", "error");                                                                                                                                                                                            
                                                           response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                                                                   
                                                       }
                                                   
                                               }else{                                                       
                                                   session.setAttribute("actualizar", "error");                                                                                                                                     
                                                   response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                               }
                                           }
                                       }else{
                                           tutoresDAO = new TutoresDAO();
                                           int obtenr=tutoresDAO.mostrarTutoresFiltroPersona(Integer.parseInt(request.getParameter("EditEmpIdEmpleado").trim()));
                                           int idCargoEmp2 = listaCargoEmpleado.get(0).getId_cargoEmpleado();
                                           if(obtenr > 0){
                                               if(tutoresDAO.eliminarTutor(idCargoEmp2)){
                                                  session.setAttribute("actualizar", "ok"); 
                                                  response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id="); 
                                               }else{
                                                   session.setAttribute("actualizar", "error"); 
                                                  response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                               }    
                                           }else{
                                               session.setAttribute("actualizar", "ok"); 
                                               response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                           }
                                       }
                                        
                                   }else{
                                       session.setAttribute("actualizar", "ok"); 
                                       response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id="); 
                                   }
                                  
                               }else{
                                   int idCargoNuevo = Integer.parseInt(cargo_actual);
                                   int idCargoEmpleado = listaCargoEmpleado.get(0).getId_cargoEmpleado();
                                   cargoEmpleado.setId_cargoEmpleado(listaCargoEmpleado.get(0).getId_cargoEmpleado());
                                   cargoEmpleado.setEstado("F");
                                   cargoEmpleado.getEmpleado().setId_empleado(empleado.getId_empleado());
                                   cargoEmpleado.getCargo().setId_cargo(Integer.parseInt(cargo_actual));
                                   if(cargoEmpleadoDAO.modificarCargoEmpleado(cargoEmpleado)){                                     
                                       if(cargoEmpleadoDAO.registrarCargoEmpleado(cargoEmpleado)){                                          
                                            if(idCargoNuevo == 5){                                      
                                                String notutoria = request.getParameter("EditEmpIdCurso");                                       
                                                if(!"".equals(notutoria)){                                           
                                                    tutores = new Tutores();
                                                    tutoresDAO = new TutoresDAO();
                                                    String txtidCargEmpl= request.getParameter("EditIDCargoEmpleado");                                      
                                                    if(txtidCargEmpl.equals("")){                                                                                              
                                                        int idcar= cargoEmpleadoDAO.obtenerIdCargoEmpleado(Integer.parseInt(request.getParameter("EditEmpIdEmpleado").trim()));
                                                        
                                                        //int removertutoria = tutoresDAO.mostrarTutoresFiltroPersona(Integer.parseInt(request.getParameter("EditEmpIdEmpleado").trim()));
                                                        
                                                        tutores.setParalelo(request.getParameter("EditEmpParalelo"));                                               
                                                        tutores.getCurso_educativo().setId_curso(Integer.parseInt(notutoria));
                                                        if(tutoresDAO.registrarTutor(tutores, idcar)){                                                                                                                           
                                                            session.setAttribute("actualizar", "ok");                                                                                                                                     
                                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                                                                
                                                        }else{                                                                                                                                                      
                                                            session.setAttribute("actualizar", "error");                                                                                                                                     
                                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                               
                                                        }                                              
                                                    }else{                                             
                                                        if(tutoresDAO.eliminarTutor(Integer.parseInt(txtidCargEmpl))){                                                                                                               
                                                            int idcar= cargoEmpleadoDAO.obtenerIdCargoEmpleado(Integer.parseInt(request.getParameter("EditEmpIdEmpleado").trim()));
                                                            tutores.setParalelo(request.getParameter("EditEmpParalelo"));                                               
                                                            tutores.getCurso_educativo().setId_curso(Integer.parseInt(notutoria));                                          
                                                            if(tutoresDAO.registrarTutor(tutores, idcar)){                                                                                                                                                   
                                                                session.setAttribute("actualizar", "ok");                                                                                                                                                                                            
                                                                response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                                                                                                                   
                                                            }else{                                                                                                                                                                                                                                                                             
                                                                session.setAttribute("actualizar", "error");                                                                                                                                                                                            
                                                                response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");                                                                                                   
                                                            }
                                                        }else{                                                                                                         
                                                            session.setAttribute("actualizar", "error");                                                                                                                                     
                                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                                        }
                                                    }
                                                }else{                                          
                                                    session.setAttribute("actualizar", "ok"); 
                                                    response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id="); 
                                                }                                       
                                            }else{
                                                int cargoviejo = listaCargoEmpleado.get(0).getCargo().getId_cargo();
                                                if(cargoviejo == 5){
                                                    tutores = new Tutores();
                                                    tutoresDAO = new TutoresDAO();
                                                    int idcarg= cargoEmpleadoDAO.obtenerIdCargoEmpleado(Integer.parseInt(request.getParameter("EditEmpIdEmpleado").trim()));
                                                    int obtener = tutoresDAO.mostrarTutoresFiltroPersona(Integer.parseInt(request.getParameter("EditEmpIdEmpleado").trim()));
                                                    if(obtener > 0){                                                    
                                                        if(tutoresDAO.eliminarTutor(idCargoEmpleado)){                                                       
                                                            session.setAttribute("actualizar", "ok"); 
                                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                                        }else{                                                    
                                                            session.setAttribute("actualizar", "error"); 
                                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                                        }
                                                    }else{
                                                        session.setAttribute("actualizar", "ok"); 
                                                        response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                                    }
                                                }else{
                                                    session.setAttribute("actualizar", "ok"); 
                                                    response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                                }
                                                 
                                            }  
                                       }else{
                                           session.setAttribute("actualizar", "error");
                                           response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                       }
                                   }else{
                                       session.setAttribute("actualizar", "error");
                                       response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id="); 
                                   }
                                   
                               }                                                              
                            }else{
                              session.setAttribute("actualizar", "error");
                              response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id="); 
                            }                           
                            
                        } else {
                            session.setAttribute("actualizar", "error");
                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case "eliminar_empleado":
                    try {
                        empleado = new Empleado();
                        empleadoDAO = new EmpleadoDAO();

                        cargoEmpleadoDAO = new Cargo_EmpleadoDAO();
                        cargoEmpleado = new Cargo_Empleado();
                        empleado.setId_empleado(Integer.parseInt(request.getParameter("id")));
                        
                        if (empleadoDAO.eliminarEmpleado(empleado)) {
                            listaCargoEmpleado = cargoEmpleadoDAO.mostrarCargosEmpleadosFiltroPersona(empleado);
                            if(listaCargoEmpleado.size()>0){
                                cargoEmpleado.setId_cargoEmpleado(listaCargoEmpleado.get(0).getId_cargoEmpleado());
                                cargoEmpleado.setEstado("F");
                                cargoEmpleado.getEmpleado().setId_empleado(empleado.getId_empleado());
                                int aux=listaCargoEmpleado.get(0).getId_cargoEmpleado();
                                if(cargoEmpleadoDAO.modificarCargoEmpleado(cargoEmpleado)){
                                    tutores = new Tutores();
                                    tutoresDAO = new TutoresDAO();              
                                    int idcarg=aux;
                                    int obtenerp=tutoresDAO.mostrarTutoresFiltroPersona(Integer.parseInt(request.getParameter("id")));
                                    if(obtenerp > 0){                                      
                                        if(tutoresDAO.eliminarTutor(idcarg)){                                       
                                            session.setAttribute("eliminar", "ok");
                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id="); 
                                        }else{                                        
                                            session.setAttribute("eliminar", "error");
                                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                        }
                                    }else{
                                        session.setAttribute("eliminar", "ok");
                                        response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                    }
                                     
                                }else{
                                    session.setAttribute("eliminar", "error");
                                    response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                                }
                                  
                            }else{
                                session.setAttribute("eliminar", "error");
                                response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                            }
                            
                        } else {
                            session.setAttribute("eliminar", "error");
                            response.sendRedirect("srvEmpleado?accion=mostrar_empleados&id=");
                        }
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
            }
        }
    }
    
    private String f_RutaModificada(String ruta) {

        int longuitud = ruta.length();
        String entrada = ruta.substring(0, longuitud - 29);
        String rutaConcat = ruta.substring(longuitud - 23, longuitud);
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
