/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Carlos Muñoz CSE
 */

function confirmarEliminacionEstudiante(id_estudiante)
{
    alertify.confirm('Confirmación', '¿Está seguro que desea eliminar este usuario?', function () {
        window.location.href = '/UECGV/srvEstudiante?accion=eliminar_estudiante&id_estudiante=' + id_estudiante + '';
    }, function () {
        //toastr.error("Eliminación Cancelada");
    }).set('labels', {ok: 'Si', cancel: 'No'});
}
function confirmarEliminacionALU(idPersona)
{
    alertify.confirm('Confirmación', '¿Está seguro que desea eliminar esta persona?', function () {
        window.location.href = '/UECGV/srvUsuarios?accion=eliminar&idPersona=' + idPersona + '';
    }, function () {
        toastr.error("Eliminación Cancelada");
    }).set('labels', {ok: 'Si', cancel: 'No'});
}

function datosModalMostrarALU(tipoIdentificacion, identificacion, nacionalidad, genero, nombres, apellidos, fecha_nacimiento, nombre_curso, direccion, numero_hermanos, lugar_ocupa,
        carnet_discapacidad, discapacidad, tipo_discapacidad, historia_clinica, apellidosFa, nombresfa, tipo_identificacionFa, cue, celular, ocupacion, lugardeTrabajo, correo, nacionalidad, identificacion,
        nombreRef, apellidoRef, parentescoRef, celularRef, telefonoRef, correo_personal, celularAl,foto)
{
    
    $('#modAluTipoIdentificacion').html(tipoIdentificacion);
    $('#modVerAludentificacion').html(identificacion);
    $('#modVerAluPais').html(nacionalidad);
    if (genero === 'M')
        $('#modVerALuGenero').html('Masculino');
    else
        $('#modVerALuGenero').html('Femenino');
    //$('#modVerAluFoto').html("no existe aun");
    $('#modVerAluNombres').html(nombres);
    $('#modVerAluApellidos').html(apellidos);
    $('#modVerAluFechaNacimiento').html(fecha_nacimiento);
    // $('#modVerAluTelefono').html("no asignado");
    $('#modVerAluCurso').html(nombre_curso);

    $('#modVerAluDireccion').html(direccion);
    $('#modVerAluHermanos').html(numero_hermanos);
    $('#modAverLugarHermanos').html(lugar_ocupa);
    $('#modVerAluCelular').html(celularAl);
    $('#modVerAluEmail').html(correo_personal);
    $('#modVerAluCarnetDiscapacidad').html(carnet_discapacidad);

    $('#modVerAluDiscapacidad').html(discapacidad);
    $('#modVerAluTipoDiscapacidad').html(tipo_discapacidad);
    $('#modVerAluHistClinica').html(historia_clinica);


    $('#modVerAluCelular').html("No Asignado");

    //datos del familiar 
    $('#modVerAluIdentificacionR').html(identificacion);
    $('#modVerAluTipoidentificacionR').html(tipo_identificacionFa);
    $('#modVerAluNombresR').html(nombresfa);
    $('#modAluVerApellidosR').html(apellidosFa);

    $('#modVerAluCueR').html(cue);
    $('#modVerAluCelularR').html(celular);
    $('#modVerALuOcupacionR').html(ocupacion);
    $('#modVerAluLgrDetrabajo').html(lugardeTrabajo);
    $('#modVerAluEmailR').html(correo);
    $('#modVerAluPaisR').html(nacionalidad);

    //datos del contacto de referencia 
    $('#modVerRefNombres').html(nombreRef);
    $('#modVerRefApellidos').html(apellidoRef);
    $('#modVerRefParentesco').html(parentescoRef);
    $('#modVerRefCeluar').html(celularRef);
    $('#modVerRefTelefono').html(telefonoRef);


  var img = document.getElementById("modVerAluFoto");
    if(foto === ''){
        var ruta="assets/img/profile-pic.jpg";
        img.setAttribute("src",ruta);
    }else{
        img.setAttribute("src",foto);
    }

    $('#exampleModalLongTitleShow').html("Datos del Alumno: ".bold().concat(nombres, " ", apellidos));
}

