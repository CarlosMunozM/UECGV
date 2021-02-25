/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Carlos Muñoz
 */

function confirmarRecuperarCuentaUsuario(id_usuario, usuario, correo)
{
    $('#btnRecuperarClaveAdmin').attr("disabled", true);
    $('#btnModificarModAdmin').attr("disabled", true);
    $('#btnEliminarModAdmin').attr("disabled", true);
    
    alertify.confirm('Confirmación', '¿Enviar correo de recuperación de clave a este usuario?', function () {
        window.location.href = '/UECGV/srvUsuario?accion=recuperarClaveAdministracion&id_usuario=' + id_usuario + '&usuario=' + usuario + '&correo=' + correo + '';
    }, function () {
        $('#btnRecuperarClaveAdmin').attr("disabled", false);
        $('#btnModificarModAdmin').attr("disabled", false);
        $('#btnEliminarModAdmin').attr("disabled", false);
        
        //toastr.error("Eliminación Cancelada");
    }).set('labels', {ok: 'Si', cancel: 'No'});
}

function confirmarEliminacionCuentaUsuario(id_usuario)
{
    alertify.confirm('Confirmación', '¿Está seguro que desea eliminar este usuario?', function () {
        window.location.href = '/UECGV/srvUsuario?accion=eliminar_usuario&id_usuario=' + id_usuario + '';
    }, function () {
        //toastr.error("Eliminación Cancelada");
    }).set('labels', {ok: 'Si', cancel: 'No'});
}


function datosModalModificar(idUsuario, usuario)
{
    $("#errorusuario").text("");
    $("#modCtaUsrIdUsuario").val(idUsuario);
    $('#exampleModalEditCtaUsrLongTitleShow').html("Modificar Usuario: ".bold().concat(usuario));

    $('#btnModificarCtaUsr').text("Guardar");
    $('#btnModificarCtaUsr').attr("disabled", false);

    $('#modEditCtaUsrUsuario').focus();
}

function datosModalRegistrar(idEmpleado, correo, apellidos, nombres)
{
    $("#errorusuario_add").text("");
    $("#addCtaUsrIdEmpleado").val(idEmpleado);
    $("#addCtaUsrCorreo").val(correo);
    $('#exampleModalAddCtaUsrLongTitleShow').html("Registrar Usuario a: ".bold().concat(apellidos, " ", nombres));

    $('#btnRegistrarCtaUsr').text("Guardar");
    $('#btnRegistrarCtaUsr').attr("disabled", false);
    
    $('#addEditCtaUsrUsuario').focus();
}

