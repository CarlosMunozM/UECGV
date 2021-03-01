<%-- 
    Document   : registrarPersonas
    Created on : 15-ene-2019, 9:35:53
    Author     : Carlos
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Registro - U.E.M Carmelina Granja Villanueva</title>

        <meta name="description" content="top menu &amp; navigation" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css" />

        <!-- page specific plugin styles -->

        <!-- text fonts -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

        <!--[if lte IE 9]>
                <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
        <![endif]-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ace-skins.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ace-rtl.min.css" />

        <script src="<%=request.getContextPath()%>/js/ace-extra.min.js"></script>
    </head>

    <body class="no-skin">
        <%@include file="../EstructuraAplicacion/encabezado.jsp" %>

        <div class="main-container ace-save-state" id="main-container">
            <script type="text/javascript">
                try {
                    ace.settings.loadState('main-container')
                } catch (e) {
                }
            </script>

            <div id="sidebar" class="sidebar      h-sidebar                navbar-collapse collapse          ace-save-state">
                <script type="text/javascript">
                    try {
                        ace.settings.loadState('sidebar')
                    } catch (e) {
                    }
                </script>

                <div class="sidebar-shortcuts" id="sidebar-shortcuts">


                    <ul class="breadcrumb">
                        <li>
                            <i class="ace-icon fa fa-home home-icon"></i>
                            <a href="MenuAplicacion/menu.jsp">Menu</a>

                        <li>
                            <a href="srvEmpleado?accion=mostrar">Registrar Empleado</a>
                        </li>

                    </ul><!-- /.breadcrumb -->
                </div><!-- /.sidebar-shortcuts -->
            </div>

            <div class="main-content">
                <div class="main-content-inner">

                    <div class="page-content">
                        
                        <a href="#" data-toggle="tooltip" title="Mostrar Empleado">
                            <button  onclick=" location.href = 'srvEmpleado?accion=mostrar_empleados'" class="btn btn-success fa fa-user" style="float: right"> Mostrar</button>
                        </a>
                        
                        <center>
                            <h4><b>Registrar Empleado</b></h4><br>
                        </center>
                        
                        <form autocomplete="off"  action="/UECGV/srvEmpleado?accion=registrar_empleado" method="post" enctype="multipart/form-data">
                            <input type="text" id="modRegIDCargoEmpleado"  hidden="" name="RegIDCargoEmpleado">
                            <div class="row " style="margin-left: 4%">
                                <div class="col-sm-2">
                                    <label>Tipo de Identificación</label>
                                    <select id="modRegEmpTipoIdentificacion" class="form-control" name="RegEmpTipoIdentificacion" required="" onchange="validarRegistroLongitudIdent()">
                                        <option></option>
                                        <option value="Cédula">Cédula</option>
                                        <option value="Pasaporte">Pasaporte</option>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <label>Identificación</label>
                                    <input type="text" id="modRegEmpIdentificacion" class="form-control"  maxlength="10" required="" onkeypress="return soloNumeros(event)" onkeyup="validar()" name="RegEmpIdentificacion" placeholder="Identificacion">
                                    <span id="erroridentificaciion" style="color: #f00;"></span>
                                </div>
                                <div class="col-sm-3">
                                    <label>Cantón</label>                                    
                                    <select id="modRegEmpIdCanton"  required="" class="form-control input-sm" name="RegEmpIdCanton">
                                        <option></option>                                       
                                        <c:forEach var="listRegisto" items="${listaCantones}">
                                            <option value="${listRegisto.id_canton}">${listRegisto.nombre_canton}</option>
                                        </c:forEach>  
                                    </select>                                                 
                                </div>
                                <div class="col-sm-3">
                                    <label>Provincia</label>
                                    <input type="text" input style="text-transform:uppercase"  id="modRegEmpProvincia" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="RegEmpProvincia" disabled="">
                                </div>
                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-4">
                                    <label>Nombres</label>
                                    <input type="text" input style="text-transform:uppercase"  id="modRegEmpNombres" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="RegEmpNombres" placeholder="Nombres">
                                </div>
                                <div class="col-sm-4">
                                    <label>Apellidos</label>
                                    <input type="text" id="modRegEmpApellidos" class="form-control" style="text-transform:uppercase" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="RegEmpApellidos" placeholder="Apellidos">
                                </div>
                                <div class="col-sm-3">
                                    <label>Cargo</label>
                                    <select id="modRegEmpIdCargo" class="form-control" onchange="MostrarTxtRegistroTutoria()" name="RegEmpIdCargo" required="" >
                                        <option></option>
                                        <c:forEach var="cargo" items="${listaCargos}">
                                            <option value="${cargo.id_cargo}">${cargo.nombre_cargo}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-4">
                                    <label>Correo Personal</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                        <input type="email" id="modRegEmpCorreoPersonal" style="text-transform:lowercase" class="form-control" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" maxlength="50" required="" name="RegEmpCorreoPersonal" onkeyup="validarCorreoPersonal()" placeholder="E-mail">
                                    </div>
                                    <span id="erroremail" style="color: #f00;"></span>
                                </div>
                                <div class="col-sm-4">
                                    <label>Teléfono</label>
                                    <input type="text" id="modRegEmpTelefono" class="form-control" style="text-transform:uppercase" maxlength="10" onkeypress="return soloNumeros(event)" required="" name="RegEmpTelefono" placeholder="Teléfono">
                                </div>                                                                                    
                                <div class="col-sm-3">
                                    <label>Género</label>
                                    <select id="modREgEmpGenero" class="form-control" name="RegEmpGenero" required="">
                                        <option></option>
                                        <option value="M">Masculino</option>
                                        <option value="F">Femenino</option>
                                    </select>
                                </div>                               
                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Correo Institucional</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                        <input type="email" id="modRegEmpCorreoInstitucional" style="text-transform:lowercase" class="form-control" pattern="[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$" maxlength="50" required="" name="RegEmpCorreoInstitucional" onkeyup="validarCorreoInstitucional()" placeholder="E-mail Institucional">
                                    </div>
                                    <span id="erroremail" style="color: #f00;"></span>
                                </div>                                
                               <div class="col-sm-3">
                                    <label>Contrato</label>
                                    <select id="modRegEmpContrato"  class="form-control" name="RegEmpContrato" required=""> 
                                        <option></option>
                                        <option value="Contrato Ocacional">Contrato Ocacional</option>
                                        <option value="Nombramiento Provisional">Nombramiento Provisional</option>
                                        <option value="Nombramiento Definitivo">Nombramiento Definitivo</option>
                                    </select>
                                </div>                                
                                 <div class="col-sm-2">
                                    <label>Escalafón</label>
                                    <select id="modRegEmpEscalafon" class="form-control"  name="RegEmpEscalafon" required=""> 
                                        <option value=""></option>
                                        <option value="Categoría A">Categoría A</option>
                                        <option value="Categoría B">Categoría B</option>
                                        <option value="Categoría C">Categoría C</option>
                                        <option value="Categoría D">Categoría D</option>
                                        <option value="Categoría E">Categoría E</option>
                                        <option value="Categoría F">Categoría F</option>
                                        <option value="Categoría G">Categoría G</option>
                                        <option value="Categoría H">Categoría H</option>
                                        <option value="Categoría I">Categoría I</option>
                                        <option value="Categoría J">Categoría J</option>
                                    </select>
                                </div>                              
                                <div class="col-sm-3">
                                    <label>Nacionalidad</label>
                                    <input type="text" id="modRegEmpNacionalidad" value="Ecuatoriana" class="form-control" maxlength="20" onkeypress="return soloLetras(event)" required="" name="RegEmpNacionalidad" placeholder="Ecuatoriana">
                                </div>
                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Foto</label>
                                    
                                        
                                        <input type="file" class="form-control" maxlength="100" id="modRegFoto" name="RegEmpFoto" placeholder="Seleccione su archivo"/>
                                    
                                    <span id="erroremail" style="color: #f00;"></span>
                                </div>                                
                                <div class="col-sm-3">
                                    <label>Estado civil</label>
                                    <select id="modRegEmpEstadoCivil" style="text-transform:uppercase" class="form-control" name="RegEmpEstadoCivil" required="">
                                        <option></option>
                                        <option value="Soltero">Soltero</option>
                                        <option value="Casado">Casado</option>
                                        <option value="Divorciado">Divorciado</option>
                                        <option value="Unión de hecho">Unión de hecho</option>
                                    </select>
                                </div>                                
                                 <div class="col-sm-2">
                                    <label>Fecha de nacimiento</label>
                                    <input type="date" id="modRegEmpFechaNacimiento" class="form-control"  maxlength="50" required="" name="RegEmpFechaNacimiento" placeholder="">
                                </div>                                
                                <div class="col-sm-3">
                                    <label>Celular</label>
                                    <input type="text" id="modRegEmpCelular" class="form-control" onkeypress="return soloNumeros(event)" maxlength="10" required="" name="RegEmpCelular" placeholder="Celular">
                                </div>
                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-11">
                                    <label>Dirección</label>
                                    <textarea id="modRegEmpDireccion" class="form-control"  maxlength="100" onkeypress="return soloLetrasNumerosCaracEspe(event)" required="" name="RegEmpDireccion" placeholder="Dirección"></textarea>
                                    <%--pattern="[A-Za-z0-9ñ]+"--%>
                                </div>
                            </div>
                            <br>
                            <center id="RegTituloTutorias" style="display: none">
                            <h4><b>TUTORÍAS</b></h4>
                             </center>
                            <div class="row" style="margin-left: 4%; display: none" id="RegtTutoria">
                               <di class="row" style="margin-left: 4%">
                                <div class="col-sm-6">
                                    <label>Curso</label>
                                    <select id="RegEmpIdCurso" class="form-control" name="RegEmpIdCurso" onchange="validarRegistroTutoresxCurso()">
                                        <option></option>
                                        <c:forEach var="cursoEd" items="${listaCursoEducativo}">
                                            <option value="${cursoEd.id_curso}">${cursoEd.nombre_curso} - ${cursoEd.tipo}</option>
                                        </c:forEach>
                                    </select>
                                </div>
      
                                <div class="col-sm-5">
                                    <label>Paralelo</label>
                                    <select id="modRegEmpParalelo" class="form-control"  name="RegEmpParalelo" onchange="validarRegistroTutoresxCurso()"> 
                                        <option value=""></option>
                                        <option value="A">A</option>
                                        <option value="B">B</option>
                                        <option value="C">C</option>
                                    </select>
                                </div>
                            </di> 
                            </div>
                            <br>
   
                            
                            <center>
                                <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30" id="btnRegistrar" >Registrar</button>                        
                            </center>
                        </form>

                    </div><br><br>

                </div><!-- /.main-content -->



                <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
                </a>
            </div><!-- /.main-container -->
            <%@include file="../EstructuraAplicacion/piedepagina.jsp" %>
        </div>
        <!-- basic scripts -->

        <!--[if !IE]> -->
        <script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>

        <!-- <![endif]-->

        <!--[if IE]>
    <script src="assets/js/jquery-1.11.3.min.js"></script>
    <![endif]-->
        <script type="text/javascript">
            if ('ontouchstart' in document.documentElement)
                document.write("<script src='/UECGV/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>
        <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

        <!-- page specific plugin scripts -->

        <!-- ace scripts -->
        <script src="<%=request.getContextPath()%>/js/ace-elements.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/ace.min.js"></script>


        <!-- inline scripts related to this page -->
        <script type="text/javascript">
            jQuery(function ($) {
                var $sidebar = $('.sidebar').eq(0);
                if (!$sidebar.hasClass('h-sidebar'))
                    return;

                $(document).on('settings.ace.top_menu', function (ev, event_name, fixed) {
                    if (event_name !== 'sidebar_fixed')
                        return;

                    var sidebar = $sidebar.get(0);
                    var $window = $(window);

                    //return if sidebar is not fixed or in mobile view mode
                    var sidebar_vars = $sidebar.ace_sidebar('vars');
                    if (!fixed || (sidebar_vars['mobile_view'] || sidebar_vars['collapsible'])) {
                        $sidebar.removeClass('lower-highlight');
                        //restore original, default marginTop
                        sidebar.style.marginTop = '';

                        $window.off('scroll.ace.top_menu')
                        return;
                    }


                    var done = false;
                    $window.on('scroll.ace.top_menu', function (e) {

                        var scroll = $window.scrollTop();
                        scroll = parseInt(scroll / 4);//move the menu up 1px for every 4px of document scrolling
                        if (scroll > 17)
                            scroll = 17;


                        if (scroll > 16) {
                            if (!done) {
                                $sidebar.addClass('lower-highlight');
                                done = true;
                            }
                        } else {
                            if (done) {
                                $sidebar.removeClass('lower-highlight');
                                done = false;
                            }
                        }

                        sidebar.style['marginTop'] = (17 - scroll) + 'px';
                    }).triggerHandler('scroll.ace.top_menu');

                }).triggerHandler('settings.ace.top_menu', ['sidebar_fixed', $sidebar.hasClass('sidebar-fixed')]);

                $(window).on('resize.ace.top_menu', function () {
                    $(document).triggerHandler('settings.ace.top_menu', ['sidebar_fixed', $sidebar.hasClass('sidebar-fixed')]);
                });
            });
        </script>
        
        <!-- Carlos Muñoz -->
        <script src="/UECGV/js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="/UECGV/Administracion/js/Validaciones.js" type="text/javascript"></script>
        <script src="/UECGV/Administracion/js/CRUDempleados.js" type="text/javascript"></script>
                
    </body>
</html>
