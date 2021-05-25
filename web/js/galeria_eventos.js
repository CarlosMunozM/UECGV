$(document).ready( function() {
    var modal = document.getElementById("myModal");
    var captionText = document.getElementById("caption");
    
    $.ajax({
        type: 'POST',
        url: "srvEventoImagen",
        cache: false,
        data: {accion: "Listar_Anio"},
        dataType: 'json',
        success: function (data) {
            if(data.length > 0){
                var option = "";
                $.each(data, function(index, item){
                    option += '<option value="'+item+'">'+item+'</option>';
                });
                $("#selectEvents").append(option);
            }
        },
        error: function(data){
            console.log(data);
        }
    });
    
    
    $("#btnMostrarEvents").click(function () {
        var anio = $("#selectEvents").val();
        if (anio !== "0") {
            $("#seccion_eventos").slideUp("slow");
            $.ajax({
                type: 'POST',
                url: "srvEventoImagen",
                cache: false,
                data: {accion: "Listar_Eventos", anio: anio},
                dataType: 'json',
                success: function (data) {
                    if (data.length > 0) {
                        var div_image = "";
                        $.each(data, function(index, item){
                            div_image += '<div class="col-xs-12 col-sm-6 col-md-4">'+
					'<div class="tile-gallery">'+
					'<img src="'+item.rutaimagen+'" alt="'+item.nombre_evento+'" '+
                                             'style="border-radius: 0%; max-width: 250px; max-height: 250px;">'+
						'<p class="text-center"><strong>'+item.nombre_evento+'</strong></p>' +
						'<span class="text-center"><strong><small>Desde '+item.feha_inicio + ' al ' + item.fecha_fin+ '</small></strong></span>'+
						'<div class="divider-general"></div>' +
                                                '<p class="text-justify">' +
                                                    item.descripcion +
                                                '</p></div></div>';
                        });
                        
                        $("#seccion_eventos").html("");
                        $("#dividir-sec").remove();
                        $("#seccion_eventos").append(
                        '<h2 class="text-center"><i class="fa fa-calendar-o"></i> &nbsp; Eventos Año '+anio+'</h2>'+
			'<div class="row">' + div_image + '</div>');
                
                        $("#seccion_eventos").after('<div id="dividir-sec" class="divider-general"></div>');
                        $("#seccion_eventos").slideDown("slow");
                        initEventsImg();
                    }
                },
                error: function (data) {
                    console.log(data);
                }
            });
            
        }
    });
    
    
    function initEventsImg(){
        //Eventos
        $("img").click(function(){
            var url = $(this).attr("src");
            modal.style.display = "block";
            $("#img01").attr("src", url);
            captionText.innerHTML = $(this).attr("alt");
        });

        $("#close-modal").click(function(){
          modal.style.display = "none";  
          $("#img01").attr("src", "");
        });
    }
});


