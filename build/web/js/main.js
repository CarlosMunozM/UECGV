$(document).ready(function () {
    $('#fr_facebook').hide();

    // Slider 
    $.ajax({
        type: 'POST',
        url: "srvImagenPresentacion",
        cache: false,
        data: {accion: "Listar_index"},
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
            var li_img = "";
            var div_img = "";
            
            if(data.length == 0){
                //Indicadores
                li_img = '<li data-target="#slider-ins" data-slide-to="0" class="active"></li>';
                ///Imagenes
                div_img = '<div class="item active">' +
                        '<img src="assets/img/Banner_bachillerato_asistente.png" alt="Default">' +
                        '<div class="carousel-caption"></div></div>';
            }else{
                $.each(data, function(index, item){
                    if(index == 0){
                        //Indicadores
                        li_img += '<li data-target="#slider-ins" data-slide-to="'+index+'" class="active"></li>';
                        ///Imagenes
                        div_img += '<div class="item active">' +
                                    '<img src="'+item.ruta+'" alt="Default">' +
                                    '<div class="carousel-caption"></div></div>';
                    }else{
                        //Indicadores
                        li_img += '<li data-target="#slider-ins" data-slide-to="'+index+'"></li>';
                        ///Imagenes
                        div_img += '<div class="item">' +
                                    '<img src="'+item.ruta+'" alt="Default">' +
                                    '<div class="carousel-caption"></div></div>';
                    }

                });    
            }
            
            $(".principal-slide").prepend(
                    $('<div />', {
                        "class": 'carousel-inner',
                        html: div_img,
                        role: "listbox"
                    })
            );
            
            $(".principal-slide").prepend(
                    $('<ol />', {
                        "class": 'carousel-indicators',
                        html: li_img
                    })
            );
    
            
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(data);
        }
    });
    
    /***** Mostrar/Ocultar menu mobil *****/
    $(".show-close-menu-m").click(function () {
        var menu = $(".navigation");
        if (menu.css("opacity") === "0") {
            menu.addClass("show-navigations");
            $(this).css({"color": "#FFCA28"});
        } else {
            menu.removeClass("show-navigations");
            $(this).css({"color": "#fff"});
        }
    });
    /***** Mostrar/ocultar mega-menu *****/
    $('.btm-mega-menu').click(function (e) {
        e.preventDefault();
        var megamenu = $(".mega-menu");
        if (megamenu.css("opacity") === "0") {
            megamenu.addClass("show-mega-menu");
            $('.btm-mega-menu').css({"color": "#FFA726"});
        } else {
            megamenu.removeClass("show-mega-menu");
            $('.btm-mega-menu').css({"color": "#fff"});
        }
    });
    /***** Abrir link en una nueva ventana *****/
    $(".open-link-newTab").click(function (e) {
        e.preventDefault();
        var HrefLink = $(this).attr("href");
        window.open(HrefLink, '_blank');
    });
    /***** Moverse el la pagina institucion con Scroll *****/
    $('.scroll-navigation-ins ul li').click(function () {
        var seccion = $(this).attr('data-href');
        $('body,html').animate({
            scrollTop: $(seccion).offset().top - 70
        }, 1000);
        return false;
    });
    /***** boton ir arriba *****/
    $('.btn-up').click(function () {
        $('body,html').animate({scrollTop: '0px'}, 300);
    });
    /*****Mostrar y ocultar boton ir arriba *****/
    $(window).scroll(function () {
        if ($(this).scrollTop() >= 500) {
            $('.btn-up').fadeIn();
        } else {
            $('.btn-up').fadeOut();
        }
    });

    $('#frm_icon_facebook').click(function (e) {
        $('#fr_facebook').toggleClass('show');
    });
    
    // Año en pie de página
    $('.fa-copyright').after("&nbsp; " + (new Date).getFullYear());
    
    /******** Mapa de OpenStreetMap ************/
    var map;
    var epsg4326 = new OpenLayers.Projection("EPSG:4326");  // transform from WGS 1984
    map = new OpenLayers.Map({
        div: "mapa-ins",
        displayProjection: epsg4326   // With this setting, lat and lon are displayed correctly in MousePosition and permanent anchor
    });
    map.addLayer(new OpenLayers.Layer.OSM());
    map.addControls([
        new OpenLayers.Control.MousePosition(),
        new OpenLayers.Control.ScaleLine(),
        new OpenLayers.Control.LayerSwitcher(),
        new OpenLayers.Control.Permalink({anchor: true})
    ]);
    var projectTo = map.getProjectionObject(); //The map projection (Spherical Mercator)
    var lonLat = new OpenLayers.LonLat(-79.43915, -1.115481).transform(epsg4326, projectTo);
    var zoom = 16;
    var markers = new OpenLayers.Layer.Markers("U.E.Milenio Carmelina Granja Villanueva");
    markers.addMarker(new OpenLayers.Marker(lonLat));
    map.addLayer(markers);
    map.setCenter(lonLat, zoom);
});