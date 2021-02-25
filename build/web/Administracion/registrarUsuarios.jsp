<%-- 
    Document   : registrarPersonas
    Created on : 15-ene-2019, 9:35:53
    Author     : Carlos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Registro - U.E.M Carmelina Granja Villanueva</title>

        <meta name="description" content="top menu &amp; navigation" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="../css/bootstrap.min.css" />
        <link rel="stylesheet" href="../css/font-awesome.min.css" />

        <!-- page specific plugin styles -->

        <!-- text fonts -->
        <link rel="stylesheet" href="../css/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="../css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

        <!--[if lte IE 9]>
                <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
        <![endif]-->
        <link rel="stylesheet" href="../css/ace-skins.min.css" />
        <link rel="stylesheet" href="../css/ace-rtl.min.css" />

        <script src="../js/ace-extra.min.js"></script>
    </head>

    <body class="no-skin">
        <%@include file="../EstructuraAplicacion/encabezado.jsp" %>

        <div class="main-container ace-save-state" id="main-container">
            <script type="text/javascript">
                try {
                    ace.settings.loadState('main-container')
                } catch (e) {
                }
            </script>

            <div id="sidebar" class="sidebar      h-sidebar                navbar-collapse collapse          ace-save-state">
                <script type="text/javascript">
                    try {
                        ace.settings.loadState('sidebar')
                    } catch (e) {
                    }
                </script>

                <div class="sidebar-shortcuts" id="sidebar-shortcuts">


                    <ul class="breadcrumb">
                        <li>
                            <i class="ace-icon fa fa-home home-icon"></i>
                            <a href="../MenuAplicacion/menu.jsp">Menu</a>

                        <li>
                            <a href="../srvUsuarios?accion=mostrar">Registrar Estudiantes</a>
                        </li>

                    </ul><!-- /.breadcrumb -->
                </div><!-- /.sidebar-shortcuts -->
            </div>

            <div class="main-content">
                <div class="main-content-inner">

                    <div class="page-content">
                        
                        <a href="#" data-toggle="tooltip" title="Mostrar Usuario">
                            <button  onclick=" location.href = '../srvUsuarios?accion=mostrar'" class="btn btn-success fa fa-user" style="float: right"> Mostrar</button>
                        </a>
                        
                        <center>
                            <h4><b>Registrar Estudiantes</b></h4><br>
                        </center>
                        
                        <form autocomplete="off"  action="/UECGV/srvUsuarios?accion=insertar" method="post">
                            <div class="row " style="margin-left: 4%">
                                <div class="col-sm-2">
                                    <label>Tipo de Identificación</label>
                                    <select id="tipoIdentificacion" style="text-transform:uppercase" class="form-control" name="sltRegTipoIdentificacion" required=""onchange="documetoSeleccionado()">
                                        <option></option>

                                        <option value="cedula">Cédula</option>
                                        <option value="pasaporte">Pasaporte</option>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <label>Identificación</label>
                                    <input type="text" id="identificacion" class="form-control"  maxlength="20" onkeypress="return soloNumeros(event)" required="" name="txtRegIdentificacion" placeholder="Identificacion">
                                    <span id="erroridentificaciion" style="color: #f00;"></span>
                                </div>
                                <div class="col-sm-2">
                                    <label>País</label>
                                    
                                    <select id="pais" required="" style="text-transform:uppercase"  class="form-control input-sm" name="sltRegPais">
                                        <option></option>
                                        <c:forEach var="lista" items="${listaPaises}">
                                            <option value="${lista.idPais}">${lista.nombre}</option>
                                        </c:forEach>     
                                    </select>                                                 
                                </div>

                                <div class="col-sm-2">
                                    <label>Género</label>
                                    <select id="genero" style="text-transform:uppercase" class="form-control" name="sltRegGenero" required="">
                                        <option></option>
                                        <option value="M">Masculino</option>
                                        <option value="F">Femenino</option>
                                    </select>
                                </div>
                                
                                <div class="col-sm-2">
                                    <label>Foto</label>
                                    <input type="file" id="apellidos" class="form-control"  maxlength="50" required="" name="txtRegFoto" placeholder="Foto">
                                </div>

                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Nombres</label>
                                    <input type="text" style="text-transform:uppercase" id="nombres" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegNombres" placeholder="Nombres">
                                </div>
                                <div class="col-sm-3">
                                    <label>Apellidos</label>
                                    <input type="text" style="text-transform:uppercase" id="apellidos" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegApellidos" placeholder="Apellido">
                                </div>
                                <div class="col-sm-3">
                                    <label>Fecha de nacimiento</label>
                                     <input type="date" id="apellidos" class="form-control"   required="" name="txtRegFecha" placeholder="">
                                </div>
                            
                            
                                <div class="col-sm-2">
                                    <label>Teléfono</label>
                                    <input type="text" style="text-transform:uppercase"  id="telefono" class="form-control" maxlength="10" onkeypress="return soloNumeros(event)" required="" name="txtRegTelefono" placeholder="Teléfono">
                                </div>
                                </div>
                            <br>
                            <div class="row" style="margin-left: 4%">

                                <div class="col-sm-5">
                                    <label>E-mail</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                        <input type="email" style="text-transform:lowercase"  id="email" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" maxlength="50" required="" name="txtRegEmail" placeholder="E-mail">
                                    </div>
                                    <span id="erroremail" style="color: #f00;"></span>
                                </div>
                                
                                
                                <div class="col-sm-3">
                                    <label>Celular</label>                                         
                                        <input type="text" id="usuario" placeholder="0000000000" class="form-control" onkeypress="return soloNumeros(event)" maxlength="10" required="" name="txtRegCelular">
                                   
                                    
                                </div>
                                 <div class="col-sm-3">
                                    <label>Curso</label>
                                    <select id="genero" style="text-transform:uppercase" class="form-control" name="sltRegCurso" required="">
                                     
                                        <option value="1">Octavo</option>
                                        <option value="2">Noveno</option>
                                    </select>
                                </div>
                                
                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-8">
                                    <label>Dirección</label>
                                    <input type="text" id="apellidos" style="text-transform:uppercase"  class="form-control" onkeypress="return soloLetrasNumerosCaracEspe(event)" maxlength="50" required="" name="txtRegDireccion" placeholder="Dirección">
                                    <%--pattern="[A-Za-z0-9ñ]+"--%>
                                </div>
                                <div class="col-sm-3">
                                    <label>Foto del domicilio</label>
                                    <input type="file" id="apellidos" class="form-control"  maxlength="50" required="" name="txtRegFotoDomicilio" placeholder="Foto">
                                </div>
                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-1">
                                    <label>Hermanos</label>
                                    <input type="text" id="telefono" class="form-control" maxlength="1" onkeypress="return soloNumeros(event)" required="" name="txtRegNumHermanos" placeholder="0">
                                </div>
                                <div class="col-sm-2">
                                    <label>Lugar entre hermanos</label>
                                    <input type="text" id="LugarHnos" class="form-control" maxlength="1" onkeypress="return soloNumeros(event)" required="" name="txtRegNumHermanos" placeholder="0">
                                </div>
                                <div class="col-sm-2">
                                    <label>Carnet de Discapacidad</label>
                                    <input type="text" id="CrntDisc" class="form-control" onkeypress="return soloLetrasNumerosv2(event)" maxlength="20" required="" name="txtRegCarnet" placeholder="Código">
                                </div>
                                <div class="col-sm-2">
                                    <label>Discapacidad</label>
                                    <input type="text" id="discapacidad" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegDiscapacidad" placeholder="Discapacidad">
                                </div>
                                <div class="col-sm-2">
                                    <label>Tipo de discapacidad</label>
                                     <select id="TipoDisc" style="text-transform:uppercase" class="form-control" name="sltRegTipoDiscp" required="">
                                        <option value="1">Ninguna</option>
                                        <option value="2">Fisíca</option>
                                        <option value="3">Mental</option>
                                    </select>
                                </div>
                            
                            
                                <div class="col-sm-2">
                                    <label>Historia clínica</label>
                                    <input type="text" style="text-transform:uppercase" id="HistClinica" class="form-control" maxlength="10" onkeypress="return soloLetrasNumerosv2(event)" required="" name="txtRegHistoriaClinica" placeholder="">
                                </div>
                                
                                </div>
                            
                            <br>
                             <center>
                            <h4><b>Datos del Representante</b></h4><br>
                             </center>
                            <div class="row " style="margin-left: 4%">
                                <div class="col-sm-2">
                                    <label>Tipo de Identificación</label>
                                    <select id="tipoIdentificacion" style="text-transform:uppercase"  onchange="documetoSeleccionado()" class="form-control" name="sltRegFamiliarTipoIdentificacion" required="">
                                        <option></option>

                                        <option value="cedula">Cédula</option>
                                        <option value="pasaporte">Pasaporte</option>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <label>Identificación</label>
                                    <input type="text" id="identificacion" class="form-control"  maxlength="20" onkeypress="return soloNumeros(event)" required=""  name="txtRegFamiliarIdentificacion" placeholder="Identificacion">
                                    <span id="erroridentificaciion" style="color: #f00;"></span>
                                </div>
                                <div class="col-sm-2">
                                    <label>País</label>
                                    
                                    <select id="pais" required=""  style="text-transform:uppercase" class="form-control input-sm" name="sltRegFamiliarPais">
                                        <option></option>
                                        <c:forEach var="lista" items="${listaPaises}">
                                            <option value="${lista.idPais}">${lista.nombre}</option>
                                        </c:forEach>     
                                    </select>                                                 
                                </div>

                                <div class="col-sm-2">
                                    <label>Parentesco</label>
                                    <select id="parentesco" style="text-transform:uppercase" class="form-control" name="sltRegParentesco" required="">
                                        <option></option>
                                        <option value="MADRE">MADRE</option>
                                        <option value="PADRE">PADRE</option>
                                    </select>
                                </div>
                                
                                <div class="col-sm-2">
                                    <label>C.U.E</label>
                                    <input type="text" id="apellidos" class="form-control"  maxlength="50" required="" name="txtRegCue" placeholder="codigo">
                                </div>

                            </div>
                            <br>
                             <div class="row" style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Nombres</label>
                                    <input type="text" id="nombres" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegNombres" placeholder="Nombres">
                                </div>
                                <div class="col-sm-3">
                                    <label>Apellidos</label>
                                    <input type="text" id="apellidos" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegApellidos" placeholder="Apellido">
                                </div>
                                <div class="col-sm-3">
                                    <label>Ocupación</label>
                                     <input type="text" id="apellidos" style="text-transform:uppercase"  class="form-control"   required="" name="txtRegFamiliarOcupacion" placeholder="Ocupación">
                                </div>
                            
                            
                                <div class="col-sm-2">
                                    <label>Lugar de Trabajo</label>
                                    <input type="text" id="telefono" style="text-transform:uppercase"  class="form-control" maxlength="10" onkeypress="return soloLetrasNumerosCaracEspe()(event)" required="" name="txtRegFamiliarLugar" placeholder="Lugar de Trabajo">
                                </div>
                                </div>
                            <br>
                             <div class="row" style="margin-left: 4%">

                                <div class="col-sm-8">
                                    <label>E-mail</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                        <input type="email" id="email" style="text-transform:lowercase"  class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" maxlength="100" required="" name="txtRegEmail" placeholder="E-mail">
                                    </div>
                                    <span id="erroremail" style="color: #f00;"></span>
                                </div>
                                
                                
                                <div class="col-sm-3">
                                    <label>Celular</label>                                         
                                        <input type="text" id="usuario" placeholder="0000000000" class="form-control" onkeypress="return soloNumeros(event)" maxlength="10" required="" name="txtRegCelular">
                                   
                                    
                                </div>
                                 
                                
                            </div>
                            <br>
                            <center>
                                <button type="submit" class="btn btn-primary btn-flat m-b-30 m-t-30" id="btnRegistrar">Registrar</button>                        
                            </center>
                        </form>

                    </div><br><br>

                </div><!-- /.main-content -->



                <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
                    <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
                </a>
            </div><!-- /.main-container -->
            <%@include file="../EstructuraAplicacion/piedepagina.jsp" %>
        </div>
        <!-- basic scripts -->

        <!--[if !IE]> -->
        <script src="../js/jquery-2.1.4.min.js"></script>

        <!-- <![endif]-->

        <!--[if IE]>
    <script src="assets/js/jquery-1.11.3.min.js"></script>
    <![endif]-->
        <script type="text/javascript">
            if ('ontouchstart' in document.documentElement)
                document.write("<script src='/UECGV/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>
        <script src="../js/bootstrap.min.js"></script>

        <!-- page specific plugin scripts -->

        <!-- ace scripts -->
        <script src="../js/ace-elements.min.js"></script>
        <script src="../js/ace.min.js"></script>


        <!-- inline scripts related to this page -->
        <script type="text/javascript">
            jQuery(function ($) {
                var $sidebar = $('.sidebar').eq(0);
                if (!$sidebar.hasClass('h-sidebar'))
                    return;

                $(document).on('settings.ace.top_menu', function (ev, event_name, fixed) {
                    if (event_name !== 'sidebar_fixed')
                        return;

                    var sidebar = $sidebar.get(0);
                    var $window = $(window);

                    //return if sidebar is not fixed or in mobile view mode
                    var sidebar_vars = $sidebar.ace_sidebar('vars');
                    if (!fixed || (sidebar_vars['mobile_view'] || sidebar_vars['collapsible'])) {
                        $sidebar.removeClass('lower-highlight');
                        //restore original, default marginTop
                        sidebar.style.marginTop = '';

                        $window.off('scroll.ace.top_menu')
                        return;
                    }


                    var done = false;
                    $window.on('scroll.ace.top_menu', function (e) {

                        var scroll = $window.scrollTop();
                        scroll = parseInt(scroll / 4);//move the menu up 1px for every 4px of document scrolling
                        if (scroll > 17)
                            scroll = 17;


                        if (scroll > 16) {
                            if (!done) {
                                $sidebar.addClass('lower-highlight');
                                done = true;
                            }
                        } else {
                            if (done) {
                                $sidebar.removeClass('lower-highlight');
                                done = false;
                            }
                        }

                        sidebar.style['marginTop'] = (17 - scroll) + 'px';
                    }).triggerHandler('scroll.ace.top_menu');

                }).triggerHandler('settings.ace.top_menu', ['sidebar_fixed', $sidebar.hasClass('sidebar-fixed')]);

                $(window).on('resize.ace.top_menu', function () {
                    $(document).triggerHandler('settings.ace.top_menu', ['sidebar_fixed', $sidebar.hasClass('sidebar-fixed')]);
                });
            });
        </script>
        
        <!-- Carlos Muñoz -->
        <script src="/UECGV/js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="/UECGV/Administracion/js/Validaciones.js" type="text/javascript"></script>
        <script src="/UECGV/Administracion/js/CRUDpersonas.js" type="text/javascript"></script>
                
    </body>
</html>
