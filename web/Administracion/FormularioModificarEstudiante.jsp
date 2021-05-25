<%-- 
    Document   : mostrarUsuarios
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
        <title>Usuarios - U.E.M Carmelina Granja Villanueva</title>

        <meta name="description" content="top menu &amp; navigation" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

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

        <link href="<%=request.getContextPath()%>/Administracion/css/material-design-iconic-font.min.css" rel="stylesheet" type="text/css"/>


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
                            <a href="srvEstudiante?accion=mostrar_estudiante">Alumnos</a>
                        </li>

                    </ul><!-- /.breadcrumb -->
                </div><!-- /.sidebar-shortcuts -->
                <a href="#" data-toggle="tooltip" title="Mostrar Usuario">
                            <button  onclick=" location.href = '<%=request.getContextPath()%>/srvEstudiante?accion=mostrar_estudiante'" class="btn btn-success fa fa-user" style="float: right; margin-right: 5%"> Regresar</button>
                        </a>
            </div>

            <div class="main-content">
                
                <div class="main-content-inner">

                    <div class="page-content">

                        <section id="section-datos" class="full-reset mb-200" style="background-color: #fff; padding: 20px 0;">
                            <div class="container">
                                <div class="row">
                                    <section class="col-xs-12">
                                        <article class="full-reset" hidden="true">
                                            <div class="page-header" style="margin-bottom:20px;">
                                                <h1><i class="fa fa fa-user"></i> &nbsp; Actualizar Datos - Estudiante</h1>
                                            </div>
                                            <div class="abs-center">
                                                <fieldset>
                                                    <form>
                                                        <div class="form-group">
                                                            <label>Ingrese su número de identificación</label>
                                                            <!--<input type="text" class="form-control" id="txtIdentificacion" name="txtBuscar" placeholder="Cédula/Pasaporte"  onkeyup="if (/\D/g.test(this.value))
                                                                        this.value = this.value.replace(/\D/g, '')">-->
                                                            <input type="text" class="form-control" id="txtIdentificacion" name="txtBuscar" placeholder="Cédula/Pasaporte"  onkeyup="if (/\D/g.test(this.value))
                                                                        this.value = this.value.replace(/\D/g, '')" value="${cedula}">
                                                            <small class="form-text text-muted">Los datos deben ser actualizados por una persona responsable.</small>
                                                        </div>
                                                        <center>
                                                            <button id="btn-buscarEst" type="button" class="btn btn-primary"><i class="fa fa-search"></i>&nbsp; Buscar</button>
                                                        </center>
                                                    </form>
                                                </fieldset>
                                            </div>
                                        </article>
                                    </section>
                                </div>
                                <div class="row mt-25 form-datos">
                                    <button id="btnModificar" type="button" class="btn btn-primary" ><i class="fa fa-pencil"></i>&nbsp; Modificar</button>
                                    <br/><label class="text-danger campo-obligatorios">Campos obligatarios (*)</label>
                                    <form id="formEstudiante" autocomplete="off" method="post" enctype="multipart/form-data" class="mt-25" >
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <img id="regImgAlum" src="" height="250"/>
                                                <br>
                                                <label>Foto</label>
                                                <input type="file" id="regFotoAlu" name="regFotoAlu" class="form-control" name="txtRegFoto" placeholder="Foto" disabled accept="image/jpg, image/png, image/jpeg">
                                                <input type="hidden" name="idEstd" id="idEstd">
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <label>Tipo de Identificación</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <select id="tipoIdentificacionAlu" style="text-transform:uppercase" class="form-control" name="sltRegTipoIdentificacion" required disabled>
                                                    <option></option>
                                                    <option value="Cédula">Cédula</option>
                                                    <option value="Pasaporte">Pasaporte</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Identificación</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" style="text-transform:uppercase" id="regidentificacionAlu" class="form-control"  maxlength="20" required="" name="txtRegIdentificacion" placeholder="Identificacion" readonly onkeyup="if (/\D/g.test(this.value))
                                                            this.value = this.value.replace(/\D/g, '')">
                                                <span id="erroridentificaciion" style="color: #f00;"></span>
                                            </div>
                                            <div class="col-sm-2">
                                                <label>Nacionalidad</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" style="text-transform:uppercase" id="regpaisAlu" class="form-control" maxlength="50" required="" name="txtRegNacionalidad" placeholder="nacionalidad" readonly>
                                            </div>
                                            <div class="col-sm-2">
                                                <label>Género</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <select id="reggeneroAlu" style="text-transform:uppercase;" class="form-control" name="sltRegGenero" required="" disabled>
                                                    <option></option>
                                                    <option value="M">Masculino</option>
                                                    <option value="F">Femenino</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Fecha de nacimiento</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="date" id="regfechaNacimientoAlu" class="form-control" required name="txtRegFecha" readonly>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-4">
                                                <label>Nombres</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" style="text-transform:uppercase" id="regnombresAlu" class="form-control" maxlength="50" required="" name="txtRegNombres" placeholder="Nombres" readonly>
                                            </div>
                                            <div class="col-sm-4">
                                                <label>Apellidos</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" style="text-transform:uppercase" id="regapellidosAlu" class="form-control"  maxlength="50" required="" name="txtRegApellidos" placeholder="Apellidos" readonly>
                                            </div>
                                            <div class="col-sm-4">
                                                <label>Correo Electrónico</label>
                                                <div class="input-group">
                                                    <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                                    <input type="email" style="text-transform:lowercase"  id="emailAlu" class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" maxlength="50"  name="txtRegEmail" readonly>
                                                </div>
                                                <span id="erroremail" style="color: #f00;"></span>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row" >
                                            <div class="col-sm-3">
                                                <label>Celular</label>                                         
                                                <input type="text" id="regCelularAlu" class="form-control" maxlength="10" name="txtRegCelular" readonly>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Curso</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <select id="regCursoAlu" style="text-transform:uppercase" class="form-control" name="sltRegCurso" required="" disabled>
                                                    <option></option> 
                                                </select>
                                            </div>
                                            <div class="col-sm-6">
                                                <label>Dirección</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regDireccionAlu" style="text-transform:uppercase"  class="form-control" maxlength="50" required="" name="txtRegDireccion" placeholder="Dirección" readonly="">
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-2">
                                                <label>Número de Hermanos</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="number" id="regHermanoAlu" class="form-control" maxlength="1" required="" name="txtRegNumHermanos" readonly >
                                            </div>
                                            <div class="col-sm-2">
                                                <label>Lugar que ocupa</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="number" id="regLugarHnosAlu" class="form-control" maxlength="1"  required="" name="txtRegLugHermanos" readonly>
                                            </div>
                                            <div class="col-sm-6">
                                                <label>Foto del domicilio</label>
                                                <input type="file" id="fotoDomicilioAlu" class="form-control"  name="txtRegFotoDomicilio" placeholder="Foto" disabled accept="image/jpg, image/png, image/jpeg">
                                            </div>
                                            <div class="col-sm-2">
                                                <label>Tipo de discapacidad</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <select id="regTipoDiscAlu" style="text-transform:uppercase" class="form-control" name="sltRegTipoDiscp" required disabled>
                                                    <option value="NINGUNA">Ninguna</option>
                                                    <option value="FISÍCA">Fisíca</option>
                                                    <option value="MENTAL">Mental</option>
                                                </select>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row discapacidad-seccion">
                                            <div class="col-sm-4">
                                                <label>Discapacidad</label>
                                                <input type="text" id="regDiscapacidadAlu" name="regDiscapacidadAlu" style="text-transform:uppercase" class="form-control" maxlength="50" name="txtRegDiscapacidad" placeholder="Discapacidad" readonly>
                                            </div>
                                            <div class="col-sm-4">
                                                <label>Carnet de Discapacidad</label>
                                                <input type="text" id="regCrntDiscAlu" name="regCrntDiscAlu" class="form-control" maxlength="20" readonly>
                                            </div>
                                            <div class="col-sm-4">
                                                <label>Historia clínica</label>
                                                <input type="text" style="text-transform:uppercase" id="regHistClinicaAlu" class="form-control" name="txtRegHistoriaClinica" maxlength="30" readonly>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-sm-12" >
                                                <br>
                                                <label>¿Quién Vive Con usted?</label>
                                                <div id="check-convivencia" class="checkbox">
                                                </div>
                                            </div>
                                        </div>
                                        <center>
                                            <h4><b>Datos del Padre</b></h4><br>
                                        </center>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label>Tipo de Identificación</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <select id="regtipoIdentificacionRep" style="text-transform:uppercase"  onchange="documetoSeleccionado()" class="form-control" name="txtTipoIdePadre" required="" disabled>
                                                    <option></option>
                                                    <option value="Cédula">Cédula</option>
                                                    <option value="Pasaporte">Pasaporte</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Identificación</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regIdentificacionRep" class="form-control"  maxlength="20"  required=""  name="txtRegFamiliarIdentificacionPadre" placeholder="IDENTIFICACIÓN" readonly onkeyup="if (/\D/g.test(this.value))
                                                            this.value = this.value.replace(/\D/g, '')">
                                                <span id="erroridentificaciion" style="color: #f00;"></span>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Nacionalidad</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" style="text-transform:uppercase" id="regNacionalidadRep" class="form-control"  maxlength="50" required="" name="txtRegNacionalidadPadre" readonly placeholder="NACIONALIDAD">
                                            </div>
                                            <div class="col-sm-3">
                                                <label>C.U.E</label>
                                                <input type="text" id="regCueRep" class="form-control"  maxlength="50" name="txtRegCuePadre" placeholder="Código único electrónico" readonly>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label>Nombres</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regnombresRep" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegNombresPadre" placeholder="Nombres" readonly>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Apellidos</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regApellidosRep" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegApellidosPadre" placeholder="Apellido" readonly>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Ocupación</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regOcupacionRep" style="text-transform:uppercase"  class="form-control"   required="" name="txtRegFamiliarOcupacionPadre" placeholder="Ocupación" readonly>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Lugar de Trabajo</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regTrabajoRep" style="text-transform:uppercase"  class="form-control" maxlength="10" onkeypress="return soloLetrasNumerosCaracEspe()(event)" required="" name="txtRegFamiliarLugarPadre" placeholder="Lugar de Trabajo" readonly>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-8">
                                                <label>Correo Electrónico</label>
                                                <div class="input-group">
                                                    <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                                    <input type="email" id="regEmailRep" style="text-transform:lowercase"  class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" maxlength="100" name="txtRegEmailPadre" readonly>
                                                </div>
                                                <span id="erroremail" style="color: #f00;"></span>
                                            </div>
                                            <div class="col-sm-4">
                                                <label>Celular</label>                                         
                                                <input type="text" id="regCelularRep" class="form-control" onkeypress="return soloNumeros(event)" maxlength="10" name="txtRegCelularPadre" readonly>
                                            </div>
                                        </div>
                                        <br>
                                        <center>
                                            <h4><b>Datos de la Madre</b></h4><br>
                                        </center>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label>Tipo de Identificación</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <select id="regtipoIdentificacionMad" style="text-transform:uppercase"  onchange="documetoSeleccionado()" class="form-control" name="txtTipoIdeMadre" required="" disabled>
                                                    <option></option>
                                                    <option value="Cédula">Cédula</option>
                                                    <option value="Pasaporte">Pasaporte</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Identificación</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regIdentificacionMad" class="form-control"  maxlength="20"  required=""  name="txtRegFamiliarIdentificacionMadre" placeholder="IDENTIFICACIÓN" readonly onkeyup="if (/\D/g.test(this.value))
                                                            this.value = this.value.replace(/\D/g, '')">
                                                <span id="erroridentificaciion" style="color: #f00;"></span>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Nacionalidad</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" style="text-transform:uppercase" id="regNacionalidadMad" class="form-control"  maxlength="50" required="" name="txtRegNacionalidadMadre" placeholder="NACIONALIDAD" readonly >
                                            </div>
                                            <div class="col-sm-3">
                                                <label>C.U.E</label>
                                                <input type="text" id="regCueMad" class="form-control"  maxlength="50" name="txtRegCueMadre" placeholder="Código único electrónico" readonly>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label>Nombres</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regnombresMad" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegNombresMadre" placeholder="Nombres" readonly>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Apellidos</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regApellidosMad" style="text-transform:uppercase" class="form-control" onkeypress="return soloLetrasv2(event)" maxlength="50" required="" name="txtRegApellidosMadre" placeholder="Apellidos" readonly>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Ocupación</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regOcupacionMad" style="text-transform:uppercase"  class="form-control"   required="" name="txtRegFamiliarOcupacionMadre" placeholder="Ocupación" readonly>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Lugar de Trabajo</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="regTrabajoMad" style="text-transform:uppercase"  class="form-control"  required="" name="txtRegFamiliarLugarMadre" placeholder="Lugar de Trabajo" readonly>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-8">
                                                <label>Correo Electrónico</label>
                                                <div class="input-group">
                                                    <div class="input-group-addon"><i class="fa fa-envelope"></i></div>
                                                    <input type="email" id="regEmailMad" style="text-transform:lowercase"  class="form-control" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" maxlength="100" name="txtRegEmailMadre" readonly>
                                                </div>
                                                <span id="erroremail" style="color: #f00;"></span>
                                            </div>
                                            <div class="col-sm-4">
                                                <label>Celular</label>                                         
                                                <input type="text" id="regCelularMad" class="form-control" onkeypress="return soloNumeros(event)" maxlength="10" name="txtRegCelularMadre" readonly>
                                            </div>
                                        </div>
                                        <br>
                                        <center>
                                            <h4><b>Datos de una Persona de Referencia</b></h4><br>
                                        </center>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label>Tipo de Identificación</label>
                                                <select id="regtipoIdentificacionRef" style="text-transform:uppercase"  onchange="documetoSeleccionado()" class="form-control" name="txtTipoIdeRef" disabled>
                                                    <option></option>
                                                    <option value="Cédula">Cédula</option>
                                                    <option value="Pasaporte">Pasaporte</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Identificación</label>
                                                <input type="text" id="regIdentificacionRef" class="form-control"  maxlength="20"   name="txtRefFamiliarIdentificacionRef" placeholder="IDENTIFICACIÓN" readonly="" onkeyup="if (/\D/g.test(this.value))
                                                            this.value = this.value.replace(/\D/g, '')">
                                                <span id="erroridentificaciion" style="color: #f00;"></span>
                                            </div>
                                            <div class="col-sm-3">
                                                <label>Nombres</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="modEditNonbresRef" style="text-transform:uppercase" class="form-control" maxlength="50" required="" name="txtRefNombres" placeholder="nombres" readonly>

                                            </div>
                                            <div class="col-sm-3">
                                                <label>Apellidos</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="modEditApellidoRef"  style="text-transform:uppercase" class="form-control" maxlength="50" required="" name="txtRefApellidos" placeholder="apellidos" readonly>
                                            </div>
                                        </div>
                                        <br>
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <label>Parentesco</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                <input type="text" id="modEditParentescoRef"  style="text-transform:uppercase" class="form-control" maxlength="50" required=""  placeholder="parentesco" readonly name="txtRefParentesco">                               
                                            </div>

                                            <div class="col-sm-3">
                                                <label>Teléfono</label>
                                                <input type="text" id="modEditTelefonoRef" style="text-transform:uppercase" class="form-control"  maxlength="50" name="txtReftelefono" placeholder="teléfono" readonly>          

                                            </div>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <label>Celular</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                                    <input type="text" id="modEditcelularRef" style="text-transform:uppercase" class="form-control" maxlength="50" required="" name="txtRefCelular" placeholder="celular" readonly>
                                                </div>
                                            </div>
                                        </div>
                                        <br>
                                        <center>
                                            <!--button type="button" class="btn btn-secondary btn-flat m-b-30 m-t-30" id="btnCancelar"><i class="fa fa-times"></i>&nbsp; Cancelar</button-->                        
                                            <button type="button" class="btn btn-primary btn-flat m-b-30 m-t-30" id="btnGuardar"><i class="fa fa-save"></i>&nbsp; Guardar</button
                                        </center>
                                    </form>
                                </div>
                            </div>
                        </section>
                    </div>

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
        <script src="<%=request.getContextPath()%>/Administracion/js/actualizardatos.js"></script>



        <!-- inline scripts related to this page -->
        <script type="text/javascript">

                                                    $(document).ready(function () {
                                                        var valeditcorreo = true, valeditidentificacion = true;

                                                        $('#bootstrap-data-table').DataTable({
                                                            "scrollX": true,
                                                            "language": {
                                                                "url": "/UECGV/Administracion/plugins/dataTable/Spanish.json"
                                                            }
                                                        });

                                                    });

        </script>

        <script src="<%=request.getContextPath()%>/js/toastr.js" type="text/javascript"></script>
        <link href="<%=request.getContextPath()%>/css/toastr.min.css" rel="stylesheet" type="text/css"/>

        <script src="/UECGV/Administracion/plugins/alertify/alertify.min.js" type="text/javascript"></script>
        <link href="/UECGV/Administracion/plugins/alertify/alertify.min.css" rel="stylesheet" type="text/css"/>
        <link href="/UECGV/Administracion/plugins/alertify/default.min.css" rel="stylesheet" type="text/css"/>

        <%@include file="/Administracion/FormulariosModales.jspf"%>

        <script src="/UECGV/Administracion/js/Validaciones.js" type="text/javascript"></script>
        <script src="/UECGV/Administracion/js/CRUDpersonas.js" type="text/javascript"></script>


        <script src="/UECGV/Administracion/plugins/dataTable/jquery.dataTables.min.js" type="text/javascript"></script>
        <link href="/UECGV/Administracion/plugins/dataTable/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>

    </body>
</html>
