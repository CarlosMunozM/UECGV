<%-- 
    Document   : newjsp
    Created on : 13-ene-2019, 14:25:30
    Author     : Carlos Muñoz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Cambiar Clave</title>

        <meta name="description" content="3 styles with inline editable feature" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="../css/bootstrap.min.css" />
        <link rel="stylesheet" href="../css/font-awesome.min.css" />

        <!-- page specific plugin styles -->
        <link rel="stylesheet" href="../css/jquery-ui.custom.min.css" />
        <link rel="stylesheet" href="../css/jquery.gritter.min.css" />
        <link rel="stylesheet" href="../css/select2.min.css" />
        <link rel="stylesheet" href="../css/bootstrap-datepicker3.min.css" />
        <link rel="stylesheet" href="../css/bootstrap-editable.min.css" />

        <!-- text fonts -->
        <link rel="stylesheet" href="../css/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="../css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />


        <!--Menu-->

        <!-- FONTAWESOME STYLES-->
        <link href="../css/font-awesome.css" rel="stylesheet" />
        <link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css' />
        <!--Fin Menu -->



        <!--[if lte IE 9]>
                <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
        <![endif]-->
        <link rel="stylesheet" href="../css/ace-skins.min.css" />
        <link rel="stylesheet" href="../css/ace-rtl.min.css" />

        <script src="../js/ace-extra.min.js"></script>

        <script src="../js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="../js/toastr.js" type="text/javascript"></script>
        <link href="../css/toastr.min.css" rel="stylesheet" type="text/css"/>


        <style>
            .texto{
                font-size: 12.5px;
            }

            .centrar {
                float: none;
                margin-left: 50px;
                margin-right: auto;

            }
            .centrardiv{

                float: none;
                margin-left: auto;
                margin-right: auto;
            }

            .invalid {
                background: url('/UECGV/Administracion/img/cerrar.png') no-repeat 0 50%;
                padding-left: 22px;
                line-height: 24px;
                color: #ec3f41;
            }

            .valid {
                background: url('/UECGV/Administracion/img/true.png') no-repeat 0 50%;
                padding-left: 22px;
                line-height: 24px;
                color: #3a7d34;
            }
        </style>

    </head>

    <body class="no-skin">

        <%@include file="../Administracion/encabezadoSimple.jspf" %>

        <div class="main-container ace-save-state" id="main-container"><br><br><br>
            <div class="centrardiv texto col-sm-4" >
                <div>
                    <!--<div class="well" style="margin-bottom: 3px;padding: 10px; display: flex;"> -->
                    <div class="well">
                        <center>
                            <h4><b>Cambio de clave</b></h4>
                        </center>
                    </div>
                </div>
                <div class="row">
                    <div class="well" style="display: flex;">
                        <form id="formulario" style="width: 100%; margin-bottom: 0" onsubmit="return false">
                            <div class="row-fluid">
                                <div id="pswd_info">
                                    <h6>La clave debe cumplir con los siguientes parámetros:</h6>
                                    <ul>
                                        <li id="letter" class="">Al menos <strong>una letra</strong></li>
                                        <li id="capital" class="">Al menos <strong>una letra en mayúscula y una letra en minúscula</strong></li>
                                        <li id="number" class="">Al menos <strong>un número</strong></li>
                                        <li id="length" class="">Al menos ha de contener <strong>8 caracteres</strong></li>
                                        <li id="igualdad" class="" hidden="">La nueva clave <strong>no debe ser igual</strong> a la anterior</li>
                                        <li id="nuevarepetir" class="">La nueva clave <strong>debe ser igual</strong> a repetir clave.</li>
                                    </ul>
                                </div>
                                <p></p>
                                <input type="hidden" name="action" value="changepass">

                                <div class="row form-group centrar" hidden="">
                                    <div class="col-4 ">
                                        <label  style="padding-right: 10px" >Clave Actual</label>
                                        <input id="id_anterior" name="anterior" type="password" required=""  style="text-transform: none;">
                                    </div>
                                </div>
                                <div class="row form-group centrar">
                                    <div class="col-4 ">
                                        <label  style="padding-right: 15px">Nueva Clave</label>
                                        <input id="id_nueva" class="validarclave " name="txtModPassClave" type="password" required="" aria-autocomplete="list" style="text-transform: none; width: 140px">
                                    </div>
                                </div>
                                <div class="row form-group centrar">
                                    <div class="col-4 ">
                                        <label  style="padding-right: 10px">Repetir Clave</label>
                                        <input class="validarclave" id="id_repetir" name="repetir" type="password" required="" style="text-transform: none; width: 140px">
                                    </div>
                                </div>
                            </div>

                            <div class="row-fluid" style="margin-bottom: 0">
                                <div style="text-align: right;">
                                    <a onclick="cambiarClave()" class="btn btn-primary btn-form" id="formbutton" style="display: none;">Guardar</a>
                                    <a href="/UECGV/index.jsp" class="btn btn-danger">Cancelar</a>
                                </div>
                                <div style="text-align: left;">
                                    <a href="/UECGV/index.jsp" style="font-size: 15px"><b>Iniciar Sesión</b></a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                $('.validarclave').keyup(function () {
                    var pswd = $(this).val();
                    var habilitar = true;
                    if (pswd.length < 8) {
                        $('#length').removeClass('valid').addClass('invalid');
                        habilitar = false;
                    } else {
                        $('#length').removeClass('invalid').addClass('valid');
                    }
                    if (pswd.match(/[A-z]/)) {
                        $('#letter').removeClass('invalid').addClass('valid');
                    } else {
                        habilitar = false;
                        $('#letter').removeClass('valid').addClass('invalid');
                    }
                    if (pswd.match(/[A-Z]/) && pswd.match(/[a-z]/)) {
                        $('#capital').removeClass('invalid').addClass('valid');
                    } else {
                        habilitar = false;
                        $('#capital').removeClass('valid').addClass('invalid');
                    }
                    if (pswd.match(/\d/)) {
                        $('#number').removeClass('invalid').addClass('valid');
                    } else {
                        habilitar = false;
                        $('#number').removeClass('valid').addClass('invalid');
                    }
                    if ($("#id_nueva").val().toLowerCase() != $("#id_anterior").val().toLowerCase()) {
                        $('#igualdad').removeClass('invalid').addClass('valid');
                    } else {
                        habilitar = false;
                        $('#igualdad').removeClass('valid').addClass('invalid');
                    }
                    if ($("#id_nueva").val() == $("#id_repetir").val()) {
                        $('#nuevarepetir').removeClass('invalid').addClass('valid');
                    } else {
                        habilitar = false;
                        $('#nuevarepetir').removeClass('valid').addClass('invalid');
                    }
                    if (habilitar) {
                        $("#formbutton").show();
                    } else {
                        $("#formbutton").hide();
                    }
                });

                toastr.options = {
                    "positionClass": "toast-bottom-left",
                    "showMethod": "show",
                    "hideMethod": "hide"
                }
            });

            function cambiarClave()
            {
                $.ajax({
                    async: true,
                    type: "POST",
                    data: $('#formulario').serialize(),
                    url: '/UECGV/srvUsuario?accion=modificarClaveNoRequerido',
                    success: function (responseText)
                    {
                        var respon = responseText;
                        if (respon === 'ok')
                        {
                            toastr.success("Contraseña Modificada");
                            limpiar();
                            location.href = '../login.jsp';
                        } else
                        {
                            toastr.error("Error al modificar Contraseña");
                        }
                    }
                });
            }

            function limpiar()
            {
                $("#id_nueva").val('');
                $("#id_repetir").val('');
                $('#length').removeClass();
                $('#letter').removeClass();
                $('#capital').removeClass();
                $('#number').removeClass();
                $('#igualdad').removeClass();
                $('#nuevarepetir').removeClass();
                $("#formbutton").hide();
            }
        </script>



    </body>
</html>
