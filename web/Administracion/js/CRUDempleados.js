/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Carlos Muñoz
 */
var bandera = false;
var editbandera = false;
var controlar = false;
var tienetutoria = false;
var respIdcurso = "";
var respParalelo = "";
function confirmarEliminacion(idPersona)
{
    alertify.confirm('Confirmación', '¿Está seguro que desea eliminar esta persona?', function () {
        window.location.href = '/UECGV/srvEmpleado?accion=eliminar_empleado&id=' + idPersona + '';
    }, function () {
        toastr.error("Eliminación Cancelada");
    }).set('labels', {ok: 'Si', cancel: 'No'});
}
function MostrarTxtRegistroTutoria(){
    var idcarg = $('#modRegEmpIdCargo').val();
    //alert(""+idcarg);
    var c="";
    if(idcarg == 5){
        bandera = false;
        $('#RegEmpIdCurso').val(c);
        $('#RegEmpIdCurso').change();
        controlar=true;
        $('#modRegEmpParalelo').val(c);
        $('#modRegEmpParalelo').change();
        controlar=true;
        document.getElementById("RegTituloTutorias").style.display="block";
        document.getElementById("RegtTutoria").style.display="block";
        
    }else{
        bandera = false;
        $('#RegEmpIdCurso').val(c);
        $('#RegEmpIdCurso').change();
        bandera = false;
        $('#modRegEmpParalelo').val(c);
        $('#modRegEmpParalelo').change();
        //controlar = true;
        document.getElementById("RegTituloTutorias").style.display="none";
        document.getElementById("RegtTutoria").style.display="none";
        
    }
}
function MostrarTxtActualizarTutoria(){
    var idcarg = $('#modEditEmpCargo').val();

    if(idcarg == 5){
       
        document.getElementById("modEditTituloTutorias").style.display="block";
        document.getElementById("modEditTutoria").style.display="block";
        
        
    }else{
        
        document.getElementById("modEditTituloTutorias").style.display="none";
        document.getElementById("modEditTutoria").style.display="none";
        
    }
}

function Remove(){
    var m="";
    $('#modEditEmpIdCurso').val(m);

    $('#modEditEmpIdCurso').change();
    
    $('#modEditEmpParalelo').val(m);
    $('#modEditEmpParalelo').change();
    document.getElementById('modEditEmpIdCurso').disabled=false; 
    document.getElementById('modEditEmpParalelo').disabled=false;
    document.getElementById('btnModificar').disabled=false;
}

function validarRegistroTutoresxCurso(){
    var idcurso = $("#RegEmpIdCurso").val();
    var paralelo = $("#modRegEmpParalelo").val();
    
    if(idcurso !== "" && paralelo !== ""){
        bandera = false;
        $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvTutores?accion=mostrar_tutores_filtro_curso&idCurso='+idcurso+ '&paralelo='+paralelo+'',
            success: function (responseText)
            {
                if (responseText !== "")
                {
                     alert("Ya existe un docente asignado a este curso");
                     $('#modRegIDCargoEmpleado').val(responseText);
                     var hj = $("#modRegIDCargoEmpleado").val();
                }else{
                     $('#modRegIDCargoEmpleado').val(responseText); 
                } 
            }
        });
    }else{
        
        if(idcurso == "" && paralelo !== "" ){
            //document.getElementById('btnRegistrar').disabled=true;
              bandera = true;
            
        }else{
            if(idcurso != "" && paralelo == ""){
              // document.getElementById('btnRegistrar').disabled=false;
              bandera = true;
               
            }
        }
        
      
    }
    if(bandera == true){
           document.getElementById('btnRegistrar').disabled=true;
    }else{
           document.getElementById('btnRegistrar').disabled=false; 
    }
}

