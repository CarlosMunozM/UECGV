<%-- 
    Document   : imagenesSlider1
    Created on : 24/02/2021, 4:33:10
    Author     : ASUS
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Imágenes - U.E.M Carmelina Granja Villanueva</title>

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
                            <a href="srvImagenPresentacion?accion=Listar">Imagenes</a>
                        </li>

                    </ul><!-- /.breadcrumb -->
                </div><!-- /.sidebar-shortcuts -->
            </div>

            <div class="main-content">
                <div class="main-content-inner">

                    <div class="page-content">

                        <a href="#" data-toggle="tooltip" title="Actualizar Tabla">
                            <button  onclick=" location.href = 'srvImagenPresentacion?accion=Listar'" class="btn btn-primary fa fa-refresh" style="float: left"> Actualizar</button>
                        </a>
                        <a type="button" data-toggle="modal" data-target="#addImagenSlider" title="Registrar Imagen">
                            <button id="btnRegistrarImgPresentacion" class="btn btn-success fa fa-plus" style="float: right"> Registrar</button>
                        </a>
                        <center>
                            <h3><b>Imágenes</b></h3><br>
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
                                        <th style="text-align: center;">Registrado Por </th>
                                        <th style="text-align: center;">Imagen </th>
                                        <th style="text-align: center;"><i class="fa fa-list"></i></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="p" items="${imagenes}">
                                        <tr>
                                            <td>${p.nombre}</td>
                                            <td><img name="" src="${p.ruta}"height="80" width="150" /></td>
                                            <td style="text-align: center;">
                                                <a href="#" data-toggle="tooltip" title="Eliminar"><button class="btn btn-sm btn-danger" onclick="confirmarEliminacionImg('${p.id_imgpresentacion}');"> <i class="zmdi zmdi-delete" ></i></button></a>                                       
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

            <div class="modal fade" id="addImagenSlider" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <%-- <form method="post" action="srvImagenPresentacion?accion=Guardar" enctype="multipart/form-data"> --%>
                        <form autocomplete="off" id="frmImagenPresentacion"  enctype="multipart/form-data">
                            <div class="modal-header bg-primary">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="btnCerrarModalmgPresentacion"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title"  style="color: white;text-align: center" id="myModalLabel">Agregar imagen</h4>
                            </div>
                            <div class="modal-body">

                                <div class="form-group">
                                    <label>Imagen</label>
                                    <input type="file" id="files" name="RegevtFoto" class="file"  multiple=true placeholder="Seleccione su archivo" required="" pattern="[a-zA-z0-9áéíóúÁÉÍÓÚñÑ ]{1,50}" maxlength="50" data-placement="top" title="Seleccione su archivo">
                                    <small class="text-danger">Dimensiones Ancho (3000px o 3333px) entre Alto (1000px o 1113px)</small>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-danger" data-dismiss="modal">Cerrar</button>
                                <button id="btn-guardar" type="submit" class="btn btn-primary" name="accion" value="Guardar" disabled>Guardar</button>
                            </div>
                        </form>
                    </div>
                </div>
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

            <script src="/UECGV/js/jquery-3.3.1.min.js" type="text/javascript"></script>

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

>
            <script src="/UECGV/js/toastr.js" type="text/javascript"></script>
            <link href="/UECGV/css/toastr.min.css" rel="stylesheet" type="text/css"/>
            
            <script type="text/javascript">
                
                 $(document).ready(function () {
                    
                    $('#bootstrap-data-table').DataTable({
                        "scrollX": true,
                        "language": {
                            "url": "/UECGV/Administracion/plugins/dataTable/Spanish.json"
                        }
                    });
                    
                     toastr.options = {
                        "positionClass": "toast-bottom-right",
                        "showMethod": "show",
                        "hideMethod": "hide"
                    };
                    
                    $('#btn-guardar').click(function () {
                        guardarImagenPresentacion();
                    });
                    
                    $('#btnRegistrarImgPresentacion').click(function () {
                        //alert('click');
                        $("#RegevtFoto").val(null);
                    });
                    
                });
                
                function guardarImagenPresentacion()
                {
                    $('#btn-guardar').text("Registrando...");
                    $('#btn-guardar').attr("disabled", true);

                    var form = $('#frmImagenPresentacion')[0];
                    var data = new FormData(form);

                    $.ajax({

                        type: "POST",
                        enctype: 'multipart/form-data',
                        data: data,
                        url: '/UECGV/srvImagenPresentacion?accion=Guardar',
                        processData: false,  // Important!
                        contentType: false,
                        cache: false,
                        success: function (responseText)
                        {
                            if (responseText)
                            {
                                toastr.success("Guardado con éxito");                    
                                $("#btnCerrarModalmgPresentacion").click();
                                location.href = '/UECGV/srvImagenPresentacion?accion=Listar';

                            } else{
                                $('#btn-guardar').text("Registrar");
                                $('#btn-guardar').attr("disabled", false);
                                //$("#btnCerrarModalmgPresentacion").click();
                                toastr.error("Error¡ No se logro registrar la presentación.");
                                
                            }
                        }
                    });
                }
               
                $("#files").change(function () {
                    var file = this.files[0];
                    var img = new Image();
                    img.src = window.URL.createObjectURL(file);
                    img.onload = function () {
                        if ((this.width.toFixed(0) >= 3000 && this.width.toFixed(0) <= 3333) && (this.height.toFixed(0) >= 1000 && this.height.toFixed(0) <= 1113)) {
                            $("#btn-guardar").attr("disabled", false);
                        } else {
                            $("#btn-guardar").attr("disabled", true);
                            toastr.error("Las dimensiones de la imagen no son corresctas");
                        }
                    };
                });

                function confirmarEliminacionImg(idimg) {
                    alertify.confirm('Confirmación', '¿Está seguro que desea eliminar esta imagen?', function () {
                        window.location.href = '/UECGV/srvImagenPresentacion?accion=Eliminar&id=' + idimg + '';
                    }, function () {
                        toastr.error("Eliminación Cancelada");
                    }).set('labels', {ok: 'Si', cancel: 'No'});
                }
                
            </script>
    </body>
</html>