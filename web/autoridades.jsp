<%-- 
    Document   : autoridades
    Created on : 20-feb-2021, 17:14:46
    Author     : DHL-SIS-ING
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="estructura/head_pagina.jspf" %>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/style.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/jquery-1.11.2.min.js"><\/script>');</script>
        <script src="js/modernizr.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/autoridades.js"></script>
        <script src="js/main.js"></script>
    </head>
    <body>
        <!--======================================== Boton ir arriba ========================================-->
        <i class="btn-up fa fa-arrow-circle-o-up hidden-xs"></i>
        <!--======================================== Navegación ========================================-->
        <%@include file="estructura/encabezado_presentacion.jspf" %>
        <!--======================================== Contenido de la pagina ========================================-->
        <section id="section-autoridades" class="full-reset mb-200" style="background-color: #fff; padding: 20px 0;">
            <div class="container">
                <div class="row">
                    <section class="col-xs-12">
                        <article class="full-reset">
                            <div class="page-header" style="margin-bottom:20px;">
                                <h1><i class="fa fa-graduation-cap"></i> &nbsp; Autoridades</h1>
                            </div>
                            <div class="autoridades">
                            </div>
                        </article>
                    </section>
                </div>
            </div>
        </section>
        <!--======================================== Pie de pagina ========================================-->
        <%@include file="estructura/pie_pagina_presentacion.jspf" %>
        <script src="http://www.openlayers.org/api/OpenLayers.js"></script>
    </body>
</html>