function validarModificacionTutoresxCurso(){
    var validcurso = $("#modEditEmpIdCurso").val();
    var valparalelo = $("#modEditEmpParalelo").val();
    
    if(validcurso !== "" && valparalelo !== "" && tienetutoria === false){
        editbandera = false;
        $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvTutores?accion=mostrar_tutores_filtro_curso&idCurso='+validcurso+ '&paralelo='+valparalelo+'',
            success: function (responseText)
            {
                if (responseText !== "")
                {
                     alert("Ya existe un docente asignado a este curso");
                     $('#modEditIDCargoEmpleado').val(responseText);
                    // var hj = $("#modRegIDCargoEmpleado").val();
                }else{
                     $('#modEditIDCargoEmpleado').val(responseText); 
                } 
            }
        });
    }else{
       // alert(valparalelo);
        if(validcurso == "" && valparalelo !== "" ){
            document.getElementById('btnModificar').disabled=true;
              editbandera = true;
            
        }else{
            if(validcurso != "" && valparalelo == ""){
              document.getElementById('btnModificar').disabled=true;
              editbandera = true;
               
            }
        }
        
      
    }
    if(editbandera == true){
           document.getElementById('btnModificar').disabled=true;
    }else{
         document.getElementById('btnModificar').disabled=false; 
    }
}
function validar(){
    var ident = $('#modRegEmpIdentificacion').val();
    if(ident.length >= 10){
      $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvValidacion?accion=validar_identificacion&valor='+ident+'',
            success: function (responseText)
            {
                if (responseText === 'si')
                {
                     alert("Ya existe en la base de datos");
                     document.getElementById('btnRegistrar').disabled=true;
                     bandera = true;
                }else{
                    if(responseText === 'no'){
                      document.getElementById('btnRegistrar').disabled=false;
                      bandera = false;
                    }
                }
            }
        });  
    }
}
function validarCorreoPersonal(){
    var ident = $('#modRegEmpCorreoPersonal').val();
      $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvValidacion?accion=validar_correoPersonal&valor='+ident+'',
            success: function (responseText)
            {
                if (responseText === 'si')
                {
                     alert("Ya existe este correo en la base de datos");
                     document.getElementById('btnRegistrar').disabled=true;
                     bandera = true;
                }else{
                    if(bandera==false){
                       document.getElementById('btnRegistrar').disabled=false;
                       bandera = false;
                    }
                } 
            }
        });  
}

function EditvalidarIdentificacion(){
    var ident = $('#modEditEmpIdentificacion').val();
    if(ident.length >= 10){
      $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvValidacion?accion=validar_identificacion&valor='+ident+'',
            success: function (responseText)
            {
                if (responseText === 'si')
                {
                     alert("Ya existe en la base de datos");
                     document.getElementById('btnModificar').disabled=true;
                     editbandera = true;
                }else{
                    if(editbandera == false){
                        document.getElementById('btnModificar').disabled=false;
                        editbandera = false;
                    }
                }
            }
        });  
    }
}

