<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
	<%@include file="estructura/head_pagina.jspf" %>
	<link rel="stylesheet" href="css/font-awesome.min.css">
	<link rel="stylesheet" href="css/normalize.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css'>
	<link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
	<link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="css/modalImagen.css">
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>window.jQuery || document.write('<script src="js/jquery-1.11.2.min.js"><\/script>');</script>
	<script src="js/modernizr.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/main.js"></script>
</head>
<body>
	<!--======================================== Boton ir arriba ========================================-->
	<i class="btn-up fa fa-arrow-circle-o-up hidden-xs"></i>
	<!--======================================== Navegación ========================================-->
	<%@include file="estructura/encabezado_presentacion.jspf" %>
        
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="index.jsp">Inicio</a>
                <li>
                    <a href="galeria.jsp">Eventos</a>
                </li>
            </ul>
        </div>
        
	<!--======================================== Contenido de la pagina ========================================-->
	<section class="full-reset section-gallery-ins mb-200">
		<!-- ======================================== Eventos del 2013 ========================================-->
                <div id="select_eventos" class="container input-group">
                    <h3>Eventos</h3>
                    <select id="selectEvents" class="form-control" style="width: 20%; margin-right: 10px;">
                        <option value="0"></option>
                    </select>
                    <button id="btnMostrarEvents" class="btn btn-primary fa fa-eye">&nbsp;Mostrar</button>
                </div>
                <!-- ===== Eventos ===-->
                <article id="seccion_eventos" class="container">
                    
                </article>
	</section>
        
        <!-- The Modal -->
        <div id="myModal" class="modal">
            <!-- The Close Button -->
            <span class="close" id="close-modal">&times;</span>
            <!-- Modal Content (The Image) -->
            <img class="modal-content" id="img01">
            <!-- Modal Caption (Image Text) -->
            <div id="caption"></div>
        </div>
	<!--======================================== Pie de pagina ========================================-->
	<%@include file="estructura/pie_pagina_presentacion.jspf" %>
        <script src="js/galeria_eventos.js"></script>
</body>
</html>