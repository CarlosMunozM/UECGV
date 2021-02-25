<%-- 
    Document   : mostrarUsuarios
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
        <title>Empleados - U.E.M Carmelina Granja Villanueva</title>

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

        <link href="<%=request.getContextPath()%>/Administracion/css/material-design-iconic-font.min.css" rel="stylesheet" type="text/css"/>

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
                            <a href="<%=request.getContextPath()%>/MenuAplicacion/menu.jsp">Menu</a>

                        <li>
                            <a href="srvEmpleado?accion=mostrar_empleados&id=">Empleados</a>
                        </li>

                    </ul><!-- /.breadcrumb -->
                </div><!-- /.sidebar-shortcuts -->
            </div>

            <div class="main-content">
                <div class="main-content-inner">

                    <div class="page-content">

                        <a href="#" data-toggle="tooltip" title="Actualizar Tabla">
                            <button  onclick=" location.href = 'srvEmpleado?accion=mostrar_empleados&id='" class="btn btn-primary fa fa-refresh" style="float: left"> Actualizar</button>
                        </a>
                        <a href="#" data-toggle="tooltip" title="Registrar Usuario">
                            <button  onclick=" location.href = 'srvEmpleado?accion=mostrar_formRegistro&id='" class="btn btn-success fa fa-user" style="float: right"> Registrar</button>
                        </a>
                        <center>
                            <h3><b>Empleados</b></h3><br>
                        </center>
                        <div class="row">
                            <c:if test="${mensaje != null}" >
                                <div class="alert alert-danger hidden-sm hidden-xso">
                                    <center>${mensaje}</center> 
                                </div>
                            </c:if>
                            <table id="bootstrap-data-table"  class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                                <thead>
                                    <tr>
                                        <th style="text-align: center;">Usuario</th>
                                        <th style="text-align: center;">Cargo</th>
                                        <th style="text-align: center;">Contrato</th>
                                        <th style="text-align: center;"><i class="fa fa-list"></i></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="Users" items="${listaUsuarios}">
                                        <tr>
                                            <td>${Users.apellidos} ${Users.nombres}</td>
                                            <td>${Users.cargo.nombre_cargo}</td>
                                            <td>${Users.contrato}</td>
                                            <td style="text-align: center;">
                                                <a href="#" data-toggle="tooltip" title="Ver Datos">
                                                    <button class="btn btn-sm btn-info" id="btnMostrarDatos" data-toggle="modal" data-target="#modalDatos"
                                                            onclick="datosModalMostrar('${Users.tipo_identificacion}','${Users.identificacion}','${Users.nacionalidad}','${Users.genero}','${Users.nombres}','${Users.apellidos}','${Users.celular}','${Users.correo_personal}','${Users.telefono}','${Users.contrato}','${Users.escalafon}','${Users.correo_institucional}','${Users.estado_civil}','${Users.foto}','${Users.fecha_nacimiento}','${Users.direccion}','${Users.canton.nombre_canton}','${Users.canton.provincia.nombre_provincia}','${Users.cargo.nombre_cargo}','${Users.tutores.curso_educativo.nombre_curso}','${Users.tutores.paralelo}','${Users.tutores.curso_educativo.tipo}')">
                                                        <i class="zmdi zmdi-eye"></i></button>
                                                </a>

                                                <a href="#" data-toggle="tooltip" title="Modificar">
                                                    <button class="btn btn-sm btn-success" id="btnMostrarModificarMod" data-toggle="modal" data-target="#modalModificarDatos"
                                                            onclick="datosModalModificar('${Users.id_empleado}','${Users.tipo_identificacion}','${Users.identificacion}','${Users.nacionalidad}','${Users.genero}','${Users.nombres}','${Users.apellidos}','${Users.celular}','${Users.correo_personal}','${Users.telefono}','${Users.contrato}','${Users.escalafon}','${Users.correo_institucional}','${Users.estado_civil}','${Users.foto}','${Users.fecha_nacimiento}','${Users.direccion}','${Users.canton.id_canton}','${Users.canton.provincia.nombre_provincia}','${Users.cargo.id_cargo}','${Users.tutores.curso_educativo.id_curso}','${Users.tutores.paralelo}')">
                                                        <i class="zmdi zmdi-edit"></i></button>
                                                </a>
                                                <a href="#" data-toggle="tooltip" title="Eliminar">
                                                    <button class="btn btn-sm btn-danger" onclick="confirmarEliminacion('${Users.id_empleado}');"> <i class="zmdi zmdi-delete" ></i></button>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                            <br><br><br><br><br>

                        </div>

                    </div><!-- /.main-content -->

                    
                    <c:if test="${sessionScope.actualizar == 'ok'}" >
                    <script type="text/javascript">
                       alert('Datos Actualizados Correctamente');
                    </script>
                    
                    </c:if>
        
                    <c:if test="${sessionScope.actualizar == 'error'}" >
                     <script type="text/javascript">
                        alert('Datos Actualizados Incorrectamente');
                    </script>
                    
                    </c:if> 
                    <% session.setAttribute("actualizar","");%>
                    
                    <c:if test="${sessionScope.registrar == 'ok'}" >
                    <script type="text/javascript">
                       alert('Datos Guardados');
                    </script>
                    
                    </c:if>
        
                    <c:if test="${sessionScope.registrar == 'error'}" >
                     <script type="text/javascript">
                        alert('Error al Guardar');
                    </script>
                    
                    </c:if> 
                    <% session.setAttribute("registrar","");%>
                    
                     <c:if test="${sessionScope.eliminar == 'ok'}" >
                    <script type="text/javascript">
                       alert('Usuario Eliminado');
                    </script>
                    
                    </c:if>
        
                    <c:if test="${sessionScope.eliminar == 'error'}" >
                     <script type="text/javascript">
                        alert('Error al Guardar');
                    </script>
                    
                    </c:if> 
                    <% session.setAttribute("eliminar","");%>
                    
                    
                    

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

            


            <!-- inline scripts related to this page -->
            <script type="text/javascript">

                    $(document).ready(function () {
                        var valeditcorreo = true, valeditidentificacion = true;
                        
                        $('#bootstrap-data-table').DataTable({
                            "scrollX": true,
                            "language": {
                                "url": "/UECGV/Administracion/plugins/dataTable/Spanish.json"
                            }
                        });
                        
                    });

            </script>
            
             <script src="<%=request.getContextPath()%>/js/toastr.js" type="text/javascript"></script>
            <link href="<%=request.getContextPath()%>/css/toastr.min.css" rel="stylesheet" type="text/css"/>

            <script src="/UECGV/Administracion/plugins/alertify/alertify.min.js" type="text/javascript"></script>
            <link href="/UECGV/Administracion/plugins/alertify/alertify.min.css" rel="stylesheet" type="text/css"/>
            <link href="/UECGV/Administracion/plugins/alertify/default.min.css" rel="stylesheet" type="text/css"/>
            
            <%@include file="/Administracion/FormulariosModalesEmpleados.jspf"%>
            
            <script src="/UECGV/Administracion/js/Validaciones.js" type="text/javascript"></script>
            <script src="/UECGV/Administracion/js/CRUDempleados.js" type="text/javascript"></script>
            
            
            
            <script src="/UECGV/Administracion/plugins/dataTable/jquery.dataTables.min.js" type="text/javascript"></script>
            <link href="/UECGV/Administracion/plugins/dataTable/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>

    </body>
</html>
