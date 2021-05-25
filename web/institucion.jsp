<%-- 
    Document   : institucion
    Created on : 03/02/2021, 12:45:01
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
                    <a href="institucion.jsp">Institución</a>
                </li>
            </ul>
        </div>
        <!--======================================== Contenido de la pagina ========================================-->
        <section class="full-reset" style="background-color: #fff;">
            <div class="container">
                <div class="row">
                    <section class="col-xs-12 col-sm-8 col-md-9 info-section-ins">
                        <!--======================================== Mision y vision ========================================-->
                        <article class="full-reset" id="mision-vision">
                            <div class="page-header" style="margin-bottom:40px;">
                                <h1><i class="fa fa-graduation-cap"></i> &nbsp; Misión y Vision</h1>
                            </div>
                            <br><br>
                            <div class="panel panel-primary">
                                <div class="panel-heading lead text-center titles">Misión</div>
                                <div class="panel-body">
                                    <p class="lead text-justify">	
                                        Somos una institución fiscal reconocida a nivel provincial y local que forma personas creativas a través de
                                        una educación integral, innovadora, inclusiva y de calidad, capaces de dialogar, investigar y contribuir a la 
                                        conservación del medio ambiente, al desarrollo de la familia y de una sociedad más solidaria en el marco de la justicia y la paz.
                                    </p>
                                </div>
                            </div>
                            <br><br>
                            <div class="panel panel-primary">
                                <div class="panel-heading lead text-center titles">Visión</div>
                                <div class="panel-body">
                                    <p class="lead text-justify">
                                        En el año 2022 la Unidad Educativa “Carmelina Granja Villanueva” será líder y referente por su propuesta
                                        curricular propia e innovadora, que utilice las nuevas tecnologías, medios de comunicación, cultura de la 
                                        lectura y corrientes pedagógicas actuales; que garantiza una formación sólida en valores, ciudadanía, identidad personal, 
                                        regional y nacional; cuidando y preservando el medio ambiente para el desarrollo sostenible y apertura al mundo globalizado a través de proyectos innovadores.
                                    </p>
                                </div>
                            </div>	
                        </article>
                        <!--======================================== Reseña historica ========================================-->
                        <article class="full-reset" id="resena-ins">
                            <div class="page-header" style="margin-bottom:50px;">
                                <h1><i class="fa fa-university"></i> &nbsp; Reseña histórica</h1>
                            </div>
                            <h3 class="text-center titles text-uppercase">Antecedentes</h3>
                            <p class="lead text-justify">
                                La Unidad Educativa del Milenio “Carmelina Granja Villanueva”, fue creada mediante RESOLUCION MINISTERIAL N° 261-CZ5-DAJ-2013 del 28 de marzo del 2013, como plantel de ciclo básico y bachillerato.
                                <br><br>
                                El 27 de octubre del 2013 mediante RESOLUCION MINISTERIAL N° MINEDUC-CZ5-2013-0306-R se resuelve disponer que se unifique la Escuela de Educación Básica “2 DE JULIO” 
                                con código AMIE 12H00966, a la Unidad Educativa del Milenio “Carmelina Granja Villanueva”, con código AMIE 12H02517. Ambas instituciones ubicadas en el Circuito Educativo 12D03C09, Distrito Educativo QUEVEDO – MOCACHE 12D03, parroquia San Carlos cantón Quevedo, provincia Los Ríos, zona 5.
                                <br><br>
                                En las respectivas jornadas matutina y vespertina se ofertaron: 
                                Educación inicial, nivel 2, subniveles 1 y 2;
                                Educación General Básica, subniveles preparatorios, básicos elementales, básica media y básica superior; 
                                Bachillerato en Ciencias General (antiguo bachillerato) y a partir del 2014 – 2015 Bachillerato General Unificado en Ciencias.
                                <br><br>
                                A partir del 02 de Mayo del año 2013 inicia las actividades pedagógicas en la sección matutina y posteriormente a partir del 14 de junio del presente año realiza sus actividades pedagógicas de manera matutina y vespertina.
                            </p>
                            <br><br>
                            <h3 class="text-center titles text-uppercase">Ideario</h3>
                            <p class="lead text-justify">
                                De la concepción del Modelo Educativo vigente y del slogan “Sembrando Educación de calidad y calidez con excelencia”, se desprende nuestro ideario que orienta a una educación diferente a la vanguardia
                                de los avances educativos actuales alineados a las normas Constitucionales vigentes, Ley Orgánica de Educación Intercultural, Reglamento LOEI, Código de Convivencia Institucional, Código de la Niñez y la Adolescencia; ofreciendo una educación competitiva con calidad y calidez que promueva el desarrollo en el marco del buen vivir.
                            </p>
                            <br><br>
                            <h3 class="text-center titles text-uppercase">Lema</h3>
                            <p class="lead text-center">
                                “Sembrando Educación de calidad y calidez con excelencia”
                            </p>
                            <br><br>
                            <h3 class="text-center titles text-uppercase">VALORES INSTITUCIONALES</h3>
                            <p class="lead text-justify">
                                La Unidad Educativa “Carmelina Granja Villanueva” basa su ideario en la formación integral del estudiante fundamentado en los siguientes principios:
                            </p>
                            <p class="lead text-justify">
                                <strong>LIBERTAD:</strong> Porque formamos personas libres y autónomas a través de un currículo que integra las teorías,
                                conceptos y enfoques disciplinares con la praxis y los valores humanos, para que trasciendan hacia un futuro
                                propio, con competencias personales, sociales y comunicativas, comprometidos al desarrollo y bienestar de la sociedad.
                            </p>
                            <p class="lead text-justify">
                                <strong>EDUCACIÓN:</strong> Porque ayudamos al estudiante a perfeccionarse en todas sus dimensiones, potenciando la 
                                investigación científica, el desarrollo del pensamiento e inteligencia, los valores de sensibilidad, afectividad y 
                                relaciones con los demás, dentro del marco democrático, demostrando justicia social, desarrollo de una conciencia nacional y solidaria.
                            </p>
                            <p class="lead text-justify">
                                <strong>FORMACIÓN:</strong> Porque contribuimos a que cada estudiante adquiera una personalidad firme y equilibrada,
                                mediante la práctica permanente de la moralidad, ética y libertad humana, en el marco de una convivencia alegre y feliz con su entorno social y natural. 
                            </p>
                            <p class="lead text-justify">
                                <strong>ENSEÑANZA:</strong> Porque favorecemos el desarrollo de las facultades intelectuales del estudiante para que las 
                                utilice como instrumento propio de creación del conocimiento e innovaciones, llevándolos a un aprender a
                                aprehender, aprender a pensar, aprender a expresarse, aprender a ser y aprender a emprender. 
                            </p>
                            <p class="lead text-justify">
                                <strong>INCLUSIÓN:</strong> Porque promovemos la inserción y el acompañamiento a 
                                todos los estudiantes incluyendo e insertando aquellos con necesidades educativas especiales.
                            </p>
                            <p class="lead text-justify">
                                <strong>EQUIDAD:</strong> Porque educamos con igualdad de condiciones, sin considerar género ni edad, respetando la pluriculturalidad, la multietnicidad e ideología de la comunidad educativa. 
                            </p>
                            <p class="lead text-justify">
                                <strong>DEMOCRACIA:</strong> Porque promovemos espacios para la participación, organización, planeación y ejecución de procesos académicos estudiantiles, 
                                que permitan la práctica de la libertad, equidad, inclusión, solidaridad, justicia, autonomía y responsabilidad.
                            </p>
                        </article>
                        <!--======================================== Ubicacion geografica ========================================-->
                        <article class="full-reset" id="ubicacion-ins">
                            <div class="page-header">
                                <h1><i class="fa fa-map"></i> &nbsp; Ubicación geográfica</h1>
                            </div>
                            <p class="lead text-justify">
                                La UEM "Carmelina Granja Villanueva" es una Institución pública, ubicada en la parroquia San Carlos - Quevedo. Presta su servicio educativo a niñ@s (matutina), jóvenes (vespertina) y adultos (Nocturno). 
                                Siempre sembrando con calidad y calidez.
                            </p>
                            <br><br>
                            <h3 class="text-center titles"><i class="fa fa-map-marker"></i> &nbsp; Mapa de localización</h3>
                            <br>
                            <div id ="mapa-ins"></div>
                        </article>
                    </section>
                    <!--======================================== Navegacion fija lateral derecha ========================================-->
                    <nav class="hidden-xs scroll-navigation-ins">
                        <figure class="full-reset">
                            <img src="assets/img/logo_UECGV.png" alt="Logo" class="img-responsive center-box img-rounded">
                        </figure>
                        <h4 class="text-center titles">Información de la institución</h4>
                        <ul class="list-unstyled full-reset">
                            <li data-href="#mision-vision">Misión y Visión</li>
                            <li data-href="#resena-ins">Reseña Histórica</li>
                            <li data-href="#ubicacion-ins">Ubicación Geofráfica</li>
                        </ul>
                    </nav>
                </div>
            </div>
        </section>
        <!--======================================== Pie de pagina ========================================-->
        <%@include file="estructura/pie_pagina_presentacion.jspf" %>
        <!--======================================== API de GOOGLE Maps ========================================-->
        <script src="http://www.openlayers.org/api/OpenLayers.js"></script>
    </body>
</html>