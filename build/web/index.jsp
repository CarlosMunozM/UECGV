<%-- 
    Document   : presentacion
    Created on : 03/02/2021, 10:23:22
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
        <!--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">-->
        <link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/style.css">
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

        <!--======================================== Logo & Lema ========================================-->

        <div class="row">
            <div class="col-lg-12">


                <div id="slider-ins" class="carousel slide" data-ride="carousel">
                    <!-- Indicadores -->
                    <ol class="carousel-indicators">
                        <li data-target="#slider-ins" data-slide-to="0" class="active"></li>
                        <li data-target="#slider-ins" data-slide-to="1"></li>
                        <li data-target="#slider-ins" data-slide-to="2"></li>
                    </ol>

                    <!-- Imagenes -->
                    <div class="carousel-inner" role="listbox">

                        <!-- Primera imagen -->
                        <div class="item active">
                            <img src="assets/img/Banner_bachillerato_asistente.png" alt="Default">
                            <div class="carousel-caption">
                                Imágen 1
                            </div>
                        </div>

                        <!-- Segunda imagen -->
                        <div class="item">
                            <img src="assets/img/Banner_bachillerato_contador.png" alt="Default">
                            <div class="carousel-caption">
                                Imágen 2
                            </div>
                        </div>

                        <!-- Tercera imagen -->
                        <div class="item">
                            <img src="assets/img/Banner_bachillerato_electro.png" alt="Default">
                            <div class="carousel-caption">
                                Imágen 3
                            </div>
                        </div>

                    </div>

                    <!-- Controles -->
                    <a class="left carousel-control" href="#slider-ins" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#slider-ins" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>

            </div>
        </div>


        <div class="divider-general"></div>
        <!--======================================== Video corto & carrusel========================================-->
        <section class="full-reset" style="background-color: rgb(242, 242, 242); padding: 40px 0;">
            <div class="container">
                <h2 class="text-center titles">Información de la institución</h2>
                <br><br>
                <!--======================================== Carrusel ========================================-->
                <div class="row">
                    <div class="col-xs-12 col-sm-6 col-sm-push-6">
                        <h3 class="text-center titles">Fotos Instalaciones</h3>
                        <p class="lead text-justify">
                            El primer mandatario y sus autoridades de gobierno inauguraron en el año 2013 la Unidad Educativa del Milenio (UEM) “María Carmelina Granja Villanueva” ubicada en la parroquia San Carlos del Cantón Quevedo.
                        </p>
                        <i class="fa fa-picture-o icon-index hidden-xs hidden-sm"></i>
                    </div>
                    <div class="col-xs-12 col-sm-6 col-sm-pull-6">

                        <div id="slider-ins2" class="carousel slide" data-ride="carousel">
                            <!-- Indicadores -->
                            <ol class="carousel-indicators">
                                <li data-target="#slider-ins" data-slide-to="0" class="active"></li>
                                <li data-target="#slider-ins" data-slide-to="1"></li>
                                <li data-target="#slider-ins" data-slide-to="2"></li>
                            </ol>

                            <!-- Imagenes -->
                            <div class="carousel-inner" role="listbox">

                                <div class="item active">
                                    <img src="assets/img/institucion_entrada.jpg" alt="Default" >
                                    <div class="carousel-caption">
                                        
                                    </div>
                                </div>

                                <!-- Segunda imagen -->
                                <div class="item">
                                    <img src="assets/img/institucion_pasillo.jpg" alt="Default" >
                                    <div class="carousel-caption">
                                        
                                    </div>
                                </div>

                                <!-- Tercera imagen -->
                                <div class="item">
                                    <img src="assets/img/institucion_entrada2.jpg" alt="Default" >
                                    <div class="carousel-caption">
                                        
                                    </div>
                                </div>


                            </div>

                            <!-- Controles -->
                            <a class="left carousel-control" href="#slider-ins2" role="button" data-slide="prev">
                                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="right carousel-control" href="#slider-ins2" role="button" data-slide="next">
                                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                        </div>


                    </div>
                </div>
                <br>
                <div class="divider-general"></div>
                <br>
                <!--======================================== Video ========================================-->
                <div class="row">
                    <div class="col-xs-12 col-sm-6">
                        <h3 class="text-center titles">Video Instalaciones</h3>
                        <p class="lead text-justify">
                            La Unidad Educativa “Carmelina Granja Villanueva” basa su ideario en la formación integral del estudiante, fundamentado los siguientes principios:
                            libertad, educación, formación, enseñanza, inclusión, equidad y democracia.
                        </p>
                        <i class="fa fa-film icon-index hidden-xs hidden-sm"></i>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <iframe width="100%" height="380" src="https://www.youtube.com/embed/GDB0eWnyhLY" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
                    </div>
                </div>

                <br>
                <div class="divider-general"></div>
                <br>

                <div class="row">

                    <div class="col-xs-12 col-sm-6">
                        <img src="assets/img/institucion_docentes.jpg" class="img-thumbnail">
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <h3 class="text-center titles">Misión</h3>
                        <p class="lead text-justify">Somos una institución fiscal reconocida a nivel provincial y local que forma personas creativas a través de
                                        una educación integral, innovadora, inclusiva y de calidad, capaces de dialogar, investigar y contribuir a la 
                                        conservación del medio ambiente, al desarrollo de la familia y de una sociedad más solidaria en el marco de la justicia y la paz.</p>
                        <i class="fa fa-university icon-index hidden-xs hidden-sm"></i>
                    </div>

                </div>

                <br>
                <div class="divider-general"></div>
                <br>

                <div class="row">

                    <div class="col-xs-12 col-sm-6">
                        <h3 class="text-center titles">Visión</h3>
                        <p class="lead text-justify">En el año 2022 la Unidad Educativa “Carmelina Granja Villanueva” será líder y referente por su propuesta
                                        curricular propia e innovadora, que utilice las nuevas tecnologías, medios de comunicación, cultura de la 
                                        lectura y corrientes pedagógicas actuales; que garantiza una formación sólida en valores, ciudadanía, identidad personal, 
                                        regional y nacional; cuidando y preservando el medio ambiente para el desarrollo sostenible y apertura al mundo globalizado a través de proyectos innovadores.</p>
                        <i class="fa fa-university icon-index hidden-xs hidden-sm"></i>
                    </div>

                    <div class="col-xs-12 col-sm-6">
                        <img src="assets/img/institucion_docentes2.jpg" class="img-thumbnail">
                    </div>

                </div>

                <br>
                <center>
                    <a href="institucion.jsp" class="open" style="font-size:1.5em">Has click aqui para más información</a>
                </center>

            </div>
        </section>
        <div class="divider-general"></div>
        <!--======================================== Acontecer institucional ========================================-->
        <section class="events-ins">
            <div class="container-fluid">
                <h2 class="text-center titles">ACONTECER INSTITUCIONAL</h2>
                <br><br>
                <div class="row">
                    <!--======================================== Articulo 1 ========================================-->
                    <article class="col-xs-12 col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src="assets/gallery/default.png" alt="IMG" class="img-responsive img-rounded">
                            <div class="caption">
                                <h3 class="text-center">Lorem ipsum dolor sit amet</h3>
                                <p class="text-justify">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis quam, incidunt, sapiente id quibusdam voluptatem.</p>
                                <p class="text-center"><a href="#" class="btn btn-primary" role="button">Ver imágenes</a></p>
                            </div>
                        </div>
                    </article>
                    <!--======================================== Articulo 2 ========================================-->
                    <article class="col-xs-12 col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src="assets/gallery/default.png" alt="IMG" class="img-responsive img-rounded">
                            <div class="caption">
                                <h3 class="text-center">Lorem ipsum dolor sit amet</h3>
                                <p class="text-justify">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis quam, incidunt, sapiente id quibusdam voluptatem.</p>
                                <p class="text-center"><a href="#" class="btn btn-primary" role="button">Ver imágenes</a></p>
                            </div>
                        </div>
                    </article>
                    <!--======================================== Articulo 3 ========================================-->
                    <article class="col-xs-12 col-sm-6 col-md-4">
                        <div class="thumbnail">
                            <img src="assets/gallery/default.png" alt="IMG" class="img-responsive img-rounded">
                            <div class="caption">
                                <h3 class="text-center">Lorem ipsum dolor sit amet</h3>
                                <p class="text-justify">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Perferendis quam, incidunt, sapiente id quibusdam voluptatem.</p>
                                <p class="text-center"><a href="#" class="btn btn-primary" role="button">Ver imágenes</a></p>
                            </div>
                        </div>
                    </article>
                </div>
            </div>
        </section>
        <div class="divider-general"></div>
        <!--======================================== Enlaces importantes ========================================-->
        <!--section class="text-center important-links-index">
            <h2 class="titles">Sitios y enlaces importantes</h2>

            <a href="#!" class="open-link-newTab">
                <i class="fa fa-graduation-cap"></i>
                <p>MOODLE</p>
            </a>

            <a href="#!" class="open-link-newTab">
                <i class="fa fa-paw"></i>
                <p>ARA-MACAO</p>
            </a>

            <a href="#!" class="open-link-newTab">
                <i class="fa fa-globe"></i>
                <p>JOVENES A.T.T</p>
            </a>

            <a href="#!" class="open-link-newTab">
                <i class="fa fa-star-o"></i>
                <p>Promo. INS</p>
            </a>

            <a href="#!" class="open-link-newTab">
                <i class="fa fa-file-text-o"></i>
                <p>Cons.conducta</p>
            </a>

            <a href="#!" class="open-link-newTab">
                <i class="fa fa-download"></i>
                <p>Descargas</p>
            </a>
        </section-->
        <!--======================================== Pie de pagina ========================================-->
        <%@include file="estructura/social_bar.jspf" %>
        <%@include file="estructura/pie_pagina_presentacion.jspf" %>
        <script src="http://www.openlayers.org/api/OpenLayers.js"></script>
    </body>
</html>