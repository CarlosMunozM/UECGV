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
        <title>Alumnos - U.E.M Carmelina Granja Villanueva</title>

        <meta name="description" content="top menu &amp; navigation" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <%@include file="../EstructuraAplicacion/head_icono.jsp" %>
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

            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
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
                            <a href="srvEstudiante?accion=mostrar_estudiante">Alumnos</a>
                        </li>

                    </ul><!-- /.breadcrumb -->
                </div><!-- /.sidebar-shortcuts -->
            </div>

            <div class="main-content">
                <div class="main-content-inner">

                    <div class="page-content">

                        <a href="#" data-toggle="tooltip" title="Actualizar Tabla">
                            <button  onclick=" location.href = 'srvEstudiante?accion=mostrar_estudiante'" class="btn btn-primary fa fa-refresh" style="float: left"> Actualizar</button>
                        </a>
                        <a href="#" data-toggle="tooltip" title="Descargar Datos">
                            <button id="descargar_datos" class="btn btn-secondary fa fa-download" style="float: right"></button>
                        </a>
                        <a href="#" data-toggle="tooltip" title="Registrar Usuario">
                            <button  onclick=" location.href = 'srvCurso_Educativo?accion=mostrar_curso_educativo'" class="btn btn-success fa fa-user" style="float: right"> Registrar</button>
                        </a>
                        <center>
                            <h3><b>Alumnos</b></h3><br>
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
                                        <th style="text-align: center;">Cédula</th>
                                        <th style="text-align: center;">Curso</th>
                                        <th style="text-align: center;"><i class="fa fa-list"></i></th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <c:forEach var="Users" items="${listaUsuarios}">
                                        <tr>
                                            <td>${Users.apellidos} ${Users.nombres}</td>
                                            <td>${Users.identificacion}</td>
                                            <td>${Users.curso_educativo.nombre_curso}  ${Users.curso_educativo.tipo}</td>
                                            <td style="text-align: center;">
                                                <a href="#" data-toggle="tooltip" title="Ver Datos">
                                                    <button class="btn btn-sm btn-info" id="btnMostrarDatos" data-toggle="modal" data-target="#modalDatos" 
                                                            onclick="datosModalMostrarALU('${Users.tipo_identificacion}','${Users.identificacion}','${Users.nacionalidad}','${Users.genero}','${Users.nombres}','${Users.apellidos}','${Users.fecha_nacimiento}','${Users.curso_educativo.nombre_curso}','${Users.curso_educativo.tipo}','${Users.direccion}','${Users.numero_hermanos}','${Users.lugar_ocupa}','${Users.carnet_discapacidad}',
                                                                        '${Users.discapacidad}','${Users.tipo_discapacidad}','${Users.historia_clinica}','${Users.familiar.nombres}','${Users.familiar.apellidos}','${Users.familiar.tipo_identificacion}','${Users.familiar.cue}','${Users.familiar.celular}','${Users.familiar.ocupacion}','${Users.familiar.lugar_trabajo}','${Users.familiar.correo}','${Users.familiar.nacionalidad}','${Users.familiar.identificacion}',
                                                                        '${Users.referencia.nombres}','${Users.referencia.apellidos}','${Users.referencia.celular}','${Users.referencia.telefono}','${Users.referencia.parentesco}','${Users.correo}','${Users.celular}','${Users.foto}','${Users.foto_domicilio}')">
                                                  <i class="zmdi zmdi-eye "></i> </button>
                                                    
                                                </a>

                                                <a href="#" data-toggle="tooltip" title="Modificar">
                                                    <button class="btn btn-sm btn-success" id="btnMostrarModificarMod" onclick="enviaridentificacion('${Users.identificacion}')">     
                                                        <i class="zmdi zmdi-edit"></i></button>
                                                </a>
                                                <a href="#" data-toggle="tooltip" title="Eliminar" onclick="confirmarEliminacionEstudiante('${Users.id_estudiante}');">
                                                    <button class="btn btn-sm btn-danger" > <i class="zmdi zmdi-delete" ></i></button>
                                                </a>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>
                            <br><br><br><br><br>

                        </div>

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
                        
                        toastr.options = {
                            "positionClass": "toast-bottom-right",
                            "showMethod": "show",
                            "hideMethod": "hide"
                        };
                        
                        var valeditcorreo = true, valeditidentificacion = true;
                        
                        $('#bootstrap-data-table').DataTable({
                            "scrollX": true,
                            "language": {
                                "url": "/UECGV/Administracion/plugins/dataTable/Spanish.json"
                            }
                        });
                        
                        $("#descargar_datos").click(function(){
                            $("#descargar_datos").attr("disabled", true);
                            $("#descargar_datos").removeClass("fa-download");
                            $("#descargar_datos").text("...");
                            
                            $.ajax({
                                url: '/UECGV/srvDescargarDatos',
                                contentType: 'application/vnd.ms-excel',
                                data: {seccion: 'alumnos'},
                                success: function (data, textStatus, jqXHR) {
                                    var b64Data = window.atob(data);
                                    var bin = b64Data;
                                    
                                    var link = document.createElement('a');
                                    link.innerHTML = 'Descargar Datos Alumnos';
                                    link.download = 'Alumnos.xlsx';
                                    link.href = 'data:application/octet-stream;base64,' + data;
                                    link.click();
                                    toastr.success("Descarga exitosa.");
                                    $("#descargar_datos").attr("disabled", false);
                                    $("#descargar_datos").addClass("fa-download");
                                    $("#descargar_datos").text("");
                                },
                                error: function (jqXHR, textStatus, errorThrown) {
                                    toastr.error("Error! no se logro descargar el archivo.");
                                    $("#descargar_datos").attr("disabled", false);
                                    $("#descargar_datos").addClass("fa-download");
                                    $("#descargar_datos").text("");
                                }
                            });
                        });
                        
                    });

            </script>
            
             <script src="<%=request.getContextPath()%>/js/toastr.js" type="text/javascript"></script>
            <link href="<%=request.getContextPath()%>/css/toastr.min.css" rel="stylesheet" type="text/css"/>

            <script src="/UECGV/Administracion/plugins/alertify/alertify.min.js" type="text/javascript"></script>
            <link href="/UECGV/Administracion/plugins/alertify/alertify.min.css" rel="stylesheet" type="text/css"/>
            <link href="/UECGV/Administracion/plugins/alertify/default.min.css" rel="stylesheet" type="text/css"/>
            
            <%@include file="/Administracion/FormulariosModales.jspf"%>
            
            <script src="/UECGV/Administracion/js/Validaciones.js" type="text/javascript"></script>
            <script src="/UECGV/Administracion/js/CRUDpersonas.js" type="text/javascript"></script>
            
            
            <script src="/UECGV/Administracion/plugins/dataTable/jquery.dataTables.min.js" type="text/javascript"></script>
            <link href="/UECGV/Administracion/plugins/dataTable/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>

    </body>
</html>
