<%-- 
    Document   : Login
    Created on : 12-dic-2018, 19:40:32
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Iniciar Sesión</title>
        <link rel="icon" href="assets/img/logo_UECGV.png" >
        <meta name="description" content="User login page" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <link rel="stylesheet" href="css/font-awesome.min.css" />
        <!-- text fonts -->
        <link rel="stylesheet" href="css/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="css/ace.min.css" />

        <link rel="stylesheet" href="css/ace-rtl.min.css" />

        <!-- Carlos Muñoz -->
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <link href="css/toastr.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/toastr.js" type="text/javascript"></script>



    </head>

    <body class="login-layout">
        <div class="main-container">
            <div class="main-content">
                <div class="row">
                    <div class="col-sm-10 col-sm-offset-1">
                        <div class="login-container">
                            <div class="center">
                                <br><br>
                                <h1>
                                    <a href="/UECGV/index.jsp" style="text-decoration:none;"><span class="white" id="id-text2">U.E.M Carmelina Granja Villanueva</span></a>
                                </h1>
                                <%--<h3 id="id-company-text"><b> &copy; Universidad Técnica Estatal de Quevedo</b></h3>--%>
                            </div>

                            <div class="space-6"></div>

                            <div class="position-relative">
                                <div id="login-box" class="login-box visible widget-box no-border">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <h4 class="header blue lighter bigger center">
                                                <i class="ace-icon fa fa-users green"></i>
                                                <b>Iniciar Sesión</b>
                                            </h4>

                                            <div class="space-6"></div>

                                            <form id="frmIniciarSesion" autocomplete="off">
                                                <fieldset>
                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="text" class="form-control" placeholder="Usuario" id="txtUsuario" name="usuario" />
                                                            <i class="ace-icon fa fa-user"></i>
                                                        </span>
                                                    </label>

                                                    <label class="block clearfix">
                                                        <span class="block input-icon input-icon-right">
                                                            <input type="password" class="form-control" placeholder="Contraseña" id="txtClave" name="clave" />
                                                            <i class="ace-icon fa fa-lock"></i>
                                                        </span>
                                                    </label>

                                                    <div class="space"></div>

                                                    <div class="clearfix ">
                                                        <center>

                                                            <button type="button"  class="width-35 btn btn-sm btn-primary" id="btnIniciarSesion">
                                                                <i class="ace-icon fa fa-key"></i>

                                                                <span class="bigger-110">Entrar</span>
                                                            </button>
                                                        </center>
                                                    </div>

                                                    <div class="space-4"></div>
                                                </fieldset>
                                            </form>

                                        </div><!-- /.widget-main -->

                                        <div class="toolbar clearfix">
                                            <div>
                                                <a href="#" data-target="#forgot-box" class="forgot-password-link">
                                                    <i class="ace-icon fa fa-arrow-left"></i>
                                                    Olvidé mi Contraseña
                                                </a>
                                            </div>

                                        </div>
                                    </div><!-- /.widget-body -->
                                </div><!-- /.login-box -->



                                <div id="forgot-box" class="forgot-box widget-box no-border">
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <h4 class="header red lighter bigger">
                                                <i class="ace-icon fa fa-key"></i>
                                                Recuperar Contraseña
                                            </h4>

                                            <div class="space-6"></div>
                                            <div id="correo">
                                                <p>
                                                    Ingrese su correo institucional
                                                </p>

                                                
                                                    <fieldset>
                                                        <form autocomplete="off" action="#">
                                                            <label class="block clearfix">
                                                                <span class="block input-icon input-icon-right">
                                                                    <input type="email" class="form-control" placeholder="Email" id="txtCorreo"/>
                                                                    <i class="ace-icon fa fa-envelope"></i>
                                                                    <span id="errorcorreo" style="color: #f00;"></span>
                                                                </span>
                                                            </label>
                                                        </form>
                                                        <div class="clearfix">
                                                            <button type="button" class="width-35 pull-right btn btn-sm btn-danger" id="btnEnviarCorreo">
                                                                <i class="ace-icon fa fa-send"></i>
                                                                <span class="bigger-110">Enviar</span>
                                                            </button>
                                                        </div>
                                                    </fieldset>
                                                
                                            </div>
                                            <div id="codigo">
                                                <p>
                                                    Ingrese el código que le enviamos a su correo
                                                </p>

                                                
                                                    <fieldset>
                                                        <form autocomplete="off" action="#">
                                                            <label class="block clearfix">
                                                                <span class="block input-icon input-icon-right">
                                                                    <input type="text" class="form-control" placeholder="Código" id="txtCodigo"/>
                                                                    <i class="ace-icon fa fa-pencil"></i>
                                                                    <span id="errorcodigo" style="color: #f00;"></span>
                                                                </span>
                                                            </label>
                                                        </form>
                                                        <div class="clearfix">
                                                            <button type="button" class="width-45 pull-right btn btn-sm btn-danger" id="btnComprobarCodigo">
                                                                <i class="ace-icon fa fa-check"></i>
                                                                <span class="bigger-110">Recuperar</span>
                                                            </button>
                                                        </div>
                                                    </fieldset>
                                                
                                            </div>
                                        </div><!-- /.widget-main -->

                                        <div class="toolbar center">
                                            <a href="#" data-target="#login-box" class="back-to-login-link" id="enlaceLogin">
                                                Iniciar Sesión
                                                <i class="ace-icon fa fa-arrow-right"></i>
                                            </a>
                                        </div>
                                    </div><!-- /.widget-body -->
                                </div><!-- /.forgot-box -->

                            </div><!-- /.position-relative -->
                        </div>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.main-content -->
        </div><!-- /.main-container -->

        <!-- basic scripts -->
        
        <!--Carlos Muñoz -->
        <script src="Administracion/js/login.js" type="text/javascript" ></script>
        

        <!--[if !IE]> -->
        <script src="js/jquery-2.1.4.min.js"></script>
        
        <script type="text/javascript">
            if ('ontouchstart' in document.documentElement)
                document.write("<script src='js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>

        <!-- inline scripts related to this page -->
        <script type="text/javascript">
            jQuery(function ($) {
                $(document).on('click', '.toolbar a[data-target]', function (e) {
                    e.preventDefault();
                    var target = $(this).data('target');
                    $('.widget-box.visible').removeClass('visible'); //hide others
                    $(target).addClass('visible'); //show target
                });

            });
        </script>
    </body>
</html>
