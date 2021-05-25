<%-- 
    Document   : actualizardatos
    Created on : 23-feb-2021, 8:11:53
    Author     : DHL-SIS-ING
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="estructura/head_pagina.jspf" %>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/normalize.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link href='http://fonts.googleapis.com/css?family=PT+Sans+Narrow' rel='stylesheet' type='text/css'>
        <link href='http://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="css/style.css">
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="js/jquery-1.11.2.min.js"><\/script>');</script>
        <script src="js/modernizr.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/main.js"></script>
        <script src="js/actualizardatos.js"></script>
        <script src="https://momentjs.com/downloads/moment.js"></script>
        <script src="js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <link href="css/toastr.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/toastr.js" type="text/javascript"></script>
    </head>
    <body>
        <!--======================================== Boton ir arriba ========================================-->
        <i class="btn-up fa fa-arrow-circle-o-up hidden-xs"></i>
        <!--======================================== Navegación ========================================-->
        <%@include file="estructura/encabezado_presentacion.jspf" %>
        
        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="index.jsp">Inicio</a>
                <li>
                    <a href="actualizardatos.jsp">Actualizar Datos - Estudiante</a>
                </li>
            </ul>
        </div>
        <!--======================================== Contenido de la pagina ========================================-->
        <section id="section-datos" class="full-reset mb-200" style="background-color: #fff; padding: 20px 0;">
            <div class="container">
                <div class="row">
                    <section class="col-xs-12">
                        <article class="full-reset">
                            <div class="page-header" style="margin-bottom:20px;">
                                <h1><i class="fa fa fa-user"></i> &nbsp; Actualizar Datos - Estudiante</h1>
                            </div>
                            <div class="abs-center">
                                <fieldset>
                                    <form>
                                        <div class="form-group">
                                            <label>Ingrese su número de identificación</label>
                                            <input type="text" class="form-control" id="txtIdentificacion" name="txtBuscar" placeholder="Cédula/Pasaporte"  onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')">
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
                <div class="row mt-25 form-datos" hidden>
                    <button id="btnModificar" type="button" class="btn btn-primary"><i class="fa fa-pencil"></i>&nbsp; Modificar</button>
                    <br/><label class="text-danger campo-obligatorios">Campos obligatarios (*)</label>
                    <form id="formEstudiante" autocomplete="off" method="post" enctype="multipart/form-data" class="mt-25" >
                        <div class="row">
                            <div class="col-sm-4">
                                <img id="regImgAlum" src="" height="250"/>
                                <br>
                                <label>Foto</label>
                                <input type="file" id="regFotoAlu" name="regFotoAlu" class="form-control" name="txtRegFoto" placeholder="Foto" disabled accept="image/jpg, image/png">
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
                                <input type="text" style="text-transform:uppercase" id="regidentificacionAlu" class="form-control"  maxlength="20" required="" name="txtRegIdentificacion" placeholder="Identificacion" readonly onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')">
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
                                <input type="file" id="fotoDomicilioAlu" class="form-control"  name="txtRegFotoDomicilio" placeholder="Foto" disabled accept="image/jpg, image/png">
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
                                <input type="text" id="regIdentificacionRep" class="form-control"  maxlength="20"  required=""  name="txtRegFamiliarIdentificacionPadre" placeholder="IDENTIFICACIÓN" readonly onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')">
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
                                <input type="text" id="regOcupacionRep" style="text-transform:uppercase"  class="form-control"  maxlength="50"  required="" name="txtRegFamiliarOcupacionPadre" placeholder="Ocupación" readonly>
                            </div>
                            <div class="col-sm-3">
                                <label>Lugar de Trabajo</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                <input type="text" id="regTrabajoRep" style="text-transform:uppercase"  class="form-control" maxlength="50" onkeypress="return soloLetrasNumerosCaracEspe()(event)" required="" name="txtRegFamiliarLugarPadre" placeholder="Lugar de Trabajo" readonly>
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
                                <input type="text" id="regIdentificacionMad" class="form-control"  maxlength="20"  required=""  name="txtRegFamiliarIdentificacionMadre" placeholder="IDENTIFICACIÓN" readonly onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')">
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
                                <input type="text" id="regOcupacionMad" style="text-transform:uppercase"  class="form-control"   required="" name="txtRegFamiliarOcupacionMadre" placeholder="Ocupación" readonly maxlength="50">
                            </div>
                            <div class="col-sm-3">
                                <label>Lugar de Trabajo</label><label class="text-danger campo-obligatorios">&nbsp;*</label>
                                <input type="text" id="regTrabajoMad" style="text-transform:uppercase"  class="form-control"  required="" name="txtRegFamiliarLugarMadre" placeholder="Lugar de Trabajo" readonly maxlength="50">
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
                                <input type="text" id="regIdentificacionRef" class="form-control"  maxlength="20"   name="txtRefFamiliarIdentificacionRef" placeholder="IDENTIFICACIÓN" readonly="" onkeyup="if (/\D/g.test(this.value)) this.value = this.value.replace(/\D/g,'')">
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
                            <button type="button" class="btn btn-secondary btn-flat m-b-30 m-t-30" id="btnCancelar"><i class="fa fa-times"></i>&nbsp; Cancelar</button>                        
                            <button type="button" class="btn btn-primary btn-flat m-b-30 m-t-30" id="btnGuardar"><i class="fa fa-save"></i>&nbsp; Guardar</button
                        </center>
                    </form>
                </div>
            </div>
        </section>
        <!--======================================== Pie de pagina ========================================-->
        <%@include file="estructura/pie_pagina_presentacion.jspf" %>
        <script src="http://www.openlayers.org/api/OpenLayers.js"></script>
    </body>
</html>