function validarCorreoInstitucional(){
    var ident = $('#modRegEmpCorreoInstitucional').val();
      $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvValidacion?accion=validar_correoInstitucional&valor='+ident+'',
            success: function (responseText)
            {
                if (responseText === 'si')
                {
                     alert("Ya existe este correo en la base de datos");
                     document.getElementById('btnRegistrar').disabled=true;
                     bandera = true;
                }else{
                    if(bandera == false){
                      document.getElementById('btnRegistrar').disabled=false;
                      bandera = false;
                    }
                } 
            }
        });  
}
function EditValidarCorreoPersonal(){
    var ident = $('#modEditEmpEmail').val();
      $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvValidacion?accion=validar_correoPersonal&valor='+ident+'',
            success: function (responseText)
            {
                if (responseText === 'si')
                {
                     alert("Ya existe este correo en la base de datos");
                     document.getElementById('btnModificar').disabled=true;
                     editbandera=true;
                }else{
                    if(editbandera == false){
                       document.getElementById('btnModificar').disabled=false;
                       editbandera = false;
                    }
                } 
            }
        });  
}
function EditValidarCorreoInstitucional(){
    var ident = $('#modEditEmpEmailInstitucional').val();
      $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvValidacion?accion=validar_correoInstitucional&valor='+ident+'',
            success: function (responseText)
            {
                if (responseText === 'si')
                {
                     alert("Ya existe este correo en la base de datos");
                     document.getElementById('btnModificar').disabled=true;
                     editbandera = true;
                }else{
                    if(editbandera == false){
                      document.getElementById('btnModificar').disabled=false;
                      editbandera = false;
                    }
                } 
            }
        });  
}
function datosModalMostrar(tipoIdentificacion, identificacion, nacionalidad, genero, nombres, apellidos, celular, emailpersonal, telefono, contrato, 
escalafon, correoinstitucional,estadocivil,foto,fechanacimiento,direccion,canton,provincia,cargo,nombreCurso,paralelo,tipo)
{
    //alert(""+paralelo);
    $('#modVerTipoIdentificacion').html(tipoIdentificacion);
    $('#modVerIdentificacion').html(identificacion);
    $('#modVerPais').html(nacionalidad);
    $('#modVerEmail').html(emailpersonal);
    $('#modVerEmailInstitucional').html(correoinstitucional);
    $('#modVerEscalafon').html(escalafon);
    $('#modVerEstadoCivil').html(estadocivil);
    //$('#modVerFoto').html(foto);
    $('#modVerContrato').html(contrato);
    $('#modVerCelular').html(celular);
    $('#modVerCargo').html(cargo);
    
    if (genero === 'M')
        $('#modVerGenero').html('Masculino');
    else
        $('#modVerGenero').html('Femenino');

    $('#modVerNombres').html(nombres);
    $('#modVerApellidos').html(apellidos);
    
    $('#modVerCanton').html(canton);
    $('#modVerProvincia').html(provincia);
    
    $('#modVerTelefono').html(telefono);
    $('#modVerFechaNacimiento').html(fechanacimiento);
    $('#modVerDireccion').html(direccion);
    $('#modVerCelular').html(celular);
    //$('#modVerCelular').text(celular);
    $('#exampleModalLongTitleShow').html("Datos del Empleado: ".bold().concat(nombres, " ", apellidos));
    
    var img = document.getElementById("modVerEmpFotoPresentacion");
    if(foto === ''){
        var ruta="assets/img/profile-pic.jpg";
        img.setAttribute("src",ruta);
    }else{
        img.setAttribute("src",foto);
    }
    $('#modVerCurso').html("".bold().concat(nombreCurso," - ", tipo));
    $('#modVerParalelo').html(paralelo);
    if(cargo !== "DOCENTE"){
        document.getElementById("editTituloTutorias").style.display="none";
        document.getElementById("EditTutoria").style.display="none";
    }else{
        document.getElementById("editTituloTutorias").style.display="block";
        document.getElementById("EditTutoria").style.display="block";
    }
    
}

function datosModalModificar(idEmpleado,tipoIdentificacion, identificacion, nacionalidad, genero, nombres, apellidos, celular, emailpersonal, telefono, contrato, 
escalafon, correoinstitucional,estadocivil,foto,fechanacimiento,direccion,idcanton,provincia,idcargo, idcurso,paralelo)
{
    $("#modEditEmpIdEmpleado").val(idEmpleado);
    $("#modEditEmpTipoIdentificacion").val(tipoIdentificacion);
    $("#modEditEmpTipoIdentificacion").change();
    $('#modEditEmpIdentificacion').val(identificacion);
    $('#modEditEmpProvincia').val(provincia);
    $("#modEditEmpCanton").val(idcanton);
    $("#modEditEmpCanton").change();
    $("#modEditEmpGenero").val(genero);
    $("#modEditEmpGenero").change();
    $('#modEditEmpNombres').val(nombres);
    $('#modEditEmpApellidos').val(apellidos);
    $("#modEditEmpCargo").val(idcargo);
    $("#modEditEmpCargo").change();
    $('#modEditEmpEmail').val(emailpersonal);
    $('#modEditEmpEmailInstitucional').val(correoinstitucional);
    $('#modEditEmpTelefono').val(telefono);
    $('#modEditEmpNacionalidad').val(nacionalidad);
    $('#modEditEmpDireccion').val(direccion);
    $('#exampleModalEditLongTitleShow').html("Modificar Empleado: ".bold().concat(nombres, " ", apellidos));
    $("#modEditEmpEscalafon").val(escalafon);
    $("#modEditEmpEscalafon").change();
    $("#modEditEmpEstadoCivil").val(estadocivil);
    $("#modEditEmpEstadoCivil").change();
    $("#modEditEmpFechaNacimiento").val(fechanacimiento);
    $("#modEditEmpFechaNacimiento").change();
    $("#modEditEmpContrato").val(contrato);
    $("#modEditEmpContrato").change();
    $("#modEditEmpAuxFoto").val(foto);
    //$("#EditEmpFotoPresentacion");
    $("#modEditEmpCelular").val(celular);
    $("#modEditErroridentificaciion").text("");
    $("#modEditErroremail").text("");
    var idcarg = idcargo;
    
    var c="";
    if(idcarg == 5){
       // bandera = false;
        tienetutoria = true;
        $('#modEditEmpIdCurso').val(idcurso);
        $('#modEditEmpIdCurso').change();
        
        $('#modEditEmpParalelo').val(paralelo);
        $('#modEditEmpParalelo').change();
        
        document.getElementById("modEditTituloTutorias").style.display="block";
        document.getElementById("modEditTutoria").style.display="block";
        var cp = idcurso;
        //respParalelo = paralelo;
        //alert(idcurso);
        if(cp != 0){
            tienetutoria = true;
          document.getElementById('modEditEmpIdCurso').disabled=true; 
          document.getElementById('modEditEmpParalelo').disabled=true; 
        }else{
            tienetutoria = false;
          $('#modEditEmpIdCurso').val("");
          $('#modEditEmpIdCurso').change();
        
          $('#modEditEmpParalelo').val("");
          $('#modEditEmpParalelo').change();  
          document.getElementById('modEditEmpIdCurso').disabled=false; 
          document.getElementById('modEditEmpParalelo').disabled=false; 
        }
    }else{
        $('#modEditEmpIdCurso').val("");
          $('#modEditEmpIdCurso').change();
        
          $('#modEditEmpParalelo').val("");
          $('#modEditEmpParalelo').change(); 
        document.getElementById('modEditEmpIdCurso').disabled=false; 
        document.getElementById('modEditEmpParalelo').disabled=false;
    }
    $("#modEditEmpFoto").val("");
    document.getElementById('btnModificar').disabled=false;
    valeditcorreo = true;
    valeditidentificacion = true;
}

