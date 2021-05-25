$(document).ready(function () {
    $.ajax({
        type: 'POST',
        url: "srvEventoImagen",
        cache: false,
        data: {accion: "Eventos_Recientes"},
        dataType: 'json',
        success: function (data) {
            if (data.length > 0) {
                $("#eventReciente").removeAttr("hidden");
                var div_image = "";
                $.each(data, function(index, item){
                    div_image += '<article class="col-xs-12 col-sm-6 col-md-4">' +
                                 '<div class="thumbnail">' +
                                 '<img src="' +item.rutaimagen+ '" alt="IMG" class="img-responsive img-rounded">'+
                                 '<h3 class="text-center">'+item.nombre_evento+'</h3>' +
                                 '<span class="text-center"><strong><small>Desde '+item.feha_inicio + ' al ' + item.fecha_fin+ '</small></strong></span>'+
                                 '<p class="text-justify">' + item.descripcion + '</p>'+
                                 '</div></div></article>';
                });
                $("#events-row").append(div_image);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });

});