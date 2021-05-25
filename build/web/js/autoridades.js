$(document).ready(function () {
    //Listar autoridades
    $.ajax({
        type: 'POST',
        url: "srvAutoridades",
        cache: false,
        dataType: 'json',
        success: function (data) {
            var autoridad_1 = [], autoridad_2 = [], autoridad_3 = [], autoridad_4 = [];
            for(autoridad in data){
                if (parseInt(data[autoridad].jerarquia) === 1) {
                    autoridad_1.push(data[autoridad]);
                }
                if (parseInt(data[autoridad].jerarquia) === 2) {
                    autoridad_2.push(data[autoridad]);
                }
                if (parseInt(data[autoridad].jerarquia) === 3) {
                    autoridad_3.push(data[autoridad]);
                }
                if (parseInt(data[autoridad].jerarquia) === 4) {
                    autoridad_4.push(data[autoridad]);
                }
            }
            
            //Rector
            var autoridad = "";
            for (aut in autoridad_1) {
                autoridad += '<div class="col-xs-12 col-sm-6 col-md-4">' +
                        '<div class="tile-gallery">' +
                        '<img src="' + autoridad_1[aut].foto + '" alt="Default">' +
                        '<p><strong>' + autoridad_1[aut].nombre_cargo + '</strong></p>' +
                        '<p>' + autoridad_1[aut].nombres + ' ' + autoridad_1[aut].apellidos + '</p>' +
                        '<p><strong>Correo:</strong>&nbsp; ' + autoridad_1[aut].correo_institucional + '</p>' +
                        '</div>' +
                '</div>';
            }
            $(".autoridades").append(
                    $('<div />', {
                        "class": 'row',
                        html: autoridad,
                        style: "margin-bottom: 10px"
                        })
                    );
            $(".autoridades").append($('<div />', {"class": 'divider-general'}));
            //Vicerrector
            var autoridad = "";
            for (aut in autoridad_2) {
                autoridad += '<div class="col-xs-12 col-sm-6 col-md-4">' +
                        '<div class="tile-gallery">' +
                        '<img src="' + autoridad_2[aut].foto + '" alt="Default">' +
                        '<p><strong>' + autoridad_2[aut].nombre_cargo + '</strong></p>' +
                        '<p>' + autoridad_2[aut].nombres + ' ' + autoridad_2[aut].apellidos + '</p>' +
                        '<p><strong>Correo:</strong>&nbsp; ' + autoridad_2[aut].correo_institucional + '</p>' +
                        '</div>' +
                '</div>';
            }
            $(".autoridades").append(
                    $('<div />', {
                        "class": 'row',
                        html: autoridad,
                        style: "margin-bottom: 10px; margin-top: 10px"
                        })
                    );
            $(".autoridades").append($('<div />', {"class": 'divider-general'}));
            
            //Inspector General
            var autoridad = "";
            for (aut in autoridad_3) {
                autoridad += '<div class="col-xs-12 col-sm-6 col-md-4">' +
                        '<div class="tile-gallery">' +
                        '<img src="' + autoridad_3[aut].foto + '" alt="Default">' +
                        '<p><strong>' + autoridad_3[aut].nombre_cargo + '</strong></p>' +
                        '<p>' + autoridad_3[aut].nombres + ' ' + autoridad_3[aut].apellidos + '</p>' +
                        '<p><strong>Correo:</strong>&nbsp; ' + autoridad_3[aut].correo_institucional + '</p>' +
                        '</div>' +
                '</div>';
            }
            $(".autoridades").append(
                    $('<div />', {
                        "class": 'row',
                        html: autoridad,
                        style: "margin-bottom: 10px; margin-top: 10px"
                        })
                    );
            $(".autoridades").append($('<div />', {"class": 'divider-general'}));
            
            //Inspector
            var autoridad = "";
            for (aut in autoridad_4) {
                autoridad += '<div class="col-xs-12 col-sm-6 col-md-4">' +
                        '<div class="tile-gallery">' +
                        '<img src="' + autoridad_4[aut].foto + '" alt="Default">' +
                        '<p><strong>' + autoridad_4[aut].nombre_cargo + '</strong></p>' +
                        '<p>' + autoridad_4[aut].nombres + ' ' + autoridad_4[aut].apellidos + '</p>' +
                        '<p><strong>Correo:</strong>&nbsp; ' + autoridad_4[aut].correo_institucional + '</p>' +
                        '</div>' +
                '</div>';
            }
            $(".autoridades").append(
                    $('<div />', {
                        "class": 'row',
                        html: autoridad,
                        style: "margin-bottom: 10px; margin-top: 10px"
                        })
                    );
            $("#section-autoridades").removeClass("mb-200");
        },
        error: function (data) {
            console.log(data);
        }
    });
});