$(document).ready(function () {

    var b = true, valuser = true, valcorreo = true, validentificacion = true;
    var errorcedula = '';


 

    $("#modEditIdentificacion").focusout(function () {
        buscarIdentificacionModificar($("#modEditIdentificacion").val(), "modEditErroridentificaciion", $("#modEditAuxIdentificacion").val());
    });


    $('#modEditEmpFoto').change(function(){
       
     //var ruta = $("#modEditEmpFoto").val();
       var a = document.getElementById('modEditEmpFoto');
       if(a.value === "")
       {
        $("#editfileLabel").html("");
       // editfileLabel.innerHTML = "";
       }
       else
       {
        var theSplit = a.value.split('\\');
        $("#editfileLabel").html(""+theSplit[theSplit.length-1]);
        
       }
    });
    $('#btnModificar').click(function () {
        
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
    $('#modRegEmpIdCanton').change(function () {

        var idcanton=$("#modRegEmpIdCanton").val();
        
        $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvProvincia?accion=mostrar_provinciasporcanton&id=' +idcanton + '',
            success: function (responseText)
            {
                //alert(''+responseText);
                if (responseText !== '')
                {
                     $('#modRegEmpProvincia').val(responseText);                   
                } 
            }
        });
    });
    $('#modEditEmpCanton').change(function () {

        var idcanton=$("#modEditEmpCanton").val();
        
        $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvProvincia?accion=mostrar_provinciasporcanton&id=' +idcanton + '',
            success: function (responseText)
            {
                //alert(''+responseText);
                if (responseText !== '')
                {
                     $('#modEditEmpProvincia').val(responseText);                   
                } 
            }
        });
    });
    
    function buscarUsuario(nombreUsuario, span) {

        $.ajax({
            async: true,
            type: "GET",
            url: '/UECGV/srvEmpleados?accion=buscarUsuario&username=' + nombreUsuario + '',
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

    function buscarIdentificacion() {
        //var ident=$('#modRegEmpIdentificacion').val();
        alert("hola");
//        $.ajax({
//            async: true,
//            type: "GET",
//            url: '/UECGV/srvEmpleados?accion=buscarIdentificacion&ident=' + ident + '',
//            success: function (responseText)
//            {
//                if (responseText === 'ok')
//                {
//                    validentificacion = false;
//                    valeditidentificacion = false;
//                    $("#" + span + "").text("Ya existe en el sistema");
//
//                } else
//                {
//                    validentificacion = true;
//                    valeditidentificacion = true;
//                    $("#" + span + "").text("");
//                }
//            }
//        });
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
                url: '/UECGV/srvEmpleados?accion=buscarIdentificacion&ident=' + ident + '',
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

    

    
});

