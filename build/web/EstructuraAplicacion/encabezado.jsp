<script type="text/javascript">
    /* Coeminza el script del Reloj */
    function actualizaReloj() {
        marcacion = new Date();
        // fecha= marcacion.getDay() +':'+ marcacion.getMonth() +':'+ marcacion.getYear();
        Hora = marcacion.getHours();
        Minutos = marcacion.getMinutes();
        Segundos = marcacion.getSeconds();

        if (Hora <= 9)
            Hora = "0" + Hora;

        if (Minutos <= 9)
            Minutos = "0" + Minutos;

        if (Segundos <= 9)
            Segundos = "0" + Segundos;

        var Inicio, Script, Final, Total;
        
        Inicio = "<font size='3' color='white'>";

        Script = Hora + ":" + Minutos + ":" + Segundos;
        
       
        Final = "</font>";

        Total = Inicio + Script + Final;

        document.getElementById("reloj").innerHTML = Total;
    }
    var int = self.setInterval("actualizaReloj()", 1000);
    
</script>
<!--style=" background:#009549;"-->
<div id="navbar" class="navbar navbar-default    navbar-collapse       h-navbar ace-save-state" >
    <div class="navbar-container ace-save-state" id="navbar-container">
        <div class="navbar-header pull-left">

            <a href="<%=request.getContextPath()%>/MenuAplicacion/menu.jsp" class="navbar-brand">
                <small>

                    <i class="fa fa-cogs"></i>
                    U.E.M Carmelina Granja Villanueva
                </small>
            </a>
            <!--
            <button class="pull-right navbar-toggle navbar-toggle-img collapsed" type="button" data-toggle="collapse" data-target=".navbar-buttons,.navbar-menu">
                <span class="sr-only">Toggle user menu</span>
                
                <img src="../assets/img/user.jpg" />
                
            </button>
            -->
            <button class="pull-right navbar-toggle collapsed" type="button" data-toggle="collapse" data-target=".navbar-buttons,.navbar-menu">
                <span class="sr-only">Toggle sidebar</span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>
            </button>
        </div>

        <div class="navbar-buttons navbar-header pull-right  collapse navbar-collapse" role="navigation">
            <ul class="nav ace-nav">
                <!--style="background:#009549;"-->
                <li class="light-blue dropdown-modal user-min" >
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle" >

                        <div id="reloj"></div>
                    </a>
                </li>
                <li class="light-blue dropdown-modal user-min" >
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle" >

                        ${sessionScope.apellidos} ${sessionScope.nombres}
                        <%--
                        
                        <c:if test="${sessionScope.idpersona == null}" >
                            <meta http-equiv="Refresh" content="0;/DomoticaUTEQ/login.jsp">   
                        </c:if>
                        --%>
                    </a>
                </li>




                
                <!--style="background:#009549;"-->
                <li class="light-blue dropdown-modal user-min" >
                    
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <i class="ace-icon fa fa-user"></i>
                    
                        <span class="user-info">
                            <small>Bienvenido/a,</small>
                            
                        </span>
                    
                        <i class="ace-icon fa fa-caret-down"></i>
                    </a>

                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <!--
                        <li>
                            <a href="#cambiarClave" role="button" data-toggle="modal">
                                <i class="ace-icon fa fa-key"></i>
                                Cambiar la clave
                            </a>
                        </li>
                        
                        <li>
                            <a href="#modalEditUser" role="button" data-toggle="modal" onclick="editDatosEncabezado('${sessionScope.identificacionModEdit}', '${sessionScope.nombres}', '${sessionScope.apellidos}', '${sessionScope.emailModEdit}', '${sessionScope.telefonoModEdit}', '${sessionScope.direccionModEdit}')">
                                <i class="ace-icon fa fa-user"></i>
                                Actualizar datos
                            </a>
                        </li>
                        
                        <li class="divider"></li>
                        -->
                        <li>
                            <a href="/UECGV/srvUsuario?accion=cerrar_sesion">
                                <i class="ace-icon fa fa-power-off"></i>
                                Cerrar la sesión
                            </a>
                        </li>
                    </ul>
                </li>






            </ul>
        </div>

        <nav role="navigation" class="navbar-menu pull-left collapse navbar-collapse">
            <ul class="nav navbar-nav">

            </ul>
        </nav>
    </div><!-- /.navbar-container -->
</div>

<!Carlos Muñoz-->
<!Colocar en todas las paginas-->
<script src="<%=request.getContextPath()%>/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/Administracion/js/Validaciones.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/toastr.js" type="text/javascript"></script>
<link href="<%=request.getContextPath()%>/js/toastr.min.css" rel="stylesheet" type="text/css"/>
<%--<%@include file="../Administracion/validarSesion.jspf" %>  --%>
<%@include file="../Administracion/CambiarClave.jspf"%>  
<%@include file="../Administracion/modalEditUser.jspf"%> 
<!--<script src="<%=request.getContextPath()%>/assetsmenu/js/push.min.js"></script>-->



