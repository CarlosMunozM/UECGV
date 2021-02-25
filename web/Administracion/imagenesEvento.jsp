<%-- 
    Document   : imagenesSlider
    Created on : 11/02/2021, 13:01:47
    Author     : ASUS
--%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="top menu &amp; navigation" />
        <meta name="author" content="">
        <link rel="icon" href="../../favicon.ico">
        <link rel="canonical" href="https://getbootstrap.com/docs/3.4/examples/starter-template/">

        <title>Menu - U.E.M Carmelina Granja Villanueva</title>
         
        <!-- pepe -->
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/estilos.css">
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



        <script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>




    </head>

    <body>
        <%@include file="../EstructuraAplicacion/encabezado.jsp" %> 


        <section id="breadcrumb">
            <div class="page-content">
                <a href="#" data-toggle="tooltip" title="Registrar Usuario">
                    <button  type="button" data-target="#addImagenSlider"class="btn btn-success fa fa-user" style="float: right"> Registrar Evento</button>
                </a>
            </div>
        </section>
        <table id="bootstrap-data-table"  class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>
                <tr>
                    <th style="text-align: center;">Nombre evento </th>
                    <th style="text-align: center;">Persona que publicó</th>
                    <th style="text-align: center;">Descripcion</th>
                    <th style="text-align: center;">Fecha Inicio</th>
                    <th style="text-align: center;">Fecha Fin</th>
                    <th style="text-align: center;"><i class="fa fa-list"></i></th>
                </tr>
            </thead>
            <tbody>

                <c:forEach var="p" items="${eventosmg}">
                    <tr>
                        <td>${p.nombre_evento}</td>
                        <td>${p.nombrepersona}</td>
                        <td>${p.descripcion}</td>
                        <td>${p.feha_inicio}</td>
                        <td>${p.fecha_fin}</td>
                        <td style="text-align: center;">
                            <a href="#" data-toggle="tooltip" title="Eliminar"><button class="btn btn-sm btn-danger" > <i class="zmdi zmdi-delete" ></i></button></a>                                       
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
       



        <!-- Bootstrap core JavaScript
          ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!-- Modal agragar imagen -->

        <div class="modal fade" id="addImagenSlider" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <form method="post" action="srvEventoImagen?accion=Guardar"enctype="multipart/form-data">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">AgregarEvento</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Titulo Evento</label>
                                <input type="text" name="txtnombre_evento" class="form-control">     
                            </div>
                            <div class="form-group">
                                <label>Descripcion</label>
                                <textarea name="txtdescripcion"class="form-control" placeholder="informacion del evento"></textarea>  
                            </div>
                            <div class="form-group">
                                <label>Fecha inicio de Evento</label>
                                <input type="date" name="txtfecha_inicio" class="form-control">     
                            </div>
                            <div class="form-group">
                                <label>Fecha fin de Evento</label>
                                <input type="date" name="txtfecha_fin" class="form-control">     
                            </div>                  
                            <div class="form-group">
                                <label>Imagen</label>
                                <input type="file" id="files" name="fileImagen" class="file"  multiple=true placeholder="Seleccione su archivo" required="" pattern="[a-zA-z0-9áéíóúÁÉÍÓÚñÑ ]{1,50}" maxlength="50" data-placement="top" title="Seleccione su archivo">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button class="btn btn-primary" name="accion" value="Guardar">Guardar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


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

        <script src="https://code.jquery.com/jquery-1.12.4.min.js" integrity="sha384-nvAa0+6Qg9clwYCGGPpDQLVpLNn0fRaROjHqs13t4Ggj3Ez50XnGQqc/r8MhnRDZ" crossorigin="anonymous"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