function datosModalModificarALU(tipoIdentificacion, identificacion, nacionalidad, genero, nombres, apellidos, fecha_nacimiento, nombre_curso, direccion, numero_hermanos, lugar_ocupa,
        carnet_discapacidad, discapacidad, tipo_discapacidad, historia_clinica, apellidosFa, nombresfa, tipo_identificacionFa, cue, celular, ocupacion, lugardeTrabajo, correo, nacionalidad, identificacion,
        nombreRef, apellidoRef, parentescoRef, celularRef, telefonoRef, correo_personal, celularAl)
{
    
    $('#modEditAluTipoIdentificacion').val(tipoIdentificacion);
    $('#modEditAludentificacion').val(identificacion);
    $('#modEditAluPais').val(nacionalidad);
    if (genero === 'M')
        $('#modEditALuGenero').val('Masculino');
    else
        $('#modEditALuGenero').val('Femenino');
    $('#modEditrAluFoto').val("no existe aun");
    $('#modEditAluNombres').val(nombres);
    $('#modEditAluApellidos').val(apellidos);
    $('#modEditAluFechaNacimiento').val(fecha_nacimiento);
    // $('#modVerAluTelefono').html("no asignado");
    $('#modEditAluCurso').val(nombre_curso);

    $('#modEditAluDireccion').val(direccion);
    $('#modEditAluHermanos').val(numero_hermanos);
    $('#modEditAluLugarHermanos').val(lugar_ocupa);
    $('#modEditAluCelular').val(celularAl);
    $('#modEditAluEmail').val(correo_personal);
    $('#modEditAluCarnetDiscapacidad').val(carnet_discapacidad);

    $('#modEditAluDiscapacidad').val(discapacidad);
    $('#modEditAluTipoDiscapacidad').val(tipo_discapacidad);
    $('#modEditAluHistClinica').val(historia_clinica);


    

    //datos del familiar 
    $('#modEditAluTipoidentificacionR').val(identificacion);
    $('#modEditidentificacionR').val(tipo_identificacionFa);
    $('#modEditnombresR').val(nombresfa);
    $('#modEditapellidosR').val(apellidosFa);

    $('#modEdidCueR').val(cue);
    $('#modEditCelularR').val(celular);
    $('#modEditOcupacion').val(ocupacion);
    $('#modEditLgrTrabajo').val(lugardeTrabajo);
    $('#modEditemailR').val(correo);
    $('#modEditNacionalidadR').val(nacionalidad);

    //datos del contacto de referencia 
    $('#modEditNonbresRef').val(nombreRef);
    $('#modEditApellidoRef').val(apellidoRef);
    $('#modEditParentescoRef').val(parentescoRef);
    $('#modEditcelularRef').val(celularRef);
    $('#modEditTelefonoRef').val(telefonoRef)


    $('#exampleModalLongTitleShow').html("Datos del Alumno: ".bold().concat(nombres, " ", apellidos));

    valeditcorreo = true;
    valeditidentificacion = true;
}