$(document).ready(function () {

    $('#btnRecuperarClaveAdmin').attr("disabled", false);
    $('#btnModificarModAdmin').attr("disabled", false);
    $('#btnEliminarModAdmin').attr("disabled", false);
    
    var valuser = true, valuser2 = true;

    $('#btnModificarCtaUsr').click(function () {
        if ($('#modEditCtaUsrUsuario').val() != '')
        {
            
            $('#btnModificarCtaUsr').text("Guardando....");
            $('#btnModificarCtaUsr').attr("disabled", true);
            
            $.ajax({
                async: true,
                type: "GET",
                url: '/UECGV/srvUsuario?accion=buscarUsuario&usuario=' + $("#modEditCtaUsrUsuario").val() + '',
                success: function (responseText)
                {
                    if (responseText === 'usuarioOcupado')
                    {
                        valuser = false;
                        valuser2 = false;
                        $("#errorusuario").text("Este usuario ya existe");
                        toastr.error("Usuario no disponible");
                        
                        $('#btnModificarCtaUsr').text("Guardar");
                        $('#btnModificarCtaUsr').attr("disabled", false);

                    } else
                    {
                        cambiarClaveCtaUsr();
                        valuser = true;
                        valuser2 = true;
                        $("#errorusuario").text("");
                    }
                }
            });
                
        } else
            toastr.error("Usuario vacío");

    });

    $('#btnRegistrarCtaUsr').click(function () {
        if ($('#addEditCtaUsrUsuario').val() != '')
        {
            $('#btnRegistrarCtaUsr').text("Guardando....");
            $('#btnRegistrarCtaUsr').attr("disabled", true);
            
            $.ajax({
                async: true,
                type: "GET",
                url: '/UECGV/srvUsuario?accion=buscarUsuario&usuario=' + $("#addEditCtaUsrUsuario").val() + '',
                success: function (responseText)
                {
                    if (responseText === 'usuarioOcupado')
                    {
                        valuser = false;
                        valuser2 = false;
                        $("#errorusuario").text("Este usuario ya existe");
                        toastr.error("Usuario no disponible");
                        
                        $('#btnRegistrarCtaUsr').text("Guardar");
                        $('#btnRegistrarCtaUsr').attr("disabled", false);

                    } else
                    {
                        registrarCuentaUsuario();
                        valuser = true;
                        valuser2 = true;
                        $("#errorusuario").text("");
                    }
                }
            });
            
        } else
            toastr.error("Usuario vacío");

    });
    function limpiar()
    {
        $('#modEditCtaUsrUsuario').val('');
        $("#errorusuario").text("");
        $('#modCtaUsrIdUsuario').val("");
        $('#modEditCtaUsrUsuario').val("");
    }

    function limpiarAddCtaUsr()
    {
        $('#addEditCtaUsrUsuario').val("");
        $("#errorusuario_add").text("");
        $('#addCtaUsrIdEmpleado').val("");
        $('#addEditCtaUsrUsuario').val("");
        $('#addCtaUsrCorreo').val("");
    }

    $('#btnCerrarModalCtaUsr').click(function () {
        limpiar();
    });

    $('#btnCerrarInfModalCtaUsr').click(function () {
        limpiar();
    });

    $('#btnCerrarModalAddCtaUsr').click(function () {
        limpiarAddCtaUsr();
    });

    $('#btnCerrarInfModalAddCtaUsr').click(function () {
        limpiarAddCtaUsr();
    });

    toastr.options = {
        "positionClass": "toast-bottom-right",
        "showMethod": "show",
        "hideMethod": "hide"
    }


    $("#modEditCtaUsrUsuario").keyup(function () {
        buscarUsuario($("#modEditCtaUsrUsuario").val(), "errorusuario");
    });

    $("#modEditCtaUsrUsuario").focusout(function () {
        buscarUsuario($("#modEditCtaUsrUsuario").val(), "errorusuario");
    });

    $("#addEditCtaUsrUsuario").keyup(function () {
        buscarUsuario($("#addEditCtaUsrUsuario").val(), "errorusuario_add");
    });

    $("#addEditCtaUsrUsuario").focusout(function () {
        buscarUsuario($("#addEditCtaUsrUsuario").val(), "errorusuario_add");
    });


    function buscarUsuario(nombreUsuario, span) {

        $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvUsuario?accion=buscarUsuario&usuario=' + nombreUsuario + '',
            success: function (responseText)
            {
                if (responseText === 'usuarioOcupado')
                {
                    valuser = false;
                    valuser2 = false;
                    $("#" + span + "").text("Este usuario ya existe");

                } else
                {
                    valuser = true;
                    valuser2 = true;
                    $("#" + span + "").text("");
                }
            }
        });
    }


});

function cambiarClaveCtaUsr()
{
    $.ajax({
        async: true,
        type: "GET",
        url: '/UECGV/srvUsuario?accion=modificar_usuario&id_usuario=' + $('#modCtaUsrIdUsuario').val() + '&usuario=' + $('#modEditCtaUsrUsuario').val() + '',
        success: function (responseText)
        {
            var respon = responseText;
            if (respon === 'ok')
            {
                $("#btnCerrarModalCtaUsr").click();
                //toastr.success("Usuario modificado");
                
                $('#btnModificarCtaUsr').text("Guardar");
                $('#btnModificarCtaUsr').attr("disabled", false);
                
                location.href = '/UECGV/srvUsuario?accion=mostrar_cuentas_usuarios';

            } else
            {
                $("#btnCerrarModalCtaUsr").click();
                toastr.error("Error al modificar usuario");
            }
        }
    });
}

function registrarCuentaUsuario()
{
    $.ajax({
        async: true,
        type: "GET",
        url: '/UECGV/srvUsuario?accion=registrar_usuario&id_empleado=' + $('#addCtaUsrIdEmpleado').val() + '&usuario=' + $('#addEditCtaUsrUsuario').val() + '&correo=' + $('#addCtaUsrCorreo').val() + '',
        success: function (responseText)
        {
            var respon = responseText;
            if (respon === 'ok')
            {
                $("#btnCerrarInfModalAddCtaUsr").click();
                //toastr.success("Usuario modificado");
                
                $('#btnRegistrarCtaUsr').text("Guardar");
                $('#btnRegistrarCtaUsr').attr("disabled", false);
                
                location.href = '/UECGV/srvUsuario?accion=mostrar_cuentas_usuarios';

            } else
            {
                $("#btnCerrarInfModalAddCtaUsr").click();
                toastr.error("Error al modificar usuario");
            }
        }
    });
}