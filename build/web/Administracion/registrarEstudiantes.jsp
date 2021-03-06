<%-- 
    Document   : registrarPersonas
    Created on : 15-ene-2019, 9:35:53
    Author     : Carlos
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta charset="utf-8" />
        <title>Registro - U.E.M Carmelina Granja Villanueva</title>

        <meta name="description" content="top menu &amp; navigation" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

        <%@include file="../EstructuraAplicacion/head_icono.jsp" %>
        <!-- bootstrap & fontawesome -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/font-awesome.min.css" />

        <!-- page specific plugin styles -->

        <!-- text fonts -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/fonts.googleapis.com.css" />

        <!-- ace styles -->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

        <!--[if lte IE 9]>
                <link rel="stylesheet" href="assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
        <![endif]-->
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ace-skins.min.css" />
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ace-rtl.min.css" />

        <script src="<%=request.getContextPath()%>/js/ace-extra.min.js"></script>

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
                            <a href="<%=request.getContextPath()%>/MenuAplicacion/menu.jsp">Menu</a>

                        <li>
                            <a href="<%=request.getContextPath()%>/srvUsuarios?accion=mostrar">Registrar Estudiantes</a>
                        </li>

                    </ul><!-- /.breadcrumb -->
                </div><!-- /.sidebar-shortcuts -->
            </div>

            <div class="main-content">
                <div class="main-content-inner">

                    <div class="page-content">

                        <a href="#" data-toggle="tooltip" title="Mostrar Usuario">
                            <button  onclick=" location.href = '<%=request.getContextPath()%>/srvEstudiante?accion=mostrar_estudiante'" class="btn btn-success fa fa-user" style="float: right"> Mostrar</button>
                        </a>

                        <center>
                            <h4><b>Registrar Estudiantes</b></h4><br>
                        </center>
                        <br/><label class="text-danger campo-obligatorios" style="margin-left: 5%">Campos obligatarios (*)</label>
                        <br>
                        <form autocomplete="on"  enctype="multipart/form-data" id="formRegistrarAlu">
                            <div class="row " style="margin-left: 4%">
                                <div class="col-sm-2">
                                    <label>Tipo de Identificaci??n</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                    <select id="tipoIdentificacionAlu" style="text-transform:uppercase" class="form-control" name="sltRegTipoIdentificacion" required="" onchange="documetoSeleccionado()">
                                        <option></option>

                                        <option value="cedula">C??dula</option>
                                        <option value="pasaporte">Pasaporte</option>
                                    </select>
                                </div>
                                <div class="col-sm-3 form-group">
                                    <label>Identificaci??n</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                    <input type="text" style="text-transform:uppercase" id="regidentificacionAlu" class="form-control "  maxlength="20" onkeypress="return soloNumeros(event)" onkeyup="validarIdentificacion()" required="" name="txtRegIdentificacion" placeholder="Identificacion">

                                </div>
                                <div class="col-sm-2">
                                    <label>Nacionalidad</label><label class="text-danger campo-obligatorios">&nbsp;*</label>

                                    <input type="text" style="text-transform:uppercase" id="regpaisAlu" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegNacionalidad" placeholder="nacionalidad" value="ECUATORIANA">

                                </div>

                                <div class="col-sm-2">
                                    <label>G??nero</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                    <select id="reggeneroAlu" style="text-transform:uppercase" class="form-control" name="sltRegGenero" required="">
                                        <option></option>
                                        <option value="M">Masculino</option>
                                        <option value="F">Femenino</option>
                                    </select>
                                </div>

                                <div class="col-sm-2">
                                    <label>Foto Del Estudiante</label>
                                    <input type="file" id="regFotoAlu" class="form-control"  onchange="validarFile(this)" maxlength="50"  name="txtRegFoto" placeholder="Foto">
                                </div>

                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Nombres</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                    <input type="text" style="text-transform:uppercase" id="regnombresAlu" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50"  name="txtRegNombres" placeholder="Nombres">
                                </div>
                                <div class="col-sm-3">
                                    <label>Apellidos</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                    <input type="text" style="text-transform:uppercase" id="regapellidosAlu" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50"  name="txtRegApellidos" placeholder="Apellido">
                                </div>
                                <div class="col-sm-3">
                                    <label>Fecha de nacimiento</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                    <input type="date" id="regfechaNacimientoAlu" class="form-control"    name="txtRegFecha" placeholder="" required="">
                                </div>

                                <div class="col-sm-2">
                                    <label>Curso</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                    <select id="regCursoAlu" style="text-transform:uppercase" class="form-control" name="sltRegCurso" required="">
                                        <option></option>
                                        <c:forEach var="listaCursos" items="${listaCursos}">
                                            <option value="${listaCursos.id_curso}">${listaCursos.nombre_curso} ${listaCursos.tipo}</option>
                                        </c:forEach>  

                                    </select>
                                </div>

                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Foto del domicilio</label>
                                    <input type="file" id="fotoDomicilioAlu" onchange="validarFile(this)" class="form-control"  maxlength="50" name="txtRegFotoDomicilio" placeholder="Foto">
                                </div>
                                <div class="col-sm-5">
                                    <label>E-mail</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                        <input type="email" style="text-transform:lowercase"  id="emailAlu" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" maxlength="50" name="txtRegEmail" placeholder="E-mail">
                                    </div>
                                    <span id="erroremail" style="color: #f00;"></span>
                                </div>


                                <div class="col-sm-3">
                                    <label>Celular</label>                                         
                                    <input type="text" id="regCelularAlu" placeholder="CELULAR" class="form-control" onkeypress="return soloNumeros(event)" maxlength="10" name="txtRegCelular">


                                </div>


                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">

                                <div class="col-sm-8">
                                    <label>Direcci??n</label> 
                                    <input type="text" id="regDireccionAlu" style="text-transform:uppercase"  class="form-control" onkeypress="return soloLetrasNumerosCaracEspe(event)" maxlength="50"  name="txtRegDireccion" placeholder="Direcci??n">
                                    <%--pattern="[A-Za-z0-9??]+"--%>
                                </div>

                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-1">
                                    <label>Hermanos</label>
                                    <input type="text" id="regHermanoAlu" class="form-control" maxlength="1" onkeypress="return soloNumeros(event)"  name="txtRegNumHermanos" placeholder="0">
                                </div>
                                <div class="col-sm-2">
                                    <label>Lugar entre hermanos</label>
                                    <input type="text" id="regLugarHnosAlu" class="form-control" maxlength="1" onkeypress="return soloNumeros(event)"  name="txtRegLugHermanos" placeholder="0">
                                </div>
                                <div class="col-sm-2">
                                    <label>Tipo de discapacidad</label>
                                    <select id="regTipoDiscAlu"  style="text-transform:uppercase" onchange="discapacidad(this)" class="form-control" name="sltRegTipoDiscp" >
                                        <option></option>
                                        <option value="1">Ninguna</option>
                                        <option value="2">Fis??ca</option>
                                        <option value="3">Mental</option>
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <label>Carnet de Discapacidad</label>
                                    <input type="text" id="regCrntDiscAlu" class="form-control" onkeypress="return soloLetrasNumerosv2(event)" maxlength="20"  name="txtRegCarnet" placeholder="C??digo">
                                </div>
                                <div class="col-sm-2">
                                    <label>Discapacidad</label>
                                    <input type="text" id="regDiscapacidadAlu" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50"  name="txtRegDiscapacidad" placeholder="Discapacidad">
                                </div>



                                <div class="col-sm-2">
                                    <label>Historia cl??nica</label>
                                    <input type="text" style="text-transform:uppercase" id="regHistClinicaAlu" class="form-control" maxlength="10" onkeypress="return soloLetrasNumerosv2(event)" name="txtRegHistoriaClinica" placeholder="">
                                </div>
                                <div class="col-sm-12" >
                                    <br>
                                    <label>??Qui??n Vive Con usted?</label>
                                    <div class="checkbox">
                                        <c:forEach var="listaConvivencia" items="${listaConvivencia}">

                                            <label><input  type="checkbox" name="listaFamiliaresAlu" value="${listaConvivencia.id_convivencia}">  ${listaConvivencia.familiar} &nbsp;&nbsp;&nbsp</label>

                                        </c:forEach>
                                    </div>


                                </div>
                            </div>



                            <br>
                            <center>
                                <h4><b>Datos del Padre</b></h4><br>
                            </center>
                            <div class="row " style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Tipo de Identificaci??n</label>
                                    <select id="regtipoIdentificacionRepP" style="text-transform:uppercase"  onchange="documetoSeleccionado()" class="form-control" name="txtTipoIdePadre" >
                                        <option></option>

                                        <option value="cedula">C??dula</option>
                                        <option value="pasaporte">Pasaporte</option>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <label>Identificaci??n</label>
                                    <input type="text" id="regIdentificacionRepP" class="form-control"  maxlength="20" onkeypress="return soloNumeros(event)"  name="txtRegFamiliarIdentificacionPadre" placeholder="Identificacion">
                                    <span id="erroridentificaciion" style="color: #f00;"></span>
                                </div>
                                <div class="col-sm-3">
                                    <label>Nacionalidad</label>
                                    <input type="text" style="text-transform:uppercase" id="regNacionalidadRepP" class="form-control"  maxlength="50"  name="txtRegNacionalidadPadre" placeholder="nacionalidad" onkeypress="return soloLetrasv2(event)" value="ECUATORIANA">


                                </div>


                                <div class="col-sm-2">
                                    <label>C.U.E</label>
                                    <input type="text" id="regCueRepP" class="form-control"  maxlength="50"  name="txtRegCuePadre" placeholder="codigo" onkeypress="return soloNumeros(event)">
                                </div>

                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Nombres</label>
                                    <input type="text" id="regnombresRepP" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50"  name="txtRegNombresPadre" placeholder="Nombres">
                                </div>
                                <div class="col-sm-3">
                                    <label>Apellidos</label>
                                    <input type="text" id="regApellidosRepP" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" name="txtRegApellidosPadre" placeholder="Apellido">
                                </div>
                                <div class="col-sm-3">
                                    <label>Ocupaci??n</label>
                                    <input type="text" id="regOcupacionRepP" style="text-transform:uppercase"  class="form-control"   name="txtRegFamiliarOcupacionPadre" placeholder="Ocupaci??n">
                                </div>


                                <div class="col-sm-2">
                                    <label>Lugar de Trabajo</label>
                                    <input type="text" id="regTelefonoRepP" style="text-transform:uppercase"  class="form-control" maxlength="10" onkeypress="return soloLetrasNumerosCaracEspe()(event)"  name="txtRegFamiliarLugarPadre" placeholder="Lugar de Trabajo">
                                </div>
                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">

                                <div class="col-sm-8">
                                    <label>E-mail</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                        <input type="email" id="regEmailRepP" style="text-transform:lowercase"  class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" maxlength="100"  name="txtRegEmailPadre" placeholder="E-mail">
                                    </div>
                                    <span id="erroremail" style="color: #f00;"></span>
                                </div>


                                <div class="col-sm-3">
                                    <label>Celular</label>                                         
                                    <input type="text" id="regCelularRepP" placeholder="CELULAR" class="form-control" onkeypress="return soloNumeros(event)" maxlength="10"  name="txtRegCelularPadre">


                                </div>


                            </div>
                            <br>

                            <center>
                                <h4><b>Datos de la Madre</b></h4><br>
                            </center>
                            <div class="row " style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Tipo de Identificaci??n</label>
                                    <select id="regtipoIdentificacionRep" style="text-transform:uppercase"  onchange="documetoSeleccionado()" class="form-control" name="sltRegFamiliarTipoIdentificacionMadre">
                                        <option></option>

                                        <option value="cedula">C??dula</option>
                                        <option value="pasaporte">Pasaporte</option>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <label>Identificaci??n</label>
                                    <input type="text" id="regIdentificacionRep" style="text-transform:uppercase" class="form-control"  maxlength="20" onkeypress="return soloNumeros(event)"   name="txtRegFamiliarIdentificacionMadre" placeholder="Identificacion">
                                    <span id="erroridentificaciion" style="color: #f00;"></span>
                                </div>
                                <div class="col-sm-3">
                                    <label>Nacionalidad</label>
                                    <input type="text" style="text-transform:uppercase" id="regNacionalidadRep" class="form-control"  maxlength="50"  name="txtRegNacionalidadMadre" placeholder="nacionalidad" onkeypress="return soloLetrasv2(event)" value="ECUATORIANA">


                                </div>



                                <div class="col-sm-2">
                                    <label>C.U.E</label>
                                    <input type="text" id="regCueRep" class="form-control"  maxlength="50"  name="txtRegCueMadre" placeholder="codigo" onkeypress="return soloNumeros(event)">
                                </div>

                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-3">
                                    <label>Nombres</label>
                                    <input type="text" id="regnombresRep" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50"  name="txtRegNombresMadre" placeholder="Nombres">
                                </div>
                                <div class="col-sm-3">
                                    <label>Apellidos</label>
                                    <input type="text" id="regApellidosRep" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50"  name="txtRegApellidosMadre" placeholder="Apellido">
                                </div>
                                <div class="col-sm-3">
                                    <label>Ocupaci??n</label>
                                    <input type="text" id="regOcupacionRep" style="text-transform:uppercase"  class="form-control"    name="txtRegFamiliarOcupacionMadre" placeholder="Ocupaci??n">
                                </div>


                                <div class="col-sm-2">
                                    <label>Lugar de Trabajo</label>
                                    <input type="text" id="regTelefonoRep" style="text-transform:uppercase"  class="form-control" maxlength="10" onkeypress="return soloLetrasNumerosCaracEspe()(event)"  name="txtRegFamiliarLugarMadre" placeholder="Lugar de Trabajo">
                                </div>
                            </div>
                            <br>
                            <div class="row" style="margin-left: 4%">

                                <div class="col-sm-8">
                                    <label>E-mail</label>
                                    <div class="input-group">
                                        <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                        <input type="email" id="regEmailRep" style="text-transform:lowercase"  class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" maxlength="100"  name="txtRegEmailMadre" placeholder="E-mail">
                                    </div>
                                    <span id="erroremail" style="color: #f00;"></span>
                                </div>


                                <div class="col-sm-3">
                                    <label>Celular</label>                                         
                                    <input type="text" id="regCelularRep" placeholder="CELULAR" class="form-control" onkeypress="return soloNumeros(event)" maxlength="10" name="txtRegCelular">


                                </div>


                            </div>
                            <br>

                            <center>
                                <h4><b>Datos de un Contacto Adicional </b></h4><br>
                            </center>
                            <div class="row" style="margin-left: 4%">
                                <div class="col-sm-2">
                                    <label>Tipo de Identificaci??n</label>
                                    <select id="regtipoIdentificacionRep" style="text-transform:uppercase"  onchange="documetoSeleccionado()" class="form-control" name="sltRegTipoIdentificacionRef" >
                                        <option></option>

                                        <option value="cedula">C??dula</option>
                                        <option value="pasaporte">Pasaporte</option>
                                    </select>
                                </div>
                                <div class="col-sm-3">
                                    <label>Identificacion</label>
                                    <input type="text" id="modEditCedulaRef" style="text-transform:uppercase" class="form-control" onkeypress="return soloNumeros(event)" maxlength="50"  name="txtRegCedulaRef" placeholder="Identificacion">

                                </div>
                                <div class="col-sm-3">
                                    <label>Nombres</label>
                                    <input type="text" id="modEditNonbresRef" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50"  name="txtRegNombresRef" placeholder="nombres">

                                </div>
                                <div class="col-sm-3">
                                    <label>Apellidos</label>
                                    <input type="text" id="modEditApellidoRef"  style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50"  name="txtRegApellidosRef" placeholder="apellidos">

                                </div>
                                <div class="col-sm-2">
                                    <label>Parentesco</label>

                                    <input type="text" id="modEditParentescoRef"  style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="20"  name="txtRegParentescoRef" placeholder="parentesco">                               
                                </div>

                                <div class="col-sm-3">
                                    <label>Telefono</label>
                                    <input type="text" id="modEditTelefonoRef" style="text-transform:uppercase" class="form-control" onkeypress="return soloNumeros(event)" maxlength="10"  name="txtRegtelefonoRef" placeholder="telefono">                               

                                </div>
                                <div class="col-sm-3">
                                    <label>Celular</label>

                                    <input type="text" maxlength="10" id="modEditcelularRef" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasNumerosv2(event)"  name="txtRegCelularRef" placeholder="celular">                               

                                </div>
                            </div>
                            <br>
                            <center>
                                <button type="button" class="btn btn-primary btn-flat m-b-30 m-t-30" id="btnRegistrarestudiante" >Registrar</button>                        
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
        <script src="<%=request.getContextPath()%>/js/jquery-2.1.4.min.js"></script>

        <!-- <![endif]-->

        <!--[if IE]>
    <script src="assets/js/jquery-1.11.3.min.js"></script>
    <![endif]-->
        <script type="text/javascript">
                                        if ('ontouchstart' in document.documentElement)
                                            document.write("<script src='/UECGV/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
        </script>
        <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>

        <!-- page specific plugin scripts -->

        <!-- ace scripts -->
        <script src="<%=request.getContextPath()%>/js/ace-elements.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/ace.min.js"></script>


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

        <script>
            //Funcion de JS que valida el archivo ingresado al input. Formato y Tama??o.
            function validarFile(all)
            {
                //EXTENSIONES Y TAMANO PERMITIDO.
                var extensiones_permitidas = [".png", ".bmp", ".jpg", ".jpeg"];
                var tamano = 8; // EXPRESADO EN MB.
                var rutayarchivo = all.value;
                var ultimo_punto = all.value.lastIndexOf(".");
                var extension = rutayarchivo.slice(ultimo_punto, rutayarchivo.length);
                if (extensiones_permitidas.indexOf(extension) == -1)
                {
                    alert("Extensi??n de archivo no valida");
                    document.getElementById(all.id).value = "";
                    return; // Si la extension es no v??lida ya no chequeo lo de abajo.
                }
                if ((all.files[0].size / 1048576) > tamano)
                {
                    alert("El archivo no puede superar los " + tamano + "MB");
                    document.getElementById(all.id).value = "";
                    return;
                }
            }


            function enviarRegintrarEstudiante()
            {



                if ($("#tipoIdentificacionAlu").val() !== "" && $("#regidentificacionAlu").val() !== "" && $("#regpaisAlu").val() !== "" &&
                        $("#reggeneroAlu").val() !== "" && $("#regfechaNacimientoAlu") !== "" && $("#regnombresAlu").val() !== "" && $("#regapellidosAlu").val() !== "" &&
                        $("#regCursoAlu").val() !== "") {



                    $('#btnRegistrarestudiante').text("Enviando");
                    $('#btnRegistrarestudiante').attr("disabled", true);
                    var form = $('#formRegistrarAlu')[0];
                    var data = new FormData(form);
                    $.ajax({
                        // async: true,
                        enctype: 'multipart/form-data',
                        type: "POST",
                        data: data,
                        url: '/UECGV/srvEstudiante?accion=registrar_estudiante',
                        processData: false, // Important!
                        contentType: false,
                        cache: false,
                        success: function (responseText)
                        {
                            var respon = responseText;
                            if (respon === 'ok')
                            {
                                $('#btnRegistrarestudiante').text("Registrar");
                                $('#btnRegistrarestudiante').attr("disabled", false);

                                //$("#btnCerrarModalContactenos").click();
                                toastr.success("Mensaje enviado");
                                window.location.href = '/UECGV/srvEstudiante?accion=mostrar_estudiante';

                            } else
                            {
                                //toastr.success("Exito al Registrar");
                                $('#btnRegistrarestudiante').text("Registrar");
                                $('#btnRegistrarestudiante').attr("disabled", false);


                                // $("#btnCerrarModalContactenos").click();
                                toastr.error(respon);
                            }
                        }
                    });
                } else {
                    toastr.error("Complete los campos obligatorios");
                }

            }
            $(document).ready(function () {
                $('#btnRegistrarestudiante').click(function () {
                    enviarRegintrarEstudiante();
                    $("#btnGuardar").attr("disabled", true);
                    // alert("si vale");
                });
            });

        </script>


        <!-- Sistemas  -->
        <script src="/UECGV/js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="/UECGV/Administracion/js/Validaciones.js" type="text/javascript"></script>
        <script src="/UECGV/Administracion/js/CRUDpersonas.js" type="text/javascript"></script>

    </body>
</html>