$(document).ready(function () {

    var b = true, valuser = true, valcorreo = true, validentificacion = true;
    var errorcedula = '';

    $("#usuario").keyup(function () {
        buscarUsuario($("#usuario").val(), "errorusuario");
    });

    $("#usuario").focusout(function () {
        buscarUsuario($("#usuario").val(), "errorusuario");
    });

    $("#email").keyup(function () {
        buscarCorreo($("#email").val(), "erroremail");
    });

    $("#email").focusout(function () {
        buscarCorreo($("#email").val(), "erroremail");
    });

    $("#identificacion").keyup(function () {
        buscarIdentificacion($("#identificacion").val(), "erroridentificaciion");
    });

    $("#identificacion").focusout(function () {
        buscarIdentificacion($("#identificacion").val(), "erroridentificaciion");
    });

    $("#modEditEmail").keyup(function () {
        buscarCorreoModificar($("#modEditEmail").val(), "modEditErroremail", $("#modEditAuxEmail").val());
    });

    $("#modEditEmail").focusout(function () {
        buscarCorreoModificar($("#modEditEmail").val(), "modEditErroremail", $("#modEditAuxEmail").val());
    });

    $("#modEditIdentificacion").keyup(function () {
        buscarIdentificacionModificar($("#modEditIdentificacion").val(), "modEditErroridentificaciion", $("#modEditAuxIdentificacion").val());
    });

    $("#modEditIdentificacion").focusout(function () {
        buscarIdentificacionModificar($("#modEditIdentificacion").val(), "modEditErroridentificaciion", $("#modEditAuxIdentificacion").val());
    });

    $('#btnModificar').click(function () {

        buscarIdentificacionModificar($("#modEditIdentificacion").val(), "modEditErroridentificaciion", $("#modEditAuxIdentificacion").val());
        buscarCorreoModificar($("#modEditEmail").val(), "modEditErroremail", $("#modEditAuxEmail").val());

        if ($('#modEditPais').val() === '1')
        {
            errorcedula = validarIdentificacion($('#modEditIdentificacion').val());
            if (errorcedula !== 'ok')
            {
                alert(errorcedula);
                return false;
            }
        }

        if (valcorreo && validentificacion)
        {
            //alert("Correcto");
            return true;
        } else
        {
            alert("Corrija los errores");
            return false;
        }
    });


    $('#btnRegistrar').click(function () {

        if ($('#pais').val() === '1')
        {
            errorcedula = validarIdentificacion($('#identificacion').val());
            if (errorcedula !== 'ok')
            {
                alert(errorcedula);
                return false;
            }
        }

        if (valuser && valcorreo && validentificacion)
        {
            //alert("Correcto");
            return true;
        } else
        {
            alert("Corrija los errores");
            return false;
        }
    });

    function buscarUsuario(nombreUsuario, span) {

        $.ajax({
            async: true,
            type: "GET",
            url: '/WebCarmelina/srvUsuarios?accion=buscarUsuario&username=' + nombreUsuario + '',
            success: function (responseText)
            {
                if (responseText === 'ok')
                {
                    valuser = false;
                    $("#" + span + "").text("Este usuario ya existe");

                } else
                {
                    valuser = true;
                    $("#" + span + "").text("");
                }
            }
        });
    }

    function buscarCorreo(correo, span) {

        $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvLogin?accion=buscarCorreo&correo=' + correo + '',
            success: function (responseText)
            {
                if (responseText === 'ok')
                {
                    valcorreo = false;
                    valeditcorreo = false;
                    $("#" + span + "").text("Este e-mail ya existe en el sistema");

                } else
                {
                    valcorreo = true;
                    valeditcorreo = true;
                    $("#" + span + "").text("");
                }
            }
        });
    }

    function buscarIdentificacion(ident, span) {

        $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvUsuarios?accion=buscarIdentificacion&ident=' + ident + '',
            success: function (responseText)
            {
                if (responseText === 'ok')
                {
                    validentificacion = false;
                    valeditidentificacion = false;
                    $("#" + span + "").text("Ya existe en el sistema");

                } else
                {
                    validentificacion = true;
                    valeditidentificacion = true;
                    $("#" + span + "").text("");
                }
            }
        });
    }

    function validarIdentificacion(identificacion) {
        return validarCedulaEC(identificacion);
    }

    function buscarIdentificacionModificar(ident, span, aux) {

        if (ident === aux)
        {
            validentificacion = true;
        } else
        {
            $.ajax({
                async: true,
                type: "GET",
                url: '/UECGV/srvUsuarios?accion=buscarIdentificacion&ident=' + ident + '',
                success: function (responseText)
                {
                    if (responseText === 'ok')
                    {
                        validentificacion = false;
                        $("#" + span + "").text("Ya existe en el sistema");

                    } else
                    {
                        validentificacion = true;
                        $("#" + span + "").text("");
                    }
                }
            });
        }
    }

    function buscarCorreoModificar(correo, span, aux) {

        if (correo === aux)
        {
            valcorreo = true;
        } else
        {
            $.ajax({
                async: true,
                type: "GET",
                url: '/UECGV/srvLogin?accion=buscarCorreo&correo=' + correo + '',
                success: function (responseText)
                {
                    if (responseText === 'ok')
                    {
                        valcorreo = false;
                        valeditcorreo = false;
                        $("#" + span + "").text("Este e-mail ya existe en el sistema");

                    } else
                    {
                        valcorreo = true;
                        valeditcorreo = true;
                        $("#" + span + "").text("");
                    }
                }
            });
        }
    }

    function validacionEmailUsuario(email) {
        if (email.length > 0)
        {
            if (!validarEmail(email))
            {
                $("#erroremail").text("Sintáxis incorrecta");
                b = false;
            } else
            {
                $("#erroremail").text(" ");
                b = true;
            }
        } else
        {
            $("#erroremail").text(" ");
            b = true;
        }
    }
    

});


var input = document.getElementById("regCrntDiscAlu");
var input2 = document.getElementById("regDiscapacidadAlu");


function discapacidad(elemento) {
  d = elemento.value;
  
  if(d === "1"){
    
    //input.style.display ="none";
    //input.label.values("00000");
        input.disabled = true;
        input2.disabled=true;
       input.value="nan";
       input2.value="NINGUNA";
    
  }else{
    input2.disabled=false;  
    input.disabled = false;
  }
    
  
}


function validarIdentificacion(){
    var ident = $('#regidentificacionAlu').val();
    if(ident.length >= 10){
      $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvValidacion?accion=validad_identificacionEstudiante&valor='+ident+'',
            success: function (responseText)
            {
                console.log(responseText);
                if (responseText === 'si')
                {
                     alert("Ya existe en la base de datos");
                     document.getElementById('btnRegistrarestudiante').disabled=true;
                     bandera = true;
                }else{
                    if(responseText === 'no'){
                       
                      document.getElementById('btnRegistrarestudiante').disabled=false;
                      bandera = false;
                    }
                }
            },
            error:function (data){
                console.log(data);
            } 
        });  
        
    }
}
