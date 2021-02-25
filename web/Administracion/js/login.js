/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function ()
{
    var idusuario = "";
    var b = true;

    toastr.options = {
        "positionClass": "toast-bottom-right",
        "showMethod": "show",
        "hideMethod": "hide"
    };

    $('#btnIniciarSesion').click(function () {
        iniciarSesion();
    });

    $('#txtClave').keypress(function (e) {
        var keycode = (e.keyCode ? e.keyCode : e.which);
        if (keycode == '13') {
            iniciarSesion();
            e.preventDefault();
            return false;
        }
    });

    $('#codigo').hide();

    $('#enlaceLogin').click(function () {
        $('#codigo').hide();
        $('#correo').show();
        $('#errorcorreo').text("");
        $('#txtCorreo').val("");
        $('#btnEnviarCorreo').text("Enviar");
        $('#btnEnviarCorreo').attr("disabled", false);

        $('#txtCodigo').val("");
        $('#errorcodigo').text("");
        b = false;
    });

    $("#txtCorreo").keyup(function () {
        buscarCorreo($("#txtCorreo").val());
    });
    $("#txtCorreo").focusout(function () {
        buscarCorreo($("#txtCorreo").val());
    });

    function buscarCorreo(correo) {

        $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvUsuario?accion=buscarCorreoInstitucional&correo=' + correo + '',
            success: function (responseText)
            {
                var respon = responseText;
                if (respon === 'ok')
                {
                    b = true;
                    $("#errorcorreo").text("");

                } else
                {
                    b = false;
                    $("#errorcorreo").text("Correo institucional inválido");
                }
            }
        });
    }

    $('#btnEnviarCorreo').click(function () {
        if (b)
        {
            $.ajax({
                async: true,
                type: "GET",
                url: '/UECGV/srvUsuario?accion=buscarCorreoInstitucional&correo=' + $("#txtCorreo").val() + '',
                success: function (responseText)
                {
                    var respon = responseText;
                    if (respon === 'ok')
                    {
                        b = true;
                        $("#errorcorreo").text("");

                        if ($('#txtCorreo').val() !== '')
                        {

                            $('#btnEnviarCorreo').text("Enviando....");
                            $('#btnEnviarCorreo').attr("disabled", true);

                            $.ajax({
                                async: true,
                                type: "GET",
                                url: '/UECGV/srvUsuario?accion=enviarCodigo&correo=' + $('#txtCorreo').val() + '',
                                success: function (responseText)
                                {
                                    var respon = responseText;
                                    if (respon !== '')
                                    {
                                        idusuario = respon;
                                        toastr.info("Se ha enviando un código a su e-mail");
                                        b = true;
                                        $('#correo').hide();
                                        $('#codigo').show();
                                        return false;

                                    } else
                                    {
                                        toastr.error(respon);
                                        $('#btnEnviarCorreo').text("Enviar Código");
                                        $('#btnEnviarCorreo').attr("disabled", false);
                                        return false;
                                    }
                                }
                            });


                        } else
                            toastr.error("Ingrese su correo institucional");

                    } else
                    {
                        b = false;
                        $("#errorcorreo").text("Correo institucional inválido");
                    }
                }
            });

        } else
        {
            toastr.error("Ingrese un correo institucional válido");
            return false;
        }
    });

    $('#btnComprobarCodigo').click(function () {
        if ($('#txtCodigo').val() !== '')
        {
            $('#btnComprobarCodigo').text("Recuperando....");
            $('#btnComprobarCodigo').attr("disabled", true);
            $.ajax({
                async: true,
                type: "GET",
                url: '/UECGV/srvUsuario?accion=recuperarClave&id_usuario=' + idusuario + '&codigo=' + $('#txtCodigo').val() + '&correo=' + $('#txtCorreo').val() + '',
                success: function (responseText)
                {
                    var respon = responseText;
                    if (respon === 'ok')
                    {
                        alert('Su usuario y contraseña han sido enviados a su correo electónico');
                        window.location.href = '/UECGV/login.jsp';
                        return false;

                    } else
                    {
                        toastr.error(respon);
                        $('#btnComprobarCodigo').text("Recuperar");
                        $('#btnComprobarCodigo').attr("disabled", false);
                        return false;
                    }
                }
            });
        } else
            toastr.error("Ingrese el código que le enviamos a su e-mail");

    });

});


function iniciarSesion()
{
    if ($("#txtUsuario").val() === '' || $("#txtClave").val() === '')
    {
        toastr.error("Llene todos los campos");
        return false;
    } else
    {

        $.ajax({
            async: true,
            type: "POST",
            data: $('#frmIniciarSesion').serialize(),
            url: '/UECGV/srvUsuario?accion=iniciarSesion',
            success: function (responseText)
            {
                var respon = responseText;

                if (respon != '')
                {

                    if (respon == 'NO')
                        location.href = '/UECGV/MenuAplicacion/menu.jsp';
                    else
                        location.href = '/UECGV/Administracion/cambiarClave2.0.jsp';
                } else
                {
                    toastr.error("Error al inicar sesión");
                    return false;
                }

            }
        });
    }
}

