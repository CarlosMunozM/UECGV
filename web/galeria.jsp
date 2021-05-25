<%-- 
    Document   : Galeria
    Created on : 03/02/2021, 13:22:33
    Author     : Carlos
--%>

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
                    <a href="galeria.jsp">Galería</a>
                </li>
            </ul>
        </div>
        
	<!--======================================== Contenido de la pagina ========================================-->
	<section class="full-reset section-gallery-ins">
		<!--======================================== Fotos de la institucion ========================================-->
		<article class="container">
                    <h2 class="text-center"><i class="fa fa-picture-o"></i> &nbsp; Fotos Instalaciones</h2>
                    <div class="row">
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/institucion1.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/institucion2.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/institucion3.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/institucion4.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/institucion5.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/institucion6.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/aula_4.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/aula_5.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/aula_6.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/aula_1.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/aula_2.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                        <article class="col-xs-12 col-sm-6 col-md-4">
                            <div class="thumbnail">
                                <img src="Imagenes/Institucion/aula_3.jpg" alt="IMG" class="img-responsive img-rounded">
                            </div>
                        </article>
                    </div>
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
        <script src="js/modalImagen.js"></script>
</body>
</html>