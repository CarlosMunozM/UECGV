$(document).ready( function() {
    var estudiante;
    var estFamiliar1;
    var estFamiliar2;
    var estReferencia;
    var padre;
    var madre;
    var referencia;
    var file_foto;
    var file_domicilio;
    
    $("#btnCancelar").attr("disabled", true);
    $("#btnGuardar").attr("disabled", true);
    
    $("#btn-buscarEst").click(function(){
        desabilitarInput();
        toastr.options = {
            "positionClass": "toast-bottom-right",
            "showMethod": "show",
            "hideMethod": "hide"
        };
        $.ajax({
            type: 'POST',
            url: "srvActualizarDatos",
            cache: false,
            data: {id: $("#txtIdentificacion").val(), modo: "buscar_estudiante"},
            dataType: 'json',
            success: function (data) {
                if(data.id_estudiante !== 0){
                    estudiante = data.id_estudiante;
                    $("#idEstd").val(estudiante);
                    //Cursos
                    listarCursos("listar_cursos", data.curso_educativo.id_curso);
                    listarConvivencia("listar_convivencia", data.id_estudiante);
                    $("#tipoIdentificacionAlu").val(data.tipo_identificacion);
                    $("#regidentificacionAlu").val(data.identificacion);
                    $("#regpaisAlu").val(data.nacionalidad);
                    $("#reggeneroAlu").val(data.genero);
                    $("#regImgAlum").attr("src",data.foto);
                    var fecha = new Date(data.fecha_nacimiento);
                    var mes = fecha.getMonth() + 1;
                    fecha = fecha.getFullYear() + "-" +  ('0' + mes).slice(-2).toString() + "-"  + ('0' + fecha.getDate()).slice(-2).toString();
                    $("#regfechaNacimientoAlu").val(fecha);
                    $("#regnombresAlu").val(data.nombres);
                    $("#regapellidosAlu").val(data.apellidos);
                    $("#emailAlu").val(data.correo);
                    $("#regCelularAlu").val(data.celular);
                    $("#regDireccionAlu").val(data.direccion);
                    $("#regHermanoAlu").val(data.numero_hermanos);
                    $("#regLugarHnosAlu").val(data.lugar_ocupa);
                    $("#fotoDomicilioAlu").val(data.foto_domicilio);
                    $("#regTipoDiscAlu").val(data.tipo_discapacidad);
                    if(data.tipo_discapacidad !== "NINGUNA"){
                        $(".discapacidad-seccion").slideDown("slow");
                        $("#regDiscapacidadAlu").val(data.discapacidad);
                        $("#regCrntDiscAlu").val(data.carnet_discapacidad);
                        $("#regHistClinicaAlu").val(data.historia_clinica);
                    }else{
                        $(".discapacidad-seccion").slideUp("slow");
                        $("#regDiscapacidadAlu").val("");
                        $("#regCrntDiscAlu").val("");
                        $("#regHistClinicaAlu").val("");
                    }
                    
                    //Datos Padres
                    mostrarPadres(estudiante, "buscar_familiar");
                    //Datos Referencia
                    mostrarReferencia(estudiante, "buscar_referencia");
                    
                    $("#section-datos").removeClass("mb-200");
                    $(".form-datos").slideDown("slow");
                }else{
                    toastr.error("Registro no existente.");
                    toastr.error("Contacte con la Administración.");
                }
            },
            error: function (data) {
                console.log(data);
            }
        });
    });
   
    $("#btnCancelar").click(function(){
        $("#regFotoAlu").attr("disabled", true);
        
        $("#tipoIdentificacionAlu").attr("disabled", true);
        $("#regidentificacionAlu").attr("readonly", true);
        $("#regpaisAlu").attr("readonly", true);
        $("#reggeneroAlu").attr("disabled", true);
        $("#regfechaNacimientoAlu").attr("readonly", true);
        $("#regnombresAlu").attr("readonly", true);
        $("#regapellidosAlu").attr("readonly", true);
        $("#emailAlu").attr("readonly", true);
        $("#regCelularAlu").attr("readonly", true);
        $("#regCursoAlu").attr("disabled", true);
        $("#regDireccionAlu").attr("readonly", true);
        $("#regHermanoAlu").attr("readonly", true);
        $("#regLugarHnosAlu").attr("readonly", true);
        $("#fotoDomicilioAlu").attr("disabled", true);
        $("#regTipoDiscAlu").attr("disabled", true);
        $("#regDiscapacidadAlu").attr("readonly", true);
        $("#regHistClinicaAlu").attr("readonly", true);
         $("#regCrntDiscAlu").attr("readonly", true);
        
        //Datos padres
        $("#regtipoIdentificacionRep").attr("disabled", true);
        $("#regIdentificacionRep").attr("readonly", true);
        $("#regNacionalidadRep").attr("readonly", true);
        $("#regCueRep").attr("readonly", true);
        $("#regnombresRep").attr("readonly", true);
        $("#regApellidosRep").attr("readonly", true);
        $("#regOcupacionRep").attr("readonly", true);
        $("#regTrabajoRep").attr("readonly", true);
        $("#regEmailRep").attr("readonly", true);
        $("#regCelularRep").attr("readonly", true);
        
        $("#regtipoIdentificacionMad").attr("disabled", true);
        $("#regIdentificacionMad").attr("readonly", true);
        $("#regNacionalidadMad").attr("readonly", true);
        $("#regCueMad").attr("readonly", true);
        $("#regnombresMad").attr("readonly", true);
        $("#regApellidosMad").attr("readonly", true);
        $("#regOcupacionMad").attr("readonly", true);
        $("#regTrabajoMad").attr("readonly", true);
        $("#regEmailMad").attr("readonly", true);
        $("#regCelularMad").attr("readonly", true);
        
        //Referencia
        $("#modEditParentescoRef").attr("readonly", true);
        $("#regtipoIdentificacionRef").attr("disabled", true);
        $("#regIdentificacionRef").attr("readonly", true);
        $("#modEditNonbresRef").attr("readonly", true);
        $("#modEditApellidoRef").attr("readonly", true);
        $("#modEditTelefonoRef").attr("readonly", true);
        $("#modEditcelularRef").attr("readonly", true);
        
        $("#btnCancelar").attr("disabled", true);
        $("#btnGuardar").attr("disabled", true);
    });
    
    $("#btnModificar").click(function () {
        $("#regFotoAlu").attr("disabled", false);
        
        //$("#tipoIdentificacionAlu").attr("disabled", false);
        $("#regidentificacionAlu").attr("readonly", false);
        $("#regpaisAlu").attr("readonly", false);
        $("#reggeneroAlu").attr("disabled", false);
        $("#regfechaNacimientoAlu").attr("readonly", false);
        $("#regnombresAlu").attr("readonly", false);
        $("#regapellidosAlu").attr("readonly", false);
        $("#emailAlu").attr("readonly", false);
        $("#regCelularAlu").attr("readonly", false);
        $("#regCursoAlu").attr("disabled", false);
        $("#regDireccionAlu").attr("readonly", false);
        $("#regHermanoAlu").attr("readonly", false);
        $("#regLugarHnosAlu").attr("readonly", false);
        $("#fotoDomicilioAlu").attr("disabled", false);
        $("#regTipoDiscAlu").attr("disabled", false);
        $("#regDiscapacidadAlu").attr("readonly", false);
        $("#regHistClinicaAlu").attr("readonly", false);
         $("#regCrntDiscAlu").attr("readonly", false);
        //$("regCrntDiscAlu").val(data.celular);
        
        //Datos padres
        $("#regtipoIdentificacionRep").attr("disabled", false);
        $("#regIdentificacionRep").attr("readonly", false);
        $("#regNacionalidadRep").attr("readonly", false);
        $("#regCueRep").attr("readonly", false);
        $("#regnombresRep").attr("readonly", false);
        $("#regApellidosRep").attr("readonly", false);
        $("#regOcupacionRep").attr("readonly", false);
        $("#regTrabajoRep").attr("readonly", false);
        $("#regEmailRep").attr("readonly", false);
        $("#regCelularRep").attr("readonly", false);
        
        $("#regtipoIdentificacionMad").attr("disabled", false);
        $("#regIdentificacionMad").attr("readonly", false);
        $("#regNacionalidadMad").attr("readonly", false);
        $("#regCueMad").attr("readonly", false);
        $("#regnombresMad").attr("readonly", false);
        $("#regApellidosMad").attr("readonly", false);
        $("#regOcupacionMad").attr("readonly", false);
        $("#regTrabajoMad").attr("readonly", false);
        $("#regEmailMad").attr("readonly", false);
        $("#regCelularMad").attr("readonly", false);
        
        //Referencia
        $("#modEditParentescoRef").attr("readonly", false);
        $("#regtipoIdentificacionRef").attr("disabled", false);
        $("#regIdentificacionRef").attr("readonly", false);
        $("#modEditNonbresRef").attr("readonly", false);
        $("#modEditApellidoRef").attr("readonly", false);
        $("#modEditTelefonoRef").attr("readonly", false);
        $("#modEditcelularRef").attr("readonly", false);
        
        $("#btnCancelar").attr("disabled", false);
        $("#btnGuardar").attr("disabled", false);
        
        //Validacion
        if($("#tipoIdentificacionAlu").val() === 'Cédula'){
            $("#regidentificacionAlu").attr("maxlength", 10);
        }else{
            $("#regidentificacionAlu").attr("maxlength", 15);
        }
        
        if($("#regtipoIdentificacionRep").val() === 'Cédula'){
            $("#regIdentificacionRep").attr("maxlength", 10);
        }else{
            $("#regIdentificacionRep").attr("maxlength", 15);
        }
       
        if($("#regtipoIdentificacionMad").val() === 'Cédula'){
            $("#regIdentificacionMad").attr("maxlength", 10);
        }else{
            $("#regIdentificacionMad").attr("maxlength", 15);
        }
        
        if($("#modEditParentescoRef").val() === 'Cédula'){
            $("#regtipoIdentificacionRef").attr("maxlength", 10);
        }else{
            $("#regtipoIdentificacionRef").attr("maxlength", 15);
        }
    });
    
    function mostrarPadres(id, modo){
        $.ajax({
            type: 'POST',
            url: "srvActualizarDatos",
            cache: false,
            data: {id: id, modo: modo},
            dataType: 'json',
            success: function (data) {
                //Datos Padres
                $.each(data, function (index, item) {
                    if (item.parentesco === "PADRE") {
                        estFamiliar1 = (item.estdFamiliar === 0 || item.estdFamiliar === undefined) ? 0: item.estdFamiliar;
                        padre = (item.familiar.id_familiar === 0 || item.familiar.id_familiar === undefined) ? 0: item.familiar.id_familiar;
                        $("#regtipoIdentificacionRep").val(item.familiar.tipo_identificacion);
                        $("#regIdentificacionRep").val(item.familiar.identificacion);
                        $("#regNacionalidadRep").val(item.familiar.nacionalidad);
                        $("#regCueRep").val(item.familiar.cue);
                        $("#regnombresRep").val(item.familiar.nombres);
                        $("#regApellidosRep").val(item.familiar.apellidos);
                        $("#regOcupacionRep").val(item.familiar.ocupacion);
                        $("#regTrabajoRep").val(item.familiar.lugar_trabajo);
                        $("#regEmailRep").val(item.familiar.correo);
                        $("#regCelularRep").val(item.familiar.celular);
                    } else {
                        estFamiliar2 = (item.estdFamiliar === 0 || item.estdFamiliar === undefined) ? 0: item.estdFamiliar;
                        madre = (item.familiar.id_familiar === 0 || item.familiar.id_familiar === undefined) ? 0: item.familiar.id_familiar;
                        $("#regtipoIdentificacionMad").val(item.familiar.tipo_identificacion);
                        $("#regIdentificacionMad").val(item.familiar.identificacion);
                        $("#regNacionalidadMad").val(item.familiar.nacionalidad);
                        $("#regCueMad").val(item.familiar.cue);
                        $("#regnombresMad").val(item.familiar.nombres);
                        $("#regApellidosMad").val(item.familiar.apellidos);
                        $("#regOcupacionMad").val(item.familiar.ocupacion);
                        $("#regTrabajoMad").val(item.familiar.lugar_trabajo);
                        $("#regEmailMad").val(item.familiar.correo);
                        $("#regCelularMad").val(item.familiar.celular);
                    }
                });
            },
            error: function (data) {
                console.log(data);
            }
        });
    }
    
    function mostrarReferencia(id, modo){
        $.ajax({
            type: 'POST',
            url: "srvActualizarDatos",
            cache: false,
            data: {id: id, modo: modo},
            dataType: 'json',
            success: function (data) {
                estReferencia = (data.estdReferencia === 0 || data.estdReferencia === undefined) ? 0: data.estdReferencia;
                referencia = data.referencia.id_referencia;
                $("#modEditParentescoRef").val(data.referencia.telefono);
                $("#regtipoIdentificacionRef").val(data.referencia.tipo_identificacion);
                $("#regIdentificacionRef").val(data.referencia.identificacion);
                $("#modEditNonbresRef").val(data.referencia.nombres);
                $("#modEditApellidoRef").val(data.referencia.apellidos);
                $("#modEditTelefonoRef").val(data.referencia.celular);
                $("#modEditcelularRef").val(data.parentesco);
            },
            error: function (data) {
                console.log(data);
            }
        });
    }
    
    function listarCursos(modo, check_curso){
        $.ajax({
            type: 'POST',
            url: "srvActualizarDatos",
            cache: false,
            data: {modo: modo},
            dataType: 'json',
            success: function (data) {
                $("#regCursoAlu").html("<option></option>");
                $.each(data, function(index, item){
                    var option = $('<option />', {
                                    value: item.id_curso,
                                    text: item.nombre_curso + " " + item.tipo
                                });
                    $("#regCursoAlu").append(option);
                });
                $("#regCursoAlu").val(check_curso);
            },
            error: function (data) {
                console.log(data);
            }
        });
    }
    
    function listarConvivencia(modo, id){
        $.ajax({
            type: 'POST',
            url: "srvActualizarDatos",
            cache: false,
            data: {modo: modo},
            dataType: 'json',
            success: function (data) {
                var arrayItem = [];
                $.ajax({
                    type: 'POST',
                    url: "srvActualizarDatos",
                    cache: false,
                    data: {modo: "mostrar_convivencia", id: id},
                    dataType: 'json',
                    success: function (reponse) {
                        $.each(reponse, function(index, item){
                            arrayItem.push(item.convivencia.id_convivencia);
                        });
                        $("#check-convivencia").html("");
                        $.each(data, function (index, item) {
                            var input = "";
                            if ($.inArray(item.id_convivencia, arrayItem) >= 0) {
                                input = '<input  name="'+item.familiar+'" type="checkbox" value="' + item.id_convivencia + '" checked="checked">' + item.familiar + '&nbsp;&nbsp;&nbsp;</label>';
                            } else {
                                input = '<input  name="'+item.familiar+'" type="checkbox" value="' + item.id_convivencia + '" >' + item.familiar + '&nbsp;&nbsp;&nbsp;</label>';
                            }
                            var option = $('<label />', {
                                html: input
                            });
                            $("#check-convivencia").append(option);
                        });
                    },
                    error: function (reponse) {
                        console.log(reponse);
                        $("#check-convivencia").html("");
                        $.each(data, function (index, item) {
                            var input = '<input name="'+item.familiar+'" type="checkbox" value="' + item.id_convivencia + '" >' + item.familiar + '&nbsp;&nbsp;&nbsp;</label>';
                            var option = $('<label />', {
                                html: input
                            });
                            $("#check-convivencia").append(option);
                        });
                    }
                });
            },
            error: function (data) {
                console.log(data);
            }
        });
    }
    
    function desabilitarInput(){
        $("#regFotoAlu").attr("disabled", true);
        
        $("#tipoIdentificacionAlu").attr("disabled", true);
        $("#regidentificacionAlu").attr("readonly", true);
        $("#regpaisAlu").attr("readonly", true);
        $("#reggeneroAlu").attr("disabled", true);
        $("#regfechaNacimientoAlu").attr("readonly", true);
        $("#regnombresAlu").attr("readonly", true);
        $("#regapellidosAlu").attr("readonly", true);
        $("#emailAlu").attr("readonly", true);
        $("#regCelularAlu").attr("readonly", true);
        $("#regCursoAlu").attr("disabled", true);
        $("#regDireccionAlu").attr("readonly", true);
        $("#regHermanoAlu").attr("readonly", true);
        $("#regLugarHnosAlu").attr("readonly", true);
        $("#fotoDomicilioAlu").attr("disabled", true);
        $("#regTipoDiscAlu").attr("disabled", true);
        $("#regDiscapacidadAlu").attr("readonly", true);
        $("#regHistClinicaAlu").attr("readonly", true);
         $("#regCrntDiscAlu").attr("readonly", true);
        
        //Datos padres
        $("#regtipoIdentificacionRep").attr("disabled", true);
        $("#regIdentificacionRep").attr("readonly", true);
        $("#regNacionalidadRep").attr("readonly", true);
        $("#regCueRep").attr("readonly", true);
        $("#regnombresRep").attr("readonly", true);
        $("#regApellidosRep").attr("readonly", true);
        $("#regOcupacionRep").attr("readonly", true);
        $("#regTrabajoRep").attr("readonly", true);
        $("#regEmailRep").attr("readonly", true);
        $("#regCelularRep").attr("readonly", true);
        
        $("#regtipoIdentificacionMad").attr("disabled", true);
        $("#regIdentificacionMad").attr("readonly", true);
        $("#regNacionalidadMad").attr("readonly", true);
        $("#regCueMad").attr("readonly", true);
        $("#regnombresMad").attr("readonly", true);
        $("#regApellidosMad").attr("readonly", true);
        $("#regOcupacionMad").attr("readonly", true);
        $("#regTrabajoMad").attr("readonly", true);
        $("#regEmailMad").attr("readonly", true);
        $("#regCelularMad").attr("readonly", true);
        
        //Referencia
        $("#modEditParentescoRef").attr("readonly", true);
        $("#regtipoIdentificacionRef").attr("disabled", true);
        $("#regIdentificacionRef").attr("readonly", true);
        $("#modEditNonbresRef").attr("readonly", true);
        $("#modEditApellidoRef").attr("readonly", true);
        $("#modEditTelefonoRef").attr("readonly", true);
        $("#modEditcelularRef").attr("readonly", true);
        
        $("#btnCancelar").attr("disabled", true);
        $("#btnGuardar").attr("disabled", true);
    }
    
    
    $("#regFotoAlu").change(function(){
        var reader = new FileReader();
        reader.onload = function(e) {
          $('#regImgAlum').attr('src', e.target.result);
        };
        reader.readAsDataURL($(this).get(0).files[0]);
        file_foto = $(this).get(0).files[0];
    });
    
    /*$("#regFotoAlu").change(function () {
        console.log(this.files[0]);
    });*/
    
    $("#btnGuardar").click(function(e){
        toastr.options = {
                        "positionClass": "toast-bottom-right",
                        "showMethod": "show",
                        "hideMethod": "hide"
                    };
        
        if($("#tipoIdentificacionAlu").val() !== "" && $("#regidentificacionAlu").val() !== "" && $("#regpaisAlu").val() !== "" && 
            $("#reggeneroAlu").val() !== "" && $("#regfechaNacimientoAlu") !== "" && $("#regnombresAlu").val() !== "" && $("#regapellidosAlu").val() !== "" &&
             $("#regCursoAlu").val() !== "" && $("#regDireccionAlu").val() !== "" &&
            $("#regHermanoAlu").val() !== "" && $("#regLugarHnosAlu").val() !== "" && $("#regTipoDiscAlu").val() !== "" &&
            $("#regtipoIdentificacionRep").val() !== "" && $("#regIdentificacionRep").val() !== "" && $("#regNacionalidadRep").val() !== "" && $("#regnombresRep").val() !== "" &&
            $("#regApellidosRep").val() !== "" && $("#regOcupacionRep").val() !== "" && $("#regTrabajoRep").val() !== "" && $("#regtipoIdentificacionMad").val() !== "" &&
            $("#regIdentificacionMad").val() !== "" && $("#regNacionalidadMad").val() !== "" && $("#regnombresMad").val() !== "" && $("#regApellidosMad").val() !== "" &&
            $("#regOcupacionMad").val() !== "" && $("#regTrabajoMad").val() !== "" && $("#modEditParentescoRef").val() !== "" && $("#modEditNonbresRef").val() !== "" &&
            $("#modEditApellidoRef").val() !== "" && $("#modEditcelularRef").val() !== ""){
            
            $("#btnGuardar").attr("disabled", true);
            $("#btnGuardar").text("Guardando..");
            
            var datos = $("#formEstudiante").serializeArray();
            datos.push({"name": "id_estudiante", "value": estudiante});
            datos.push({"name": "id_padre", "value": padre});
            datos.push({"name": "id_madre", "value": madre});
            datos.push({"name": "id_familiar1", "value": estFamiliar1});
            datos.push({"name": "id_familiar2", "value": estFamiliar2});
            datos.push({"name": "id_referencia", "value": referencia});
            datos.push({"name": "id_estdReferencia", "value": estReferencia});
            datos.push({"name": "txtRegFotoDomicilio", "value": $("#fotoDomicilioAlu").val()});
            datos.push({"name": "regFotoAlu", "value": $("#regFotoAlu").val()});

            datos_form = {};
            $.each(datos, function(index, item){
               datos_form[item.name] = item.value;
            });
            
            $.ajax({
                type: 'POST',
                url: "srvActualizarDatos",
                cache: false,
                data: {modo: "actualizar_datos", datos: JSON.stringify(datos_form)},
                dataType: 'json',
                success: function (data) {
                    if(data){
                        ///Actualizar Foto
                        var form = $("#formEstudiante")[0];
                        var data_form = new FormData(form);
                        $.ajax({
                            type: "POST",
                            enctype: 'multipart/form-data',
                            data: data_form,
                            url: '/UECGV/srvSubirImagen',
                            processData: false, // Important!
                            contentType: false,
                            cache: false,
                            success: function (response){
                                if(response){
                                    toastr.success("Datos Actualizados");
                                    $("#section-datos").addClass("mb-200");
                                    $(".form-datos").slideUp("slow");
                                    $("#btnGuardar").attr("disabled", false);
                                    $("#btnGuardar").text("Guardar");
                                    $("#fotoDomicilioAlu").val("");
                                    $("#regFotoAlu").val("");
                                }else{
                                    toastr.success("Datos Actualizados");
                                    toastr.error("Error Actualización de Imagen");
                                    $("#btnGuardar").attr("disabled", false);
                                    $("#btnGuardar").text("Guardar");
                                }
                            }
                        });                        
                        
                    }else{
                        toastr.error("Error de Actualización");
                    }
                },
                error: function(data){
                    console.log(data);
                }
            });
        }else{
            toastr.error("Rellene todos los campos obligatorios");
        }
    });
    
    $("#regTipoDiscAlu").change(function(e){
        if(this.value !== 'NINGUNA'){
            $(".discapacidad-seccion").slideDown("slow");
            $("#regDiscapacidadAlu").val("");
            $("#regCrntDiscAlu").val("");
            $("#regHistClinicaAlu").val("");
        }else{
            if(this.value === "NINGUNA"){
                $(".discapacidad-seccion").slideUp("slow");
                $("#regDiscapacidadAlu").val("");
                $("#regCrntDiscAlu").val("");
                $("#regHistClinicaAlu").val("");
            }
        }
    });
    
    function convet_minuscula(e) {
        e.value = e.value.toLowerCase();
        alert(e.value);
    }
    
    $("#regHermanoAlu").on('input', function(){
        var cant = parseInt(this.value, 10);
        if(cant <= -1){
            this.value = 0;
        }
    });
    
     $("#regLugarHnosAlu").on('input', function(){
        var cant = parseInt(this.value, 10);
        if(cant <= -1){
            this.value = 0;
        }
    });
    
    $("#tipoIdentificacionAlu").change(function(){
        if(this.value === 'Cédula'){
            $("#regidentificacionAlu").attr("maxlength", 10);
            var input = $("#regidentificacionAlu").val();
            if(input.length >= 11){
                $("#regidentificacionAlu").val(input.substring(0, 9));
            }
        }else{
            $("#regidentificacionAlu").attr("maxlength", 15);
        };
    });
                    
    
    $("#regtipoIdentificacionRep").change(function(){
        if(this.value === 'Cédula'){
            $("#regIdentificacionRep").attr("maxlength", 10);
            var input = $("#regIdentificacionRep").val();
            if(input.length >= 11){
                $("#regIdentificacionRep").val(input.substring(0, 9));
            }
        }else{
            $("#regIdentificacionRep").attr("maxlength", 15);
        };
    });
   
   
    $("#regtipoIdentificacionMad").change(function(){
        if(this.value === 'Cédula'){
            $("#regIdentificacionMad").attr("maxlength", 10);
            var input = $("#regIdentificacionMad").val();
            if(input.length >= 11){
                $("#regIdentificacionMad").val(input.substring(0, 9));
            }
        }else{
            $("#regIdentificacionMad").attr("maxlength", 15);
        };
    });
        
    $("#modEditParentescoRef").change(function(){
        if(this.value === 'Cédula'){
            $("#regtipoIdentificacionRef").attr("maxlength", 10);
            var input = $("#regtipoIdentificacionRef").val();
            if(input.length >= 11){
                $("#regtipoIdentificacionRef").val(input.substring(0, 9));
            }
        }else{
            $("#regtipoIdentificacionRef").attr("maxlength", 15);
        };
    });
});